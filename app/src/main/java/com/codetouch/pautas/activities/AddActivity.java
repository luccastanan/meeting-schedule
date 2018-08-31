package com.codetouch.pautas.activities;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.codetouch.pautas.Constants;
import com.codetouch.pautas.R;
import com.codetouch.pautas.database.DatabaseHelper;
import com.codetouch.pautas.models.Schedule;

public class AddActivity extends AppCompatActivity {

    private DatabaseHelper db;
    private EditText edtTitle, edtDescription, edtDetails, edtActor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        db = new DatabaseHelper(this);

        edtTitle = findViewById(R.id.edt_title);
        edtDescription = findViewById(R.id.edt_description);
        edtDetails = findViewById(R.id.edt_details);
        edtActor = findViewById(R.id.edt_actor);

        findViewById(R.id.btn_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Schedule schedule = new Schedule(
                        edtTitle.getText().toString(),
                        edtDescription.getText().toString(),
                        edtDetails.getText().toString(),
                        0);
                boolean res = db.insert(schedule);

                Intent rIntent = new Intent();
                if (res)
                    rIntent.putExtra("schedule", schedule);
                setResult(res ? Activity.RESULT_OK : Activity.RESULT_CANCELED, rIntent);
                AddActivity.this.finish();
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
