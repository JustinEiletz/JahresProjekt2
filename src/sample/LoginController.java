package sample;

import calculations.PasswordHashing;
import daos.UserDao;
import entity.User;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import manager.ApplicationManager;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    private final UserDao userDao = new UserDao();

    @FXML
    private TextField loginTF;

    @FXML
    private TextField passwordTF;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Initialize Login Controller");

        // for debugging only
        loginTF.setText("test@mail.de");
        passwordTF.setText("pass123");
    }

    @FXML
    private void loginClick(final Event event) {
        User user = userDao.findByName(loginTF.getText());
        System.out.println(user != null ? user.getEmail() : "null");
        if (user != null) {
            String hashedPassword = "" + PasswordHashing.Hash(passwordTF.getText());
            if (hashedPassword.equals(user.getHashedPassword())) {
                System.out.println("Login successfully");
                ApplicationManager app = ApplicationManager.getInstance();
                app.setCurrentUser(user);
                // TODO: activate dashboard scene
                // ViewManager.getInstanceVM().activateScene();
            } else {
                System.out.println("wrong credentials");
            }
        } else {
            System.out.println("wrong credentials");
        }
    }

    @FXML
    private void cancelClick(Event event) {
        System.out.println("cancel click!");
        System.exit(0);
    }
}
