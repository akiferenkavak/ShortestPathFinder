// This class represents the cities and its distance to other cities
// The distances are edges in the graph
public class CityData {
	
    private String cityName;
    private int cityDistance;
    
    // Initialize the city name and distance using constructor
    // Time Complexity -> O(1)
    public CityData(String cityName, int cityDistance) {
        this.cityName = cityName;
        // Distance from the current city to the neighbor cities
        this.cityDistance = cityDistance;
    }
    
    // Return the name of the city
    // Time Complexity -> O(1)
    public String getCityName() {
        return cityName;
    }
    
    // Set the name of the city
    // Time Complexity -> O(1)
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
    
    // Return the city distance
    // Time Complexity -> O(1)
    public int getCityDistance() {
        return cityDistance;
    }
    
    // Set the city distance
    // Time Complexity -> O(1)
    public void setCityDistance(int cityDistance) {
        this.cityDistance = cityDistance;
    }
    
    // Time Complexity -> O(n)
    // n is the length of the cityName string
    @Override
    public String toString() {
        return "CityData{" + "cityName='" + cityName + '\'' + ", cityDistance=" + cityDistance + '}';
    }
}
