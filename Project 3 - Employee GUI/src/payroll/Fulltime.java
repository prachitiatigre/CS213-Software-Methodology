package payroll;

import java.text.DecimalFormat;

/**
The Fulltime class extends the employee class
It includes specific data and operations of a full time employee
@author Prachiti Atigre, Ujani Patel
*/
public class Fulltime extends Employee {

	private static final int PAY_PERIODS_PER_YEAR = 26;
	private double annualSalary, managementRole;
	
	/**
	This constructor takes a name, department, and the date object and calls the super constructor
	@param name the name of the employee
	@param department the department the employee is a part of
	@param dateHired the date that the employee was hired on
	*/
	public Fulltime(String name, String department, Date dateHired) {
		super(name, department, dateHired);
	}
	
	/**
	Helper method to set the annual salary of the employee
	@param annualSalary the annual salary of the employee
	*/
	public void setAnnualSalary(double annualSalary) {
		this.annualSalary = annualSalary;
		
	}

	/**
	Helper method to get the annual salary of the employee
	@return the annual salary of the employee
	*/
	public double getAnnualSalary() {
		return this.annualSalary;
	}
	
	/**
	Helper method to set the management role of the employee
	@param managementRole the management role of the employee
	*/
	public void setManagementRole(double managementRole) {
		this.managementRole = managementRole;
	}
	
	/**
	Helper method to get the management role of the employee
	@return the management role of the employee
	*/
	public double getManagementRole() {
		return this.managementRole;
	}
	
	/**
	This method calculates the payments of the full time employee
	*/
	@Override
	public void calculatePayment() {
		
		double annualSalary = this.getAnnualSalary();
		double payment = annualSalary / PAY_PERIODS_PER_YEAR;
		
		super.setPayment(payment);
	}
	
	/**
	This method returns a string representation of the object Fulltime. It includes the name, 
	department and date hired of the employee from the super class and the payment, annual salary from this class
	@return String format
	*/
	@Override
	public String toString() {
		
		DecimalFormat df = new DecimalFormat("#,##0.00"); //Formats the output of the payment and annual salary
		
		return super.toString() + "::Payment $" + df.format(super.getPayment()) + "::FULL TIME::Annual Salary $" 
				+ df.format(this.getAnnualSalary());
	}
	
	/**
	This method checks if the Fulltime employee object is equal to another Fulltime employee Object
	Compares name, department, date hired and annual salary of the employee
	@param obj the Employee object
	@return true if the objects are equal, false otherwise
	*/
	@Override
	public boolean equals(Object obj) {
		
		if(obj instanceof Fulltime) { 
			
			Fulltime emp = (Fulltime) obj;  
			
			return emp.getProfile().getName().equals(this.getProfile().getName()) 
					&& emp.getProfile().getDepartment().equals(this.getProfile().getDepartment()) 
						&& (emp.getProfile().getDateHired().compareTo(this.getProfile().getDateHired()) == 0)
							&& (emp.getAnnualSalary()) == (this.getAnnualSalary());
		}
		return false;
	}
}