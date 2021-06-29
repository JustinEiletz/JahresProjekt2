package sample;

import daos.UserDao;
import entity.User;
import enums.UserRole;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import manager.ApplicationManager;

import javax.persistence.NoResultException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class UserAdministrationController extends BaseController<UserAdministrationController> implements Initializable {

    private final UserDao userDao = new UserDao();

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
        userList = FXCollections.observableArrayList();
        userListView.setItems(userList);
        updateUserList();

        filterTF.textProperty().addListener(o -> updateUserList());
        userListView.getSelectionModel().selectedItemProperty().addListener(o -> newUserSelected());

        roleToggleGroup = new ToggleGroup();
        guestRB.setToggleGroup(roleToggleGroup);
        guestRB.setUserData(UserRole.GUEST);
        guestRB.setId("guest");

        userRB.setToggleGroup(roleToggleGroup);
        userRB.setUserData(UserRole.USER);
        userRB.setSelected(true);
        userRB.setId("user");

        adminRB.setToggleGroup(roleToggleGroup);
        adminRB.setUserData(UserRole.ADMIN);
        adminRB.setId("admin");
    }

    private void updateUserList() {
        selectedUser = null;
        userList.clear();
        List<User> users  = userDao.findAll();
        for (User u : users) {
            if (filterTF.getText() != null && !filterTF.getText().isBlank() && !u.getEmail().toLowerCase().contains(filterTF.getText().toLowerCase()))
                continue;
            userList.add(u);
        }
    }

    private void newUserSelected() {
        User newSelection = userListView.getSelectionModel().getSelectedItem();
        if (newSelection != null && selectedUser != newSelection) {
            selectedUser = newSelection;
            emailTF.setText(selectedUser.getEmail());
            passwordTF.clear();
            confirmationTF.clear();
            roleToggleGroup.selectToggle(null);
            switch (selectedUser.getRole()) {
                case USER -> roleToggleGroup.selectToggle(userRB);
                case ADMIN -> roleToggleGroup.selectToggle(adminRB);
                case GUEST -> roleToggleGroup.selectToggle(guestRB);
            }
        }
    }

    private void errorAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR, "Error", ButtonType.OK);
        alert.setContentText(msg);
        alert.show();
    }

    @Override
    protected Class<UserAdministrationController> getClassType() { return UserAdministrationController.class; }

    @FXML
    private void clearButtonClick() {
        emailTF.clear();
        passwordTF.clear();
        confirmationTF.clear();
    }

    @FXML
    private void addNewButtonClick() {
        User user = userDao.findByLogin(emailTF.getText());
        if (user != null) {
            errorAlert("E-mail already exists.");
            return;
        } else if (emailTF.getText().isBlank() || !emailTF.getText().contains("@")) {
            errorAlert("E-Mail is not correct or empty.");
            return;
        } else if (passwordTF.getText().isBlank()) {
            errorAlert("Password is empty.");
            return;
        } else if (!passwordTF.getText().equals(confirmationTF.getText())) {
            errorAlert("Passwords don't match.");
            return;
        }
        user = new User();
        String loginName = emailTF.getText().trim();
        user.setLoginName(loginName.substring(0, loginName.indexOf("@")));
        user.setEmail(emailTF.getText().trim());
        user.setHashedPassword(passwordTF.getText());
        user.setRole((UserRole) roleToggleGroup.getSelectedToggle().getUserData());
        userDao.create(user);
        updateUserList();
    }

    @FXML
    private void updateButtonClick() {
        if (selectedUser == null) return;
        UserDao userDao = new UserDao();
        User exists = userDao.findByLogin(emailTF.getText());
        if (exists != null && !exists.getId().equals(selectedUser.getId())) {
            errorAlert("A user with that e-mail already exists.");
            return;
        }
        selectedUser.setEmail(emailTF.getText());
        if (!passwordTF.getText().isBlank()) {
            if (passwordTF.getText().equals(confirmationTF.getText())) {
                selectedUser.setHashedPassword(passwordTF.getText());
            } else {
                errorAlert("Passwords don't match.");
                return;
            }
        }
        selectedUser.setRole((UserRole)roleToggleGroup.getSelectedToggle().getUserData());
        userDao.update(selectedUser);
        updateUserList();
    }

    @FXML
    private void deleteButtonClick() {
        if (selectedUser == null) return;
        if (selectedUser.getId().equals(ApplicationManager.getInstance().getCurrentUser().getId())) {
            errorAlert("Can't delete logged in user.");
            return;
        }
        UserDao userDao = new UserDao();
        userDao.delete(selectedUser);
        updateUserList();
    }
}
