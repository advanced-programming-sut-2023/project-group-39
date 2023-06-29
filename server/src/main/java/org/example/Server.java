package org.example;

import java.io.IOException;
import java.net.ServerSocket;

public class Server {
    public Server(int port) throws IOException {
        System.out.println("starting server");
        ServerSocket serverSocket = new ServerSocket(port);
    }
}
