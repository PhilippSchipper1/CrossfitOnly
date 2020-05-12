package de.fs.crossfitonly;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InsertSetActivity extends AppCompatActivity {
    private static final String TAG = "InsertWorkoutActivity";

    DatabaseHelper mDatabaseHelper;
    private Button btnAdd;
    private EditText editText, editText2, editText3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_set);
        editText = (EditText) findViewById(R.id.set);
        editText2 = (EditText) findViewById(R.id.wdh);
        editText3 = (EditText) findViewById(R.id.weight);
        btnAdd = (Button) findViewById(R.id.btnAdd);

        mDatabaseHelper = new DatabaseHelper(this);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newEntry = editText.getText().toString();
                String newEntry2 = editText2.getText().toString();
                String newEntry3= editText3.getText().toString();

                if (editText.length() != 0 ) {
                    AddData(newEntry);
                    editText.setText("");

                    finish();

                } else {
                    toastMessage("You must put something in the text field!");
                }

            }
        });

    }

    public void AddData(String newEntry) {
        boolean insertData = mDatabaseHelper.addDataSet(newEntry, null);

        if (insertData) {
            toastMessage("Data Successfully Inserted!");
        } else {
            toastMessage("Something went wrong");
        }
    }

    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
}
