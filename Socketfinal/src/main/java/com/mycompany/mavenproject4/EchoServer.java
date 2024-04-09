
package com.mycompany.mavenproject4;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;



public class EchoServer {
    public static void main (String [] args) throws IOException{
        System.out.println("Waiting for client .....");
        ServerSocket tes = new ServerSocket(9806);
        Socket soc = tes.accept();
        System.out.println("Connection established!");
        BufferedReader use = new BufferedReader (new InputStreamReader(soc.getInputStream()));
        String str = use.readLine();
        PrintWriter out = new PrintWriter(soc.getOutputStream(),true);
        out.println("Server says:"+ str);

        
    }
    
}
