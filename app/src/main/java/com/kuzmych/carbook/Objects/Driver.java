package com.kuzmych.carbook.Objects;

import java.util.Date;

/**
 * Created by Yuri on 13.08.2017.
 * yuri.kuzmych@gmail.com
 */

public class Driver {

	private int id;
	private String FirstName;
	private String SecondName;
	private String Phone_Number;

	private String Licence_Number = "B072RRE";
	private String Licence_StartDate;
	private String Licence_EndDate;
	private String Licence_Categories;	//A1 A B1 B C1 C D1 D //BE C1E CE D1E DE //T

	public Driver() {
	}

	public Driver(String firstName, String secondName) {
		FirstName = firstName;
		SecondName = secondName;
	}

	public Driver(int id, String firstName, String secondName) {
		this.id = id;
		FirstName = firstName;
		SecondName = secondName;
	}

	public Driver(int id, String firstName, String secondName, String phone_Number, String licence_Number, String licence_StartDate, String licence_EndDate, String licence_Categories) {
		this.id = id;
		FirstName = firstName;
		SecondName = secondName;
		Phone_Number = phone_Number;
		Licence_Number = licence_Number;
		Licence_StartDate = licence_StartDate;
		Licence_EndDate = licence_EndDate;
		Licence_Categories = licence_Categories;
	}

	@Override
	public String toString() {
		return FirstName +" "+ SecondName;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return FirstName;
	}
	public void setFirstName(String firstName) {
		FirstName = firstName;
	}
	public String getSecondName() {
		return SecondName;
	}
	public void setSecondName(String secondName) {
		SecondName = secondName;
	}
	public String getPhone_Number() {
		return Phone_Number;
	}
	public void setPhone_Number(String phone_Number) {
		Phone_Number = phone_Number;
	}
	public String getLicence_Number() {
		return Licence_Number;
	}
	public void setLicence_Number(String licence_Number) {
		Licence_Number = licence_Number;
	}
	public String getLicence_StartDate() {
		return Licence_StartDate;
	}
	public void setLicence_StartDate(String licence_StartDate) {
		Licence_StartDate = licence_StartDate;
	}
	public String getLicence_EndDate() {
		return Licence_EndDate;
	}
	public void setLicence_EndDate(String licence_EndDate) {
		Licence_EndDate = licence_EndDate;
	}
	public String getLicence_Categories() {
		return Licence_Categories;
	}
	public void setLicence_Categories(String licence_Categories) {
		Licence_Categories = licence_Categories;
	}

}