
import java.io.*;
import java.net.*;
import java.util.*;

public class Server {

    public static void main(String[] args) throws Exception {
        System.out.println("The chat server is running...");
        ServerSocket listener = new ServerSocket(1234);
        try {
            while (true) {
                new Handler(listener.accept()).start();
               
            }
        } finally {
            listener.close();
        }
    }

static boolean usernameExists(String inputLine) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    static void addUser(String username, PrintWriter out) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    static void removeUser(String username) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    static void broadcast(String inputLine, String username) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
}

  