/* Atharva Gupta, Independence HS APCS, 3/09/21
 * HuffmanCoding Project: <HuffmanTree.java representsat a huffman
 * encoding tree with the following methods and its purpose./>
 */
 
 //import statements
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.PrintStream;
import java.io.Writer;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

class Node implements Comparable<Node> {
	
   public Node left; // binary 0
   public Node right; // binary 1
   public int frequency;
   public char val;
	
   public Node() {
      left = null;
      right = null;
   }
   
   //parameterized construtor
   public Node(int freq, char val, Node left, Node right) {
      this.frequency = freq;
      this.left = left;
      this.val = val;
      this.right = right;
   }
	
   //takes only the freqency
   public Node(int freq) {
      this.frequency = freq;
      this.val = (char)(freq);
      left = null;
      right = null;
   }
	
   //use for the priority queue
   @Override
   public int compareTo(Node o) {
      return this.frequency - o.frequency;
   }
	
   @Override
   public String toString() {
      if(left != null && right != null) {
         return "" + this.frequency;
      }
      return "" + this.val;
   }

}

public class HuffmanTree {
   //instance variables
   public static final int ELEMENT_SIZE = 256;
   private PriorityQueue<Node> pq;
   private static Node root;

   //constructor that takes the counts or occurences of the characters as a parameter
   public HuffmanTree(int[] count) {		
      if(count.length != ELEMENT_SIZE) { //throws exception if count isn't 256 elements
         throw new IllegalArgumentException("list size is not containing 256 elements");
      }
   	
      pq = new PriorityQueue<Node>();
      for(int i = 0; i < count.length; i++) {
         if(count[i] > 0) {
            pq.add(new Node(count[i],(char)(i), null, null));
         }
      }
      pq.add(new Node(count.length)); //EOF character
      
      while (pq.size() > 1) {
         Node left  = pq.poll();
         Node right = pq.poll();
         Node combine = new Node(left.frequency + right.frequency, '\0' ,left, right);
         pq.add(combine);
      }
      root = pq.poll();
      TreePrinter.printTree(root);
   }
   
   //codeFile constructor
   //rebuilds the tree directly from the codeFile
   public HuffmanTree(String codeFile) throws FileNotFoundException {
      Scanner scanner = new Scanner(new File(codeFile));
      Node temp = new Node();
      Node tempRoot = temp;
      while(scanner.hasNext()) {
         char value = (char)(scanner.nextInt());
         if(scanner.hasNext()) {
            String binaryValue = scanner.next();
            for(int i = 0; i < binaryValue.length(); i++) {
               if(binaryValue.charAt(i) == '0') {
                  if(tempRoot.left == null) {
                     tempRoot.left = new Node();
                  }
                  tempRoot = tempRoot.left;
               } else { //binaryValue == '1'
                  if(tempRoot.right == null) {
                     tempRoot.right = new Node();
                  }
                  tempRoot = tempRoot.right;
               }
            }
            tempRoot.val = value;
         }
         tempRoot = temp; //resets the pointer of tempRoot to temp
      }
      root = temp; //sets root to temp
      TreePrinter.printTree(root);
   }  
       
   //method will decode the stream of bits(0's and 1's) and output the characters to the file
   public void decode(BitInputStream in, String outFile) throws FileNotFoundException {
      PrintWriter out = new PrintWriter(new File(outFile + ".new"));
      Node temp = root;
      int index = 0;
      while(index != -1)  {
         index = in.readBit();
         if(index != -1) {
            if(index == 0) {
               temp = temp.left;
            } else if(index == 1) {
               temp = temp.right;
            }
         }
         if(temp.left == null && temp.right == null && (int)temp.val != 256) {
            out.write(temp.val);
            temp = root;
         } else if((int)temp.val == 256) {
            index = -1;
         }
      }
      out.flush();
      out.close();
    }
   
   //should write encoding tree to the files in a .code format
   public void write(String fileName) {
      PrintWriter writer = null;
      try { 
         writer = new PrintWriter(new File(fileName + ".code")); 
         writer.print(buildText(root, "", ""));
         
         writer.flush();
         writer.close();
      }
      catch (FileNotFoundException io) { 
         System.out.println("Could not create file: " + fileName); 
      }
   }
   
   //privater static method that returns a string for the binary values
   private static String buildText(Node node, String msg, String binary) { //add 0 and 1
      if(node != null) {
         if(node.left == null && node.right == null) {
            msg += (int)node.val + "\n" + binary + "\n";
            binary = "";
            msg = buildText(node.left, msg, binary + "0");
            msg = buildText(node.right, msg, binary + "1");
         } else {
            msg = buildText(node.left, msg, binary + "0");
            msg = buildText(node.right, msg, binary + "1");
         }
      }
      return msg;
   }
 	
}