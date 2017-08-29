package io.coreflodev.testcurve;

import android.app.Application;

import io.coreflodev.testcurve.common.dagger.AppComponent;
import io.coreflodev.testcurve.common.dagger.DaggerAppComponent;

public class TestCurveApplication extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.create();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
