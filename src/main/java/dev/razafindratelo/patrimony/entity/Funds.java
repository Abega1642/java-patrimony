package dev.razafindratelo.patrimony.entity;

import lombok.Getter;
import java.time.LocalDate;


@Getter
public abstract sealed class Funds extends Possession permits Account, Cash {
    private final LocalDate creationDate;

    public Funds(String name, Money value, LocalDate atTheDate, String description, LocalDate creationDate) {
        super(name, value, atTheDate, description);
        this.creationDate = creationDate;
    }
}
