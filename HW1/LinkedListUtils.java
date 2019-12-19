import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Set;

/*
 * SD2x Homework #1
 * Implement the methods below according to the specification in the assignment description.
 * Please be sure not to change the signature of any of the methods!
 */

public class LinkedListUtils {

    public static void insertSorted(LinkedList<Integer> list, int value) {
	if (list != null) {
	    if (list.isEmpty()) {
		list.add(value);
	    } else {
		for (int i = 0; i < list.size(); i++) {
		    if (list.get(i) >= value) {
			list.add(i, value);
			break;
		    }
		    if (i == list.size() - 1) {
			list.add(value);
			break;
		    }
		}
	    }
	}
    }

    public static void removeMaximumValues(LinkedList<String> list, int N) {
	if(list != null && !list.isEmpty() && N > 0) {
	    if(list.size() <= N) {
		list.removeAll(list);
	    } else {
		Set<String> removeDups = new LinkedHashSet<>(list);
		LinkedList<String> sortedList = new LinkedList<>(removeDups);
		Collections.sort(sortedList, Collections.reverseOrder());
		for(int i = 0; i < N; i++) {
		    sortedList.remove();
		}
		list.retainAll(sortedList);
	    }
	}
    }

    public static boolean containsSubsequence(LinkedList<Integer> one, LinkedList<Integer> two) {

	if(one != null && two != null && !one.isEmpty() && !two.isEmpty()) {
	    for(int i = 0; i < (one.size() - two.size()); i++) {
		boolean bool = true;
		for(int j = 0; j < two.size(); j++) {
		    if(one.get(i+j) != two.get(j)) {
			bool = false;
			break;
		    }
		}
		if(bool) {
		    return true;
		}
	    }
	}
	return false;
    }
}