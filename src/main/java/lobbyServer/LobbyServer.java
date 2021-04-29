package lobbyServer;

import com.google.gson.Gson;
import utils.ServerDTO;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;

public class LobbyServer {
    // Серверный UDP-сокет запущен на этом порту
    public final static int SERVICE_PORT = 8000;
    public  static ArrayList<ServerDTO> serverList = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        try {
            while(true) {
            DatagramSocket serverSocket = new DatagramSocket(SERVICE_PORT);
            byte[] receivingDataBuffer = new byte[1024];
            byte[] sendingDataBuffer = new byte[102400];

                DatagramPacket inputPacket = new DatagramPacket(receivingDataBuffer, receivingDataBuffer.length);
                serverSocket.receive(inputPacket);
                String receivedData = new String(inputPacket.getData());
                InetAddress senderAddress = inputPacket.getAddress();
                int senderPort = inputPacket.getPort();
                if (receivedData.trim().equals("getServers")) {
                    Gson gson = new Gson();
                    String text =gson.toJson(serverList);
                    sendingDataBuffer = text.getBytes();
                    DatagramPacket outputPacket = new DatagramPacket(
                            sendingDataBuffer, sendingDataBuffer.length,
                            senderAddress, senderPort);
                    serverSocket.send(outputPacket);
                } else {
                    serverList.add(new ServerDTO(senderAddress.getHostAddress(), senderPort, Integer.parseInt(receivedData.trim())));
                    for (int i = 0; i < serverList.size() - 1; i++) {
                        if (serverList.get(i).getIp().equals(serverList.get(serverList.size() - 1).getIp())) {
                            serverList.get(i).setLoad(serverList.get(serverList.size() - 1).getLoad());
                            serverList.remove(serverList.size() - 1);
                        }
                    }
                }
                serverSocket.close();
            }
            } catch(SocketException e){
                e.printStackTrace();
            }


    }
}
