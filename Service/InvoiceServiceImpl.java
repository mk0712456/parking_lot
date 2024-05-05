package Service;

import Factories.CalculateFeesStrategyFactory;
import Repositories.InvoiceRepository;
import Strategy.PricingStrategy.CalculateFeesStrategy;
import exception.InvalidGateException;
import exception.InvalidTicketException;
import models.*;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

public class InvoiceServiceImpl implements InvoiceService{
    private TicketService ticketService;
    private GateService gateService;
    private CalculateFeesStrategyFactory calculateFeesStrategyFactory;
    private InvoiceRepository invoiceRepository;
    public InvoiceServiceImpl(TicketService ticketService, GateService gateService, CalculateFeesStrategyFactory calculateFeesStrategyFactory, InvoiceRepository invoiceRepository){
        this.ticketService = ticketService;
        this.gateService = gateService;
        this.calculateFeesStrategyFactory = calculateFeesStrategyFactory;
        this.invoiceRepository = invoiceRepository;
    }
    @Override
    public Bill generateInvoice(int ticketId, int gateId) throws InvalidTicketException, InvalidGateException {
        /*
        Get tckt from db, if tckt is null throw exception
        Get gate from db, if it is entry get throw exception
        calculate cost by entry time from tckt with relevant strategy which leads us to creating the pricing strategy
            1)weekday strategy
            2)weekend strategy
        create invoice store in db and return to controller
         */
        Ticket ticket = ticketService.getTicketById(ticketId);
        if(ticket == null){
            throw new InvalidTicketException("Ticket is not present in db");
        }
        Gate gate = gateService.getGateById(gateId);
        if(gate == null ){
            throw new InvalidGateException("Invoice cant be generated as gate is invalid");
        }
        if(gate.getGateType().equals(GateType.ENTRY)){
            throw new InvalidGateException("Invoice can be genrated from entry gate");
        }

        Date entryDate = ticket.getEntryTime();
        Date exitDate = new Date();
        // by giving in exittime factory figures out which strategy to use and sends the specific obj
        CalculateFeesStrategy calculateFeesStrategy = calculateFeesStrategyFactory.getCalculateFeesStrayegy(exitDate);
        //amount is calculated by calling relevant strategy
        double total_amount = calculateFeesStrategy.calculateFees(entryDate, exitDate, ticket.getVechicle().getVechicleType());

        BillDetails billDetails = new BillDetails();
        billDetails.setName("Parking charges");
        billDetails.setPrice(total_amount);

        Bill bill = new Bill();
        bill.setDetails(Arrays.asList(billDetails));
        bill.setTicket(ticket);
        bill.setExitTime(exitDate);

        return invoiceRepository.insertBill(bill);
    }
}
