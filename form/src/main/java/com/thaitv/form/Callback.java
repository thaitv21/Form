package com.thaitv.form;

import android.widget.EditText;

import java.util.List;

public interface Callback {
        void callback(boolean isSuccessful, List<EditText> invalidInputs, List<EditText> emptyInputs);
    }