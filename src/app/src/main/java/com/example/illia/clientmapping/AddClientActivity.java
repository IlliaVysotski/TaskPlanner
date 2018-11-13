package com.example.illia.clientmapping;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static android.R.attr.id;

public class AddClientActivity extends AppCompatActivity implements View.OnClickListener {

    DBHelper dbHelper;
    private Button btnOk;
    private EditText etFirstName, etLastName, etPhone, etAddress, etZametki;
    private TextView wrongAdd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_client);

        btnOk = (Button) findViewById(R.id.btnOk);
        btnOk.setOnClickListener(this);

        etFirstName = (EditText) findViewById(R.id.etFirstName);
        etLastName = (EditText) findViewById(R.id.etLastName);
        etPhone = (EditText) findViewById(R.id.etPhone);
        etAddress = (EditText) findViewById(R.id.etAddress);
        etZametki = (EditText) findViewById(R.id.etZametki);

        wrongAdd = (TextView) findViewById(R.id.wrongAdd);

        dbHelper = new DBHelper(this);
    }

    @Override
    public void onClick(View v) {

        Bundle extras = getIntent().getExtras();

        switch (v.getId()) {

            case R.id.btnOk:
                if (!etFirstName.getText().toString().equals("") &&
                        !etLastName.getText().toString().equals("") &&
                        !etPhone.getText().toString().equals("") &&
                        !etAddress.getText().toString().equals("")) {
                    //функция добавления записи в бд
                    Contact contact = new Contact(
                            extras.getString("id0"),
                            etFirstName.getText().toString(),
                            etLastName.getText().toString(),
                            etPhone.getText().toString(),
                            etAddress.getText().toString(),
                            etZametki.getText().toString());
                    if (dbHelper.getContactByPhone(contact.getPhone(), extras.getString("id0")) == null) {

                        dbHelper.addContact(contact);

                        Intent intent = new Intent(this, MainActivity.class);
                        intent.putExtra("id0", contact.getID0());
                        startActivity(intent);
                    } else {
                        wrongAdd.setText("      Contact is ready in db!");
                    }
                    break;
                } else {
                    wrongAdd.setText("              Empty fields!");
                }
        }
    }
}
