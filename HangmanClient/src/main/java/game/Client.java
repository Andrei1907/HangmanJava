package game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;

public class Client {
    public static void main (String[] args) throws IOException {
        String serverAddress = "127.0.0.1";
        int PORT = 8100;
        try (
                Socket clientSocket = new Socket(serverAddress, PORT);
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader (
                        new InputStreamReader(clientSocket.getInputStream())) ) {
            boolean ok = true;
            System.out.println("Am inceput");
            out.println("eu");
            System.out.println("Am trimis numele eu");

            while (ok) {
                System.out.println("Am intrat in while");
                String word = in.readLine();
                System.out.println("Am primit " + word);

                while(true) {
                    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                    String request = br.readLine();
                    out.println(request);
                    System.out.println("Am primit " + request + ", trimis la server");
                    if(request.equals("exit")) {
                        clientSocket.close();
                        break;
                    }
                    // Wait the response from the server ("Hello World!")
                    String response = in.readLine();
                    List<String> result = Arrays.stream(response.split("-")).toList();
                    System.out.println("Am primit tripletul " + result);

                    if(result.get(0).equals("Won")) {
                        System.out.println("bravo");
                        out.println("no");
                        clientSocket.close();
                        ok = false;
                        break;
                    } else
                    if(result.get(0).equals("Lost")) {
                        System.out.println("urit");
                        out.println("no");
                        clientSocket.close();
                        ok = false;
                        break;
                    }
                    else System.out.println(result.get(1) + " " + result.get(2));
                }
            }
        } catch (UnknownHostException e) {
            System.err.println("No server listening... " + e);
        }
    }
}
