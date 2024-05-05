package Repositories;

import models.Bill;

import java.util.HashMap;
import java.util.Map;

public class InvoiceRepository {
    private Map<Integer, Bill> map;

    public InvoiceRepository(Map<Integer, Bill> map) {
        this.map = map;
    }
    public InvoiceRepository(){
        this.map = new HashMap<>();
    }
    public static int ID = 0;
    public Bill insertBill(Bill bill){
        bill.setId(ID);
        map.put(ID++, bill);
        return bill;
    }
}
