package Client.Model;

import java.io.IOException;
import java.net.Socket;

public class ClientModel {
    private String ipAdress = null;
    private int port = 0;
    Socket socket = null;
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
}
