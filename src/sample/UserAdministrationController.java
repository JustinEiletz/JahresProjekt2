package sample;

import daos.UserDao;
import entity.User;
import enums.UserRole;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import manager.ApplicationManager;
import manager.ViewManager;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class UserAdministrationController implements Initializable {
    @FXML
    private TextField filterTF;
    @FXML
    private ListView<User> userListView;

    @FXML
    private TextField emailTF;
    @FXML
    private PasswordField passwordTF;
    @FXML
    private PasswordField confirmationTF;

    @FXML
    private RadioButton guestRB;
    @FXML
    private RadioButton userRB;
    @FXML
    private RadioButton adminRB;

    private ObservableList<User> userList;
    private User selectedUser = null;

    private ToggleGroup roleToggleGroup;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ApplicationManager app = ApplicationManager.getInstance();
        User user = app.getCurrentUser();

        Stage s = ViewManager.getInstanceVM().getStage();
        s.setTitle("User Administration");

        userList = FXCollections.observableArrayList();
        userListView.setItems(userList);
        updateUserList();

        filterTF.textProperty().addListener(o -> updateUserList());
        userListView.getSelectionModel().selectedItemProperty().addListener(o -> newUserSelected());

        roleToggleGroup = new ToggleGroup();
        guestRB.setToggleGroup(roleToggleGroup);
        guestRB.setUserData(UserRole.Guest);
        guestRB.setId("guest");

        userRB.setToggleGroup(roleToggleGroup);
        userRB.setUserData(UserRole.User);
        userRB.setSelected(true);
        userRB.setId("user");

        adminRB.setToggleGroup(roleToggleGroup);
        adminRB.setUserData(UserRole.Admin);
        adminRB.setId("admin");
    }

    private void updateUserList() {
        selectedUser = null;
        userList.clear();
        UserDao userDao = new UserDao();
        List<User> users  = userDao.findAll();
        for(User u : users) {
            if(!filterTF.getText().isBlank()
            && !u.getEmail().toLowerCase().contains(filterTF.getText().toLowerCase())) {
                continue;
            }
            userList.add(u);
        }
    }

    private void newUserSelected() {
        User newSelection = userListView.getSelectionModel().getSelectedItem();
        if(newSelection != null && selectedUser != newSelection) {
            selectedUser = newSelection;
            emailTF.setText(selectedUser.getEmail());
            passwordTF.clear();
            confirmationTF.clear();
            roleToggleGroup.selectToggle(null);
            switch(selectedUser.getRole()) {
                case User: roleToggleGroup.selectToggle(userRB); break;
                case Admin: roleToggleGroup.selectToggle(adminRB); break;
                case Guest: roleToggleGroup.selectToggle(guestRB); break;
            }
        }
    }

    private void errorAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR, "Error", ButtonType.OK);
        alert.setContentText(msg);
        alert.show();
    }

    @FXML
    private void backButtonClick()
    {
        ViewManager.getInstanceVM().activateScene(ViewManager.getInstanceVM().getDashboardScene());
    }

    @FXML
    private void clearButtonClick()
    {
        emailTF.clear();
        passwordTF.clear();
        confirmationTF.clear();
    }

    @FXML
    private void addNewButtonClick()
    {
        UserDao userDao = new UserDao();
        User user = userDao.findByName(emailTF.getText());
        if(user != null) {
            errorAlert("E-mail already exists.");
            return;
        }
        else if(emailTF.getText().isBlank()) {
            errorAlert("E-Mail is empty");
            return;
        }
        else if(passwordTF.getText().isBlank()) {
            errorAlert("Password is empty");
            return;
        }
        else if(!passwordTF.getText().equals(confirmationTF.getText())) {
            errorAlert("Passwords don't match.");
            return;
        }

        User newUser = new User();
        newUser.setEmail(emailTF.getText().trim());
        newUser.setHashedPassword(passwordTF.getText());
        newUser.setRole((UserRole) roleToggleGroup.getSelectedToggle().getUserData());
        userDao.create(newUser);
        updateUserList();
    }

    @FXML
    private void updateButtonClick()
    {
        if(selectedUser == null) return;
        UserDao userDao = new UserDao();
        User exists = userDao.findByName(emailTF.getText());
        if(exists != null && exists.getId() != selectedUser.getId()) {
            errorAlert("A user with that e-mail already exists.");
            return;
        }
        selectedUser.setEmail(emailTF.getText());
        if(!passwordTF.getText().isBlank()) {
            if(passwordTF.getText().equals(confirmationTF.getText())) {
                selectedUser.setHashedPassword(passwordTF.getText());
            }
            else {
                errorAlert("Passwords don't match.");
                return;
            }
        }
        selectedUser.setRole((UserRole)roleToggleGroup.getSelectedToggle().getUserData());
        userDao.update(selectedUser);
        updateUserList();
    }

    @FXML
    private void deleteButtonClick()
    {
        if(selectedUser == null) return;
        if(selectedUser.getId() == ApplicationManager.getInstance().getCurrentUser().getId()) {
            errorAlert("Can't delete logged in user.");
            return;
        }
        UserDao userDao = new UserDao();
        userDao.delete(selectedUser);
        updateUserList();
    }
}
