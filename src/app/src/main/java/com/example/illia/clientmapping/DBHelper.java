package com.example.illia.clientmapping;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static android.content.ContentValues.TAG;

public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "contactDb";

    public static final String TABLE_USERS = "users";

    public static final String KEY_LOGIN = "login";
    public static final String KEY_PASSWORD = "password ";

    public static final String TABLE_CONTACTS = "contacts";

    public static final String KEY_ID0 = "id0";
    public static final String KEY_FIRSTNAME = "firstname";
    public static final String KEY_LASTNAME = "lastname";
    public static final String KEY_PHONE = "phone";
    public static final String KEY_ADDRESS = "address";
    public static final String KEY_ZAMETKI = "zametki";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_CONTACTS + "(" + KEY_ID0 + " text, " + KEY_FIRSTNAME
                + " text, " + KEY_LASTNAME + " text, " + KEY_PHONE + " text, " + KEY_ADDRESS
                + " text, " + KEY_ZAMETKI + " text" + ");");

        db.execSQL("create table " + TABLE_USERS + "(" + KEY_LOGIN
                + " text, " + KEY_PASSWORD + " text" + ");");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_CONTACTS);

        db.execSQL("drop table if exists " + TABLE_USERS);

        onCreate(db);

    }

    public void addContact(Contact contact) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_ID0, contact.getID0());
        values.put(KEY_FIRSTNAME, contact.getFirstName());
        values.put(KEY_LASTNAME, contact.getLastName());
        values.put(KEY_PHONE, contact.getPhone());
        values.put(KEY_ADDRESS, contact.getAddress());
        values.put(KEY_ZAMETKI, contact.getZametki());

        db.insert(TABLE_CONTACTS, null, values);
        db.close();
    }

    public void addUser(User user) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_LOGIN, user.getLogin());
        values.put(KEY_PASSWORD, user.getPassword());

        db.insert(TABLE_USERS, null, values);
        db.close();
    }

    public void delContact(Contact contact) {

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CONTACTS, KEY_ID0 + " = ? AND " + KEY_PHONE + " = ?", new String[] { String.valueOf(contact.getID0()), String.valueOf(contact.getPhone()) });
        db.close();
    }

    public Contact getContactByName(String firstname, String lastname, String id0) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_CONTACTS, new String[] { KEY_ID0,
                        KEY_FIRSTNAME, KEY_LASTNAME, KEY_PHONE, KEY_ADDRESS,
                        KEY_ZAMETKI }, KEY_FIRSTNAME + " = ? AND " + KEY_LASTNAME + " = ? AND " + KEY_ID0 + " = ?",
                new String[] { String.valueOf(firstname), String.valueOf(lastname), String.valueOf(id0) }, null, null, null, null);

        if (cursor != null &&
            cursor.moveToFirst()) {
            Contact contact = new Contact(
                    cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5));
            return contact;
        }
        return null;
    }

    public Contact getContactByPhone(String phone, String id0) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_CONTACTS, new String[] { KEY_ID0,
                        KEY_FIRSTNAME, KEY_LASTNAME, KEY_PHONE, KEY_ADDRESS,
                        KEY_ZAMETKI }, KEY_PHONE + " = ? AND " + KEY_ID0 + " = ?",
                new String[] { String.valueOf(phone), String.valueOf(id0) }, null, null, null, null);

        if (cursor != null &&
            cursor.moveToFirst()) {
            Contact contact = new Contact(
                    cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5));
            return contact;
        }
        return null;
    }

    public User getUser(String login) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query( TABLE_USERS, new String[] {KEY_LOGIN, KEY_PASSWORD }, KEY_LOGIN + " = ?",
                new String[] { String.valueOf(login) }, null, null, null, null);

        if (cursor != null &&
            cursor.moveToFirst()) {

            User user = new User(cursor.getString(0), cursor.getString(1));

            return user;
        }

        return null;
    }

}