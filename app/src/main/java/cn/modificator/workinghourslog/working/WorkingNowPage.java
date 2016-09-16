package cn.modificator.workinghourslog.working;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.databinding.ObservableBoolean;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import cn.modificator.workinghourslog.R;
import cn.modificator.workinghourslog.data.WorkingLogDao;
import cn.modificator.workinghourslog.data.bean.WorkingHourLog;
import cn.modificator.workinghourslog.databinding.WorkingNowPageBinding;

/**
 * Created by Modificator
 * time: 16/9/6.下午9:15
 * des:create file and achieve model
 */

public class WorkingNowPage extends Fragment {

    WorkingNowPageBinding binding;

    FloatingActionButton btnAddLog;

    Activity mContext;

    //页面状态,表示是否存在没有结束的任务
    ObservableBoolean hasUnfinishWork = new ObservableBoolean(false);
    //当前工作中的日志
    WorkingHourLog log;

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = WorkingNowPageBinding.inflate(inflater);
        binding.setHasUnfinishWork(hasUnfinishWork);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initViews();
    }

    private void initViews() {
        Toolbar toolbar = (Toolbar) getView().findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);

        btnAddLog = binding.fab;
        btnAddLog.setOnClickListener(v -> {

            if (hasUnfinishWork.get()) {
                log.setEndTime(dateFormat.format(new Date()));

                log.setWorkCount(countWorkTime());
                WorkingLogDao.update(log);
                hasUnfinishWork.set(false);
                Toast.makeText(mContext, "已结束当前工作", Toast.LENGTH_SHORT).show();
                return;
            }
            ActivityOptionsCompat optionsCompat = ActivityOptionsCompat
                    .makeSceneTransitionAnimation(mContext, btnAddLog, "addLog");
            Intent intent = new Intent(mContext, AddWorkingLog.class);
            ActivityCompat.startActivity(mContext, intent, optionsCompat.toBundle());
        });
    }

    private String countWorkTime() {
        Date startTime;
        Date endTime;
        try {
            startTime = dateFormat.parse(log.getStartTime());
            endTime = dateFormat.parse(log.getEndTime());
        } catch (ParseException e) {
            e.printStackTrace();
            return "0";
        }
        long count = endTime.getTime() - startTime.getTime();
        long second = count / 1000;
        int hour = (int) (second / 3600);
        int minute = (int) ((second - hour * 3600) / 60);

        StringBuffer result = new StringBuffer();
        if (hour > 0) result.append(hour).append("小时");
        result.append(minute).append("分钟");
//        result.append(second-hour*3600-minute*60).append("秒");

        return result.toString();
    }

    private void loadWorking() {
        log = WorkingLogDao.loadUnfinishWork();
        hasUnfinishWork.set(log != null);
        if (hasUnfinishWork.get()) {
            binding.setData(log);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        loadWorking();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) loadWorking();
    }

    public static WorkingNowPage getInstance() {
        WorkingNowPage workingNowPage = new WorkingNowPage();
        return workingNowPage;
    }
}
