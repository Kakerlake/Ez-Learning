package com.example.daniel.myapplication;

/**
 * Created by Chefsehero on 17.06.2015.
 */
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.Context;
import android.content.ContentValues;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class MyDBHandlerStats extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final int LANGZEITGEDAECHTNIS = 20;
    private static final int KURZZEITGEDAECHTNIS = 10;

    private static final String DATABASE_NAME = "kartei.db";
    public static final String TABLE_WORDS = "woerter";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_DEUTSCH = "deutsch";
    public static final String COLUMN_ENGLISH = "english";
    public static final String COLUMN_WERT = "werte";

   public ArrayList<String> results = new ArrayList<String>();

    public MyDBHandlerStats(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "CREATE TABLE " + TABLE_WORDS + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_DEUTSCH + " TEXT, " + COLUMN_ENGLISH + " TEXT, " + COLUMN_WERT + " INTEGER" + ");";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_WORDS);
        onCreate(db);
    }

    public void addWords(KarteiKarte karte) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_DEUTSCH, karte.getDeutsch());
        values.put(COLUMN_ENGLISH, karte.getEnglisch());
        values.put(COLUMN_WERT, karte.getWert());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_WORDS, null, values);
        db.close();
    }

    public void deleteKarte(String Word) {
        String dbString = "Word";
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_WORDS + "WHERE" + COLUMN_DEUTSCH + "=\"" + dbString + "\"" + " OR WHERE  " + COLUMN_ENGLISH + "=\"" + dbString + "\";");

    }

    public String maxToString() {
        String dbMaxString;
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT english FROM woerter ORDER BY werte DESC limit 1 ";
        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();
        dbMaxString = c.getString(c.getColumnIndex("english"));
        db.close();
        return dbMaxString;
    }


    public int lZeitToInt() {

        SQLiteDatabase db = getWritableDatabase();
        String count = "SELECT id FROM " + TABLE_WORDS + " WHERE " + COLUMN_WERT + " >= " + LANGZEITGEDAECHTNIS;
        Cursor c = db.rawQuery(count, null);
        c.moveToFirst();
        int dbLZeitAnzahl = c.getCount();
        db.close();
        return dbLZeitAnzahl;
    }

    public int kZeitToInt() {

        SQLiteDatabase db = getWritableDatabase();
        String count = "SELECT id FROM " + TABLE_WORDS + " WHERE " + COLUMN_WERT + " >= " + KURZZEITGEDAECHTNIS;
        Cursor c = db.rawQuery(count, null);
        c.moveToFirst();
        int dbKZeitAnzahl = c.getCount();
        db.close();
        return dbKZeitAnzahl;
    }

    public int nGelerntToInt() {

        SQLiteDatabase db = getWritableDatabase();
        String count = "SELECT id FROM " + TABLE_WORDS + " WHERE " + COLUMN_WERT + " == " + 0;
        Cursor c = db.rawQuery(count, null);
        c.moveToFirst();
        int nGelerntAnzahl = c.getCount();
        db.close();
        return nGelerntAnzahl;
    }

    public int GelerntToInt() {

        SQLiteDatabase db = getWritableDatabase();
        String count = "SELECT id FROM " + TABLE_WORDS + " WHERE " + COLUMN_WERT + " != " + 0;
        Cursor c = db.rawQuery(count, null);
        c.moveToFirst();
        int GelerntAnzahl = c.getCount();
        db.close();
        return GelerntAnzahl;
    }

    public int totalToInt() {

        SQLiteDatabase db = getWritableDatabase();
        String count = "SELECT id FROM " + TABLE_WORDS;
        Cursor c = db.rawQuery(count, null);
        c.moveToFirst();
        int totalAnzahl = c.getCount();
        db.close();
        return totalAnzahl;
    }

    public ArrayList loeschenAusgabe() {
        SQLiteDatabase db = getWritableDatabase();
        String count = "SELECT deutsch, english from woerter";
        Cursor c = db.rawQuery(count, null);
        if (c.moveToFirst()) {
            do {
                String deutsch = c.getString(c.getColumnIndex("deutsch"));
                String english = c.getString(c.getColumnIndex("english"));
                results.add("Deutsch: " + deutsch + " | English: " + english);
            } while (c.moveToNext());
        }
        return results;
    }
}