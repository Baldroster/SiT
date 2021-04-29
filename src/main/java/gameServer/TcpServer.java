package gameServer;

import utils.ServerDTO;
import utils.TcpSocketWrapper;

import java.io.IOException;
import java.net.ServerSocket;
import java.time.LocalDateTime;


public class TcpServer {
    private static ServerDTO serverDTO = new ServerDTO( "192.168.31.22", 8001, 0);
    private static LocalDateTime lastRequest = LocalDateTime.now();

    public static void startTcpServer() {

        Runnable serverTask = () -> {
            try {
                try (ServerSocket server = new ServerSocket(serverDTO.getPort())) {
                    checkConnection();

                    System.out.println("Server started!");

                    while (true) {

                        TcpSocketWrapper tcpSocketWrapper = new TcpSocketWrapper(server);
                        String request = tcpSocketWrapper.readLine();
                        System.out.println(request);
                        String response = GameLogic.checkCommand(request);
                        tcpSocketWrapper.writeLine(response);
                        serverDTO.setLoad(1);
                        lastRequest = LocalDateTime.now();

                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        };
        Thread serverThread = new Thread(serverTask);
        serverThread.start();
    }

    public static ServerDTO getServerDTO() {
        return serverDTO;
    }

    private static void checkConnection() {
        Runnable timeTask = () -> {
            if (lastRequest.plusMinutes(3).isBefore(LocalDateTime.now())) {
                serverDTO.setLoad(0);
            }
        };
        Thread timeThread = new Thread(timeTask);
        timeThread.start();
    }
}

