/*
 * Samin Bahizad
 * Programming Assignment 2 Huffman Coding
 * CSS342- Winter 2021
 */

/** 
 * ArrayHeap class. This will be Array and minHeap. 
 * @author Samin Bahizad
 * @version Winter 2021
 *
 */
public class ArrayHeap<T> {
	/** Contains elements in the heap */
	private HuffmanTreeNode[] myArray;
	/**
	 * number of elements in the heap
	 */
	private int mySize;
	public ArrayHeap()
	{
		myArray = new HuffmanTreeNode[15];
		mySize = 0;
	}
	
	/**
	 * @return the first element in the arrayheap without removing it 
	 */
	public HuffmanTreeNode peek()
	{
		return myArray[0];
	}
	
	/**
	 * @param theNode the node to add
	 * resizing if needed
	 */
	public void add(HuffmanTreeNode theNode)
	{
		if(mySize == myArray.length)
		{
			resize();
		}
		
		myArray[mySize] = theNode;
		mySize++;

		if(mySize > 1)
		{
			heapifyAdd();
		}
	}
	
	/**
	 * returns the number of elements in the arrayheap
	 * @return the size of the array heap 
	 */
	public int getSize()
	{
		return mySize;
	}
	
	/**
	 * resizes the array to double size and copies elements over
	 */
	private void resize()
	{
		HuffmanTreeNode[] newArray = new HuffmanTreeNode[myArray.length * 2];
		
		for(int i = 0; i < myArray.length; i++)
		{
			newArray[i] = myArray[i];
		}
		
		myArray = newArray;
	}
	
	/**
	 * @return true if array is empty
	 */
	public boolean isEmpty()
	{
		if(myArray[0] == null)
		{
			return true;
		}
		
		return false;
	}
	
	/**
	 * removes the minimum element without changing the order 
	 * @return the minimum element in the array heap
	 */
	public HuffmanTreeNode removeMin()
	{
		if(isEmpty())
		{
			System.out.println("Underflow error");
			return null;
		}
		
		HuffmanTreeNode maxElement = myArray[0];
		myArray[0] = myArray[mySize - 1];
		myArray[mySize - 1] = null;
		mySize--;
		heapifyRemove();

		
		return maxElement;
	}
	
	/** maintaining order when a new element is added
	 * compares the added element with its parent and swaps if it is greater
	 */
	private void heapifyAdd()
	{
		HuffmanTreeNode temp;
	  int next = mySize - 1;
	  temp = myArray[next];

	  while(next != 0 && temp.compareTo(myArray[(next - 1) / 2]) < 0)
	  {
	  	myArray[next] = myArray[(next - 1) / 2];
	  	next = (next - 1) / 2;
	  }
	  
	  myArray[next] = temp;
	}
	
	/** maintaining order when an element is removed 
	 * swaps the last element with root and compares with children and swaps if it is greater
	 */
	private void heapifyRemove()
	{
		HuffmanTreeNode temp = myArray[0];
		int parent = 0, left = 1, right = 2;

		while(parent < mySize)
		{
			if(parent >= myArray.length ||
					right >= myArray.length ||
					left >= myArray.length)
			{
				parent = mySize;
			}
			
			else if(myArray[left] == null && myArray[right] == null)
			{
				parent = mySize;
			}
			
			else if(myArray[right] == null && temp.compareTo(myArray[left]) > 0)
			{
				HuffmanTreeNode temp2 = myArray[parent];
				myArray[parent] = myArray[left];
				myArray[left] = temp2;
				parent = left;
				left = (2 * parent) + 1;
				right = (2 * parent) + 2;
			}
			
			else if(myArray[right] != null && myArray[left].compareTo(myArray[right]) <= 0 &&
					temp.compareTo(myArray[left]) > 0)
			{
				HuffmanTreeNode temp2 = myArray[parent];
				myArray[parent] = myArray[left];
				myArray[left] = temp2;
				parent = left;
				left = (2 * parent) + 1;
				right = (2 * parent) + 2;
			}
			
			else if(myArray[right] != null && myArray[right].compareTo(myArray[left]) <= 0 &&
					temp.compareTo(myArray[right]) > 0)
			{
				HuffmanTreeNode temp2 = myArray[parent];
				myArray[parent] = myArray[right];
				myArray[right] = temp2;
				parent = right;
				left = (2 * parent) + 1;
				right = (2 * parent) + 2;
			}
			
			else
			{
				parent = mySize;
			}
		}
	}
	

	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		
		sb.append("[");
		for(int i = 0; i < mySize; i++)
		{
			sb.append(myArray[i]);
			if(i != mySize - 1)
			{
				sb.append(", ");	
			}
		}
		sb.append("]");
		
		return sb.toString();
	}

}
