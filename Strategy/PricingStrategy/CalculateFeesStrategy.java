package Strategy.PricingStrategy;

import models.VechicleType;

import java.util.Date;

public interface CalculateFeesStrategy {
    public double calculateFees(Date startTime, Date endTime, VechicleType vechicleType);
}
