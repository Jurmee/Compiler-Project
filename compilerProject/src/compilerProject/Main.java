package compilerProject;
import java.nio.file.*;;

public class Main
{

	public static void main(String[] args) throws Exception
	{
	
		//This converts file into a string 
		String data = new String(Files.readAllBytes(Paths.get("C:\\Users\\Jeremy\\Desktop\\Compilers Repo\\Compiler-Project\\compilerProject\\src\\compilerProject\\compilerTest.txt")));
		
		 
		Lexigram file = new Lexigram(data);
		
		//testing purposes
		System.out.println(file.getData());

	}

	
	
}
