package cn.modificator.workinghourslog.history;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import cn.modificator.workinghourslog.R;
import cn.modificator.workinghourslog.data.WorkingLogDao;
import cn.modificator.workinghourslog.data.bean.WorkingHourLog;
import cn.modificator.workinghourslog.utils.DividerItemDecoration;

/**
 * Created by Modificator
 * time: 16/9/16.下午4:37
 * des:create file and achieve model
 */

public class WorkingLogHistory extends Fragment {


    View rootView;
    Context mContext;
    RecyclerView mRecyclerView;
    List<WorkingHourLog> dataList = new ArrayList<>();
    HistoryLogListAdapter adapter = new HistoryLogListAdapter(dataList);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.history_log, null);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        rootView = getView();
        initViews();
    }

    private void initViews() {
        mRecyclerView = findView(R.id.mRecyclerView);
        Toolbar toolbar = findView(R.id.toolbar);
        toolbar.setTitle("工作记录");

        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(mContext,DividerItemDecoration.VERTICAL_LIST));

        loadData();
    }

    private void loadData() {
        dataList.clear();
        dataList.addAll(WorkingLogDao.getAllWorkingLog());
        adapter.notifyDataSetChanged();
    }

    private <T extends View> T findView(int id) {
        return (T) rootView.findViewById(id);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            loadData();
        }
    }

    public static WorkingLogHistory getInstance() {
        return new WorkingLogHistory();
    }
}
