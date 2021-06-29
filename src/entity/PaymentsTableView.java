package entity;

import javafx.beans.property.SimpleStringProperty;

public class PaymentsTableView extends Rental {

    public final SimpleStringProperty rentalId = new SimpleStringProperty("Id");
    public final SimpleStringProperty rentalAdditionalCosts = new SimpleStringProperty("AdditionalCosts");
    public final SimpleStringProperty rentalLivingSpace = new SimpleStringProperty("LivingSpace");
    public final SimpleStringProperty rentalAdditionalCostsPerSquareMeter = new SimpleStringProperty("AdditionalCostsPerSquareMeter");
    public final SimpleStringProperty rentalPriceSquareMeterWarm = new SimpleStringProperty("PriceSquareMeterWarm");
    public final SimpleStringProperty rentalObjectPriceCold = new SimpleStringProperty("ObjectPriceCold");
    public final SimpleStringProperty rentalObjectPriceWarm = new SimpleStringProperty("ObjectPriceWarm");

    private Integer id;
    private Double additionalCosts;
    private Double livingSpace;
    private Double additionalCostsPerSquareMeter;
    private Double priceSquareMeterWarm;
    private Double objectPriceCold;
    private Double objectPriceWarm;


    public PaymentsTableView() {
        this(null, null, null, null, null, null, null);
    }

    public PaymentsTableView(final Integer id, final Double additionalCosts, final Double livingSpace, final Double additionalCostsPerSquareMeter, final Double priceSquareMeterWarm, final Double objectPriceCold, final Double objectPriceWarm) {
        this.id = id;
        this.additionalCosts = additionalCosts;
        this.livingSpace = livingSpace;
        this.additionalCostsPerSquareMeter = additionalCostsPerSquareMeter;
        this.priceSquareMeterWarm = priceSquareMeterWarm;
        this.objectPriceCold = objectPriceCold;
        this.objectPriceWarm = objectPriceWarm;
    }

    public String getRentalId() { return rentalId.get(); }
    public SimpleStringProperty rentalIdProperty() { return rentalId; }

    public String getRentalAdditionalCosts() { return rentalAdditionalCosts.get(); }
    public SimpleStringProperty rentalAdditionalCostsProperty() { return rentalAdditionalCosts; }

    public String getRentalLivingSpace() { return rentalLivingSpace.get(); }
    public SimpleStringProperty rentalLivingSpaceProperty() { return rentalLivingSpace; }

    public String getRentalAdditionalCostsPerSquareMeter() { return rentalAdditionalCostsPerSquareMeter.get(); }
    public SimpleStringProperty rentalAdditionalCostsPerSquareMeterProperty() { return rentalAdditionalCostsPerSquareMeter; }

    public String getRentalPriceSquareMeterWarm() { return rentalPriceSquareMeterWarm.get(); }
    public SimpleStringProperty rentalPriceSquareMeterWarmProperty() { return rentalPriceSquareMeterWarm; }

    public String getRentalObjectPriceCold() { return rentalObjectPriceCold.get(); }
    public SimpleStringProperty rentalObjectPriceColdProperty() { return rentalObjectPriceCold; }

    public String getRentalObjectPriceWarm() { return rentalObjectPriceWarm.get(); }
    public SimpleStringProperty rentalObjectPriceWarmProperty() { return rentalObjectPriceWarm; }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Double getAdditionalCosts() { return additionalCosts; }
    public void setAdditionalCosts(Double additionalCosts) { this.additionalCosts = additionalCosts; }

    public Double getLivingSpace() { return livingSpace; }
    public void setLivingSpace(Double livingSpace) { this.livingSpace = livingSpace; }

    public Double getAdditionalCostsPerSquareMeter() { return additionalCostsPerSquareMeter; }
    public void setAdditionalCostsPerSquareMeter(Double additionalCostsPerSquareMeter) { this.additionalCostsPerSquareMeter = additionalCostsPerSquareMeter; }

    public Double getPriceSquareMeterWarm() { return priceSquareMeterWarm; }
    public void setPriceSquareMeterWarm(Double priceSquareMeterWarm) { this.priceSquareMeterWarm = priceSquareMeterWarm; }

    public Double getObjectPriceCold() { return objectPriceCold; }
    public void setObjectPriceCold(Double objectPriceCold) { this.objectPriceCold = objectPriceCold; }

    public Double getObjectPriceWarm() { return objectPriceWarm; }
    public void setObjectPriceWarm(Double objectPriceWarm) { this.objectPriceWarm = objectPriceWarm; }
}
