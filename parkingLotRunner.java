import DTOs.generateTicketRequestDTO;
import DTOs.generateTicketResponseDTO;
import Repositories.GateRepository;
import Repositories.ParkingLotRepository;
import Repositories.TicketRepository;
import Repositories.VechicleRepository;
import Service.GateService;
//import Service.GateServiceImpl;
import Service.TicketService;
import Service.TicketServiceImpl;
import Strategy.SpotAssignment.AssignSpotStrategy;
import Strategy.SpotAssignment.NearestSpotAssignmentStrategy;
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
        gate1.setGateType(GateType.EXIT);
        gate1.setId(2);
        gate1.setName("Exit-1");
        gate1.setOperator(new Operator());

        Gate gate3 = new Gate();
        gate3.setGateType(GateType.ENTRY);
        gate3.setId(3);
        gate3.setName("Entry-2");
        gate3.setOperator(new Operator());

        Map<Integer, Gate> gateMap = new HashMap<Integer, Gate>(){{
            put(1, gate1);
            put(2, gate2);
            put(2, gate3);
        }};
        List<Spot> spots  = Arrays.asList(new Spot("S1", SpotStatus.UNOCCUPIED, VechicleType.CAR), new Spot("S2", SpotStatus.UNOCCUPIED, VechicleType.BIKE), new Spot("S3", SpotStatus.UNOCCUPIED, VechicleType.BIKE) );


        Section section = new Section();
        section.setId(1);
        section.setName("section A");
        section.setSpots(spots);

        Floor floor = new Floor();
        List<Section> s = new ArrayList<>();
        floor.setId(1);
        floor.setFloor_no(0);
        s.add(section);
        floor.setSections(s);
        floor.setFloorStatus(FloorStatus.OPERATIONAL);

        ParkingLot parkingLot1 = new ParkingLot();
        parkingLot1.setId(1);
        List<Floor> f = new ArrayList<>();
        f.add(floor);
        parkingLot1.setFloors(f);
        List<Gate> g = Arrays.asList(gate1, gate2);
        parkingLot1.setGate(g);
        Map<Integer, ParkingLot> parkingLotMap = new HashMap<Integer, ParkingLot>(){{
            put(1, parkingLot1);
        }};

        GateRepository gateRepository = new GateRepository(gateMap);
        ParkingLotRepository parkingLotRepository = new ParkingLotRepository(parkingLotMap);
        TicketRepository ticketRepository = new TicketRepository();
        VechicleRepository vechicleRepository = new VechicleRepository();

        GateService gateService = new GateService(gateRepository);
        AssignSpotStrategy assignSpotStrategy = new NearestSpotAssignmentStrategy();
        TicketService ticketService = new TicketServiceImpl(gateService, vechicleRepository, assignSpotStrategy, parkingLotRepository, ticketRepository);

        TicketController ticketController = new TicketController(ticketService);

        generateTicketRequestDTO requestDTO = new generateTicketRequestDTO();
        requestDTO.setGate_id(1);
        requestDTO.setVechicle_no("TN 94 7984");
        requestDTO.setVechicle_type(VechicleType.BIKE.toString());

        generateTicketResponseDTO responseDTO = new generateTicketResponseDTO();
        System.out.println(responseDTO);
        }
    }

