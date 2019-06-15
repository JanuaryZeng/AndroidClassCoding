package cn.edu.nuc.librarydemo_94240;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDataBase extends SQLiteOpenHelper {

    final String CREATE_TABLE_SQL = "create table library_tb(_id integer primary key autoincrement," +
            "name text,author text,count text);";
    public MyDataBase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_SQL);
        db.execSQL("insert into library_tb values(null,?,?,?)", new String[]{"白夜行", "村上春树", "100"});
        db.execSQL("insert into library_tb values(null,?,?,?)", new String[]{"时间简史", "霍金", "50"});
        db.execSQL("insert into library_tb values(null,?,?,?)", new String[]{"百年孤独", "匿名", "50"});
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
