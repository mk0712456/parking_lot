package models;

public class Slab extends BaseModel{


    private VechicleType vechicleType;
    private int startHour;
    private int endHour;
    private int pricePerHour;
    public Slab(int id, VechicleType vechicleType, int startHour, int endHour, int pricePerHour) {
        setId(id);
        this.vechicleType = vechicleType;
        this.startHour = startHour;
        this.endHour = endHour;
        this.pricePerHour = pricePerHour;
    }

    public VechicleType getVechicleType() {
        return vechicleType;
    }

    public void setVechicleType(VechicleType vechicleType) {
        this.vechicleType = vechicleType;
    }

    public int getStartHour() {
        return startHour;
    }

    public void setStartHour(int startHour) {
        this.startHour = startHour;
    }

    public int getEndHour() {
        return endHour;
    }

    public void setEndHour(int endHour) {
        this.endHour = endHour;
    }

    public int getPricePerHour() {
        return pricePerHour;
    }

    public void setPricePerHour(int pricePerHour) {
        this.pricePerHour = pricePerHour;
    }
}
