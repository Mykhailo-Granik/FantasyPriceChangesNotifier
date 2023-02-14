package persistence;

public class Destination {

    private int minimalTargetForPotentialPriceRise;
    private int maximalTargetForPotentialPriceDrop;
    private DestinationAddress destinationAddress;

    public Destination(double minimalTargetForPotentialPriceRise, double maximalTargetForPotentialPriceDrop, DestinationAddress destinationAddress) {
        this.minimalTargetForPotentialPriceRise = (int) Math.round(minimalTargetForPotentialPriceRise * 10);
        this.maximalTargetForPotentialPriceDrop = (int) Math.round(maximalTargetForPotentialPriceDrop * 10);
        this.destinationAddress = destinationAddress;
    }
}
