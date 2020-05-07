package de.fs.crossfitonly;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String TAG ="DatabaseHelper";

    public static final String TABLE_NAME = "workout_table";
    public static final String COL1 = "id";
    public static final String COL2 =  "workout";





    public DatabaseHelper(Context context) {

        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE "+TABLE_NAME+" (ID INTEGER PRIMARY KEY AUTOINCREMENT, "+ COL2+ "TEXT)";
        db.execSQL(createTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);
    }
    public boolean addText(String text){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, text);

        long result =sqLiteDatabase.insert(TABLE_NAME, null, contentValues);

        if(result==-1){
            return false;
        }else {
            return true;
        }
    }
    public ArrayList getWorkouts(){
        SQLiteDatabase sqLiteDatabase= this.getReadableDatabase();
        ArrayList<String>arrayList = new ArrayList<String>();

        Cursor cursor= sqLiteDatabase.rawQuery("select * from " + TABLE_NAME, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()){
            arrayList.add(cursor.getString(cursor.getColumnIndex(COL2)));
            cursor.moveToNext();
        }
        return arrayList;
    }
}
