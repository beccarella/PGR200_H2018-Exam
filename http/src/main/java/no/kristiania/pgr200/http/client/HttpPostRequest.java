package no.kristiania.pgr200.http.client;

import no.kristiania.pgr200.http.server.HttpQuery;

public class HttpPostRequest {

    private String host;
    private int port;
    private String path;

    private HttpQuery formQuery = new HttpQuery();

    public HttpPostRequest(String host, int port, String path) {
        this.host = host;
        this.port = port;
        this.path = path;
    }

    public String getUrl() {
        return "http://" + host + ":" + port + path;
    }

    public HttpQuery getFormQuery() {
        return formQuery;
    }
}
