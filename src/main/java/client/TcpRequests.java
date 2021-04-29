package client;

import utils.ServerDTO;
import utils.TcpSocketWrapper;

import java.io.IOException;


public class TcpRequests {
    static String ip = null;
    static int port = 8001;

    public static String createRequest(String request) {

        String response = null;
        try {
            TcpSocketWrapper socketWrapper = new TcpSocketWrapper(ip, port);
            socketWrapper.writeLine(request);
            response = socketWrapper.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    public static void createPolling(ServerDTO server) {
        ip = server.getIp();
        Runnable pollingTask = () -> {
            while (true) {
                createRequest("ping");
                try {
                    Thread.sleep(20_000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread pollingThread = new Thread(pollingTask);
        pollingThread.start();
    }
}
