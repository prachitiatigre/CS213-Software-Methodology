package payroll;

import java.text.DecimalFormat;

/**
The Management class extends the Fulltime class
It includes specific data and operations of a management employee
@author Prachiti Atigre, Ujani Patel
*/
public class Management extends Fulltime{

	private static final int MANAGER_ROLE = 1, DEPARTMENT_HEAD_ROLE = 2, DIRECTOR_ROLE = 3;
	private static final int PAY_PERIODS_PER_YEAR = 26;
	private static final double MANAGER_COMPENSATION = 5000.00/26.00;
	private static final double DEPARTMENT_HEAD_COMPENSATION = 9500.00/26.00;
	private static final double DIRECTOR_COMPENSATION = 12000.00/26.00;
	
	/**
	This constructor takes a name, department, and the date object and calls the super constructor
	@param name the name of the employee
	@param department the department the employee is a part of
	@param dateHired the date that the employee was hired on
	*/	
	public Management(String name, String department, Date dateHired) {
		super(name, department, dateHired);
	}
	
	/**
	This method calculates the payments of the management employee
	*/
	@Override
	public void calculatePayment() {
		
		double managementRole = super.getManagementRole();
		double payment;
		
		if(managementRole == MANAGER_ROLE) {
			
			payment = (super.getAnnualSalary() / PAY_PERIODS_PER_YEAR)+ MANAGER_COMPENSATION;
			super.setPayment(payment);
		}	
			
		else if(managementRole == DEPARTMENT_HEAD_ROLE) {
			
			payment = (super.getAnnualSalary() / PAY_PERIODS_PER_YEAR) + DEPARTMENT_HEAD_COMPENSATION;
			super.setPayment(payment);
		}
		
		else if(managementRole == DIRECTOR_ROLE) {
			
			payment = (super.getAnnualSalary() / PAY_PERIODS_PER_YEAR) + DIRECTOR_COMPENSATION;
			super.setPayment(payment);
		}	
	}
	
	/**
	This method returns a string representation of the object Management. It includes the name, 
	department and date hired of the employee from the super class and the annual salary and additional compensation from this class
	@return String format
	*/
	@Override
	public String toString() {
		
		DecimalFormat df = new DecimalFormat("#,##0.00");
		
		double managementRole = super.getManagementRole();
		
		if(managementRole == MANAGER_ROLE) {
			return super.toString() + "::Manager Compensation $" + df.format(MANAGER_COMPENSATION);
		}
		
		else if(managementRole == DEPARTMENT_HEAD_ROLE) {
			return super.toString() + "::Department Head Compensation $" + df.format(DEPARTMENT_HEAD_COMPENSATION);
		}
		
		else if(managementRole == DIRECTOR_ROLE) {
			return super.toString() + "::Director Compensation $" + df.format(DIRECTOR_COMPENSATION);
		}
		
		return null;
	}
	
	/**
	This method checks if the Management employee object is equal to another Management employee Object
	Compares name, department, date hired, annual salary and management role of the employee
	@param obj the Employee object
	@return true if the objects are equal, false otherwise
	*/
	@Override
	public boolean equals(Object obj) { 
		
		if(obj instanceof Management) { //Check if obj is an instance of the Book Object
			
			Management emp = (Management) obj;  
			
			return emp.getProfile().getName().equals(this.getProfile().getName()) 
					&& emp.getProfile().getDepartment().equals(this.getProfile().getDepartment()) 
						&& (emp.getProfile().getDateHired().compareTo(this.getProfile().getDateHired()) == 0)
					&& (emp.getAnnualSalary()) == (this.getAnnualSalary())
					&& (emp.getManagementRole()) == (this.getManagementRole());
		}
		return false;
	}
}
