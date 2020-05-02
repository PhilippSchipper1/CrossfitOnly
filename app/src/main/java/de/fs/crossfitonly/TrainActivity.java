package de.fs.crossfitonly;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class TrainActivity extends AppCompatActivity {

     ListView lv;
     FloatingActionButton fab;
     String name;
     ArrayList<LvItem>arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train);

        lv= findViewById(R.id.lv_train);
        fab= findViewById(R.id.flot);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(TrainActivity.this);
                dialog.setContentView(R.layout.fabitem);
                final EditText etname = dialog.findViewById(R.id.etname);
                Button button = dialog.findViewById(R.id.btnsave);



                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        name = etname.getText().toString();

                        LvItem lvItem = new LvItem();
                        lvItem.setName(name);

                        arrayList.add(lvItem);
                        dialog.dismiss();

                        ContectAdapter contectAdapter = new ContectAdapter(arrayList, TrainActivity.this);
                        lv.setAdapter(contectAdapter);

                    }
                });
                dialog.show();
            }
        });
    }
}
