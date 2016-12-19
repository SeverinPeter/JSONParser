package junittests;

import java.util.HashMap;
import java.util.Map;

public class TestMap {
	private static Map<Object, Object> map = new HashMap<>();
	
	static {
		map.put("first", 12);
		map.put("second", "place");
	}
	
	public Map<Object, Object> getmap() {
		return map;
	}

}
