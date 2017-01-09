package junittests;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestClassJavaToJava
{
	private int intValue;
	private String stringValue;
	private String[] stringArray = {"Hallo", "Du", "Boy"};
	private List<Integer> nummern = new ArrayList<>();
	
	public int getIntValue()
	{
		return intValue;
	}
	public void setIntValue(int intValue)
	{
		this.intValue = intValue;
	}
	public String getStringValue()
	{
		return stringValue;
	}
	public void setStringValue(String stringValue)
	{
		this.stringValue = stringValue;
	}
	public String[] getStringArray()
	{
		return stringArray;
	}
	public void setStringArray(String[] stringArray)
	{
		this.stringArray = stringArray;
	}
	public List<Integer> getNummern()
	{
		return nummern;
	}
	public void setNummern(List<Integer> nummern)
	{
		this.nummern = nummern;
	}
	
	
}
