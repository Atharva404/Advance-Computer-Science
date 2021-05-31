//loop in main
//3 - number of dominoe tiles
//2 - how many loops - new adjacency setups
//1 2 , 2 3 --> numSimulations
import java.util.*;
import java.io.*;
public class ChildsPlay {
   public static void main(String[] args) throws FileNotFoundException {
      Scanner scanner = new Scanner(new File("play.dat"));
      int numSimulations = scanner.nextInt();
      scanner.nextLine();
      int tiles = scanner.nextInt(), adjencencyLoop = scanner.nextInt();
      scanner.nextLine();
      Map<Integer, Set<Integer>> graph = new HashMap<>();
      for(int i = 0; i < adjencencyLoop; i++) {
         int num1 = scanner.nextInt(), num2 = scanner.nextInt();
         graph = buildGraph(graph, num1, num2);
         scanner.nextLine();
      }
      int startTile = scanner.nextInt();
      Set<Integer> visited = new HashSet<>();
      System.out.println(dominosKnocked(graph, visited, startTile, 1)); 
   }
   
   public static int dominosKnocked(Map<Integer, Set<Integer>> graph, Set<Integer> visited, int DominoNum, int knocked) {
      if(!graph.containsKey(DominoNum)) {
         return 0;
      }
      if(DominoNum != 0 && !visited.contains(DominoNum)) {
         visited.add(DominoNum);
         Set<Integer> neighbors = graph.get(DominoNum);
         for(Integer s: neighbors) {
            if(!visited.contains(s)) {
              knocked++;
              knocked = dominosKnocked(graph, visited, s, knocked);
            }
         }
         return knocked;
     }
     return knocked;
   }
   
   public static Map<Integer, Set<Integer>> buildGraph(Map<Integer, Set<Integer>> graph, int num1, int num2) {
      if(!graph.containsKey(num1)) {
         graph.put(num1, new HashSet<Integer>());  
         graph.get(num1).add(num2);
      } else {
        graph.get(num1).add(num2);
      }
      if(!graph.containsKey(num2)) {
         graph.put(num2, new HashSet<Integer>());
      }
      return graph;
   }

}