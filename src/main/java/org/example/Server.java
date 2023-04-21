package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static final int PORT = 8926;

    public static void main(String[] args) {
        String city = null;

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     // ждем подключения
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())))
                {
                    if (city == null) {
                        city = getString(out, in);

                    } else {

                        city = getStr(city, out, in);
                    }

                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private static String getString(PrintWriter out, BufferedReader in) throws IOException {
        String city;
        out.println("???");
        city = in.readLine();
        out.println("OK");
        return city;
    }

    private static String getStr(String city, PrintWriter out, BufferedReader in) throws IOException {
        out.println(city);
        String newCity = in.readLine();
        if (city.charAt(city.length() - 1) == newCity.charAt(0)) {
            city = newCity;
            out.println("OK");
        } else {
            out.println("NOT OK");
        }
        return city;
    }
}