package Client.View;

import Client.Model.ClientModel;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

import java.util.ArrayList;

public class ChatView {
    private Stage stage;
    private ClientModel model;

    private BorderPane root;
    private HBox boxChatOverview;


    private ScrollPane paneConversationOverview;
    private Pane childForScrollPane;

    private HBox boxChatControls;
    private Label lblCreateNewPrivateConvo;
    private Label lblCreateNewChatroom;
    private Label lblJoinChatRoom;


    private ArrayList<Conversation> listConvos;


    public ChatView(Stage stage, ClientModel model) {
        this.model = model;
        this.stage = stage;


        // chat controls to create new private chat / group or join group
        lblCreateNewPrivateConvo = new Label("Create new private conversation");
        lblCreateNewChatroom = new Label("Create new group");
        lblJoinChatRoom = new Label("Join chatroom");
        boxChatControls = new HBox(5);
        boxChatControls.getChildren().addAll(lblCreateNewPrivateConvo, lblCreateNewChatroom, lblJoinChatRoom);
        boxChatControls.setMinHeight(100);
        HBox.setHgrow(lblJoinChatRoom, Priority.ALWAYS);

        listConvos = new ArrayList<>();
        childForScrollPane = new Pane();
        childForScrollPane.getChildren().addAll(listConvos);
        paneConversationOverview = new ScrollPane();
        paneConversationOverview.setContent(childForScrollPane);
        paneConversationOverview.setMinHeight(200);
        paneConversationOverview.setPrefWidth(boxChatControls.getPrefWidth());


    }

    public void start() {
        stage.show();
    }
}
