package Client.Controller;

import Client.Model.ClientModel;
import Client.View.ChatView;
import Client.View.ConfigView;
import Client.View.LoginView;
import javafx.application.Platform;

public class ChatRoomController {
    private ChatView chatView;
    private ClientModel model;
    private ConfigView configView;
    private LoginView loginView;

    public ChatRoomController(ClientModel model, ChatView chatView, ConfigView configView, LoginView loginView) {
        this.model = model;
        this.chatView = chatView;
        this.configView = configView;
        this.loginView = loginView;

        configView.getBtnConnect().setOnAction(e -> {
            model.connectToServer();
            if (model.isConnected()) {
                configView.updateStatusConnect(true);
            }
        });

        configView.getBtnNext().setOnAction(e-> {
            if (model.isConnected()) {
                configView.stop();
                loginView.start();
            }
        });

        configView.getBtnEnterIP().setOnAction( e -> {
            if (model.validateIpAddress(configView.getInputIPAdress().getText())) {
                configView.updateStatusIpAdress(true);
                model.setIpAdress(configView.getInputIPAdress().getText());
            } else {
                configView.updateStatusIpAdress(false);
            }
        });

        configView.getBtnEnterPort().setOnAction( e -> {
           if (model.validatePortNumber(configView.getInputPort().getText())) {
               configView.updateStatusPort((true));
               model.setPort(Integer.parseInt(configView.getInputPort().getText()));
           } else {
               configView.updateStatusPort(false);
           }
        });

        loginView.getBtnCreateAcc().setOnAction( e -> {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    model.sendMessage(model.createSendableString("CreateLogin", loginView.getInputUserName().getText(), loginView.getInputPassword().getText()));
                }
            });
        });

        loginView.getBtnLogin().setOnAction( e -> {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    model.sendMessage(model.createSendableString("Login", loginView.getInputUserName().getText(), loginView.getInputPassword().getText()));
                }
            });
        });

        loginView.getBtnNext().setOnAction( e -> {
            Platform.runLater(new Runnable() {

                @Override
                public void run() {
                    loginView.stop();
                    chatView.start();
                }
            });
        });

        model.getMessageProperty().addListener((observable, oldValue, newValue) -> {
            Platform.runLater(new Runnable() {

                @Override
                public void run() {
                    switch (model.getMessageType()) {
                        case "CreateLogin":
                            if (model.isActionSuccessful()) {
                                loginView.updateStatusLogin("Account successfully created - Go on with Login");
                            } else {
                                loginView.updateStatusLogin("Oops, the account couldn't be created - try another username and password");
                            }
                            break;
                        case "Login":
                            if (model.isActionSuccessful()) {
                                loginView.updateStatusLogin("Login Successful - Click <\"Next\"> to go to Chatroom");
                            } else {
                                loginView.updateStatusLogin("Oops, this didn¨t work! Check the entered credentials or try to create an account first");
                            }
                            break;
                    }
                }
            });

        });
    }
}
