package com.acme.a3csci3130;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.database.FirebaseDatabase;

public class DetailViewActivity extends Activity {
	private EditText nameField, business_numberField, addressField;
	private Spinner primary_businessField, provinceField;
	private String uid;
	private MyApplicationData appState;
    Contact receivedPersonInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);
        receivedPersonInfo = (Contact)getIntent().getSerializableExtra("Contact");
		appState = ((MyApplicationData) getApplicationContext());

        //assign fields from activity
		nameField = (EditText) findViewById(R.id.name);
		business_numberField = (EditText) findViewById(R.id.business_number);
		primary_businessField = (Spinner) findViewById(R.id.primary_business);
		addressField = (EditText) findViewById(R.id.address);
		provinceField = (Spinner) findViewById(R.id.province);

        if(receivedPersonInfo != null){
			nameField.setText(receivedPersonInfo.name);
			business_numberField.setText(receivedPersonInfo.business_number);
			setSpinner(primary_businessField, receivedPersonInfo.primary_business);
			addressField.setText(receivedPersonInfo.address);
			setSpinner(provinceField, receivedPersonInfo.province);
        }
    }

    public void setSpinner(Spinner s, String val){
		if (s != null || (!(val.equalsIgnoreCase("")))) {
			for (int i = 0; i < s.getCount();i++){
				if (s.getItemAtPosition(i).toString().equalsIgnoreCase(val)){
					s.setSelection(i);
					break;
				}
			}
		}
	}

    public void updateContact(View v){
		uid = receivedPersonInfo.uid;
		String business_number = business_numberField.getText().toString();
		String name = nameField.getText().toString();
		String primary_business = primary_businessField.getSelectedItem().toString();
		String address = addressField.getText().toString();
		String province = provinceField.getSelectedItem().toString();

		Contact person = new Contact(uid, business_number, name, primary_business, address, province);
		appState.firebaseReference.child(uid).setValue(person);
		finish();
    }

    public void eraseContact(View v){
		uid = receivedPersonInfo.uid;
		appState.firebaseReference.child(uid).setValue(null);
		finish();
    }
}
