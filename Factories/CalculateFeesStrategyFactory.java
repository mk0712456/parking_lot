package Factories;

import Repositories.SlabRepository;
import Strategy.PricingStrategy.CalculateFeesStrategy;
import Strategy.PricingStrategy.weekdayStrategy;
import Strategy.PricingStrategy.weekendStrategy;

import java.util.Calendar;
import java.util.Date;

public class CalculateFeesStrategyFactory {
    public SlabRepository slabRepository;
    public CalculateFeesStrategyFactory(SlabRepository slabRepository){
        this.slabRepository = slabRepository;
    }
    public CalculateFeesStrategy getCalculateFeesStrayegy(Date exitDate){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(exitDate);
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        boolean is_weekend = day == Calendar.SATURDAY || day == Calendar.SUNDAY;
        CalculateFeesStrategy calculateFeesStrategy;
        if(is_weekend){
            calculateFeesStrategy = new weekendStrategy(slabRepository);
        }
        else{
            calculateFeesStrategy = new weekdayStrategy();
        }
        return calculateFeesStrategy;
    }
}
