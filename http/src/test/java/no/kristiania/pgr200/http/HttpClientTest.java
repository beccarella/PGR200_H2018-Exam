package no.kristiania.pgr200.http;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

import no.kristiania.pgr200.http.client.HttpGetRequest;
import no.kristiania.pgr200.http.client.HttpResponse;
import org.junit.Test;

public class HttpClientTest {

    @Test
    public void shouldExecuteHttpRequest() throws IOException {
        HttpGetRequest request = new HttpGetRequest("urlecho.appspot.com", 80, "/echo");
        HttpResponse response = request.execute();
        assertThat(response.getStatusCode()).isEqualTo(200);
    }

    @Test
    public void shouldParseStatusLine() throws IOException {
        HttpGetRequest request = new HttpGetRequest("urlecho.appspot.com", 80, "/echo?status=404");
        HttpResponse response = request.execute();
        assertThat(response.getStatusCode()).isEqualTo(404);
        assertThat(response.getStatusText()).isEqualTo("Not Found");
    }

    @Test
    public void shouldParseHeaders() throws IOException {
        HttpGetRequest request = new HttpGetRequest("urlecho.appspot.com", 80, "/echo?Location=http%3A%2F%2Fwww.google.com");
        HttpResponse response = request.execute();
        assertThat(response.getHeader("location")).isEqualTo("http://www.google.com");
    }

    @Test
    public void shouldReadBody() throws IOException {
        HttpGetRequest request = new HttpGetRequest("urlecho.appspot.com", 80,
                "/echo?body=hello+world");
        HttpResponse response = request.execute();
        assertThat(response.getBody()).isEqualTo("hello world");

    }

}
