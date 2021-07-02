package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Locale;

public class Server {

    public static void main(String[] args) throws IOException {
        System.out.println("Server started");

        int port = 8089;

        while (true) {
            ServerSocket serverSocket = new ServerSocket(port);
            Socket clientSocket = serverSocket.accept();
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            System.out.printf("New connection accepted. Port %d%n", clientSocket.getPort());
            out.println("Hello, write your name please:");
            final String name = in.readLine();
            out.println(String.format("Hi %s, are you child? (only yes/no answers)", name));
            final String answer = in.readLine();
            switch (answer.toLowerCase(Locale.ROOT)) {
                case "yes" -> out.println(String.format("Welcome to the kids area, %s! Let's play!", name));
                case "no" -> out.println(String.format("Welcome to the adult zone, %s!" +
                        " Have a good rest, or a good working day!", name));
                default -> out.println("ONLY \"yes\" or \"no\"");
            }

            serverSocket.close();
        }

    }
}
