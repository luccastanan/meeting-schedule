package com.codetouch.pautas.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.codetouch.pautas.models.Schedule;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static String DB_NAME = "Schedules";
    private static int DB_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        /*db.execSQL("CREATE TABLE IF NOT EXISTS users (" +
                "id INT PRIMARY KEY, " +
                "name VARCHAR(255), " +
                "email VARCHAR(255), " +
                "pass VARCHAR(255));");
        db.execSQL("CREATE TABLE IF NOT EXISTS schedules (" +
                "id INT PRIMARY KEY, " +
                "description VARCHAR(255), " +
                "details TEXT," +
                "author_id INT," +
                "status INT, " +
                "FOREIGN KEY (author_id) REFERENCES users (id));");*/
        db.execSQL("CREATE TABLE IF NOT EXISTS schedules (" +
                "id INT PRIMARY KEY, " +
                "title VARCHAR(255), " +
                "description VARCHAR(255), " +
                "details TEXT, " +
                "status INT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }

    public boolean insert(Schedule schedule) {
        ContentValues values = new ContentValues();
        values.put("description", schedule.getDescription());
        values.put("details", schedule.getDetails());
        values.put("status", schedule.isStatus() ? 1 : 0);
        try {
            long rowId = getWritableDatabase().insert("schedules", null, values);
            return rowId > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Schedule> list() {
        Cursor cursor = getReadableDatabase().rawQuery("SELECT * FROM schedules", null);
        List<Schedule> scheduleList = new ArrayList<>();
        while (cursor.moveToNext()) {
            scheduleList.add(
                    new Schedule(
                            cursor.getInt(cursor.getColumnIndex("id")),
                            cursor.getString(cursor.getColumnIndex("title")),
                            cursor.getString(cursor.getColumnIndex("description")),
                            cursor.getString(cursor.getColumnIndex("details")),
                            cursor.getInt(0),
                            cursor.getInt(cursor.getColumnIndex("status")) == 1)
            );
        }
        return scheduleList;
    }
}
