package de.fs.crossfitonly;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class StartActivity extends AppCompatActivity {

    private Button button_crossfit;
    private Button button_train;
    private  Button button_bmi;
    private Button button_plank;
    GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        button_crossfit = findViewById(R.id.button_crossfit);
        button_train = findViewById(R.id.button_workout);
        button_bmi = findViewById(R.id.button_bmi);
        button_plank = findViewById(R.id.button_plank);

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

        button_bmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBMI();
            }
        });

        button_plank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPlank();
            }
        });

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);




    }

    private void openCrossfit() {
        Intent intent = new Intent(this, CrossfitActivity.class);
        startActivity(intent);
    }

    private void openTrain() {
        Intent intent = new Intent(this, TrainActivity.class);
        startActivity(intent);
    }
    private void openBMI(){
        Intent intent = new Intent(this, BodyActivity.class);
        startActivity(intent);
    }
    private void openPlank(){
        Intent intent = new Intent(this, PlankActivity.class);
        startActivity(intent);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.logout,menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        switch(item.getItemId()){
            case R.id.logout:

                mGoogleSignInClient.signOut()
                        .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                                Intent intent = new Intent(StartActivity.this,LoginActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        });
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
}
}
