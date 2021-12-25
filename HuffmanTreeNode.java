/*
 * Samin Bahizad
 * Programming Assignment 2 Huffman Coding
 * CSS342- Winter 2021
 */

/** 
 * @author Samin Bahizad
 * @version Winter 2021
 *
 */
public class HuffmanTreeNode implements Comparable<HuffmanTreeNode>{
	/**
	 * the character in the node
	 */
	private char myChar;
	/**
	 * the frequency of the character
	 */
	private int myFrequency;
	/**
	 * the code of the character
	 */
	private String myCode;
	/**
	 * the right pointer node
	 */
	private HuffmanTreeNode myRight;
	/**
	 * the left pointer node
	 */
	private HuffmanTreeNode myLeft;
	

	public HuffmanTreeNode()
	{
		myChar = '\u0000';
	}

	public HuffmanTreeNode(char Char)
	{
		myChar = Char;
		myFrequency = 1;
	}
	
	/**
	 * 
	 * @return character of the node
	 */
	public char getCharacter()
	{
		return myChar;
	}
	
	/**
	 * @return frequency of the node
	 */
	public int getFrequency()
	{
		return myFrequency;
	}
	
	/**
	 * @return the code of the node
	 */
	public String getCode()
	{
		return myCode;
	}
	
	/**
	 * @return the right pointer of this node
	 */
	
	public HuffmanTreeNode getRight()
	{
		return myRight;
	}
	
	/**
	 * @param theRight the node the pointer points to
	 */
	public void setRight(HuffmanTreeNode theRight)
	{
		myRight = theRight;
	}
	
	/**
	 * @return the left pointer of this node
	 */
	public HuffmanTreeNode getLeft()
	{
		return myLeft;
	}

	/**
	 * @param theLeft the node to the pointer points to
	 */
	
	public void setLeft(HuffmanTreeNode theLeft)
	{
		myLeft = theLeft;
	}

	public void setFrequency(int theFreq)
	{
		myFrequency = theFreq;
	}

	public void setCode(String theCode)
	{
		myCode = theCode;
	}


	/**
	 * compares the node frequency and when one is bigger it will return positive and when the same return 0
	 * otherwise it should return negative number
	 * @return an integer that helps with sorting
	 */
	@Override
	public int compareTo(HuffmanTreeNode theOther)
	{
		return myFrequency - theOther.getFrequency();
	}
	

	public String toString()
	{
		if(myChar != '\u0000')
		{
			return "(" + myChar + ", " + myFrequency + ")";
		}
		
		return "(null, " + myFrequency + ")";
	}
}
