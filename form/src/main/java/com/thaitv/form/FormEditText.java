package com.thaitv.form;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.TextUtils;
import android.util.AttributeSet;

import java.util.regex.Pattern;

public class FormEditText extends android.support.v7.widget.AppCompatEditText  {

    private String regex;

    private boolean containsSpecialCharacter = false;
    private boolean containsNumber = false;
    private boolean containsUppercaseCharacter = false;
    private int passwordMinLength = -1;
    private boolean trim = true;

    public FormEditText(Context context) {
        super(context);
    }

    public FormEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.FormEditText);
        containsSpecialCharacter = ta.getBoolean(R.styleable.FormEditText_containsSpecialCharacter, false);
        containsNumber = ta.getBoolean(R.styleable.FormEditText_containsDigit, false);
        containsUppercaseCharacter = ta.getBoolean(R.styleable.FormEditText_containsUpperCase, false);
        regex = ta.getString(R.styleable.FormEditText_regex);
        passwordMinLength = ta.getInt(R.styleable.FormEditText_passwordMinLength, -1);
        trim = ta.getBoolean(R.styleable.FormEditText_trim, true);
        ta.recycle();
    }

    public FormEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs);
    }

    public boolean validatePassword(String value) {
        StringBuilder sb = new StringBuilder();
        sb.append("^");
        if (containsNumber) {
            sb.append("(?=.*\\d)");
        }
        sb.append("(?=.*[a-z])");
        if (containsUppercaseCharacter) {
            sb.append("(?=.*[A-Z])");
        }
        if (containsSpecialCharacter) {
            sb.append("(?=.*[@$!%*?&])");
        }
        sb.append("[a-zA-Z\\d@$!%*?&]{");
        if (passwordMinLength > 0) {
            sb.append(passwordMinLength);
        } else {
            sb.append("0");
        }
        sb.append(",}$");
        String regex = sb.toString();
        Pattern pattern = Pattern.compile(regex);
        return !TextUtils.isEmpty(value) && pattern.matcher(value).matches();
    }

    public boolean isEmpty() {
        Editable text = getText();
        if (text == null) {
            return true;
        }
        String value = text.toString();
        return value.isEmpty();
    }

    public boolean isValid() {
        return new Validation.Builder().add(this, regex, false, trim).build().validate();
    }
}
