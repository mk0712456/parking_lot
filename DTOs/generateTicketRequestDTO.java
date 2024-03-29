package DTOs;



public class generateTicketRequestDTO {
    private int gate_id;
    private String vechicle_no;
    private String vechicle_type;

    public int getGate_id() {
        return gate_id;
    }

    public void setGate_id(int gate_id) {
        this.gate_id = gate_id;
    }

    public String getVechicle_no() {
        return vechicle_no;
    }

    public void setVechicle_no(String vechicle_no) {
        this.vechicle_no = vechicle_no;
    }

    public String getVechicle_type() {
        return vechicle_type;
    }

    public void setVechicle_type(String vechicle_type) {
        this.vechicle_type = vechicle_type;
    }
}
