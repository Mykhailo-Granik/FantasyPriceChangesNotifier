package persistence;

import util.DoubleWithOnePlace;

public class Destination {

    private DoubleWithOnePlace minimalTargetForPotentialPriceRise;
    private DoubleWithOnePlace maximalTargetForPotentialPriceDrop;
    private DestinationAddress destinationAddress;

    public Destination(double minimalTargetForPotentialPriceRise, double maximalTargetForPotentialPriceDrop, DestinationAddress destinationAddress) {
        this.minimalTargetForPotentialPriceRise = new DoubleWithOnePlace(minimalTargetForPotentialPriceRise);
        this.maximalTargetForPotentialPriceDrop = new DoubleWithOnePlace(maximalTargetForPotentialPriceDrop);
        this.destinationAddress = destinationAddress;
    }
}
