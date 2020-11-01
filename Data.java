import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Data
{
	static final String FILE_LOC = "roles.dat";
	static ArrayList<String> roles = new ArrayList<String>();
	
	public static void readData() throws IOException
	{
		BufferedReader br = new BufferedReader(new FileReader(new File(FILE_LOC)));
		String line = null;
		
		while((line = br.readLine()) != null)
			roles.add(line);
		
		br.close();
	}
	
	public static void writeData() throws IOException
	{
		PrintWriter pw = new PrintWriter(new FileWriter(new File(FILE_LOC)));
		
		for(String role : roles)
			pw.println(role);
		
		pw.close();
	}
}
