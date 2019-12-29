/*
 * SD2x Homework #5
 * Implement the methods below according to the specification in the assignment description.
 * Please be sure not to change the method signatures!
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.SortedMap;
import java.util.TreeMap;


public class MovieRatingsProcessor {

    public static List<String> getAlphabeticalMovies(TreeMap<String, PriorityQueue<Integer>> movieRatings) {
	List<String> listExport = new ArrayList<>();

	if(movieRatings == null || movieRatings.isEmpty()) {
	    return listExport;
	}

	SortedMap<String, PriorityQueue<Integer>> alphabetic = movieRatings;
	return new ArrayList<String>(alphabetic.keySet());
    }

    public static List<String> getAlphabeticalMoviesAboveRating(TreeMap<String, PriorityQueue<Integer>> movieRatings, int rating) {
	SortedMap<String, PriorityQueue<Integer>> alphabetic = new TreeMap<>();
	ArrayList<String> movieListAboveRating = new ArrayList<String>();

	if(movieRatings == null || movieRatings.isEmpty()) {
	    return movieListAboveRating;
	}

	for(Entry<String, PriorityQueue<Integer>> item : movieRatings.entrySet()) {
	    if(item.getValue().peek() > rating) {
		alphabetic.put(item.getKey(), item.getValue());
	    }
	}

	movieListAboveRating.addAll(alphabetic.keySet());

	return movieListAboveRating;
    }


    /*
     *  1. Remove all ratings in the PriorityQueue that are below (but not equal to) rating for every movie in the Map. 
     *  2. If all ratings are removed from a movie’s PriorityQueue, then remove the mapping from the TreeMap. 
     *  3. This method should return a new TreeMap that maps a movie title to the number of ratings that were removed from its corresponding PriorityQueue;
     *  4. The TreeMap that is returned should only contain movies that had ratings removed from its PriorityQueue.
     */
    public static TreeMap<String, Integer> removeAllRatingsBelow(TreeMap<String, PriorityQueue<Integer>> movieRatings, int rating) {
	TreeMap<String, Integer> alphabetic = new TreeMap<>();

	if(movieRatings == null || movieRatings.isEmpty()) {
	    return alphabetic;
	}

	System.out.println("--------------------------------");
	System.out.println(movieRatings);
	System.out.println("--------------------------------");

	for(Entry<String, PriorityQueue<Integer>> item : movieRatings.entrySet()) {
	    int removalCount = 0;
	    String movieTitle = item.getKey();
	    PriorityQueue<Integer> movieScores = item.getValue();
	    System.out.println(movieTitle + "=" + movieScores + " remove less than " + rating);

	    //Remove ratings below threshold value
	    while(!movieScores.isEmpty() && movieScores.peek() < rating) { //iterate through priority queue while PQ top value is less than the given rating
		movieScores.remove();
		removalCount++;
//		System.out.println(removalCount);
	    }
	    System.out.println(movieTitle + " removed " + removalCount + " entries " + movieScores.size() + " remains.");
	    
	    if(removalCount != 0 && movieScores.size() != 0) { //some ratings have been removed but not all ratings are eliminated 
		alphabetic.put(movieTitle, removalCount);
		System.out.println(movieTitle + " added with " + removalCount + " removals");
	    } else {
		System.out.println("--Skip this entry");
	    }
	}
	return alphabetic;
    }
}
