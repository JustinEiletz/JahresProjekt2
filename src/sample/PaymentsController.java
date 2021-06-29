package sample;

import calculations.CalculationRental;
import daos.RentalDao;
import entity.PaymentsTableView;
import entity.PaymentsTableView;
import entity.Rental;
import entity.Tenant;
import entity.TenantTableView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import manager.ViewManager;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class PaymentsController extends BaseController<PaymentsController> implements Initializable {

    private final RentalDao rentalDao = new RentalDao();
    private final CalculationRental calculationRental = new CalculationRental();

    @FXML
    private TableView<PaymentsTableView> paymentsTV;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        PaymentsTableView pTV = new PaymentsTableView();
        TableColumn<PaymentsTableView, String> id = new TableColumn<>(pTV.getRentalId());
        TableColumn<PaymentsTableView, String> additionalCosts = new TableColumn<>(pTV.getRentalAdditionalCosts());
        TableColumn<PaymentsTableView, String> livingSpace = new TableColumn<>(pTV.getRentalLivingSpace());
        TableColumn<PaymentsTableView, String> additionalCostsPerSquareMeter = new TableColumn<>(pTV.getRentalAdditionalCostsPerSquareMeter());
        TableColumn<PaymentsTableView, String> priceSquareMeterWarm = new TableColumn<>(pTV.getRentalPriceSquareMeterWarm());
        TableColumn<PaymentsTableView, String> objectPriceCold = new TableColumn<>(pTV.getRentalObjectPriceCold());
        TableColumn<PaymentsTableView, String> objectPriceWarm = new TableColumn<>(pTV.getRentalObjectPriceWarm());
        paymentsTV.getColumns().addAll(id, additionalCosts, livingSpace, additionalCostsPerSquareMeter, priceSquareMeterWarm, objectPriceCold, objectPriceWarm);

        id.setCellValueFactory(new PropertyValueFactory<>(pTV.getRentalId()));
        id.setMinWidth(150);

        additionalCosts.setCellValueFactory(new PropertyValueFactory<>(pTV.getRentalAdditionalCosts()));
        additionalCosts.setMinWidth(150);

        livingSpace.setCellValueFactory(new PropertyValueFactory<>(pTV.getRentalLivingSpace()));
        livingSpace.setMinWidth(150);

        additionalCostsPerSquareMeter.setCellValueFactory(new PropertyValueFactory<>(pTV.getRentalAdditionalCosts()));
        additionalCostsPerSquareMeter.setMinWidth(150);

        priceSquareMeterWarm.setCellValueFactory(new PropertyValueFactory<>(pTV.getRentalPriceSquareMeterWarm()));
        priceSquareMeterWarm.setMinWidth(150);

        objectPriceCold.setCellValueFactory(new PropertyValueFactory<>(pTV.getRentalObjectPriceCold()));
        objectPriceCold.setMinWidth(150);

        objectPriceWarm.setCellValueFactory(new PropertyValueFactory<>(pTV.getRentalObjectPriceWarm()));
        objectPriceWarm.setMinWidth(150);

        updatePaymentsTableView();
    }

    @Override
    protected Class<PaymentsController> getClassType() { return PaymentsController.class; }

    private void updatePaymentsTableView() {
        paymentsTV.getItems().clear();
        List<Rental> rentals = rentalDao.findAll();

        for (Rental rental : rentals) {
            PaymentsTableView pTV = new PaymentsTableView();
            pTV.setId(rental.getId());
            pTV.setAdditionalCosts(rental.getAdditionalCosts());
            pTV.setLivingSpace(rental.getLivingSpace());
            pTV.setPriceSquareMeterCold(rental.getPriceSquareMeterCold());
            pTV.setAdditionalCostsPerSquareMeter(calculationRental.calculateAdditionalCostsPerSquareMeter(rental.getAdditionalCosts(), rental.getLivingSpace()));
            pTV.setPriceSquareMeterWarm(calculationRental.calculatePriceSquareMeterWarm(pTV.getPriceSquareMeterCold(), pTV.getAdditionalCostsPerSquareMeter()));
            pTV.setObjectPriceCold(calculationRental.calculateObjectPriceCold(rental.getLivingSpace(), pTV.getPriceSquareMeterCold()));
            pTV.setObjectPriceWarm(calculationRental.calculateObjectPriceWarm(rental.getLivingSpace(), pTV.getPriceSquareMeterWarm()));
            paymentsTV.getItems().add(pTV);
        }
    }
}
