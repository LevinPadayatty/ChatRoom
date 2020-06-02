package Common.Messages;

import java.util.ArrayList;

public class Message {

    public enum Type {CreateLogin, Login, ChangePassword, DeleteLogin, Logout, CreateChatroom,JoinChatroom,LeaveChatroom,DeleteChatroom,ListChatrooms,
        STARTGAME, YOURTURN, Ping, SendMessage, UserOnline, ListChatroomUsers}


    private Type type;
    private String message;
    private String userName;

    private volatile ArrayList<String> userNames;
    private volatile ArrayList<?> arrayList;


    public Message(Message.Type type, String message) {
        this.message = message;
        this.type = type;


    switch (type) {
        case Logout:
            this.userName = message;
            break;
        case Login:
            this.message = message;
            break;
        case UserOnline:
            this.userName = message;
            break;
        case CreateChatroom:
            this.userName = message;
            break;
        }
        this.message = message;
    }

    // Getters and Setters


    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public ArrayList<String> getUserNames() {
        return userNames;
    }

    public void setUserNames(ArrayList<String> userNames) {
        this.userNames = userNames;
    }

    public ArrayList<?> getArrayList() {
        return arrayList;
    }

    public void setArrayList(ArrayList<?> arrayList) {
        this.arrayList = arrayList;
    }
}



