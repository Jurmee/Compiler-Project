package compilerProject;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Lexigram {
	static String data;
	public Lexigram(String data){
		this.data = data;
	}
	
	public String getData() {
		return data;
	}
	
	
	public enum TokenType{
		// token types to look for, 
		OPERATOR("[*|+|=|-|/|<|>|%]"),
		KEYWORD("int|float|bool|true|false|if|else|then|endif|while|whileend|do|doend|for|forend|input|output|and|or|not+"),
		SEPERATOR("['|(|)|{|}|,|.|;|sp]"),
		REAL("-?[0-9]+"),
		IDENTIFIER ("\\w+| \\w+[$]"),
		WHITESPACE("[\t\f\r\n]+");
		
		//unitialized string variable, used in line 35
		public final String pattern;
		
		//constructor for TokenType, when a string is used as an argument it is stored at line 32
		private TokenType(String pattern) {
			this.pattern = pattern;
		}
	}
	
	//Token class, containg a TokenType enumeration from line 22 and an unitialized string 
	public static class Token{
		public TokenType tType;
		public String tData;
		
		
		//constrcutor for Token class 
		public Token(TokenType type, String data) {
			this.tType = type;
			this.tData = data;
		}
		
		@Override 
		//format in which the token and its lexeme is printed to the terminal
		public String toString() {
			return String.format("(Token Type: %s || Lexeme: %s)", tType.name(), tData);
		}
	}
	
	//very important, this is a list of tokens using arraylist
	//arraylist is bascially java's version of a dynamic array comes in the java.util package
	//below i create an arraylist of type Token and i name it lexlist
	public static ArrayList<Token> lexList() {
		
		//nested dynamically allocated arraylist of type Token called "tokens"
		//within lexlList
        ArrayList<Token> tokens = new ArrayList<Token>();
        
        //lexer Patterns
        
        //StringBuffer is a java thing, from what i found it does something to parse a string into separate tokens. 
        //I suppose its similar to like iomanip in c++ where you can manipulate a string
        //different java lexical analyzers i found online have the exact same code block from line 72-75
        StringBuffer tokenPatternsBuffer = new StringBuffer();
        for (TokenType tokenType : TokenType.values())
            tokenPatternsBuffer.append(String.format("|(?<%s>%s)", tokenType.name(), tokenType.pattern));
        //Captial "P" Pattern is a built in java class object, I think it holds a string and it is used in the Matcher class object
        //tokenPatterns is used in line 83 
        Pattern tokenPatterns = Pattern.compile(new String(tokenPatternsBuffer.substring(1)));
        
        
        //java built object class that uses the compiled string from the Pattern class
        Matcher matcher = tokenPatterns.matcher(data);
        //while loop to determine what what the tokenPattern is
        while (matcher.find()) {
        	//line 86, "name" refers to all the possible tokenTypes i.e int,float,etc.
        	
        	/*if the name matches any of the token types it adds a new token with the label it is as the variable type and the name as 
        	the data variable*/
        	if (matcher.group(TokenType.OPERATOR.name()) != null) {
        		tokens.add(new Token(TokenType.OPERATOR,matcher.group(TokenType.OPERATOR.name())));
        		continue;
        	} 
        	else if (matcher.group(TokenType.KEYWORD.name()) != null) {
        		tokens.add(new Token(TokenType.KEYWORD,matcher.group(TokenType.KEYWORD.name())));
        		continue;
        	}
        	else if (matcher.group(TokenType.SEPERATOR.name()) != null) {
        		tokens.add(new Token(TokenType.SEPERATOR,matcher.group(TokenType.SEPERATOR.name())));
        		continue;
        }
        	else if (matcher.group(TokenType.REAL.name()) != null) {
        		tokens.add(new Token(TokenType.REAL,matcher.group(TokenType.REAL.name())));
        		continue;
        	}
        	else if (matcher.group(TokenType.IDENTIFIER.name()) != null) {
        		tokens.add(new Token(TokenType.IDENTIFIER,matcher.group(TokenType.IDENTIFIER.name())));
        	}
        	else if (matcher.group(TokenType.WHITESPACE.name()) != null)
                continue;
        }
        return tokens;
	}
}