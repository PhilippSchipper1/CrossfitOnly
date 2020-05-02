package de.fs.crossfitonly;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    private TextView title;
    private TextView wod;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_acitvity);

        title = findViewById(R.id.title_tv);
        wod = findViewById(R.id.wod_tv);

        Bundle extra = getIntent().getExtras();
        if(extra!= null){
            String t= extra.getString("EXTRA_TITLE");
            String w= extra.getString("EXTRA_WOD");


            Log.d("DetailActivity", t);
            Log.d("DetailActivity", w);

            title.setText(t);
            wod.setText(w);
        }



    }
}
