package sample;

import entity.Rental;
import entity.Tenant;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class TenantEditController implements Initializable {

    @FXML
    private TextField idTF;
    @FXML
    private TextField nameTF;
    @FXML
    private TextField surnameTF;
    @FXML
    private TextField mobilenumberTF;
    @FXML
    private TextField addressTF;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Tenant edit = TenantController.GetAddEditTenant();
        if(edit != null) {
            idTF.setText(edit.getId().toString());
            nameTF.setText(edit.getForeName());
            surnameTF.setText(edit.getSurName());
            mobilenumberTF.setText(edit.getPhoneNumber());
            addressTF.setText(edit.getAddress().toString());
        }
    }
}
