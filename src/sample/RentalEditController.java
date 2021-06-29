package sample;

import daos.TenantDao;
import entity.Address;
import entity.Rental;
import entity.Tenant;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class RentalEditController extends BaseController<RentalEditController> implements Initializable {

    @FXML
    private TextField objectNumberTF;

    @FXML
    private TextField objectTypeTF;

    @FXML
    private TextField descriptionTF;

    @FXML
    private TextField livingSpaceTF;

    @FXML
    private TextField pricePerSquareMeter;

    @FXML
    private TextField additionalCostTF;

    @FXML
    private TextField noticeTF;

    @FXML
    private ComboBox<Tenant> tenantCombo;

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
        Rental rental = RentalController.GetAddEditRental();
        TenantDao tenantDao = new TenantDao();
        List<Tenant> tenantList = tenantDao.findAll();
        ObservableList<Tenant> tenantsObservableList = FXCollections.observableArrayList();
        tenantsObservableList.addAll(tenantList);
        tenantCombo.setItems(tenantsObservableList);

        if (rental != null) {
            objectNumberTF.setText(rental.getObjectNr().toString());
            objectTypeTF.setText(rental.getObjectTyp());
            descriptionTF.setText(rental.getObjectDesc());
            livingSpaceTF.setText(rental.getLivingSpace().toString());
            pricePerSquareMeter.setText(rental.getPriceSquareMeterCold().toString());
            additionalCostTF.setText(rental.getAdditionalCosts().toString());
            noticeTF.setText(rental.getNotice());

            Address address = rental.getAddress();
            streetTF.setText(address.getStreet());
            postCodeTF.setText(address.getPostCode());
            cityTF.setText(address.getPlace());
            zipCodeTF.setText(address.getStreetNumber());
            for (Tenant t : tenantCombo.getItems()) {
                if (t.getId().equals(rental.getTenant().getId())) {
                    tenantCombo.getSelectionModel().select(t);
                    break;
                }
            }
        } else {
            livingSpaceTF.setText("10.0");
            additionalCostTF.setText("20.0");
            objectNumberTF.setText("123");
            pricePerSquareMeter.setText("3500.0");
        }
    }

    @Override
    protected Class<RentalEditController> getClassType() {
        return RentalEditController.class;
    }

    private void closeWindow() {
        Stage stage = (Stage) window.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void applyButtonClick() {
        Rental rental = RentalController.GetAddEditRental();
        if(rental == null) {
            rental = new Rental();
            rental.setAddress(new Address());
            RentalController.SetAddEditRental(rental);
        }

        rental.setObjectDesc(descriptionTF.getText());
        rental.setLivingSpace(Double.parseDouble(livingSpaceTF.getText()));
        rental.setPriceSquareMeterCold(Double.parseDouble(pricePerSquareMeter.getText()));
        rental.setAdditionalCosts(Double.parseDouble(additionalCostTF.getText()));
        rental.setNotice(noticeTF.getText());

        Address address = rental.getAddress();
        address.setStreetNumber(zipCodeTF.getText());
        address.setPostCode(postCodeTF.getText());
        address.setStreet(streetTF.getText());
        address.setPlace(cityTF.getText());

        rental.setObjectTyp(objectTypeTF.getText());
        rental.setObjectNr(Integer.parseInt(objectNumberTF.getText()));
        rental.setNotice(noticeTF.getText());
        rental.setTenant(tenantCombo.getValue());
        closeWindow();
    }
}
