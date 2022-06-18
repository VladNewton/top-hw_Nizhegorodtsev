package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class StartServer {
    public static void main(String[] args) throws IOException {
//        ServerSocket serverSocket = new ServerSocket(8080);
//        Socket socket = serverSocket.accept();
//        new MonoThreadClientHandler(socket).run();
        MultiThreadServer.run();
    }
}
