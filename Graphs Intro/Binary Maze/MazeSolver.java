import java.util.*;
import java.io.*;

class Location {
   private int row;
   private int col;
   private int distance; 
   
   public Location(Location loc, int dis) {
      this.row = loc.getRow();
      this.col = loc.getCol();
      this.distance = dis;
   }
   
   public Location(int x, int y) {
      this.row = x;
      this.col = y;
   }
      
   public int getRow() {
      return this.row;
   }
      
   public Location(Location loc) {
      this.row = loc.getRow();
      this.col = loc.getCol();
   }
   public int getCol() {
      return this.col;
   }
   
   public int getDistance() {
      return distance;
   }
      
   public void setDistance(int distance) {
      this.distance = distance;
   }
   
   @Override
   public boolean equals(Object object) {
      Location o = (Location)object;
      return o != null && (o.getRow() == this.row && o.getCol() == this.col);
   }
   
   @Override
   public int hashCode() {
      return row * col; 
   }
   
   public String toString() {
      return "X: " + row + " Y: " + col;
   }
}


public class MazeSolver {
   int width;
   int height;
   int[][] maze;
   Map<Location, Set<Location>> graph;
   
    
   public MazeSolver(int h, int w) { 
      width = w; 
      height = h;
      maze = new int[height][width];
      graph = new HashMap<>();
   }
   
   public static void main(String[] args) throws Exception {
      Scanner reader = new Scanner(new File("maze.txt"));
      MazeSolver ms = new MazeSolver(reader.nextInt(), reader.nextInt());
      for (int i = 0; i < ms.height; i++) {
         for (int j = 0; j < ms.width; j++) {
            ms.maze[i][j] = reader.nextInt();
         }
      }
      ms.buildGraph();
      Location start = new Location(reader.nextInt(), reader.nextInt());
      start.setDistance(0);
      Location end = new Location(reader.nextInt(), reader.nextInt());
      
      System.out.print(ms.shortestPath(start, end));
   }
   public void buildGraph() {
      for(int i = 0; i < height; i++) {
         for(int j = 0; j < width; j++) {
            if(maze[i][j] == 1) {
               Location loc = new Location(i, j);
               graph.put(loc, new HashSet<Location>());
               Location loc1 = new Location(i, j - 1);
               if(isValid(loc1)) {
                  graph.get(loc).add(loc1);
                  //graph.get(new Location(i, j)).add(new Location(i - 1, j));
               }
               Location loc2 = new Location(i, j + 1);
               if(isValid(loc2)) {
                  graph.get(loc).add(loc2);
                  //graph.get(new Location(i, j)).add(new Location(i, j + 1));
               }
               Location loc3 = new Location(i + 1, j);
               if(isValid(loc3)) {
                  graph.get(loc).add(loc3);
                  //graph.get(new Location(i, j)).add(new Location(i + 1, j));
               }        
               Location loc4 = new Location(i - 1 , j);
               if(isValid(loc4)) {
                  graph.get(loc).add(loc4);
                  //graph.get(new Location(i, j)).add(new Location(i, j - 1));
               }
            }
         }  
      }
   }

   public int shortestPath(Location start, Location end) {
      Queue<Location> q = new LinkedList<>();
      Set<Location> visited = new HashSet<>();
      Location newLoc = new Location(start, 0);
      visited.add(new Location(newLoc));
      q.add(newLoc);
      while(!q.isEmpty()) {
         Location current = q.peek();
         Location currentLocation = new Location(current);
         if(current.equals(new Location(end.getRow(), end.getCol()))) {
            return current.getDistance();
         }     
         q.remove();      
         Set<Location> adjacent = graph.get(new Location(currentLocation.getRow(), currentLocation.getCol()));
         
         if(adjacent != null) {
            for(Location adjacentLoc: adjacent) {
               if(isValid(adjacentLoc) && !visited.contains(new Location(adjacentLoc.getRow(), adjacentLoc.getCol()))) {  
                  visited.add(adjacentLoc);
                  Location newAdjacnetLoc = new Location(adjacentLoc, current.getDistance() + 1);
                  q.add(newAdjacnetLoc);
               } 
            }
         }
      }
      return -1;
   }
   
   private boolean isValid(Location locToTest) {
      int row = locToTest.getRow(), col = locToTest.getCol();
      return row >= 0 && row < height && col >= 0 && col < width && maze[row][col] == 1;
   }
}