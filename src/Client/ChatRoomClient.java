package Client;

import Client.Controller.ChatController;
import Client.Model.ClientModel;
import Client.View.ChatView;
import Client.View.ConfigView;
import Client.View.LoginView;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

public class ChatRoomClient extends Application {

    private ClientModel model;
    private ChatView chatView;
    private ConfigView configView;
    private LoginView loginView;
    private ChatController controller;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.model = new ClientModel();

        this.configView = new ConfigView(primaryStage, model);

        Stage loginStage = new Stage();
        this.loginView = new LoginView(loginStage, model);

        Stage chatStage = new Stage();
        this.chatView = new ChatView(chatStage, model);



        this.controller = new ChatController(model, chatView, configView, loginView);

        configView.start();
    }




}
