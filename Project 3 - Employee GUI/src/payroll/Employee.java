package payroll;

/**
This class is a super class. It defines common data and operations for all employee type. 
These data and operations are required by all subclasses 
@author Prachiti Atigre, Ujani Patel 
*/
public class Employee {

	private Profile profile;	
	private double payment = 0;
	
	/**
	Constructor that creates a new profile with the name, department and date hired of the employee
	@param name the name of the employee
	@param department the department the employee is a part of
	@param dateHired the date that the employee was hired on
	*/
	public Employee(String name, String department, Date dateHired) {
		
		this.profile = new Profile(name, department, dateHired);
	}

	/**
	This method returns a string representation of the object Employee. It includes the name, 
	department and date hired of the employee
	@return String format
	*/
	@Override
	public String toString() {
		
		return this.getProfile().getName() + "::" + this.getProfile().getDepartment() + "::" 
				+ this.getProfile().getDateHired().getMonth() + "/" + this.getProfile().getDateHired().getDay() + "/" 
					+ this.getProfile().getDateHired().getYear();
	}
	
	/**
	This method checks if the Employee object is equal to another Employee Object
	Compares name, department and date hired of the employee
	@param obj the Employee object
	@return true if the objects are equal, false otherwise
	*/
	@Override 
	public boolean equals(Object obj) { 
		
		if(obj instanceof Employee) { //Check if obj is an instance of the Book Object
			
			Employee employee = (Employee) obj;  
			
			return employee.getProfile().equals(this.getProfile());
		}
		return false;
	}
	
	/**
	Helper method to set the value of payment of the employee
	@param payment the payment of the employee
	*/
	public void setPayment(double payment) {
		this.payment = payment;
	}
	
	/**
	Helper method to get the value of payment of the employee
	@return payment the payment of the employee
	*/
	public double getPayment() {
		return this.payment;
	}
	
	/**
	Helper method to get the profile of the employee
	@return profile the profile of the employee
	*/
	public Profile getProfile() {
		return this.profile;
	}
	
	/**
	Helper method to calculate the payment of the employee 
	*/
	public void calculatePayment() {
		
	}
}