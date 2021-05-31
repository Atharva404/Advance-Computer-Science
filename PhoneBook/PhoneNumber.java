/* Atharva Gupta, Independence HS APCS, 4/7/21
 * PhoneBook Project: <PhoneNumber.java stores the instance
 * variable of a string phoneNumber./>
 */
public class PhoneNumber {
   String phoneNumber;
   
   public PhoneNumber(String phoneNumber) {
      this.phoneNumber = phoneNumber;
   }
   
  //@Override
  //overriden from the object class 
  //checks if other phoneNumber  equals this phoneNumber
   public boolean equals(PhoneNumber other) {
      return other.phoneNumber.equals(this.phoneNumber);
   }
   
   //@Override
   public String toString() {
      return "PhoneNumber: " + phoneNumber;
   }
}