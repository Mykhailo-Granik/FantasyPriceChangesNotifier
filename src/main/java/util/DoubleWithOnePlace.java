package util;

import lombok.EqualsAndHashCode;

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
}
