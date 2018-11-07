package no.kristiania.pgr200.http;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

import org.junit.BeforeClass;
import org.junit.Test;

public class HttpServerTest {

    private static HttpEchoServer server;

    @BeforeClass
    public static void createServer() throws IOException {
        server = new HttpEchoServer(0);
    }


    @Test
    public void shouldExecuteHttpRequest() throws IOException {
        HttpRequest request = new HttpRequest("localhost", server.getPort(), "/echo");
        HttpResponse response = request.execute();
        assertThat(response.getStatusCode()).isEqualTo(200);
    }

    @Test
    public void shouldSetStatusLine() throws IOException {
        HttpRequest request = new HttpRequest("localhost", server.getPort(), "/echo?status=404");
        HttpResponse response = request.execute();
        assertThat(response.getStatusCode()).isEqualTo(404);
        assertThat(response.getStatusText()).isEqualTo("Not Found");
    }

    @Test
    public void shouldParseHeaders() throws IOException {
        HttpRequest request = new HttpRequest("localhost", server.getPort(),
                "/echo?Location=http%3A%2F%2Fwww.google.com");
        HttpResponse response = request.execute();
        assertThat(response.getHeader("location")).isEqualTo("http://www.google.com");
    }

    @Test
    public void shouldReadBody() throws IOException {
        HttpRequest request = new HttpRequest("localhost", server.getPort(),
                "/echo?body=hello+world");
        HttpResponse response = request.execute();
        assertThat(response.getBody()).isEqualTo("hello world");
    }


    @Test
    public void shouldPostData() throws IOException {
        HttpRequest request = new HttpRequest("localhost", server.getPort(),
                "/echo");
        request.setMethod("POST");
        request.setFormBody(new HttpQuery().put("body", "hello world").put("status", "202"));
        HttpResponse response = request.execute();
        assertThat(response.getBody()).isEqualTo("hello world");
    }

    @Test
    public void shouldReturn500ForPostWithoutData() throws IOException {
        HttpRequest request = new HttpRequest("localhost", server.getPort(),
                "/echo");
        request.setMethod("POST");
        HttpResponse response = request.execute();
        assertThat(response.getStatusCode()).isEqualTo(500);
    }

}
