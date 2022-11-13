package payroll;

import java.util.Calendar;
import java.util.StringTokenizer;

/**
This class handles all the input Dates and Checks it's Validity.
This Class also uses the Calendar Class through which we can get today's date. 
@author: Prachiti Atigre, Ujani Patel
*/
public class Date implements Comparable <Date> {
	
	String date;
	private int year, month, day;
	
	public static final int YEAR_NUNETEENHUNDRED = 1900;
	
	public static final int QUADRENNIAL = 4, CENTENNIAL = 100, QUATERCENTENNIAL = 400;
	
	public static final int DATE_IS_GREATER = -1, DATE_IS_NOT_GREATER = 1, DATES_ARE_EQUAL = 0; 
	
	public static final int MIN_NUM_OF_DAYS_IN_MONTH = 1, MAX_NUM_OF_DAYS_IN_MONTH = 31, THIRTY_DAYS_IN_MONTH = 30, 
			FEB_NUM_OF_DAYS_IN_LEAP_YEAR = 29, FEB_NUM_OF_DAYS_IN_NON_LEAP_YEAR = 28;
	
	public static final int JANUARY = 01, FEBRUARY = 02, MARCH = 03, APRIL = 04, MAY = 05, JUNE = 06, JULY = 07, AUGUST = 8, 
			SEPTEMBER = 9, OCTOBER = 10, NOVEMBER = 11, DECEMBER = 12;
	
	/**
	Takes date as parameter in the mm/dd/yyyy format and creates a Date object
	@param date the date entered by the user
	*/
	public Date(String date) { 

		StringTokenizer st = new StringTokenizer(date, "/");
		this.month = Integer.parseInt(st.nextToken());
		this.day = Integer.parseInt(st.nextToken());
		this.year = Integer.parseInt(st.nextToken());
	} 
	
	/**
	Creates an object with today’s date using Calendar class
	*/
	public Date() { 
	
		Calendar today = Calendar.getInstance();
		
		this.year = today.get(Calendar.YEAR);
		this.month = today.get(Calendar.MONTH) + 1;
		this.day = today.get(Calendar.DAY_OF_MONTH);
	}
	
	/**
	Compares two dates
	@param date the date to be compared
	@return -1 is date is greater, 1 if date is smaller and 0 if the dates are equal
	*/
	@Override
	public int compareTo(Date date) {
		
		if(date.year > year)  
			return DATE_IS_GREATER;
        
		if(date.year < year)  
			return DATE_IS_NOT_GREATER;
		
        if(date.month > month)
        	return DATE_IS_GREATER;
        
        if(date.month < month) 
        	return DATE_IS_NOT_GREATER;
        
        if(date.day > day)
        	return DATE_IS_GREATER;
        
        if(date.day < day)
        	return DATE_IS_NOT_GREATER;
        
        return DATES_ARE_EQUAL; 
	}
	
	/**
	Helper method to get the day of the month
	@return day of the year
	*/
	public int getDay() {
		return this.day;
	}
	
	/**
	Helper method to get the month of the year
	@return month of the year
	*/
	public int getMonth() {
		return this.month;
	}
	
	/**
	Helper method to get the year
	@return year
	*/
	public int getYear() {
		return this.year;
	}
	
	/**
	Checks if the date is a leap year
	@param year the year to be checked
	@return true if it is a leap year, false otherwise
	*/
	public boolean isLeapYear(int year) {
		
		if(year % QUADRENNIAL == 0) {
			
			if(year % CENTENNIAL == 0) {
			
				if(year % QUATERCENTENNIAL == 0) {
					return true; //Is leap year
				}
				else {
					return false; //Is not leap year
				}
			}
			else {
				return true; //Is leap year
			}
		}
		else {
			return false; //Is not leap year
		}
	}
	
	/**
	Checks if the date is a valid date. For example, a future date, any date before the year 1900 is invalid
	@return true if it is a valid date, false otherwise
	*/
	public boolean isValid() {
		
		Date myToday = new Date();
		
		//Same date as today's date
		if(year == myToday.year && month == myToday.month && day == myToday.day) {
			return true;
		}
		
		//Checks if year <1900 or >today's date
		if(year < YEAR_NUNETEENHUNDRED || year > myToday.year) {
			return false;
		}
		
		else if(year == myToday.year) {
			
			if(month > myToday.month) {
				return false;
			}
			else if(month == myToday.month) {
				
				if(day >= MIN_NUM_OF_DAYS_IN_MONTH && day < myToday.day) {
					return true;
				}
				else if(day > myToday.day) {
					return false;
				}
			}	
		}
		
		else {
	
			//Check Month and Number of Days
			if(month == JANUARY || month == MARCH || month == MAY || month == JULY || month == AUGUST || 
					month == OCTOBER || month == DECEMBER) {
				
				if(day >= MIN_NUM_OF_DAYS_IN_MONTH && day <= MAX_NUM_OF_DAYS_IN_MONTH) {
					return true;
				}
				else {
					return false;
				}
			}
			
			else if(month == APRIL || month == JUNE || month == SEPTEMBER || month == NOVEMBER) {
				
				if(day >= MIN_NUM_OF_DAYS_IN_MONTH && day <= THIRTY_DAYS_IN_MONTH) {
					return true;
				}
				else {
					return false;
				}
			}
			
			else if(month == FEBRUARY) {
				
				if(isLeapYear(year)) {
					
					if(day <= FEB_NUM_OF_DAYS_IN_LEAP_YEAR) {
						return true;
					}
					else {
						return false;
					}
				}
				else {
					
					if(day >= MIN_NUM_OF_DAYS_IN_MONTH && day <= FEB_NUM_OF_DAYS_IN_NON_LEAP_YEAR) {
						return true;
					}
					else {
						return false;
					}
				}
			}
			
			else {
				return false;
			}
		}
		return false;
	}
}

