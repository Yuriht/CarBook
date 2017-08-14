package com.kuzmych.carbook.Fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.kuzmych.carbook.Adapters.VehicleAdapter;
import com.kuzmych.carbook.Classes.DividerItemDecoration;
import com.kuzmych.carbook.Classes.RecyclerClickListener;
import com.kuzmych.carbook.MainActivity;
import com.kuzmych.carbook.Objects.Vehicle;
import com.kuzmych.carbook.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yuri on 14.08.2017.
 * yuri.kuzmych@gmail.com
 */

public class MainFragment extends Fragment {

	private MainActivity mainActivity;
	private View this_view;

	private List<Vehicle> vehicleList = new ArrayList<>();
	private RecyclerView recyclerView;
	private VehicleAdapter mAdapter;

	public MainFragment() {
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		mainActivity = (MainActivity) activity;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		this_view = inflater.inflate(R.layout.fragment_main, container, false);

		//Car List
		recyclerView = (RecyclerView) this_view.findViewById(R.id.recycler_view);
		mAdapter = new VehicleAdapter(vehicleList);
		RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(mainActivity);
		recyclerView.setLayoutManager(mLayoutManager);
		recyclerView.addItemDecoration(new DividerItemDecoration(mainActivity, LinearLayoutManager.VERTICAL));
		recyclerView.setItemAnimator(new DefaultItemAnimator());
		recyclerView.setAdapter(mAdapter);
		UpdateList();

		//Car Item ClickListener
		recyclerView.addOnItemTouchListener(new RecyclerClickListener(mainActivity, recyclerView, new RecyclerClickListener.ClickListener() {
			@Override
			public void onClick(View view, int position) {
				final Vehicle item = vehicleList.get(position);
				new AlertDialog.Builder(mainActivity)
						.setTitle(R.string.action_menu_tittle)
						.setItems(R.array.car_actions_array, new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int which) {
								// The 'which' argument contains the index position of the selected item
								switch (which){
									case 0 : 	//View
										mainActivity.ShowVehicleFragment(VehicleFragment.Action.View, item.getId());
										break;
									case 1 : 	//Edit
										mainActivity.ShowVehicleFragment(VehicleFragment.Action.Edit, item.getId());
										break;
									case 2 : 	//Delete
										mainActivity.db.deleteVehicle(item);
										UpdateList();
										Toast.makeText(mainActivity, R.string.toast_item_deleted, Toast.LENGTH_SHORT).show();
										break;
								}
							}
						}).create().show();
			}

			@Override
			public void onLongClick(View view, int position) {

			}
		}));

		//AddCar Button
		Button b_add_car = (Button) this_view.findViewById(R.id.button_add_car);
		b_add_car.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				mainActivity.ShowVehicleFragment(VehicleFragment.Action.Add, 0);
			}
		});

		return this_view;
	}

	//Reload List
	public void UpdateList() {
		vehicleList.clear();
		vehicleList.addAll(mainActivity.db.getAllVehicles());
		mAdapter.notifyDataSetChanged();
	}
}
