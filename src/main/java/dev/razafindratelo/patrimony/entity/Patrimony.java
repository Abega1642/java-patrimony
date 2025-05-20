package dev.razafindratelo.patrimony.entity;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
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
        projectedPossessions.forEach(p -> p.futureProjection(futureDate));

        setAtTheDate(futureDate);
        setPossessions(projectedPossessions);
        return this;
    }

    public double getFutureValueOfPatrimony(LocalDate futureDate) {
        Money zero = Money.zeroValueOf(Devise.ariary());

        var futureProjection = futureProjection(futureDate);

        var totalValue = futureProjection.getPossessions().stream()
                .map(Possession::getValue)
                .reduce(zero, Money::add);

        return totalValue.getMontant();

    }

}
