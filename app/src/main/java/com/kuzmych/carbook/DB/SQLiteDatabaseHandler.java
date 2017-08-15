package com.kuzmych.carbook.DB;

/**
 * Created by Yuri on 14.08.2017.
 * yuri.kuzmych@gmail.com
 */

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.kuzmych.carbook.Objects.Vehicle;
import com.kuzmych.carbook.Objects.Driver;

import java.util.ArrayList;
import java.util.List;

public class SQLiteDatabaseHandler extends SQLiteOpenHelper {

	//DB
	private static final int DATABASE_VERSION = 5;
	private static final String DATABASE_NAME = "CarsData";

	//Tables
	private static final String TABLE_VEHICLES = "Vehicles";
	private static final String TABLE_DRIVERS = "Drivers";

	//Columns
	private static final String KEY_ID = "id";
	private static final String VEHICLE_BRAND = "Brand";
	private static final String VEHICLE_MODEL = "Model";
	private static final String VEHICLE_YEAR = "Year";
	private static final String VEHICLE_DRIVER_ID = "Driver_Id";
	private static final String VEHICLE_CATEGORY = "Category";
	private static final String VEHICLE_TRANSMISSION = "Transmission";
	private static final String VEHICLE_DRIVE_WHEEL = "Drive_Wheel";
	private static final String VEHICLE_ENGINE_TYPE = "Engine_Type";
	private static final String VEHICLE_ENGINE_POWER = "Engine_Power";
	private static final String VEHICLE_REGISTRATION_NUMBER = "Registration_Number";
	private static final String DRIVER_FIRST_NAME = "First_Name";
	private static final String DRIVER_SECOND_NAME = "Second_Name";
	private static final String DRIVER_PHONE_NUMBER = "Phone_Number";
	private static final String DRIVER_LICENSE_NUMBER = "Licence_Number";
	private static final String DRIVER_LICENSE_START_DATE = "Licence_StartDate";
	private static final String DRIVER_LICENSE_END_DATE = "Licence_EndDate";
	private static final String DRIVER_LICENSE_CATEGIROES = "Licence_Categories";


	public SQLiteDatabaseHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// Creating Tables
	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_TABLE = "CREATE TABLE " + TABLE_VEHICLES + "("
				+ KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ VEHICLE_BRAND + " TEXT,"
				+ VEHICLE_MODEL + " TEXT,"
				+ VEHICLE_YEAR + " INTEGER,"
				+ VEHICLE_DRIVER_ID + " TEXT,"
				+ VEHICLE_CATEGORY + " TEXT,"
				+ VEHICLE_TRANSMISSION + " TEXT,"
				+ VEHICLE_DRIVE_WHEEL + " TEXT,"
				+ VEHICLE_ENGINE_TYPE + " TEXT,"
				+ VEHICLE_ENGINE_POWER + " INTEGER,"
				+ VEHICLE_REGISTRATION_NUMBER + " TEXT"
				+ ")";
		db.execSQL(CREATE_TABLE);

		CREATE_TABLE = "CREATE TABLE " + TABLE_DRIVERS + "("
				+ KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ DRIVER_FIRST_NAME + " TEXT,"
				+ DRIVER_SECOND_NAME + " TEXT,"
				+ DRIVER_PHONE_NUMBER + " TEXT,"
				+ DRIVER_LICENSE_NUMBER + " TEXT,"
				+ DRIVER_LICENSE_START_DATE + " TEXT,"
				+ DRIVER_LICENSE_END_DATE + " TEXT,"
				+ DRIVER_LICENSE_CATEGIROES + " TEXT"
				+ ")";
		db.execSQL(CREATE_TABLE);
	}

	// Upgrading database
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_VEHICLES);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_DRIVERS);
		// Create tables again
		onCreate(db);
	}

	/**
	 * Vehicles CRUD(Create, Read, Update, Delete) Operations
	 */

	// Adding new vehicle
	public void addVehicle(Vehicle vehicle) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(VEHICLE_BRAND, vehicle.getBrand());
		values.put(VEHICLE_MODEL, vehicle.getModel());
		values.put(VEHICLE_YEAR, vehicle.getYear());
		values.put(VEHICLE_DRIVER_ID, vehicle.getDriverId());
		values.put(VEHICLE_CATEGORY, vehicle.getCategory());
		values.put(VEHICLE_TRANSMISSION, vehicle.getTransmission());
		values.put(VEHICLE_DRIVE_WHEEL, vehicle.getDriveWheel());
		values.put(VEHICLE_ENGINE_TYPE, vehicle.getEngine_Type());
		values.put(VEHICLE_ENGINE_POWER, vehicle.getEngine_Power());
		values.put(VEHICLE_REGISTRATION_NUMBER, vehicle.getRegistration_Number());

		// Inserting Row
		db.insert(TABLE_VEHICLES, null, values);
		db.close(); // Closing database connection
	}

	// Getting single vehicle
	public Vehicle getVehicle(int id) {
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db.query(TABLE_VEHICLES, new String[]
						{KEY_ID,
						VEHICLE_BRAND,
						VEHICLE_MODEL,
						VEHICLE_YEAR,
						VEHICLE_DRIVER_ID,
						VEHICLE_CATEGORY,
						VEHICLE_TRANSMISSION,
						VEHICLE_DRIVE_WHEEL,
						VEHICLE_ENGINE_TYPE,
						VEHICLE_ENGINE_POWER,
						VEHICLE_REGISTRATION_NUMBER
						}, KEY_ID + "=?",
				new String[] { String.valueOf(id) }, null, null, null, null);
		if (cursor != null)
			cursor.moveToFirst();

		Vehicle vehicle = new Vehicle(
				Integer.parseInt(cursor.getString(0)),
				cursor.getString(1),
				cursor.getString(2),
				Integer.parseInt(cursor.getString(3)),
				cursor.getString(4),
				cursor.getString(5),
				cursor.getString(6),
				cursor.getString(7),
				cursor.getString(8),
				Integer.parseInt(cursor.getString(9)),
				cursor.getString(10));
		return vehicle;
	}

	// Getting All Vehicles
	public List getAllVehicles() {
		List vehicleList = new ArrayList();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_VEHICLES;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				Vehicle vehicle = new Vehicle();
				vehicle.setId(Integer.parseInt(cursor.getString(0)));
				vehicle.setBrand(cursor.getString(1));
				vehicle.setModel(cursor.getString(2));
				// Adding vehicle to list
				vehicleList.add(vehicle);
			} while (cursor.moveToNext());
		}

		return vehicleList;
	}

	// Updating single vehicle
	public int updateVehicle(Vehicle vehicle) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(VEHICLE_BRAND, vehicle.getBrand());
		values.put(VEHICLE_MODEL, vehicle.getModel());
		values.put(VEHICLE_YEAR, vehicle.getYear());
		values.put(VEHICLE_DRIVER_ID, vehicle.getDriverId());
		values.put(VEHICLE_CATEGORY, vehicle.getCategory());
		values.put(VEHICLE_TRANSMISSION, vehicle.getTransmission());
		values.put(VEHICLE_DRIVE_WHEEL, vehicle.getDriveWheel());
		values.put(VEHICLE_ENGINE_TYPE, vehicle.getEngine_Type());
		values.put(VEHICLE_ENGINE_POWER, vehicle.getEngine_Power());
		values.put(VEHICLE_REGISTRATION_NUMBER, vehicle.getRegistration_Number());

		// updating row
		return db.update(TABLE_VEHICLES, values, KEY_ID + " = ?",
				new String[] { String.valueOf(vehicle.getId()) });
	}

	// Deleting single vehicle
	public void deleteVehicle(Vehicle vehicle) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_VEHICLES, KEY_ID + " = ?",
				new String[] { String.valueOf(vehicle.getId()) });
		db.close();
	}

	// Deleting all vehicle
	public void deleteAllVehicles() {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_VEHICLES,null,null);
		db.close();
	}

	// Getting vehicles Count
	public int getVehiclesCount() {
		String countQuery = "SELECT  * FROM " + TABLE_VEHICLES;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery, null);
		return cursor.getCount();
	}



	/**
	 * Drivers CRUD(Create, Read, Update, Delete) Operations
	 */

	// Adding new driver
	public void addDriver(Driver driver) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(DRIVER_FIRST_NAME, driver.getFirstName());
		values.put(DRIVER_SECOND_NAME, driver.getSecondName());
		values.put(DRIVER_PHONE_NUMBER, driver.getPhone_Number());
		values.put(DRIVER_LICENSE_NUMBER, driver.getLicence_Number());
		values.put(DRIVER_LICENSE_START_DATE, driver.getLicence_StartDate());
		values.put(DRIVER_LICENSE_END_DATE, driver.getLicence_EndDate());
		values.put(DRIVER_LICENSE_CATEGIROES, driver.getLicence_Categories());

		// Inserting Row
		db.insert(TABLE_DRIVERS, null, values);
		db.close(); // Closing database connection
	}

	// Getting single driver
	public Driver getDriver(int id) {
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db.query(TABLE_DRIVERS, new String[]
						{KEY_ID,
								DRIVER_FIRST_NAME,
								DRIVER_SECOND_NAME,
								DRIVER_PHONE_NUMBER,
								DRIVER_LICENSE_NUMBER,
								DRIVER_LICENSE_START_DATE,
								DRIVER_LICENSE_END_DATE,
								DRIVER_LICENSE_CATEGIROES
						}, KEY_ID + "=?",
				new String[] { String.valueOf(id) }, null, null, null, null);
		if (cursor != null)
			cursor.moveToFirst();

		Driver driver = new Driver(
				Integer.parseInt(cursor.getString(0)),
				cursor.getString(1),
				cursor.getString(2),
				cursor.getString(3),
				cursor.getString(4),
				cursor.getString(5),
				cursor.getString(6),
				cursor.getString(7));
		return driver;
	}

	// Getting All Drivers
	public List getAllDrivers() {
		List driverList = new ArrayList();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_DRIVERS;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				Driver driver = new Driver();
				driver.setId(Integer.parseInt(cursor.getString(0)));
				driver.setFirstName(cursor.getString(1));
				driver.setSecondName(cursor.getString(2));
				// Adding vehicle to list
				driverList.add(driver);
			} while (cursor.moveToNext());
		}

		return driverList;
	}

	// Updating single driver
	public int updateDriver(Driver driver) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(DRIVER_FIRST_NAME, driver.getFirstName());
		values.put(DRIVER_SECOND_NAME, driver.getSecondName());
		values.put(DRIVER_PHONE_NUMBER, driver.getPhone_Number());
		values.put(DRIVER_LICENSE_NUMBER, driver.getLicence_Number());
		values.put(DRIVER_LICENSE_START_DATE, driver.getLicence_StartDate());
		values.put(DRIVER_LICENSE_END_DATE, driver.getLicence_EndDate());
		values.put(DRIVER_LICENSE_CATEGIROES, driver.getLicence_Categories());

		// updating row
		return db.update(TABLE_DRIVERS, values, KEY_ID + " = ?",
				new String[] { String.valueOf(driver.getId()) });
	}

	// Deleting single driver
	public void deleteDriver(Driver driver) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_DRIVERS, KEY_ID + " = ?",
				new String[] { String.valueOf(driver.getId()) });
		db.close();
	}

	// Deleting all drivers
	public void deleteAllDrivers() {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_DRIVERS,null,null);
		db.close();
	}

	// Getting drivers Count
	public int getDriversCount() {
		String countQuery = "SELECT  * FROM " + TABLE_DRIVERS;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery, null);
		return cursor.getCount();
	}
}