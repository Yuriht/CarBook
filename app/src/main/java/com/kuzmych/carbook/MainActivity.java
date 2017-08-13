package com.kuzmych.carbook;

/**
 * Created by Yuri on 13.08.2017.
 * yuri.kuzmych@gmail.com
 */

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.kuzmych.carbook.Adapters.VehicleAdapter;
import com.kuzmych.carbook.Objects.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

	private List<Vehicle> vehicleList = new ArrayList<>();
	private RecyclerView recyclerView;
	private VehicleAdapter mAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

		mAdapter = new VehicleAdapter(vehicleList);
		RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
		recyclerView.setLayoutManager(mLayoutManager);
		recyclerView.setItemAnimator(new DefaultItemAnimator());
		recyclerView.setAdapter(mAdapter);

		prepareTestData();
	}

	private void prepareTestData() {
		Vehicle vehicle;
		for (int i=0; i<31; i++) {
			vehicle = new Vehicle("Chevrolet", "Aveo " + i);
			vehicleList.add(vehicle);
		}
		mAdapter.notifyDataSetChanged();
	}

}
