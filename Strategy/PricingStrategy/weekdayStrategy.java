package Strategy.PricingStrategy;

import Utils.DateAndTimeUtils;
import models.VechicleType;

import java.util.Date;

public class weekdayStrategy implements CalculateFeesStrategy{
    @Override
    public double calculateFees(Date startTime, Date endTime, VechicleType vechicleType) {
        return DateAndTimeUtils.calculatehrs(startTime, endTime) * 10 ;
    }
}
