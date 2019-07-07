package com.connection;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Server {


    public static void main(String args[]) {
        Server server = new Server();
        server.go();
    }

    public void go() {

        try {
            ServerSocket serversock = new ServerSocket(4242);
            Socket outgoing = serversock.accept();
            InputStreamReader inputStreamReader = new InputStreamReader(outgoing.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            BufferedReader bufIn = new BufferedReader(new InputStreamReader(System.in));
            PrintStream printStream = new PrintStream(outgoing.getOutputStream());

            while (true) {
                String received = bufferedReader.readLine();
                System.out.println(received);

                if(received.trim().equalsIgnoreCase("hi"))
                {
                    String serverMessage = bufIn.readLine().trim();
                    printStream.println(serverMessage);
                    printStream.println("your turn: ");
                }

            }
        } catch (Exception e) {
            System.out.println("Server Side Problem");
        }
    }



}
