***** City Pathfinding Project *****

***** Overview *****

This project implements a pathfinding system for Turkish cities using two popular graph traversal algorithms: Breadth-First Search (BFS) and Depth-First Search (DFS). The system includes a GUI application, command-line interaction, and a robust backend to handle data processing and algorithm execution.

The system provides users with:

Shortest paths between cities using BFS and DFS.

Detailed execution times and path lengths for comparison.

A visual interface for easy interaction.

***** Features *****

Graph Representation: Turkish cities are represented as nodes in a graph, with distances between them as edges.

Algorithm Comparison: Both BFS and DFS algorithms are implemented for pathfinding.

Custom Data Structures: Includes custom implementations of stack and queue interfaces for flexibility and performance.

GUI: A user-friendly interface to select cities and view results.

Command-Line Interaction: Direct access to algorithms via terminal for advanced users.

***** Files *****

File

Description

Main.java

Handles user interaction, executes BFS and DFS, and displays results.

SetUp.java

Constructs the graph from a CSV file containing adjacency matrix data.

Node.java

Represents cities as nodes in the graph.

CityData.java

Stores city details and distances to other cities.

CityChecker.java

Validates user input for city names.

BFS.java

Implements the BFS algorithm to find the shortest path.

DFS.java

Implements the DFS algorithm to find the shortest path.

GUI.java

Provides a graphical user interface for city selection and pathfinding.

MyQueueInterface.java

Defines the contract for custom queue implementations.

MyStackInterface.java

Defines the contract for custom stack implementations.

***** Requirements *****

Java Development Kit (JDK): Version 8 or higher.

Swing Library: Included in the Java Standard Edition for GUI development.

CSV File: A properly formatted CSV file representing the adjacency matrix for the cities.

***** Setup Instructions *****

Clone the repository:

git clone <repository_url>

Open the project in your preferred Java IDE.

Ensure the CSV file containing the adjacency matrix is in the project root directory.

Run the Main.java file for command-line interaction or GUI.java for the graphical interface.

***** Usage *****

GUI

Launch the GUI application by running GUI.java.

Select the start and end cities from the dropdown menus.

Click Run BFS or Run DFS to calculate the shortest path.

View the results in the output area.

Command-Line

Run the Main.java file.

Enter the start and end cities when prompted.

View the shortest path, path length, and execution time for BFS and DFS in the terminal.

Enter Q to quit the application.

***** Custom Data Structures *****

MyQueueInterface: Defines methods for queue operations, such as enqueue, dequeue, and peek.

MyStackInterface: Defines methods for stack operations, such as push, pop, and peek.

***** Algorithms *****

Breadth-First Search (BFS)

Time Complexity: 

Ensures the shortest path in terms of the number of edges.

Depth-First Search (DFS)

Time Complexity: 

Explores deeper paths before backtracking, which may not always yield the shortest path.

***** Future Improvements *****

Extend support for weighted graphs in BFS and DFS.

Implement additional graph traversal algorithms, such as Dijkstra's or A*.

Provide analytics and visualizations for the graph structure.

***** Note *****

This project is intended for educational purposes and personal use. If you find it helpful, a mention or credit would be appreciated.
