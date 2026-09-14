package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    @Test
    void add() {
        Calculator calc = new Calculator();
        int actual = calc.add(2, 3);
        int expected = 5;

        assertEquals(expected,actual);

    }
}