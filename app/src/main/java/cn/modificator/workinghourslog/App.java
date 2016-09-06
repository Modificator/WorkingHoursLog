package cn.modificator.workinghourslog;

import android.app.Application;

import cn.modificator.workinghourslog.data.bean.DBOpenHelper;

/**
 * Created by Modificator
 * time: 16/9/6.下午10:37
 * des:create file and achieve model
 */

public class App extends Application {

    static DBOpenHelper dbOpenHelper;

    @Override
    public void onCreate() {
        super.onCreate();
        dbOpenHelper = new DBOpenHelper(getApplicationContext());

    }

    public static DBOpenHelper getDbOpenHelper() {
        return dbOpenHelper;
    }
}
