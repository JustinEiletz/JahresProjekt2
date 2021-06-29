package entity;

import javafx.beans.property.SimpleStringProperty;

public class RentalTableView extends Rental {

    public final SimpleStringProperty rentalId = new SimpleStringProperty("Id");
    public final SimpleStringProperty rentalDesc = new SimpleStringProperty("Desc");
    public final SimpleStringProperty rentalTyp = new SimpleStringProperty("Typ");
    public final SimpleStringProperty rentalNotice = new SimpleStringProperty("Notice");

    private Integer id;
    private String desc;
    private String typ;
    private String notice;

    public RentalTableView() {
        this(null, "", "", "");
    }

    public RentalTableView(final Integer rentalId, final String rentalDesc, final String rentalTyp, final String rentalNotice) {
        this.id = rentalId;
        this.desc = rentalDesc;
        this.typ = rentalTyp;
        this.notice = rentalNotice;
    }

    public String getRentalId() { return rentalId.get(); }

    public SimpleStringProperty rentalIdProperty() { return rentalId; }

    public String getRentalDesc() { return rentalDesc.get(); }

    public SimpleStringProperty rentalDescProperty() { return rentalDesc; }

    public String getRentalTyp() { return rentalTyp.get(); }

    public SimpleStringProperty rentalTypProperty() { return rentalTyp; }

    public String getRentalNotice() { return rentalNotice.get(); }

    public SimpleStringProperty rentalNoticeProperty() { return rentalNotice; }

    public Integer getId() { return id; }
    public void setId(final Integer id) { this.id = id; }

    public String getDesc() { return desc; }
    public void setDesc(final String desc) { this.desc = desc; }

    public String getTyp() { return typ; }
    public void setTyp(final String typ) { this.typ = typ; }

    public String getNotice() { return notice; }
    public void setNotice(final String notice) { this.notice = notice; }




}
