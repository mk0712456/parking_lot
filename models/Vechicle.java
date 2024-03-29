package models;

public class Vechicle extends BaseModel{
    private String vechicle_no;
    private VechicleType vechicleType;

    public String getVechicle_no() {
        return vechicle_no;
    }

    public void setVechicle_no(String vechicle_no) {
        this.vechicle_no = vechicle_no;
    }

    public VechicleType getVechicleType() {
        return vechicleType;
    }

    public void setVechicleType(VechicleType vechicleType) {
        this.vechicleType = vechicleType;
    }
}
