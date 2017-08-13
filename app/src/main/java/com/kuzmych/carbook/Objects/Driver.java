package com.kuzmych.carbook.Objects;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Yuri on 13.08.2017.
 * yuri.kuzmych@gmail.com
 */

public class Driver {
	public String FirstName;
	public String SecondName;
	public String ThirdName;

	public Date BirthDate;
	public String BirthPlace;
	public String Phne_Number;

	public String Licence_Number = "B072RRE";
	public Date Licence_StartDate;
	public Date Licence_EndDate;
	public ArrayList<String> Licence_Categories;	//A1 A B1 B C1 C D1 D
													//BE C1E CE D1E DE
													//T
}