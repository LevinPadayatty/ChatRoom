package Client.View;

import Client.Model.ClientModel;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginView {
    private Stage stage;
    private ClientModel model;

    private VBox root;
    private HBox boxButtons;

    private Label lblInstruction;
    private Label lblStatus;
    private TextField inputUserName;
    private TextField inputPassword;

    private Button btnCreateAcc;
    private Button btnLogin;

    public LoginView (Stage stage, ClientModel model) {
        this.stage = stage;
        this.model = model;

        lblInstruction = new Label("Enter Username & password, then create an account and log in");
        inputUserName = new TextField("Enter username here...");
        inputPassword = new TextField("Enter password here...");

        btnCreateAcc = new Button("Create Account");
        btnLogin = new Button("Log in");
        boxButtons = new HBox(5);
        boxButtons.getChildren().addAll(btnCreateAcc, btnLogin);

        lblStatus = new Label("Create an account first");

        root = new VBox(20);
        root.getChildren().addAll(lblInstruction, inputUserName, inputPassword, boxButtons, lblStatus);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Login");
    }

    public void start() {
        stage.show();
    }

    public void stop() {
        stage.hide();
    }

    public void updateStatusLogin(String text) {
        lblStatus.setText(text);
    }

    public TextField getInputUserName() {
        return inputUserName;
    }

    public TextField getInputPassword() {
        return inputPassword;
    }

    public Button getBtnCreateAcc() {
        return btnCreateAcc;
    }

    public Button getBtnLogin() {
        return btnLogin;
    }
}
