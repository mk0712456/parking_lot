package models;

public enum VechicleType {
    BIKE,
    CAR,
    EV_CAR,
    TRUCK;

    public static VechicleType get_type_from_string(String vechicle_type){
        for (VechicleType value : VechicleType.values()) {
            if(vechicle_type.equalsIgnoreCase(value.toString())){
                return value;
            }
        }
        throw new IllegalArgumentException("Unsupported vehicle type");
    }
}
