package dev.razafindratelo.patrimony.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertEquals;


class PatrimonyTest {

    @Test
    void test_patrimony_value_after_one_year() {
        var ilo = new Agent("Ilo");
        var usedDate = LocalDate.of(2024, 4, 13);


        var currentAccount = new Account(
                "Life spending account",
                new Money (600_000.0, Devise.ariary()),
                null,
                "Current account of Mr Ilo",
                usedDate,
                AccountType.CURRENT_ACCOUNT,
                1,
                Set.of()
        );

        var cash = new Cash(
                "Cash money",
                new Money(400_000d, Devise.ariary()),
                null,
                "Cash money of Mr Ilo",
                usedDate
        );

        var lifeSpending = new LifeSpending(
                "LIfe spending",
                new Money(500_000.0, Devise.ariary()),
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
                usedDate,
                AccountType.SAVINGS_ACCOUNT,
                5,
                Set.of()
        );

        var computer = new Material(
                "MacBook Pro",
                new Money (2_000_000.0, Devise.ariary()),
                null,
                "The computer of Mr Ilo",
                10,
                LocalDate.of(2021, 10, 26)
        );

        var clothes = new Material(
                "Clothes",
                new Money (1_000_000d, Devise.ariary()),
                null,
                "Set of clothes of Mr Ilo",
                20,
                LocalDate.of(2024, 1, 1)
        );

        var subject = new Patrimony(
            ilo,
            null,
                Set.of(savingsAccount, lifeSpending, cash, currentAccount, computer, clothes)
        );

        var expected = 3_400_980d;

        var actual = subject.getFutureValueOfPatrimony(LocalDate.of(2026, 6, 24));

        assertEquals(expected, actual);

    }
}