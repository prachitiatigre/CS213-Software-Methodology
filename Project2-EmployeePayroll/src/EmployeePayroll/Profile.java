package EmployeePayroll;

/**
This class stores the data of an employee in the form of the data type Profile
The class also stores getter and setter methods for the data type Profile
@author Prachiti Atigre, Ujani Patel 
*/
public class Profile {
	
	private String name; //employee’s name in the form “lastname,firstname”
	private String department; //department code: CS, ECE, IT
	private Date dateHired;
	
	private double hourlyRate, annualSalary, managementRole;
	private int hoursWorked;
	
	@Override
	public String toString() {
		return department;
	}
	
	/**
	This method checks if the Profile object is equal to another Profile Object
	Compares name, department and date hired of the employee
	@param obj the Profile object
	@return true if the objects are equal, false otherwise
	*/
	@Override
	public boolean equals(Object obj) { 
		
		if(obj instanceof Profile) { //Check if obj is an instance of the Book Object
			
			Profile profile = (Profile) obj;  
			
			return profile.getName().equals(this.getName()) 
					&& profile.getDepartment().equals(this.getDepartment())
						&& (profile.getDateHired().compareTo(this.getDateHired()) == 0);
		}
		return false; 
	}
	
	/**
	Takes in the instance value of name, departmentCode and date hired of the employee
	@param name the name of the employee
	@param departmentCode the department that the employee is in
	@param date the date that the employee was hired on
	*/
	public Profile(String name, String departmentCode, Date date) {
		
		this.name = name;
		this.department = departmentCode;
		this.dateHired = date;
	}

	/**
	Helper method to get the name of the employee
	@return the name of the employee
	*/
	public String getName() {
		return this.name;
	}
	
	/**
	Helper method to get the department of the employee
	@return the department of the employee
	*/
	public String getDepartment() {
		return this.department;
	}
	
	/**
	Helper method to get the date hired of the employee
	@return the date hired of the employee
	*/
	public Date getDateHired() {
		return this.dateHired;
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
	Helper method to set the hours worked of the employee
	@param hoursWorked the hours worked of the employee
	*/
	public void setHoursWorked(int hoursWorked) {
		this.hoursWorked = hoursWorked;
	}
	
	/**
	Helper method to get the hours worked of the employee
	@return the hours worked of the employee
	*/
	public int getHoursWorked() {
		return this.hoursWorked;
	}
}