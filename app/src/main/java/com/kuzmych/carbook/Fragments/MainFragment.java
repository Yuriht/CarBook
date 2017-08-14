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

import com.kuzmych.carbook.Adapters.DriverAdapter;
import com.kuzmych.carbook.Adapters.VehicleAdapter;
import com.kuzmych.carbook.Classes.DividerItemDecoration;
import com.kuzmych.carbook.Classes.RecyclerClickListener;
import com.kuzmych.carbook.MainActivity;
import com.kuzmych.carbook.Objects.Driver;
import com.kuzmych.carbook.Objects.Vehicle;
import com.kuzmych.carbook.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yuri on 14.08.2017.
 * yuri.kuzmych@gmail.com
 */

public class MainFragment extends Fragment implements View.OnClickListener{

	private MainActivity mainActivity;
	private View this_view;

	private enum ListType {Cars, Drivers}
	private ListType listType = ListType.Cars;

	private List<Vehicle> vehicleList = new ArrayList<>();
	private List<Driver> driverList = new ArrayList<>();
	private RecyclerView recyclerView;
	private VehicleAdapter vehicleAdapter;
	private DriverAdapter driverAdapter;

	private Button button_Cars;
	private Button button_Drivers;
	private Button b_add_car;

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

		//List
		recyclerView = (RecyclerView) this_view.findViewById(R.id.recycler_view);
		vehicleAdapter = new VehicleAdapter(vehicleList);
		driverAdapter = new DriverAdapter(driverList);
		RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(mainActivity);
		recyclerView.setLayoutManager(mLayoutManager);
		recyclerView.addItemDecoration(new DividerItemDecoration(mainActivity, LinearLayoutManager.VERTICAL));
		recyclerView.setItemAnimator(new DefaultItemAnimator());
		ListItemClickListener();
		UpdateList();

		//Buttons
		b_add_car = (Button) this_view.findViewById(R.id.button_add_new);
		button_Cars = (Button) this_view.findViewById(R.id.button_Cars);
		button_Drivers = (Button) this_view.findViewById(R.id.button_Drivers);
		b_add_car.setOnClickListener(this);
		button_Cars.setOnClickListener(this);
		button_Drivers.setOnClickListener(this);
		DrawButtons();

		return this_view;
	}

	//Buttons click
	@Override
	public void onClick(View v) {
		switch (v.getId()) {

			case R.id.button_add_new:
				if (listType == ListType.Cars) { mainActivity.ShowVehicleFragment(VehicleFragment.Action.Add, 0); }
				if (listType == ListType.Drivers) { mainActivity.ShowDriverFragment(DriverFragment.Action.Add, 0); }
				break;

			case R.id.button_Cars:
				listType = ListType.Cars;
				DrawButtons();
				UpdateList();
				break;

			case R.id.button_Drivers:
				listType = ListType.Drivers;
				DrawButtons();
				UpdateList();
				break;

			default: break;
		}
	}


	//Reload List
	public void UpdateList() {
		switch (listType) {
			case Cars:
				vehicleList.clear();
				vehicleList.addAll(mainActivity.db.getAllVehicles());
				if (recyclerView.getAdapter() != vehicleAdapter) { recyclerView.setAdapter(vehicleAdapter); }
				else vehicleAdapter.notifyDataSetChanged();
				break;
			case Drivers:
				driverList.clear();
				driverList.addAll(mainActivity.db.getAllDrivers());
				if (recyclerView.getAdapter() != driverAdapter) { recyclerView.setAdapter(driverAdapter); }
				else driverAdapter.notifyDataSetChanged();
				break;
		}

	}

	//List items click
	private void ListItemClickListener() {
		recyclerView.addOnItemTouchListener(new RecyclerClickListener(mainActivity, recyclerView, new RecyclerClickListener.ClickListener() {
			@Override
			public void onClick(View view, int position) {
				final int pos = position;
				new AlertDialog.Builder(mainActivity)
						.setTitle(R.string.action_menu_tittle)
						.setItems(R.array.list_actions_array, new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int which) {
								// The 'which' argument contains the index position of the selected item
								switch (which){
									case 0 : 	//View
										if (listType == ListType.Cars) { mainActivity.ShowVehicleFragment(VehicleFragment.Action.View, vehicleList.get(pos).getId()); }
										if (listType == ListType.Drivers) { mainActivity.ShowDriverFragment(DriverFragment.Action.View, driverList.get(pos).getId()); }
										break;
									case 1 : 	//Edit
										if (listType == ListType.Cars) { mainActivity.ShowVehicleFragment(VehicleFragment.Action.Edit, vehicleList.get(pos).getId()); }
										if (listType == ListType.Drivers) { mainActivity.ShowDriverFragment(DriverFragment.Action.Edit, driverList.get(pos).getId()); }
										break;
									case 2 : 	//Delete
										if (listType == ListType.Cars) { mainActivity.db.deleteVehicle(vehicleList.get(pos)); }
										if (listType == ListType.Drivers) { mainActivity.db.deleteDriver(driverList.get(pos)); }
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
	}

	//Draw Top Togle Buttons
	private void DrawButtons() {
		switch (listType) {
			case Cars:
				button_Cars.setBackgroundColor(getResources().getColor(R.color.togledButtonBG));
				button_Cars.setTextColor(getResources().getColor(R.color.togledButtonText));
				button_Drivers.setBackgroundColor(getResources().getColor(R.color.untogledButtonBG));
				button_Drivers.setTextColor(getResources().getColor(R.color.untogledButtonText));
				break;
			case Drivers:
				button_Cars.setBackgroundColor(getResources().getColor(R.color.untogledButtonBG));
				button_Cars.setTextColor(getResources().getColor(R.color.untogledButtonText));
				button_Drivers.setBackgroundColor(getResources().getColor(R.color.togledButtonBG));
				button_Drivers.setTextColor(getResources().getColor(R.color.togledButtonText));
				break;
		}
	}
}
