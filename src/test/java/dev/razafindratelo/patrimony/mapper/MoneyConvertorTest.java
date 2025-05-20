package dev.razafindratelo.patrimony.mapper;

import dev.razafindratelo.patrimony.entity.Devise;
import dev.razafindratelo.patrimony.entity.Money;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static dev.razafindratelo.patrimony.entity.DeviseType.*;

class MoneyConvertorTest {

    private static final Devise AR = new Devise(ARIARY, "Malagasy devise");
    private static final Devise E = new Devise(EURO, "European devise");
    private static final Devise US_DOLLAR = new Devise(USD, "USA devise");

    @Test
    void test_ariary_to_euro() {
        Money subject = new Money(10_000.0, AR);

        Money expected = new Money(1.9704433497536946, E);

        Money actual = MoneyConvertor.convertMoney(subject, E);

        assertEquals(expected.getDevise(), actual.getDevise());
        assertEquals(expected.getMontant(), actual.getMontant());
    }

    @Test
    void test_euro_to_ariary() {
        Money subject = new Money(3.0, E);

        Money expected = new Money(15_225.0, AR);

        Money actual = MoneyConvertor.convertMoney(subject, AR);

        assertEquals(expected.getDevise(), actual.getDevise());
        assertEquals(expected.getMontant(), actual.getMontant());
    }

    @Test
    void test_us_dollar_to_euro() {
        Money subject = new Money(1_000.0, US_DOLLAR);

        Money expected = new Money(892.8571428571428, E);

        Money actual = MoneyConvertor.convertMoney(subject, E);

        assertEquals(expected.getDevise(), actual.getDevise());
        assertEquals(expected.getMontant(), actual.getMontant());
    }

    @Test
    void test_euro_to_euro() {
        Money subject = new Money(1_000.0, E);

        Money expected = new Money(1_120.0, US_DOLLAR);

        Money actual = MoneyConvertor.convertMoney(subject, US_DOLLAR);

        assertEquals(expected.getDevise(), actual.getDevise());
        assertEquals(expected.getMontant(), actual.getMontant());
    }

    @Test
    void test_ariary_to_ariary() {
        Money subject = new Money(10_000.0, AR);

        Money expected = new Money(10_000.0, AR);

        Money actual = MoneyConvertor.convertMoney(subject, AR);

        assertEquals(expected.getDevise(), actual.getDevise());
        assertEquals(expected.getMontant(), actual.getMontant());
    }

}