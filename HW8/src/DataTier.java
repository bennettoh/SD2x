import java.io.File;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/*
 * SD2x Homework #8
 * This class represents the Data Tier in the three-tier architecture.
 * Implement the appropriate methods for this tier below.
 */

public class DataTier {

    private String fileName; // the name of the file to read

    public DataTier(String inputSource) {
	fileName = inputSource;

    }

    

    /*
     * read the data file containing information about the books, create Book objects for each, and 
     * then return the Book objects.
     */
    public Set<Book> getAllBooks() {

	Set<Book> books = new HashSet<>();
	File file = new File(fileName);
	try {
	    Scanner sc = new Scanner(file);
	    while(sc.hasNext()) {
		String currentLine = sc.nextLine();
		
		String[] bookinfo = currentLine.split("\t");
		
		Book currentBook = new Book(bookinfo[0], bookinfo[1], Integer.parseInt(bookinfo[2]));
		books.add(currentBook);
	    }
	    sc.close();
	} catch (Exception e) {
	    e.printStackTrace();
	}

	return books;
    }

}
