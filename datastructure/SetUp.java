// This class handles the graph construction

// Read the CSV file line by line
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;


public class SetUp {
	
	// Read the adjacency matrix that represents the cities and distances from the CSV file
	// Convert it into a 2D array
    public static int[][] get2DArrayFromCSV(String filePath) {
        int[][] array;
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            // Temporarily store the lines in the array list
            // This allows for a flexible resizing
            MyArrayList<String[]> lines = new MyArrayList<>();
            
            // Read each lines
            // Split it into an array of strings
            while ((line = br.readLine()) != null) {
                lines.add(line.split(","));
            }
            
            // Calculate the rows and columns
            // Exclude the headers
            int rows = lines.size() - 1;
            int cols = lines.get(0).length - 1;
            
            // Initialize the adjacency matrix
            array = new int[rows][cols];
            
            for (int i = 1; i <= rows; i++) {
                for (int j = 1; j <= cols; j++) {
                	// Parse distances
                	// Store distances
                	// Skip headers
                    array[i - 1][j - 1] = Integer.parseInt(lines.get(i)[j]);
                }
            }
            // Return the finished adjacency matrix
            return array;
        } catch (IOException e) {
        	// Print error
            e.printStackTrace();
            // Return null if there is an error while reading the file
            return null;
        }
    }
    
    // Convert the 2D adjacency matrix into a graph
    public static Node[] generateNodes(int[][] distances, City[] cityList) {
    	// Initialize an array
    	// Store the nodes
        Node[] nodes = new Node[cityList.length];
        // Create a node for each city using the City enum
        for (City city : cityList) {
        	// Create a node for each city with its name
            nodes[city.getIndex()] = new Node(city.name());
        }
        // Use the adjacency matrix to add in the neighbors for each node (city)
        for (int i = 0; i < distances.length; i++) {
            for (int j = 0; j < distances[i].length; j++) {
            	// Check the edges (distances)
                if (distances[i][j] > 0 && distances[i][j] < 9999) {
                	// Add the neighbours of the current node (city) 
                    nodes[i].addNeighbour(new CityData(cityList[j].name(), distances[i][j]));

                }
            }
        }
        // Return the final array of nodes (cities) 
        // Represent the graph
        return nodes;
    }
    
    // Why we used an enum?
    // Readability -> Names of the cities are easier to reference than indices
    // Consistency -> Eliminate the errors due to mismatched indices
    public enum City {
        ISTANBUL(0), ANKARA(1), IZMIR(2), BURSA(3), ADANA(4),
        GAZIANTEP(5),KONYA(6), DIYARBAKIR(7), ANTALYA(8), 
        MERSIN(9), KAYSERI(10), URFA(11), MALATYA(12), 
        SAMSUN(13), DENIZLI(14), BATMAN(15), TRABZON(16);

        // Unique indexes for each city
    	private final int index;

        // Constructor to initialize the cities indexes
    	City(int index) {
            this.index = index;
        }
    	
    	// Return the index of the city
        // Time Complexity -> O(1)
        public int getIndex() {
            return index;
        }

    }
}
