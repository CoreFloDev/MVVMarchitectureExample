package io.coreflodev.testcurve.rxjava;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import javax.inject.Inject;

import io.coreflodev.testcurve.R;
import io.coreflodev.testcurve.TestCurveApplication;
import io.coreflodev.testcurve.rxjava.dagger.DaggerSumComponent;
import io.reactivex.disposables.CompositeDisposable;

public class SumActivity extends AppCompatActivity {

    @Inject
    SumActivityViewModel sumActivityViewModel;
    private CompositeDisposable compositeDisposable;
    private TextView tvSum;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sum);

        DaggerSumComponent.builder()
                .appComponent(((TestCurveApplication) getApplication()).getAppComponent())
                .build()
                .inject(this);

        compositeDisposable = new CompositeDisposable();
        setupViews();
    }

    private void setupViews() {
        tvSum = findViewById(R.id.tv_sum_result);
        setTextChangedListener(R.id.et_number_fifth, SumDataModel.Number.FIRST);
        setTextChangedListener(R.id.et_number_third, SumDataModel.Number.SECOND);
        setTextChangedListener(R.id.et_number_first, SumDataModel.Number.THIRD);
        setTextChangedListener(R.id.et_number_sixth, SumDataModel.Number.FOURTH);
        setTextChangedListener(R.id.et_number_fourth, SumDataModel.Number.FIFTH);
        setTextChangedListener(R.id.et_number_second, SumDataModel.Number.SIXTH);

        tvSum.setOnClickListener(view -> {
            sumActivityViewModel.toggleAnimation();
            tvSum.setAlpha(1);
        });

    }

    private void setTextChangedListener(int res, final SumDataModel.Number number) {
        ((EditText) findViewById(res)).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() > 0) {
                    sumActivityViewModel.setNumber(number, Long.valueOf(charSequence.toString()));
                } else {
                    sumActivityViewModel.setNumber(number, 0L);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        bind();
    }

    private void bind() {
        compositeDisposable.add(sumActivityViewModel
                .getSum()
                .subscribe(integer -> tvSum.setText(getResources().getString(R.string.sum, integer))));

        compositeDisposable.add(sumActivityViewModel
                .getAnimationPulse()
                .subscribe(__ ->
                        tvSum.setAlpha(tvSum.getAlpha() == 1 ? 0 : 1)
                ));
    }

    @Override
    protected void onPause() {
        super.onPause();
        unBind();
    }

    private void unBind() {
        compositeDisposable.clear();
    }
}
