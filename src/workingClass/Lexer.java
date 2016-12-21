package workingClass;

import java.text.ParseException;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lexer
{
	private static List<TokenInfo> TOKEN_INFOS = new LinkedList<TokenInfo>();

	static
	{
		addTokenInfo("^(?[0-9]+\\.[0-9]+)", TokenType.FLOAT);
		addTokenInfo("^(?[0-9]+)", TokenType.INT);
		addTokenInfo("^\\[", TokenType.OPEN_BRACKET);
		addTokenInfo("^\\]", TokenType.CLOSE_BRACKET);
		addTokenInfo("^\\,", TokenType.COMA);
		addTokenInfo("^\\{", TokenType.OPEN_BRACE);
		addTokenInfo("^\\}", TokenType.CLOSE_BRACE);
		addTokenInfo("^\\:", TokenType.COLLON);
		addTokenInfo("^(\".*?\")", TokenType.STRING);	
	}

	private static void addTokenInfo(String regex, TokenType tokenType)
	{
		TOKEN_INFOS.add(new TokenInfo(Pattern.compile(regex), tokenType));
	}
	
	private String input;
	private Token peekedToken;

	public Lexer(String input)
	{
		this.input = input.trim();
	}

	public String getInput()
	{
		return input;
	}

	public Token peekToken() throws ParseException
	{
		if ( peekedToken == null )
			peekedToken = getToken();
		
		return peekedToken;
	}
	
	public Token nextToken() throws ParseException
	{
		Token nextToken = peekedToken;
		
		if ( nextToken == null )
			nextToken = getToken();
		else
			peekedToken = null;
		
		return nextToken;
	}
	
	private Token getToken() throws ParseException
	{
		Token nextToken = null;

		for (TokenInfo tokenInfo : TOKEN_INFOS)
		{
			Matcher matcher = tokenInfo.regex.matcher(input);

			if (matcher.find())
			{
				String token = matcher.group();
				nextToken = new Token(token, tokenInfo.tokenType);
				input = matcher.replaceFirst("").trim();
				break;
			}
		}

		if (nextToken == null && input!= null)
			nextToken = new Token(null, TokenType.ENDOFSTRING);

		return nextToken;
	}

}
