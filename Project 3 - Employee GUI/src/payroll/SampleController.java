package payroll;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
This class reads all the commands from GUI
These GUI commands are provided access to the Company class which gives service to respective methods for each command 
@author Prachiti Atigre, Ujani Patel 
*/
public class SampleController {

    @FXML
    private Label Department;

    @FXML
    private DatePicker employeeDateHired;

    @FXML
    private ToggleGroup department, employeeType, Management;

    @FXML
    private Button clearButton, addEmployee, removeEmployee, setHours;
    
    @FXML
    private TextField employeeAnnualSalary, employeeHoursWorked, employeeHourlyRate, employeeName;
    
    @FXML
    private RadioButton CS, IT, ECE, fullTime, partTime, management, manager, departmentHead, director;
    
    @FXML
    private MenuItem readFrom, exportFile, printEmployee, printByDepartment, printByDateHired, compute;

    @FXML
    private TextArea messageArea;
    
    int numEmployees = 0;
    
    private static final int ARRAY_INDEX_0 = 0, ARRAY_INDEX_1 = 1, ARRAY_INDEX_2 = 2, 
    		ARRAY_INDEX_3 = 3, ARRAY_INDEX_4 = 4, ARRAY_INDEX_5 = 5;
	
    private static final int RESET_HOURS = 0;
    private static final int NO_EMPLOYEES = 0;
    private static final int FULLTIME_AND_NON_MANAGEMENT_ROLE = 0;
	private static final int MIN_PAY_RATE = 0, MIN_ANNUAL_SALARY = 0;
	private static final int MIN_HOURS_WORKED = 0, MAX_HOURS_WORKED = 100;
	private static final int MANAGER_ROLE = 1, DEPARTMENT_HEAD_ROLE = 2, DIRECTOR_ROLE = 3;
    
	Company com = new Company();
	
	/**
	This method disables certain selections from GUI to prevent invalid selections for certain EmployeeType.
	@param event on the event of selecting a radioButton, textField and Button
	*/
	@FXML
    void disableEmployeeType(ActionEvent event) {

		try {
			if(partTime.isSelected()) {
				
				employeeHourlyRate.setDisable(false);
				employeeHoursWorked.setDisable(false);
				employeeAnnualSalary.setDisable(true);
				manager.setDisable(true);
				departmentHead.setDisable(true);
				director.setDisable(true);
				setHours.setDisable(false);
			}
			
			if(fullTime.isSelected()) {
				employeeHourlyRate.setDisable(true);
				employeeHoursWorked.setDisable(true);
				employeeAnnualSalary.setDisable(false);
				manager.setDisable(true);
				departmentHead.setDisable(true);
				director.setDisable(true);
				setHours.setDisable(true);
			}
			
			if(management.isSelected()) {
				employeeHourlyRate.setDisable(true);
				employeeHoursWorked.setDisable(true);
				employeeAnnualSalary.setDisable(false);
				manager.setDisable(false);
				departmentHead.setDisable(false);
				director.setDisable(false);
				setHours.setDisable(true);
			}
		}
		
		catch(Exception e) {
			messageArea.appendText(e.toString());
		}
    }
	
	/**
	This method calls the addEmployee function to add Employee.
	Before being added, validity on name length, date, hourlyRate and annualSalary is checked.
	@param event on the event of selecting a the addEmployee Button to potentially add Employee.
	*/
	@FXML
	void add(ActionEvent event) {
		
		try {	
			
			String name = employeeName.getText();
			
			RadioButton selectedDepartment = (RadioButton) department.getSelectedToggle();
			String department = selectedDepartment.getText();
			    	
			RadioButton selectedEmployeeType = (RadioButton) employeeType.getSelectedToggle();
			String type = selectedEmployeeType.getText();
			
			LocalDate localDate = employeeDateHired.getValue();
			DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("MM/dd/YYY");
			String dateString = localDate.format(formatDate);
			Date dateHired = new Date(dateString);
			
			if(name.length() == 0) { 
				enterAllInfoMsg();
				employeeDateHired.setValue(null);
			}
			
			else {
			
				if(!dateHired.isValid()) {
					printInvalidDate(dateHired);
				}
				
				else {
					
					if(type.equals("Part Time")) {
						
						double hourlyRate = Double.parseDouble(employeeHourlyRate.getText());
						
						if(hourlyRate < MIN_PAY_RATE) {
							messageArea.appendText("Pay rate cannot be negative. \n");
						}
						
						else {
							Parttime employee = new Parttime(name, department, dateHired);
							
							employee.setHourlyRate(hourlyRate);
							
							addEmployee(employee);
							employeeDateHired.setValue(null);
						}
				    }
			
					else if(type.equals("Full Time")) {
						
						double annualSalary = Integer.parseInt(employeeAnnualSalary.getText());
			
						if(annualSalary < MIN_ANNUAL_SALARY) {
							messageArea.appendText("Salary cannot be negative. \n");
						}
			
						else {
							Fulltime employee = new Fulltime(name, department, dateHired);
							
							employee.setAnnualSalary(annualSalary);
							employee.setManagementRole(FULLTIME_AND_NON_MANAGEMENT_ROLE);
								
							addEmployee(employee);
							employeeDateHired.setValue(null);
						}
				    }
			
					else if(type.equals("Management")) {
				
						double annualSalary = Integer.parseInt(employeeAnnualSalary.getText());
					
						RadioButton selectedManagement = (RadioButton) Management.getSelectedToggle();
						String managementRole = selectedManagement.getText();
						
						if(annualSalary < MIN_ANNUAL_SALARY) {
							messageArea.appendText("Salary cannot be negative. \n");
						}
					
						else {
							Management employee = new Management(name, department, dateHired);
					        	
							employee.setAnnualSalary(annualSalary);
				
							if(managementRole.equals("Manager")) {
								employee.setManagementRole(MANAGER_ROLE);
					        }
							else if(managementRole.equals("Department Head")) {
								employee.setManagementRole(DEPARTMENT_HEAD_ROLE);
							}
							else if(managementRole.equals("Director")) {
								employee.setManagementRole(DIRECTOR_ROLE);
					        }
							
							addEmployee(employee);
							employeeDateHired.setValue(null);
						}
				    }
				}
			}
		}
		
		catch (Exception e) {
			enterAllInfoMsg();
		}
	}

	/**
	This method removes the Employee.
	The remove method calls the remove function from Company class. 
	@param event on the event of selecting the removeEmployee Button to potentially remove Employee. 
	*/
    @FXML
    void remove(ActionEvent event) {

    	try {	
			
			String name = employeeName.getText();
			
			if(name.length() == 0) {
				enterAllInfoMsg();
				employeeDateHired.setValue(null);
			}
			
			else {
		    	
				RadioButton selectedDepartment = (RadioButton) department.getSelectedToggle();
		    	String departmentCode = selectedDepartment.getText();
		    	
		    	LocalDate localDate = employeeDateHired.getValue();
		    	DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("MM/dd/YYY");
		    	String dateString = localDate.format(formatDate);
		    	Date date = new Date(dateString);
		    	
		    	Employee employee = new Employee(name, departmentCode, date);
		    	
		    	if(com.remove(employee)) {
		    		numEmployees = numEmployees - 1;
		    		employeeDateHired.setValue(null);
					messageArea.appendText("Employee removed. \n");
		    	}
		    	else {
		    		
		    		if(numEmployees == NO_EMPLOYEES)
		    			messageArea.appendText("Employee database is empty. \n");
					else
						messageArea.appendText("Employee does not exist. \n");
		    	}
			}
    	}
    	
    	catch (Exception e) {
    		enterAllInfoMsg();
		}
    }
    
    /**
	This method sets the Hours for a PartTime Employee.
	Before setting the Hours, validity of date, and hoursWorked are checked.  
	@param event on the event of selecting the setHours Button, hours are set for PartTime Employee if valid. 
	*/
    @FXML
    void setHours(ActionEvent event) {
    	
    	try {	
			
			String name = employeeName.getText();
			
			if(name.length() == 0) {
				enterAllInfoMsg();
				employeeDateHired.setValue(null);
			}
			
			else {
    	
		    	RadioButton selectedDepartment = (RadioButton) department.getSelectedToggle();
		    	String departmentCode = selectedDepartment.getText();
		    	
		    	LocalDate localDate = employeeDateHired.getValue();
		    	DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("MM/dd/YYY");
		    	String dateString = localDate.format(formatDate);
		    	Date dateHired = new Date(dateString);
		    	
		    	boolean checkDate = dateHired.isValid();
		    	
		    	if(!checkDate) {
		    		printInvalidDate(dateHired);
				}
		    	
		    	RadioButton selectedEmloyeeType = (RadioButton) employeeType.getSelectedToggle();
		    	String type = selectedEmloyeeType.getText();
		    	
		    	int hoursWorked = Integer.parseInt(employeeHoursWorked.getText());
		    	
				if(numEmployees == NO_EMPLOYEES) {
					messageArea.appendText("Employee database is empty. \n");
				}
				
				else if(type.equals("Part Time"))
				{
					Parttime employee = new Parttime(name, departmentCode, dateHired);
					employee.setHoursWorked(hoursWorked);
					
					if(com.setHours(employee)) {
						
						messageArea.appendText("Working hours set. \n");
						employeeDateHired.setValue(null);
					}
					
					else {	
						
						if(hoursWorked < MIN_HOURS_WORKED) {
							messageArea.appendText("Working hours cannot be negative. \n");
						}
						else if(hoursWorked > MAX_HOURS_WORKED) {
							messageArea.appendText("Invalid Hours: over 100. \n");
						}
						else {
							employee.setHoursWorked(RESET_HOURS);
							messageArea.appendText("Employee does not exist. \n");
						}
					}
				}
			}
    	}

    	catch (Exception e) {
    		enterAllInfoMsg();
		}
    }

    /**
	This method computes payment for Employees. 
	The method calls the processPayments function which computes Employee payments.   
	@param event upon selecting the compute menuItem, Employees payments are computed.  
	*/
    @FXML
    void compute(ActionEvent event) {

    	if(numEmployees == NO_EMPLOYEES)
    		messageArea.appendText("Employee database is empty. \n");
		
    	else {
			com.processPayments();
			messageArea.appendText("Calculation of employee payments is done. \n");
		}
    }

    /**
   	This method clears the TextArea.    
   	@param event on the event of selecting the clearButton, TeaxtArea is cleared. 
   	*/
    @FXML
    void clear(ActionEvent event) {
    	messageArea.setText("");
    }

    /**
   	This method exports a textFile. 
   	The textFile includes Employee name, department,date, employeeType, hourlyRate and Hours Worked.
   	@param event on the event of selecting the export menuItem, the textFile is exported. 
   	*/
    @FXML
    void exportFile(ActionEvent event) {

    	if(numEmployees == 0) {
    		messageArea.setText("Employee database is empty. \n");
    	}
    	
    	else {	
	    	try {
	    		FileChooser choose = new FileChooser();
	    		choose.setTitle("Choose a File to Export");
	    		choose.getExtensionFilters().addAll(
	    				new ExtensionFilter("Text Files", "*.txt"),
	    				new ExtensionFilter("All Files", "*.*"));
	    		
	    		Stage mainStage = new Stage(); 
	    		File fileExport = choose.showSaveDialog(mainStage);
	    		messageArea.appendText(com.exportDatabase(fileExport));
	    	}
	    	
	    	catch(Exception e) {
	    		messageArea.appendText(e.toString());
	    	}
    	}
    }

    /**
   	This method takes in a ImportFile . 
   	The ImportFile will call the function addEmployee and will add the Employees listed in the ImportFile.
   	@param event on the event of selecting the import menuItem, the choose textFile will be imported. 
   	*/
    @FXML
    void importFile(ActionEvent event) {
    	
    	String name, departmentCode, dateHired, hourlyRate, annualSalary, managementRole, line, command = null;
    	
    	FileChooser choose = new FileChooser();
    	choose.setTitle("Choose a File to Import");
    	choose.getExtensionFilters().addAll(
    	new ExtensionFilter("Text Files", "*.txt"),
    	new ExtensionFilter("Text Files", "*.*"));
    	Stage mainStage = new Stage();
    	File textFile = choose.showOpenDialog(mainStage);
    	
    	try {
    		
    		Scanner sc = new Scanner(textFile);
    		
    		while(sc.hasNextLine()) {
    			
    			line = sc.nextLine();
    			String[] splited = line.split(",");
				command = splited[ARRAY_INDEX_0];	
				
				if(command.equals("P")) {
				
					name = splited[ARRAY_INDEX_1];
					departmentCode = splited[ARRAY_INDEX_2];
					dateHired = splited[ARRAY_INDEX_3];
					hourlyRate = splited[ARRAY_INDEX_4];
					
					Date date = new Date(dateHired);
					
					Parttime employee = new Parttime(name, departmentCode, date);
					employee.setHourlyRate(Double.parseDouble(hourlyRate));
					
					addEmployee(employee);		
					employeeDateHired.setValue(null);
				}
				
				else if(command.equals("F")) {
					
					name = splited[ARRAY_INDEX_1];
					departmentCode = splited[ARRAY_INDEX_2];
					dateHired = splited[ARRAY_INDEX_3];
					annualSalary = splited[ARRAY_INDEX_4];
					
					Date date = new Date(dateHired);
					Fulltime employee = new Fulltime(name, departmentCode, date);
					
					employee.setAnnualSalary(Double.parseDouble(annualSalary));
					
					addEmployee(employee);	
					employeeDateHired.setValue(null);
				}
				
				else if(command.equals("M")) {
				
					name = splited[ARRAY_INDEX_1];
					departmentCode = splited[ARRAY_INDEX_2];
					dateHired = splited[ARRAY_INDEX_3];
					annualSalary = splited[ARRAY_INDEX_4];
					managementRole = splited[ARRAY_INDEX_5];
					
					if(Double.parseDouble(managementRole) == MANAGER_ROLE 
							|| Double.parseDouble(managementRole) == DIRECTOR_ROLE
								|| Double.parseDouble(managementRole) == DEPARTMENT_HEAD_ROLE ) {
						
						Date date = new Date(dateHired);
						Management employee = new Management(name, departmentCode, date);
						
						employee.setAnnualSalary(Double.parseDouble(annualSalary));
						employee.setManagementRole(Double.parseDouble(managementRole));
						
						addEmployee(employee);
						employeeDateHired.setValue(null);
					}
				}	
    		}
    		sc.close();
    	} 
    	
    	catch (FileNotFoundException e) {
    		messageArea.appendText(e.toString());
    	}
    }

    /**
   	This method calls the print function in the Company class and prints 
   	the earning statements for all employees in the company in the TextArea
   	@param event on the event of selecting the printEmployee menuItem. 
   	*/
    @FXML
    void printEmployee(ActionEvent event) {
    	
    	if(numEmployees == 0) {
    		messageArea.appendText("Employee database is empty. \n");
		}
    	
    	else {
    		messageArea.appendText("--Printing earning statements for all employees-- \n");
    		
    		for(int i = 0; i < numEmployees; ++i) {
    			messageArea.appendText(com.print(i) + "\n");
    		}
    	}
    }
    
    /**
   	This method calls the printByDate function in the Company class and prints 
   	the earning statements for all employees in the company by date in the TextArea.
   	@param event on the event of selecting the printByDateHired menuItem. 
   	*/
    @FXML
    void printByDateHired(ActionEvent event) {
    	
    	if(numEmployees == 0) {
    		messageArea.appendText("Employee database is empty. \n");
		}
    	
    	else {
    		com.printByDate();
    		messageArea.appendText("--Printing earning statements by date hired-- \n");

    		for(int i = 0; i < numEmployees; ++i) {
    			messageArea.appendText(com.print(i) + "\n");
    		}
    	}	
    }

    /**
   	This method calls the printByDepartment function in the Company class and prints 
   	the earning statements for all employees in the company by department in the TextArea
   	@param event on the event of selecting the printByDepartment menuItem. 
   	*/
    @FXML
    void printByDepartment(ActionEvent event) {

    	if(numEmployees == 0) {
    		messageArea.appendText("Employee database is empty. \n");
		}
    	
    	else {
    		
    		messageArea.appendText("--Printing earning statements by department-- \n");
    	
    		for(int i = 0; i < numEmployees; ++i) {
    			messageArea.appendText(com.printByDepartment(i) + "\n");
    		}
    	}
    }

    /**
   	This method calls the add function in the Company class to add the Employee.
   	@param employee employee to be added 
   	*/
    public void addEmployee(Employee employee) {
    	
    	if(com.add(employee)) {
			numEmployees = numEmployees + 1;
			messageArea.appendText("Employee added. \n");
		}
		else {
			messageArea.appendText("Employee is already in the list. \n");
		}
    }
    
    /**
	This method prints "mm/dd/yyyy is not a valid date!" whenever the user inputs an invalid date
	@param dateHired the date hired of the employee
	*/
    public void printInvalidDate(Date dateHired) {
    	messageArea.appendText(dateHired.getMonth() + "/" + dateHired.getDay() + "/" 
				+ dateHired.getYear() + " is not a valid date! \n");
    }
    
    /**
    This method prints the message when the user doesn't fill all the information correctly
    */
    public void enterAllInfoMsg() {
    	messageArea.appendText("Please enter all information correctly. \n");
    }
}