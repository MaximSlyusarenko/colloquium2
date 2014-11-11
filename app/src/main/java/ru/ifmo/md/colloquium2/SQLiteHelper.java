package ru.ifmo.md.colloquium2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME =  "dataBase";
    public static final String TABLE = "mytable";
    public static final String COLUMN_ID = "_id";
    public static final String CANDIDATE_NAME = "candidate";
    public static final String CANDIDATE_VOTES = "votes";
    public static final int DATABASE_VERSION = 1;

    public static final String CREATE_BASE = "create table " + TABLE + "("
            + COLUMN_ID + " integer primary key autoincrement, "
            + CANDIDATE_NAME + " text not null, " + CANDIDATE_VOTES + " text not null, " + ");";

    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase dataBase) {
        dataBase.execSQL(CREATE_BASE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase dataBase, int oldVersion, int newVersion) {
        dataBase.execSQL("drop table if exists " + TABLE);
        onCreate(dataBase);
    }
}
