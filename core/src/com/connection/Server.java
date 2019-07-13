package com.connection;

import com.adventuregames.MyGame;
import com.adventuregames.fight.FightAttackDisplay;
import com.fighterlvl.warrior.Fighter;
import com.fighterlvl.warrior.Player;

import java.io.*;
import java.net.*;

public class Server {
    private boolean end = false;
    private Player player;
    private Fighter ennemy;
    private MyGame game;


    public Server(MyGame aGame, Player player)
    {
        this.player = player;
        this.ennemy = null;
        this.game = aGame;
    }

        public void go() {

        try {
            ServerSocket serversock = new ServerSocket(4242);
            Socket outgoing = serversock.accept();
            InputStreamReader inputStreamReader = new InputStreamReader(outgoing.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            BufferedReader bufIn = new BufferedReader(new InputStreamReader(System.in));
            PrintStream printStream = new PrintStream(outgoing.getOutputStream());

            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outgoing.getOutputStream());
            ObjectInputStream objectInputStream = new ObjectInputStream(outgoing.getInputStream());

            objectOutputStream.writeObject(player.getFighter());
            objectOutputStream.flush();

            while (true) {


                    String received = bufferedReader.readLine();
                    System.out.println(received);


                    if(received.trim().equalsIgnoreCase("turn of player two:") && end == false) {
                        printStream.println("object needed");
                        Object obj = objectInputStream.readObject();
                        player.setFighter((Fighter) obj);
                        obj = objectInputStream.readObject();
                        ennemy = (Fighter) obj;

                        printStream.println("player two is playing...");
                        player.setEnnemi(ennemy);
                        game.setScreen(new FightAttackDisplay(game, player.getFighter(), player.getEnnemi()));

                        // game.setScreen(new FightAttackDisplay((game)));
                       // player.getFighter().fight(ennemy);
                        printStream.println("turn of player one: ");



                }
                    if(received.trim().equalsIgnoreCase("object needed")) {
                    objectOutputStream.writeObject(ennemy);
                    objectOutputStream.flush();
                    objectOutputStream.writeObject(player.getFighter());
                }

                   if(!player.getFighter().isAlive())
                    {
                        System.out.println("you are dead");
                        end = true;
                    }
                   if(ennemy != null)
                   {
                    if(!ennemy.isAlive())
                    {
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
