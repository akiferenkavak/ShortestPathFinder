import java.util.*;

public class ShortestPathDFS {

    public static class Result {
        List<String> shortestPath;
        int shortestDistance;

        Result() {
            this.shortestPath = new ArrayList<>();
            this.shortestDistance = Integer.MAX_VALUE;
        }
    }


    public static Result findShortestPath(Node[] nodes, String startCity, String endCity) {
        // En kısa yol ve mesafe için sonuç saklama
        Result result = new Result();

        // Stack: DFS için kullanılacak
        Stack<StackFrame> stack = new Stack<>();

        // Ziyaret edilen şehirleri saklayan set
        Set<String> visited = new HashSet<>();

        // İlk şehirle başla
        stack.push(new StackFrame(startCity, new ArrayList<>(), 0));

        while (!stack.isEmpty()) {
            // Stack'ten bir frame al
            StackFrame frame = stack.pop();
            String currentCity = frame.city;
            List<String> path = frame.path;
            int currentDistance = frame.distance;

            // Eğer şehir zaten ziyaret edildiyse devam et
            if (visited.contains(currentCity)) continue;

            // Mevcut şehri ziyaret edilmiş olarak işaretle
            visited.add(currentCity);
            path.add(currentCity);

            // Eğer hedef şehre ulaşıldıysa
            if (currentCity.equals(endCity)) {
                if (currentDistance < result.shortestDistance) {
                    result.shortestDistance = currentDistance;
                    result.shortestPath = new ArrayList<>(path); // Kopya al
                    System.out.println("found it");
                }
            } else {
                // Mevcut şehrin Node'unu bul
                Node currentNode = findNodeByName(nodes, currentCity);

                // Komşuları stack'e ekle
                for (CityData neighbour : currentNode.getNeighbours()) {
                    if (!visited.contains(neighbour.getCityName())) {
                        stack.push(new StackFrame(neighbour.getCityName(),
                                new ArrayList<>(path),
                                currentDistance + neighbour.getCityDistance()));
                    }
                }
            }

            // Backtracking: Mevcut şehri ziyaret edilmemiş olarak işaretle
           // visited.remove(currentCity);
        }

        return result;
    }

    private static Node findNodeByName(Node[] nodes, String cityName) {
        for (Node node : nodes) {
            if (node.getName().equals(cityName)) {
                return node;
            }
        }
        throw new IllegalArgumentException("City " + cityName + " not found in nodes.");
    }

    // Stack Frame Class: Iterative DFS için gerekli
    static class StackFrame {
        String city;
        List<String> path;
        int distance;

        StackFrame(String city, List<String> path, int distance) {
            this.city = city;
            this.path = path;
            this.distance = distance;
        }
    }
/* 
    public static void main(String[] args) {
        // Örnek şehir ve mesafe verileriyle oluşturulmuş Node array'i
        Node[] nodes = {
            new Node("Istanbul", new CityData[]{new CityData("Ankara", 450), new CityData("Izmir", 320)}),
            new Node("Ankara", new CityData[]{new CityData("Istanbul", 450), new CityData("Bursa", 200)}),
            new Node("Izmir", new CityData[]{new CityData("Istanbul", 320), new CityData("Antalya", 250)}),
            new Node("Bursa", new CityData[]{new CityData("Ankara", 200), new CityData("Antalya", 350)}),
            new Node("Antalya", new CityData[]{new CityData("Izmir", 250), new CityData("Bursa", 350)})
        };

        // Başlangıç ve hedef şehir
        String startCity = "Istanbul";
        String endCity = "Antalya";

        // En kısa yolu bul
        Result result = findShortestPath(nodes, startCity, endCity);

        // Sonuçları yazdır
        System.out.println("Shortest Path: " + result.shortestPath);
        System.out.println("Shortest Distance: " + result.shortestDistance);
    }*/
}
