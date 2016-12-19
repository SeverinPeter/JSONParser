package junittests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import jsonClasses.JsonArray;
import jsonClasses.JsonInt;
import jsonClasses.JsonObject;
import jsonClasses.JsonString;
import workingClass.Frame;
import workingClass.JSONtoJava;

public class FrameTest
{

	@Test
	public void testJavaToJava() throws Throwable
	{
		Auto testObject = new Auto();
		Person besitzer = new Person();
		List<String> ausstattung = new ArrayList<>();
		ausstattung.add("Radio");
		ausstattung.add("Sitzheizung");
		besitzer.setName("Meier");
		besitzer.setVorname("Peter");
		testObject.setBesitzer(besitzer);
		testObject.setKennnumer(123456789);
		testObject.setTyp("SUV");
		testObject.setAusstattung(ausstattung);


		Frame frame = new Frame();
		
		JsonObject testJSONObject = frame.convertToJSON(testObject);
		System.out.println(testJSONObject);
		Object testJavaObject = frame.convertToJava(testJSONObject, Auto.class);
		
		assertEquals(( Object ) testObject, testJavaObject);
	}
	
//	@Test
//	public void testPrint() {
//		JSONObject jsonObj = new JSONObject();
//		JSONString jsonStr = new JSONString("Hi");
//		JSONArray jsonArr = new JSONArray();
//			JSONInt int1 = new JSONInt(11);
//			JSONInt int2 = new JSONInt(22);
//			jsonArr.addVarray(int1);
//			jsonArr.addVarray(int2);
//		JSONString key1 = new JSONString("String");
//		JSONString key2 = new JSONString("Array");
//	
//		jsonObj.setPair(key1, jsonStr);
//		jsonObj.setPair(key2, jsonArr);
//		
//		System.out.println(Frame.toJSONString(jsonObj));
//		assertEquals("{\"Array\" : [ 11, 22 ], \"String\" : \"Hi\"}", Frame.toJSONString(jsonObj));
//		
//	}

}
