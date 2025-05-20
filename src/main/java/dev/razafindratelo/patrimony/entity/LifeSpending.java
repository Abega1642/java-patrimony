package dev.razafindratelo.patrimony.entity;

import lombok.Getter;
import lombok.ToString;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@ToString(callSuper = true)
public final class LifeSpending extends Possession {
    private final Account financier;
    private final int operationDay;
    private final LocalDate startOfThePunctuation;


    public LifeSpending(
            String name,
            Money value,
            LocalDate atTheDate,
            String description,
            Account financier,
            int operationDay,
            LocalDate startOfThePunctuation
    ) {
        super(name, value, atTheDate, description);
        this.financier = financier;
        this.operationDay = operationDay;
        this.startOfThePunctuation = startOfThePunctuation;

        financier.financeLifeSpending(this);
    }

    @Override
    public Possession futureProjection(LocalDate futureValue) {

        List<LocalDate> occurence = new ArrayList<>();

        var date = LocalDate.of(startOfThePunctuation.getYear(), startOfThePunctuation.getMonth(), operationDay);

        while (date.isBefore(futureValue)) {
            occurence.add(date);
            date = date.plusMonths(1);
        }

        var accountAfterWithdraw = financier.withdraw(getValue(), occurence.getFirst());

        for (int i = 1; i < occurence.size(); i++) {
            accountAfterWithdraw = accountAfterWithdraw.withdraw(getValue(), occurence.get(i));
        }
        
        return new LifeSpending(
                getName(),
                getValue().multiply(occurence.size()),
                futureValue,
                getDescription(),
                accountAfterWithdraw,
                getOperationDay(),
                getStartOfThePunctuation()
        );

    }
}
