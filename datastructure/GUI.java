import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI {

    // Nodes (cities) in the graph
    private static Node[] nodes;

    public static void main(String[] args) {
        // Initialize the graph nodes using adjacency matrix from the CSV file
        // Time Complexity -> O(V^2)
    	// V is the number of cities (nodes)
        nodes = SetUp.generateNodes(SetUp.get2DArrayFromCSV("Turkish cities.csv"), SetUp.City.values());

        // Set up the main application frame
        JFrame frame = new JFrame("City Search with BFS and DFS");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(650, 550);

        // Create a main panel to organize GUI components
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(245, 245, 245));

        // Drop down panel for city selection
        JPanel dropdownPanel = new JPanel();
        dropdownPanel.setLayout(new GridLayout(2, 2, 10, 10));
        dropdownPanel.setBorder(BorderFactory.createTitledBorder("Select Cities"));
        dropdownPanel.setBackground(new Color(240, 248, 255));

        // Create drop downs for selecting start and end cities
        JComboBox<String> startCityDropdown = new JComboBox<>();
        JComboBox<String> endCityDropdown = new JComboBox<>();

        // Time Complexity -> O(V), where V is the number of cities
        for (SetUp.City city : SetUp.City.values()) {
            startCityDropdown.addItem(city.name());
            endCityDropdown.addItem(city.name());
        }

        // Add labels and drop downs to the panel
        dropdownPanel.add(new JLabel("Start City:", JLabel.RIGHT));
        dropdownPanel.add(startCityDropdown);
        dropdownPanel.add(new JLabel("End City:", JLabel.RIGHT));
        dropdownPanel.add(endCityDropdown);

        // Buttons to execute BFS and DFS algorithms
        JButton bfsButton = new JButton("Run BFS");
        JButton dfsButton = new JButton("Run DFS");

        // Style the buttons
        bfsButton.setBackground(new Color(72, 209, 204));
        bfsButton.setForeground(Color.WHITE);
        bfsButton.setFont(new Font("Arial", Font.BOLD, 14));

        dfsButton.setBackground(new Color(255, 127, 80));
        dfsButton.setForeground(Color.WHITE);
        dfsButton.setFont(new Font("Arial", Font.BOLD, 14));

        // Add buttons to a separate panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setBackground(new Color(245, 245, 245));
        buttonPanel.add(bfsButton);
        buttonPanel.add(dfsButton);

        // Text area to display the results of the searches
        JTextArea resultArea = new JTextArea(15, 40);
        resultArea.setEditable(false);
        resultArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        resultArea.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        JScrollPane scrollPane = new JScrollPane(resultArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Results"));

        // Add all panels to the main panel
        mainPanel.add(dropdownPanel, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        mainPanel.add(scrollPane, BorderLayout.SOUTH);

        // Add the main panel to the frame
        frame.add(mainPanel);

        // Define actions for BFS button
        bfsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String startCity = (String) startCityDropdown.getSelectedItem();
                String endCity = (String) endCityDropdown.getSelectedItem();

                if (startCity != null && endCity != null) {
                    long startTime = System.currentTimeMillis();
                    // Time Complexity -> O(V + E)
                    BFS.ShortestPathBFS bfsResult = BFS.findPath(nodes, startCity, endCity);
                    long endTime = System.currentTimeMillis();

                    resultArea.append("\n--- BFS Results ---\n");
                    resultArea.append("Path: " + formatPath(bfsResult.path) + "\n");
                    resultArea.append("Path Length: " + bfsResult.pathLength + "\n");
                    resultArea.append("Execution Time: " + (endTime - startTime) + " ms\n");
                } else {
                    resultArea.append("\nPlease select both start and end cities.\n");
                }
            }
        });

        // Define actions for DFS button
        dfsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String startCity = (String) startCityDropdown.getSelectedItem();
                String endCity = (String) endCityDropdown.getSelectedItem();

                if (startCity != null && endCity != null) {
                    long startTime = System.currentTimeMillis();
                    // Time Complexity -> O(V + E)
                    DFS.ShortestPathDFS dfsResult = DFS.findPath(nodes, startCity, endCity);
                    long endTime = System.currentTimeMillis();

                    resultArea.append("\n--- DFS Results ---\n");
                    resultArea.append("Path: " + formatPath(dfsResult.path) + "\n");
                    resultArea.append("Path Length: " + dfsResult.pathLength + "\n");
                    resultArea.append("Execution Time: " + (endTime - startTime) + " ms\n");
                } else {
                    resultArea.append("\nPlease select both start and end cities.\n");
                }
            }
        });

        // Display the frame
        frame.setVisible(true);
    }

    // Format for the path display
    // Time Complexity -> O(n)
    // n is the size of the path
    private static String formatPath(MyArrayList<String> path) {
        if (path == null || path.size() == 0) {
            return "No path found.";
        }
        StringBuilder formattedPath = new StringBuilder();
        for (int i = 0; i < path.size(); i++) {
            formattedPath.append(path.get(i));
            if (i < path.size() - 1) {
                formattedPath.append(" -> ");
            }
        }
        return formattedPath.toString();
    }
}