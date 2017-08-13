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
 */

public class VehicleAdapter extends RecyclerView.Adapter<VehicleAdapter.MyViewHolder> {

	private List<Vehicle> vehicleList;

	public class MyViewHolder extends RecyclerView.ViewHolder {
		public TextView brand, model;

		public MyViewHolder(View view) {
			super(view);
			brand = (TextView) view.findViewById(R.id.textView_brand);
			model = (TextView) view.findViewById(R.id.textView_model);
		}
	}


	public VehicleAdapter(List<Vehicle> vehicleList) {
		this.vehicleList = vehicleList;
	}

	@Override
	public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View itemView = LayoutInflater.from(parent.getContext())
				.inflate(R.layout.car_list_row, parent, false);

		return new MyViewHolder(itemView);
	}

	@Override
	public void onBindViewHolder(MyViewHolder holder, int position) {
		Vehicle vehicle = vehicleList.get(position);
		holder.brand.setText(vehicle.getBrand());
		holder.model.setText(vehicle.getModel());
	}

	@Override
	public int getItemCount() {
		return vehicleList.size();
	}

}