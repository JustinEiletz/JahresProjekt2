package calculations;

public class CalculationRental {

    public Double calculateAdditionalCostsPerSquareMeter(final Double additionalCosts,  final Double livingSpace) {
        return (additionalCosts / livingSpace);
    }

    public Double calculatePriceSquareMeterWarm(final Double priceSquareMeterCold, final Double additionalCostsPerSquareMeter) {
        return (priceSquareMeterCold * additionalCostsPerSquareMeter);
    }

    public Double calculateObjectPriceCold(final Double livingSpace, final Double priceSquareMeterCold) {
        return (livingSpace * priceSquareMeterCold);
    }

    public Double calculateObjectPriceWarm(final Double livingSpace, final Double priceSquareMeterWarm) {
        return (livingSpace * priceSquareMeterWarm);
    }
}
