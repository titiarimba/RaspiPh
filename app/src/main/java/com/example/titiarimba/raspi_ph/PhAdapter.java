package com.example.titiarimba.raspi_ph;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class PhAdapter extends RecyclerView.Adapter<PhAdapter.ViewHolder> {

    private List<Ph> dataph;
    private Context context;

    public PhAdapter(List<Ph> dataph, Context context) {
        super();
        this.dataph = dataph;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_ph_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvNilai.setText(dataph.get(position).getNilai());
        holder.tvTime.setText(dataph.get(position).getTime());
        holder.tvDate.setText(dataph.get(position).getDate());
    }

    @Override
    public int getItemCount() {
        return dataph.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvNilai, tvDate, tvTime;

        public ViewHolder(View itemview) {
            super(itemview);
            tvNilai = (TextView) itemview.findViewById(R.id.tv_ph);
            tvTime = (TextView) itemview.findViewById(R.id.tv_time);
            tvDate = (TextView) itemview.findViewById(R.id.tv_date);
        }
    }
}
