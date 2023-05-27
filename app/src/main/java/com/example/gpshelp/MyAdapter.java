package com.example.gpshelp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gpshelp.activitys.CallinfoActivity;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    ArrayList<Call> list;

    public MyAdapter(Context context, ArrayList<Call> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.calls_list_item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Call call = list.get(position);
        holder.address.setText(call.getAddress());
        holder.info.setText(call.getInfo());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CallinfoActivity.class);
                intent.putExtra("address", call.getAddress());
                intent.putExtra("info", call.getInfo());
                intent.putExtra("id", call.getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView address, info;

        public MyViewHolder (@NonNull View itemView) {
            super(itemView);

            address = itemView.findViewById(R.id.address);
            info = itemView.findViewById(R.id.info);
        }
    }
}
