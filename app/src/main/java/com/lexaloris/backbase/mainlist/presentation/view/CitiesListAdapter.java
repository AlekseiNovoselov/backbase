package com.lexaloris.backbase.mainlist.presentation.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.lexaloris.backbase.R;
import com.lexaloris.backbase.mainlist.entities.Cities;
import com.lexaloris.backbase.mainlist.entities.City;
import com.lexaloris.backbase.mainlist.entities.Coordination;

public class CitiesListAdapter extends RecyclerView.Adapter<CitiesListAdapter.CityCellViewHolder> {
    private final OnItemClickListener listener;
    private Cities data = new Cities();

    public CitiesListAdapter(OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public CityCellViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.city_item_view, parent, false);
        return new CityCellViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CityCellViewHolder holder, int position) {
        holder.bind(data.get(position), listener);
    }

    public void update(Cities data) {
        this.data = data;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class CityCellViewHolder extends RecyclerView.ViewHolder {
        private TextView titleView;
        private TextView subtitleView;
        private Button aboutButton;

        CityCellViewHolder(View view) {
            super(view);
            titleView = view.findViewById(R.id.city_cell_title);
            subtitleView = view.findViewById(R.id.city_cell_subtitle);
            aboutButton = view.findViewById(R.id.city_cell_button_about);
        }

        private void bind(final City city, final OnItemClickListener listener) {
            Coordination coordination = city.getCoordination();
            String title = city.getName() + " " + city.getCountryName();
            String subtitle = coordination.getLon() + " " + coordination.getLat();
            titleView.setText(title);
            subtitleView.setText(subtitle);

            itemView.setOnClickListener(v -> listener.onItemClick(city));
            aboutButton.setOnClickListener(v -> listener.onButtonClick());
        }
    }
}
