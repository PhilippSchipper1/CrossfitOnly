package de.fs.crossfitonly;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class CrossfitActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView lv;
    private ArrayList<Workout> wodList;
    private ArrayList<String> titleList;
    private Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = findViewById(R.id.lv);

       wodList = DataHelper.loadWorkout(this);
       titleList = new ArrayList<>();
       for (int i =0; i<wodList.size(); i++){
           String str= wodList.get(i).getTitle();
           titleList.add(str);
       }

       adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, titleList);
       lv.setAdapter((ListAdapter) adapter);


       lv.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {

        Intent intent = new Intent(this, CrossfitDetailActivity.class);
        String title = wodList.get(pos).getTitle();
        String wod = wodList.get(pos).getWod();

        Log.d("MainActivity", title);
        Log.d("MainActivity", wod);

        intent.putExtra("EXTRA_TITLE", title);
        intent.putExtra("EXTRA_WOD", wod);

        startActivity(intent);
    }
}
