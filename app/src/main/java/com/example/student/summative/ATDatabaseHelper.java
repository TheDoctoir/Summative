package com.example.student.summative;

/**
 * Created by Student on 26/05/2016.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class ATDatabaseHelper extends SQLiteOpenHelper
{
    private static final String DB_NAME = "animeReviewDB";
    private static final int DB_VERSION = 1;


    public ATDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
                            int version)
    {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        updateMyDatabase(db, 0, DB_VERSION);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        updateMyDatabase(db, oldVersion, newVersion);

    }

    private void updateMyDatabase(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("CREATE TABLE LIST (_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "ANIME TEXT, " +
                "STUDIO TEXT, " +
                "REVIEW REAL);");

    }

    public void insertElement(SQLiteDatabase db, ContentValues newContent)
    {
        db.insert("LIST", null, newContent);

    }
}
