package cn.modificator.workinghourslog.utils;

import android.content.Context;
import android.util.TypedValue;

import cn.modificator.workinghourslog.R;

/**
 * Created by Modificator
 * time: 16-9-8.下午4:52
 * des:create file and achieve model
 */

public class Utils {

    public static int getActionBarHeight(Context context) {
        int actionBarHeight = 0;
        TypedValue tv = new TypedValue();
        if (context.getTheme().resolveAttribute(android.R.attr.actionBarSize,
                tv, true))
            actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data,
                    context.getResources().getDisplayMetrics());

        if (actionBarHeight == 0
                && context.getTheme().resolveAttribute(R.attr.actionBarSize,
                tv, true)) {
            actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data,
                    context.getResources().getDisplayMetrics());
        }

        return actionBarHeight;
    }
}
