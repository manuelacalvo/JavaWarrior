package com.connection;

import com.adventuregames.MyGame;
import com.fighterlvl.warrior.Fighter;
import com.fighterlvl.warrior.Player;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;


public class Client
{
    private boolean end = false;
    private Player player;
    private Fighter ennemy;


    public Client(MyGame aGame, Player player)
    {
        this.player = player;
        this.ennemy = null;
    }

    public void go(String ip)
    {
        try
        {
            Socket incoming = new Socket(ip,4242);
            BufferedReader bufIn = new BufferedReader(new InputStreamReader(System.in));
            PrintStream printStream = new PrintStream(incoming.getOutputStream());
            InputStreamReader stream = new InputStreamReader(incoming.getInputStream());
            BufferedReader reader = new BufferedReader(stream);
            System.out.println("Well connected");

            ObjectOutputStream objectOutputStream = new ObjectOutputStream(incoming.getOutputStream());
            objectOutputStream.flush();

            ObjectInputStream objectInputStream = new ObjectInputStream(incoming.getInputStream());

            Object object= objectInputStream.readObject();
            ennemy = (Fighter) object ;
            printStream.println("player one is playing...");
            player.getFighter().fight(ennemy);
            printStream.println("turn of player two:");
            String advice = reader.readLine();
            System.out.println(advice);
            if(advice.trim().equalsIgnoreCase("object needed")) {
                objectOutputStream.writeObject(ennemy);
                objectOutputStream.flush();
                objectOutputStream.writeObject(player.getFighter());
                objectOutputStream.flush();
            }

            while(true)
            {
                advice = reader.readLine();
                System.out.println(advice);

                if (advice.equalsIgnoreCase("turn of player one: ") && !end) {
                    printStream.println("object needed");
                    player.setFighter((Fighter)objectInputStream.readObject());
                    ennemy = (Fighter)objectInputStream.readObject();
                    printStream.println("player one is playing...");
                    player.getFighter().fight(ennemy);
                    printStream.println("turn of player two: ");



                }
                if(advice.trim().equalsIgnoreCase("object needed")) {
                    objectOutputStream.writeObject(ennemy);
                    objectOutputStream.flush();
                    objectOutputStream.writeObject(player.getFighter());
                    objectOutputStream.flush();
                }
                if(!player.getFighter().isAlive())
                {
                    end = true;
                    System.out.println("you are dead");
                }
                if(ennemy!= null) {
                    if (!ennemy.isAlive()) {
                        end = true;
                        System.out.println("you win");
                    }
                }

            }

        }
        catch(Exception e)
        {
            System.out.println("Client Side Error");
        }


    }


}