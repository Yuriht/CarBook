package com.kuzmych.carbook.Objects;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Yuri on 13.08.2017.
 * yuri.kuzmych@gmail.com
 */

public class Driver {

	private int id;
	private String FirstName;
	private String SecondName;

	private Date BirthDate;
	private String BirthPlace;
	private String Phne_Number;

	private String Licence_Number = "B072RRE";
	private Date Licence_StartDate;
	private Date Licence_EndDate;
	private ArrayList<String> Licence_Categories;	//A1 A B1 B C1 C D1 D
													//BE C1E CE D1E DE
													//T

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

}