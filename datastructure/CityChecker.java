// This class validates user input for city names
// Ensures the input matches a valid city in the graph
import java.util.Scanner;

public class CityChecker {

    public static String checkCityInput (String inputCity, Node[] nodes) {
    	
    	// Scanner for user input
        Scanner scanner = new Scanner(System.in);

        while (true) {
        	// Iterate through all the cities (nodes)
        	// Check if the name user entered (inputCity) matches any existing city name
        	// Time Complexity -> O(n)
        	// n is the number of cities in the graph
        	for (Node node : nodes) {
        		// If the entered city name matches 
        		// Return the city name in upper case
                if (node.getName().equalsIgnoreCase(inputCity)) {
                    return inputCity.toUpperCase();
                }
            }

            // If user enters the letter "q" exit the program
        	// Time Complexity -> O(1)
            if (inputCity.equalsIgnoreCase("q")) {
                System.out.println("Thank you for using our program !");
                // Close the scanner
                scanner.close();
                // Terminate the program
                System.exit(0);
            }

            // If user enters an invalid city name ask user to enter a new and valid city name
            System.out.println("City not found!\n\nPlease enter a valid city: ");
            // Read new input (city name)
            inputCity = scanner.nextLine().trim();
        }
    }
}
