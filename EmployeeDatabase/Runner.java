public class Runner {
   public static void main(String[] args) {
   System.out.println(1792 % 100);
      EmployeeDatabase base = new EmployeeDatabase(1, new Employee("Atharva"));
      // base.put(2, new Employee("toy2"));
//       base.put(3, new Employee("toy3"));
//       base.put(4, new Employee("toy4"));
//       base.put(5, new Employee("toy5"));
//       base.put(6, new Employee("toy6"));
//       base.put(7, new Employee("toy7"));
//       System.out.println(base.get(7));
//       
//       base.put(34, new Employee("toy34"));
//       
//       base.put(78, new Employee("toy78"));
//       
      base.put(92, new Employee("toy92")); 
      System.out.println(base.get(92)); 
      
      base.put(1792, new Employee("updatedtoy92"));
      System.out.println(base.get(1792));
      
     // System.out.println(base.get(91)); //should throw nullpointer exception
   }
}