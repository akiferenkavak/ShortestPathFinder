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

            this.path = new ArrayList<>(path); // Derin kopya
            this.distance = distance;
        }
    }

}
