package com.myfablo.seller.utils;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;

import androidx.asynclayoutinflater.view.AsyncLayoutInflater;

import java.util.ArrayList;
import java.util.List;

public class AsyncCell extends FrameLayout {
    private int layoutId = -1;
    private boolean isInflated = false;
    private List<Runnable> bindingFunctions = new ArrayList<>();

    public AsyncCell(Context context) {
        super(context, null, 0, 0);
        LayoutParams params = new LayoutParams(MATCH_PARENT, WRAP_CONTENT);
        setLayoutParams(params);
    }

    public void inflate() {
        AsyncLayoutInflater inflater = new AsyncLayoutInflater(getContext());
        inflater.inflate(layoutId, this, (view, resid, parent) -> {
            isInflated = true;
            addView(createDataBindingView(view));
            bindView();
        });
    }

    private void bindView() {
        for (Runnable bindFunc : bindingFunctions) {
            bindFunc.run();
        }
        bindingFunctions.clear();
    }

    public void bindWhenInflated(Runnable bindFunc) {
        if (isInflated) {
            bindFunc.run();
        } else {
            bindingFunctions.add(bindFunc);
        }
    }

    public View createDataBindingView(View view) {
        return view;
    }
}