package com.iteso.model;

import java.time.LocalDate;

import javax.swing.JOptionPane;

public class Date {
	
	
public static final int MIN_YEAR, MAX_YEAR;
	
	public static final String ENE = "Enero";
	public static final String FEB = "Febrero";
	public static final String MAR = "Marzo";
	public static final String ABR = "Abril";
	public static final String MAY = "Mayo";
	public static final String JUN = "Junio";
	public static final String JUL = "Julio";
	public static final String AGO = "Agosto";
	public static final String SEP = "Septiembre";
	public static final String OCT = "Octubre";
	public static final String NOV = "Noviembre";
	public static final String DEC = "Diciembre";
	
	// guardar finals para consulta
	// DEBE SER STATIC FINAL para que no se modifique y para que sea un solo objeto
	public static final String[] monthArray = {ENE, FEB, MAR, ABR, MAY, JUN, JUL, AGO, SEP, OCT, NOV, DEC};
	
	private int    day = 1, month = 1, year = 2017;
	// private String monthName = "Enero";
	private int    format = 0;
	
	static {
		MIN_YEAR = Integer.parseInt(JOptionPane.showInputDialog("Año inicial:"));
		MAX_YEAR = Integer.parseInt(JOptionPane.showInputDialog("Año final:"));
	}
	
	public Date() {
		toSystemDate();
	}

	public Date(int day, int month, int year) {
		setYear(year);
		setMonth(month);
		setDay(day);
	}

	public Date(int day, int month, int year, int format) {
		this(day, month, year);
		setFormat(format);
	}

	public int getDay() {
		return this.day;
	}

	public void toSystemDate() {
//		Usar LocalDate
		LocalDate today= LocalDate.now();
		setDay(today.getDayOfMonth());
		setMonth(today.getMonthValue());
		setYear(today.getYear());
	}
	
	public void setDay(int day) {
		if(isValidDate(day, this.month, this.year))
			this.day = day;
	}

	public int getMonth() {
		return this.month;
	}

	public void setMonth(int month) {
		if(isValidDate(this.day, month, this.year)) {
			this.month = month;
		
		}
			
	}

	public int getYear() {
		return this.year;
	}

	public void setYear(int year) {
		if(isValidDate(this.day, this.month, year))
			this.year = year;
	}

	public int getFormat() {
		return this.format;
	}
	
	public void setFormat(int format) {
		if(format >= 0 && format <= 2) this.format = format;
	}
	
	public String getMonthName() {
		return monthArray[this.month-1];
	}
	
	public String toString() {
		switch(this.format) {
			case 0  : return String.format("%02d/%02d/%02d", 
				                             this.day, this.month,this.year % 100);

			case 1  : return String.format("%d-%s-%d", 
			                 this.day, this.monthArray[this.month-1].substring(0, 3), this.year);

			default : return String.format("%d de %s de %d", 
			                 this.day, this.monthArray[this.month-1].toLowerCase(),   this.year);
		}
	}
	
	public boolean equals(Object o) {
		if(!(o instanceof Date)) return false;
		Date d = (Date) o;
		return this.day == d.day && this.month == d.month && this.year == d.year;
	}

	public Date clone() {
		return new Date(this.day, this.month, this.year, this.format);
	}
	
	public void next() {
		int dd = this.day;
		int mm = this.month;
		int yy = this.year;
		dd ++;
		if((isLeap(year) == true && day == 29 && month == 2) || 
		   (dd > 28 &&  month == 2 && isLeap(year) == false) ||
		   (dd > 30 && (month == 4 || month == 6 || month == 9 || month == 11)) ||
		    dd > 31) {
			dd = 1;
			mm ++;
			if(mm > 12) {
				mm = 1;
				yy ++;
			}
		}
		this.day = dd;
		setMonth(mm);
		this.year = yy;
	}
	
	public static boolean isLeap(int year) {
		return year % 400 == 0 || year % 4 == 0 && year % 100 != 0;
	}
	
	public boolean isLeap() {
		return isLeap(this.year);
	}
	
	public static boolean isValidDate(int dd, int mm, int yy) {
		if(dd < 1 || dd > 31 || mm < 1 || mm > 12 || yy < MIN_YEAR || yy > MAX_YEAR) 
			return false;
		
		else {
			if((isLeap(yy) && mm == 2 && dd == 29) ||
				(dd <= 28 && mm == 2) ||
				(dd <= 30 && (mm == 4 || mm == 6 || mm == 9 || mm == 11)) ||
				(dd <= 31 && (mm == 1 || mm == 3 || mm == 5 || mm == 7 || mm == 8 || mm == 10 || mm == 12))) 
				return true;
		}
		return false;
	}
	
	
	public void m5() {
		System.out.println("m5 de Date");
	}
	
	public void m6() {
		System.out.println("m6 de Date");
		m5();
	}
	
	public static void main(String[] args) {
		Date d = new Date(15, 6, 1987);
	d.setFormat(2);
	System.out.println(d);
	}

	
}

