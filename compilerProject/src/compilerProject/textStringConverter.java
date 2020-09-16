package compilerProject;
import java.nio.file.*;;


public class textStringConverter {
	
	public static String readFile(String fileName)throws Exception
	{
		String data = "";
		data = new String(Files.readAllBytes(Paths.get(fileName)));
		return data;
	}
	
		
	
}
