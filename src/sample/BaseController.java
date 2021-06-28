package sample;

import daos.ChatDao;
import entity.Chat;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import manager.ApplicationManager;
import manager.ViewManager;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class BaseController<T> {

    protected abstract Class<T> getClassType();

    @FXML
    private Parent chatUtil;

    @FXML
    private TextField messageTF;

    @FXML
    private TextArea chatTA;

    @FXML
    public void backButtonClick() {
        ViewManager.getInstanceVM().activateScene(ViewManager.getInstanceVM().getDashboardScene());
    }

    @FXML
    public void chatButtonClick() {
        ViewManager.getInstanceVM().activateScene(ViewManager.getInstanceVM().getDashboardScene());
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
