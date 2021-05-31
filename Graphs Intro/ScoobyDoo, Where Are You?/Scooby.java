import java.util.*;
import java.io.*;
public class Scooby {

   public static void main(String[] args) throws FileNotFoundException {
      Scanner scanner = new Scanner(new File("scooby.dat"));
      int n = scanner.nextInt();
      scanner.nextLine();
      Map<String, Set<String>> graph = new HashMap<>();
      boolean connection = false;
      while(scanner.hasNextLine()) {
         String line = scanner.nextLine();
         buildGraph(graph, line);
         String isConnection = scanner.nextLine();
         Set<String> visited = new HashSet<>();
         visited.add(isConnection.substring(0, 1));
         connection = hasConnection(graph, isConnection.substring(0, 1), isConnection.substring(1, 2), visited);
         if(connection) {
            System.out.println("yes");
         } else {
            System.out.println("no");
         }
         graph = new HashMap<>();
      }
   
   }
   
      //new set..if visited add character to the set
   public static boolean hasConnection(Map<String, Set<String>> graph, String room1, String room2, Set<String> visited) {
      if(!graph.containsKey(room1) && !graph.containsKey(room2)) {
         return false;
      }
      if(room1 != null && room2 != null) {
         Set<String> neighbors = graph.get(room1);
         if(neighbors.contains(room2)){
            return true;
         } else {
            for(String adjacent: neighbors) {
               if(!visited.contains(adjacent)) {
                  visited.add(adjacent);
                  return hasConnection(graph, adjacent, room2, visited);
               } 
            }
         }
      }
      return false;
   }
   
   public static void buildGraph(Map<String, Set<String>> graph, String line) {
      for(int i = 0; i < line.length(); i+=3) {
         String room = line.substring(i, i + 2);
         if(!graph.containsKey(room.substring(0, 1))) {
            graph.put(room.substring(0, 1), new HashSet<String>());  
         }
         graph.get(room.substring(0, 1)).add(room.substring(1, 2));
         if(!graph.containsKey(room.substring(1, 2))) {
            graph.put(room.substring(1, 2), new HashSet<String>());
         }
         graph.get(room.substring(1, 2)).add(room.substring(0, 1));
      }
   }
}