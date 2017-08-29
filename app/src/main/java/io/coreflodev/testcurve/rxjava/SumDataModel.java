package io.coreflodev.testcurve.rxjava;

import java.util.HashMap;
import java.util.Map;

public class SumDataModel {

    private Map<Number, Long> numbers;
    private boolean animationState;

    public SumDataModel() {
        numbers = new HashMap<>();
        animationState = false;
        for (Number number : Number.values()) {
            numbers.put(number, 0L);
        }
    }

    public void setNumber(Number number, Long value) {
        numbers.put(number, value);
    }

    public void animationToggle() {
        animationState = !animationState;
    }

    public long getNumber(Number number) {
        return numbers.get(number);
    }

    public boolean getAnimationState() {
        return animationState;
    }

    public enum Number {
        FIRST,
        SECOND,
        THIRD,
        FOURTH,
        FIFTH,
        SIXTH
    }
}
