package dev.razafindratelo.patrimony.entity;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.Set;
import static dev.razafindratelo.patrimony.entity.DeviseType.ARIARY;
import static org.junit.jupiter.api.Assertions.*;

class LifeSpendingTest {


    @Test
    void test_futureProjection() {
        final Devise AR = new Devise(ARIARY, "Malagasy devise");
        var usedDate = LocalDate.of(2025, 1, 1);

        var currentAccount = new Account(
                "Life spending account",
                new Money (200_000.0, AR),
                null,
                "Current account of Mr Ilo",
                usedDate,
                AccountType.CURRENT_ACCOUNT,
                1,
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

        double expected = 200_000d;
        double actual = lifeSpending.futureProjection(LocalDate.of(2025, 2, 4)).getValue().getMontant();

        assertEquals(expected, actual);
    }

}