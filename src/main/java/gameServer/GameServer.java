package gameServer;

import java.io.IOException;


public class GameServer {


    public static void main(String[] args) throws IOException {
        TcpServer.startTcpServer();
        UdpClient.sendServerLoad();
    }
}