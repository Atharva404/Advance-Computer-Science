/* Atharva Gupta, Independence HS APCS, 3/24/21
 * Employee Database Project: <This lab is to create a data
 * structure to store a number of Employee Objects./>
 */
public class EmployeeDatabase {  
 
   //instance fields
   private Entry[] table;
   private static int tableSize = 100;
   private int size;
   
   //private inner class
   private class Entry {
      int id;
      Employee employee;
      
      public Entry(int id, Employee employee) {
         this.id = id;
         this.employee = employee;
      }
      
      @Override
      public String toString() {
         return id + ": " + employee.name;
      }
   }
   
   //constructor
   public EmployeeDatabase(int id, Employee employee) {
      table = new Entry[tableSize];
      table[badHashCode(id)] = new Entry(id, employee);
      size = 1;      
   }
   
   //will increase the length of table
   private void grow() {
      Entry[] temp = new Entry[table.length + 100]; 
      for (int i = 0; i < table.length; i++) {
         temp[i] = table[i]; 
      }
      table = temp; 
      tableSize = table.length;
   }
   
   //returns the index
   public int badHashCode(int idNum) {
      return idNum % tableSize;
   }
   
   public int goodHashCode(int idNum) {
      table = new Entry[79];
      tableSize = 79;
      return idNum % tableSize;
   }
   
   //puts the value in the specified index
   public void put(int key, Employee value) {
      if(size == tableSize - 1) {
         grow();
      }
      int index = linearCollision(key);
      table[index] = new Entry(key, value);
      size++;
   }
   
   //if want the employee value, this is getMethod
   public Employee get(int key) {
      int index = badHashCode(key);
      while(table[index].id != key) {
         index++;
      }
      if(table[index].id == key) {
         return table[index].employee;
      } else {
         return null;
      }
   }
   
   //if want to get Entry Object
   private Entry getEntry(int key) {
      while(table[badHashCode(key)].id != key) {
         key++;
      }
      if(table[badHashCode(key)].id == key) {
         return table[key];
      } else {
         return null;
      }
   }
   //will incrememnt the value of index if the 
   //position is taken by 1.
   public int linearCollision(int index) {
     while(table[badHashCode(index)] != null) {
        index++;
     }
      return badHashCode(index);
   }
   
   //will increment the position is the position
   //is taken
   public int quadCollision(int index) {
      int i = 0;;
      while(table[badHashCode(index)] != null) {
         index = index + (int)(Math.pow(i, 2));
         i++;
      } 
      return index;
   }
   
}