package servlets.workingClass;

import servlets.jsonClasses.JsonObject;

public class Frame {

	Object workingjobject = new Object();

	public JsonObject convertToJSON(Object obj) throws Throwable {
		JsonObject returnObject = null;
		if (obj instanceof java.lang.Object) {
			JavatoJSON worker = new JavatoJSON();
			workingjobject = (JsonObject) worker.handleObject(obj);
			returnObject = (JsonObject) workingjobject;
			// print((JsonObject) workingjobject);
		} else {
			System.out.println("No JavaObject");
		}

		return returnObject;
	}

	public Object convertToJava(JsonObject obj, Class<?> c) throws Throwable {
		Object returnObject = null;
		JSONtoJava worker = new JSONtoJava();
		returnObject = (Object) worker.handleJsonValue(obj, c);
		return returnObject;
	}

	public static void print(JsonObject obj) {
		System.out.println(obj.toString());
	}

	public static String toJSONString(JsonObject jobj) {
		return jobj.toString();
	}

}
