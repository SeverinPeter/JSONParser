package workingClass;

import java.util.Stack;

import jsonClasses.JsonArray;
import jsonClasses.JsonFloat;
import jsonClasses.JsonIdentifier;
import jsonClasses.JsonInt;
import jsonClasses.JsonKeyWord;
import jsonClasses.JsonObject;
import jsonClasses.JsonString;
import jsonClasses.JsonValue;

public class Parser
{
    private Lexer lexer;
    private Stack<JsonValue> jsonObjectStack;
    private JsonValue peekedJsonValue = null;

    public Parser(Lexer lexer)
    {
                   this.lexer = lexer;
                   jsonObjectStack = new Stack<>();
    }

    public JsonObject parseObject() throws Throwable
    {
                   System.out.println("Object");
                   validate(TokenType.OPEN_BRACE);

                   JsonObject jsonObject = new JsonObject();
                   jsonObjectStack.push(jsonObject);

                   if (!(isNextTokenType(TokenType.CLOSE_BRACE) || isNextTokenType(TokenType.EOS)))
                   {
                                   parsePair();

                                   while (isNextTokenType(TokenType.COMA))
                                   {
                                                   lexer.nextToken();
                                                   parsePair();
                                   }
                   }

                   buildJsonObject(jsonObject);
                   validate(TokenType.CLOSE_BRACE);

                   return jsonObject;
    }

    private void parsePair() throws Throwable
    {
                   System.out.println("Pair");
                   parseIdentifier();
                   lexer.nextToken();
                   parseValue();
    }

    private void parseIdentifier() throws Throwable
    {
                   System.out.println("Identifier");
                   JsonIdentifier jsonIdentifier = new JsonIdentifier(validate(TokenType.STRING).getValue());
                   jsonObjectStack.push(jsonIdentifier);
    }

    private void parseArray() throws Throwable
    {
                   System.out.println("Array");
                   validate(TokenType.OPEN_BRACKET);

                   JsonArray jsonArray = new JsonArray();
                   jsonObjectStack.push(jsonArray);

                   if (!(isNextTokenType(TokenType.CLOSE_BRACKET) || isNextTokenType(TokenType.EOS)))
                   {
                                   parseValue();

                                   while (isNextTokenType(TokenType.COMA))
                                   {
                                                   lexer.nextToken();
                                                   parseValue();
                                   }
                   }

                   buildJsonArray(jsonArray);
                   validate(TokenType.CLOSE_BRACKET);
    }

    private void parseValue() throws Throwable
    {
                   System.out.println("Value");
                   if (isNextTokenType(TokenType.STRING))
                   {
                                   String value = lexer.nextToken().getValue();
                                   JsonString jsonStringValue = new JsonString(value);
                                   jsonStringValue.setValue(value);
                                   jsonObjectStack.push(jsonStringValue);
                   }
                   else if (isNextTokenType(TokenType.MINUS) || isNextTokenType(TokenType.FLOAT)
                                                   || isNextTokenType(TokenType.INT))
                   {
                                   parseNumber();
                   }
                   else if (isNextTokenType(TokenType.OPEN_BRACE))
                   {
                                   parseObject();
                   }
                   else if (isNextTokenType(TokenType.OPEN_BRACKET))
                   {
                                   parseArray();
                   }
                   else if (isNextTokenType(TokenType.TRUE))
                   {
                                   jsonObjectStack.push(JsonKeyWord.TRUE);
                                   lexer.nextToken();
                   }
                   else if (isNextTokenType(TokenType.FALSE))
                   {
                                   jsonObjectStack.push(JsonKeyWord.FALSE);
                                   lexer.nextToken();
                   }
                   else if (isNextTokenType(TokenType.NULL))
                   {
                                   jsonObjectStack.push(JsonKeyWord.NULL);
                                   lexer.nextToken();
                   }
                   else
                   {
                                   throw new RuntimeException("Expected a value but was " + lexer.peekToken().getTokenType());
                   }
    }

    private void parseNumber() throws Throwable
    {
                   int modifier = 1;

                   if (isNextTokenType(TokenType.MINUS))
                   {
                                   modifier = -1;
                                   lexer.nextToken();
                   }
                   if (isNextTokenType(TokenType.FLOAT))
                   {
                                   
                                   Float value = Float.parseFloat(lexer.nextToken().getValue()) * modifier;
                                   JsonFloat jsonFloat = new JsonFloat(value);

                                   jsonObjectStack.push(jsonFloat);
                   }
                   else if (isNextTokenType(TokenType.INT))
                   {
                                   
                                   int value = Integer.parseInt(lexer.nextToken().getValue()) * modifier;
                                   JsonInt jsonInt = new JsonInt(value);
                                   jsonObjectStack.push(jsonInt);
                   }
                   else
                                   throw new RuntimeException("Expected a value but was " + lexer.peekToken().getTokenType());
    }

    private Token validate(TokenType expectedTokenType) throws Throwable
    {
                   Token actualToken = lexer.nextToken();
                   TokenType actualTokenType = (actualToken == null ? null : actualToken.getTokenType());

                   if (!expectedTokenType.equals(actualTokenType))
                   {
                                   throw new RuntimeException(
                                                                  "Syntax error: Expected " + expectedTokenType + ", but was " + actualTokenType + ".");
                   }

                   return actualToken;
    }

    private boolean isNextTokenType(TokenType tokenType) throws Throwable
    {
                   return lexer.peekToken().getTokenType().equals(tokenType);
    }

    private void buildJsonObject(JsonObject jsonObject)
    {
                   while (peekJsonValue().getClass() != JsonObject.class)
                   {
                                   JsonValue value = nextJsonValue();

                                   JsonString identifier = new JsonString(((JsonIdentifier) nextJsonValue()).getValue());
                                   jsonObject.getMembers().put(identifier, value);
                   }
    }

    private void buildJsonArray(JsonArray jsonArray)
    {
                   while (peekJsonValue().getClass() != JsonArray.class)
                   {
                                   JsonValue value = nextJsonValue();

                                   jsonArray.getElements().add(value);
                   }
    }

    private JsonValue peekJsonValue()
    {
                   if (peekedJsonValue == null)
                                   peekedJsonValue = nextJsonValue();

                   return peekedJsonValue;
    }

    private JsonValue nextJsonValue()
    {
                   JsonValue nextJsonValue = peekedJsonValue;

                   if (nextJsonValue == null)
                   {
                                   nextJsonValue = jsonObjectStack.pop();
                   }
                   else
                   {
                                   peekedJsonValue = null;
                   }

                   return nextJsonValue;
    }


}
