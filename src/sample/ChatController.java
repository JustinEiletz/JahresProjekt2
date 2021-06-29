package sample;

import daos.ChatDao;
import entity.Chat;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import manager.ApplicationManager;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class ChatController extends BaseController<ChatController> implements Initializable {

    @FXML
    private TextField messageTF;

    @FXML
    private TextArea chatTA;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) { }

    @Override
    protected Class<ChatController> getClassType() {
        return ChatController.class;
    }

    @FXML
    private void sendMessage() {
        if (messageTF.getText() != null && !messageTF.getText().equals("")) {
            String message = messageTF.getText();
            String loginName = ApplicationManager.getInstance().getCurrentUser().getLoginName();

            ChatDao chatDao = new ChatDao();
            Chat chatMessage = new Chat(loginName, getCurrentTime(), message);
            chatDao.create(chatMessage);

            listMessage(chatMessage);
        }
    }

    private void listMessage(final Chat message) {
        chatTA.setText(chatTA.getText() + " " + message.getLoginName() + ": " + message.getTimeStamp() + ": " + message.getTextMessage() + " \n");
    }

    private String getCurrentTime() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        return dtf.format(LocalDateTime.now());
    }
}
