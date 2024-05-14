package Strategy.PricingStrategy;

import Repositories.SlabRepository;
import Utils.DateAndTimeUtils;
import models.Slab;
import models.VechicleType;

import java.util.Date;
import java.util.List;

public class weekendStrategy implements CalculateFeesStrategy{
    private SlabRepository slabRepository;
    public weekendStrategy(SlabRepository slabRepository){
        this.slabRepository = slabRepository;
    }
    @Override
    public double calculateFees(Date startTime, Date endTime, VechicleType vechicleType) {
        List<Slab> slabs = slabRepository.getSlabByVechicleType(vechicleType);
        int hrs = DateAndTimeUtils.calculatehrs(startTime, endTime);
        double total_amount = 0;
        for(Slab slab : slabs){
            if(hrs >= slab.getStartHour() && slab.getEndHour()!=-1){
                if(hrs >= slab.getEndHour()){
                    total_amount = total_amount + ( (slab.getEndHour() - slab.getStartHour()) * slab.getPricePerHour() );
                }
                else {
                    total_amount = total_amount + ( (hrs - slab.getStartHour()) * slab.getPricePerHour() );
                }
            }
            else if(slab.getEndHour() == -1){
                total_amount = total_amount +( (hrs - slab.getStartHour()) * slab.getPricePerHour() );
            }
            else{
                break;
            }
        }
        return total_amount;
    }
}
