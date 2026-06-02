package Graphs;
import java.util.*;

public class Tester {
	
	/**
	 * Simple implementation of dijkstra's that
	 * finds shortest path between two nodes using BFS
	 * @param graph
	 * @param startNode
	 * @param endNode
	 */
	public static void findShortestPath(Graph graph, int startNode, int endNode) {
		Map<Integer, Integer> parentMap = createParentMap(graph, startNode); 
        boolean found = parentMap.containsKey(endNode);

        // reconstruct path
        if (found) {
            LinkedList<Integer> path = new LinkedList<>();
            Integer current = endNode;
            
            // Trace backward from the end node to the start node
            while (current != null) {
                path.addFirst(current);
                current = parentMap.get(current); // Move to the parent
                
                if (current == null && path.getFirst() != startNode) {
                    //when the start node is not the parent of anything
                    break;
                 
                }else if (current != null && current == startNode) {
                    // Manually add the start node and exit the loop
                    path.addFirst(current);
                    break;
                }
            }

            System.out.println("Length of SP: " + (path.size() - 1));
        } else {
            System.out.println("No path found from " + startNode + " to " + endNode + ".");
        }
    }
	
	 /**
     * helper that uses BFS to find parents of each node
     * @param graph The Graph object.
     * @param startNode The starting node index.
     * @return A Map where the key is the node and the value is its predecessor on 
     * the shortest path from the startNode.
     */
    private static Map<Integer, Integer> createParentMap(Graph graph, int startNode) {
        // Parent map to store the predecessor of each node
        Map<Integer, Integer> parentMap = new HashMap<>();
        
        // Queue for the Breadth-First Search
        Queue<Integer> queue = new LinkedList<>();
        
        // Set to track visited nodes
        Set<Integer> visited = new HashSet<>();
        if (startNode < 0 || startNode >= graph.getSize()) {
            return parentMap; // Return empty map on invalid input
        }

        // Initialize BFS
        queue.add(startNode);
        visited.add(startNode);
        
        while (!queue.isEmpty()) {
            int current = queue.poll();
            // Use the getter method to retrieve neighbors
            for (int neighbor : graph.getAdjList(current)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    //store current node as neighbor's parent
                    parentMap.put(neighbor, current); 
                    queue.add(neighbor);
                }
            }
        }
        
        return parentMap;
    }
    
    
    
	public static void main(String[] args) {
        // Example: 5 vertices (0, 1, 2, 3, 4)
        // Edges: (0, 1), (0, 4), (1, 2), (1, 3), (1, 4), (2, 3), (3, 4)
        int numVertices = 5;
        int[][] edges = {
            {0, 1}, 
            {0, 4}, 
            {1, 2}, 
            {1, 3}, 
            {1, 4}, 
            {2, 3}, 
            {3, 4}
        };

        Graph myGraph = new Graph(numVertices, edges);
        myGraph.printGraph();

        //Perform Traversals
        myGraph.depthFirstSearch(0);
        myGraph.breadthFirstSearch(0);

        //Check Degrees (In an undirected graph, In-Degree == Out-Degree)
        System.out.println("\n--- Degree Checks ---");
        int nodeToCheck = 1;
        System.out.println("Out-Degree of Node " + nodeToCheck + ": " + myGraph.getOutDegree(nodeToCheck));
        System.out.println("In-Degree of Node " + nodeToCheck + ": " + myGraph.getInDegree(nodeToCheck));
        
        nodeToCheck = 4;
        System.out.println("Out-Degree of Node " + nodeToCheck + ": " + myGraph.getOutDegree(nodeToCheck));
        System.out.println("In-Degree of Node " + nodeToCheck + ": " + myGraph.getInDegree(nodeToCheck));
        
        
        findShortestPath(myGraph, 0, 3); // Path: 0 -> 1 -> 3 (Length 2)
        findShortestPath(myGraph, 2, 4); // Path: 2 -> 3 -> 4 (Length 2)
	}
}

