import java.io.File;
import java.util.*;
import java.util.Map.Entry;

/*
 * SD2x Homework #3
 * Implement the methods below according to the specification in the assignment description.
 * Please be sure not to change the method signatures!
 */
public class Analyzer {

    /*
     * Implement this method in Part 1
     * This method should take the name of the file to read and read it one line at a time, creating Sentence objects and putting them into the List.
     * Note that the method returns a List containing Sentence objects.
     */
    public static List<Sentence> readFile(String filename){
	Scanner sc;
	LinkedList<Sentence> sentences = new LinkedList<>();

	try {
	    sc = new Scanner(new File(filename));
	} catch (Exception e) {
	    return sentences;	//if the file cannot be opened for reading or if the input filename is null, this method should return an empty List
	}

	while(sc.hasNext()) {
	    String line = sc.nextLine();
	    int spaceIndex = line.indexOf(" ");
	    int score = Integer.parseInt(line.substring(0, spaceIndex));
	    String text = line.substring(spaceIndex + 1, line.length());
	    sentences.add(new Sentence(score, text));
	}
	sc.close();
	return sentences;
    }

    /*
     * Implement this method in Part 2
     */
    public static Set<Word> allWords(List<Sentence> sentences) {
	Set<Word> words = new HashSet<>();
	List<Word> wordsList = new ArrayList<>();	//use list to manipulate information
	
	if (sentences.isEmpty()) {
	    return words;	//if the list is empty, method returns an empty set.
	}
	
	for(Sentence sentence : sentences) {
//	    System.out.println("Sentence examined " + sentence.getText());
	    String[] tokens = sentence.getText().split(" ");

	    for (String token : tokens) {	//lower casing and special character filter needs to be implemented
		Word wordTemp = new Word(token);
		int index = wordsList.indexOf(wordTemp); 
		if (index == -1) {
		    wordTemp.increaseTotal(sentence.getScore());
		    wordsList.add(wordTemp);
//		    System.out.println(token + " added for the score of " + sentence.getScore());
		} else {
//		    System.out.println("This word exists " + token + ". Score increased by " + sentence.getScore());
		    wordsList.get(index).increaseTotal(sentence.getScore());
		}
	    }
	}
	
	words = new HashSet<Word> (wordsList);
	
	return words;
    }

    /*
     * Implement this method in Part 3
     */
    public static Map<String, Double> calculateScores(Set<Word> words) {
	Map<String, Double> dictionary = new HashMap<>();
	
	if (words.isEmpty()) {
	    return dictionary;
	}

	for(Word word : words) {
	    String key = word.getText();
	    double value = word.calculateScore();
	    dictionary.put(key, value);
	}

	return dictionary;

    }

    /*
     * Implement this method in Part 4
     */
    public static double calculateSentenceScore(Map<String, Double> wordScores, String sentence) {
	if(wordScores == null) {
	    return 0;
	}
	
	String[] tokens = sentence.split(" ");
	double sentenceScore = 0;
	int wordCount = 0;
	
	for (String token : tokens) {
	    if(wordScores.containsKey(token)) {
		sentenceScore += wordScores.get(token);
		wordCount++;
	    }
	}

	return sentenceScore/wordCount;

    }

    /*
     * This method is here to help you run your program. Y
     * You may modify it as needed.
     */
    public static void main(String[] args) {
	String filename = "C:\\Users\\Bennett\\eclipse-workspace\\SD2x\\src\\reviews.txt";

	List<Sentence> sentences = Analyzer.readFile(filename);
	Set<Word> dictionary = Analyzer.allWords(sentences);
	Map<String, Double> wordScores = Analyzer.calculateScores(dictionary);
	
	/*tests
	Iterator<Word> itr = dictionary.iterator();
	while(itr.hasNext()){
		System.out.println(itr.getText());
	}

	for (Entry<String, Double> entry : wordScores.entrySet()) {
            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
	}
	*/
	
	System.out.print("Please enter a sentence: ");
	Scanner in = new Scanner(System.in);
	String sentence = in.nextLine();
	in.close();
	double score = Analyzer.calculateSentenceScore(wordScores, sentence);
	System.out.println("The sentiment score is " + score);
    }
}
