package com.connection;

import com.adventuregames.MyGame;
import com.fighterlvl.warrior.Fighter;
import com.fighterlvl.warrior.Player;

import java.io.*;
import java.net.Socket;




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

    public void go()
    {
        try
        {
            Socket incoming = new Socket("192.168.0.36",4242);
            BufferedReader bufIn = new BufferedReader(new InputStreamReader(System.in));
            PrintStream printStream = new PrintStream(incoming.getOutputStream());
            InputStreamReader stream = new InputStreamReader(incoming.getInputStream());
            BufferedReader reader = new BufferedReader(stream);
            System.out.println("Well connected");

            ObjectOutputStream objectOutputStream = new ObjectOutputStream(incoming.getOutputStream());
            objectOutputStream.flush();

            ObjectInputStream objectInputStream = new ObjectInputStream(incoming.getInputStream());

            while(true)
            {
                Object object= objectInputStream.readObject();
                System.out.println("betwween");
                ennemy = (Fighter) object ;
                printStream.println("player one is playing...");
                    /*player.getFighter().fight(ennemy);
                    printStream.println("turn of player two: ");
                    objectOutputStream.writeObject(player.getFighter());
                    while(!end) {
                        String advice = reader.readLine();
                        System.out.println(advice);

                        if (advice.equalsIgnoreCase("turn of player one: ")) {
                            ennemy = (Fighter)objectInputStream.readObject();

                            if(!ennemy.isAlive())
                            {
                                end =true;
                            }
 printStream.println("player one is playing...");
                            player.getFighter().fight(ennemy);
                            printStream.println("turn of player two: ");
                            objectOutputStream.writeObject(player.getFighter());
                            //String userMessage = bufIn.readLine().trim();
                            //printStream.println(userMessage);

                            //advice = reader.readLine().trim();
                           // System.out.println(advice);
                        }
                    }*/
            }

        }
        catch(Exception e)
        {
            System.out.println("Client Side Error");
        }

    }
}