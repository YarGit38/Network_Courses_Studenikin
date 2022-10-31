import java.io.*;
import java.net.*;
import java.util.*;
public class MyClass {

    public static void main(String[] args) throws SocketException {
        Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces();

        for (NetworkInterface mynet : Collections.list(nets)) {
            System.out.println("Display name: " + mynet.getDisplayName());
//            System.out.println("Name: " + mynet.getName());
            if (mynet.isUp())
                System.out.println("Активен");
            else System.out.println("Не активен");

            if (mynet.isLoopback())
                System.out.println("Является интерфейсом loopback");
            else System.out.println("Не является интерфейсом loopback");

            if (mynet.isVirtual())
                System.out.println("Является виртуальным интерфейсом");
            else System.out.println("Не является виртуальным интерфейсом");

            System.out.println(" ");
        }
    }
}
