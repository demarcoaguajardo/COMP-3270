import java.io.*;
import java.util.*;

/*
 * Graph class represents an undirected graph using adjacency list representation.
 */
class Graph {
    // We use a map to store the adjacency list of each node.
    private Map<String, List<String>> adjList;

    public Graph() {
        // Initialize the adjacency list.
        this.adjList = new HashMap<>();
    }

    // Adds an edge between node1 and node2.
    public void addEdge(String node1, String node2) {
        this.adjList.putIfAbsent(node1, new ArrayList<>());
        this.adjList.putIfAbsent(node2, new ArrayList<>());
        this.adjList.get(node1).add(node2);
        this.adjList.get(node2).add(node1);
    }

    /*
     * BFS algorithm to find the shortest path between two nodes and
     * time to search and distance.
     */
    public Pair<List<String>, Pair<Long, Integer>> bfs(String startNode, String endNode) {
        // Starts the timer.
        long startTime = System.nanoTime();

        // Queue to store the nodes to be visited.
        Map<String, String> parent = new HashMap<>();
        Map<String, Integer> distance = new HashMap<>();
        Queue<String> queue = new LinkedList<>();
        queue.add(startNode);
        parent.put(startNode, null);
        distance.put(startNode, 0);

        // While the queue is empty, we keep searching for the end node.
        while (!queue.isEmpty()) {
            String current = queue.poll();
            if (current.equals(endNode)) {
                break;
            }
            
            // Add the neighbors of the current node to the queue.
            for (String neighbor : this.adjList.get(current)) {
                if (!parent.containsKey(neighbor)) {
                    parent.put(neighbor, current);
                    distance.put(neighbor, distance.get(current) + 1);
                    queue.add(neighbor);
                }
            }
        }

        // If the end node is not found, return null path, return the time and distance as -1.
        if (!parent.containsKey(endNode)) {
            return new Pair<>(null, new Pair<>(System.nanoTime() - startTime, -1));
        }

        // Reconstruct the path from the end node to the start node.
        List<String> path = new ArrayList<>();
        for (String node = endNode; node != null; node = parent.get(node)) {
            path.add(node);
        }
        Collections.reverse(path);

        // Return the path, the time taken to search, and the distance.
        return new Pair<>(path, new Pair<>(System.nanoTime() - startTime, distance.get(endNode)));
    }

    /* 
     * DFS algorithm to find the shortest path between two nodes and
     * time to search and distance.
     */
    public Pair<List<String>, Pair<Long, Integer>> dfs(String startNode, String endNode) {
        // Starts the timer.
        long startTime = System.nanoTime();
    
        // Stack to store the nodes to be visited.
        Map<String, String> parent = new HashMap<>();
        Map<String, Integer> depth = new HashMap<>();
        Deque<String> stack = new ArrayDeque<>();
        stack.push(startNode);
        parent.put(startNode, null);
        depth.put(startNode, 0);
    
        // While the stack is empty, we keep searching for the end node.
        while (!stack.isEmpty()) {
            String current = stack.pop();
            if (current.equals(endNode)) {
                break;
            }
    
            // Add the neighbors of the current node to the stack.
            for (String neighbor : this.adjList.get(current)) {
                if (!parent.containsKey(neighbor)) {
                    parent.put(neighbor, current);
                    depth.put(neighbor, depth.get(current) + 1);
                    stack.push(neighbor);
                }
            }
        }
    
        // If the end node is not found, return null path, return the time and distance as -1.
        if (!parent.containsKey(endNode)) {
            return new Pair<>(null, new Pair<>(System.nanoTime() - startTime, -1));
        }
    
        // Reconstruct the path from the end node to the start node.
        List<String> path = new ArrayList<>();
        for (String node = endNode; node != null; node = parent.get(node)) {
            path.add(node);
        }
        Collections.reverse(path);
    
        // Return the path, the time taken to search, and the distance.
        return new Pair<>(path, new Pair<>(System.nanoTime() - startTime, depth.get(endNode)));
    }
}

// Pair class
class Pair<K, V> {
    private K key;
    private V value;

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }
}
