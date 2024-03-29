package Service;

import Repositories.GateRepository;
import models.Gate;

public class GateService {
    GateRepository gateRepository;

    public GateService(GateRepository gateRepository) {
        this.gateRepository = gateRepository;
    }

    public Gate getGateById(int gate_id){
        return gateRepository.getGateById(gate_id);
    }
}
