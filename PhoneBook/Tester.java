import java.util.*;
import java.io.*;
public class Tester {
   public static void main(String[] args) throws Exception {
      PhoneBook book = new PhoneBook(10);
      book.put(new Person("Amaura"), new PhoneNumber("224-220-9457"));
      System.out.println(book.put(new Person("Amaura"), new PhoneNumber("510-335-9060")));
     Person person = new Person("Amaura");
     System.out.println(book.get(person));
   }
}