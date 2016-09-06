package cn.modificator.workinghourslog.working;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.modificator.workinghourslog.databinding.WorkingNowPageBinding;

/**
 * Created by Modificator
 * time: 16/9/6.下午9:15
 * des:create file and achieve model
 */

public class WorkingNowPage extends Fragment {

    WorkingNowPageBinding binding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = WorkingNowPageBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initViews();
    }

    private void initViews() {


    }

    public static WorkingNowPage getInstance() {
        WorkingNowPage workingNowPage = new WorkingNowPage();
        return workingNowPage;
    }
}
