import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Graph graph = new Graph();
        // Read the edge list from the file and add the edges to the graph.
        BufferedReader reader = new BufferedReader
            (new FileReader("Test_Case_Assignment2.txt"));

        String line;
        
        while ((line = reader.readLine()) != null) {
            String[] nodes = line.split(",");
            graph.addEdge(nodes[0].trim(), nodes[1].trim());
        }

        reader.close();

        
        String startNode = "N_0";

        // Get the end node from the user.
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the second node #: ");
        String endNode = "N_" + scanner.nextLine();
        scanner.close();

        // Perform BFS from N_0 to N_x
        Pair<List<String>, Pair<Long, Integer>> bfsResult = graph.bfs(startNode, endNode);
        if (bfsResult.getKey() == null) {
            System.out.println("\nNo BFS path found between " + startNode + " and " + endNode);
        } else {
            // Prints the shortest path
            System.out.println("\nShortest BFS path between " + startNode +
                " and " + endNode + ": " + bfsResult.getKey());
            // Prints the distance
            System.out.println("BFS Distance: " + bfsResult.getValue().getValue());
            // Prints the time taken to search
            System.out.println("BFS Time taken to search: " 
                + bfsResult.getValue().getKey() + " nanoseconds");
        }

        // Perform DFS from N_0 to N_x
        Pair<List<String>, Pair<Long, Integer>> dfsResult = graph.dfs(startNode, endNode);
        if (dfsResult.getKey() == null) {
            System.out.println("\nNo DFS path found between " + startNode + " and " + endNode);
        } else {
            // Prints the shortest path
            System.out.println("\nShortest DFS path between " + startNode +
                " and " + endNode + ": " + dfsResult.getKey());
            // Prints the distance
            System.out.println("DFS Distance: " + dfsResult.getValue().getValue());
            // Prints the time taken to search
            System.out.println("DFS Time taken to search: " 
                + dfsResult.getValue().getKey() + " nanoseconds\n");
        }
        
    }
}