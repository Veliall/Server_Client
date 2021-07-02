package client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

    public static void main(String[] args) {

        String host = "netology.homework";
        int port = 8089;

        try (Socket clientSocket = new Socket(host, port);
             BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
            while (true) {
                String resp = in.readLine();
                System.out.println(resp);
                System.out.println("Exit - if you want to disconnect from the server.");
                String input = reader.readLine();
                if (input.equalsIgnoreCase("exit")) {
                    break;
                }
                out.println(input);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
