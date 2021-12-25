/*
 * Samin Bahizad
 * Programming Assignment 2 Huffman Coding
 * CSS342- Winter 2021
 */

/** 
 * class to build a huffman tree using a huffman frequency table by inserting nodes from the table into a priority queue
 * @author Samin Bahizad
 * @version Winter 2021
 *
 *
 */
public class HuffmanTree {
	/**
	 * string form of the table
	 */
	private String myStringTable;
	/**
	 * table to get the nodes from
	 */
	private HuffmanFrequencyTable myFrequencyTable;	
	/**
	 * root of the tree
	 */
	private HuffmanTreeNode myNode;
	/**
	 * queue to build the tree from
	 */
	 private PriorityQueue myQueue;

	
	
	/**
	 * constructor adds the elements from the table into a priority queue
	 *  which results in building the huffman tree.
	 * @param theTable
	 */
	public HuffmanTree(HuffmanFrequencyTable theTable)
	{
		myFrequencyTable = theTable;
		myQueue = new PriorityQueue();
		
		for(HuffmanTreeNode TreeNode : myFrequencyTable.getTable())
		{
			myQueue.add(TreeNode);
		}
		
		myNode = myQueue.peek();
		
		buildHuffmanTree();
		
		myStringTable = myFrequencyTable.toString();
	}
	/**
	 * builds the huffman tree by combining the nodes with smallest frequency 
	 * in the priority queue until only one is left and we assign the code to each node
	 */
	public void buildHuffmanTree()
	{
		
		while(myQueue.getSize() > 1)
		{
			combineNodes();
		}
		
		myNode = myQueue.removeNext();

		assignCodes(myNode, "");
	}
	public HuffmanTreeNode getHuffmanTree()
	{
		return myNode;
	}

	private void combineNodes()
	{
		HuffmanTreeNode connector = new HuffmanTreeNode();
		
		HuffmanTreeNode left, right;

		left = myQueue.removeNext();
		right = myQueue.removeNext();
		
		connector.setFrequency(left.getFrequency() + right.getFrequency());
		connector.setLeft(left);
		connector.setRight(right);
		
		myQueue.addElement(connector);
	}
	
	
	/**
	 * returns the frequency table
	 * @return the frequency table
	 */
	public HuffmanFrequencyTable getTable()
	{
		return myFrequencyTable;
	}
	
	/**
	 * returns the string of the table
	 * @return the string form of the table
	 */
	public String getStringTable()
	{
		return myStringTable;
	}
	
	/**
	 * prints al of the nodes from the Huffman tree for testing 
	 * @param theNode the root
	 */
	public void printNodes(HuffmanTreeNode theNode)
	{
		if(theNode == null)
		{
			return;
		}
		
		printNodes(theNode.getLeft());
		
		printNodes(theNode.getRight());
		if(theNode.getCharacter() != '\u0000')
		{
			System.out.println(theNode.getCharacter() +
					" " + theNode.getFrequency() +
					" " + theNode.getCode());
		}
		
		else
		{
			System.out.println("null " + theNode.getFrequency() +
					" " + theNode.getCode());
		}
	}
	/**
	 * using postorder traversal to generate codes and assign them to the huffman nodes
	 * @param theCode our new code
	 */
	private void assignCodes(HuffmanTreeNode theNode, String theCode)
	{
		if(theNode == null)
		{
			return;
		}
		
		theNode.setCode(theCode);
		
		assignCodes(theNode.getLeft(), theCode += "0");
		
		theCode = theCode.substring(0, theCode.length() - 1);
		
		assignCodes(theNode.getRight(), theCode += "1");
	}
	
}
