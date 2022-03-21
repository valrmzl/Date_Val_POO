package com.iteso.model;

import java.time.LocalTime;
import java.util.Calendar;

public class Millis extends DateTime {

	private int milliseconds=0;
	private long timestamp=0;
	//private String String;

//	public Millis(int day, int month, int year, 4int format, int hours, int minutes, int seconds) {
//		super(day, month, year, format, hours, minutes, seconds);
//		// TODO Auto-generated constructor stub
//	}

	public Millis(int day, int month, int year, int hours, int minutes, int seconds, int milliseconds) {
		super(day, month, year, hours, minutes, seconds);
		setMilliseconds(milliseconds);
		findTimestamp();
	}

	public Millis(int hours, int minutes, int seconds) {
		super(hours, minutes, seconds);
		// TODO Auto-generated constructor stub
	}
	
	public Millis(long ts) {
		setTimestamp(ts);
		System.out.println("ts del usuario: "+getTimestamp());
		//findDateFromTimestamp();
	}

	public Millis() {
		super();
		toSystemMillis();
	}

	public Millis(Date d) {
		super(d);
	}
	
	public void toSystemMillis() {
		Calendar cal = Calendar.getInstance();
		this.timestamp = cal.getTimeInMillis();
		long module = timestamp % 1000;
		this.milliseconds = (int)module;
	}
	
	
	public int getMilliseconds() {
		return milliseconds;
	}


	public long getTimestamp() {
		return timestamp;
	}


	public void setMilliseconds(int milliseconds) {
		if(milliseconds >= 0 && milliseconds <= 999)
		this.milliseconds = milliseconds;
	}

	public void setTimestamp(long timestamp) {
		if(timestamp >= 0 && timestamp <= Calendar.getInstance().getTimeInMillis())
		this.timestamp = timestamp;
	}
	
	public String toString() {
		if(getFormat() == 0) {
			String hms = super.toString().substring(1, 9); 
			String d = super.toString().substring(11, 19);
			return String.format("[%s.%d] %s", hms, getMilliseconds(), d);
		}
		else {
			return String.format("%s (%d)", super.toString(), getTimestamp());
		}
			
	}

	public void findTimestamp() {
		
		DateTime firstEpoch = new DateTime(31, 12, 1969, 18, 0, 0);
		DateTime copiedDate = super.clone();
		long ts = 0L;
		
		while(!firstEpoch.equals(copiedDate)) {
			firstEpoch.next();
			ts++;
		}
		
		timestamp = ts*1000 + getMilliseconds();
		
//		Primero de varios intentos que no funcionó:
//		timestamp = ((getYear()-1970)*31556926L + ((getMonth()-1)*2629743) + (getDay()*86400)
//				+ (getHours()*3600) + (getMinutes()*60) + getSeconds() - (86400*13))*1000 
//				+ getMilliseconds();
		
		//dif 2,610,838,000 millis
	}



}
