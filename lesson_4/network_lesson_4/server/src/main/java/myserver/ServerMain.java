package myserver;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ServerMain {
    public static void main(String[] args) throws IOException{

        byte[] buf = new byte[256];
        DatagramPacket packet = new DatagramPacket(buf, buf.length);
        DatagramSocket socket = new DatagramSocket(50_000);
        while (true) {
            socket.receive(packet);
            String received = new String(packet.getData(), 0, packet.getLength());
            if ("Stop".equals(received)) break;
            InetAddress address = packet.getAddress();
            int port = packet.getPort();
            System.out.println("Received: " + received);
            packet = new DatagramPacket(buf, buf.length, address, port);
            socket.send(packet);
            socket.close();
        }

    }
}
