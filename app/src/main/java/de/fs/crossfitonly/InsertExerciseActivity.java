package de.fs.crossfitonly;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InsertExerciseActivity extends AppCompatActivity {
    private static final String TAG = "InsertWorkoutActivity";

    DatabaseHelper mDatabaseHelper;
    private Button btnAdd;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_workout);
        editText = (EditText) findViewById(R.id.insert);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        mDatabaseHelper = new DatabaseHelper(this);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newEntry = editText.getText().toString();

                int id = getIntent().getIntExtra("id", -1);
                String id_neu = Integer.toString(id);
                if (editText.length() != 0) {
                    AddData(newEntry, id_neu);
                    editText.setText("");

                    finish();

                } else {
                    toastMessage("You must put something in the text field!");
                }

            }
        });

    }

    public void AddData(String newEntry, String id) {
        boolean insertData = mDatabaseHelper.addDataDetail(newEntry, id);

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
