package junittests;

import java.util.HashSet;
import java.util.Set;

public class TestUsed1 {
	Set<Object> set = new HashSet<>();
	TestUsed2 obj = new TestUsed2(this);
	
	public TestUsed1() {
		set.add(obj);
	}

}
