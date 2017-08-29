package io.coreflodev.testcurve.common.dagger;

import dagger.Module;
import dagger.Provides;
import io.coreflodev.testcurve.rxjava.SumDataModel;

@Module
public class AppModule {

    @Provides
    @AppScope
    public SumDataModel provideSumDataModel() {
        return new SumDataModel();
    }
}
