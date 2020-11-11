package com.example.test;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class WeatherInfoAdapter extends RecyclerView.Adapter<WeatherInfoAdapter.WeatherViewHolder> {


    LayoutInflater inflater;
    private List<WeatherInfo> weatherList;
    View view;

    public WeatherInfoAdapter(Context c, List<WeatherInfo> weatherList) {
        this.inflater=LayoutInflater.from(c);
        this.weatherList=weatherList;

    }

    @NonNull
    @Override
    public WeatherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

         view= inflater.inflate(R.layout.weather_layout, parent, false);

        return new WeatherViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull WeatherViewHolder holder, int position) {
        WeatherInfo weather =weatherList.get(position);
        holder.temperature.setText(weather.getTemperature());
        holder.cloudCoverage.setText(weather.getCloudCoverage());
        holder.validTime.setText(weather.getValidTime());

    }

    @Override
    public int getItemCount() {
        return weatherList.size();
    }

    public class WeatherViewHolder extends RecyclerView.ViewHolder {


        TextView temperature, cloudCoverage, validTime;
        public WeatherViewHolder(@NonNull View itemView) {
            super(itemView);
            temperature =itemView.findViewById(R.id.temperature);
            cloudCoverage=itemView.findViewById(R.id.cloudCoverage);
            validTime=itemView.findViewById(R.id.validTime);
        }
    }
}
