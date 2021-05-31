public class Location {
   private int row;
   private int col;
   private int distance; 
   
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
      
   public void setDistance(int distance) {
      this.distance = distance;
   }
   
   public boolean equals(Location o) {
      if(o == null) {
         return false;
      }
      return o.getRow() == this.row && o.getCol() == this.col;
   }
   
   @Override
   public int hashCode() {
      return row * col; 
   }
   
   public String toString() {
      return "X: " + row + " Y: " + col;
   }
}

