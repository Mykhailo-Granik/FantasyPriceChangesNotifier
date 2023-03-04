package util;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
public class DoubleWithOnePlace implements Comparable<DoubleWithOnePlace> {

    private final int valueTimesTen;

    public DoubleWithOnePlace(double value) {
        this.valueTimesTen = (int) Math.round(value * 10);
    }

    @Override
    public int compareTo(DoubleWithOnePlace o) {
        return Integer.compare(valueTimesTen, o.valueTimesTen);
    }

    @Override
    public String toString() {
        return valueTimesTen / 10 + "." + Math.abs(valueTimesTen) % 10;
    }
}
