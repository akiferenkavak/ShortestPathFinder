// This class used to implement the Breadth First Search (BFS) algorithm to find the shortest path between two cities 
public class BFS {
	
	// Store the shortest path and its length
    public static class ShortestPathBFS {
    	// List of cities in the shortest path
        MyArrayList<String> path;
        // Total length of the shortest path
        int pathLength;
        
        // Constructor to initialize an empty path
        // Time Complexity -> O(1)
        public ShortestPathBFS() {
            this.path = new MyArrayList<>();
            // Set the paths length to a high value
            // Ensure it is updated with shorter paths during BFS
            this.pathLength = Integer.MAX_VALUE;
        }
    }
    
    // Find the shortest path between two cities
    // Time Complexity -> O(V + E)
    // V is the number of nodes (cities)
    // E is the number of edges
    public static ShortestPathBFS findPath(Node[] nodes, String startCity, String endCity) {
    	
    	// Store the shortest path and its length
        ShortestPathBFS bfsPath = new ShortestPathBFS();
        
        // Manage the BFS traversal
        
        // Why we used queue (MyQueue)?
        // BFS requires a queue to process nodes in a first-in-first-out (FIFO) manner 
        // Ensure level by level traversal
        MyQueue<QueueFrame> queue = new MyQueue<>(10);
        
        // Track visited cities
        
        // Why we used array list (MyArrayList)?
        // Dynamic resizing and efficiency in random access
        MyArrayList<String> visited = new MyArrayList<>();
        // Track the shortest distance from the start city to each visited city
        MyArrayList<Integer> pathLengths = new MyArrayList<>();

        // Add the starting city to the queue
        // Enqueue the starting city with an empty path and 0 km distance
        queue.enqueue(new QueueFrame(startCity, new MyArrayList<>(), 0));
        // Mark the starting city as visited
        visited.add(startCity);
        // The distance from start city to itself (0 km)
        pathLengths.add(0);
        
        // Traverse the graph
        // Continue processing the queue until every city has been explored
        while (!queue.isEmpty()) {
        	
        	// Process the following city (dequeue)
            QueueFrame frame = queue.dequeue();
            // The current city for processing
            String currentCity = frame.city;
            // Path taken to reach the city
            MyArrayList<String> path = frame.path;
            // Distance to this city
            int pathLength = frame.pathLength;
            
            // Add the current city to the path
            path.add(currentCity);
            
            // Check if the end city is reached
            if (currentCity.equals(endCity)) {
            	// Check if a shorter path is found
            	// Update the shortest path 
                if (pathLength < bfsPath.pathLength) {
                	// Copy the current path
                	bfsPath.path = copyPath(path);
                	// Update the shortest path length
                	bfsPath.pathLength = pathLength;
                }
                // Continue for further processing of this path
                continue;
            }

            // Explore the neighbours of the current city
            for (Node node : nodes) {
            	// Find the current city in the graph
                if (node.getName().equals(currentCity)) {
                	// Iterate through the neighbors of the current city
                    for (CityData neighbour : node.getNeighbours()) {
                    	// Calculate distance to neighbour
                        int newPathLength = pathLength + neighbour.getCityDistance();
                        // Check if the neighbour has been visited (with a shorter or equal path length)
                        boolean alreadyVisited = false;
                        for (int i = 0; i < visited.size(); i++) {
                            if (visited.get(i).equals(neighbour.getCityName()) && pathLengths.get(i) <= newPathLength) {
                                alreadyVisited = true;
                                break;
                            }
                        }

                        // Add a neighbour if it has not been visited with a shorter path
                        if (!alreadyVisited) {
                        	// Mark as visited
                            visited.add(neighbour.getCityName());
                            // The new path length
                            pathLengths.add(newPathLength);
                            // Copy current path
                            // Avoid changes
                            MyArrayList<String> newPath = copyPath(path);
                            // Enqueue neighbour
                            queue.enqueue(new QueueFrame(neighbour.getCityName(), newPath, newPathLength));
                        }
                        
                    }
                    // Stop processing the neigbours
                    break;
                }
            }
        }
        
        // Return the shortest path and its length
        // When the queue is empty
        return bfsPath;
    }
    
    // Represent a state in the BFS queue
    // Why we used QueueFrame?
    // Easier to manage the current city, path, and path length
    private static class QueueFrame {
    	// The current city
        String city;
        // The path taken to reach this city
        MyArrayList<String> path;
        int pathLength;

        public QueueFrame(String city, MyArrayList<String> path, int pathLength) {
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
    
}
