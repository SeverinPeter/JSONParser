package workingClass;

public class Token {
	  public final TokenType tokenType;
	  public final String name;

	  public Token(String name, TokenType tokenType) {
	    super();
	    this.tokenType = tokenType;
	    this.name = name;
	  }

	public String getValue()
	{
		return name;
	}

	public TokenType getTokenType()
	{
		return tokenType;
	}
	}
