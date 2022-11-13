package EmployeePayroll;
import static org.junit.Assert.*;
import org.junit.Test;

/**
This class is a JUnit test case class 
It tests the add, remove, setHours and calculatePayment method for different scenarios 
@author Prachiti Atigre, Ujani Patel 
*/
public class JUnitTestCase {

	Company myCompany = new Company();
	
	// ---------------------------------- Add Test Cases ----------------------------------
	
	@Test
	/**
	Adding 2 employees with the different name, same department, different date 
	Valid
	assertTrue = green
	*/
	public void testAddate1() {
		
		Employee employee1 = new Employee("Doe,Jane", "IT", new Date("6/1/1750"));
		Employee employee2 = new Employee("Patel,Ujani", "IT", new Date ("2/22/2021"));
		
		assertTrue(myCompany.add(employee1));
		assertTrue(myCompany.add(employee2));
	}
	
	@Test
	/**
	Adding employee with the same name, same department, same date 
	Invalid 
	assertFalse = green
	*/
	public void testAddate2() {
		
		Employee employee1 = new Employee("Doe,Jane", "IT", new Date("6/1/2020"));
		Employee employee2 = new Employee("Doe,Jane", "IT", new Date("6/1/2020"));
		
		assertTrue(myCompany.add(employee1)); 
		assertFalse(myCompany.add(employee2));
	}
	
	@Test
	/**
	Adding employee with the same name, different department, same date
	Valid 
	assertTrue = green; 
	*/
	public void testAddate3() {
		
		Employee employee1 = new Employee("Doe,Jane", "IT", new Date("6/1/2020"));
		Employee employee2 = new Employee("Doe,Jane", "CS", new Date("6/1/2020"));
		
		assertTrue(myCompany.add(employee1)); 
		assertTrue(myCompany.add(employee2));
	}
	
	@Test
	/**
	Adding employee with the same name, different department, different date, different employment type
	Valid 
	assertTrue = green; 
	*/
	public void testAddate4() {
	
		Parttime employee1 = new Parttime("Doe,Jane", "CS", new Date("7/1/2020"));
		Fulltime employee2 = new Fulltime("Doe,Jane", "ECE", new Date ("1/1/2005"));
		Management employee3 = new Management("Doe,Jane", "IT", new Date ("2/28/2010"));
		
		assertTrue(myCompany.add(employee1)); 
		assertTrue(myCompany.add(employee2));
		assertTrue(myCompany.add(employee3)); 	
	}
	
	@Test
	/**
	Adding employee with the same name, same department, different date, different employment type
	Valid
	assertTrue = green; 
	*/
	public void testAddate5() {
		
		Parttime employee1 = new Parttime("Doe,Jane", "CS", new Date("7/1/2020"));
		Fulltime employee2 = new Fulltime("Doe,Jane", "CS", new Date("2/29/2020"));
		
		assertTrue(myCompany.add(employee1)); 
		assertTrue(myCompany.add(employee2));	
	}
	
	@Test
	/**
	Adding employee with the same name, same department, same date, different employee type
	Valid
	assertTrue = green; 
	*/
	public void testAddate6() {

		Parttime employee1 = new Parttime("Doe,Jane", "CS", new Date("7/1/2020"));
		Fulltime employee2 = new Fulltime("Doe,Jane", "CS", new Date("2/29/2020"));
		
		assertTrue(myCompany.add(employee1)); 
		assertTrue(myCompany.add(employee2));	
	}
	
	
	@Test
	/**
	Adding two employees with same name, same department, same date and same employment type 
	Valid
	assertTrue(employee1, employee3, employee5) = green
	Invalid 
	assertFalse(employee2, employee4, employee6) = green
	*/
	public void testAdd7() {
		
		Parttime employee1 = new Parttime("Doe,Jane", "CS", new Date("7/1/2020"));
		Parttime employee2 = new Parttime("Doe,Jane", "CS", new Date("7/1/2020"));
		
		Fulltime employee3 = new Fulltime("Doe,Jane", "ECE", new Date("1/1/2005"));
		Fulltime employee4 = new Fulltime("Doe,Jane", "ECE", new Date("1/1/2005"));
		
		Management employee5 = new Management("Doe,Jane", "IT", new Date("2/28/2010"));
		Management employee6 = new Management("Doe,Jane", "IT", new Date("2/28/2010"));
		
		assertTrue(myCompany.add(employee1)); 
		assertTrue(myCompany.add(employee3));
		assertTrue(myCompany.add(employee5)); 
		
		assertFalse(myCompany.add(employee2)); 
		assertFalse(myCompany.add(employee4));
		assertFalse(myCompany.add(employee6));
	}
	
	@Test
	/**
	Adding employee with the same name, same department, different date, same employment type
	Valid
	assertTrue = green
	*/
	public void testAdd8() {
		
		Fulltime employee1 = new Fulltime("Doe,Jane", "ECE", new Date("7/1/2020"));
		Fulltime employee2 = new Fulltime("Doe,Jane", "ECE", new Date("2/29/2020"));
		
		assertTrue(myCompany.add(employee1)); 
		assertTrue(myCompany.add(employee2));		
	}
	
	@Test
	/**
	Adding employee with the same name, same department, same date, same employment type
	Valid 
	assertTrue(employee1) == green 
	Invalid 
	assertFlase(employee2) == green
	*/
	public void testAdd9() {
		
		Management employee1 = new Management("Brown,Jacob", "ECE", new Date("9/9/2001"));
		Management employee2 = new Management("Brown,Jacob", "ECE", new Date("9/9/2001"));
		
		assertTrue(myCompany.add(employee1)); 
		assertFalse(myCompany.add(employee2));				
	}
	
	@Test
	/**
	Adding 3 employees with different name, different department, different date, different employment type
	Valid 
	assertTrue(employee1,employee2,employee3) == green
	*/
	public void testAddate10() {
		
		Parttime employee2 = new Parttime("Potter,Harry", "IT", new Date("12/30/2001"));
		Management employee1 = new Management("Weasley,Ron", "ECE", new Date("9/9/2001"));
		Fulltime employee3 = new Fulltime("Granger,Hermoine", "CS", new Date("5/5/2010"));
		
		assertTrue(myCompany.add(employee1));
		assertTrue(myCompany.add(employee2));
		assertTrue(myCompany.add(employee3));
	}
	
	@Test
	/**
	Adding 2 employees with different name, same department, different date, same employment type
	Valid 
	assert True(employee 1, employee2) == green; 
	*/
	public void testAddate11() {
	
		Parttime employee1 = new Parttime("Brown Sarah", "IT", new Date("4/5/2000"));
		Parttime employee2 = new Parttime("John,Jacob", "IT", new Date("4/5/2000"));
		
		assertTrue(myCompany.add(employee1));
		assertTrue(myCompany.add(employee2));
	}
	
	@Test
	/**
	Adding 6 employees to check if container grows when number of employees is more than 4  
	*/
	public void testAdd12() {
		
		Parttime employee1 = new Parttime("Brown Sarah", "IT", new Date("4/5/2000"));
		Parttime employee2 = new Parttime("John,Jacob", "IT", new Date("4/5/2000"));
		Management employee3 = new Management("Brown,Jacob", "ECE", new Date("9/9/2010"));
		Fulltime employee4 = new Fulltime("John,Apple", "CS", new Date("01/01/1999"));
		Management employee5 = new Management("Potter,Harry","ECE", new Date("9/9/2010"));
		Fulltime employee6 = new Fulltime("Johnson,Joe", "IT", new Date("02/02/2002"));
		
		assertTrue(myCompany.add(employee1));
		assertTrue(myCompany.add(employee2));
		assertTrue(myCompany.add(employee3));
		assertTrue(myCompany.add(employee4));
		assertTrue(myCompany.add(employee5));
		assertTrue(myCompany.add(employee6));	
	}
	
	// ---------------------------------- Remove Test Cases ----------------------------------	
	
	@Test
	/**
	Add an employee, remove that employee
	Valid 
	assertTrue(employee) == green
	*/
	public void testRemove1() {
		
		Parttime employee1 = new Parttime("Jane,Doe", "IT", new Date("6/1/2010")); 
		
		assertTrue(myCompany.add(employee1)); 
		assertTrue(myCompany.remove(employee1));
	}
	
	@Test
	/**
	Remove an employee that was never added
	Valid 
	assertFalse(employee) == green 
	*/
	public void testRemove2() {
	
		Fulltime employee1 = new Fulltime("Granger,Hermoine","CS", new Date("6/1/2010"));
		
		assertFalse(myCompany.remove(employee1)); 
	}
	
	@Test
	/** 
	Add 3 employees, remove an employee that was never added;
	Valid: 
	assertTrue(employee1,employee2,employee4) == green; 
	Invalid 
	assertFalse(employeee3) == green; 
	*/
	public void testRemove3() {

		Parttime employee3 = new Parttime("Doe,Joe", "CS", new Date("06/01/1999"));
		Fulltime employee1 = new Fulltime("Brown,John","IT", new Date("08/09/2009"));
		Parttime employee2 = new Parttime("Potter,Harry", "CS", new Date("05/04/2003"));
		Management employee4 = new Management("Brown,Jacob","ECE", new Date("03/01/2011"));
		
		assertTrue(myCompany.add(employee1));
		assertTrue(myCompany.add(employee2));
		assertTrue(myCompany.add(employee4));
		assertFalse(myCompany.remove(employee3));	
		
	}
	
	@Test
	/**
	Add an employee, remove the employee, add the employee back
	Valid
	assertTrue(employee1) == green
	*/
	public void testRemove4() {
	
		Fulltime employee1 = new Fulltime("Brown,John", "IT", new Date("02/02/1987"));
	
		assertTrue(myCompany.add(employee1));
		assertTrue(myCompany.remove(employee1));
		assertTrue(myCompany.add(employee1));	
	}
	
	@Test
	/**
	Add an employee, add the same employee again. Remove that employee twice
	*/
	public void testRemove5() {
		
		Parttime employee1 = new Parttime("Morgan,Joe", "ECE", new Date("05/05/1986"));
		
		assertTrue(myCompany.add(employee1));
		assertFalse(myCompany.add(employee1));
		assertTrue(myCompany.remove(employee1)); 
		assertFalse(myCompany.remove(employee1));
	}
	
	@Test
	/**
	Remove an employee twice without adding. Then add the employee.
	*/
	public void testRemove6() {
		
		Parttime employee1 = new Parttime("Morgan,Joe", "ECE", new Date("05/05/1986"));
		
		assertFalse(myCompany.remove(employee1));
		assertFalse(myCompany.remove(employee1));
		assertTrue(myCompany.add(employee1));
	}
	
	@Test
	/**
	Add multiple employees with different employment types and remove one of them
	*/
	public void testRemove7() {
		
		Fulltime employee1 = new Fulltime("Brown,John","IT", new Date("05/05/1986"));
		Parttime employee2 = new Parttime("Morgan,Joe","ECE",new Date("02/02/1987"));
		Management employee3 = new Management("Brown,Jacob","ECE", new Date("06/01/1999"));
		Management employee4 = new Management("Brown,John","IT", new Date("06/01/1999"));
		
		assertTrue(myCompany.add(employee1));
		assertTrue(myCompany.add(employee2));
		assertTrue(myCompany.add(employee3));
		assertTrue(myCompany.add(employee4));
		assertTrue(myCompany.remove(employee2));
	}
	
	// ---------------------------------- Set Hours Test Cases ----------------------------------
	
	@Test
	/**
	Set hours worked to 100
	Valid
	*/
	public void testSetHours1() {
		
		Parttime employee1 = new Parttime("Morgan,Joe", "ECE", new Date("05/05/1986"));
		
		employee1.getProfile().setHoursWorked(100);
		
		assertTrue(myCompany.add(employee1));
		assertTrue(myCompany.setHours(employee1));
	}
	
	@Test
	/**
	Set hours worked to 111
	Invalid because hours exceed 100
	*/
	public void testSetHours2() {
		
		Parttime employee1 = new Parttime("Brown,Joe", "IT", new Date("02/02/2002"));
		
		employee1.getProfile().setHoursWorked(111);
		
		assertTrue(myCompany.add(employee1));
		assertFalse(myCompany.setHours(employee1));	
	}
	
	@Test
	/**
	Set hours worked to 0
	Valid
	*/
	public void testSetHours3() {
		
		Parttime employee1 = new Parttime("Brown,Kate", "ECE", new Date("09/02/2002"));
		
		employee1.getProfile().setHoursWorked(0);
		
		assertTrue(myCompany.add(employee1));
		assertTrue(myCompany.setHours(employee1));	
	}
	
	@Test
	/**
	Set hours worked to -5
	Invalid because hours cannot be negative
	*/
	public void testSetHours4() {
		
		Parttime employee1 = new Parttime("Brown,John", "ECE", new Date("09/02/2002"));
		
		employee1.getProfile().setHoursWorked(-5);
		
		assertTrue(myCompany.add(employee1));
		assertFalse(myCompany.setHours(employee1));	
	}
	
	@Test
	/**
	Set hours worked to 80
	Valid because in the range 0-100
	*/
	public void testSetHours5() {
		
		Parttime employee1 = new Parttime("Roger,John", "CS", new Date("12/30/2001"));
		
		employee1.getProfile().setHoursWorked(80);
		
		assertTrue(myCompany.add(employee1));
		assertTrue(myCompany.setHours(employee1));	
	}
	
	@Test
	/**
	Set hours worked to 5
	Invalid because employee not added
	*/
	public void testSetHours6() {
		
		Parttime employee1 = new Parttime("Rain,Summer", "IT", new Date("04/01/1999"));
		
		employee1.getProfile().setHoursWorked(5);
		
		assertFalse(myCompany.setHours(employee1));	
	}

	// ---------------------------------- Calculate Payment Test Cases ----------------------------------
	
	@Test
	/**
	Calculating payment for a full time employee with a manager role
	*/
	public void testCalculatePayment1() {
		
		Management employee1 = new Management("Brown,John", "IT", new Date("1/1/2001"));
		
		employee1.getProfile().setAnnualSalary(85000.00);
		employee1.getProfile().setManagementRole(1);
		
		assertTrue(myCompany.add(employee1));
		employee1.calculatePayment();
		double payment = employee1.getPayment();
		assertEquals(3461.53, payment,0.01);
	}
	
	@Test
	/**
	Calculating payment for a full time employee with a department head role
	*/ 
	public void testCalculatePayment2() {
		
		Management employee1 = new Management("Morgan,James", "ECE", new Date("2/4/2006"));
		
		employee1.getProfile().setAnnualSalary(85000.00);
		employee1.getProfile().setManagementRole(2);
		
		assertTrue(myCompany.add(employee1));
		employee1.calculatePayment();
		double payment = employee1.getPayment();
		assertEquals(3634.62, payment,0.01);
	}
	
	@Test
	/**
	Calculating payment for a full time employee with a director role
	*/ 
	public void testCalculatePayment3() {
		
		Management employee1 = new Management("Morgan,James", "ECE", new Date("2/4/2006"));
		
		employee1.getProfile().setAnnualSalary(85000.00);
		employee1.getProfile().setManagementRole(3);
		
		assertTrue(myCompany.add(employee1));
		employee1.calculatePayment();
		double payment = employee1.getPayment();
		assertEquals(3730.77, payment,0.01);
	}
	
	@Test
	/**
	Calculating payment for a full time employee with a manager role
	Invalid because calculation is not correct
	*/ 
	public void testCalculatePayment4() {
		
		Management employee1 = new Management("Morgan,James", "ECE", new Date("2/4/2006"));
		
		employee1.getProfile().setAnnualSalary(85000.00);
		employee1.getProfile().setManagementRole(1);
		
		assertTrue(myCompany.add(employee1));
		employee1.calculatePayment();
		double payment = employee1.getPayment();
		assertNotEquals(3000.51, payment);
	}
	
	@Test
	/**
	Calculating payment for a full time employee with a department head role
	Invalid because calculation is not correct
	*/
	public void testCalculatePayment5() {
		
		Management employee1 = new Management("Morgan,James", "ECE", new Date("2/4/2006"));
		
		employee1.getProfile().setAnnualSalary(85000.00);
		employee1.getProfile().setManagementRole(2);
		
		assertTrue(myCompany.add(employee1));
		employee1.calculatePayment();
		double payment = employee1.getPayment();
		assertNotEquals(4000, payment);
	}
	
	@Test
	/**
	Calculating payment for a full time employee with a director role
	Invalid because calculation is not correct
	*/
	public void testCalculatePayment6() {
		
		Management employee1 = new Management("Morgan,James", "ECE", new Date("2/4/2006"));
		
		employee1.getProfile().setAnnualSalary(85000.00);
		employee1.getProfile().setManagementRole(3);
		
		assertTrue(myCompany.add(employee1));
		employee1.calculatePayment();
		double payment = employee1.getPayment();
		assertNotEquals(3259, payment);
	}
	
	@Test
	/**
	Calculating payment for a full time employee
	Invalid because management role is invalid
	*/ 
	public void testCalculatePayment7() {
		
		Management employee1 = new Management("Morgan,James", "ECE", new Date("2/4/2006"));
		
		employee1.getProfile().setAnnualSalary(85000.00);
		employee1.getProfile().setManagementRole(4);
		
		assertTrue(myCompany.add(employee1));
		employee1.calculatePayment();
		double payment = employee1.getPayment();
		assertNotEquals(5000, payment);
	}
}