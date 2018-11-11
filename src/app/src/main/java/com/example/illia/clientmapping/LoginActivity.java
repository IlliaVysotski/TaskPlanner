package com.example.illia.clientmapping;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnLogin, btnSign;
    private EditText etLogin, etPassword;
    private TextView wrong;

    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);

        btnSign = (Button) findViewById(R.id.btnSign);
        btnSign.setOnClickListener(this);

        etLogin = (EditText) findViewById(R.id.etLogin);
        etPassword = (EditText) findViewById(R.id.etPassword);

        wrong = (TextView) findViewById(R.id.wrongAdd);

        dbHelper = new DBHelper(this);
    }

    @Override
    public void onClick(View v) {

        String login = etLogin.getText().toString();
        String password = etPassword.getText().toString();

        switch (v.getId()) {

            case R.id.btnLogin:
                if (!login.equals("") && !password.equals("")) {

                    if (!(dbHelper.getUser(login) == null)) {
                        if (dbHelper.getUser(login).getPassword().toString().equals(password)) {

                            Intent intent = new Intent(this, MainActivity.class);

                            User user = dbHelper.getUser(login);
                            intent.putExtra("id0", user.getLogin());

                            startActivity(intent);
                        } else {
                            etPassword.setText("");
                            wrong.setText("     Wrong password! ");
                        }
                    } else {
                        etLogin.setText("");
                        etPassword.setText("");
                        wrong.setText("     Not found! Sign in, please! ");
                    }
                } else {
                    wrong.setText("             Empty fields!");
                }
                break;

            case R.id.btnSign:
                if (!login.equals("") && !password.equals("")) {
                    if (!(dbHelper.getUser(login) == null)) {

                        wrong.setText("     This login is ready used!");
                    } else {

                        User user = new User(login, password);
                        dbHelper.addUser(user);

                        Intent intent = new Intent(this, MainActivity.class);
                        intent.putExtra("id0", user.getLogin());
                        startActivity(intent);
                    }
                } else {
                    wrong.setText("             Empty fields!");
                }
                break;
        }
    }


}
