package dev.razafindratelo.patrimony.entity;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;
import static dev.razafindratelo.patrimony.entity.DeviseType.*;

class MaterialTest {

    private static final Devise DEVISE = new Devise(ARIARY, "Malagasy devise");
    private static final Money AMOUNT = new Money(20_000.0, DEVISE);

    @Test
    void test_futureProjection_in_one_year() {

        Material subject = new Material (
                "Clothes",
                AMOUNT,
                null,
                "Buy a new clothes",
                20,
                LocalDate.now()
        );

        double expected = 16_000;

        double actual = subject.futureProjection(LocalDate.now().plusYears(1)).getValue().getMontant();

        assertEquals(expected, actual);
    }


    @Test
    void test_futureProjection_in_five_years() {

        Material subject = new Material (
                "Clothes",
                AMOUNT,
                null,
                "Buy a new clothes",
                20,
                LocalDate.now()
        );

        double expected = 6_553.600000000001;

        double actual = subject.futureProjection(LocalDate.now().plusYears(5)).getValue().getMontant();

        assertEquals(expected, actual);
    }



}