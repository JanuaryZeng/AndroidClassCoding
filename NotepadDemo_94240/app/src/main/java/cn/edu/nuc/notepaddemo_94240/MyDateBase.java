package cn.edu.nuc.notepaddemo_94240;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MyDateBase extends SQLiteOpenHelper {

    final String CREATE_TABLE_SQL = "create table notepad_tb(_id integer primary key autoincrement," +
            "title,context,date)";
    final String TAG = "MuDateBase";

    public MyDateBase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i(TAG, "date: "+"------------"+oldVersion+"-----------");
    }
}
