package com.kuzmych.carbook.Fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.kuzmych.carbook.DB.SQLiteDatabaseHandler;
import com.kuzmych.carbook.MainActivity;
import com.kuzmych.carbook.Objects.Driver;
import com.kuzmych.carbook.R;

/**
 * Created by Yuri on 14.08.2017.
 * yuri.kuzmych@gmail.com
 */

public class DriverFragment extends Fragment {

	public static String TAG = "TAG_DriverFragment";
	public final static String ACTION = "ACTION";
	public final static String ITEM_ID = "ITEM_ID";

	public enum Action {Add, View, Edit};

	private MainActivity mainActivity;
	private View this_view;
	private SQLiteDatabaseHandler db;
	private Action action;
	private int driver_id;

	//ui elements
	private TextView text_title;
	private EditText editText_first_name;
	private EditText editText_second_name;
	private EditText editText_phone_number;
	private EditText editText_license_number;
	private EditText editText_license_start_date;
	private EditText editText_license_end_date;
	private EditText editText_licence_categories;
	private Button button_cancel;
	private Button button_save;

	public DriverFragment() {
	}

	//Static method to pass data for this fragment
	public static DriverFragment newInstance(Action action, int driver_id){
		DriverFragment driverFragment = new DriverFragment();
		Bundle args = new Bundle();
		args.putSerializable(ACTION, action);
		args.putInt(ITEM_ID, driver_id);
		driverFragment.setArguments(args);
		return driverFragment;
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		mainActivity = (MainActivity) activity;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		action = (Action) getArguments().get(ACTION);
		driver_id = getArguments().getInt(ITEM_ID);

		this_view = inflater.inflate(R.layout.fragment_driver, container, false);

		text_title = (TextView) this_view.findViewById(R.id.textView_Title);
		editText_first_name = (EditText) this_view.findViewById(R.id.editText_first_name);
		editText_second_name = (EditText) this_view.findViewById(R.id.editText_second_name);
		editText_phone_number = (EditText) this_view.findViewById(R.id.editText_phone_number);
		editText_license_number = (EditText) this_view.findViewById(R.id.editText_license_number);
		editText_license_start_date = (EditText) this_view.findViewById(R.id.editText_license_start_date);
		editText_license_end_date = (EditText) this_view.findViewById(R.id.editText_license_end_date);
		editText_licence_categories = (EditText) this_view.findViewById(R.id.editText_licence_categories);
		button_cancel = (Button) this_view.findViewById(R.id.button_cancel);
		button_save = (Button) this_view.findViewById(R.id.button_save);

		switch (action){
			case Add:
				text_title.setText(R.string.str_add_driver);
				break;

			case View:
				//Set UI to read only
				text_title.setText(R.string.str_driver);
				editText_first_name.setEnabled(false);
				editText_second_name.setEnabled(false);
				editText_phone_number.setEnabled(false);
				editText_license_number.setEnabled(false);
				editText_license_start_date.setEnabled(false);
				editText_license_end_date.setEnabled(false);
				editText_licence_categories.setEnabled(false);
				button_save.setVisibility(View.INVISIBLE);
				GetDriverData();
				break;

			case Edit:
				text_title.setText(R.string.str_edit_driver);
				GetDriverData();
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
			String first_nameStr = editText_first_name.getText().toString();
			String second_nameStr = editText_second_name.getText().toString();
			String phone_numberStr = editText_phone_number.getText().toString();
			String license_numberStr = editText_license_number.getText().toString();
			String start_dateStr = editText_license_start_date.getText().toString();
			String end_dateStr = editText_license_end_date.getText().toString();
			String licence_categoriesStr = editText_licence_categories.getText().toString();

			//save
			Driver driver = new Driver(driver_id, first_nameStr, second_nameStr, phone_numberStr, license_numberStr, start_dateStr, end_dateStr, licence_categoriesStr);
			switch (action){
				case Add:
					mainActivity.db.addDriver(driver);
					Toast.makeText(mainActivity, R.string.toast_item_inserted, Toast.LENGTH_SHORT).show();
					break;
				case Edit:
					mainActivity.db.updateDriver(driver);
					Toast.makeText(mainActivity, R.string.toast_item_edited, Toast.LENGTH_SHORT).show();
					break;
			}
			mainActivity.UpdateList();
			getActivity().getSupportFragmentManager().popBackStack();
		}
	};

	private boolean GetErrors() {
		return 	!(editText_first_name.length()>0 &&
				editText_second_name.length()>0 &&
				editText_phone_number.length()>0 &&
				editText_license_number.length()>0 &&
				editText_license_start_date.length()>0 &&
				editText_license_end_date.length()>0 &&
				editText_licence_categories.length()>0);
	}

	private void GetDriverData() {
		Driver driver = mainActivity.db.getDriver(driver_id);

		editText_first_name.setText(driver.getFirstName());
		editText_second_name.setText(driver.getSecondName());
		editText_phone_number.setText(driver.getPhone_Number());
		editText_license_number.setText(driver.getLicence_Number());
		editText_license_start_date.setText(driver.getLicence_StartDate());
		editText_license_end_date.setText(driver.getLicence_EndDate());
		editText_licence_categories.setText(driver.getLicence_Categories());
	}

}