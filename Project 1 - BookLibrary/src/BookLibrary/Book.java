package BookLibrary;
import BookLibrary.Date;

/**
This class stores the data of a book in the form of the data type Book
The class also stores getter and setter methods for the data type Book
@author Prachiti Atigre, Ujani Patel 
*/
public class Book {
	
	private String name;
	private String number; //A 5-digit serial number unique to the book
	private Date datePublished;
	private boolean checkedOut;
	
	/**
	Helper method to set the value of checkedOut to either true or false
	@param checkedOut determines if the book is checked out or not
	*/
	public void setCheckedOut(boolean checkedOut) {
		this.checkedOut = checkedOut;
	}
	
	/**
	Helper method to get the value of checkedOut which is either true or false
	@return true if book has been checked out, false otherwise
	*/
	public boolean getCheckedOut() {
		return this.checkedOut;
	}
	
	/**
	This method checks if the Book object is equal to another Book Object
	@param obj the Book object
	@return true if the book objects are equals, false otherwise
	*/
	@Override //Tag to prevent change of signature
	public boolean equals(Object obj) {
		
		if(obj instanceof Book) { //Check if obj is an instance of the Book Object
			Book book = (Book) obj;  
			return book.name.equals(this.name);
		}
		return false;
	}
	
	/**
	This method returns a string representation of the object Book
	@return String format
	*/
	@Override
	public String toString() {
		
		boolean bookAvailability = this.getCheckedOut();
		
		if(bookAvailability) {
			return "Book#" + number + "::" +this.getName() + "::" + this.getDate().getMonth() + "/" + this.getDate().getDay() + "/" + 
				this.getDate().getYear() + "::is checked out.";
		}
		
		else {
			return "Book#" + number + "::" +this.getName() + "::" + this.getDate().getMonth() + "/" + this.getDate().getDay() + "/" + 
				this.getDate().getYear() + "::is available.";
		}
	}
	
	/**
	1ST Book Constructor: Takes in the instance value of name, datePublished and number
	@param name the name of the book
	@param date the date published of the book
	@param number the serial number of the book
	*/
	public Book(String name, Date date, String number) {
		
		this.name = name;
		this.datePublished = date;
		this.number = number;
	}
	
	/**
	2ND Book Constructor: Takes in the instance value of the number
	@param number the serial number of the book
	*/
	public Book(String number) {
		this.number = number;
	}
	
	/**
	Helper method to get the string of the Book name
	@return the name of the book
	*/
	public String getName() {
		return this.name;
	}
	
	/**
	Helper method to get the string of the Book number
	@return the serial number of the book
	*/
	public String getNumber() {
		return this.number;
	}
	
	/**
	Helper method to get the string of the date published from the Date class
	@return the date of the book
	*/
	public Date getDate() {
		return this.datePublished;
	}	
}
