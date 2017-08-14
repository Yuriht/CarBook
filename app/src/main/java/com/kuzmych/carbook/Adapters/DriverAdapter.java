package com.kuzmych.carbook.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kuzmych.carbook.Objects.Driver;
import com.kuzmych.carbook.R;

import java.util.List;

/**
 * Created by Yuri on 13.08.2017.
 * yuri.kuzmych@gmail.com
 */

public class DriverAdapter extends RecyclerView.Adapter<DriverAdapter.ViewHolder>{

	private List<Driver> items;

	class ViewHolder extends RecyclerView.ViewHolder {
		public TextView first_name, second_name;
		public ViewHolder(View itemView) {
			super(itemView);
			first_name = (TextView) itemView.findViewById(R.id.textView_first_name);
			second_name = (TextView) itemView.findViewById(R.id.textView_second_name);
		}
	}

	public DriverAdapter(List<Driver> items) {
		this.items = items;
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext())
				.inflate(R.layout.driver_list_row, parent, false);
		return new ViewHolder(view);
	}

	@Override
	public void onBindViewHolder(ViewHolder holder, int position) {
		Driver item = items.get(position);
		holder.first_name.setText(item.getFirstName());
		holder.second_name.setText(item.getSecondName());
	}

	@Override
	public int getItemCount() {
		return items.size();
	}

	public Driver getItem(int position) {
		return items.get(position);
	}

}