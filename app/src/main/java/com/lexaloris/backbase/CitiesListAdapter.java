package com.lexaloris.backbase;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class CitiesListAdapter extends RecyclerView.Adapter<CitiesListAdapter.MyViewHolder> {
    private ArrayList<String> data = new ArrayList<>();

    @NonNull
    @Override
    public CitiesListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.city_item_view, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String model = data.get(position);
        holder.textView.setText(model);
    }

    public void update(ArrayList<String> data) {
        this.data = data;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;

        MyViewHolder(View view) {
            super(view);
            textView = view.findViewById(R.id.cityNameView);
        }
    }
}
