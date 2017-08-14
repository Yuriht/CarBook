package com.kuzmych.carbook.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kuzmych.carbook.Objects.Vehicle;
import com.kuzmych.carbook.R;

import java.util.List;

/**
 * Created by Yuri on 13.08.2017.
 * yuri.kuzmych@gmail.com
 */

public class VehicleAdapter extends RecyclerView.Adapter<VehicleAdapter.ViewHolder>{

	private List<Vehicle> items;

	class ViewHolder extends RecyclerView.ViewHolder {
		public TextView brand, model;
		public ViewHolder(View itemView) {
			super(itemView);
			brand = (TextView) itemView.findViewById(R.id.textView_brand);
			model = (TextView) itemView.findViewById(R.id.textView_model);
		}
	}

	public VehicleAdapter(List<Vehicle> items) {
		this.items = items;
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext())
				.inflate(R.layout.car_list_row, parent, false);
		return new ViewHolder(view);
	}

	@Override
	public void onBindViewHolder(ViewHolder holder, int position) {
		Vehicle item = items.get(position);
		holder.brand.setText(item.getBrand());
		holder.model.setText(item.getModel());
	}

	@Override
	public int getItemCount() {
		return items.size();
	}

	public Vehicle getItem(int position) {
		return items.get(position);
	}

}