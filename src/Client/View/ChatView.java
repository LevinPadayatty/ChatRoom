package Client.View;

import Client.Model.ClientModel;
import javafx.stage.Stage;

public class ChatView {
    private Stage stage;
    private ClientModel model;

    public ChatView(Stage stage, ClientModel model) {
        this.model = model;
        this.stage = stage;


    }

    public void start() {
        stage.show();
    }

}
