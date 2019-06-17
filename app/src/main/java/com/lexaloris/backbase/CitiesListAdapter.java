package com.lexaloris.backbase;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lexaloris.backbase.model.Cities;
import com.lexaloris.backbase.model.City;
import com.lexaloris.backbase.model.Coordination;

public class CitiesListAdapter extends RecyclerView.Adapter<CitiesListAdapter.MyViewHolder> {
    private Cities data = new Cities();

    @NonNull
    @Override
    public CitiesListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.city_item_view, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        City city = data.get(position);
        Coordination coordination = city.getCoordination();

        String title = city.getName() + " " + city.getCountryName();
        String subtitle = coordination.getLon() + " " + coordination.getLat();
        holder.titleView.setText(title);
        holder.subtitleView.setText(subtitle);
    }

    public void update(Cities data) {
        this.data = data;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView titleView;
        private TextView subtitleView;

        MyViewHolder(View view) {
            super(view);
            titleView = view.findViewById(R.id.city_cell_title);
            subtitleView = view.findViewById(R.id.city_cell_subtitle);
        }
    }
}
