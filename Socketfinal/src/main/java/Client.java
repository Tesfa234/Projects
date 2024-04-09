import java.io.*;
import java.net.*;
import javax.swing.*;

public class Client {
    private BufferedReader in;
    private PrintWriter out;
    private JFrame frame = new JFrame("Chat Room");
    private JTextField textField = new JTextField(40);
    private JTextArea messageArea = new JTextArea(1,1);
    

    public Client() {
        textField.setEditable(false);
        messageArea.setEditable(false);
        messageArea.setLineWrap(true);
        messageArea.setWrapStyleWord(true);
        frame.getContentPane().add(textField, "North");
        frame.getContentPane().add(messageArea, "Center");
        frame.pack();

        textField.addActionListener(e -> {
            out.println(textField.getText());
            textField.setText("");
        });
    }


    private String getName() {
        return JOptionPane.showInputDialog(frame, "Choose a screen name:", "Screen name selection", JOptionPane.PLAIN_MESSAGE);
    }

    private void run() throws IOException {
      
        Socket socket = new Socket("localhost", 1234);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);

        while (true) {
            String line = in.readLine();
            if (line.startsWith("SUBMITNAME"))

{
                out.println(getName());
            } else if (line.startsWith("NAMEACCEPTED")) {
                textField.setEditable(true);
            } else if (line.startsWith("MESSAGE")) {
                messageArea.append(line.substring(8));
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Client client = new Client();
        client.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        client.frame.setVisible(true);
        client.run();
    }
}