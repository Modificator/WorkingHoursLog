package cn.modificator.workinghourslog.data;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import cn.modificator.workinghourslog.App;
import cn.modificator.workinghourslog.data.bean.WorkingHourLog;

/**
 * Created by Modificator
 * time: 16/9/6.下午10:43
 * des:create file and achieve model
 */

public class WorkingLogDao {

    final static String TABLE_NAME = "worklog";
    final static String COLUMN_ID = "_id";
    final static String COLUMN_PROJECT_NAME = "project_name";
    final static String COLUMN_GROUP_NAME = "group_name";
    final static String COLUMN_START_TIME = "start_time";
    final static String COLUMN_END_TIME = "end_time";
    final static String COLUMN_WORK_COUNT = "work_count";
    final static String COLUMN_CREATE_TIME = "ctime";

    public static void insert(WorkingHourLog log) {
        SQLiteDatabase database = App.getDbOpenHelper().getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_PROJECT_NAME, log.getPjtName());
        contentValues.put(COLUMN_GROUP_NAME, log.getWorkingGroup());
        contentValues.put(COLUMN_START_TIME, log.getStartTime());
        contentValues.put(COLUMN_END_TIME, log.getEndTime());
        contentValues.put(COLUMN_WORK_COUNT, log.getEndTime());
        database.insert(TABLE_NAME, "", contentValues);
        database.close();
    }

    public static void update(WorkingHourLog log) {
        SQLiteDatabase database = App.getDbOpenHelper().getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_PROJECT_NAME, log.getPjtName());
        contentValues.put(COLUMN_GROUP_NAME, log.getWorkingGroup());
        contentValues.put(COLUMN_START_TIME, log.getStartTime());
        contentValues.put(COLUMN_END_TIME, log.getEndTime());
        contentValues.put(COLUMN_WORK_COUNT, log.getEndTime());
        database.update(TABLE_NAME, contentValues, "_id=?", new String[]{log.getId() + ""});
    }

    public static List<WorkingHourLog> getAllWorkingLog() {
        List<WorkingHourLog> logList = new ArrayList<>();
        SQLiteDatabase database = App.getDbOpenHelper().getReadableDatabase();
        Cursor cursor = database.rawQuery("select * from " + TABLE_NAME + " order by _ID DESC", new String[]{});
        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                WorkingHourLog log = new WorkingHourLog();
                log.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)));
                log.setPjtName(cursor.getString(cursor.getColumnIndex(COLUMN_PROJECT_NAME)));
                log.setWorkingGroup(cursor.getString(cursor.getColumnIndex(COLUMN_GROUP_NAME)));
                log.setStartTime(cursor.getString(cursor.getColumnIndex(COLUMN_START_TIME)));
                log.setEndTime(cursor.getString(cursor.getColumnIndex(COLUMN_END_TIME)));
                log.setWorkCount(cursor.getString(cursor.getColumnIndex(COLUMN_WORK_COUNT)));
                logList.add(log);
            }
        }
        if (cursor != null) {
            cursor.close();
        }

        return logList;
    }

}
