import DTOs.generateTicketRequestDTO;
import DTOs.generateTicketResponseDTO;
import DTOs.generateInvoiceRequestDTO;
import DTOs.generateInvoiceResponseDTO;
import Factories.CalculateFeesStrategyFactory;
import Repositories.*;
import Service.*;
//import Service.GateServiceImpl;
import Strategy.SpotAssignment.AssignSpotStrategy;
import Strategy.SpotAssignment.NearestSpotAssignmentStrategy;
import controllers.InvoiceController;
import controllers.TicketController;
import models.*;

import java.util.*;

import static javax.swing.UIManager.put;

public class parkingLotRunner {
    public static void main(String[] args) {
        //gate_repo and parkinglot_repo should not be null initially
        Gate gate1 = new Gate();
        gate1.setGateType(GateType.ENTRY);
        gate1.setId(1);
        gate1.setName("Entry-1");
        gate1.setOperator(new Operator());

        Gate gate2 = new Gate();
        gate2.setGateType(GateType.EXIT);
        gate2.setId(2);
        gate2.setName("Exit-1");
        gate2.setOperator(new Operator());

        Gate gate3 = new Gate();
        gate3.setGateType(GateType.ENTRY);
        gate3.setId(3);
        gate3.setName("Entry-2");
        gate3.setOperator(new Operator());

        Map<Integer, Gate> gateMap = new HashMap<Integer, Gate>(){{
            put(1, gate1);
            put(2, gate2);
            put(3, gate3);
        }};
        List<Spot> spots  = Arrays.asList(new Spot("S1", SpotStatus.UNOCCUPIED, VechicleType.CAR), new Spot("S2", SpotStatus.UNOCCUPIED, VechicleType.BIKE), new Spot("S3", SpotStatus.UNOCCUPIED, VechicleType.BIKE) );
        List<Section> sections = new ArrayList<>();

        Section section = new Section();
        section.setId(1);
        section.setName("section A");
        section.setSpots(spots);
        sections.add(section);

        List<Floor> floors = new ArrayList<>();
        Floor floor = new Floor();
        floor.setId(1);
        floor.setFloor_no(0);
        floor.setFloorStatus(FloorStatus.OPERATIONAL);
        floor.setSections(sections);
        floors.add(floor);

        List<Gate> gates = Arrays.asList(gate1, gate2, gate3);

        ParkingLot parkingLot = new ParkingLot();
        parkingLot.setGate(gates);
        parkingLot.setFloors(floors);
        parkingLot.setId(1);

        Map<Integer, ParkingLot> parkingLotMap = new HashMap<Integer, ParkingLot>(){{
            put(1, parkingLot);
        }};

        Slab slab1 = new Slab(1,VechicleType.CAR,0,2,5);
        Slab slab2 = new Slab(2,VechicleType.CAR,2,4,10);
        Slab slab3 = new Slab(3,VechicleType.CAR,4,8,20);
        Slab slab4 = new Slab(4, VechicleType.CAR,8,-1,30);
        Map<Integer,Slab> slabMap = new HashMap<Integer, Slab>(){{
            put(1, slab1);
            put(2, slab2);
            put(3, slab3);
            put(4, slab4);
        }};
        SlabRepository slabRepository = new SlabRepository(slabMap);
        InvoiceRepository invoiceRepository = new InvoiceRepository();
        CalculateFeesStrategyFactory calculateFeesStrategyFactory = new CalculateFeesStrategyFactory(slabRepository);

        GateRepository gateRepository = new GateRepository(gateMap);
        ParkingLotRepository parkingLotRepository = new ParkingLotRepository(parkingLotMap);
        TicketRepository ticketRepository = new TicketRepository();
        VechicleRepository vechicleRepository = new VechicleRepository();

        GateService gateService = new GateService(gateRepository);
        AssignSpotStrategy assignSpotStrategy = new NearestSpotAssignmentStrategy();

        TicketService ticketService = new TicketServiceImpl(gateService, vechicleRepository, assignSpotStrategy, parkingLotRepository, ticketRepository);
        InvoiceService invoiceService = new InvoiceServiceImpl(ticketService, gateService, calculateFeesStrategyFactory, invoiceRepository);

        TicketController ticketController = new TicketController(ticketService);
        InvoiceController invoiceController = new InvoiceController(invoiceService);

        generateTicketRequestDTO requestDTO = new generateTicketRequestDTO();
        requestDTO.setGate_id(1);
        requestDTO.setVechicle_no("TN 94 7984");
        requestDTO.setVechicle_type(VechicleType.BIKE.toString());

        generateTicketRequestDTO requestDTO2 = new generateTicketRequestDTO();
        requestDTO2.setGate_id(1);
        requestDTO2.setVechicle_no("TN 94 7900");
        requestDTO2.setVechicle_type(VechicleType.CAR.toString());

        generateTicketRequestDTO requestDTO3 = new generateTicketRequestDTO();
        requestDTO3.setGate_id(3);
        requestDTO3.setVechicle_no("TN 94 7954");
        requestDTO3.setVechicle_type(VechicleType.CAR.toString());

        generateTicketResponseDTO responseDTO = ticketController.generateTicket(requestDTO);
        generateTicketResponseDTO responseDTO2 = ticketController.generateTicket(requestDTO2);
        generateTicketResponseDTO responseDTO3 = ticketController.generateTicket(requestDTO3);
        int ticketId = responseDTO.getTicket().getId();
        System.out.println(responseDTO);

        try{
            Thread.sleep(5000);
        }
        catch (Exception e){
            System.out.println("Thread sleep exception");
        }

        generateInvoiceRequestDTO invoiceRequestDTO = new generateInvoiceRequestDTO();
        invoiceRequestDTO.setGateId(gate2.getId());
        invoiceRequestDTO.setTicketId(ticketId);

        generateInvoiceResponseDTO invoiceResponseDTO = invoiceController.generateInvoice(invoiceRequestDTO);
        System.out.println(invoiceResponseDTO);
        }
    }

