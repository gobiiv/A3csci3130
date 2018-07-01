package com.acme.a3csci3130;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class CreateContactActivity extends Activity implements AdapterView
        .OnItemSelectedListener {

    private Button submitButton;
    private EditText nameField, business_numberField, addressField;
    private Spinner primary_businessField, provinceField;
    private MyApplicationData appState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_contact_activity);
        //Get the app wide shared variables
        appState = ((MyApplicationData) getApplicationContext());

        submitButton = (Button) findViewById(R.id.submitButton);
        nameField = (EditText) findViewById(R.id.name);
        business_numberField = (EditText) findViewById(R.id.business_number);
        primary_businessField = (Spinner) findViewById(R.id.primary_business);
        //primary_businessField.setOnItemSelectedListener(this);
        addressField = (EditText) findViewById(R.id.address);
        provinceField = (Spinner) findViewById(R.id.province);
        //provinceField.setOnItemSelectedListener(this);
    }

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }

    public void submitInfoButton(View v) {
        //each entry needs a unique ID
        String uid = appState.firebaseReference.push().getKey();
		String business_number = business_numberField.getText().toString();
		String name = nameField.getText().toString();
        String primary_business = primary_businessField.getSelectedItem().toString();
        String address = addressField.getText().toString();
        String province = provinceField.getSelectedItem().toString();
        Validator val = new Validator();
        if(val.validate(business_number, name, primary_business, address, province)){
            Contact person = new Contact(uid, business_number, name, primary_business, address, province);
            appState.firebaseReference.child(uid).setValue(person);
            finish();
        } else {
            TextView err = (TextView) findViewById(R.id.error);
            err.setText("Invalid! Please verify fields.");
        }


    }
}
