package junittests;

import java.util.HashMap;
import java.util.Map;

public class TestClass {
	private static int age = 16;
	private static String name = "Alfons";
	private static String[] toys = {"Tedy", "Barbie"};
	private static Map<String, Integer> friends = new HashMap<>();
	
	static {
		friends.put("Boys", 12);
		friends.put("Girls", 10);
	}
	
	public int getage() {
		return age;
	}
	
	public String getname() {
		return name;
	}
	
	public String[] gettoys() {
		return toys;
	}
	
	public Map<String, Integer> getfriends() {
		return friends;
	}

}
