package com.dido.exam.model.enums;

public enum StyleEnum {

    POP("POP"), ROCK("ROCK"), JAZZ("JAZZ");

    private String value;

    StyleEnum(String value) {
        this.value = value;
    }

    public static String valueOf(StyleEnum styleEnum) {

        for(StyleEnum valueEnum : StyleEnum.values()) {

            if (valueEnum.equals(styleEnum)) {
                return valueEnum.value;
            }
        }

        return null;
    }
}
