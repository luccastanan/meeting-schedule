package com.codetouch.pautas;

import android.content.Context;
import android.content.SharedPreferences;

public class Utilities {
    public static void saveUserId(Context context, int userId) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(Constants.PREFERENCES, Context.MODE_PRIVATE);
        sharedPreferences.edit().putInt("user_id", userId).apply();
    }

    public static int getUserId(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(Constants.PREFERENCES, Context.MODE_PRIVATE);
        return sharedPreferences.getInt("user_id", -1);
    }
}
