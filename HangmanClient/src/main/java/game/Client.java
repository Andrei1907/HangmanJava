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
                        new InputStreamReader(clientSocket.getInputStream()));
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            new LoginFrame().setVisible(true);

            boolean ok = true;
            String username = br.readLine();
            out.println(username);

            String verification = in.readLine();
            while(verification.equals("ErrorUser")) {
                System.out.println("Login:");
                username = br.readLine();
                out.println(username);
                verification = in.readLine();
                System.out.println("Am trimis numele " + username);
            }

            //ecran intermediar
            if(verification.equals("ErrorServer")) {
                System.out.println("Eroare la server");
                clientSocket.close();
                return;
            } else {
                out.println("Start");
            }

            while (ok) {
                String word = in.readLine();
                System.out.println("Am primit " + word);

                while(true) {
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
                        request = br.readLine();
                        out.println(request);
                        if(request.equals("no")) {
                            clientSocket.close();
                            ok = false;
                        }
                        break;
                    } else
                    if(result.get(0).equals("Lost")) {
                        System.out.println("urit");
                        request = br.readLine();
                        out.println(request);
                        if(request.equals("no")) {
                            clientSocket.close();
                            ok = false;
                        }
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
