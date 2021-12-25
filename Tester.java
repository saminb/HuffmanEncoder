/*
 * Samin Bahizad
 * Programming Assignment 2 Huffman Coding
 * CSS342- Winter 2021
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
/** 
 * 
 * 
 * @author Samin Bahizad
 * @version Winter 2021
 *
 */
/** This class includes the Encoding class, Decoding class and it runs the program by having test strings to see if our compression works. */
public class Tester {
	
	/** This Encoder class accepts a string and the huffmantree to encode the string into a bit stream*/
	public class Encoder {
	
	private String myString;

	private String encodedString;
	
	private HuffmanFrequencyTable myFrequencyTable;

		public Encoder(HuffmanTree theTree, String theString)
		{
			myFrequencyTable = theTree.getTable();
			myString = theString;
			encodedString = encode();
		}
		
		/**
		 * encodes by building a string from the character code
		 * @return the encoded string
		 */
		
		private String encode()
		{
			char[] charArray = myString.toCharArray();
			StringBuilder encoded = new StringBuilder();
			
			for(char c : charArray)
			{
				for(HuffmanTreeNode TreeNode : myFrequencyTable.getTable())
				{
					if(TreeNode.getCharacter() == c)
					{
						encoded.append(TreeNode.getCode());
					}
				}
			}
			
			return encoded.toString();
		}
		
		public String getEncoded()
		{
			return encodedString;
		}
	}
	/** This decoder class takes in a coded bitstream and a huffman tree to decode the string*/
		public class Decoder {

			/**
			 * encoded string
			 */
			private String encodedString;
			/**
			 * decoded string
			 */
			private String decodedString;
			/**
			 * the huffman tree to use codes from
			 */
			private HuffmanTree myTree;
			
			/**
			 * constructor that initializes the encoded and decoded strings and decodes the encoded string
			 * @param theTree the tree to get codes from
			 * @param the encoded string
			 */
			public Decoder(HuffmanTree theTree, String theEncoded)
			{
				encodedString = theEncoded;
				myTree = theTree;
				decodedString = decode();
			}
			
			/**
			 * decodes the encoded string by taking instructions from the bit stream 0 for left, 1 for right until a null is hit
			 * @return decoded string
			 */
			private String decode()
			{
				char[] charArray = encodedString.toCharArray();
				
				HuffmanTreeNode currentNode = myTree.getHuffmanTree();
				
				StringBuilder decoded = new StringBuilder();
				
				for(char c : charArray)
				{
					if(c == '0')
					{
						if(currentNode.getLeft() == null)
						{
							decoded.append(currentNode.getCharacter());
							currentNode = myTree.getHuffmanTree();
						}
						
						currentNode = currentNode.getLeft();
					}
					
					else if(c == '1')
					{
						if(currentNode.getRight() == null)
						{
							decoded.append(currentNode.getCharacter());
							currentNode = myTree.getHuffmanTree();
						}
						
						currentNode = currentNode.getRight();
					}
				}
				
				decoded.append(currentNode.getCharacter());
				
				return decoded.toString();
			}

			public String getDecoded()
			{
				return decodedString;
			}
		}



	/**
	 * constructs a tester object that prints to console and to an output file. 
	 * The output is going to be the the huffman frequency table, the encoded bit stream,
	 *  compression ratio, and the decoded string.
	 * @param in is the input 
	 */
		
	public Tester(String in)
	{
		HuffmanTree huffmanTree = new HuffmanTree(new HuffmanFrequencyTable(in));
		System.out.println("% java Tester " + in);
		System.out.println("================================");
		System.out.print(huffmanTree.getStringTable());
		System.out.println("================================");
		System.out.println("Encoded bit stream: ");
		Encoder e = new Encoder(huffmanTree, in);
		System.out.println(e.getEncoded());
		System.out.println();
		// creating objects for each bit stream to make compression calculation easier.
		double a= (double)(huffmanTree.getHuffmanTree().getFrequency() * 8);
		double b= (double)e.getEncoded().length();
		System.out.println("Total number of bits without Huffman coding " + 
				"(8-bits per character): " + (int)a);
		System.out.println("Total number of bits with Huffman coding: " + (int)b);
		System.out.println("Compression Ratio: (( Total number of bits with Huffman coding/ Total number of bits without Huffman coding)*100 for percentage of compression)  " +
		((b/a)*100));
		Decoder d = new Decoder(huffmanTree, e.getEncoded());
		System.out.println("Decoded String: " + d.getDecoded());

		
		PrintStream printer = null;
		try
		{
			printer = new PrintStream(new File("output.txt"));
		}
		
		catch(FileNotFoundException fnfe)
		{
			System.out.println(fnfe);
		}
		printer.println("% java Tester " + in);
		printer.println("================================");
		printer.print(huffmanTree.getStringTable());
		printer.println("================================");
		printer.println("Encoded bit stream: ");
		printer.println(e.getEncoded());
		printer.println();
		printer.println("Total number of bits without Huffman coding " + 
				"(8-bits per character): " +
				huffmanTree.getHuffmanTree().getFrequency() * 8);
		printer.println("Total number of bits with Huffman coding: " + 
		e.getEncoded().length());
		printer.println("Compression Ratio: (( Total number of bits with Huffman coding/ Total number of bits without Huffman coding)*100 for percentage of compression) " +
				((b/a)*100));
		printer.println("Decoded String: " + d.getDecoded());
	}

//I am not sure if I should do a unit testing? I am just going to test it in the main method

	public static void main(String[] args) {
// two test subjects to test the program
		Tester t = new Tester("eeyjjjj");
		Tester t1= new Tester("Eerie eyes seen near lake.");
	
	}

}

