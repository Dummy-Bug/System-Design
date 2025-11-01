package com.lld.factories;

import com.lld.models.vehicle.VehicleType;
import com.lld.strategies.price.BikePriceStrategy;
import com.lld.strategies.price.CarPriceStrategy;
import com.lld.strategies.price.PriceCalculationStrategy;

public class PriceCalculationFactory {

    public static PriceCalculationStrategy getPriceCalculationStrategyInstance(VehicleType vehicleType) {
        if (vehicleType.equals(VehicleType.BIKE)) {
            return new BikePriceStrategy();
        } else {
            return new CarPriceStrategy();
        }
    }
}
