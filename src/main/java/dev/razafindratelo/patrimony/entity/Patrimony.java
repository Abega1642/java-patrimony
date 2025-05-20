package dev.razafindratelo.patrimony.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@ToString
public class Patrimony {
    private Agent owner;
    private LocalDate atTheDate;
    private Set<Possession> possessions;

    public Patrimony(Agent owner, LocalDate atTheDate, Set<Possession> possessions) {
        this.owner = owner;
        this.atTheDate = atTheDate;
        this.possessions = possessions;
    }

    public Patrimony futureProjection(LocalDate futureDate) {
        Set<Possession> projectedPossessions = new HashSet<>(this.possessions);
        projectedPossessions = projectedPossessions.stream().map(p -> p.futureProjection(futureDate))
                .collect(Collectors.toSet());

        return new Patrimony(
                getOwner(),
                futureDate,
                projectedPossessions
        );
    }

    public double getFutureValueOfPatrimony(LocalDate futureDate) {
        Money zero = Money.zeroValueOf(Devise.ariary());

        var futureProjection = futureProjection(futureDate);

        var totalValue = futureProjection.getPossessions().stream()
                .filter(p -> !(p instanceof LifeSpending))
                .map(Possession::getValue)
                .reduce(zero, Money::add);

        return totalValue.getMontant();
    }

}
