package myclient;

import java.io.IOException;
import java.net.*;

public class ClientMain {
    public static void main(String[] args) throws IOException{
        InetAddress address = InetAddress.getByName("localhost");
        DatagramSocket socket = new DatagramSocket();
            DatagramPacket packet = answer("Hello", socket, address);
            socket.receive(packet);
            String received = new String(
                    packet.getData(), 0, packet.getLength());
            System.out.println("Received: " + received);
            answer("Stop", socket, address);


    }

    private static DatagramPacket answer(final String command, final DatagramSocket socket, final InetAddress address) throws IOException {
        byte[] buf = command.getBytes();
        DatagramPacket packet = new DatagramPacket(buf, buf.length, address, 50_000);
        socket.send(packet);
        return packet;
    }

}
