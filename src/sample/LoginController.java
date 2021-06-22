package sample;

import calculations.PasswordHashing;
import daos.UserDao;
import entity.User;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import manager.ApplicationManager;
import manager.ViewManager;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    private TextField loginName;

    @FXML
    private TextField password;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Initialize Login Controller");

        // for debugging only
        loginName.setText("test@mail.de");
        password.setText("pass123");
    }

    @FXML
    private void loginClick(Event event)
    {
        UserDao dao = new UserDao();
        User user = dao.findByName(loginName.getText());
        System.out.println(user != null ? user.getEmail() : "null");
        if(user != null) {
            String hashedPassword = PasswordHashing.Hash(password.getText());
            if(hashedPassword.equals(user.getHashed_password())) {
                System.out.println("Login successfull");
                ApplicationManager app = ApplicationManager.getInstance();
                app.setCurrentUser(user);
                // TODO: activate dashboard scene
                // ViewManager.getInstanceVM().activateScene();
            }
            else {
                System.out.println("wrong credentials");
            }
        }
        else {
            System.out.println("wrong credentials");
        }
    }

    @FXML
    private void cancelClick(Event event)
    {
        System.out.println("cancel click!");
        System.exit(0);
    }
}
