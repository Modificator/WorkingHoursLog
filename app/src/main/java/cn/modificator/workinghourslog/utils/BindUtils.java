package cn.modificator.workinghourslog.utils;

import android.databinding.BindingAdapter;
import android.databinding.ObservableBoolean;
import android.view.View;

/**
 * Created by Modificator
 * time: 16/9/16.下午4:14
 * des:create file and achieve model
 */

public class BindUtils {

    @BindingAdapter("visible")
    public static void  viewVisible(View v,ObservableBoolean visible){
        v.setVisibility(visible.get()?View.VISIBLE:View.GONE);
    }

}
