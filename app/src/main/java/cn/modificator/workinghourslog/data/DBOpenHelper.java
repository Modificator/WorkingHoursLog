package cn.modificator.workinghourslog.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Modificator
 * time: 16/9/6.下午10:22
 * des:create file and achieve model
 */

public class DBOpenHelper extends SQLiteOpenHelper {
    static final int DB_VERSION = 1;
    static final String DB_NAME = "workinglog.db";

    public DBOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE worklog(" +
                "_id integer PRIMARY KEY autoincrement," +
                "project_name text," +
                "group_name TEXT," +
                "start_time TEXT," +
                "end_time TEXT," +
                "work_count TEXT," +
                "work_desc TEXT," +
                "ctime TIMESTAMP default CURRENT_TIMESTAMP" +
                ")";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
