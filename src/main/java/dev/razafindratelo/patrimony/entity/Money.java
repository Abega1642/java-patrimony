package dev.razafindratelo.patrimony.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class Money {
    private final Double montant;
    private final Devise devise;

    private Money(Devise devise) {
        this.montant = 0.0;
        this.devise = devise;
    }

    public static Money zeroValueOf(Devise devise) {
        return new Money(devise);
    }

    public Money add(Money money) {
        Money finalAmount = convertMoney(money, this.devise);
        return new Money(
                finalAmount.getMontant() + this.montant,
                this.devise
        );
    }

    public static Money convertMoney(Money subjectMoney, Devise targetDevise) {
        if (subjectMoney.getDevise() == null) throw new IllegalArgumentException("Subject money can't be null");
        if (targetDevise == null) throw new IllegalArgumentException("Target devise can't be null");
        if (targetDevise.equals(subjectMoney.getDevise())) return subjectMoney;

        Money newValue = subjectMoney.multiply(subjectMoney.getDevise().getName().getAriaryValue())
                .divide(targetDevise.getName().getAriaryValue());


        return new Money(newValue.getMontant(), targetDevise);
    }

    public Money multiply(double multiplier) {
        return new Money(
                this.montant * multiplier,
                this.devise
        );
    }

    public Money divide(double multiplier) {
        return this.multiply(1 / multiplier);
    }

    public Money subtract(Money money) {
        Money finalAmount = convertMoney(money, this.devise);

        return new Money(
                this.montant - finalAmount.getMontant(),
                this.devise
        );
    }

    @Override
    public String toString() {
        return "Money {"+
               "\n \t montant= " + montant +
               "\n \t devise= " + devise +
                "}";
    }
}
