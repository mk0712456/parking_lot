package DTOs;

import models.Bill;
import models.Invoice;

public class generateInvoiceResponseDTO {
    private Bill bill;
    private Response response;

    public Bill getInvoice() {
        return bill;
    }

    public void setInvoice(Bill bill) {
        this.bill = bill;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }
}
