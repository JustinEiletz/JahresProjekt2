package entity;

import javafx.beans.property.SimpleStringProperty;

public class RentalTableView extends Rental {

    private final SimpleStringProperty rentalId = new SimpleStringProperty(String.valueOf(getObjectNr()));
    private final SimpleStringProperty rentalDesc = new SimpleStringProperty(getObjectDesc());
    private final SimpleStringProperty rentalTyp = new SimpleStringProperty(getObjectTyp());
    private final SimpleStringProperty rentalNotice = new SimpleStringProperty(getNotice());

    public RentalTableView() {
        this("", "", "", "");
    }

    public RentalTableView(final String rentalId, final String rentalDesc, final String rentalTyp, final String rentalNotice) {
        setRentalId(rentalId);
        setRentalDesc(rentalDesc);
        setRentalTyp(rentalTyp);
        setRentalNotice(rentalNotice);
    }

    public void setRentalId(String rentalId) {
        this.rentalId.set(rentalId);
    }

    public void setRentalDesc(String rentalDesc) {
        this.rentalDesc.set(rentalDesc);
    }

    public void setRentalTyp(String rentalTyp) {
        this.rentalTyp.set(rentalTyp);
    }

    public void setRentalNotice(String rentalNotice) {
        this.rentalNotice.set(rentalNotice);
    }

    public String getRentalId() {
        return rentalId.get();
    }

    public SimpleStringProperty rentalIdProperty() {
        return rentalId;
    }

    public String getRentalDesc() {
        return rentalDesc.get();
    }

    public SimpleStringProperty rentalDescProperty() {
        return rentalDesc;
    }

    public String getRentalTyp() {
        return rentalTyp.get();
    }

    public SimpleStringProperty rentalTypProperty() {
        return rentalTyp;
    }

    public String getRentalNotice() {
        return rentalNotice.get();
    }

    public SimpleStringProperty rentalNoticeProperty() {
        return rentalNotice;
    }
}
