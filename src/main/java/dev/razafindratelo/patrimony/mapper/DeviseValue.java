package dev.razafindratelo.patrimony.mapper;

import lombok.Getter;

@Getter
public enum DeviseValue {
    // 19 Mai values

    EURO_TO_ARIARY(5_075.0),
    USD_TO_ARIARY(4_499.93),
    EURO_TO_USD(1.12);

    private final double value;

    DeviseValue(double value) {
        this.value = value;
    }
}
