import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;


public class TextEditor {

    
    private static Set<String> StringReader(String posLoc) {
	      Set<String> result = new TreeSet<String>();
	      BufferedReader br = null;
	      try {
	         br = new BufferedReader(new FileReader(new File(posLoc)));
	         String availalbe;
	         while((availalbe = br.readLine()) != null) {
	             result.add(availalbe);            
	         }
	      } catch (FileNotFoundException e) {
	         e.printStackTrace();
	      } catch (IOException e) {
	         e.printStackTrace();
	      } finally {
	         if (br != null) {
	            try {
	               br.close();
	            } catch (IOException e) {
	               e.printStackTrace();
	            }
	         }
	      }
	      return result;
	  }
    
    
    
    
    public static void main(String[] args) throws Exception 
    { 
	Set<String> quotes = StringReader("121 defintions of architecture.txt");
	Iterator itr = quotes.iterator();
	ArrayList<Quote> summary = new ArrayList<>();
	
	while(itr.hasNext()) {
	    String quote = (String) itr.next();
	    String[] tokens = quote.split("\\. \"|\" - | in ");
	    
	    
	    summary.add(new Quote(tokens[2], tokens[1]));

	    
	}
	for (Quote item : summary) {
	    System.out.println("Quote goes: " + item.getQuote());
	    System.out.println("Written by: " + item.getAuthor());
	}
    }
}