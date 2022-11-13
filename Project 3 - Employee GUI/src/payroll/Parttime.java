package payroll;

import java.text.DecimalFormat;

/**
The Parttime class extends the Employee class
It includes specific data and operations of a part time employee
@author Prachiti Atigre, Ujani Patel
*/
public class Parttime extends Employee{

	private static final int MINIMUM_HOURS_WORKED = 0;
	private static final int MAXIMUM_HOURS_WORKED = 100; 
	private static final int MAXIMUM_HOURS_WORKED_WITHOUT_ADDITIONAL_PAY = 80;
	
	private static final double ADDITIONAL_PAY_RATE = 1.5;
	
	private double hourlyRate;
	private double hoursWorked;
	
	/**
	This constructor takes a name, department, and the date object and calls the super constructor
	@param name the name of the employee
	@param department the department the employee is a part of
	@param dateHired the date that the employee was hired on
	*/
	public Parttime(String name, String department, Date dateHired) {
		super(name, department, dateHired);
	}

	
	/**
	Helper method to set the hourly rate of the employee
	@param hourlyRate the hourly rate of the employee
	*/
	public void setHourlyRate(double hourlyRate) {
		this.hourlyRate = hourlyRate;
	}

	/**
	Helper method to get the hourly rate of the employee
	@return the hourly rate of the employee
	*/
	public double getHourlyRate() {
		return this.hourlyRate;
	}
	
	/**
	Helper method to set the hours worked of the employee
	@param hoursWorked the hours worked of the employee
	*/
	public void setHoursWorked(double hoursWorked) {
		this.hoursWorked = hoursWorked;
	}
	
	/**
	Helper method to get the hours worked of the employee
	@return the hours worked of the employee
	*/
	public double getHoursWorked() {
		return this.hoursWorked;
	}

	/**
	This method calculates the payments of the management employee
	*/
	@Override
	public void calculatePayment() {
		
		double hourlyRate = this.getHourlyRate();
		double hoursWorked = this.getHoursWorked();
		double payment;
		
		if(hoursWorked >= MINIMUM_HOURS_WORKED && hoursWorked <= MAXIMUM_HOURS_WORKED_WITHOUT_ADDITIONAL_PAY) {
			payment = hourlyRate * hoursWorked;
			super.setPayment(payment);
		}
		else if(hoursWorked > MAXIMUM_HOURS_WORKED_WITHOUT_ADDITIONAL_PAY && hoursWorked <= MAXIMUM_HOURS_WORKED) {
		
			payment = MAXIMUM_HOURS_WORKED_WITHOUT_ADDITIONAL_PAY * hourlyRate;
			double additionalHoursWorked = hoursWorked - MAXIMUM_HOURS_WORKED_WITHOUT_ADDITIONAL_PAY;
			double additionalPayment = additionalHoursWorked * hourlyRate * ADDITIONAL_PAY_RATE;
			payment = payment + additionalPayment;
			super.setPayment(payment);
		}
	}
	
	/**
	This method returns a string representation of the object Parttime. It includes the name, 
	department and date hired of the employee from the super class and the payment, hourly rate and hours worked from this class
	@return String format
	*/
	@Override
	public String toString() {
		
		DecimalFormat df = new DecimalFormat("#,##0.00");
		
		return super.toString() + "::Payment $" + df.format(super.getPayment()) + "::PART TIME::Hourly Rate $" 
				+ df.format(this.getHourlyRate()) + "::Hours Worked this period: " 
					+ this.getHoursWorked();
	}
	
	/**
	This method checks if the Parttime employee object is equal to another Parttime employee Object
	Compares name, department, date hired, hourly rate and hours worked of the employee
	@param obj the Employee object
	@return true if the objects are equal, false otherwise
	*/
	@Override
	public boolean equals(Object obj) {
		
		if(obj instanceof Parttime) { //Check if obj is an instance of the Book Object
			
			Parttime emp = (Parttime) obj;  
			
			return emp.getProfile().getName().equals(this.getProfile().getName()) 
					&& emp.getProfile().getDepartment().equals(this.getProfile().getDepartment()) 
					&& (emp.getProfile().getDateHired().compareTo(this.getProfile().getDateHired()) == 0)
							&& (emp.getHourlyRate()) == (this.getHourlyRate())
								&& (emp.getHoursWorked()) == (this.getHoursWorked());
		}
		return false;
	}
}
