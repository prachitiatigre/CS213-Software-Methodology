package EmployeePayroll;
import java.util.Scanner; 

/**
This class reads all the commands from the console
These commands are provided access to the Company class which gives service to respective methods for each command 
@author Prachiti Atigre, Ujani Patel 
*/
public class PayrollProcessing {

	private static final int ARRAY_INDEX_0 = 0, ARRAY_INDEX_1 = 1, ARRAY_INDEX_2 = 2, ARRAY_INDEX_3 = 3, ARRAY_INDEX_4 = 4,
			ARRAY_INDEX_5 = 5;
	
	private static final int MIN_PAY_RATE = 0, MIN_ANNUAL_SALARY = 0;
	private static final int PARTTIME_AND_NON_MANAGEMENT_ROLE = 4, FULLTIME_AND_NON_MANAGEMENT_ROLE = 0;
	private static final int MANAGER_ROLE = 1, DEPARTMENT_HEAD_ROLE = 2, DIRECTOR_ROLE = 3;
	private static final int NO_EMPLOYEES = 0;
	private static final int MIN_HOURS_WORKED = 0, MAX_HOURS_WORKED = 100;
	private static final int RESET_HOURS = 0;
	
	/**
	This method is called by the RunProject2 class
	As the method runs, it will read in the commands by the user and perform accordingly
	*/
	public void run() {
		
		String line, name, departmentCode, dateHired, hourlyRate, annualSalary, hoursWorked, managementRole;
		String command = null;
		int numEmployees = 0;
		
		Scanner sc = new Scanner(System.in); //The Scanner Class is used to read in the user inputs from the console
		System.out.println("Payroll Processing starts.");
		line = sc.nextLine();		
		
		Company com = new Company();
		
		while(!line.equals("Q")) { //Runs until the user enters the command Q to quit
			
			try {	
			
				String[] splited = line.split("\\s+");
				
				command = splited[ARRAY_INDEX_0];
				
				if(command.equals("AP")) {
						
					name = splited[ARRAY_INDEX_1];
					departmentCode = splited[ARRAY_INDEX_2];
					dateHired = splited[ARRAY_INDEX_3];
					hourlyRate = splited[ARRAY_INDEX_4];
					
					if(departmentCode.equals("CS") || departmentCode.equals("ECE") || departmentCode.equals("IT")) {
						
						if(Double.parseDouble(hourlyRate) < MIN_PAY_RATE) {
							System.out.println("Pay rate cannot be negative.");
						}
						else {
							Date date = new Date(dateHired);
							Parttime employee = new Parttime(name, departmentCode, date);
							
							employee.getProfile().setHourlyRate(Double.parseDouble(hourlyRate));
							employee.getProfile().setManagementRole(PARTTIME_AND_NON_MANAGEMENT_ROLE);
							
							boolean checkDate = employee.getProfile().getDateHired().isValid();
								
							if(!checkDate) {
								
								printInvalidDate(employee);
								
							}
							else {
								if(com.add(employee)) {
									numEmployees = numEmployees + 1;
									System.out.println("Employee added.");		
								}
								else {
									System.out.println("Employee is already in the list.");
								}
							}
						}
					}
					
					else {
						System.out.println("'" + departmentCode + "' is not a valid department code.");
					}
				}
					
				else if(command.equals("AF")) {
					
					name = splited[ARRAY_INDEX_1];
					departmentCode = splited[ARRAY_INDEX_2];
					dateHired = splited[ARRAY_INDEX_3];
					annualSalary = splited[ARRAY_INDEX_4];
						
					if(departmentCode.equals("CS") || departmentCode.equals("ECE") || departmentCode.equals("IT")) {
						if(Double.parseDouble(annualSalary) < MIN_ANNUAL_SALARY) {
							System.out.println("Salary cannot be negative.");
						}
						else {
							Date date = new Date(dateHired);
							Fulltime employee = new Fulltime(name, departmentCode, date);
							
							employee.getProfile().setAnnualSalary(Double.parseDouble(annualSalary));
							employee.getProfile().setManagementRole(FULLTIME_AND_NON_MANAGEMENT_ROLE);	
							
							boolean checkDate = employee.getProfile().getDateHired().isValid();
								
							if(!checkDate) {
								printInvalidDate(employee);
							}
							else {
								if(com.add(employee)) { 
									numEmployees = numEmployees + 1;
									System.out.println("Employee added.");
								}
								else {
									System.out.println("Employee is already in the list.");
								}
							}
						}
					}
					
					else {
						System.out.println("'" + departmentCode + "' is not a valid department code.");
					}
				}
				
				else if(command.equals("AM")) {
					
					name = splited[ARRAY_INDEX_1];
					departmentCode = splited[ARRAY_INDEX_2];
					dateHired = splited[ARRAY_INDEX_3];
					annualSalary = splited[ARRAY_INDEX_4];
					managementRole = splited[ARRAY_INDEX_5];
					
					if(departmentCode.equals("CS") || departmentCode.equals("ECE") || departmentCode.equals("IT")) {
						if(Double.parseDouble(annualSalary) < MIN_ANNUAL_SALARY) {
							System.out.println("Salary cannot be negative.");
						}
						
						else {
							
							if(Double.parseDouble(managementRole) == MANAGER_ROLE 
									|| Double.parseDouble(managementRole) == DEPARTMENT_HEAD_ROLE 
										|| Double.parseDouble(managementRole) == DIRECTOR_ROLE) {
								
								Date date = new Date(dateHired);
								Management employee = new Management(name, departmentCode, date);
							
								employee.getProfile().setAnnualSalary(Double.parseDouble(annualSalary));
								employee.getProfile().setManagementRole(Double.parseDouble(managementRole));
			
								boolean checkDate = employee.getProfile().getDateHired().isValid();
								
								if(!checkDate) {
									printInvalidDate(employee);
								}
								else {
									if(com.add(employee)) {
										numEmployees = numEmployees + 1;
										System.out.println("Employee added.");		
									}
									else {
										System.out.println("Employee is already in the list.");
									}
								}
							}
							else {
								System.out.println("Invalid management code.");
							}
						}
					}
					else {
						System.out.println("'" + departmentCode + "' is not a valid department code.");
					}
				}
				
				else if(command.equals("R")) { 
						
					name = splited[ARRAY_INDEX_1];
					departmentCode = splited[ARRAY_INDEX_2];
					dateHired = splited[ARRAY_INDEX_3];
						
					if(departmentCode.equals("CS") || departmentCode.equals("ECE") || departmentCode.equals("IT")) {
						
						Date date = new Date(dateHired);
						Employee employee = new Employee(name,departmentCode, date);
							
						if(com.remove(employee)) {
								numEmployees = numEmployees - 1;
								System.out.println("Employee removed.");
						}
						else {
							
							if(numEmployees == NO_EMPLOYEES)
								System.out.println("Employee database is empty.");
							else
								System.out.println("Employee does not exist.");
						}
					}
					
					else {
						System.out.println("'" + departmentCode + "' is not a valid department code." + !departmentCode.equals("CS"));
					}
				}
				
				else if(command.equals("C")) { 
					
					if(numEmployees == NO_EMPLOYEES)
						System.out.println("Employee database is empty.");
					else {
						com.processPayments();
						System.out.println("Calculation of employee payments is done.");
					}
				}
				
				else if(command.equals("S")) {
					
					if(numEmployees == NO_EMPLOYEES)
						System.out.println("Employee database is empty.");
					
					else {
						
						name = splited[ARRAY_INDEX_1];
						departmentCode = splited[ARRAY_INDEX_2];
						dateHired = splited[ARRAY_INDEX_3];
						hoursWorked = splited[ARRAY_INDEX_4];
						
						if(departmentCode.equals("CS") || departmentCode.equals("ECE") || departmentCode.equals("IT")) {
							int num = Integer.parseInt(hoursWorked);
							Date date = new Date(dateHired);
							Parttime employee = new Parttime(name, departmentCode, date);
							
							employee.getProfile().setHoursWorked(num);
							
							if(com.setHours(employee)) {
								System.out.println("Working hours set.");
							}
							
							else {	
							
								if(Double.parseDouble(hoursWorked) < MIN_HOURS_WORKED)
									System.out.println("Working hours cannot be negative.");
								
								else if(Double.parseDouble(hoursWorked) > MAX_HOURS_WORKED)
									System.out.println("Invalid Hours: over 100.");
								
								else { 
									employee.getProfile().setHoursWorked(RESET_HOURS);
									System.out.println("Employee does not exist.");
								}
							}
						}
						
						else {
							System.out.println("'" + departmentCode + "' is not a valid department code.");
						}
					}
				}
				
				else if(command.equals("PA")) { 
					
					com.print();
				}
				
				else if(command.equals("PH")) { 
					
					com.printByDate();
				}
				
				else if(command.equals("PD")) { 
					
					com.printByDepartment();
				}
				
				else if(command.equals("ap") || command.equals("af") || command.equals("am") || command.equals("r") || command.equals("c")
						|| command.equals("s") || command.equals("pa") || command.equals("pd") || command.equals("ph") || command.equals("q")) { 
					System.out.println("Command '" + command + "' not supported!");
				}
				line = sc.nextLine();
			}		
			
			catch(Exception NoSuchElementException) {
				line = sc.nextLine();
			}
		}
		System.out.println("Payroll Processing completed.");
		sc.close();
	}
	
	/**
	This method prints "mm/dd/yyyy is not a valid date!" whenever the user inputs an invalid date
	@param employee the Employee object
	*/
	public void printInvalidDate(Employee employee) {
		System.out.println(employee.getProfile().getDateHired().getMonth() + "/" + employee.getProfile().getDateHired().getDay() 
				+ "/" + employee.getProfile().getDateHired().getYear() + " is not a valid date!");
	}
}