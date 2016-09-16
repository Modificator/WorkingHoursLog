package cn.modificator.workinghourslog.history;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import cn.modificator.workinghourslog.R;
import cn.modificator.workinghourslog.data.bean.WorkingHourLog;
import cn.modificator.workinghourslog.databinding.HistoryLogItemBinding;

/**
 * Created by Modificator
 * time: 16/9/16.下午4:47
 * des:create file and achieve model
 */

public class HistoryLogListAdapter extends RecyclerView.Adapter<HistoryLogListAdapter.HistoryLogViewHolder> {
    List<WorkingHourLog> dataList;

    public HistoryLogListAdapter(List<WorkingHourLog> dataList) {
        this.dataList = dataList;
    }

    @Override
    public HistoryLogViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new HistoryLogViewHolder(
                HistoryLogItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false)
                        .getRoot()
        );
    }

    @Override
    public void onBindViewHolder(HistoryLogViewHolder holder, int position) {
        HistoryLogItemBinding binding = DataBindingUtil.getBinding(holder.itemView);
        binding.setLog(dataList.get(position));
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    protected static class HistoryLogViewHolder extends RecyclerView.ViewHolder {

        public HistoryLogViewHolder(View itemView) {
            super(itemView);
        }
    }
}
