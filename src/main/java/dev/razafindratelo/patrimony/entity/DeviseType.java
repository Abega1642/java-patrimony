package dev.razafindratelo.patrimony.entity;

import lombok.Getter;

@Getter
public enum DeviseType {
    EURO(5_075d), USD(4_499.93d), ARIARY(1d);

    private final double ariaryValue;

    DeviseType(double ariaryValue) {
        this.ariaryValue = ariaryValue;
    }
}
