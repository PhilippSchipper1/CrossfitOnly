package de.fs.crossfitonly;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";
    private static final String DATABASE_NAME = "workout.db";
    private static final String TABLE_NAME = "workout_table";
    private static final String COL1 = "ID";
    private static final String COL2 = "workout";

    private static final String TABLE_NAME2 = "exercise_table";
    private static final String DCOL1 = "ID";
    private static final String DCOL2 = "exercise";
    private static final String DCOL3 = "workoutID";

    private static final String TABLE_NAME3 = "set_table";
    private static final String SCOL1 = "ID";
    private static final String SCOL2 = "sets";
    private static final String SCOL3 = "exerciseID";
    private static final String SCOL4 = "reps";
    private static final String SCOL5 = "weight";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " + COL2+" TEXT)";
        String createTable2 = "CREATE TABLE " + TABLE_NAME2 +" (ID INTEGER PRIMARY KEY AUTOINCREMENT, " + DCOL2 +  " TEXT, "+DCOL3+" INT)";
        String createTable3 = "CREATE TABLE " + TABLE_NAME3 + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " + SCOL2 + " TEXT, "+SCOL3+" INT, "+SCOL4
                +" TEXT, "+SCOL5+" TEXT)";
        db.execSQL(createTable3);
        Log.d(TAG, createTable3);
        db.execSQL(createTable2);
        db.execSQL(createTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP IF TABLE EXISTS " + TABLE_NAME);
        db.execSQL("DROP IF TABLE EXISTS " + TABLE_NAME2);
        db.execSQL("DROP IF TABLE EXISTS " + TABLE_NAME3);

        onCreate(db);
    }

    public boolean addData(String item) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, item);

        Log.d(TAG, "addData: Adding " + item + " to " + TABLE_NAME);

        long result = db.insert(TABLE_NAME, null, contentValues);

        //if date as inserted incorrectly it will return -1
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor getData() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public Cursor getItemID(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COL1 + " FROM " + TABLE_NAME +
                " WHERE " + COL2 + " = '" + name + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public Cursor getItemDetailID(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + DCOL1 + " FROM " + TABLE_NAME2 +
                " WHERE " + DCOL2 + " = '" + name + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public void updateName(String newName, int id, String oldName) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_NAME + " SET " + COL2 +
                " = '" + newName + "' WHERE " + COL1 + " = '" + id + "'" +
                " AND " + COL2 + " = '" + oldName + "'";
        Log.d(TAG, "updateName: query: " + query);
        Log.d(TAG, "updateName: Setting name to " + newName);
        db.execSQL(query);
    }

    public void deleteName(int id, String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME + " WHERE "
                + COL1 + " = '" + id + "'" +
                " AND " + COL2 + " = '" + name + "'";
        Log.d(TAG, "deleteName: query: " + query);
        Log.d(TAG, "deleteName: Deleting " + name + " from database.");
        db.execSQL(query);
    }

    public void deleteNameDetail(int id, String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME2 + " WHERE "
                + DCOL1 + " = '" + id + "'" +
                " AND " + DCOL2 + " = '" + name + "'";
        Log.d(TAG, "deleteName: query: " + query);
        Log.d(TAG, "deleteName: Deleting " + name + " from database.");
        db.execSQL(query);
    }

    public Cursor getDataDetail(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME2+ " WHERE workoutID = "+id;
        Log.d(TAG, query);
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public boolean addDataDetail(String item, String item2) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DCOL2, item);
        contentValues.put(DCOL3, item2);

        Log.d(TAG, "addData: Adding " + item+ item2 + " to " + TABLE_NAME2);

        long result = db.insert(TABLE_NAME2, null, contentValues);

        //if date as inserted incorrectly it will return -1
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor getDataSet(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME2+ " WHERE exerciseID = "+id;
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public Cursor getItemSetID(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + SCOL1 + " FROM " + TABLE_NAME3 +
                " WHERE " + SCOL2 + " = '" + name + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public void deleteNameSet(int id, String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME3 + " WHERE "
                + SCOL1 + " = '" + id + "'" +
                " AND " + SCOL2 + " = '" + name + "'";
        Log.d(TAG, "deleteName: query: " + query);
        Log.d(TAG, "deleteName: Deleting " + name + " from database.");
        db.execSQL(query);
    }

    public boolean addDataSet(String item, String item2) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SCOL2, item);
        contentValues.put(SCOL3, item2);

        Log.d(TAG, "addData: Adding " + item + " to " + TABLE_NAME3);

        long result = db.insert(TABLE_NAME3, null, contentValues);

        //if date as inserted incorrectly it will return -1
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }
}
