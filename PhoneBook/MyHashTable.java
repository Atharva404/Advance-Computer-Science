/* Atharva Gupta, Independence HS APCS, 4/7/21
 * PhoneBook Project: <PhoneBook.java uses a open hashing 
 * approach with an array of linkedlist of table entries./>
 */
public class MyHashTable<K, V>{
   
   //instance variables
   private Entry[] table;
   private int tableSize = 100;
   private int size;
   
   //private inner class
   private class Entry {
      K person;
      V number;
      Entry next;
      
      //2 parameter constructur
      public Entry(K person, V phone) {
         this.person = person;
         this.number = phone;
         /* either way works
            this(person, phone, null);
         */
      }
      
      //3 parameter constructor
      public Entry(K person, V phone, Entry next) {
         this.person = person;
         this.number = phone;
         this.next = next;
      }
      
   }
   
   //non-parameterized constructor
   public MyHashTable() {
      this.table = (Entry[])new Object[tableSize]; //used stack overflow to solve error of generic array creation
      this.size = 0;
   } 
   
   //parameterized constructor
   public MyHashTable(int size) {
      this.table = (Entry[])new Object[size];
      this.tableSize = size;
      this.size = 0;
   }
   
   //private method doubleUp is called when 
   //array is max
   private void doubleUp() {
      Entry[] newTable = (Entry[])new Object[tableSize * 2];
      for(int i = 0; i < table.length; i++) {
         newTable[i] = table[i];
      }  
      table = newTable;
   }
   
   //overriden method put that returns the phone number of the previous 
   //Entry 
   public V put(K key, V value) {
      size++;
      if(size + 1 >= table.length) {
         doubleUp();
      }
      int personIndex = key.hashCode() % tableSize;
      
      Entry element = new Entry(key, value);
      
      if(table[personIndex] == null) {
         table[personIndex] = element;
         return null;
      } else {
         Entry current = table[personIndex];
         while(current.next != null) {
            current = current.next;
         }
         current.next = element;
         return current.number;
      }
   }
   
   //overriden method get that retrieves the phoneNumber
   //from the person provided as a parameter
   public V get(K key) {
     for(int i = 0; i < table.length; i++) {
      if(table[i] != null && table[i].person.equals(key)) {
         return table[i].number;
      } else if(table[i] != null) {
         Entry curr = table[i];
         while(curr != null) {
            if(curr.person.equals(key)) {
               return curr.number;
            }
            curr = curr.next;
         }
      }
     }
     return null;
   }
   
   
   //overriden method remove that removes the prson
   //from the list
   public V remove(K person) {
      int index = 0;
      size--;
      for(int i = 0; i < table.length; i++) {
         if(table[i].person.equals(person)) {
            index = i;
         }
      }
      Entry current = table[index];
      if(current == null) {
         table[index] = null;
         return null;
      } else {
         while(current.next != null) {
            if(current.next.person.equals(person)) {
               return current.number;
            }
         }
         table[index] = null;
         return null;
     }
   }
   
   
   //returns the size
   public int size() {
      return size;
   }
}