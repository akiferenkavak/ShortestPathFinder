// This class represents the cities (nodes) and its neighbor cities
public class Node {
	// Name of the city
    private String name;
    // Array list to store neighbour cities and distances 
    private MyArrayList<CityData> neighbours;
    
 // Why we used Array List? 
    
 // DYNAMIC SIZE, EFFICIENT ACCESS, MEMORY EFFICIENCY
 // Cities can have different numbers of neighbors
 // MyArrayList efficiently handles this situation and we didn't need predefined array sizes
    
 // The adjacency list implementation had an O(1) average time complexity for adding neighbors
 // It was great for graph representation
 
 // The adjacency list uses memory based on the number of edges
 // It made more efficient for graphs like a city network compared to adjacency matrices

    
    
    // Constructor to initialize the city name and a new empty neighbors list
    // Time Complexity -> O(1)
    public Node(String name) {
        this.name = name;
        this.neighbours = new MyArrayList<>();
    }

    // Constructor with city names and neighbours
    // Time Complexity -> O(1)
    public Node(String name, MyArrayList<CityData> neighbours) {
        this.name = name;
        this.neighbours = neighbours;
    }
    
    // Return the name of the city
    // Time Complexity -> O(1)
    public String getName() {
        return name;
    }
    
    // Return the neighbor list
    // Time Complexity -> O(1)
    public MyArrayList<CityData> getNeighbours() {
        return neighbours;
    }
    
    // Add a neighbour city to the neighbour list
    // Time Complexity -> O(1)
    public void addNeighbour(CityData neighbour) {
        this.neighbours.add(neighbour);
    }
    
    // Print the city name and its neighbors
    // Time Complexity -> O(n)
    // n is the number of neighbours
    @Override
    public String toString() {
        return "Node{name='" + name + "', neighbours=" + neighbours.toString() + "}";
    }
}
