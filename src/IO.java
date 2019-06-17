import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;

public class IO
{
	private static BufferedReader fileIn;


	public static void openInputFile(String fileName) throws IOException
	{
		try
		{
			fileIn = new BufferedReader(new FileReader(fileName));
		}
		catch (FileNotFoundException e)
		{
			System.out.println("***Cannot open " + fileName + "***");
		}
	}

	public static String readLine()throws IOException
	{
		return fileIn.readLine();
	}

	public static void closeInputFile()throws IOException
	{
		fileIn.close();
	}
}