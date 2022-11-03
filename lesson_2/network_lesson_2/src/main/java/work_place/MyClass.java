package work_place;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class MyClass {
    public static void main(String[] args) throws IOException {
        URL address = new URL("https://sber.ru/");
        URLConnection connection = address.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(
                connection.getInputStream()
        ));
        System.out.println("Размер ресурса: " + connection.getContentLength());
        System.out.println("Тип ресурса: " + connection.getContentType());
        System.out.println("Содержимое ресурса:");
        String inputline;
        while ((inputline = in.readLine()) != null)
            System.out.println(inputline);
    }
}
