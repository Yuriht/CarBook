package com.kuzmych.carbook.Fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.kuzmych.carbook.DB.SQLiteDatabaseHandler;
import com.kuzmych.carbook.MainActivity;
import com.kuzmych.carbook.Objects.Vehicle;
import com.kuzmych.carbook.R;

/**
 * Created by Yuri on 14.08.2017.
 * yuri.kuzmych@gmail.com
 */

public class VehicleFragment extends Fragment {

	public static String TAG = "TAG_VehicleFragment";
	public final static String ACTION = "ACTION";
	public final static String ITEM_ID = "ITEM_ID";

	public enum Action {Add, View, Edit};

	private MainActivity mainActivity;
	private View this_view;
	private SQLiteDatabaseHandler db;
	private Action action;
	private int vehicle_id;

	//ui elements
	private TextView text_title;
	private EditText editText_brand;
	private EditText editText_model;
	private EditText editText_year;
	private Spinner spinner_category;
	private Spinner spinner_transmission;
	private Spinner spinner_drive_wheel;
	private Spinner spinner_engine_type;
	private EditText editText_engine_power;
	private EditText editText_registration_number;
	private Button button_cancel;
	private Button button_save;

	public VehicleFragment() {
	}

	//Static method to pass data for this fragment
	public static VehicleFragment newInstance(Action action, int vehicle_id){
		VehicleFragment vehicleFragment = new VehicleFragment();
		Bundle args = new Bundle();
		args.putSerializable(ACTION, action);
		args.putInt(ITEM_ID, vehicle_id);
		vehicleFragment.setArguments(args);
		return vehicleFragment;
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		mainActivity = (MainActivity) activity;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		action = (Action) getArguments().get(ACTION);
		vehicle_id = getArguments().getInt(ITEM_ID);

		this_view = inflater.inflate(R.layout.fragment_vehicle, container, false);

		text_title = (TextView) this_view.findViewById(R.id.textView_Title);
		editText_brand = (EditText) this_view.findViewById(R.id.editText_brand);
		editText_model = (EditText) this_view.findViewById(R.id.editText_model);
		editText_year = (EditText) this_view.findViewById(R.id.editText_year);
		spinner_category = (Spinner) this_view.findViewById(R.id.spinner_category);
		spinner_transmission = (Spinner) this_view.findViewById(R.id.spinner_transmission);
		spinner_drive_wheel = (Spinner) this_view.findViewById(R.id.spinner_drive_wheel);
		spinner_engine_type = (Spinner) this_view.findViewById(R.id.spinner_engine_type);
		editText_engine_power = (EditText) this_view.findViewById(R.id.editText_engine_power);
		editText_registration_number = (EditText) this_view.findViewById(R.id.editText_registration_number);
		button_cancel = (Button) this_view.findViewById(R.id.button_cancel);
		button_save = (Button) this_view.findViewById(R.id.button_save);

		switch (action){
			case Add:
				text_title.setText(R.string.str_add_car);
				break;

			case View:
				//Set UI to read only
				text_title.setText(R.string.str_car);
				editText_brand.setEnabled(false);
				editText_model.setEnabled(false);
				editText_year.setEnabled(false);
				spinner_category.setEnabled(false);
				spinner_transmission.setEnabled(false);
				spinner_drive_wheel.setEnabled(false);
				spinner_engine_type.setEnabled(false);
				editText_engine_power.setEnabled(false);
				editText_registration_number.setEnabled(false);
				button_save.setVisibility(View.INVISIBLE);
				GetVehicleData();
				break;

			case Edit:
				text_title.setText(R.string.str_edit_car);
				GetVehicleData();
				break;
		}

		button_cancel.setOnClickListener(onClick_Cancel);
		button_save.setOnClickListener(onClick_Save);

		return this_view;
	}

	private View.OnClickListener onClick_Cancel = new View.OnClickListener() {
		public void onClick(View v) {
			getActivity().getSupportFragmentManager().popBackStack();
		}
	};

	private View.OnClickListener onClick_Save = new View.OnClickListener() {
		public void onClick(View v) {
			//check errors
			if (GetErrors() == true) {
				Toast.makeText(mainActivity, "All fields must contain text", Toast.LENGTH_LONG).show();
				return;
			}

			//init strings
			String brandStr = editText_brand.getText().toString();
			String modelStr = editText_model.getText().toString();
			int yearStr = Integer.parseInt(editText_year.getText().toString());
			String categoryStr = String.valueOf(spinner_category.getSelectedItemPosition());
			String transmissionStr = String.valueOf(spinner_transmission.getSelectedItemPosition());
			String drive_wheelStr = String.valueOf(spinner_drive_wheel.getSelectedItemPosition());
			String engine_typeStr = String.valueOf(spinner_engine_type.getSelectedItemPosition());
			int engine_powerStr = Integer.parseInt(editText_engine_power.getText().toString());
			String registration_numberStr = editText_registration_number.getText().toString();

			//save
			Vehicle vehicle = new Vehicle(vehicle_id, brandStr, modelStr, yearStr, categoryStr, transmissionStr, drive_wheelStr, engine_typeStr, engine_powerStr, registration_numberStr);
			switch (action){
				case Add:
					mainActivity.db.addVehicle(vehicle);
					Toast.makeText(mainActivity, R.string.toast_item_inserted, Toast.LENGTH_SHORT).show();
					break;
				case Edit:
					mainActivity.db.updateVehicle(vehicle);
					Toast.makeText(mainActivity, R.string.toast_item_edited, Toast.LENGTH_SHORT).show();
					break;
			}
			mainActivity.UpdateList();
			getActivity().getSupportFragmentManager().popBackStack();
		}
	};

	private boolean GetErrors() {
		return 	!(editText_brand.length()>0 &&
		editText_model.length()>0 &&
		editText_year.length()>0 &&
		spinner_category.getSelectedItemPosition()!=0 &&
		spinner_transmission.getSelectedItemPosition()!=0 &&
		spinner_drive_wheel.getSelectedItemPosition()!=0 &&
		spinner_engine_type.getSelectedItemPosition()!=0 &&
		editText_engine_power.length()>0 &&
		editText_registration_number.length()>0);
	}

	private void GetVehicleData() {
		Vehicle vehicle = mainActivity.db.getVehicle(vehicle_id);

		editText_brand.setText(vehicle.getBrand());
		editText_model.setText(vehicle.getModel());
		editText_year.setText(String.valueOf(vehicle.getYear()));
		spinner_category.setSelection(Integer.parseInt(vehicle.getCategory()));
		spinner_transmission.setSelection(Integer.parseInt(vehicle.getTransmission()));
		spinner_drive_wheel.setSelection(Integer.parseInt(vehicle.getDriveWheel()));
		spinner_engine_type.setSelection(Integer.parseInt(vehicle.getEngine_Type()));
		editText_engine_power.setText(String.valueOf(vehicle.getEngine_Power()));
		editText_registration_number.setText(vehicle.getRegistration_Number());
	}

}
