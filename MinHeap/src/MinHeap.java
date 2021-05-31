public class MinHeap {

	   //private instance variables
	   private Integer[] heap;
	   private int size;
	   public static final int DEFAULT_CAPACITY = 8;
	   
	   //default constructor
	   public MinHeap() {
	      this(0);
	   }
	   
	   //parameterized constructor
	   public MinHeap(int size) {
	      this.size = size;
	      heap = new Integer[DEFAULT_CAPACITY];
	   }
	   
	   //passes Integer nums 
	   public MinHeap(Integer...nums) {
	      this(0);
	      buildHeap(nums);
	      
	   }
	   
	   //helper method that will siftDown
	   private void buildHeap(Integer[] nums) {
	       for(Integer i: nums) {
	         if(getSize() == 0) {
	            heap[1] = i;
	         } else if(getSize() > 0) { //if greater than 0
	            if(getSize() + 1 >= heap.length) {
	               doubleCapacity();
	            }
	            heap[getSize() + 1] = i;
	         }
	         size++; //increases size
	      }
	      int index = getSize()/2;
	      while(index > 0) {
	         siftDown(index);
	         index--;
	      }
	      
	   }
	   
	   //returns size;
	   public int getSize() {
	      return size;
	   }
	   
	   //returns true if heap.length is 0
	   //false otherwise
	   public boolean isEmpty() {
	      return heap.length == 0;
	   }
	   
	   //returns the minimum value
	   public int peekMinimum() {
	      return heap[1];
	   }
	   
	   //returns the index of the left child
	   public int getLeftChildIndex(int index) {
	      return index * 2;
	   }
	   
	   //returns the index of the right child
	   public int getRightChildIndex(int index) {
	      return (index * 2) + 1;
	   }
	   
	   //returns the index of the parent 
	   public int getParentIndex(int index) {
	      return index/2;
	   }
	   
	   //doubles the size of the array
	   private void doubleCapacity() {
	      Integer[] newHeap = new Integer[heap.length * 2];
	      for(int i = 1; i < heap.length; i++) {
	         newHeap[i] = heap[i];
	      }
	      heap = newHeap;
	   }
	   
	   //inserts a value in the heap
	   public void insert(int value) {
	      if(getSize() == 0) {
	         heap[1] = value;
	         
	      } else if(getSize() > 0) {
	         if(getSize() + 1 >= heap.length) { //doubles the size if necessary
	            doubleCapacity();
	         }
	         heap[getSize() + 1] = value;
	      }
	      
	      size++;
	      bubbleUp(getSize()); //calls bubble up for heap order
	   }
	   
	   //makes sure that the array is in heap order
	   private void bubbleUp(int index) {
	      if(heap[index] != null && heap[getParentIndex(index)] != null && heap[index] < heap[getParentIndex(index)]) {
	         int temp = heap[getParentIndex(index)];
	         heap[getParentIndex(index)] = heap[index];
	         heap[index] = temp; 
	         bubbleUp(getParentIndex(index));
	      }    
	   }
	   
	   //pops the minimum and return it
	   public int popMinimum() {
	      int num = heap[1];
	      //maintains heap structure
	      heap[1] = heap[getSize()];
	      heap[getSize()] = null;
	      size--;
	   
	      siftDown(1); //calls sift down to maintain heap order 
	      return num;
	   }
	   
	   //siftDown is called to maintain heap order
	   public void siftDown(int index) {
	      if(heap[index] != null && heap[getLeftChildIndex(index)] != null && heap[getRightChildIndex(index)] != null) {
	         int minNum = Math.min(heap[getLeftChildIndex(index)], heap[getRightChildIndex(index)]);
	         if(heap[index] > minNum) {
	            if(minNum == heap[getLeftChildIndex(index)]) {
	               int temp = heap[getLeftChildIndex(index)];
	               heap[getLeftChildIndex(index)] = heap[index];
	               heap[index] = temp;  
	               siftDown(getLeftChildIndex(index));
	            } else {
	               int temp = heap[getRightChildIndex(index)];
	               heap[getRightChildIndex(index)] = heap[index];
	               heap[index] = temp;
	               siftDown(getRightChildIndex(index)); 
	            }
	         }
	      }
	   } 
	   
	   @Override
	   public String toString()
	   {
	      String output = "";
	   
	      for (int i = 1; i <= getSize(); i++) 
	         output += heap[i] + ", ";
	   
	      return output.substring(0, output.lastIndexOf(",")); //lazily truncate last comma
	   }

		/** method borrowed with minor modifications from the internet somewhere, for printing */
	   public void display() {
	      int nBlanks = 32, itemsPerRow = 1, column = 0, j = 1;
	      String dots = "...............................";
	      System.out.println(dots + dots);      
	      while (j <= this.getSize())
	      {
	         if (column == 0)                 
	            for (int k = 0; k < nBlanks; k++)
	               System.out.print(' ');
	      
	         System.out.print((heap[j] == null)? "" : heap[j]);
	      	
	         if (++column == itemsPerRow) {
	            nBlanks /= 2;                 
	            itemsPerRow *= 2;             
	            column = 0;                   
	            System.out.println();         
	         }
	         else                             
	            for (int k = 0; k < nBlanks * 2 - 2; k++)
	               System.out.print(' ');
	      	
	         j++;
	      }
	      System.out.println("\n" + dots + dots); 
	   }	
}
