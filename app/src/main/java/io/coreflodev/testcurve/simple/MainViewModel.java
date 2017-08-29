package io.coreflodev.testcurve.simple;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.adapters.TextViewBindingAdapter;

import com.android.databinding.library.baseAdapters.BR;

import java.util.ArrayList;
import java.util.List;

public class MainViewModel extends BaseObservable {

    private static final int NUMBER_OF_FIELDS = 6;
    private static final int DEFAULT_VALUE_FOR_FIELDS = 0;

    private List<Integer> numbers;

    public MainViewModel() {
        numbers = new ArrayList<>();
        for (int i = 0; i < NUMBER_OF_FIELDS; i++) {
            numbers.add(DEFAULT_VALUE_FOR_FIELDS);
        }
    }

    @Bindable
    public String getSum() {
        int sum = 0;
        for (int i = 0; i < numbers.size(); i++) {
            sum += numbers.get(i);
        }
        return "The sum is " + sum;
    }

    public TextViewBindingAdapter.OnTextChanged onChanged(final int number) {
        return (s, start, before, count) -> {
            if (number < NUMBER_OF_FIELDS) {
                int value = DEFAULT_VALUE_FOR_FIELDS;
                if (s.length() > 0) {
                    value = Integer.valueOf(s.toString());
                }
                numbers.set(number, value);
                notifyPropertyChanged(BR.sum);
            }
        };
    }
}
