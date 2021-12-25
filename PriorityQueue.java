/*
 * Samin Bahizad
 * Programming Assignment 2 Huffman Coding
 * CSS342- Winter 2021
 */

/** 
 * PriorityQueue class extends ArrayHeap and adds element and removes the next element in the priority queue
 * @author Samin Bahizad
 * @version Winter 2021
 *
 */
public class PriorityQueue<T> extends ArrayHeap<T> {
	
	/**
	 * addElement method adds element to priority queue which is related to the underlying array heap
	 * @param element the element to add
	 */
	public void addElement(HuffmanTreeNode element)
	{
		add(element);
	}
	
	/**
	 * removeNext method removes the next element in priority queue which also removes the element from the arrayheap
	 * @return the deleted node
	 */
	public HuffmanTreeNode removeNext()
	{
		return removeMin();
	}
	
	
}

