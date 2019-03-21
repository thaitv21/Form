#Form

Support validating some types of EditText:
- Email
- Password (with FormEditText instead of EditText)
- Phone
- URL

#Installation

####Step 1: Add it in your root build.gradle at the end of repositories:
```
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```

####Step 2: Add the dependency
```
implementation 'com.github.thaitv21:Form:Tag'
```

Well, now you can use it.

#Usage
There are many ways to validate input value.

###Option 1: Use Validation
```
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
```
Moreover, your Builder instance can do the following methods:
```
add(EditText editText)
add(EditText editText, String regex, boolean allowEmpty, boolean trim)
add(EditText editText, String regex, boolean allowEmpty)
add(EditText editText, boolean allowEmpty)
add(EditText editText, String regex)
addAll(EditText... editTexts)
addAll(List<EditText> editTexts)
```

###Option 2: Use FormLinearLayout
FormLinearLayout is an extended LinearLayout. In order to validate all of EditText, you just wrap your layout with a element <FormLinearLayout> like this:
```angular2html
<com.thaitv.form.FormLinearLayout
    android:id="@+id/formlinear"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:orientation="vertical">

    <FrameLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content">

        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="@string/app_name" />
        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="wrap_content">
            <EditText
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:inputType="textEmailAddress"
                android:hint="email"/>
        </LinearLayout>

        <EditText
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:inputType="textPassword"
            android:hint="password"/>
    </FrameLayout>
    <EditText
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:inputType="phone"
        android:hint="phone"/>

    <Button
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_gravity="center_horizontal"
        android:onClick="validateEverything"
        android:text="Validate"/>

</com.thaitv.form.FormLinearLayout>
```  

```
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
})
```

As you see, I don't need any instance of EditText.

#Option 3: For any layout
It's helpful if you don't want to make your layout more staked.
For any layout, like this:
```
<LinearLayout
   android:id="@+id/linearlayout"
   android:layout_width="match_parent"
   android:layout_height="wrap_content"
   android:orientation="vertical">

   <EditText
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:inputType="textEmailAddress"
        android:hint="email"/>
   <EditText
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:inputType="textPassword"
        android:hint="password"/>

   <Button
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_gravity="center_horizontal"
        android:onClick="validateEverything"
        android:text="Validate"/>
</LinearLayout>
```

```
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
```



