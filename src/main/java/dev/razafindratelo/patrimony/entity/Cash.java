package dev.razafindratelo.patrimony.entity;

import java.time.LocalDate;

public final class Cash extends Funds {

    public Cash(String name, Money value, LocalDate atTheDate, String description, LocalDate creationDate) {
        super(name, value, atTheDate, description, creationDate);
    }

    public Cash extractSomeAmount(Money amount, LocalDate atTheDate) {
        if (amount.getMontant() > getValue().getMontant())
            throw new IllegalArgumentException("Not enough money to extract that quantity of money from this cash");

        return new Cash(
                getName(),
                this.getValue().subtract(amount),
                atTheDate,
                getDescription(),
                getCreationDate()
        );
    }


    @Override
    public Possession futureProjection(LocalDate futureValue) {
        return this;
    }
}
