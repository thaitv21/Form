package com.thaitv.form.samples;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.thaitv.form.Callback;
import com.thaitv.form.FormEditText;
import com.thaitv.form.FormLinearLayout;
import com.thaitv.form.Validation;
import com.thaitv.form.ValidationUtils;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText edtEmail;
    FormEditText edtPhone;
    FormEditText edtPassword;

    FormLinearLayout form;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtEmail = findViewById(R.id.edtEmail);
        edtPhone = findViewById(R.id.edtPhone);
        edtPassword = findViewById(R.id.edtPassword);
        form = findViewById(R.id.form);

    }

    public void validate(View view) {
        new Validation.Builder().addAll(edtEmail, edtPhone, edtPassword).build().validate(new Callback() {
            @Override
            public void callback(boolean isSuccessful, List<EditText> invalidInputs, List<EditText> emptyInputs) {
                StringBuilder sb = new StringBuilder();
                sb.append("EMPTY: ");
                for (EditText edt : emptyInputs) {
                    sb.append(edt.getHint());
                    sb.append(", ");
                }
                sb.append("\nINVALID: ");
                for (EditText edt : invalidInputs) {
                    sb.append(edt.getHint());
                    sb.append(", ");
                }
                Toast.makeText(MainActivity.this, sb.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void validateForm(View view) {
        form.validate(new Callback() {
            @Override
            public void callback(boolean isSuccessful, List<EditText> invalidInputs, List<EditText> emptyInputs) {
                StringBuilder sb = new StringBuilder();
                sb.append("EMPTY: ");
                for (EditText edt : emptyInputs) {
                    sb.append(edt.getHint());
                    sb.append(", ");
                }
                sb.append("\nINVALID: ");
                for (EditText edt : invalidInputs) {
                    sb.append(edt.getHint());
                    sb.append(", ");
                }
                Toast.makeText(MainActivity.this, sb.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void validateFormEditText(View view) {
        boolean passwordIsValid = edtPassword.isValid();
        boolean phoneIsValid = edtPhone.isValid();
        StringBuilder sb = new StringBuilder();
        if (!phoneIsValid) {
            sb.append("phone number is not valid\n");
        }
        if (!passwordIsValid) {
            sb.append("password is not valid");
        }
        String s = sb.toString().trim();
        if (s.isEmpty()) {
            s = "phone number and password are valid";
        }
        Toast.makeText(MainActivity.this, s, Toast.LENGTH_LONG).show();
    }

    public void validateEverything(View view) {
        FormLinearLayout formlinear = findViewById(R.id.formlinear);
        formlinear.validate(new Callback() {
            @Override
            public void callback(boolean isSuccessful, List<EditText> invalidInputs, List<EditText> emptyInputs) {
                StringBuilder sb = new StringBuilder();
                sb.append("EMPTY: ");
                for (EditText edt : emptyInputs) {
                    sb.append(edt.getHint());
                    sb.append(", ");
                }
                sb.append("\nINVALID: ");
                for (EditText edt : invalidInputs) {
                    sb.append(edt.getHint());
                    sb.append(", ");
                }
                Toast.makeText(MainActivity.this, sb.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void validateAnyLayout() {
        LinearLayout linearLayout = findViewById(R.id.linearlayout);
        ValidationUtils.validate(linearLayout, new Callback() {
            @Override
            public void callback(boolean isSuccessful, List<EditText> invalidInputs, List<EditText> emptyInputs) {
                StringBuilder sb = new StringBuilder();
                sb.append("EMPTY: ");
                for (EditText edt : emptyInputs) {
                    sb.append(edt.getHint());
                    sb.append(", ");
                }
                sb.append("\nINVALID: ");
                for (EditText edt : invalidInputs) {
                    sb.append(edt.getHint());
                    sb.append(", ");
                }
                Toast.makeText(MainActivity.this, sb.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }


}
