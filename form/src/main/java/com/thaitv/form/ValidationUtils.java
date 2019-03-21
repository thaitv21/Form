package com.thaitv.form;

import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class ValidationUtils {

    public static boolean validate(View view, Callback callback) {
        List<EditText> editTexts = findEditText(view);
        return new Validation.Builder().addAll(editTexts).build().validate(callback);
    }

    private static List<EditText> findEditText(View view) {
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
