package com.codetouch.pautas.activities;

import android.app.Activity;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.codetouch.pautas.R;
import com.codetouch.pautas.database.DatabaseHelper;
import com.codetouch.pautas.models.Schedule;
import com.codetouch.pautas.models.User;

public class RegisterActivity extends AppCompatActivity {

    private DatabaseHelper db;
    private ConstraintLayout layout;
    private EditText edtName, edtEmail, edtPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        db = new DatabaseHelper(this);

        layout = findViewById(R.id.layout);
        edtName = findViewById(R.id.edt_name);
        edtEmail = findViewById(R.id.edt_email);
        edtPass = findViewById(R.id.edt_pass);

        findViewById(R.id.btn_register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = new User(
                        edtName.getText().toString(),
                        edtEmail.getText().toString(),
                        edtPass.getText().toString());
                boolean res = db.insertUser(user);

                if (res)
                    finish();
                else
                    Snackbar.make(layout, R.string.failed_save_user, Snackbar.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
