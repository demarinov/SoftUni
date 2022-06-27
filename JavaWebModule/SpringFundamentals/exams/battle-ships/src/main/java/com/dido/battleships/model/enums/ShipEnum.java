package com.dido.battleships.model.enums;

public enum ShipEnum {
    BATTLE("BATTLE"), CARGO("CARGO"), PATROL("PATROL");


    private String value;

    ShipEnum(String value) {
        this.value = value;
    }

    public static String valueOf(ShipEnum shipEnum) {

        for(ShipEnum valueEnum : ShipEnum.values()) {

            if (valueEnum.equals(shipEnum)) {
                return valueEnum.value;
            }
        }

        return null;
    }
}
