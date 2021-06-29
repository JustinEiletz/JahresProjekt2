package sample;

import entity.Address;
import entity.Rental;
import entity.Tenant;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

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
    private TextField streetTF;

    @FXML
    private TextField zipCodeTF;

    @FXML
    private TextField cityTF;

    @FXML
    private TextField postCodeTF;

    @FXML
    private GridPane window;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Tenant edit = TenantController.GetAddEditTenant();
        if(edit != null) {
            idTF.setText(edit.getId().toString());
            nameTF.setText(edit.getForeName());
            surnameTF.setText(edit.getSurName());
            mobilenumberTF.setText(edit.getPhoneNumber());

            streetTF.setText(edit.getAddress().getStreet());
            zipCodeTF.setText(edit.getAddress().getZipCode());
            cityTF.setText(edit.getAddress().getZipCode());
            postCodeTF.setText(edit.getAddress().getPostCode());
        }
    }

    private void closeWindow()
    {
        Stage stage = (Stage)window.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void cancelButtonClick()
    {
        closeWindow();
    }

    @FXML
    private void applyButtonClick()
    {
        Tenant edit = TenantController.GetAddEditTenant();
        if(edit == null) {
            edit = new Tenant();
            TenantController.SetAddEditTenant(edit);
        }
        if(edit.getAddress() == null) edit.setAddress(new Address());
        Address address = edit.getAddress();
        address.setStreet(streetTF.getText());
        address.setZipCode(zipCodeTF.getText());
        address.setPlace(cityTF.getText());
        address.setPostCode(postCodeTF.getText());
        edit.setForeName(nameTF.getText());
        edit.setSurName(surnameTF.getText());
        edit.setPhoneNumber(mobilenumberTF.getText());
        closeWindow();
    }
}
