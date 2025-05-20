package dev.razafindratelo.patrimony.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
public abstract sealed class Possession permits Funds, LifeSpending, Material {
    private final String name;
    private final Money value;
    private final LocalDate atTheDate;
    private final String description;

    public abstract Possession futureProjection(LocalDate futureValue);

    @Override
    public String toString() {
        return "Possession {" +
                "\n \t name= " + name +
                "\n \t value= " + value +
                "\n \t atTheDate= " + atTheDate +
                "\n \t description= " + description +
                "}";
    }
}
