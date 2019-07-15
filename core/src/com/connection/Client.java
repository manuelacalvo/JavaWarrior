package com.connection;

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
    private Fighter ennemy;
    private JWGame game;

    public Client(JWGame aGame, Player player)
    {
        this.player = player;
        this.ennemy = null;
        this.game = aGame;
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
            player.setEnnemi(ennemy);
            printStream.println("player one is playing...");
            game.setScreen(new FightScreen((game), 1, true));
            //player.getFighter().fight_attacks(ennemy);
           // game.setScreen(new FightAttackDisplay(game, player.getFighter(), player.getEnnemi()));
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
                    game.setScreen(new FightScreen((game),1,true));
                    player.setEnnemi(ennemy);
                    player.getFighter().fight_attacks(ennemy);


                    printStream.println("turn of player two: ");



                }
                if(advice.trim().equalsIgnoreCase("object needed")) {
                    objectOutputStream.writeObject(ennemy);
                    objectOutputStream.flush();
                    objectOutputStream.writeObject(player.getFighter());
                    objectOutputStream.flush();
                }
                /*if(!player.getFighter().isAlive())
                {
                    end = true;
                    System.out.println("you are dead");
                }
                if(ennemy!= null) {
                    if (!ennemy.isAlive()) {
                        end = true;
                        System.out.println("you win");
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