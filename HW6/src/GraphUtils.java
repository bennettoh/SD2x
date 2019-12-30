

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/*
 * SD2x Homework #6
 * Implement the methods below according to the specification in the assignment description.
 * Please be sure not to change the signature of any of the methods!
 */

public class GraphUtils {

    /*
     * Given a Graph, this method returns the shortest distance (in terms of number of edges) from the node labeled src to the node labeled dest. 
     * 
     * The method should return -1 for any invalid inputs, including: 
     * null values for the Graph, src, or dest; 
     * there is no node labeled src or dest in the graph; 
     * there is no path from src to dest. 
     * 
     * Keep in mind that this method does not just return the distance of any path from src to dest, it must be the shortest path.
     */
    public static int minDistance(Graph graph, String src, String dest) {
	Queue<Node> toExplore = new LinkedList<Node>();
	HashMap<Node, Integer> marked = new HashMap<>();


	if (graph == null || src == null || dest == null) {
	    return -1;
	}

	if (src.equals(dest)) {
	    return 0;
	}

	if (!graph.containsElement(src) || !graph.containsElement(dest)) {
	    return -1;
	}

	Node start = graph.getNode(src);
	toExplore.add(start);
	marked.put(start, 0);


	while (!toExplore.isEmpty()) {
	    Node current = toExplore.remove();
	    for (Node neighbor : graph.getNodeNeighbors(current)) { //explore each of current Node's neighbors
		if (!marked.containsKey(neighbor)) { //if not marked already, mark with +1 distance from current Node
		    marked.put(neighbor, marked.get(current) + 1);
		    if (neighbor.getElement().equals(dest)) { //if it is the one we are looking for, return 1 more from the current node's distance
			return marked.get(neighbor); 
		    }
		    toExplore.add(neighbor);
		}
	    }
	}

	return -1; //No path
    }

    /*
     * Given a Graph, this method returns a Set of the values of all nodes within the specified distance (in terms of number of edges) of the node labeled src, 
     * i.e. for which the minimum number of edges from src to that node is less than or equal to the specified distance. 
     * The value of the node itself should not be in the Set, even if there is an edge from the node to itself.
     * 
     * The method should return null for any invalid inputs, including: 
     * null values for the Graph or src; 
     * there is no node labeled src in the graph; 
     * distance less than 1. 
     * 
     * However, if distance is greater than or equal to 1 and there are no nodes within that distance (meaning: src is the only node in the graph), 
     * the method should return an empty Set.
     */
    public static Set<String> nodesWithinDistance(Graph graph, String src, int distance) {
	if(distance < 1 || graph == null || src == null || !graph.containsElement(src)) {
	    return null;
	}

	Queue<Node> toExplore = new LinkedList<Node>();
	Set<Node> marked = new HashSet<>();
	Node current;
	Node start = graph.getNode(src);
	Set<String> setExport = new HashSet<>();
	toExplore.add(start);

	while(!toExplore.isEmpty()) {
	    current = toExplore.remove();
	    for(Node neighbor : graph.getNodeNeighbors(current)) {
		if(!marked.contains(neighbor)) {
		    int nodeDistance = minDistance(graph, src, neighbor.getElement());
		    if(nodeDistance <= distance && nodeDistance != 0) {
			setExport.add(neighbor.getElement());
			toExplore.add(neighbor);
			marked.add(neighbor);
		    }
		}
	    }
	}
	return setExport; // this line is here only so this code will compile if you don't modify it
    }

    /*
     * Given a Graph, this method indicates whether the List of node values represents a Hamiltonian Path. 
     * A Hamiltonian Path is a valid path through the graph in which every node in the graph is visited exactly once, 
     * except for the start and end nodes, which are the same, so that it is a cycle. 
     * If the values in the input List represent a Hamiltonian Path, the method should return true, but the method should return false otherwise, 
     * e.g. 
     * if the path is not a cycle, 
     * if some nodes are not visited, 
     * if some nodes are visited more than once, 
     * if some values do not have corresponding nodes in the graph, 
     * if the input is not a valid path 
     * 
     * (i.e., there is a sequence of nodes in the List that are not connected by an edge), etc. 
     */
    public static boolean isHamiltonianPath(Graph g, List<String> values) {
	if(g == null || values == null || values.isEmpty()) {
	    return false;
	}

	if(values.get(0) != values.get(values.size() - 1)) { //Not a cycle if first and last items are different
	    return false;
	}

	Set<String> setValues = new HashSet<>(values.subList(0, values.size()));
	if(values.size() - 1 != setValues.size()) { //Repetition detection
	    return false;
	}
	
	if(values.size() - 1 != g.getNumNodes()) {
	    return false;
	}

	Set<Node> marked = new HashSet<>();

	for(int i = 0; i < values.size() - 2 ; i++) {
	    Node current = g.getNode(values.get(i));
	    Node next = g.getNode(values.get(i + 1));
//	    System.out.println("Current " + current + " Next " + next);
	    
	    if(g.getNodeNeighbors(current).contains(next)) {
		marked.add(current);
		current = next;
		next = g.getNode(values.get(i + 2));
	    } else {
//		System.out.println("Next value not found amongst neighbors");
		return false;
	    }
	}

	return true; // this line is here only so this code will compile if you don't modify it
    }

}
