package com.kuzmych.carbook;

/**
 * Created by Yuri on 13.08.2017.
 * yuri.kuzmych@gmail.com
 */

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.kuzmych.carbook.DB.SQLiteDatabaseHandler;
import com.kuzmych.carbook.Fragments.DriverFragment;
import com.kuzmych.carbook.Fragments.MainFragment;
import com.kuzmych.carbook.Fragments.VehicleFragment;

public class MainActivity extends AppCompatActivity {

	public SQLiteDatabaseHandler db;
	MainFragment mainFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		db = new SQLiteDatabaseHandler(this);

		if (findViewById(R.id.fragment_container) != null) {
			if (savedInstanceState != null) {
				return;
			}
			ShowMainFragment();
		}
	}

	public void ShowMainFragment() {
		mainFragment = new MainFragment();
		mainFragment.setArguments(getIntent().getExtras());
		getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, mainFragment).commit();
	}

	public void ShowVehicleFragment(VehicleFragment.Action action, int vehicle_id) {
		VehicleFragment fragment = VehicleFragment.newInstance(action, vehicle_id);
		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
		transaction.add(R.id.fragment_container, fragment);
		transaction.addToBackStack(fragment.TAG);
		transaction.commit();
	}

	public void ShowDriverFragment(DriverFragment.Action action, int driver_id) {
		DriverFragment fragment = DriverFragment.newInstance(action, driver_id);
		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
		transaction.add(R.id.fragment_container, fragment);
		transaction.addToBackStack(fragment.TAG);
		transaction.commit();
	}

	public void UpdateList() {
		if (mainFragment != null) mainFragment.UpdateList();
	}


	//TODO click default action is view, new buttons on detailed view /edit/ and /delete/
	//TODO floating add circle button
}