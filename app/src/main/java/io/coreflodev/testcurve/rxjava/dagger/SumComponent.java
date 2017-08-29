package io.coreflodev.testcurve.rxjava.dagger;

import dagger.Component;
import io.coreflodev.testcurve.common.dagger.AppComponent;
import io.coreflodev.testcurve.rxjava.SumActivity;

@SumScope
@Component(modules = SumModule.class, dependencies = AppComponent.class)
public interface SumComponent {

    void inject(SumActivity sumActivity);
}
