/* Atharva Gupta, Independence HS APCS, 1/25/21
 * TwentyQyestions Project: <20 Questions is a guessing game in which
 * the computer determines the object by asking a series of questions./>
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.Writer;
import java.io.IOException;

/**
 * A model for the game of 20 questions
 *
 * @author Rick Mercer
 * @edited by Atharva Gupta
 */
public class GameTree
{
   //private instant variables
   private Node root;
   private Node current;
   private String fileName; //stores the name of the file

   //private inner class
   private class Node {
      private String val; //String variable
      private Node left; 
      private Node right;
      
      //default constructor
      public Node() {
         left = null;
         right = null;
      }
      //parameterized constructor
      public Node(String line) {
         val = line;
      }
   }
     
  /**
	 * Constructor needed to create the game.
	 *
	 * @param fileName
	 *          this is the name of the file we need to import the game questions
	 *          and answers from.
	 */
   public GameTree(String fileName) {
      Scanner scanner = null;
      try { 
         scanner = new Scanner(new File(fileName)); 
         this.fileName = fileName;
         root = setUp(scanner);
         current = root;
      }
      catch (IOException io) { 
         System.out.println("Sorry, could not create file: " + io); 
      }
   }
   
   //recursvie method call helper for the constructor
   private Node setUp(Scanner scanner) {
      if(scanner.hasNextLine()) {
         String line = scanner.nextLine().trim();
         if(line.charAt(0) == 'A' || !line.endsWith("?")) { // ends in a question mark or a Q
            return new Node(line);
         } else {         
            Node temp = new Node(line);
            temp.left = setUp(scanner);
            temp.right = setUp(scanner);
            return temp;
         }
      }
      return null;
   } 
     
  	/*
	 * Add a new question and answer to the currentNode. If the current node has
	 * the answer chicken, theGame.add("Does it swim?", "goose"); should change
	 * that node like this:
	 */
	// -----------Feathers?-----------------Feathers?------
	// -------------/----\------------------/-------\------
	// ------- chicken  horse-----Does it swim?-----horse--
	// -----------------------------/------\---------------
	// --------------------------goose--chicken-----------
	/**
	 * @param newQ
	 *          The question to add where the old answer was.
	 * @param newA
	 *          The new Yes answer for the new question.
	 */
   public void add(String newQ, String newA) {
      String line = current.val;
      current.val = newQ;
      current.left = new Node(newA);
      current.right = new Node(line);      
   }

	/**
	 * True if getCurrent() returns an answer rather than a question.
	 *
	 * @return False if the current node is an internal node rather than an answer
	 *         at a leaf.
	 */
   public boolean foundAnswer()
   {
      String line = getCurrent();
      if(line.endsWith("?") || line.charAt(0) == 'Q') {
         return false;
      }    
      return true;
   }

	/**
	 * Return the data for the current node, which could be a question or an
	 * answer.  Current will change based on the users progress through the game.
	 *
	 * @return The current question or answer.
	 */
   public String getCurrent()
   {
      if(current != null) {
         return current.val;
      }
      return "";
   }

	/**
	 * Ask the game to update the current node by going left for Choice.yes or
	 * right for Choice.no Example code: theGame.playerSelected(Choice.Yes);
	 *
	 * @param yesOrNo
	 */
   public void playerSelected(Choice yesOrNo)
   {
      if(yesOrNo == Choice.Yes) {
         current = current.left;
      } else if(yesOrNo == Choice.No){
         current = current.right;
      }
   }

	/**
	 * Begin a game at the root of the tree. getCurrent should return the question
	 * at the root of this GameTree.
	 */
   public void reStart()
   {
      current = root;
   }

   //toString method that will print the line and the hyphens
   //according to the level of the tree
   @Override 
   public String toString() {
      return toString(root, "", "");
   }
   
   //toString method helper that takes 3 parameters: the node, 
   //the level of hyphens, and the finalMessage
   private String toString(Node node, String level, String finalMessage) {
      if(node != null) {
         finalMessage = toString(node.right, level + "-", finalMessage);//will add a hypen for the level
         finalMessage += level + node.val + "\n"; //adds to the finalMessage
         finalMessage = toString(node.left, level + "-", finalMessage);
      }
      return finalMessage;
   }

	/**
	 * Overwrite the old file for this gameTree with the current state that may
	 * have new questions added since the game started.
	 *
	 */
    
   public void saveGame() {
      String outputFileName = "output.data"; //creates a fileName output.data
      Writer fileWriter = null;
      PrintWriter diskFile = null;
      try { 
         diskFile = new PrintWriter(new File(outputFileName)); 
         fileWriter = new FileWriter(fileName, false); //overwrites file
         String line = saveGame("", root);
       
         diskFile.print(line);
         fileWriter.write(line);
         
         fileWriter.close();
         diskFile.close();
      }
      catch (IOException io) { 
         System.out.println("Could not create file: " + outputFileName); 
      } 
   }
   
   //saveGame helper that allows the game to overwrite the file
   //and allow text to be added using this method. 
   public String saveGame(String message, Node node) {
      if(node != null) {
         message += node.val + "\n";
         message = saveGame(message, node.left);
         message = saveGame(message, node.right);
      }
      return message;
   } 
}
