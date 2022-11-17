package Http;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.BufferedReader;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import static java.nio.charset.StandardCharsets.UTF_8;

public class ExampleHTTPHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        switch (exchange.getRequestMethod()) {
            case "GET":
                handleRequest(exchange, "Hello from GET Method");
                break;
            case "POST":
                postRequest(exchange);
//                handleRequest(exchange, "Hello from POST Method");
                break;
            default:
                throw new UnsupportedOperationException("Unsupported HTTP Method: " + exchange.getRequestMethod());

        }
    }
    private void postRequest (HttpExchange exchange) throws IOException {
        Map<String, String> inputs = parseFormData(exchange);
        String login = inputs.get("login");

        if (!"java".equals(login)) throw new InvalidLoginException("Некорректный логин,");
        handleRequest(exchange, "Hello Java");
    }
    private void handleRequest(HttpExchange exchange, String respose) throws IOException {
        exchange.sendResponseHeaders(200, respose.length());

        try (final var responseBody = exchange.getResponseBody()){
            responseBody.write(respose.getBytes(UTF_8));
            responseBody.flush();
        }
    }

    private static Map<String, String> parseFormData(final HttpExchange exchange) throws IOException {
        String requestBody = new BufferedReader(new InputStreamReader(exchange.getRequestBody(), UTF_8)).readLine();
        return Arrays.stream(requestBody.split("&"))
                .map(pair -> pair.split("="))
                .collect(Collectors.toMap(pairArr -> pairArr[0], pairArr -> URLDecoder.decode(pairArr[1], UTF_8), (a, b) -> b));
    }
}
