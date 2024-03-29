package Service;

import Repositories.ParkingLotRepository;
import Repositories.TicketRepository;
import Repositories.VechicleRepository;
import Strategy.SpotAssignment.AssignSpotStrategy;
import models.*;

import java.util.Date;

public class TicketServiceImpl implements TicketService{
   /* Ticket service needs to access gaterespository via gate service as best practice
        GateRepository gateRepository;

        public TicketServiceImpl(GateRepository gateRepository) {
            this.gateRepository = gateRepository;
        }

    */
    GateService gateService;
    VechicleRepository vechicleRepository;
    AssignSpotStrategy assignSpotStrategy;
    ParkingLotRepository parkingLotRepository;
    TicketRepository ticketRepository;

    public TicketServiceImpl(GateService gateService, VechicleRepository vechicleRepository, AssignSpotStrategy assignSpotStrategy, ParkingLotRepository parkingLotRepository, TicketRepository ticketRepository) {
        this.gateService = gateService;
        this.vechicleRepository = vechicleRepository;
        this.assignSpotStrategy = assignSpotStrategy;
        this.parkingLotRepository = parkingLotRepository;
        this.ticketRepository = ticketRepository;
    }

    @Override
    public Ticket genrateTicket(int gate_id, String vechicle_no, String vechicle_type) throws Exception {
         /*
        1. Using gate id, get the g  ate object
        2. Do a createIfNotExists on the vehicle object
        3. Using strategy pattern, figure out an empty spot or throw an error
        4. create a ticket object and store it in db
         */
        Gate gate = gateService.getGateById(gate_id);
        VechicleType type = VechicleType.get_type_from_string(vechicle_type);
        Vechicle vechicle = vechicleRepository. create_if_not_present(vechicle_no, type);
        ParkingLot parkingLotbyGateId = parkingLotRepository.getParkingLot(gate_id);
        if(parkingLotbyGateId == null){
            throw new Exception("Invalid Gate Id");
        }
        Spot spot = assignSpotStrategy.assignSpot(type, parkingLotbyGateId);
        Ticket ticket = new Ticket();
        ticket.setGate(gate);
        ticket.setVechicle(vechicle);
        ticket.setSpot(spot);
        ticket.setEntryTime(new Date());
        return ticketRepository.insertTicket(ticket);

    }
    public Ticket getTicketById(int ticketId) {
        return ticketRepository.getTicketById(ticketId);
    }
}
