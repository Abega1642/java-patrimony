package dev.razafindratelo.patrimony.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Set;


class PatrimonyTest {

    @Test
    void test_patrimony_value_after_one_year() {
        var ilo = new Agent("Ilo");
        var usedDate = LocalDate.of(2025, 1, 1);


        var currentAccount = new Account(
                "Life spending account",
                new Money (200_000.0, Devise.ariary()),
                null,
                "Current account of Mr Ilo",
                AccountType.CURRENT_ACCOUNT,
                    1,
                usedDate,
                Set.of()
        );

        var lifeSpending = new LifeSpending(
                "LIfe spending",
                new Money(100_000.0, Devise.ariary()),
                null,
                "Life spending of Mr Ilo",
                currentAccount,
                3,
                usedDate
        );

        var savingsAccount = new Account(
                "Savings account",
                new Money (200_000.0, Devise.ariary()),
                null,
                "Savings account of Mr Ilo",
                AccountType.SAVINGS_ACCOUNT,
                5,
                usedDate,
                Set.of()
        );

        var computer = new Material(
                "MacBook Pro",
                new Money (2_000_000.0, Devise.ariary()),
                null,
                "The computer of Mr Ilo",
                10,
                usedDate
        );

        var subject = new Patrimony(
            ilo,
            null,
                Set.of(lifeSpending, computer, savingsAccount)
        );

    }
}