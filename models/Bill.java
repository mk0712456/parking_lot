package models;

import java.util.Date;
import java.util.List;

public class Bill extends BaseModel {
    private Ticket ticket;
    private Date exitTime;
    private double total_amount;
    private List<BillDetails> details;

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public Date getExitTime() {
        return exitTime;
    }

    public void setExitTime(Date exitTime) {
        this.exitTime = exitTime;
    }

    public double getTotal_amount() {
        return total_amount;
    }

    private void setTotal_amount(double total_amount) {
        this.total_amount = total_amount;
    }

    public List<BillDetails> getDetails() {
        return details;
    }

    public void setDetails(List<BillDetails> details) {
        this.details = details;
        double total_amount = 0;
        for(BillDetails billDetails : details){
            total_amount = billDetails.getPrice();
        }
        setTotal_amount(total_amount);
    }
}
