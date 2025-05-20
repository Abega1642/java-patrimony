package dev.razafindratelo.patrimony.entity;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public final class Material extends Possession {

    private final double deprecatedRatio;
    private final LocalDate acquisitionDate;


    public Material(String name, Money value, LocalDate atTheDate, String description, double deprecatedRatio, LocalDate acquisitionDate) {
        super(name, value, atTheDate,  description);
        this.acquisitionDate = acquisitionDate;
        this.deprecatedRatio = deprecatedRatio;
    }

    @Override
    public Possession futureProjection(LocalDate futureValue) {
        Money zero = Money.zeroValueOf(getValue().getDevise());

        if (futureValue.isBefore(acquisitionDate))
            return new Material(getName(), zero, futureValue, getDescription(), getDeprecatedRatio(), getAcquisitionDate());

        int differenceBetweenYear = futureValue.getYear() - acquisitionDate.getYear();

        double newValue =  getValue().getMontant() * Math.pow(1 - (deprecatedRatio/100), differenceBetweenYear);
        Money newAmount = new Money(newValue, getValue().getDevise());

        return new Material(getName(), newAmount, futureValue, getDescription(), getDeprecatedRatio(), getAcquisitionDate());
    }
}
