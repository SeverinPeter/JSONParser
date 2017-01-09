package junittests;

import static org.junit.Assert.*;

import org.junit.Test;

import jsonClasses.JsonArray;
import jsonClasses.JsonFloat;
import jsonClasses.JsonInt;
import jsonClasses.JsonKeyWord;
import jsonClasses.JsonNummber;
import jsonClasses.JsonObject;
import jsonClasses.JsonString;
import jsonClasses.JsonValue;
import workingClass.JavatoJSON;

public class JavatoJSONTest {
	
	@Test
	public void testequalsIntFalse() throws Throwable {
		JsonInt obj1 = new JsonInt(12);
		JsonInt obj2 = new JsonInt(13);
		assertFalse(obj1.equals(obj2));
	}
	
	@Test
	public void testequalsIntTrue() throws Throwable {
		JsonInt obj1 = new JsonInt(666);
		JsonInt obj2 = new JsonInt(666);
		assertTrue(obj1.equals(obj2));
	}
	
	@Test
	public void testequalsFloatFlase() throws Throwable {
		JsonFloat obj1 = new JsonFloat(22.12);
		JsonFloat obj2 = new JsonFloat(13.33);
		assertFalse(obj1.equals(obj2));
	}
	
	@Test
	public void testequalsFloatTrue() throws Throwable {
		JsonFloat obj1 = new JsonFloat(99.99);
		JsonFloat obj2 = new JsonFloat(99.99);
		assertTrue(obj1.equals(obj2));
	}
	
	@Test
	public void testequalsStringFlase() throws Throwable {
		JsonString obj1 = new JsonString("Hallo");
		JsonString obj2 = new JsonString("Bye");
		assertFalse(obj1.equals(obj2));
	}
	
	@Test
	public void testequalsStringTrue() throws Throwable {
		JsonString obj1 = new JsonString("Hy");
		JsonString obj2 = new JsonString("Hy");
		assertTrue(obj1.equals(obj2));
	}
	
	@Test
	public void testequalsArrayFlase() throws Throwable {
		JsonArray obj1 = new JsonArray();
			JsonString value1 = new JsonString("Hy ");
			JsonString value2 = new JsonString("Dude");
			obj1.addElement(value1);
			obj1.addElement(value2);
		JsonArray obj2 = new JsonArray();
			JsonString value3 = new JsonString("Hy ");
			JsonString value4 = new JsonString("Friend");
			obj1.addElement(value3);
			obj1.addElement(value4);
		assertFalse(obj1.equals(obj2));
	}
	
	@Test
	public void testequalsArrayTrue() throws Throwable {
		JsonArray obj1 = new JsonArray();
			JsonString value1 = new JsonString("Hy");
			JsonString value2 = new JsonString("Dude");
			obj1.addElement(value1);
			obj1.addElement(value2);
		JsonArray obj2 = new JsonArray();
			obj2.addElement(value1);
			obj2.addElement(value2);
		assertTrue(obj1.equals(obj2));
	}
	
	@Test
	public void testequalsObjectFlase() throws Throwable {
		JsonObject obj1 = new JsonObject();
			JsonString value1 = new JsonString("hi");
			JsonFloat value2 = new JsonFloat(13.13);
			JsonArray value3 = new JsonArray();
				JsonInt entry1 = new JsonInt(12);
				JsonInt entry2 = new JsonInt(22);
				value3.addElement(entry1);
				value3.addElement(entry2);
			JsonString key1 = new JsonString("first");
			JsonString key2 = new JsonString("second");
			JsonString key3 = new JsonString("third");
		obj1.setPair(key1, value1);
		obj1.setPair(key2, value2);
		obj1.setPair(key3, value3);
		
		JsonObject obj2 = new JsonObject();
			JsonString value4 = new JsonString("nop");
			JsonFloat value5 = new JsonFloat(666.666);
			JsonArray value6 = new JsonArray();
				JsonInt entry3 = new JsonInt(666);
				JsonInt entry4 = new JsonInt(12);
				value3.addElement(entry3);
				value3.addElement(entry4);
			JsonString key4 = new JsonString("first");
			JsonString key5 = new JsonString("second");
			JsonString key6 = new JsonString("third");
		obj2.setPair(key4, value4);
		obj2.setPair(key5, value5);
		obj2.setPair(key6, value6);
		assertFalse(obj1.equals(obj2));
	}
	
	@Test
	public void testequalsObjectTrue() throws Throwable {
		JsonObject obj1 = new JsonObject();
			JsonString value1 = new JsonString("hi");
			JsonFloat value2 = new JsonFloat(666.666);
			JsonArray value3 = new JsonArray();
				JsonInt entry1 = new JsonInt(666);
				JsonInt entry2 = new JsonInt(12);
				value3.addElement(entry1);
				value3.addElement(entry2);
			JsonString key1 = new JsonString("first");
			JsonString key2 = new JsonString("second");
			JsonString key3 = new JsonString("third");
		obj1.setPair(key1, value1);
		obj1.setPair(key2, value2);
		obj1.setPair(key3, value3);
		
		JsonObject obj2 = new JsonObject();
			JsonString value4 = new JsonString("hi");
			JsonFloat value5 = new JsonFloat(666.666);
			JsonArray value6 = new JsonArray();
				JsonInt entry3 = new JsonInt(666);
				JsonInt entry4 = new JsonInt(12);
				value6.addElement(entry3);
				value6.addElement(entry4);
			JsonString key4 = new JsonString("first");
			JsonString key5 = new JsonString("second");
			JsonString key6 = new JsonString("third");
		obj2.setPair(key4, value4);
		obj2.setPair(key5, value5);
		obj2.setPair(key6, value6);
		assertTrue(obj1.equals(obj2));
	}
	
	@Test
	public void testSimpleIntFloat() throws Throwable {
		
		JavatoJSON tester = new JavatoJSON();
		
		TestIntFloat test = new TestIntFloat( 666, 12.1234 );
		
		JsonObject expectedobj = new JsonObject();
		
		JsonInt v1 = new JsonInt(666);
		JsonFloat v2 = new JsonFloat(12.1234);
		
		JsonString s1 = new JsonString("integer");
		JsonString s2 = new JsonString("floatingpoint");
		
		expectedobj.setPair(s1, v1);
		expectedobj.setPair(s2, v2);
		
		JsonObject actualobj = (JsonObject) tester.handleObject(test);
		
		assertEquals(expectedobj, actualobj);
	}
	
	@Test
	public void testSimpleStringKeyWord() throws Throwable {
		
		JavatoJSON tester = new JavatoJSON();
		
		TestStringKeyWords test = new TestStringKeyWords();
		
		JsonObject expectedobj = new JsonObject();
		
		JsonString v1 = new JsonString("Hallo");
		
		JsonString s1 = new JsonString("string");
		JsonString s2 = new JsonString("keyword");
		
		expectedobj.setPair(s1, v1);
		expectedobj.setPair(s2, JsonKeyWord.TRUE);
		
		JsonObject actualobj = (JsonObject) tester.handleObject(test);
		
		assertEquals(expectedobj, actualobj);
	}
	
	@Test
	public void testMap() throws Throwable {
		
		JavatoJSON tester = new JavatoJSON();
		
		TestMap test = new TestMap();
		
		JsonObject expectedobj = new JsonObject();
		
		JsonString s1 = new JsonString("map");
		
		JsonObject map = new JsonObject();
		
			JsonString ms1 = new JsonString("first");
			JsonString ms2 = new JsonString("second");
			
			JsonInt mv1 = new JsonInt(12);
			JsonString mv2 = new JsonString("place");
		
		map.setPair(ms1, mv1);	
		map.setPair(ms2, mv2);
		
		expectedobj.setPair(s1, map);
		
		JsonObject actualobj = (JsonObject) tester.handleObject(test);
		
		assertEquals(expectedobj, actualobj);
	}
	
	@Test
	public void testarray() throws Throwable {
		
		JavatoJSON tester = new JavatoJSON();
		
		TestArray test = new TestArray();
		
		JsonObject expectedobj = new JsonObject();
		
		JsonArray v1 = new JsonArray();
		
		JsonString s1 = new JsonString("array");
		
			JsonInt av1 = new JsonInt(11);
			JsonInt av2 = new JsonInt(22);
			JsonInt av3 = new JsonInt(33);
			JsonInt av4 = new JsonInt(44);
			
		v1.addElement(av1);
		v1.addElement(av2);
		v1.addElement(av3);
		v1.addElement(av4);
		
		expectedobj.setPair(s1, v1);
		
		JsonObject actualobj = (JsonObject) tester.handleObject(test);
		
		assertEquals(expectedobj, actualobj);
	}
	
	@Test
	public void testUsedBasic() throws Throwable {
		JavatoJSON tester = new JavatoJSON();
		
		TestArray test = new TestArray();
		
		JsonValue obj1 = tester.handleObject(test);
		JsonValue obj2 = tester.handleObject(test);
		
		assertTrue(obj1 == obj2);
	}
	
	@Test
	public void testUsedComplex() throws Throwable {
		JavatoJSON tester = new JavatoJSON();
		
		TestUsed1 test = new TestUsed1();
		
		JsonValue obj1 = tester.handleObject(test);
		JsonValue obj2 = tester.handleObject(test);
		
		assertTrue(obj1 == obj2);
	}

}
