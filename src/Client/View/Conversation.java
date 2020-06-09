package Client.View;

import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;

public class Conversation extends Label {
    public enum Type {Private, Group}
    private String name;
    private Type type;
    private boolean active;

    private TextArea chatHistory;
    private TextArea chatEntry;
    private Label lblChatMembers;

    public Conversation (String name, Type type) {
        this.name = name;
        this.type = type;
        active = true;

        chatHistory = new TextArea();
        chatHistory.setEditable(false);
        chatEntry = new TextArea();
    }

    public void addTextToHistory(String text) {
        chatHistory.appendText(text + "\n");
    }


}
