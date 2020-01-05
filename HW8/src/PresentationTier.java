import java.util.Scanner;

/*
 * SD2x Homework #8
 * This class represents the Presentation Tier in the three-tier architecture.
 * Implement the appropriate methods for this tier below. 
 * Also implement the start method as described in the assignment description.
 */

public class PresentationTier {

    private LogicTier logicTier; // link to the Logic Tier

    public PresentationTier(LogicTier logicTier) {
	this.logicTier = logicTier;
    }

    public void start() {
	System.out.print("To search books by author, type 1. To show number of books published in the input year, type 2: ");
	Scanner input = new Scanner(System.in);
	int number = input.nextInt();
	input.close();
	if(number == 1) {
	    showBookTitlesByAuthor();
	} else if(number == 2) {
	    showNumberOfBooksInYear();
	} else {
	    System.out.println("Invalid input");
	}
    }

    /*
     * using the command-line (i.e., reading from System.in), ask the user to enter part or all of an author’s name, 
     * then display (using System.out) the titles of those books whose author name includes the input name.
     */
    public void showBookTitlesByAuthor() {
	System.out.println("Input author: ");
	Scanner input = new Scanner(System.in);
	String name = input.next();
	input.close();
	
	logicTier.findBookTitlesByAuthor(name);

    }

    /*
     * using the command-line (i.e., reading from System.in), ask the user to enter a year, 
     * then display (using System.out) the number of books published in that year
     */
    public void showNumberOfBooksInYear() {
	
	System.out.println("Input year: ");
	Scanner input = new Scanner(System.in);
	int number = input.nextInt();
	input.close();
	
	logicTier.findNumberOfBooksInYear(number);
    }

}
