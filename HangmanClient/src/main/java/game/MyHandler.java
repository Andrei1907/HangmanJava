package game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class MyHandler {
    private final static String serverAddress = "127.0.0.1";
    private final static int PORT = 8100;
    static Socket clientSocket;
    static PrintWriter out;
    static BufferedReader in;

    static {
        try {
            clientSocket = new Socket(serverAddress, PORT);
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader (new InputStreamReader(clientSocket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void run1(String message) {
        out.println(message);
    }

    public static String run2() {
        try {
            return in.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
