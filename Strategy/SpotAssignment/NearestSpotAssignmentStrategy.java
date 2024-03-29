package Strategy.SpotAssignment;

import exception.NoSpotAvailableException;
import models.*;

public class NearestSpotAssignmentStrategy implements AssignSpotStrategy {
    @Override
    public Spot assignSpot(VechicleType vechicleType, ParkingLot parkingLot) throws NoSpotAvailableException {
        for(Floor floor : parkingLot.getFloors()){
            if(floor.getFloorStatus().equals(FloorStatus.OPERATIONAL)){
                for(Section section : floor.getSections()){
                    for(Spot spot : section.getSpots()){
                        if(spot.getSpotStatus().equals(SpotStatus.UNOCCUPIED)){
                            spot.setSpotStatus(SpotStatus.OCCUPIED);
                            return spot;
                        }
                    }
                }
            }
        }
        throw new NoSpotAvailableException("No spot available in the PARKING LOT!!!");

    }
}
