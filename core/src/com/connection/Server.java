package com.connection;

import com.adventuregames.MyGame;
import com.fighterlvl.warrior.Fighter;
import com.fighterlvl.warrior.Player;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Server {
    private boolean end = false;
    private Player player;
    private Fighter ennemy;


    public Server(MyGame aGame, Player player)
    {
        this.player = player;
        this.ennemy = null;
    }

    public void go() {

        try {
            ServerSocket serversock = new ServerSocket(4242);
            Socket outgoing = serversock.accept();
            InputStreamReader inputStreamReader = new InputStreamReader(outgoing.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            BufferedReader bufIn = new BufferedReader(new InputStreamReader(System.in));
            PrintStream printStream = new PrintStream(outgoing.getOutputStream());
            System.out.println("test");

            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outgoing.getOutputStream());



            while (true) {
                objectOutputStream.writeObject(player.getFighter());
                System.out.println("obj sented");
               // objectOutputStream.flush();
                /*
                while(!end) {
                    System.out.println("test2");
                    String received = bufferedReader.readLine();
                    System.out.println(received);
                    if (received.trim().equalsIgnoreCase("turn player two : ")) {
                        ObjectInputStream objectInputStream = new ObjectInputStream(outgoing.getInputStream());
                        Object obj = objectInputStream.readObject();
                        printStream.println("player two is playing...");
                        //ennemy = (Fighter) obj;
                        if(!ennemy.isAlive())
                        {
                            end = true;
                        }
                        player.getFighter().fight(ennemy);
                        // String serverMessage = bufIn.readLine().trim();
                        //printStream.println(serverMessage);
                        printStream.println("turn player one ");
                       // objectOutputStream.writeObject(player.getFighter());
                    }
                }*/
                objectOutputStream.close();
            }
        } catch (Exception e) {
            System.out.println("Server Side Problem");
        }
    }



}
