package client;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import utils.ServerDTO;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class UdpRequests {
    public final static int SERVICE_PORT = 8000;

    public static List<ServerDTO> getServers() {
        List<ServerDTO> servers = new ArrayList<>();
        Gson gson = new Gson();
        try {
            DatagramSocket clientSocket = new DatagramSocket();

            InetAddress IPAddress = InetAddress.getByName("255.255.255.255");

            byte[] sendingDataBuffer;
            byte[] receivingDataBuffer = new byte[1024];

            String sentence = "getServers";
            sendingDataBuffer = sentence.getBytes();


            DatagramPacket sendingPacket = new DatagramPacket(sendingDataBuffer, sendingDataBuffer.length, IPAddress, SERVICE_PORT);


            clientSocket.send(sendingPacket);
            DatagramPacket receivingPacket = new DatagramPacket(receivingDataBuffer, receivingDataBuffer.length);
            clientSocket.receive(receivingPacket);
            String receivedData = new String(receivingPacket.getData());
            servers = (ArrayList<ServerDTO>) gson.fromJson(receivedData.trim(), new TypeToken<ArrayList<ServerDTO>>() {}.getType());

            clientSocket.close();
        } catch (SocketException | UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Thread.sleep(1_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return servers;
    }
}


