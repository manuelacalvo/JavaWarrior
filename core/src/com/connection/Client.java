package com.connection;

import com.adventuregames.fight.FIGHT_PART;
import com.adventuregames.fight.FightScreen;
import com.fighterlvl.warrior.Fighter;
import com.fighterlvl.warrior.Player;
import com.javawarrior.JWGame;

import java.io.*;
import java.net.Socket;

public class Client
{
    private boolean end = false;
    private Player player;


    private JWGame game;

    private Fighter enemy;


    public Client(JWGame oGame, Player player)
    {
        this.game=oGame;
        this.player = player;
        this.enemy = null;
    }

    public void go(String ip)
    {
        try
        {
            Socket socket = new Socket("192.168.0.36",4242);

            // PrintStream adds functionality to another output stream
            PrintStream printStream = new PrintStream(socket.getOutputStream());

            // InputStreamReader is a bridge from byte streams to character streams: It reads bytes and decodes them into characters using a specified charset
            InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());

            // Reads text from a character-input stream, buffering characters
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            System.out.println("Well connected");

            // ObjectOutputStream writes primitive data types and graphs of Java objects to an OutputStream. objects can be read (reconstituted) using an ObjectInputStream
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.flush();

            // ObjectInputStream deserializes primitive data and objects previously written using an ObjectOutputStream
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());

            Object object = objectInputStream.readObject();
            enemy = (Fighter) object ;
            printStream.println("player one is playing...");
            player.getFighter().fight(enemy);
            game.setScreen(new FightScreen((game), FIGHT_PART.FIRST_PART, true));

            printStream.println("turn of player two:");

            String advice = bufferedReader.readLine();
            System.out.println(advice);
            if(advice.trim().equalsIgnoreCase("object needed")) {
                objectOutputStream.writeObject(enemy);
                objectOutputStream.flush();
                objectOutputStream.writeObject(player.getFighter());
                objectOutputStream.flush();
            }

            while(!end)
            {
                advice = bufferedReader.readLine();
                System.out.println(advice);

                if (advice.equalsIgnoreCase("turn of player one: ") && !end) {
                    printStream.println("object needed");
                    player.setFighter((Fighter)objectInputStream.readObject());
                    enemy = (Fighter)objectInputStream.readObject();

                    printStream.println("player one is playing...");
                    game.setScreen(new FightScreen((game),FIGHT_PART.FIRST_PART,true));
                    player.setEnnemi(enemy);
                    player.getFighter().fight_attacks(enemy);

                    printStream.println("turn of player two: ");

                }
                if(advice.trim().equalsIgnoreCase("object needed")) {
                    objectOutputStream.writeObject(enemy);
                    objectOutputStream.flush();
                    objectOutputStream.writeObject(player.getFighter());
                    objectOutputStream.flush();
                }
            }

        }
        catch(Exception e)
        {
            System.out.println("Client Side Error");
        }


    }


}