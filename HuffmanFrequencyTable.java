/*
 * Samin Bahizad
 * Programming Assignment 2 Huffman Coding
 * CSS342- Winter 2021
 */

/** 
 * Creating the HuffmanFrequency table in this class using Linkedlist
 * @author Samin Bahizad
 * @version Winter 2021
 *
 */
import java.util.LinkedList;
public class HuffmanFrequencyTable {

	private final LinkedList<HuffmanTreeNode> myFrequencyTable = new LinkedList<HuffmanTreeNode>();

	private final StringBuilder outPut;

	private final String myHeader = "char     frequency     code" + "\n--------------------------------\n";
	
	/**
	 * constructor which makes the list of huffman tree nodes
	 * @param theString the string to make table from
	 */
	public HuffmanFrequencyTable(String theString)
	{
		if(theString.length() < 2)
		{
			System.out.println("Number of characters needs to be more than 2 to create a table");
			System.exit(1);
		}
		
		outPut = new StringBuilder();
		outPut.append(myHeader);
		
		char[] charArray = theString.toCharArray();
		for(char c : charArray)
		{
			addCharacter(c);
		}
		
	}

	/**
	 * @return the list of tree nodes
	 */
	public LinkedList<HuffmanTreeNode> getTable()
	{
		return myFrequencyTable;
	}
	
	/**
	 * adds a character to the list of nodes and increments the frequency by 1 if the list has multiple of that character
	 * @param theCharacter
	 */
	public void addCharacter(char theCharacter)
	{
		boolean isIn = false;
		HuffmanTreeNode toIncrement = null;
		
		for(HuffmanTreeNode TreeNode : myFrequencyTable)
		{
			if(TreeNode.getCharacter() == theCharacter)
			{
				isIn = true;
				toIncrement = TreeNode;
				break;
			}
		}
		
		if(isIn)
		{
			toIncrement.setFrequency(toIncrement.getFrequency() + 1);
		}
		
		else
		{
			myFrequencyTable.add(new HuffmanTreeNode(theCharacter));
		}
		
	}

	@Override
	public String toString()
	{
		for(HuffmanTreeNode TreeNode : myFrequencyTable)
		{
			outPut.append(String.format("%-9s%-14d%s%n",
					TreeNode.getCharacter(), TreeNode.getFrequency(), TreeNode.getCode()));
		}
		return outPut.toString();
	}
}
