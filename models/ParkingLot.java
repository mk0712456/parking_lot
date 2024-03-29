package models;

import java.util.List;

public class ParkingLot extends BaseModel{

    private List<Floor> floors;
    private List<Gate> gate;

    public List<Floor> getFloors() {
        return floors;
    }

    public void setFloors(List<Floor> floors) {
        this.floors = floors;
    }

    public List<Gate> getGate() {
        return gate;
    }

    public void setGate(List<Gate> gate) {
        this.gate = gate;
    }


}
