package no.kristiania.pgr200.http;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class HttpPathTest {

    @Test
    public void shouldSeparatePathAndQuery() {
        HttpPath path = new HttpPath("/echo?status=200");
        assertThat(path.getPath()).isEqualTo("/echo");
        assertThat(path.getQuery()).isEqualTo("status=200");
        assertThat(path.toString()).isEqualTo("/echo?status=200");
        assertThat(path.getParameter("status")).hasValue("200");
    }

    @Test
    public void shouldHandlePathWithoutQuery() {
        HttpPath path = new HttpPath("/favicon.ico");
        assertThat(path.getPath()).isEqualTo("/favicon.ico");
        assertThat(path.getQuery()).isNull();
        assertThat(path.toString()).isEqualTo("/favicon.ico");
    }

    @Test
    public void shouldHandleMultipleParameters() {
        HttpPath path = new HttpPath("/echo?status=307&body=hello+bl%C3%A5b%C3%A6r");
        assertThat(path.getQuery()).isEqualTo("status=307&body=hello+bl%C3%A5b%C3%A6r");
        assertThat(path.getParameter("status")).hasValue("307");
        assertThat(path.getParameter("body")).hasValue("hello blåbær");
    }

    @Test
    public void shouldSetParameters() {
        HttpPath path = new HttpPath("/echo");
        path.setParameter("status", "200");
        assertThat(path.getQuery()).isEqualTo("status=200");
        assertThat(path.toString()).isEqualTo("/echo?status=200");
    }

    @Test
    public void shouldParseUrl() {
        HttpPath path = new HttpPath("/myapp/echo?status=402&body=vi%20plukker%20bl%C3%A5b%C3%A6r");
        assertThat(path.getPath()).isEqualTo("/myapp/echo");
        assertThat(path.getPathParts()).containsExactly("myapp", "echo");
        assertThat(path.getParameter("status")).hasValue("402");
        assertThat(path.getParameter("body")).hasValue("vi plukker blåbær");
    }

}
