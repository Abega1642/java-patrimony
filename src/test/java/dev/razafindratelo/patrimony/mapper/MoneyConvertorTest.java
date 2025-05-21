package dev.razafindratelo.patrimony.mapper;

import dev.razafindratelo.patrimony.entity.Devise;
import dev.razafindratelo.patrimony.entity.Money;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static dev.razafindratelo.patrimony.entity.DeviseType.*;

class MoneyConvertorTest {

    @Test
    void test_ariary_to_euro() {
        Money subject = new Money(10_000.0, Devise.ariary());

        Money expected = new Money(1.9704433497536946, Devise.euro());

        Money actual = Money.convertMoney(subject, Devise.euro());

        assertEquals(expected.getDevise(), actual.getDevise());
        assertEquals(expected.getMontant(), actual.getMontant());
    }

    @Test
    void test_euro_to_ariary() {
        Money subject = new Money(3.0, Devise.euro());

        Money expected = new Money(15_225.0, Devise.ariary());

        Money actual = Money.convertMoney(subject, Devise.ariary());

        assertEquals(expected.getDevise(), actual.getDevise());
        assertEquals(expected.getMontant(), actual.getMontant());
    }

    @Test
    void test_us_dollar_to_euro() {
        Money subject = new Money(1_000.0, Devise.usd());

        Money expected = new Money(886.6857142857143, Devise.euro());

        Money actual = Money.convertMoney(subject, Devise.euro());

        assertEquals(expected.getDevise(), actual.getDevise());
        assertEquals(expected.getMontant(), actual.getMontant());
    }

    @Test
    void test_euro_to_dollar() {
        Money subject = new Money(1_000.0, Devise.euro());

        Money expected = new Money(1127.7953212605528, Devise.usd());

        Money actual = Money.convertMoney(subject, Devise.usd());

        assertEquals(expected.getDevise(), actual.getDevise());
        assertEquals(expected.getMontant(), actual.getMontant());
    }

    @Test
    void test_ariary_to_ariary() {
        Money subject = new Money(10_000.0, Devise.ariary());

        Money expected = new Money(10_000.0, Devise.ariary());

        Money actual = Money.convertMoney(subject, Devise.ariary());

        assertEquals(expected.getDevise(), actual.getDevise());
        assertEquals(expected.getMontant(), actual.getMontant());
    }

}