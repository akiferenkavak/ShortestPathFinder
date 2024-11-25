public class Node {
    // Attributes
    private String name;
    private CityData[] neighbours;

    // Constructor
    public Node(String name, CityData[] neighbours) {
        this.name = name;
        this.neighbours = neighbours;
    }

    // Getter for name
    public String getName() {
        return name;
    }

    // Setter for name
    public void setName(String name) {
        this.name = name;
    }

    // Getter for neighbours
    public CityData[] getNeighbours() {
        return neighbours;
    }

    // Setter for neighbours
    public void setNeighbours(CityData[] neighbours) {
        this.neighbours = neighbours;
    }

    // Override toString() method for better readability
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Node{name='" + name + "', neighbours=[");
        if (neighbours != null) {
            for (CityData neighbour : neighbours) {
                sb.append(neighbour.toString()).append(", ");
            }
            if (neighbours.length > 0) sb.setLength(sb.length() - 2); // Remove last comma
        }
        sb.append("]}");
        return sb.toString();
    }
}
