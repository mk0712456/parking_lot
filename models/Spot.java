package models;

public class Spot extends BaseModel{
    private String name;
    private SpotStatus spotStatus;
    private VechicleType vechicleType;

    public Spot(String name, SpotStatus spotStatus, VechicleType vechicleType) {
        this.name = name;
        this.spotStatus = spotStatus;
        this.vechicleType = vechicleType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SpotStatus getSpotStatus() {
        return spotStatus;
    }

    public void setSpotStatus(SpotStatus spotStatus) {
        this.spotStatus = spotStatus;
    }

    public VechicleType getVechicleType() {
        return vechicleType;
    }

    public void setVechicleType(VechicleType vechicleType) {
        this.vechicleType = vechicleType;
    }
}
