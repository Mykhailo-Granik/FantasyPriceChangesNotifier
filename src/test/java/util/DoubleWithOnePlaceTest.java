package util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DoubleWithOnePlaceTest {

    @Test
    public void shouldReturnValuesAreEqualWhenDoublesAreEqual() {
        DoubleWithOnePlace a = new DoubleWithOnePlace(95.0);
        DoubleWithOnePlace b = new DoubleWithOnePlace(95.0);
        assertEquals(0, a.compareTo(b));
    }

    @Test
    public void shouldCorrectlyDetermineValueIsLessThanOtherValue() {
        DoubleWithOnePlace a = new DoubleWithOnePlace(95.0);
        DoubleWithOnePlace b = new DoubleWithOnePlace(95.1);
        assertTrue(a.compareTo(b) < 0);
    }

    @Test
    public void shouldCorrectlyDetermineValueIsGreaterThanOtherValue() {
        DoubleWithOnePlace a = new DoubleWithOnePlace(95.1);
        DoubleWithOnePlace b = new DoubleWithOnePlace(95.0);
        assertTrue(a.compareTo(b) > 0);
    }

    @Test
    public void shouldCorrectlyDetermineNegativeValueIsLessThanOtherNegativeValue() {
        DoubleWithOnePlace a = new DoubleWithOnePlace(-95.1);
        DoubleWithOnePlace b = new DoubleWithOnePlace(-95.0);
        assertTrue(a.compareTo(b) < 0);
    }

    @Test
    public void shouldCorrectlyDetermineNegativeValueIsGreaterThanOtherNegativeValue() {
        DoubleWithOnePlace a = new DoubleWithOnePlace(-95.0);
        DoubleWithOnePlace b = new DoubleWithOnePlace(-95.1);
        assertTrue(a.compareTo(b) > 0);
    }

    @Test
    public void shouldCorrectlyDetermineNegativeValueIsLessThanPositiveValue() {
        DoubleWithOnePlace a = new DoubleWithOnePlace(-95.0);
        DoubleWithOnePlace b = new DoubleWithOnePlace(95.0);
        assertTrue(a.compareTo(b) < 0);
    }

    @Test
    public void shouldCorrectlyDeterminePositiveValueIsGreaterThanNegativeValue() {
        DoubleWithOnePlace a = new DoubleWithOnePlace(95.0);
        DoubleWithOnePlace b = new DoubleWithOnePlace(-95.0);
        assertTrue(a.compareTo(b) > 0);
    }

    @Test
    public void shouldCorrectlyConvertToStringPositiveNumber() {
        DoubleWithOnePlace a = new DoubleWithOnePlace(95.3);
        assertEquals("95.3", a.toString());
    }

    @Test
    public void shouldCorrectlyConvertToStringNegativeNumber() {
        DoubleWithOnePlace a = new DoubleWithOnePlace(-97.4);
        assertEquals("-97.4", a.toString());
    }

    @Test
    public void shouldCorrectlyConvertToStringZero() {
        DoubleWithOnePlace a = new DoubleWithOnePlace(0.0);
        assertEquals("0.0", a.toString());
    }


}