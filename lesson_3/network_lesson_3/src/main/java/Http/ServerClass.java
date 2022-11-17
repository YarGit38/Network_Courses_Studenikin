package Http;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;

public class ServerClass {
    public static void main(String[] args) throws IOException, URISyntaxException {
        final var server = HttpServer.create(new InetSocketAddress("localhost", 8080), 0);
        server.setExecutor(Executors.newFixedThreadPool(10));
        server.createContext("/account", new ExampleHTTPHandler());
        server.start();
        new ClientClass().asynchronousPostRequest("http://localhost:8080/account");
    }


}
