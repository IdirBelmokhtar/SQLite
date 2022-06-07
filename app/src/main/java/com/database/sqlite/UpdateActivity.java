package com.database.sqlite;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UpdateActivity extends AppCompatActivity {

    private EditText title_input_update,author_input_update,number_input_update;
    private Button update_button, delete_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        getSupportActionBar().setTitle(getIntent().getStringExtra("id"));

        title_input_update = findViewById(R.id.title_input_update);
        author_input_update = findViewById(R.id.author_input_update);
        number_input_update = findViewById(R.id.number_input_update);
        update_button = findViewById(R.id.update_button);
        delete_button = findViewById(R.id.delete_button);

        getAndSetIntentData();


        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                myDB.updateData(getIntent().getStringExtra("id"),
                        title_input_update.getText().toString().trim(),
                        author_input_update.getText().toString().trim(),
                        number_input_update.getText().toString().trim());
            }
        });

        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });
    }

    private void getAndSetIntentData() {
        title_input_update.setText(getIntent().getStringExtra("title"));
        author_input_update.setText(getIntent().getStringExtra("author"));
        number_input_update.setText(getIntent().getStringExtra("pages"));
    }

    private void confirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + getIntent().getStringExtra("id") + " ?");
        builder.setMessage("Are you sure you want to delete " + getIntent().getStringExtra("id") + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                myDB.deleteOneRow(getIntent().getStringExtra("id"));
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.show();
    }
}