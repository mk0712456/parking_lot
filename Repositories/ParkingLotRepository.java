package Repositories;

import models.Gate;
import models.ParkingLot;

import java.util.HashMap;
import java.util.Map;

public class ParkingLotRepository {
    Map<Integer, ParkingLot> map;

    public ParkingLotRepository(Map<Integer, ParkingLot> map) {
        this.map = map;
    }
    public ParkingLotRepository() {
        this.map = new HashMap<>();
    }
    public ParkingLot getParkingLot(int gate_id){
        for(Map.Entry<Integer, ParkingLot> entry : map.entrySet()){
            ParkingLot parkingLot = entry.getValue();
            for(Gate gate: parkingLot.getGate()){
                if(gate.getId() == gate_id){
                    return parkingLot;
                }
            }
        }
        return null;
    }
}
