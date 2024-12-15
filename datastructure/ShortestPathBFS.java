import java.util.*;
//import CityData.java

public class ShortestPathBFS {

    public static class Result {
        List<String> shortestPath;
        int shortestDistance;

        public Result() {
            this.shortestPath = new ArrayList<>();
            this.shortestDistance = Integer.MAX_VALUE;
        }
    }

    public static Result findShortestPath(Node[] nodes, String startCity, String endCity) {
        Result result = new Result();
        MyQueue<QueueFrame> queue = new MyQueue<>(10);
    
        // İlk şehir ile stack başlatılıyor
        queue.enqueue(new QueueFrame(startCity, Arrays.asList(startCity), 0));
    
        while (!queue.isEmpty()) {
            QueueFrame frame = queue.dequeue();
            String currentCity = frame.city;
    
            // Eğer hedef şehre ulaşırsak
            if (currentCity.equals(endCity)) {
                if (frame.distance < result.shortestDistance) {
                    result.shortestDistance = frame.distance;
                    result.shortestPath = new ArrayList<>(frame.path);
                }
                continue;
            }
    
            // Mevcut şehirden komşuları al
            Node currentNode = findNodeByName(nodes, currentCity);
            if (currentNode == null) continue;
    
            // Komşuların kontrolü ve yeni yolların oluşturulması
            for (CityData neighbour : currentNode.getNeighbours()) {
                // Eğer bu path içinde şehir yoksa ilerle
                if (!frame.path.contains(neighbour.getCityName())) {
                    List<String> newPath = new ArrayList<>(frame.path);
                    newPath.add(neighbour.getCityName());
                    queue.enqueue(new QueueFrame(neighbour.getCityName(), newPath, frame.distance + neighbour.getCityDistance()));
                }
            }
        }
    
        // Eğer hedef şehre ulaşılamadıysa
        if (result.shortestPath.isEmpty()) {
            System.out.println("Hedef şehir '" + endCity + "' ulaşılamaz.");
            result.shortestDistance = -1;
        }
    
        return result;
    }
    

    private static Node findNodeByName(Node[] nodes, String cityName) {
        for (Node node : nodes) {
            if (node.getName().equals(cityName)) {
                return node;
            }
        }
        return null;
    }

    static class QueueFrame {
        String city;
        List<String> path;
        int distance;

        QueueFrame(String city, List<String> path, int distance) {
            this.city = city;
            this.path = new ArrayList<>(path); // Derin kopya
            this.distance = distance;
        }
    }

}
