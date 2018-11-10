package no.kristiania.pgr200.http.client;

import no.kristiania.pgr200.http.server.HttpHeader;
import no.kristiania.pgr200.http.server.HttpQuery;

import java.io.IOException;
import java.net.Socket;

public class HttpRequest {

    private String hostname;
    private int port;
    private String requestTarget;
    private String method = "GET";
    private HttpHeader httpHeader;
    private String body;

    public HttpRequest(String hostname, int port, String requestTarget) {
        this.hostname = hostname;
        this.port = port;
        this.requestTarget = requestTarget;
        this.httpHeader = new HttpHeader()
                .put("Connection", "close")
                .put("Host", hostname);
    }

    public static void main(String[] args) throws IOException {
        new HttpRequest("urlecho.appspot.com", 80, "/echo").execute();
    }

    public HttpResponse execute() throws IOException {
        try (Socket socket = new Socket(hostname, port)) {
            writeRequestLine(socket);

            if (body != null) {
                httpHeader.setContentLength(body.getBytes().length);
            }
            httpHeader.writeTo(socket.getOutputStream());
            if (body != null) {
                socket.getOutputStream().write(body.getBytes());
            }

            return new HttpResponse(socket);
        }
    }

    public void writeRequestLine(Socket socket) throws IOException {
        HttpIO.writeLine(socket.getOutputStream(), method + " " + requestTarget + " HTTP/1.1");
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public void setFormBody(HttpQuery query) {
        this.body = query.toString();
        httpHeader.put("Content-type", "application/x-www-form-urlencoded");
    }
}
