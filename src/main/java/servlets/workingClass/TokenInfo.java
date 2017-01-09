package workingClass;

import java.util.regex.Pattern;

public class TokenInfo {
	public final Pattern regex;
	public final TokenType tokenType;

	public TokenInfo(Pattern regex, TokenType tokenType) {
		super();
		this.regex = regex;
		this.tokenType = tokenType;
	}
}
