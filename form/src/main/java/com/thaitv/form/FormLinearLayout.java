package com.thaitv.form;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class FormLinearLayout extends LinearLayout {

    public FormLinearLayout(Context context) {
        super(context);
    }

    public FormLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public FormLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public boolean validate(Callback callback) {
        List<EditText> editTexts = findEditText(this);
        return new Validation.Builder().addAll(editTexts).build().validate(callback);
    }

    private List<EditText> findEditText(View view) {
        List<EditText> editTexts = new ArrayList<>();
        if (view instanceof EditText) {
            editTexts.add((EditText) view);
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childView = viewGroup.getChildAt(i);
                editTexts.addAll(findEditText(childView));
            }
        }
        return editTexts;
    }
}