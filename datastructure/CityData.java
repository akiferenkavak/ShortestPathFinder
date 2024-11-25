public class CityData {
    // Attributes
    private String cityName;
    private int cityDistance;

    // Constructor
    public CityData(String cityName, int cityDistance) {
        this.cityName = cityName;
        this.cityDistance = cityDistance;
    }

    // Getter for cityName
    public String getCityName() {
        return cityName;
    }

    // Setter for cityName
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    // Getter for cityDistance
    public int getCityDistance() {
        return cityDistance;
    }

    // Setter for cityDistance
    public void setCityDistance(int cityDistance) {
        this.cityDistance = cityDistance;
    }

    // Override toString() method for better readability
    @Override
    public String toString() {
        return "CityData{" +
                "cityName='" + cityName + '\'' +
                ", cityDistance=" + cityDistance +
                '}';
    }
}
