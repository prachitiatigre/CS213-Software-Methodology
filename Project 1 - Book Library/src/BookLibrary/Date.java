package BookLibrary;
import BookLibrary.Date;
import java.util.Calendar;
import java.util.StringTokenizer;

/**
This class handles all the input Dates and Checks it's Validity.
This Class also uses the Calendar Class through which we can get today's date. 
@author: Prachiti Atigre, Ujani Patel
*/
public class Date {
	
	String date;
	
	private int year, month, day;
	
	public static final int YEAR_NUNETEENHUNDRED = 1900;
	
	public static final int QUADRENNIAL = 4, CENTENNIAL = 100, QUATERCENTENNIAL = 400;
	
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
					
					if(day == FEB_NUM_OF_DAYS_IN_LEAP_YEAR) {
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
	
	/**
	Testbed Main
	Tests if the given dates are valid
	@param args the input argument
	*/
	public static void main(String [] args) {

		//Test Case 1:
		Date myDate1 = new Date ("2/2/1740");
		boolean boolean1 = myDate1.isValid();
		if(boolean1)
			System.out.println("Date is Valid.");
		else
			System.out.println("Date is Not Valid.");
		
		//Test Case 2:
		Date myDate2 = new Date ("9/19/2023");
		boolean boolean2 = myDate2.isValid();
		if(boolean2)
			System.out.println("Date is Valid.");
		else
			System.out.println("Date is Not Valid.");

		//Test Case 3:
		Date myDate3 = new Date ("15/1/2021");
		boolean boolean3 = myDate3.isValid();
		if(boolean3)
			System.out.println("Date is Valid.");
		else
			System.out.println("Date is Not Valid.");	
		
		//Test Case 4:
		Date myDate4 = new Date ("-1/5/2020");
		boolean boolean4 = myDate4.isValid();
		if(boolean4)
			System.out.println("Date is Valid.");
		else
			System.out.println("Date is Not Valid.");

		//Test Case 5:
		Date myDate5 = new Date ("9/9/1900");
		boolean boolean5 = myDate5.isValid();
		if(boolean5)
			System.out.println("Date is Valid.");
		else
			System.out.println("Date is Not Valid.");

		//Test Case 6:
		Date myDate6 = new Date ("1/31/2001");
		boolean boolean6 = myDate6.isValid();
		if(boolean6)
			System.out.println("Date is Valid.");
		else
			System.out.println("Date is Not Valid.");
		
		//Test Case 7:
		Date myDate7 = new Date ("2/29/2020");
		boolean boolean7 = myDate7.isValid();
		if(boolean7)
			System.out.println("Date is Valid.");
		else
			System.out.println("Date is Not Valid.");

		//Test Case 8:
		Date myDate8 = new Date ("2/28/2011");
		boolean boolean8 = myDate8.isValid();
		if(boolean8)
			System.out.println("Date is Valid.");
		else
			System.out.println("Date is Not Valid.");
		
		//Test Case 9:
		Date myDate9 = new Date ("2/29/2010");
		boolean boolean9 = myDate9.isValid();
		if(boolean9)
			System.out.println("Date is Valid.");
		else
			System.out.println("Date is Not Valid.");
		
		//Test Case 10:
		Date myDate10 = new Date ("2/8/2021");
		boolean boolean10 = myDate10.isValid();
		if(boolean10)
			System.out.println("Date is Valid.");
		else
			System.out.println("Date is Not Valid.");
		
		//Test Case 11:
		Date myDate11 = new Date ("4/10/2022");
		boolean boolean11 = myDate11.isValid();
		if(boolean11)
			System.out.println("Date is Valid.");
		else
			System.out.println("Date is Not Valid.");
		
		//Test Case 12:
		Date myDate12 = new Date ("4/-10/2022");
		boolean boolean12 = myDate12.isValid();
		if(boolean12)
			System.out.println("Date is Valid.");
		else
			System.out.println("Date is Not Valid.");
		
		//Test Case 13:
		Date myDate13 = new Date ("-5/-10/2022");
		boolean boolean13 = myDate13.isValid();
		if(boolean13)
			System.out.println("Date is Valid.");
		else
			System.out.println("Date is Not Valid.");	
	}
}