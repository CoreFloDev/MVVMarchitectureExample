package io.coreflodev.testcurve.rxjava;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SumDataModelTest {

    private SumDataModel dataModel;

    @Before
    public void setup() {
        dataModel = new SumDataModel();
    }

    @Test
    public void testConstructor() {
        for (SumDataModel.Number number : SumDataModel.Number.values()){
            assertEquals(0L , dataModel.getNumber(number));
        }
        assertEquals(false, dataModel.getAnimationState());
    }

    @Test
    public void testSetNumbers() {
        long expected = 90L;
        dataModel.setNumber(SumDataModel.Number.FIRST, expected);

        assertEquals(expected, dataModel.getNumber(SumDataModel.Number.FIRST));
    }

    @Test
    public void testAnimationToggle() {
        dataModel.animationToggle();
        assertEquals(true, dataModel.getAnimationState());
        dataModel.animationToggle();
        assertEquals(false, dataModel.getAnimationState());
        dataModel.animationToggle();
        assertEquals(true, dataModel.getAnimationState());
    }
}
