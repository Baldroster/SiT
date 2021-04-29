package utils;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpSocketWrapper implements Closeable {

    private final Socket socket;
    private final BufferedReader reader;
    private final BufferedWriter writer;


    public TcpSocketWrapper(ServerSocket server) throws IOException {


        this.socket = server.accept();
        this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));


    }

    public TcpSocketWrapper(String ip, int port) throws IOException {

        this.socket = new Socket(ip, port);
        this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));


    }

    public void writeLine(String message) {
        try {
            writer.write(message);
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String readLine() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getIP() {
        return socket.getInetAddress().getHostAddress();
    }


    @Override
    public void close() throws IOException {
        writer.close();
        reader.close();
        socket.close();
    }
}
