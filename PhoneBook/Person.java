/* Atharva Gupta, Independence HS APCS, 4/7/21
 * PhoneBook Project: <Person.java stores the person name./>
 */
public class Person {
   String name;
   
   public Person(String name) {
      this.name = name;
   }
   
   //@Override
   ///provides a unique address to be taken into account
   //for the index of the array
   public int hashCode() {
      int asciiSum = 0;
      for(int i = 0; i < name.length(); i++) {
         asciiSum += (int)(name.charAt(i));
      }
      return asciiSum;
   }
   
   //@Override
   public boolean equals(Person other) {
      return other.name.equals(name);
   }
   
   //@Override
   public String toString() {
      return "Name: " + name;
   }
}  