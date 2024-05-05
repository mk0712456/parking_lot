package controllers;

import DTOs.Response;
import DTOs.ResponseStatus;
import DTOs.generateInvoiceRequestDTO;
import DTOs.generateInvoiceResponseDTO;
import Service.InvoiceService;
import exception.InvalidRequestException;
import models.Bill;
import models.Invoice;

public class InvoiceController {
    private InvoiceService invoiceService;
    public InvoiceController(InvoiceService invoiceService){
        this.invoiceService = invoiceService;
    }
    public generateInvoiceResponseDTO generateInvoice(generateInvoiceRequestDTO requestDTO){
        generateInvoiceResponseDTO responseDTO = new generateInvoiceResponseDTO();
        try{
            if(requestDTO.getGateId()<0){
                throw new InvalidRequestException("Invalid gate Id");
            }
            if(requestDTO.getTicketId()<0){
                throw new InvalidRequestException("Invalid ticketID");
            }
        }
        catch (Exception e) {
            Response response = new Response();
            response.setResponseStatus(ResponseStatus.FAIL);
            response.setError(e.getMessage());
            responseDTO.setResponse(response);
            return responseDTO;
        }
        try{
            Bill bill = invoiceService.generateInvoice(requestDTO.getTicketId(), requestDTO.getGateId());
            Response response = new Response();
            response.setResponseStatus(ResponseStatus.SUCCESS);
            responseDTO.setInvoice(bill);
            responseDTO.setResponse(response);
            return  responseDTO;
        }
        catch (Exception e){
            Response response = new Response();
            response.setResponseStatus(ResponseStatus.FAIL);
            response.setError(e.getMessage());
            responseDTO.setResponse(response);
            return responseDTO;
        }
    }
}
