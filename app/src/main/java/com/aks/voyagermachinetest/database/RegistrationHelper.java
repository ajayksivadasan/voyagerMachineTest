package com.aks.voyagermachinetest.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.aks.voyagermachinetest.registration.ModelRegistration;


public class RegistrationHelper {

    private static final String TABLE_NAME_USERS = "TABLE_USERS";
    public static final String DROP_TABLE_USERS = "DROP TABLE IF EXISTS " + TABLE_NAME_USERS;
    private static final String COL_ID = "id_";
    private static final String COL_NAME = "name";
    private static final String COL_EMAIL = "email";
    private static final String COL_PHONE = "phone";
    private static final String COL_U_NAME = "user_name";
    private static final String COL_PASSWORD = "password";
    public static final String CREATE_TABLE_USERS = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME_USERS + " ( " + COL_ID + " INTEGER AUTOINCREMENT PRIMARY KEY, " + COL_NAME + " TEXT," + COL_EMAIL + " TEXT,"
            + COL_PHONE + " TEXT," + COL_U_NAME + " " + "TEXT, " + COL_PASSWORD + " TEXT)";
    private final DatabaseHelper helper;
    private final SQLiteDatabase sq;

    public RegistrationHelper(Context context) {
        helper = new DatabaseHelper(context);
        sq = helper.getWritableDatabase();
    }

    public void insertIntoUsers(ModelRegistration model) {
        ContentValues values = new ContentValues();
        values.put(COL_EMAIL, model.getEmail());
        values.put(COL_NAME, model.getName());
        values.put(COL_PASSWORD, model.getPassWord());
        values.put(COL_PHONE, model.getPhone());
        values.put(COL_U_NAME, model.getuName());
        sq.insert(TABLE_NAME_USERS, null, values);
    }

    public boolean ifUserIsInDb(String uName, String pWord) {
        boolean login = false;
        Cursor cursor = sq.rawQuery("select * from " + TABLE_NAME_USERS + " where " + COL_U_NAME + " = " + uName + " and " + COL_PASSWORD + " = " + pWord, null);
        if (cursor.moveToFirst()) {
            login = true;
        }
        cursor.close();
        return login;
    }
}
