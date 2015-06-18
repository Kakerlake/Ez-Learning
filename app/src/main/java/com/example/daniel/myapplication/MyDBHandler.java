package com.example.daniel.myapplication;

/**
 * Created by Chefsehero on 17.06.2015.
 */
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.Context;
import android.content.ContentValues;

public class MyDBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "kartei.db";
    public static final String TABLE_WORDS = "woerter";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_DEUTSCH = "deutsch";
    public static final String COLUMN_ENGLISH= "english";
    public static final String COLUMN_WERT = "werte";

    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "CREATE TABLE " + TABLE_WORDS + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_DEUTSCH + " TEXT, " + COLUMN_ENGLISH + " TEXT, " + COLUMN_WERT+" INTEGER" +");";
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
        SQLiteDatabase db= getWritableDatabase();
        db.insert(TABLE_WORDS, null, values);
        db.close();
    }
    public void  deleteKarte(String Word) {
        String dbString="Word";
        SQLiteDatabase db =getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_WORDS + "WHERE" + COLUMN_DEUTSCH + "=\"" + dbString + "\"" + " OR WHERE  " + COLUMN_ENGLISH + "=\"" + dbString + "\";");

    }
    public String databaseToString() {
        String dbString="";
        SQLiteDatabase db = getWritableDatabase();
        String query ="SELECT deutsch FROM "+ TABLE_WORDS+ " Where 1";

        //Cursor points to a location in your results
        Cursor  c =db.rawQuery(query, null);

        //Move to the first row in your results
        c.moveToFirst();
    dbString=c.getString(c.getColumnIndex("deutsch"));
       /* while (!c.isAfterLast()) {
            if (c.getString(c.getColumnIndex("deutsch")) != null) {
                dbString += c.getString(c.getColumnIndex("deutsch"));
                dbString += "\n";
            }

            c.moveToNext();
        }*/

        db.close();
        return dbString;
    }

    public String kartenAusgabe(String query, String sprache) {
        String dbString="";
        SQLiteDatabase db = getWritableDatabase();
        //Cursor points to a location in your results
        Cursor  c =db.rawQuery(query, null);

        //Move to the first row in your results

/*
        while (!c.isAfterLast()) {
            if (c.getString(c.getCount()) != null) {
                dbString += c.getString(c.getColumnIndex("deutsch"));
                dbString += "\n";
            }

            c.moveToNext();
        }

*/
        c.moveToFirst();
        dbString = c.getString(c.getColumnIndex(sprache));
        c.close();


        return dbString;
    }

    public void update(String query){

        SQLiteDatabase db= getWritableDatabase();
        db.execSQL(query);
    }

}
