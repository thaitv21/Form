package com.thaitv.form;

import android.text.InputType;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Validation {

    private List<Input> inputs;

    private Validation(List<Input> inputs) {
        this.inputs = inputs;
    }

    public boolean validate(Callback callback) {
        boolean flag = true;
        List<EditText> invalidInputs = new ArrayList<>();
        List<EditText> emptyInputs = new ArrayList<>();
        for (Input input : inputs) {
            boolean isEmpty = input.isEmpty();
            if (isEmpty && !input.allowEmpty) {
                emptyInputs.add(input.editText);
                flag = false;
            }
            boolean isValid = input.isValid();
            if (!isValid) {
                invalidInputs.add(input.editText);
                flag = false;
            }
        }
        if (callback != null) {
            callback.callback(flag, invalidInputs, emptyInputs);
        }
        return flag;
    }

    public boolean validate() {
        return validate(null);
    }



    private static class Input {
        EditText editText;
        String regex;
        boolean allowEmpty = false;
        boolean trim = true;

        private Input(EditText editText, String regex) {
            this.editText = editText;
            this.regex = regex;
        }

        public Input(EditText editText, String regex, boolean allowEmpty) {
            this.editText = editText;
            this.regex = regex;
            this.allowEmpty = allowEmpty;
        }

        boolean isEmpty() {
            String s = editText.getText().toString();
            if (trim) {
                s = s.trim();
            }
            return s.isEmpty();
        }

        boolean isValid() {
            int inputType = editText.getInputType();
            String value = editText.getText().toString();
            if (trim) {
                value = value.trim();
            }
            if (regex != null) {
                return match(regex, value);
            }
            switch (inputType) {
                case InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS:
                    return match(Patterns.EMAIL_ADDRESS, value);
                case InputType.TYPE_CLASS_PHONE:
                    return match("^[+]?[0-9]{10,13}$", value);
                case InputType.TYPE_TEXT_VARIATION_URI:
                    return match(Patterns.WEB_URL, value);
            }
            if (inputType == (InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD) && (editText instanceof FormEditText)) {
                return ((FormEditText) editText).validatePassword(value);
            } else if (inputType == (InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) && (editText instanceof FormEditText)) {
                return ((FormEditText) editText).validatePassword(value);
            }
            return true;
        }

        private boolean match(String regex, String value) {
            Pattern pattern = Pattern.compile(regex);
            return !TextUtils.isEmpty(value) && pattern.matcher(value).matches();
        }

        private boolean match(Pattern pattern, String value) {
            return !TextUtils.isEmpty(value) && pattern.matcher(value).matches();
        }
    }

    public static class Builder {

        private List<Input> inputs = new ArrayList<>();

        public Builder add(EditText editText) {
            return add(editText, null);
        }

        public Builder add(EditText editText, String regex, boolean allowEmpty, boolean trim) {
            Input input = new Input(editText, regex, allowEmpty);
            input.trim = trim;
            inputs.add(input);
            return this;
        }

        public Builder add(EditText editText, String regex, boolean allowEmpty) {
            inputs.add(new Input(editText, regex, allowEmpty));
            return this;
        }

        public Builder add(EditText editText, boolean allowEmpty) {
            inputs.add(new Input(editText, null, allowEmpty));
            return this;
        }

        public Builder add(EditText editText, String regex) {
            inputs.add(new Input(editText, regex));
            return this;
        }

        public Builder addAll(EditText... editTexts) {
            for (EditText edt : editTexts) {
                inputs.add(new Input(edt, null));
            }
            return this;
        }

        public Builder addAll(List<EditText> editTexts) {
            for (EditText edt : editTexts) {
                inputs.add(new Input(edt, null));
            }
            return this;
        }

        public Validation build() {
            return new Validation(inputs);
        }
    }

}
