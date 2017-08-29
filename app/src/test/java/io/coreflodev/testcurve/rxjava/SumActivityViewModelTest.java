package io.coreflodev.testcurve.rxjava;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.concurrent.TimeUnit;

import io.reactivex.schedulers.Schedulers;
import io.reactivex.schedulers.TestScheduler;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SumActivityViewModelTest {

    @Mock
    SumDataModel modelMock;

    private SumActivityViewModel viewModel;
    private TestScheduler animationTest = new TestScheduler();

    @Before
    public void setup() {
        viewModel = new SumActivityViewModel(modelMock, animationTest, Schedulers.trampoline());
    }

    @Test
    public void testSetNumber() {
        Long expected = 45L;
        viewModel.setNumber(SumDataModel.Number.SECOND, expected);
        verify(modelMock).setNumber(SumDataModel.Number.SECOND, expected);
    }

    @Test
    public void testSum() {
        when(modelMock.getNumber(SumDataModel.Number.FIRST)).thenReturn(5L);
        when(modelMock.getNumber(SumDataModel.Number.SECOND)).thenReturn(5L);
        when(modelMock.getNumber(SumDataModel.Number.THIRD)).thenReturn(5L);
        when(modelMock.getNumber(SumDataModel.Number.FOURTH)).thenReturn(5L);
        when(modelMock.getNumber(SumDataModel.Number.FIFTH)).thenReturn(5L);
        when(modelMock.getNumber(SumDataModel.Number.SIXTH)).thenReturn(5L);

        viewModel = new SumActivityViewModel(modelMock, animationTest, Schedulers.trampoline());

        viewModel.getSum()
                .test()
                .assertValueCount(1)
                .assertValues(30L);
    }

    @Test
    public void testToggleAnimation() {
        viewModel.toggleAnimation();

        verify(modelMock).animationToggle();
        verify(modelMock, times(2)).getAnimationState();
    }

    @Test
    public void testGetAnimationPulseEnabled() {
        when(modelMock.getAnimationState()).thenReturn(true);
        viewModel.toggleAnimation();
        animationTest.advanceTimeBy(2200, TimeUnit.MILLISECONDS);

        viewModel.getAnimationPulse()
                .test()
                .assertValueCount(0);
    }

    @Test
    public void testGetAnimationPulseDisabled() {
        viewModel.getAnimationPulse()
                .test()
                .assertValueCount(0);
    }
}
