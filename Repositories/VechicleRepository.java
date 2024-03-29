package Repositories;

import models.Vechicle;
import models.VechicleType;

import java.util.HashMap;
import java.util.Map;

public class VechicleRepository {
    Map<Integer, Vechicle> map;

    public VechicleRepository(Map<Integer, Vechicle> map) {
        this.map = map;
    }
    public VechicleRepository(){
        this.map = new HashMap<>();
    }
    public static int id = 1;
    public Vechicle create_if_not_present(String vechicle_no, VechicleType vechicle_type){
        for(Map.Entry<Integer, Vechicle> entry : map.entrySet()){
            Vechicle vechicle = entry.getValue();
            if(vechicle.getVechicle_no().equals(vechicle_no)){
                return vechicle;
            }
        }
        Vechicle vechicle = new Vechicle();
        vechicle.setVechicle_no(vechicle_no);
        vechicle.setVechicleType(vechicle_type);
        map.put(id++, vechicle);
        return vechicle;
    }
}
