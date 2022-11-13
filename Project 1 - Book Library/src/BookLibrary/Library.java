package BookLibrary;
import BookLibrary.Book;

/**
This class handles all the user commands
These commands include adding, removing, checking out and returning a book.
@author Prachiti Atigre, Ujani Patel 
*/
public class Library {
	
	private Book[] books; //Array-based implementation of the bag data structure
	private int numBooks; //The number of books currently in the bag
	
	private static final int ARRAY_CAPACITY_OF_FOUR = 4;
	private static final int USE_AND_OPERATOR_WITH_NUMBER_THREE = 3;
	/**
	Default constructor to create an empty bag 
	*/
	public Library() {  
		books = new Book [ARRAY_CAPACITY_OF_FOUR];
	}
	
	/**
	Helper method to find a book in the bag
	Checks if the serial number of the book entered is equal to the serial number of any of the books already 
	present in the array through the means of a for loop
	@param book the book to be found
	@return index number of the array at which the book is present if found, -1 otherwise
	*/
	private int find(Book book) { 
		
		for(int i = 0; i < books.length; ++i) {
			
			if(books[i] != null) {
			
				if((book.getNumber()).equals(books[i].getNumber())) { 
					
					return i; //i is the index number
				}		
			}
		}
		return -1; //Book has not been found
	}
	
	/**
	Helper method to grow the capacity of the array by 4
	The array has an initial capacity of 4, and it is automatically increased whenever full
	A new array is created and all the elements from the smaller array are copied to the new array 
	*/
	private void grow() {
		
		Book [] newArrayWithMoreCapacity = new Book [books.length + ARRAY_CAPACITY_OF_FOUR];

		for(int i = 0; i < books.length; ++i) { 
			newArrayWithMoreCapacity[i] = books[i]; 
		} 
		books = newArrayWithMoreCapacity;
	}
	
	/**
	Adds the book to the array of books
	Before adding, the growArraySize method is called to check if there is space in the array. Once there is space, 
	the book is added
	@param book the book to be added
	*/
	public void add(Book book) { 	
		
		if((numBooks & USE_AND_OPERATOR_WITH_NUMBER_THREE) == 0 
				&& numBooks != 0) { //This checks if the number of books are a multiple of 4
			grow();
		}
		books[numBooks] = book;
		numBooks = numBooks + 1;
	}
	
	/**
	Removes the book from the array of books
	The find method is called to check exists in the library (What if the book has been checked out?), 
	If the book is found, the shiftArray method is called to get rid of the book
	@param book the book to be removed
	@return true if book is found, false otherwise
	*/
	public boolean remove(Book book) {
		
		int emptyIndex = find(book);
		
		if(emptyIndex == -1) //Book is not found
			return false; 
		
		else { //Book is found
			shiftArray(books, emptyIndex);
			numBooks = numBooks - 1; 
			return true;
		}
	}
	
	/**
	Helper method to move the elements of the array one index down to remove the book from the array
	The book at the index needs to be removed. All elements to the right of it are moved one index to the left, 
	eventually getting rid of the book at the index
	@param books the books array to be shifted
	@param index the index of the book to be removed
	*/
	public void shiftArray(Book [] books, int index) {
		
		if(index >= 0) {
				
			for(int i = index+1; i < books.length; i++) {
					books[i-1] = books[i];
			}
			books[books.length - 1] = null;
		}
		else {
				// Nothing
		}
	}
	
	/**
	Returns the book to the library
	The find method is called to check if the book exists in the library. If it does, the getCheckedOut method is 
	called to determine if the book has been checked out or not. getCheckedOut returns true if the book has been checked 
	out and false otherwise. If it is true, it is set to false using the setCheckedOut method and the book is 
	successfully returned 
	@param book the book to be returned
	@return true if book is returned, false otherwise
	*/
	public boolean returns(Book book) {
		
		int checkIndex1 = find(book);
		boolean checkReturn; 
		
		if(checkIndex1 == -1)
			return false;  //Book is not in the array or the library doesn't have it
		
		else {
			checkReturn = books[checkIndex1].getCheckedOut();
			if(checkReturn) { //Book has been checked out
				book.setCheckedOut(false);
				books[checkIndex1].setCheckedOut(false);
				return true;
			}
			else { //Book has not been checked out
				return false;
			}
		}
	}

	/**
	Checks out the book from the library
	The find method is called to check if the book exists in the library. If it does, the getCheckedOut method is
	called to determine if the book has been already been checked out or not. The getCheckedOut method returns true 
	if the book has been checked out and false otherwise. If it is true, the method returns false and true otherwise  
	@param book the book to be checked out
	@return true if book has successfully been checked out and false otherwise
	*/
	public boolean checkOut(Book book) { 
		
		int checkIndex = find(book);
		boolean check;
		
		if(checkIndex == -1) {
			return false;  //Book is not in the array or the library doesn't have it
		}
		
		else { //Book Found
			check = books[checkIndex].getCheckedOut();
			
			if(check) { //Book has already been checked out
				return false;
			}
			else { //Book has not been checked out
				book.setCheckedOut(true); 
				books[checkIndex].setCheckedOut(true);
				return true;
			}
		}
	}
	
	/**
	This method sorts the array by serial number
	@param books the books array to be sorted 
	*/
	public void sortArrayByNumber(Book books[]) {
		
		for(int i = 0; i < numBooks - 1; i++) {
		
			for(int j = i + 1; j < numBooks; j++) {
            	
				int bookNumOne = Integer.parseInt(books[i].getNumber());
            	int bookNumTwo = Integer.parseInt(books[j].getNumber());
            	
                if(bookNumOne > bookNumTwo) {
                    Book temp = books[i];
                    books[i] = books[j];
                    books[j] = temp;
                }
            }
        }
	}
	
	/**
	Prints the list of all available books in the library
	*/
	public void print() { 
		
		if(numBooks == 0) {
			System.out.println("Library catalog is empty!");
		}
		
		else {
			System.out.println("**List of books in the library.");
			
			//Print the catalog
			for(int i = 0; i < books.length; i++) {
				
				if(books[i] != null) {
					System.out.println(books[i]);
				}
			}
			System.out.println("**End of list");
		}
	}
	
	/**
	Prints the list of all available books in the library in ascending order of the date the book was published
	*/
	public void printByDate() {
		
		if(numBooks == 0) {
			System.out.println("Library catalog is empty!");
		}
		
		else {
			
			System.out.println("**List of books by the dates published.");
			
			//Sort by Year using selection sort
			for(int i = 0; i < numBooks - 1; i++)  {
				
				for(int j = i + 1; j < numBooks; j++) {
					
					int year1 = books[i].getDate().getYear();
					int year2 = books[j].getDate().getYear();
            	
					if(year1 > year2) {
						Book temp = books[i];
						books[i] = books[j];
						books[j] = temp;
					}
				}
			}
			
			//Sort by Month using selection sort
			for(int i = 0; i < numBooks - 1; i++) {
			
				for(int j = i + 1; j < numBooks; j++) {
	            	
	            	int year1 = books[i].getDate().getYear(); 
					int year2 = books[j].getDate().getYear();
					
	            	int month1 = books[i].getDate().getMonth();
	            	int month2 = books[j].getDate().getMonth();
	            	
	                if(month1 > month2 && year1 == year2) {
	                    Book temp = books[i];
	                    books[i] = books[j];
	                    books[j] = temp;
	                } 
	            }
	        }
			
			//Sort by Day using selection sort
			for(int i = 0; i < numBooks - 1; i++) {
	           
				for(int j = i + 1; j < numBooks; j++) {
	            	
	            	int year1 = books[i].getDate().getYear(); 
					int year2 = books[j].getDate().getYear();
					
	            	int month1 = books[i].getDate().getMonth();
	            	int month2 = books[j].getDate().getMonth();
	            	
	            	int day1 = books[i].getDate().getDay();
	            	int day2 = books[j].getDate().getDay();
	            	
	                if(day1 > day2 && year1 == year2 && month1 == month2) {
	                    Book temp = books[i];
	                    books[i] = books[j];
	                    books[j] = temp;
	                }
	            }
	        }	
			
			//Print the catalog
			for(int i=0; i < books.length; i++) {
				
				if(books[i] != null) {
					System.out.println(books[i]);
				}
			}
			System.out.println("**End of list");
		}
	}
	
	/**
	Prints the list of all available books in the library in ascending order of the serial number of the book
	*/
	public void printByNumber() { 
		
		if(numBooks == 0) {
			System.out.println("Library catalog is empty!");
		}
		
		else {
			
			System.out.println("**List of books by the book numbers.");
			
			//Sort by serial number using selection sort
			sortArrayByNumber(books);
			
			//Print the catalog
			for(int i = 0; i < books.length; i++) {
				
				if(books[i] != null) {
					System.out.println(books[i]);
				}
			}
			System.out.println("**End of list");
		}
	}
}

