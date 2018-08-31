package com.codetouch.pautas.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.codetouch.pautas.models.Schedule;
import com.codetouch.pautas.models.User;

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
        db.execSQL("CREATE TABLE IF NOT EXISTS users (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name VARCHAR(255), " +
                "email VARCHAR(255), " +
                "pass VARCHAR(255));");
        db.execSQL("CREATE TABLE IF NOT EXISTS schedules (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "title VARCHAR(255), " +
                "description VARCHAR(255), " +
                "details TEXT," +
                "author_id INT," +
                "status INT, " +
                "FOREIGN KEY (author_id) REFERENCES users (id));");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }

    public boolean insertSchedule(Schedule schedule) {
        ContentValues values = new ContentValues();
        values.put("title", schedule.getTitle());
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

    public List<Schedule> listSchedule(boolean status) {
        Cursor cursor = getReadableDatabase().rawQuery("SELECT * FROM schedules WHERE status = ?", new String[]{String.valueOf(status ? 1 : 0)});
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
        cursor.close();
        return scheduleList;
    }

    public boolean updateSchedule(Schedule schedule) {
        ContentValues values = new ContentValues();
        values.put("title", schedule.getTitle());
        values.put("description", schedule.getDescription());
        values.put("details", schedule.getDetails());
        values.put("status", schedule.isStatus() ? 1 : 0);
        try {
            long count = getWritableDatabase().update("schedules", values, "id = ?", new String[]{String.valueOf(schedule.getId())});
            return count > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean insertUser(User user) {
        ContentValues values = new ContentValues();
        values.put("name", user.getName());
        values.put("email", user.getEmail());
        values.put("pass", user.getPass());
        try {
            long rowId = getWritableDatabase().insert("users", null, values);
            return rowId > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public String selectUserName(int userId) {
        Cursor cursor = getReadableDatabase().rawQuery("SELECT name FROM users WHERE id = ?", new String[]{String.valueOf(userId)});
        String name = "";
        if (cursor.moveToFirst())
            name = cursor.getString(0);
        cursor.close();
        return name;
    }

    public int login(String email, String pass) {
        Cursor cursor = getReadableDatabase().rawQuery("SELECT id FROM users WHERE email = ? AND pass = ?", new String[]{email, pass});
        int id = -1;
        if (cursor.moveToFirst()) {
            id = cursor.getInt(0);
        }
        cursor.close();
        return id;
    }
}
