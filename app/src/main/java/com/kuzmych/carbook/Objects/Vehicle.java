package com.kuzmych.carbook.Objects;

/**
 * Created by Yuri on 13.08.2017.
 * yuri.kuzmych@gmail.com
 */

public class Vehicle {

	private enum Category {Motorcycle, Passenger, Bus, Truck, Air, Water, Space};
	private enum Type {Sedan, Hatchback, Coupe, Crossover};

	private String Brand = "Chevrolet";
	private String Model = "Aveo";
	private int Year = 2017;

	private enum Transmission {Auto, Manual};
	private enum DriveWheel {Front_Wheel, Rear_Wheel, All_Wheel};

	private enum Engine_Type {Electric, Petrol, Gas, Diesel};
	private int Engine_Power = 120; //in Horse Power
	private float Engine_Volume = 1.1f; //Engine Displacement in litres

	private String Color = "Red";
	private int Seats = 4;
	private String Registration_Number = "KA 1234 AB";

	public Vehicle() {
	}

	public Vehicle(String brand, String model) {
		Brand = brand;
		Model = model;
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

}