package Client.Model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ClientModel {
    private String ipAdress = null;
    private int port = 0;
    private Socket socket = null;


    // IO-Elements
    private OutputStreamWriter socketOut;
    private BufferedReader socketIn;

    // Unique Token that needs to be sent with every message (Server identifies Client through Token)
    private String uniqueToken ="";
    // Separated Strings from message
    private String[] messageParts = new String[4];

    private String userName;
    private String password;

    private boolean connected = false;

    public void connectToServer() {
        if (validateIpAddress(ipAdress) && validatePortNumber(Integer.toString(port))) {
            try {
                socket = new Socket(ipAdress, port);
                System.out.println("Connected");
                connected = true;
            } catch (IOException e) {
                System.out.println("Connection to server has failed");
            }
        }

        try {
            socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            socketOut = new OutputStreamWriter(socket.getOutputStream());
            System.out.println("IO-Streams successfully created");
            // Create thread to read incoming messages


        } catch (IOException e) {
            e.printStackTrace();
        }
        ListenerThread lt = new ListenerThread();
        lt.start();

        /* finally {
            if (socket != null) try {
                socket.close();
            } catch (IOException e) {
                // we don't care
            }

        }
        */
    }

    private String[] separateMessage(String msg) {
        String[] strings = msg.split("|");
        if (messageParts[3] != null) {
            checkForUniqueToken();
        }
        return strings;
    }

    private void checkForUniqueToken() {
        if (messageParts[0].equals("Result") && messageParts[1].equals("CreateLogin") && messageParts[2].equals("false")) {
            uniqueToken = messageParts[3];
        }
    }



    public void sendMessage(String message) {
        try {
            socketOut.write(message + "\n");
            socketOut.flush();
            System.out.println(message);
        } catch (IOException e) {
            System.out.println("Exception writing to server: "+message);
        }
    }

    public String createSendableString(String s) {
        return s;
    }

    public String createSendableString(String type, String userName, String password) {
        String sendable = type +"|"+userName+"|"+password;
        return sendable;
    }

    public String createSendableString(String type, String chatRoom, boolean isPublic) {
        String sendable = type +"|"+uniqueToken+"|"+chatRoom+"|"+isPublic;
        return sendable;
    }

    public boolean validateIpAddress(String ipAddress) {
        boolean valid = false;
        String[] parts = ipAddress.split("\\.", -1);

        // check for a numeric address first: 4 parts, each an integer 0 to 255
        if (parts.length == 4) {
            valid = true;
            for (String part : parts) {
                try {
                    int value = Integer.parseInt(part);
                    if (value < 0 || value > 255) valid = false;
                } catch (NumberFormatException e) {
                    // wasn't an integer
                    valid = false;
                }
            }
        }

        // If not valid, try for a symbolic address: at least two parts, each
        // part at least two characters. We don't bother checking what kinds of
        // characters they are.
        if (!valid) {
            if (parts.length >= 2) {
                valid = true;
                for (String part : parts) {
                    if (part.length() < 2) valid = false;
                }
            }
        }

        return valid;
    }

    public boolean validatePortNumber(String portText) {
        boolean formatOK = false;
        try {
            int portNumber = Integer.parseInt(portText);
            if (portNumber >= 1024 & portNumber <= 65535) {
                formatOK = true;
            }
        } catch (NumberFormatException e) {
        }
        return formatOK;
    }

    public String getIpAdress() {
        return ipAdress;
    }

    public void setIpAdress(String ipAdress) {
        this.ipAdress = ipAdress;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public boolean isConnected() {
        return connected;
    }

    private class ListenerThread extends Thread {
        volatile String message = "";

        public ListenerThread() {
            super("ListenerThread");
        }

        @Override
        public void run() {

            while (true) {
                Socket s = null;
                try {
                    message = socketIn.readLine();
                    System.out.println(message);
                    messageParts = separateMessage(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
