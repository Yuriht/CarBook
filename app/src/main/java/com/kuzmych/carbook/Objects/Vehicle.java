package com.kuzmych.carbook.Objects;

/**
 * Created by Yuri on 13.08.2017.
 * yuri.kuzmych@gmail.com
 */

public class Vehicle {

	private int id;
	private String Brand;
	private String Model;
	private int Year;

	private String Category;
	private String Transmission;
	private String DriveWheel;
	private String Engine_Type;
	private int Engine_Power; //Horse Power
	private String Registration_Number;

	public Vehicle() {
	}

	public Vehicle(String brand, String model) {
		Brand = brand;
		Model = model;
	}

	public Vehicle(int id, String brand, String model) {
		this.id = id;
		Brand = brand;
		Model = model;
	}

	public Vehicle(int id, String brand, String model, int year, String category, String transmission, String driveWheel, String engine_Type, int engine_Power, String registration_Number) {
		this.id = id;
		Brand = brand;
		Model = model;
		Year = year;
		Category = category;
		Transmission = transmission;
		DriveWheel = driveWheel;
		Engine_Type = engine_Type;
		Engine_Power = engine_Power;
		Registration_Number = registration_Number;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBrand() {
		return Brand;
	}
	public void setBrand(String brand) {
		Brand = brand;
	}
	public String getModel() {
		return Model;
	}
	public void setModel(String model) {
		Model = model;
	}
	public int getYear() {
		return Year;
	}
	public void setYear(int year) {
		Year = year;
	}
	public String getCategory() {
		return Category;
	}
	public void setCategory(String category) {
		Category = category;
	}
	public String getTransmission() {
		return Transmission;
	}
	public void setTransmission(String transmission) {
		Transmission = transmission;
	}
	public String getDriveWheel() {
		return DriveWheel;
	}
	public void setDriveWheel(String driveWheel) {
		DriveWheel = driveWheel;
	}
	public String getEngine_Type() {
		return Engine_Type;
	}
	public void setEngine_Type(String engine_Type) {
		Engine_Type = engine_Type;
	}
	public int getEngine_Power() {
		return Engine_Power;
	}
	public void setEngine_Power(int engine_Power) {
		Engine_Power = engine_Power;
	}
	public String getRegistration_Number() {
		return Registration_Number;
	}
	public void setRegistration_Number(String registration_Number) {
		Registration_Number = registration_Number;
	}

}