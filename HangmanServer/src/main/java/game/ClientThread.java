package game;

import database.Database;
import database.PlayerDAO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

class ClientThread extends Thread {
    private Socket socket = null;
    private boolean logged = false;
    private Player player;
    private Dictionary dictionary;
    private int currentScore;

    public ClientThread (Socket socket) {
        this.socket = socket;
        this.dictionary = new Dictionary();
        this.currentScore = 0;
    }

    public void run () {
        try {
            //date din formular
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            var players = new PlayerDAO();

            String usernameCommand = in.readLine();
            int id = verifyCommand(usernameCommand);
            while(id == -1) {
                out.println("ErrorUser");
                usernameCommand = in.readLine();
                id = verifyCommand(usernameCommand);
            }

            //ecran intermediar
            player = new Player(id, usernameCommand.split(" ")[1], players.getHighscore(id));
            out.println("Ok");
            if(!in.readLine().equals("Start")) {
                System.out.println("Client left");
                socket.close();
                return;
            }

            while(true) {
                Game game = new Game(player, dictionary.generateWord());

                int errorCount = game.getErrorCount();
                out.println(game.getGuessedWord());
                System.out.println("Am trimis cuvantul " + game.getGuessedWord());
                StringBuilder raspuns = new StringBuilder();

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
                        if(phase.toString().equals("Won")) {
                            currentScore += 1;
                            dictionary.removeWord(game.getGuessedWord());
                            if(player.getHighscore() < currentScore) {
                                player.setHighscore(currentScore);
                                players.updateHighscore(id, currentScore);
                                Database.getConnection().commit();
                            }
                        }
                        phase.append("-").append(currentScore).append("-").append(player.getHighscore());
                        out.println(phase);

                        received = in.readLine();
                        System.out.println(received);
                        if (received.equals("no")) {
                            raspuns.append("Client left");
                            System.out.println(raspuns);
                            break;
                        } else {
                            raspuns.append("New game");
                            break;
                        }
                    } else {
                        phase.append("-").append(game.getErrorCount()).append("-").append(game.getGuessedWord());
                        out.println(phase);
                    }
                }
                if(raspuns.toString().equals("Client left"))
                    break;
            }
        } catch (IOException e) {
            System.err.println("Communication error... " + e);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close(); // or use try-with-resources
            } catch (IOException e) { System.err.println (e); }
        }
    }

    public int verifyCommand(String command) throws SQLException {
        List<String> result = Arrays.stream(command.split(" ")).toList();
        var players = new PlayerDAO();
        int aux = players.findByName(result.get(1));
        if(result.get(0).equals("Login")) {
            return aux;
        } else if(result.get(0).equals("Register")) {
            if(aux > 0) {
                return -1;
            }
            players.create(result.get(1));
            Database.getConnection().commit();
            return players.findByName(result.get(1));
        }
        return -1;
    }
}
