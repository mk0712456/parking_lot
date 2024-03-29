package Strategy.SpotAssignment;

import exception.NoSpotAvailableException;
import models.ParkingLot;
import models.Spot;
import models.VechicleType;

public interface AssignSpotStrategy {
    public Spot assignSpot(VechicleType vechicleType, ParkingLot parkingLot) throws NoSpotAvailableException;
}
