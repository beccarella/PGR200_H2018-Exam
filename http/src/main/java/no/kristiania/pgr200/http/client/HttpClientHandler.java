package no.kristiania.pgr200.http.client;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.OutputStream;

public class HttpClientHandler {
    InputStream input = null;
    BufferedReader reader = null;
    OutputStream output = null;

    public HttpClientHandler() {

    }

    /*public static void main(String[] args) {

        switch (args[0].toLowerCase ()){
            case "list":
                request.method = "GET";
                break;
            case "add":
                request.setMethod ( "POST" );
                break;
        }

        System.out.println ("this is a method: " + request.method);

        if(request.method == "GET"){

        }
        try {
            new HttpRequest ( "localhost", 8081, "/echo" ).execute();
            //System.out.println ("You are connected!");
        } catch (IOException e){
            System.err.println ("Error getting a http-connection: " + e.getMessage () + ", Cause of error: " + e.getCause ());
        } catch (SQLException e){
            System.out.println ("SQLException: " + e.getMessage ());
        }
    }*/
}


