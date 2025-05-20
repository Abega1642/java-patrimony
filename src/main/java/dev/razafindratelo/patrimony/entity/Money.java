package dev.razafindratelo.patrimony.entity;

import dev.razafindratelo.patrimony.mapper.MoneyConvertor;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

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
        Money finalAmount = MoneyConvertor.convertMoney(money, this.devise);
        return new Money(
                finalAmount.getMontant() + this.montant,
                this.devise
        );
    }

    public Money multiply(int multiplier) {
        return new Money(
                this.montant * multiplier,
                this.devise
        );
    }

    public Money subtract(Money money) {
        Money finalAmount = MoneyConvertor.convertMoney(money, this.devise);

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
