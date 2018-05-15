
import java.io.File;
import java.io.IOException;
import java.util.*;
import static java.lang.System.*;
import java.io.PrintStream;
import java.io.FileNotFoundException;

public class Histogram {

	//PIVS
	private Map<String, Integer> inputData = new HashMap<String, Integer>();
	private int maxWordLength = 0;
	private String fileName = "input.txt";
	private List<Character> charNoWant = new ArrayList<Character>(); 
	private Scanner file;
	

	//Constructors
    public Histogram(String fileName) {
    	this.fileName = fileName;
    	try
    	{
    		file = new Scanner(new File(fileName));
    	}
    	catch(Exception e)
    	{
    		out.println("constructor failed on file scanner");
    	}
    }
    
    
    /**
	 *this will find the longest length
	 *
	 *the longest length will be needed to align 
	 *all the lines in the histogram to the same line
	 */
	public void findLongestLength()
	{			
		for(String s : inputData.keySet())
		{
			if(maxWordLength<s.length())
				maxWordLength = s.length();
		}
	}
	
	/**
	 *this is where I put all the chars 
	 *that a word may end with.
	 *later these chars will be removed
	 *from the end of the words
	 */
	public void setUpCharsNoWant()
	{
		charNoWant.add(',');
		charNoWant.add('!');
		charNoWant.add('?');
		charNoWant.add('.');
		charNoWant.add(';');
		charNoWant.add(',');
		charNoWant.add('(');
		charNoWant.add(')');
		charNoWant.add('{');
		charNoWant.add('}');
		charNoWant.add('+');
		charNoWant.add('-');		
	}
	
	/**
	 *this will set up all the data into a map
	 *
	 *a map is used because duplicates don't matter, plus speed/efficiency
	 */
	public void setUpData()
	{
		setUpCharsNoWant();
		while(file.hasNext())
		{
			String tempString = file.next();
			
			//removes all the bad ends of words
			if(charNoWant.indexOf(tempString.charAt(tempString.length()-1))>-1)
			{
				tempString = tempString.substring(0,tempString.length()-1);
			}
			
			//makes all strings lowercase for commonality
			tempString = tempString.toLowerCase();
			
			//adds to map if key exists, else adds new key
			if(inputData.containsKey(tempString))
			{
				Integer tempInt = inputData.get(tempString)+1;
				inputData.put(tempString, tempInt);
			}
			else
				inputData.put(tempString, 1);
		}
	}
	
	/**
	 *this print all data in the map
	 */
	public void printData()
	{
		for(String s : inputData.keySet())
		{				
			////////Print beginning with alignments/////////	
			out.printf("%"+(-1*maxWordLength)+"s | ", s);
			
			//////// ='s/////////
			//gets the values of said key
			int counter = inputData.get(s);
			while(counter>0)
			{
				out.print("=");
				counter--;
			}
			//prints (#) at end
			out.print(" ("+inputData.get(s)+")\n");
		}
	}
	
	/**
	 *this print all data to file
	 */
	public void printDataToFile()
	{
		try{
			PrintStream allData = new PrintStream(new File("F://output.txt"));
			System.setOut(allData);
			for(String s : inputData.keySet())
			{				
				////////Print beginning with alignments/////////	
				out.printf("%"+(-1*maxWordLength)+"s | ", s);
				
				////////Print ='s/////////
				int counter = inputData.get(s);
				while(counter>=0)
				{
					out.print("=");
					counter--;
				}
				out.print(" ("+inputData.get(s)+")\r\n");
			}
		}
		catch(FileNotFoundException e)
		{
			System.out.print("problem printing");
		}
	}
    
}