/* Atharva Gupta, Independence HS APCS, 3/09/21
 * HuffmanCoding Project: <HuffmanCompressor.java functions as a client for the 
 * HuffmanTree class./>
 */
 
//import statements
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;


public class HuffmanCompressor {
   //private instance variable
   private static int[] counts;
   
   public static void main(String[] args) throws IOException {
      compress("happy hip hop");
      //HuffmanTree tree = new HuffmanTree("happy hip hop.code");
      expand("happy hip hop.code", "happy hip hop");
   }
	
   // reads the fileName and generate a character frequency table to construct a Huffman tree
   // and write the encoding ree to file. It also generates the compressed file though the
   // naming conventions
   public static void compress(String fileName) throws IOException {
      try {
         
         //generate character frequency table
         counts = new int[256];
         FileInputStream stream = new FileInputStream(fileName + ".txt");
         int index = 0;
         while(index != -1)  {
            index = stream.read();
            if(index != -1) {
               counts[index]++;
            }
         }
      	
         HuffmanTree tree = new HuffmanTree(counts);
         tree.write(fileName);
         stream.close();
         
        //create array with the binary digit
         String[] binaryDigits = new String[257];
         Scanner scanner = new Scanner(new File(fileName + ".code"));
         while(scanner.hasNext()) {
            int i = scanner.nextInt();
            if(scanner.hasNext()) {
               String binary = scanner.next();
               binaryDigits[i] = binary;     
            }
         }
        
        //use binary digits to create a .short file using the array 
         BitOutputStream bitStream = new BitOutputStream(fileName + ".short");
         
         FileInputStream readStream = new FileInputStream(fileName + ".txt");
         int newIndex = readStream.read();
         
         while(newIndex != -1) {
            for(int i = 0; i < binaryDigits[newIndex].length(); i++) {
               if(binaryDigits[newIndex].charAt(i) == '1') {
                  bitStream.writeBit(1);
               } else if(binaryDigits[newIndex].charAt(i) == '0'){
                  bitStream.writeBit(0);
               }
            }
            newIndex = readStream.read();
         }
         
         //EOF character
         for(int i = 0; i < binaryDigits[256].length(); i++) {
            if(binaryDigits[256].charAt(i) == '1') {
               bitStream.writeBit(1);
            } else if(binaryDigits[256].charAt(i) == '0'){
               bitStream.writeBit(0);
            }
         }
         
         //closes the streams
         readStream.close();
         bitStream.close();
      
         
      } catch(FileNotFoundException e) {
         System.out.println("Could not create file: " + fileName); 
      }
   }
   
   //method rebuilds the tree from the codeFile and write the decoded output onto the fileName
   public static void expand(String codeFile, String fileName) throws FileNotFoundException {
      HuffmanTree tree = new HuffmanTree(codeFile); //creates HuffmanTree object
      tree.decode(new BitInputStream(fileName + ".short"), fileName); //calls decode
   }
	
}
