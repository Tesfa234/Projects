
package com.mycompany.mavenproject4;

import java.net.Socket;
import java.io.IOException;
import java.io.*;
public class EchoClient {
    public static void main (String [] args)throws IOException{

        System.out.println("Client started");
        Socket soc = new Socket("localhost",9806);
        BufferedReader userInput = new BufferedReader (new InputStreamReader(System.in));
        System.out.println("Enter the message");
        String str = userInput.readLine();
        PrintWriter out = new PrintWriter(soc.getOutputStream(),true);
        out.println(str);
        BufferedReader in = new BufferedReader (new InputStreamReader(soc.getInputStream()));
        System.out.println(in.readLine());

        
    }
    
}
