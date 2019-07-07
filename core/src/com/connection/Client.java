package com.connection;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;




    public class Client
    {
        public static void main(String args[])
        {
            Client dac = new Client();
            dac.go();
        }

        public void go()
        {
            try
            {
                Socket incoming = new Socket("192.168.0.36",4242);

                BufferedReader bufIn = new BufferedReader(new InputStreamReader(System.in));
                PrintStream printStream = new PrintStream(incoming.getOutputStream());
                printStream.println("hi");

                InputStreamReader stream = new InputStreamReader(incoming.getInputStream());
                BufferedReader reader = new BufferedReader(stream);
                String advice = reader.readLine();

                System.out.println("Today's advice is "+advice);
                while(true)
                {
                    advice = reader.readLine();
                    System.out.println(advice);

                    if(advice.equalsIgnoreCase("your turn: "))
                    {
                        String userMessage = bufIn.readLine().trim();
                        printStream.println(userMessage);

                        advice = reader.readLine().trim();
                        System.out.println(advice);
                    }
                }

            }
            catch(Exception e)
            {
                System.out.println("Client Side Error");
            }

        }
    }


