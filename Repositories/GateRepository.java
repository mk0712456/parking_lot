package Repositories;

import models.Gate;

import java.util.HashMap;
import java.util.Map;

public class GateRepository {
    Map<Integer, Gate> map;

    public GateRepository(Map<Integer, Gate> map) {
        this.map = map;
    }

   public GateRepository(){
        this.map = new HashMap<>();
   }
   public Gate getGateById(int gate_id){
        return map.get(gate_id);
   }
}
