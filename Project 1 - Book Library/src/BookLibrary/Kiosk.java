package BookLibrary;
import java.util.Scanner;
import java.util.StringTokenizer;

import BookLibrary.Book;
import BookLibrary.Library;

/**
This class reads all the commands from the console
These commands are provided access to the library class which gives service to respective methods for each command 
@author Prachiti Atigre, Ujani Patel 
*/
public class Kiosk {
	
	private static int SERIAL_NUMBER = 10001;
	
	/**
	This method is called by the RunProject1 class
	As the method runs, it will read in the commands by the user and perform accordingly
	*/
	public void run() {
		
		String line, name, date, number, command = null;
		
		Scanner sc = new Scanner(System.in); //The Scanner Class is used to read in the user inputs from the console
		
		System.out.println("Library Kiosk running.");
		
		Library lib = new Library();
		line = sc.nextLine();		
		
		while(!line.equals("Q")) { //Runs until the user enters the command Q to quit
			
			try {	
			
				StringTokenizer st = new StringTokenizer(line, ",");
				command = st.nextToken();
				
				if(st.hasMoreTokens()) {
					
					if(command.equals("A"))	{
						
						name = st.nextToken();
						date = st.nextToken();
						
						Date date1 = new Date(date);
						Book book = new Book(name,date1, Integer.toString(SERIAL_NUMBER));
						
						boolean checkDate = book.getDate().isValid();
						
						if(!checkDate) {
							System.out.println("Invalid Date!");
						}
						else {
							lib.add(book);
							System.out.println(book.getName() + " added to the library.");
							SERIAL_NUMBER = SERIAL_NUMBER + 1;
						}
					}
					
					else if(command.equals("R")) {
						
						number = st.nextToken();
						Book book = new Book(number);
						
						boolean dateBookCheck = lib.remove(book);
							
						if(dateBookCheck) {
								System.out.println("Book#" + number + " removed.");
						}
						else {
							System.out.println("Unable to remove, the library does not have this book.");
						}
						
					}
					
					else if(command.equals("O")) {
						
						number = st.nextToken();
						Book book = new Book(number);
						
						boolean checkOutCheck = lib.checkOut(book);
							
						if(checkOutCheck) {
							System.out.println("You've checked out Book#" + number + ". Enjoy!");
						}
						else {
							System.out.println("Book#" + number + " is not available.");
						}
					}
					
					else if(command.equals("I")) {
						
						number = st.nextToken();
						Book book = new Book(number);
						
						boolean returnCheck = lib.returns(book);
						
						if(returnCheck) {
							System.out.println("Book#" + number + " return has completed. Thanks!");
						}
						else {
							System.out.println("Unable to return Book#" + number + ".");
						}
					}
					else { //if (command.equals("a") || command.equals("r") || command.equals("o") || command.equals("i")) {
						System.out.println("Invalid command!");
					}
						
				}
				
				else {
				
					if(command.equals("PA")) { 
						lib.print(); 
					}
	
					else if(command.equals("PD")) {
						lib.printByDate();
					}
					
					else if(command.equals("PN")) {
						lib.printByNumber();
					}
					
					else if (command.equals("a") || command.equals("r") || command.equals("o") || command.equals("i") ||
							command.equals("pa") || command.equals("pn") || command.equals("pd") || command.equals("q")) {
						System.out.println("Invalid command!");
					}
					
					else {
						System.out.println("Invalid command!");
					}
				}		
				line = sc.nextLine();
			}		
			
			catch(Exception NoSuchElementException) {
				line = sc.nextLine();
			}
		}
		System.out.println("Kiosk session ended.");
		sc.close();
	}
}