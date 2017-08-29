package io.coreflodev.testcurve.rxjava.dagger;

import dagger.Module;
import dagger.Provides;
import io.coreflodev.testcurve.rxjava.SumActivityViewModel;
import io.coreflodev.testcurve.rxjava.SumDataModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@Module
public class SumModule {

    @Provides
    @SumScope
    public SumActivityViewModel provideSumActivityViewModel(SumDataModel sumDataModel) {
        return new SumActivityViewModel(sumDataModel, Schedulers.io(), AndroidSchedulers.mainThread());
    }
}
