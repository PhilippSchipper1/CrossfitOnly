package de.fs.crossfitonly;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StartActivity extends AppCompatActivity {

    private Button button_crossfit;
    private Button button_train;
    private  Button button_log;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        button_crossfit = findViewById(R.id.button_crossfit);
        button_train = findViewById(R.id.button_workout);
        button_log = findViewById(R.id.button_log);

        button_train.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTrain();
            }
        });

        button_crossfit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCrossfit();
            }
        });

        button_log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLog();
            }
        });



    }

    private void openCrossfit() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void openTrain() {
        Intent intent = new Intent(this, TrainActivity.class);
        startActivity(intent);
    }
    private void openLog(){
        Intent intent = new Intent(this, LogActivity.class);
        startActivity(intent);
    }
}
