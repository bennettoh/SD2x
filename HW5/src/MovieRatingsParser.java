/*
 * SD2x Homework #5
 * Implement the method below according to the specification in the assignment description.
 * Please be sure not to change the method signature!
 */

import java.util.List;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class MovieRatingsParser {
    /*
     * In MovieRatingsParser.java, implement the parseMovieRatings method, which takes a List of 
     * UserMovieRatings as input and creates a TreeMap data structure from it. 
     * The TreeMap is a mapping from the movie’s title to a PriorityQueue of all its associated ratings.
     * 
     * Keep in mind that the same movie title may appear numerous times in the input List, and multiple instances may even have the same rating.
     * A distinct movie title should only appear once as a key in the TreeMap, however, and be mapped to a PriorityQueue (min-heap) of all the ratings for that movie.
     */
    public static TreeMap<String, PriorityQueue<Integer>> parseMovieRatings(List<UserMovieRating> allUsersRatings) {

	TreeMap<String, PriorityQueue<Integer>> mapExport = new TreeMap<>();

	if(allUsersRatings == null || allUsersRatings.isEmpty()) {
	    return mapExport; //If the input List is null or empty, parseMovieRatings should return an empty TreeMap
	}

	/*
	 * If the input List contains any null UserMovieRatings object, or a UserMovieRatings object 
	 * whose movie title is null or an empty string, or a UserMovieRatings object whose rating 
	 * is negative, parseMovieRatings should ignore that UserMovieRatings object.
	 */
	
	for(UserMovieRating rating : allUsersRatings) {
	    if(!(rating == null || rating.getMovie() == null || rating.getMovie().isEmpty() || rating.getUserRating() < 0)){
		String title = rating.getMovie().toLowerCase();
		Integer score = new Integer(rating.getUserRating());

		if(!title.isEmpty() && title != null && score >= 0) {

		    if(mapExport.containsKey(title)) { //movie exists
			mapExport.get(title).add(score);
		    } else {
			PriorityQueue<Integer> ratings = new PriorityQueue<>();
			ratings.add(score);
			mapExport.put(title, ratings);
		    }
		}
	    }
	}

	return mapExport; // this line is here only so this code will compile if you don't modify it
    }

}