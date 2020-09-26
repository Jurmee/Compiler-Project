package compilerProject;
import java.nio.file.*;
import java.util.ArrayList;

import compilerProject.Lexigram.Token;;

public class Main
{

	public static void main(String[] args) throws Exception
	{
	
		//This converts file into a string 
		String data = new String(Files.readAllBytes(Paths.get("src/compilerProject/compilerTest.txt")));
		
		 
		Lexigram file = new Lexigram(data);
		
		//testing purposes
		ArrayList<Token> tokens = file.lexList();
		for(Token token : tokens)
			System.out.println(token);
		
	}

	
	
}
