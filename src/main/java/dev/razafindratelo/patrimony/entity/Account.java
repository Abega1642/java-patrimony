package dev.razafindratelo.patrimony.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString(callSuper = true)
public final class Account extends Funds {
    private final AccountType accountType;
    private final double interestRate;
    private Set<LifeSpending> financedLifeSpending;

    public Account(String name, Money value, LocalDate atTheDate, String description, LocalDate creationDate, AccountType accountType, double interestRate, Set<LifeSpending> financedLifeSpending) {
        super(name, value, atTheDate, description, creationDate);
        this.accountType = accountType;
        this.interestRate = interestRate;
        this.financedLifeSpending = financedLifeSpending;
    }

    public Account deposit(Money money, LocalDate atTheDate) {
        if (money.getMontant() < 0 ) throw new IllegalArgumentException("Money must be greater than zero");

        Money finalAmount = this.getValue().add(money);

        return new Account(
                getName(),
                finalAmount,
                atTheDate,
                getDescription(),
                getCreationDate(),
                this.accountType,
                this.interestRate,
                this.financedLifeSpending
        );
    }

    public Account withdraw(Money money, LocalDate atTheDate) {
        Money finalAmount = this.getValue().subtract(money);

        return new Account(
                getName(),
                finalAmount,
                atTheDate,
                getDescription(),
                getCreationDate(),
                this.accountType,
                this.interestRate,
                this.financedLifeSpending
        );
    }

    public void financeLifeSpending(LifeSpending lifeSpending) {
        Set<LifeSpending> finalFinancedLifeSpending = new HashSet<>(this.financedLifeSpending) ;
        finalFinancedLifeSpending.add(lifeSpending);
        setFinancedLifeSpending(finalFinancedLifeSpending);
    }

    @Override
    public Possession futureProjection(LocalDate futureValue) {
        var zero = Money.zeroValueOf(getValue().getDevise());

        if (futureValue.isBefore(getCreationDate()))
            return new Material(getName(), zero, futureValue, getDescription(), getInterestRate(), getCreationDate());

        int differenceBetweenYear = futureValue.getYear() - getCreationDate().getYear();

        var lifeSpendingTotalValue = financedLifeSpending.stream().map(lf -> lf.futureProjection(futureValue).getValue())
                .reduce(zero, Money::add);

        if (accountType.equals(AccountType.SAVINGS_ACCOUNT)) {
            double generalInterest =  getValue().getMontant() * (1 - (interestRate/100)) * differenceBetweenYear;
            var newAmount = new Money(getValue().getMontant() + generalInterest, getValue().getDevise());



            var finalTotalAmount = newAmount.subtract(lifeSpendingTotalValue);

            return new Account(
                    getName(),
                    finalTotalAmount,
                    futureValue,
                    getDescription(),
                    getCreationDate(),
                    accountType,
                    interestRate,
                    this.financedLifeSpending
            );
        }

        return new Account(
                getName(),
                getValue().subtract(lifeSpendingTotalValue),
                futureValue,
                getDescription(),
                getCreationDate(),
                accountType,
                interestRate,
                this.financedLifeSpending
        );
    }
}
