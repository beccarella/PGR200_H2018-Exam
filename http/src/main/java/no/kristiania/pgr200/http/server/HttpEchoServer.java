package no.kristiania.pgr200.http.server;

import no.kristiania.pgr200.database.DatabaseConnection;
import no.kristiania.pgr200.database.dao.ConferenceTalkDao;
import no.kristiania.pgr200.http.client.HttpIO;

import javax.sql.DataSource;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class HttpEchoServer {

	static DataSource dataSource;
	private ServerSocket serverSocket;

	public HttpEchoServer(DataSource dataSource, int port) throws IOException {
		serverSocket = new ServerSocket(port);
		this.dataSource = dataSource;

		new Thread(this::runServerThread).start();
	}

	private void runServerThread() {
		while (true) {
			Socket clientSocket = null;
			try {
				clientSocket = serverSocket.accept();
				handleRequest(clientSocket);
			} catch (RuntimeException e) {
				if (clientSocket != null) {
					try {
						clientSocket.close();
					} catch (IOException ioEx) {
						e.printStackTrace();
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void handleRequest(Socket clientSocket) throws IOException {
		String statusCode = null;
		String body = null;

		HttpQuery query = null;
		HttpHeader responseHeaders = new HttpHeader();

		try {
			String requestLine = HttpIO.readLine(clientSocket.getInputStream());
			String requestTarget = requestLine.split(" ")[1];
			HttpPath path = new HttpPath(requestTarget);

			HttpHeader headers = new HttpHeader();
			headers.readHeaders(clientSocket.getInputStream());

			if (requestLine.split(" ")[0].equals("POST")) {
				query = new HttpQuery(HttpIO.readBody(clientSocket.getInputStream(), headers.getContentLength()));
			} else {
				query = path.query();
			}

			ConferenceTalkDao dao = new ConferenceTalkDao(dataSource);

			statusCode = query.get("status").orElse("200");
//            body = dao.listAll().toString();
			body = query.get("body").orElse("None");
		} catch (RuntimeException e) {
			e.printStackTrace();
			writeResponseLine(clientSocket, "500");
			responseHeaders.writeTo(clientSocket.getOutputStream());
			return;
//        } catch (SQLException e) {
//            e.printStackTrace();
		}

		writeResponseLine(clientSocket, statusCode);
		responseHeaders.put("Content-Type", "text/plain; charset=utf-8").setContentLength(body.length());
		query.get("Location").ifPresent(value -> {
			responseHeaders.put("Location", value);
		});

		responseHeaders.writeTo(clientSocket.getOutputStream());

		clientSocket.getOutputStream().write(body.getBytes());
	}

	public void writeResponseLine(Socket clientSocket, String statusCode) throws IOException {
		String statusMessage = getStatusMessage(statusCode);
		HttpIO.writeLine(clientSocket.getOutputStream(), "HTTP/1.1 " + statusCode + " " + statusMessage);
	}

	private static Map<String, String> statusMessages = new HashMap<>();
	static {
		statusMessages.put("200", "OK");
		statusMessages.put("202", "Accepted");
		statusMessages.put("400", "Bad Request");
		statusMessages.put("403", "Forbidden");
		statusMessages.put("404", "Not Found");
		statusMessages.put("500", "Internal Server Error");
		statusMessages.put("503", "Service Unavailable");
		statusMessages.put("504", "Gateway Timeout");
	}

	private String getStatusMessage(String statusCode) {
		return statusMessages.get(statusCode);
	}

	public static void main(String[] args) throws IOException {
		new HttpEchoServer(DatabaseConnection.createDataSource(), 10080);
	}

	public int getPort() {
		return serverSocket.getLocalPort();
	}

}
