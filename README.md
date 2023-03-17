# MiniProject
 Get reference to the layout where you want to add the new EditTexts
LinearLayout layout = findViewById(R.id.editTexts_layout);

 Get reference to the existing EditText
EditText editText = findViewById(R.id.existing_editText);

 Set a text change listener on the existing EditText
editText.addTextChangedListener(new TextWatcher() {
    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
         Not needed for this example
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
         Check if the existing EditText is not empty
        if (!TextUtils.isEmpty(charSequence)) {
             Create a new EditText
            EditText newEditText = new EditText(MainActivity.this);
             Set some properties for the new EditText
            newEditText.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
            newEditText.setHint(Enter something);
             Add the new EditText to the layout
            layout.addView(newEditText);
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {
         Not needed for this example
    }
});
