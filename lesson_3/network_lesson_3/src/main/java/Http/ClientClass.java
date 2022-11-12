package Http;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

public class ClientClass {
    public static void asynchronousPostRequest(String uri) throws URISyntaxException {
        final HttpClient client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .build();
        final HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(uri))
                .version(HttpClient.Version.HTTP_2)
                .POST(HttpRequest.BodyPublishers.ofString("login=java&password=javaPassword"))
                .build();
        CompletableFuture<Void> futureRespose = client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenAccept(resp->{
                    System.out.println("Response from: " + resp.uri());
                    System.out.println("Response statuscode: " +resp.statusCode());
                    System.out.println("Response body: " + resp.body());
                });
        System.out.println("futureResponse" + futureRespose);

    }
}
