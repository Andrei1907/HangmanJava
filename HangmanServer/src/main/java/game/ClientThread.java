package game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

class ClientThread extends Thread {
    private Socket socket = null;
    private boolean logged = false;
    private Player player;
    private Dictionary dictionary;
    private int score;

    public ClientThread (Socket socket) {
        this.socket = socket;
        this.dictionary = new Dictionary();
        this.score = 0;
    }

    public void run () {
        try {
            System.out.println("Am inceput");
            //date din formular
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            player = new Player(in.readLine());
            System.out.println(player.getName());

            while(true) {
                System.out.println("Am intrat in while");
                Game game = new Game(player, dictionary.generateWord());
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                int errorCount = game.getErrorCount();
                out.println(game.getGuessedWord());
                System.out.println("Am trimis cuvantul " + game.getGuessedWord());
                StringBuilder raspuns = new StringBuilder();

                //test
//                String received = in.readLine();
//                System.out.println("Am primit litera " + received);
//                raspuns.append(game.verifyLetter(received)).append("-").append(score).append("-").append(10);
//                System.out.println("Am trimis " + raspuns);
//                out.println(raspuns);

                while(true) {
                    String received = in.readLine();
                    System.out.println("Am primit litera " + received + " de la client");
                    if (received.equals("exit")) {
                        raspuns.append("Client left");
                        System.out.println(raspuns);
                        break;
                    }

                    StringBuilder phase = new StringBuilder(game.verifyLetter(received));
                    System.out.println(phase);
                    if(phase.toString().equals("Won") || phase.toString().equals("Lost")) {
                        System.out.println("Este won sau lost");
                        if(phase.toString().equals("Won")) {
                            score += 1;
                        }
                        phase.append("-").append(score).append("-").append(0);
                        out.println(phase);

                        received = in.readLine();
                        System.out.println(received);
                        if (received.equals("no")) {
                            raspuns.append("Client left");
                            System.out.println(raspuns);
                            break;
                        } else {
                            raspuns.append("New game");
                        }
                    } else {
                        System.out.println("Este right sau wrong");
                        phase.append("-").append(game.getErrorCount()).append("-").append(game.getGuessedWord());
                        out.println(phase);
                    }
                }
                if(raspuns.toString().equals("Client left"))
                    break;
            }
        } catch (IOException e) {
            System.err.println("Communication error... " + e);
        } finally {
            try {
                socket.close(); // or use try-with-resources
            } catch (IOException e) { System.err.println (e); }
        }
    }
}
