package de.fs.crossfitonly;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;
import de.fs.crossfitonly.R;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class TrainSetActivity extends AppCompatActivity {
    private FloatingActionButton fab;

    private static final String TAG = "ListDataActivity";

    DatabaseHelper mDatabaseHelper;

    private int id;

    private AdapterHelper adapter;

    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train_sets);
        mListView = (ListView) findViewById(R.id.lv_train);
        mDatabaseHelper = new DatabaseHelper(this);

        id = getIntent().getIntExtra("id",-1);

        populateListView();
        fab= findViewById(R.id.flot);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insert(getIntent().getIntExtra("id", -1));
            }
        });
    }

    private void populateListView() {
        Log.d(TAG, "populateListView: Displaying data in the ListView.");

        //get the data and append to a list
        Cursor data = mDatabaseHelper.getDataSet(Integer.toString(id));
        ArrayList<Sets> listData = new ArrayList<>();
        while(data.moveToNext()){
            //get the value from the database in column 1
            //then add it to the ArrayList
            Sets set = new Sets(data.getString(1), data.getString(3), data.getString(4));
            listData.add(set);
        }
        //create the list adapter and set the adapter
        adapter = new AdapterHelper(this, R.layout.lv_complex_item, listData);
        mListView.setAdapter(adapter);

        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                           int pos, long id) {
                final String name = arg0.getItemAtPosition(pos).toString();
                Log.d(TAG, "TEST:"+ name);
                Log.d(TAG, "onItemClick: You Clicked on " + name);

                Cursor data = mDatabaseHelper.getItemSetID(name); //get the id associated with that name
                int itemID = -1;
                while(data.moveToNext()){
                    itemID = data.getInt(0);
                }
                if(itemID > -1){
                    final int finalItemID = itemID;
                    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            switch (which){
                                case DialogInterface.BUTTON_POSITIVE:
                                    mDatabaseHelper.deleteNameSet(finalItemID,name);
                                    finish();
                                    overridePendingTransition(0, 0);
                                    startActivity(getIntent());
                                    overridePendingTransition(0, 0);
                                    toastMessage("removed from database");
                                    break;

                                case DialogInterface.BUTTON_NEGATIVE:
                                    //No button clicked
                                    break;
                            }
                        }
                    };

                    AlertDialog.Builder builder = new AlertDialog.Builder(mListView.getContext());
                    builder.setMessage("Are you sure you want to delete this?").setPositiveButton("Yes", dialogClickListener)
                            .setNegativeButton("No", dialogClickListener).show();

                }
                else{
                    toastMessage("No ID associated with that name");
                }
                Log.v("long clicked","pos: " + pos);

                return true;
            }
        });
    }

    /**
     * customizable toast
     * @param message
     */
    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }


    private void insert(int id){
        Intent intent = new Intent(this, InsertSetActivity.class);
        intent.putExtra("id", this.id);
        startActivity(intent);

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {

            while (adapter.getCount()>0){
                adapter.remove(adapter.getItem(0));
            }

            Cursor cursor = mDatabaseHelper.getDataSet(Integer.toString(id));
            for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                Sets set = new Sets(cursor.getString(1), cursor.getString(3), cursor.getString(4));
                adapter.add(set);

                Log.d(TAG, "TEST:"+ set);
            }
            adapter.notifyDataSetChanged();
        }
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        finish();
        overridePendingTransition(0, 0);
        startActivity(getIntent());
        overridePendingTransition(0, 0);
    }
}
