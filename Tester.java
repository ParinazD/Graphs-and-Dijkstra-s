package Graphs;

public class Tester {

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
        
        // 1. Print the graph structure
        myGraph.printGraph();

        // 2. Perform Traversals
        myGraph.depthFirstSearch(0);
        myGraph.breadthFirstSearch(0);

        // 3. Check Degrees (In an undirected graph, In-Degree == Out-Degree)
        System.out.println("\n--- Degree Checks ---");
        int nodeToCheck = 1;
        System.out.println("Out-Degree of Node " + nodeToCheck + ": " + myGraph.getOutDegree(nodeToCheck));
        System.out.println("In-Degree of Node " + nodeToCheck + ": " + myGraph.getInDegree(nodeToCheck));
        
        nodeToCheck = 4;
        System.out.println("Out-Degree of Node " + nodeToCheck + ": " + myGraph.getOutDegree(nodeToCheck));
        System.out.println("In-Degree of Node " + nodeToCheck + ": " + myGraph.getInDegree(nodeToCheck));
    }
}

