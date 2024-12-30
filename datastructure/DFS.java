// This class used to implement the Depth First Search (DFS) algorithm to find the shortest path between two cities 
public class DFS {
	
	// Store the shortest path and its length
    public static class ShortestPathDFS {
    	// List of cities in the shortest path
        MyArrayList<String> path;
        // Total length of the shortest path
        int pathLength;
        
        // Constructor to initialize an empty path
        // Time Complexity -> O(1)
        public ShortestPathDFS() {
            this.path = new MyArrayList<>();
            // Set the paths length to a high value
            // Ensure it is updated with shorter paths during DFS
            this.pathLength = Integer.MAX_VALUE;
        }
    }
    
    // Find the shortest path between two cities
    // Time Complexity -> O(V + E)
    // V is the number of nodes (cities)
    // E is the number of edges
    public static ShortestPathDFS findPath(Node[] nodes, String startCity, String endCity) {
    	
    	// Store the shortest path and its length
    	ShortestPathDFS dfsPath = new ShortestPathDFS();
    	
    	// Manage the DFS traversal
    	
        // Why we used stack (MyStack)?
    	// DFS requires Last In First Out (LIFO) structure to explore nodes deeply before backtracking
    	// Ensure that the recently added city (node) is processed first
        MyStack<StackFrame> stack = new MyStack<>(10);
        
        // Track visited cities
        
        // Why we used array list (MyArrayList)?
        // Dynamic resizing and efficiency in random access
        MyArrayList<String> visited = new MyArrayList<>();
        // Initialize the stack with the starting city
        stack.push(new StackFrame(startCity, new MyArrayList<>(), 0));
        
        // Traverse the graph
        // Continue processing the queue until every city has been explored
        while (!stack.isEmpty()) {
            StackFrame frame = stack.pop();
            String currentCity = frame.city;
            MyArrayList<String> path = frame.path;
            int pathLength = frame.pathLength;
            
            // Add the current city to the path
            path.add(currentCity);
            
            // Check if the end city is reached
            if (currentCity.equals(endCity)) {
            	// Check if a shorter path is found
            	// Update the shortest path 
                if (pathLength < dfsPath.pathLength) {
                	// Copy the current path
                	dfsPath.path = copyPath(path);
                	// Update the shortest path length
                	dfsPath.pathLength = pathLength;
                }
                // Backtrack
                path.remove(path.size() - 1);
                continue;
            }

            // Mark the current city as visited
            if (!visitedContains(visited, currentCity)) {
                visited.add(currentCity);
            }
            
            // Process neighbours of the current city
            for (Node node : nodes) {
                if (node.getName().equals(currentCity)) {
                    for (CityData neighbour : node.getNeighbours()) {
                    	// New path length
                        int newPathLength = pathLength + neighbour.getCityDistance();
                        // Add a neighbour to the stack
                        // If the new path length is shorter than the current shortest path
                        // If the neighbour is not already in the current path
                        if (newPathLength < dfsPath.pathLength && !pathContains(path, neighbour.getCityName())) {
                            MyArrayList<String> newPath = copyPath(path);
                            stack.push(new StackFrame(neighbour.getCityName(), newPath, newPathLength));
                        }
                    }
                 // Stop processing neigbours
                    break;
                }
            }
            // Backtrack
            // Remove the last city in the path
            path.remove(path.size() - 1);
        }
        // Return the shortest path and its length
        return dfsPath;
    }

    //Represent a state in the DFS stack
    // Why we used StackFrame?
    // Easier to manage the current city, path, and path length
    private static class StackFrame {
    	// The current city
        String city;
        // The path taken to reach this city
        MyArrayList<String> path;
        int pathLength;

        public StackFrame(String city, MyArrayList<String> path, int pathLength) {
            this.city = city;
            this.path = path;
            this.pathLength = pathLength;
        }
    }
    
    // Create a copy of the path
    // Time Complexity -> O(n)
    // n is the size of the original path
    private static MyArrayList<String> copyPath(MyArrayList<String> original) {
        MyArrayList<String> copy = new MyArrayList<>();
        // Copy the current path
        // Ensure it stays unchanged during the traversal
        for (int i = 0; i < original.size(); i++) {
            copy.add(original.get(i));
        }
        return copy;
    }
    
    // Check if the given city is visited
    // Time Complexity -> O(n)
    // n is the size of the visited list
    private static boolean visitedContains(MyArrayList<String> visited, String city) {
        for (int i = 0; i < visited.size(); i++) {
            if (visited.get(i).equals(city)) return true;
        }
        return false;
    }
    
    // Check if the city is in the current path
    // Time Complexity -> O(n)
    // n is the size of the path
    private static boolean pathContains(MyArrayList<String> path, String city) {
        for (int i = 0; i < path.size(); i++) {
            if (path.get(i).equals(city)) return true;
        }
        return false;
    }
}
