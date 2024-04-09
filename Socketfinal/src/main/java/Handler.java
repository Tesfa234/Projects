 import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.*;
public class Handler extends Thread {
        private static Set<PrintWriter> writers = new HashSet<>();
        private String name;
        private Socket socket;
        private BufferedReader in;
        private PrintWriter out;

        public Handler(Socket socket) {
            this.socket = socket;
        }

    Handler(Socket clientSocket, ArrayList<Socket> clients) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

        public void run() {
            try {
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);

                while (true) {
                    out.println("SUBMITNAME");
                    name = in.readLine();
                    if (name == null) {
                        return;
                    }
                    synchronized (writers) {
                        if (!name.isEmpty() && !writers.contains(out)) {
                            writers.add(out);
                            break;
                        }
                    }
                }

                out.println("NAMEACCEPTED");
                for (PrintWriter writer : writers) {
                    writer.println("MESSAGE " + name + " has joined the chat           ");
                    
                }

                while (true) {
                    String input = in.readLine();
                    if (input == null) {
                        return;
                    }
                    for (PrintWriter writer : writers) {
                        writer.println("MESSAGE " + name + " : " + input+"           ");
                    }
                }
            } catch (IOException e) {
                System.out.println(e);
            } finally {
                if (name != null) {
                    writers.remove(out);
                    for (PrintWriter writer : writers) {
                        writer.println("MESSAGE " + name + " has left the chat           ");
                     
                    }
                }
                try {
                    socket.close();
                } catch (IOException e) {
                }
            }
        }
    }



