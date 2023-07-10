package co.bitengine.salvage.models;

import java.util.Random;

public class SalvageResultRange {
    private int min, max;
    public SalvageResultRange(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }
}
