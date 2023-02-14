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


}