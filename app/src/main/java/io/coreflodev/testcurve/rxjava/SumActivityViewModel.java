package io.coreflodev.testcurve.rxjava;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.ReplaySubject;
import io.reactivex.subjects.Subject;

public class SumActivityViewModel {

    private SumDataModel sumDataModel;
    private Map<SumDataModel.Number, Subject<Long>> numberSubjectMap;
    private Subject<Boolean> animateState;

    private Scheduler animationScheduler;
    private Scheduler androidScheduler;

    public SumActivityViewModel(SumDataModel sumDataModel, Scheduler animationScheduler, Scheduler androidScheduler) {
        this.sumDataModel = sumDataModel;
        this.numberSubjectMap = new HashMap<>();
        for (SumDataModel.Number number : SumDataModel.Number.values()) {
            numberSubjectMap.put(number, ReplaySubject.create());
            numberSubjectMap.get(number).onNext(sumDataModel.getNumber(number));
        }
        this.animateState = ReplaySubject.create();
        this.animateState.onNext(sumDataModel.getAnimationState());

        this.animationScheduler = animationScheduler;
        this.androidScheduler = androidScheduler;
    }

    public Observable<Long> getSum() {
        return Observable.combineLatest(
                numberSubjectMap.get(SumDataModel.Number.FIRST),
                numberSubjectMap.get(SumDataModel.Number.SECOND),
                numberSubjectMap.get(SumDataModel.Number.THIRD),
                numberSubjectMap.get(SumDataModel.Number.FOURTH),
                numberSubjectMap.get(SumDataModel.Number.FIFTH),
                numberSubjectMap.get(SumDataModel.Number.SIXTH),
                (first, second, third, fourth, fifth, sixth) -> first + second + third + fourth + fifth + sixth
        );
    }

    public Observable<Object> getAnimationPulse() {
        return animateState
                .switchMap(shouldAnimate -> {
                    if (shouldAnimate) {
                        return Observable.interval(500, TimeUnit.MILLISECONDS, animationScheduler);
                    } else {
                        return Observable.empty();
                    }
                })
                .observeOn(androidScheduler)
                .map(__ -> new Object());
    }

    public void setNumber(SumDataModel.Number number, Long value) {
        sumDataModel.setNumber(number, value);
        numberSubjectMap.get(number).onNext(value);
    }

    public void toggleAnimation() {
        sumDataModel.animationToggle();
        animateState.onNext(sumDataModel.getAnimationState());
    }
}
