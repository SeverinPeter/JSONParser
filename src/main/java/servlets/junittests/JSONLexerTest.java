package junittests;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import workingClass.Lexer;
import workingClass.Token;
import workingClass.TokenType;

public class JSONLexerTest
{

	@Test
	public void test() throws ParseException
	{
		String input =  "{\"menu\": {\"id\": \"file\",\"value\" : 12}}";
		Lexer lexer = new Lexer(input);
		List<Token> tokens = new ArrayList<>();
		StringBuilder sb = new StringBuilder();
		while(lexer.getInput() != null) {
			Token token = lexer.nextToken();
			if (token.tokenType == TokenType.ENDOFSTRING)
				break;
			tokens.add(token);
			sb.append(token.name + "");
		}
		System.out.println(sb.toString());
	}

}
