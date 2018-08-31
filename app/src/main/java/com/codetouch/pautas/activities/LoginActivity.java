package com.codetouch.pautas.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.EditText;

import com.codetouch.pautas.R;
import com.codetouch.pautas.Utilities;
import com.codetouch.pautas.database.DatabaseHelper;

public class LoginActivity extends AppCompatActivity {

    private DatabaseHelper db;
    private CardView layout;
    private EditText edtEmail, edtPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        db = new DatabaseHelper(this);

        layout = findViewById(R.id.layout);
        edtEmail = findViewById(R.id.edt_email);
        edtPass = findViewById(R.id.edt_pass);

        findViewById(R.id.btn_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = db.login(edtEmail.getText().toString(), edtPass.getText().toString());
                if (id != -1) {
                    Utilities.saveUserId(LoginActivity.this, id);
                    finish();
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                } else {
                    Snackbar.make(layout, R.string.invalid_login, Snackbar.LENGTH_LONG).show();
                }
            }
        });

        findViewById(R.id.btn_register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
    }
}
