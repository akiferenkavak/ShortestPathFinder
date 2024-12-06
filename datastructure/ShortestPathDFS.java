import java.util.*;
//import CityData.java

public class ShortestPathDFS {

    public static class Result {
        List<String> shortestPath;
        int shortestDistance;

        Result() {
            this.shortestPath = new ArrayList<>();
            this.shortestDistance = Integer.MAX_VALUE;
        }
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


    public static ShortestPathDFS.Result findShortestPath(Node[] nodes, String startCity, String endCity) {
        
      // En kısa yol ve mesafe için sonuç saklama
      Result result = new Result();
        
      // Stack: DFS için kullanılacak
      MyStack<StackFrame> stack = new MyStack<>();

      // Stack for cities to visit
      MyStack<String> stackCities = new MyStack<>();

      //gonna use that later for managing stackCities
      String cityToVisit;

      // İlk şehirle başla
      stack.push(new StackFrame(startCity, new ArrayList<>(Arrays.asList(startCity)), 0));
      StackFrame frame = stack.peek();

        //gonna use that for deleting the frame at the top just as a variable
      StackFrame frameToDelete = stack.peek();

      System.out.println("********************");
      String currentCity = frame.city;
      List<String> currentPath = frame.path;
      int currentDistance = frame.distance;
      Node currentNode = findNodeByName(nodes, currentCity);

      //puts the neighbours of the initial city into the stackCities
      for(int i=0;  i < (currentNode.getNeighbours().length ); i++  ){
        stackCities.push((currentNode.getNeighbours()[i]).getCityName());
        System.out.println("that one is neighbout to "+startCity+" ---> "+stackCities.peek());
      }


      // as long as there is a city in the stack
      while (!stackCities.isEmpty()) {
        System.out.println("you are at the while loop");
        /*Object[] elements = stackCities.getData();
        System.out.println("Stack elements:");
        for (Object element : elements) {
            System.out.println(element);
        }*/
        
        //give the necessary info to the loop
        frame = stack.peek();
        currentCity = frame.city;
        currentDistance = frame.distance;
        if(currentCity == null){
            break;
        }else{
            currentNode = findNodeByName(nodes, currentCity);
        }
        currentPath = frame.path;

        System.out.println(currentCity);



        //add the neighbour cities into the stackCities at the very beginning 
        // don't add if you are at the endCity
        //but don't add if the stack length is 1 because we allready add it before the while loop
        /*if((stack.getSize() > 1) && (currentCity != endCity) ){
            for(int i=0;  i < (currentNode.getNeighbours().length ); i++  ){
                stackCities.push((currentNode.getNeighbours()[i]).getCityName());
                System.out.println("this is the added city to stackcities"+stackCities.peek());
            }
        }*/


        //check if we are at the endcity
        // and if we are then hold the path and distance at the result object if they are null or bigger than the current result.
        //delete the frame from the stack always if we are in the endcity
        if( currentCity == endCity ){
            //get the distance to the city to visit
            CityData[] neighbours = currentNode.getNeighbours();
            int distance = 0; // Bulamazsak varsayılan mesafe
            
            for (CityData neighbour : neighbours) {
                if (neighbour.getCityName().equals(endCity)) {
                    distance = neighbour.getCityDistance();
                    break;
                }
            }
            
            currentDistance = currentDistance + distance;
            if(currentDistance < result.shortestDistance){
                result.shortestDistance = currentDistance;
                result.shortestPath = currentPath;
            }
            frameToDelete = stack.pop();
        }

        //delete the peek city in the cityto visit stack if it is in the path of current frame
        while (  frame.path.contains(stackCities.peek())  ){
            cityToVisit = stackCities.pop();
        }
        //now we can get the city that we want to visit safely. And delete it from the stack in order to move on.
        cityToVisit = stackCities.pop();

        //now go to the cityTovisit by adding it to the top of the stack
        if(currentCity != endCity){
            currentPath.add(cityToVisit); // Yeni şehri ekle


            //get the distance to the city to visit
            CityData[] neighbours = currentNode.getNeighbours();
            int distance = 0; // Bulamazsak varsayılan mesafe
            
            for (CityData neighbour : neighbours) {
                if (neighbour.getCityName().equals(cityToVisit)) {
                    distance = neighbour.getCityDistance();
                    break;
                }
            }
            
            currentDistance = currentDistance + distance;
            stack.push(new StackFrame(cityToVisit, currentPath, currentDistance));
            //add neighbour cities to stackCities
            if((stack.getSize() > 1) && (currentCity != endCity) ){
                for(int i=0;  i < (currentNode.getNeighbours().length ); i++  ){
                    stackCities.push((currentNode.getNeighbours()[i]).getCityName());
                    System.out.println("this is the added city to stackcities "+stackCities.peek());
                }
            }
        }


      }

      //System.out.println("path is:" + stack.peek().path);

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


}
