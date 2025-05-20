package dev.razafindratelo.patrimony.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Set;
import static dev.razafindratelo.patrimony.entity.DeviseType.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AccountTest {
    private static final Devise AR = new Devise(ARIARY, "Malagasy devise");
    private static final Devise EU = new Devise(EURO, "European devise");
    private static final Money AMOUNT_AR = new Money(20_000.0, AR);


    @Test
    void test_deposit_money() {
        var subject = new Account (
                "Account",
                AMOUNT_AR,
                null,
                "A current account",
                AccountType.CURRENT_ACCOUNT,
                0,
                LocalDate.now(),
                Set.of()
        );

        var addedMoney = new Money(4.0, EU);

        var expectedValue = new Money(40_300.0, AR);

        var expected =  new Account (
                "Account",
                expectedValue,
                LocalDate.now(),
                "A current account",
                AccountType.CURRENT_ACCOUNT,
                0,
                LocalDate.now(),
                Set.of()
        );

        Account actual = subject.deposit(addedMoney, LocalDate.now());

        assertEquals(expected.getValue().getMontant(), actual.getValue().getMontant());
        assertEquals(expected.getValue().getDevise(), actual.getValue().getDevise());
        assertEquals(expected.getAtTheDate(), actual.getAtTheDate());
    }

    @Test
    void test_future_projection_with_attached_lifeSpending() {
        var usedDate = LocalDate.of(2025, 1, 1);

        var currentAccount = new Account(
                "Life spending account",
                new Money (200_000.0, AR),
                null,
                "Current account of Mr Ilo",
                AccountType.CURRENT_ACCOUNT,
                1,
                usedDate,
                Set.of()
        );

        var lifeSpending = new LifeSpending(
                "LIfe spending",
                new Money(100_000.0, AR),
                null,
                "Life spending of Mr Ilo",
                currentAccount,
                3,
                usedDate
        );

        double expected = 0d;
        double actual = currentAccount.futureProjection(LocalDate.of(2025, 2, 4)).getValue().getMontant();

        assertEquals(expected, actual);
    }

}