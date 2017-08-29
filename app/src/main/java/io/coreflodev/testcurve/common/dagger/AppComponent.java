package io.coreflodev.testcurve.common.dagger;

import dagger.Component;
import io.coreflodev.testcurve.rxjava.SumDataModel;

@Component(modules = AppModule.class)
@AppScope
public interface AppComponent {

    SumDataModel sumDataModel();
}
