package com.example.crudoperations_sqlite.mydbhandler;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBHandler extends SQLiteOpenHelper {

    public static final String DB_NAME = "fruit_db";
    public static final int DB_VERSION = 1;
    public static final String TABLE_NAME = "fruit_mst";
    public static final String COL_Fruit_ID = "fruit_id";
    public static final String COL_Fruit_Name = "fruit_name";
    public static final String COL_Fruit_Prize = "fruit_prize";

    public MyDBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " ( "
                + COL_Fruit_ID + " INTEGER PRIMARY KEY AUTOINCREMENT , "
                + COL_Fruit_Name + " TEXT UNIQUE NOT NULL, "
                + COL_Fruit_Prize + " FLOAT NOT NULL "
                + ")";

        sqLiteDatabase.execSQL(createTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
