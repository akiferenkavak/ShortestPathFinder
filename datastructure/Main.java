// This class handles user interaction
// Executes BFS and DFS 
// Displays the results for both algorithms
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
    	// Initialize the scanner for user input
        Scanner scanner = new Scanner(System.in);
        // Generate the graph representation from the adjacency matrix
        // Use the SetUp class to read data and generate nodes
        Node[] nodes = SetUp.generateNodes(SetUp.get2DArrayFromCSV("Turkish cities.csv"), SetUp.City.values());
        
        // Printout the cities in the matrix
        System.out.println("CITIES:\n");
        for (SetUp.City city : SetUp.City.values()) {
            System.out.println(city.name());
        }
        
        // Ask user for input until they choose to quit
        while (true) {
        	
            // Ask user for start city
            System.out.print("\nEnter the starting city (or 'Q' to Quit): ");
            String startCity = scanner.nextLine().trim();
            startCity = CityChecker.checkCityInput(startCity, nodes);
            
            // Ask user for end city
            System.out.print("Enter the ending city: ");
            String endCity = scanner.nextLine().trim();
            endCity = CityChecker.checkCityInput(endCity, nodes);

            // Run DFS
            long dfsStartTime = System.currentTimeMillis();
            DFS.ShortestPathDFS dfsResult = DFS.findPath(nodes, startCity, endCity);
            long dfsEndTime = System.currentTimeMillis();

            // Run BFS
            long bfsStartTime = System.currentTimeMillis();
            BFS.ShortestPathBFS bfsResult = BFS.findPath(nodes, startCity, endCity);
            long bfsEndTime = System.currentTimeMillis();

            // Display the results for DFS
            System.out.println("\nShortest Path with DFS");
            System.out.println("DFS Path: " + formatPath(dfsResult.path));
            System.out.println("DFS Path Length: " + dfsResult.pathLength);
            System.out.println("DFS Execution Time: " + (dfsEndTime - dfsStartTime) + " ms\n");
            
            // Display the results for BFS
            System.out.println("Shortest Path with BFS");
            System.out.println("BFS Path: " + formatPath(bfsResult.path));
            System.out.println("BFS Path Length: " + bfsResult.pathLength);
            System.out.println("BFS Execution Time: " + (bfsEndTime - bfsStartTime) + " ms\n");
        }
    }
    
    // Format the path for displaying like this "CITY1 -> CITY2 -> CITY3"
    // Time Complexity -> O(n)
    // n is the size of the path
    private static String formatPath(MyArrayList<String> path) {
        if (path == null || path.size() == 0) {
            return "No path found!";
        }
        StringBuilder formattedPath = new StringBuilder();
        for (int i = 0; i < path.size(); i++) {
            formattedPath.append(path.get(i));
            if (i < path.size() - 1) {
            	// Add arrows between cities
                formattedPath.append(" -> ");
            }
        }
        return formattedPath.toString();
    }
}
