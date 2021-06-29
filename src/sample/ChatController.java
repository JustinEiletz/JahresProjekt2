package sample;

import daos.ChatDao;
import entity.Chat;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import manager.ApplicationManager;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class ChatController extends BaseController<ChatController> implements Initializable {

    private final ChatDao chatDao = new ChatDao();

    @FXML
    private TextField messageTF;

    @FXML
    private TextArea chatTA;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) throws IndexOutOfBoundsException{
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                List<Chat> chatList = chatDao.findAll();
                if (chatList.size() > 1) {
                    chatTA.clear();
                    chatList.forEach(chat -> chatTA.setText(chatTA.getText() + " " + chat.getLoginName() + ": " + chat.getTimeStamp() + ": " + chat.getTextMessage() + " \n"));
                }
            }
        }, 100, 25000);
    }

    @Override
    protected Class<ChatController> getClassType() {
        return ChatController.class;
    }

    @FXML
    private void sendMessage() {
        if (messageTF.getText() != null && !messageTF.getText().isBlank()) {
            String message = messageTF.getText();
            String loginName = ApplicationManager.getInstance().getCurrentUser().getLoginName();

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
