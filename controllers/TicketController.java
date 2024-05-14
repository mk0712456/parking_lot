package controllers;


import DTOs.Response;
import DTOs.ResponseStatus;
import DTOs.generateTicketRequestDTO;
import DTOs.generateTicketResponseDTO;
import Service.TicketService;
import exception.InvalidRequestException;
import models.Ticket;

public class TicketController {
    private TicketService ticketService;
    //actually a dependency injection
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    public generateTicketResponseDTO generateTicket(generateTicketRequestDTO requestDTO){
        //handling response and generateTicketResponse seperaely to displY specific errors to users
        generateTicketResponseDTO responseDTO = new generateTicketResponseDTO();

        try {
            if (requestDTO.getGate_id() < 0) {
                throw new InvalidRequestException("Invalid Gate ID");
            }
            if (requestDTO.getVechicle_type() == null || requestDTO.getVechicle_type().equals("")) {
                throw new InvalidRequestException("Type is mandatory");
            }
        }
        catch (InvalidRequestException e){
            Response response = new Response();
            response.setResponseStatus(ResponseStatus.FAIL);
            response.setError(e.getMessage());
            responseDTO.setResponse(response);
            return responseDTO;
        }
            //created responseDTO obj coz its a returm typr
            //orelese we would have just imported that library to use its functions
        Response response = new Response();
        try{
            Ticket ticket = ticketService.genrateTicket(requestDTO.getGate_id(), requestDTO.getVechicle_no(), requestDTO.getVechicle_type());
            responseDTO.setTicket(ticket);
            response.setResponseStatus(ResponseStatus.SUCCESS);

        }
        catch (Exception e){
           response.setResponseStatus(ResponseStatus.FAIL);
           response.setError(e.getMessage());
        }
        responseDTO.setResponse(response);
        return responseDTO;
    }
}
