package dev.razafindratelo.patrimony.mapper;

import dev.razafindratelo.patrimony.entity.Devise;
import dev.razafindratelo.patrimony.entity.Money;

import static dev.razafindratelo.patrimony.entity.DeviseType.*;
import static dev.razafindratelo.patrimony.mapper.DeviseValue.*;

public class MoneyConvertor {

    public static Money convertMoney(Money subjectMoney, Devise targetDevise) {
        if (subjectMoney.getDevise() == null) throw new IllegalArgumentException("Subject money can't be null");
        if (targetDevise == null) throw new IllegalArgumentException("Target devise can't be null");

        if (subjectMoney.getDevise().getName().equals(targetDevise.getName())) return subjectMoney;

        if (targetDevise.getName().equals(ARIARY) && subjectMoney.getDevise().getName().equals(EURO)) {
            double value = subjectMoney.getMontant() * EURO_TO_ARIARY.getValue();

            return new Money(value, targetDevise);
        } else if (targetDevise.getName().equals(EURO) && subjectMoney.getDevise().getName().equals(ARIARY)) {
            double value = subjectMoney.getMontant() * (1 / EURO_TO_ARIARY.getValue());

            return new Money(value, targetDevise);
        }

        if (targetDevise.getName().equals(ARIARY) && subjectMoney.getDevise().getName().equals(USD)) {
            double value = subjectMoney.getMontant() * USD_TO_ARIARY.getValue();

            return new Money(value, targetDevise);
        } else if (targetDevise.getName().equals(USD) && subjectMoney.getDevise().getName().equals(ARIARY)) {
            double value = subjectMoney.getMontant() * (1 / USD_TO_ARIARY.getValue());

            return new Money(value, targetDevise);
        }

        if (targetDevise.getName().equals(EURO) && subjectMoney.getDevise().getName().equals(USD)) {
            double value = subjectMoney.getMontant() * (1 / EURO_TO_USD.getValue());

            return new Money(value, targetDevise);
        } else if (targetDevise.getName().equals(USD) && subjectMoney.getDevise().getName().equals(EURO)) {
            double value = subjectMoney.getMontant() * EURO_TO_USD.getValue();

            return new Money(value, targetDevise);
        }

        throw new RuntimeException("Could not convert subject money to target devise");
    }
}
