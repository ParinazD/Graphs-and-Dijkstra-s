package Graphs;

import java.util.*;

/**
 * Represents an Undirected Graph using an Adjacency List (an array of ArrayLists).
 * Nodes are represented by integers from 0 to vertices - 1.
 */
public class Graph {
    private ArrayList<Integer>[] adjList;
    private int size;

    /**
     * Constructor for the Graph.
     * @param vertices The total number of nodes (0 to vertices-1).
     * @param connections A 2D array representing edges, e.g., {{0, 1}, {1, 2}}.
     */
    public Graph(int vertices, int[][] connections) {
        this.size = vertices;
        // Suppress warning because generic array creation is necessary for the adjacency list
        @SuppressWarnings("unchecked")
        ArrayList<Integer>[] tempAdjList = new ArrayList[vertices];
        adjList = tempAdjList;
        
        for (int i = 0; i < vertices; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int[] connection : connections) {
            int from = connection[0];
            int to = connection[1];
            if (from >= 0 && from < vertices && to >= 0 && to < vertices) {
                adjList[from].add(to);
                adjList[to].add(from); // Undirected graph: Add edge in both directions
            } else {
                System.out.println("Invalid edge: Connection [" + from + ", " + to + "] is out of bounds.");
            }
        }
    }
    


    /**
     * Calculates the out-degree of a specified node.
     * In an Undirected Graph, out-degree is simply the number of its neighbors.
     * @param node The node (vertex index) to check.
     * @return The number of edges originating from the node.
     */
    public int getOutDegree(int node) {
        if (node < 0 || node >= size) {
            System.err.println("Error: Node index " + node + " is out of graph range.");
            return -1;
        }
        // The out-degree is the size of the corresponding list in the adjacency list.
        return adjList[node].size();
    }

    /**
     * Calculates the in-degree of a specified node.
     * In a Directed Graph, this involves checking every list for the node's presence.
     * Since this is currently an Undirected Graph, in-degree == out-degree, but this
     * implementation follows the general logic for finding in-degree in any graph type.
     * @param node The node (vertex index) to check.
     * @return The number of edges terminating at the node.
     */
    public int getInDegree(int node) {
        if (node < 0 || node >= size) {
            System.err.println("Error: Node index " + node + " is out of graph range.");
            return -1;
        }
        int inDegreeCount = 0;
        // Check every adjacency list to see if it contains the target node
        for (int i = 0; i < size; i++) {
            if (adjList[i].contains(node)) {
                inDegreeCount++;
            }
        }
        return inDegreeCount;
    }
    
    // --- TRAVERSAL ALGORITHMS ---

    /**
     * Performs Depth-First Search (DFS) starting from a given node.
     * It uses a recursive helper method.
     * @param startNode The node index to begin the search from.
     */
    public void depthFirstSearch(int startNode) {
        if (startNode < 0 || startNode >= size) {
            System.err.println("Error: Start node " + startNode + " is out of graph range for DFS.");
            return;
        }
        System.out.println("\n--- DFS Traversal (Starting at node " + startNode + ") ---");
        boolean[] visited = new boolean[size];
        
        dfsRecursive(startNode, visited);
        System.out.println();
    }

    private void dfsRecursive(int node, boolean[] visited) {
        visited[node] = true;
        System.out.print(node + " "); // Process/print the node

        for (Integer neighbor : adjList[node]) {
            if (!visited[neighbor]) {
                dfsRecursive(neighbor, visited);
            }
        }
    }
    
    /**
     * Performs Breadth-First Search (BFS) starting from a given node.
     * Uses a Queue to explore the graph level by level. [Image of Breadth-First Search traversal]
     * @param startNode The node index to begin the search from.
     */
    public void breadthFirstSearch(int startNode) {
        if (startNode < 0 || startNode >= size) {
            System.err.println("Error: Start node " + startNode + " is out of graph range for BFS.");
            return;
        }
        System.out.println("\n--- BFS Traversal (Starting at node " + startNode + ") ---");

        boolean[] visited = new boolean[size];
        Queue<Integer> queue = new LinkedList<>();

        visited[startNode] = true;
        queue.add(startNode);

        while (!queue.isEmpty()) {
            int node = queue.poll();
            System.out.print(node + " "); // Process/print the node

            // Get all neighbors of the dequeued node
            for (int neighbor : adjList[node]) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.add(neighbor);
                }
            }
        }
        System.out.println();
    }

    
    /**
     * Prints the entire adjacency list representation of the graph.
     */
    public void printGraph() {
        System.out.println("\n--- Adjacency List ---");
        for (int i = 0; i < size; i++) {
            System.out.print("Node " + i + " is connected to: ");
            System.out.println(adjList[i]);
        }
    }
}