package Client.View;

import Client.Model.ClientModel;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ConfigView {
    private Stage stage;
    private ClientModel model;

    private VBox root;
    private HBox boxIP;
    private HBox boxPort;
    private HBox boxConnect;

    private Label lblWelcome;
    private Label lblInstruction;
    private Label lblStatusIpAdress;
    private Label lblStatusPort;
    private Label lblStatusConnect;
    private TextField inputIPAdress;
    private TextField inputPort;

    private Button btnEnterIP;
    private Button btnEnterPort;
    private Button btnConnect;
    private Button btnNext;


    public ConfigView (Stage stage, ClientModel model) {
        this.stage = stage;
        this.model = model;

        root = new VBox(20);

        lblWelcome = new Label("Welcome to the Chat");
        lblInstruction = new Label("Enter and submit IP-Adress, then the port");

        inputIPAdress = new TextField("147.86.8.31");
        btnEnterIP = new Button("Submit");
        boxIP = new HBox(5);
        boxIP.getChildren().addAll(inputIPAdress, btnEnterIP);

        lblStatusIpAdress = new Label("IP-Adress not submitted yet");

        inputPort = new TextField("50001");
        btnEnterPort = new Button("Submit");
        boxPort = new HBox(5);
        boxPort.getChildren().addAll(inputPort, btnEnterPort);

        lblStatusPort = new Label("Not connected to server yet");

        btnConnect = new Button("Connect");
        btnNext = new Button("Next");
        boxConnect = new HBox(5);
        boxConnect.getChildren().addAll(btnConnect, btnNext);
        lblStatusConnect = new Label("Not connected yet");

        root.getChildren().addAll(lblWelcome, lblInstruction, boxIP, lblStatusIpAdress, boxPort, lblStatusPort, boxConnect, lblStatusConnect);
        root.setAlignment(Pos.CENTER);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Chatroom - Configuration");

    }

    public void start() {
        stage.show();
    }

    public void stop() {
        stage.hide();
    }

    public void updateStatusIpAdress(boolean success) {
        if (success) {
            lblStatusIpAdress.setText("IP-Adress is valid");
        } else {
            lblStatusIpAdress.setText("Oops, this didn't work, try again!");
        }
    }

    public void updateStatusPort(boolean success) {
        if (success) {
            lblStatusPort.setText("Yes!! Port is valid");
        } else {
            lblStatusPort.setText("Oops, this was the wrong port, try again");
        }
    }

    public void updateStatusConnect(boolean success) {
        if (success) {
            lblStatusConnect.setText("Yes!! You are connected to the server");
        } else {
            lblStatusConnect.setText("Oops, something went wrong");
        }
    }

    public Label getLblStatusIpAdress() {
        return lblStatusIpAdress;
    }

    public Label getLblStatusPort() {
        return lblStatusPort;
    }

    public TextField getInputIPAdress() {
        return inputIPAdress;
    }

    public TextField getInputPort() {
        return inputPort;
    }

    public Button getBtnEnterIP() {
        return btnEnterIP;
    }

    public Button getBtnEnterPort() {
        return btnEnterPort;
    }

    public Button getBtnConnect() {
        return btnConnect;
    }

    public Button getBtnNext() {
        return btnNext;
    }
}
