package Service;

import models.Ticket;
import models.VechicleType;

public interface TicketService {
    public Ticket genrateTicket(int gate_id, String vechicle_no, String vechicle_type) throws Exception;
    public Ticket getTicketById(int ticketId);
}
