package Repositories;

import models.Slab;
import models.VechicleType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SlabRepository {
    Map<Integer, Slab> map;
    public SlabRepository(Map<Integer, Slab> map){
        this.map = map;
    }
    public SlabRepository(){
        this.map = new HashMap<>();
    }
    // all vechicles slabs are in one place as hashmap form
    // going through slab by slab in hashmap and adding it to slabs arrayList if matches and returning the list
    public List<Slab> getSlabByVechicleType(VechicleType vechicleType){
        List<Slab> slabs = new ArrayList<>();
        for(Map.Entry<Integer, Slab> entry : this.map.entrySet()){
            Slab slab = entry.getValue();
            if(slab.getVechicleType().equals(vechicleType)){
                slabs.add(slab);
            }
        }
        slabs.sort((o1, o2) -> o1.getStartHour() - o2.getStartHour());
        return slabs;
    }

}
