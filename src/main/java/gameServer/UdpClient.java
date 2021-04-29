package gameServer;

import java.io.IOException;
import java.net.*;

public class UdpClient {
    public final static int SERVICE_PORT = 8000;
    public static void sendServerLoad()  {
        Runnable task = () -> {
            while(true) {
                try {
                    DatagramSocket clientSocket = new DatagramSocket();

                    InetAddress IPAddress = InetAddress.getByName("255.255.255.255");

                    byte[] sendingDataBuffer;
                    byte[] receivingDataBuffer = new byte[8];

                    String sentence = TcpServer.getServerDTO().getLoad().toString();
                    sendingDataBuffer = sentence.getBytes();


                    DatagramPacket sendingPacket = new DatagramPacket(sendingDataBuffer, sendingDataBuffer.length, IPAddress, SERVICE_PORT);


                    clientSocket.send(sendingPacket);

                    DatagramPacket receivingPacket = new DatagramPacket(receivingDataBuffer, receivingDataBuffer.length);
                    clientSocket.close();
                } catch (SocketException | UnknownHostException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    Thread.sleep(10_000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread udpThread = new Thread(task);
        udpThread.start();
    }

}
