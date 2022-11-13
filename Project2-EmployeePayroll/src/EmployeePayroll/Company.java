package EmployeePayroll;

/**
This class handles all the user commands
These commands include adding, removing, processing employee payments, setting hours worked by employee and printing 
the earning statements of the employees
@author Prachiti Atigre, Ujani Patel 
*/
public class Company {
	
	private int numEmployee;
	private Employee[] emplist;
	
	private static final int EMPLOYEE_NOT_FOUND = -1;
	private static final int ARRAY_CAPACITY_OF_FOUR = 4;
	private static final int DATE1_GREATER_THAN_DATE2 = 1;
	private static final int USE_AND_OPERATOR_WITH_NUMBER_THREE = 3;
	private static final int MIN_HOURS_WORKED = 0, MAX_HOURS_WORKED = 100;
	
	/**
	Default constructor to create an empty array
	*/
	public Company() {  
		emplist = new Employee [ARRAY_CAPACITY_OF_FOUR];
	}
	
	/**
	Helper method to find a an employee in the company database
	Checks if the profile of the employee entered is equal to the profile of any of the employees already 
	present in the array through the means of a for loop
	@param employee the employee to be found
	@return index number of the array at which the employee is present if found, -1 otherwise
	*/
	private int find(Employee employee) { 
		
		for(int i = 0; i < numEmployee; ++i) {
			
			if(emplist[i] != null) {
				
				if(employee.getProfile().equals(emplist[i].getProfile())) {
					
					return i; //Return the array index
				}
			}
		}
		return EMPLOYEE_NOT_FOUND;
	}
	
	/**
	Helper method to grow the capacity of the array by 4
	The array has an initial capacity of 4, and it is automatically increased whenever full
	A new array is created and all the elements from the smaller array are copied to the new array 
	*/
	private void grow() {
		
		Employee [] newArrayWithMoreCapacity = new Employee [emplist.length + ARRAY_CAPACITY_OF_FOUR];

		for(int i = 0; i < emplist.length; ++i) { 
			newArrayWithMoreCapacity[i] = emplist[i]; 
		} 
		emplist = newArrayWithMoreCapacity;
	}
	
	/**
	Adds the employee to the array of employees
	Before adding, the grow method is called to check if there is space in the array. Once there is space, 
	the employee is added
	@param employee the employee to be added
	@return true if employee is added, false otherwise
	*/
	public boolean add(Employee employee) {
		
		if((numEmployee & USE_AND_OPERATOR_WITH_NUMBER_THREE) == 0 
				&& numEmployee != 0) { //This checks if the number of employees are a multiple of 4
			grow();
		}
		
		if(find(employee) == EMPLOYEE_NOT_FOUND) {
			
			emplist[numEmployee] = employee;
			numEmployee = numEmployee + 1;
			return true;
		}
		
		else {
			return false;
		}
	}
	
	/**
	Removes the employee from the array of employees
	The find method is called to check if the employee exists in the company database 
	If the employee is found, the shiftArray method is called to get rid of the employee
	@param employee the employee to be removed
	@return true if employee is found and removed, false otherwise
	*/
	public boolean remove(Employee employee) {
				
		if(find(employee) == EMPLOYEE_NOT_FOUND) { 
			return false; 
		}
		
		else { 
			shiftArray(emplist, find(employee));
			numEmployee = numEmployee - 1;
			return true;
		}
		
	}
	
	/**
	Helper method to move the elements of the array one index down to remove the employee from the array
	The employee at the index needs to be removed. All elements to the right of it are moved one index to the left, 
	eventually getting rid of the employee at the index
	@param emplist the employees array to be shifted
	@param index the index of the employee to be removed
	*/
	public void shiftArray(Employee [] emplist, int index) {
		
		if(index >= 0) {
				
			for(int i = index+1; i < emplist.length; i++) {
					emplist[i-1] = emplist[i];
			}
			emplist[emplist.length - 1] = null;
		}
		else {
				//Nothing
		}
	}

	/**
	Set working hours for a part time employee
	@param employee the part time employee whose hours need to be set
	@return true if the hours are less than 100, nonnegative and if the employee is found, false otherwise
	*/
	public boolean setHours(Employee employee) {
		
		if(find(employee) == EMPLOYEE_NOT_FOUND || employee.getProfile().getHoursWorked() < MIN_HOURS_WORKED || 
				employee.getProfile().getHoursWorked() > MAX_HOURS_WORKED) {
			
			return false;
		}
		
		else {
			
			emplist[find(employee)].getProfile().setHoursWorked(employee.getProfile().getHoursWorked());
			return true;
		}
	}
	
	/**
	Process payments for all employees
	Based on whether the employee is a part time, full time or management employee, their payments are calculated
	*/
	public void processPayments() {
		
		for(int i = 0; i < numEmployee; ++i) {
			
			if(emplist[i] != null) { 
				
				emplist[i].calculatePayment();
			}
		}
	}
	
	/**
	This method iterates through the array of employees and prints them
	*/
	public void printArray() {
		
		for(int i = 0; i < numEmployee; ++i) {
			
			if(emplist[i] != null) {
				
				System.out.println(emplist[i]);
			}
		}
	}
	
	/**
	Prints the earning statements for all employees in the company
	*/
	public void print() {
		
		if(numEmployee == 0) {
			System.out.println("Employee database is empty.");
		}
		else {
			System.out.println("--Printing earning statements for all employees--");
			printArray();
		}
	}
	
	/**
	Prints the earning statements for all employees in the company by department
	*/
	public void printByDepartment() {
	
		if(numEmployee == 0) {
			System.out.println("Employee database is empty.");
		}
		
		else {
			
			System.out.println("--Printing earning statements by department--");
			
			for(int i = 0; i < numEmployee; ++i) {
				
				if(emplist[i] != null) {
					
					if(emplist[i].getProfile().getDepartment().equals("CS")) {
						System.out.println(emplist[i]);
					}
				}
			}
			
			for(int i = 0; i < numEmployee; ++i) {
				
				if(emplist[i] != null) {
					
					if(emplist[i].getProfile().getDepartment().equals("ECE")) {
						System.out.println(emplist[i]);
					}
				}
			}
			
			for(int i = 0; i < numEmployee; ++i) {
				
				if(emplist[i] != null) {
					
					if(emplist[i].getProfile().getDepartment().equals("IT")) {
						System.out.println(emplist[i]);
					}
				}
			}
			
		}
	}
	
	/**
	Prints the earning statements for all employees in the company by date 
	*/
	public void printByDate() {
		
		if(numEmployee == 0) {
			System.out.println("Employee database is empty.");
		}
		
		else {
			System.out.println("--Printing earning statements by date hired--");
			
			for(int i = 0; i < numEmployee - 1; i++)  {
				
				for(int j = 0; j < numEmployee - i - 1; j++) {
					
					Date date1 = emplist[j].getProfile().getDateHired();
					Date date2 = emplist[j+1].getProfile().getDateHired();
					
					int num = date1.compareTo(date2);
					
					if(num == DATE1_GREATER_THAN_DATE2) {
						
						Employee temp = emplist[j];
						emplist[j] = emplist[j+1];
						emplist[j+1] = temp;
					}
					else
						continue;
				}
			}
			printArray();
		}
	}
}
