package Service;

import exception.InvalidGateException;
import exception.InvalidTicketException;
import models.Bill;
import models.Invoice;

public interface InvoiceService {
    public Bill generateInvoice(int ticketId, int gateId) throws InvalidTicketException, InvalidGateException;
}
