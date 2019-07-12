package com.connection;

import com.adventuregames.MyGame;
import com.fighterlvl.warrior.Fighter;
import com.fighterlvl.warrior.Player;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Server {

    private MyGame game;
    private boolean end = false;
    private Player player;
    private Fighter ennemy;


    public Server(MyGame oGame, Player player)
    {
        this.player = player;
        this.ennemy = null;
        this.game = oGame;
    }

    public void go() {

        try {
            ServerSocket serverSocket = new ServerSocket(4242);
            Socket socket = serverSocket.accept();

            // InputStreamReader is a bridge from byte streams to character streams: It reads bytes and decodes them into characters using a specified charset
            InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());

            // Reads text from a character-input stream, buffering characters
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            // PrintStream adds functionality to another output stream
            PrintStream printStream = new PrintStream(socket.getOutputStream());

            // ObjectOutputStream writes primitive data types and graphs of Java objects to an OutputStream. objects can be read (reconstituted) using an ObjectInputStream
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());

            // ObjectInputStream deserializes primitive data and objects previously written using an ObjectOutputStream
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());

            objectOutputStream.writeObject(player.getFighter());
            objectOutputStream.flush();

            while (!end) {

                String received = bufferedReader.readLine();
                System.out.println(received);

                if(received.trim().equalsIgnoreCase("turn of player two:") && !end ) {
                    printStream.println("object needed");
                    Object obj = objectInputStream.readObject();
                    player.setFighter((Fighter) obj);
                    obj = objectInputStream.readObject();
                    ennemy = (Fighter) obj;
                    printStream.println("player two is playing...");
                    player.getFighter().fight(ennemy);
                    printStream.println("turn of player one: ");

                }
                if(received.trim().equalsIgnoreCase("object needed")) {
                    objectOutputStream.writeObject(ennemy);
                    objectOutputStream.flush();
                    objectOutputStream.writeObject(player.getFighter());
                }

                if(!player.getFighter().isAlive()) {
                        System.out.println("you are dead");
                        end = true;
                }
                if(ennemy != null){

                    if(!ennemy.isAlive()){
                        end = true;
                        System.out.println("you win");
                    }
                }

            }
        } catch (Exception e) {
            System.out.println("Server Side Problem");
        }
    }
}
