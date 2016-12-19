package workingClass;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringJoiner;

import jsonClasses.JsonObject;
import jsonClasses.JsonValue;
import jsonClasses.JsonArray;
import jsonClasses.JsonFloat;
import jsonClasses.JsonInt;
import jsonClasses.JsonKeyWord;
import jsonClasses.JsonNummber;
import jsonClasses.JsonString;

public class JavatoJSON {
	private static final Set<Class <?>> BASIC_TYPES = new HashSet<Class <?>>();
	private static final Set<Class <?>> WRAPPER_TYPES = new HashSet<Class <?>>();
	private static final Set<Class <?>> EASY_TYPES = new HashSet<Class <?>>();
	private static final Set<Class <?>> Collection_TYPES = new HashSet<Class <?>>();
	private static final Set<Class <?>> NUMMBERS = new HashSet<Class <?>>();
	private static final Set<Class <?>> INTEGERS = new HashSet<Class <?>>();
	private static final Set<Class <?>> FLOATINGPOINTS = new HashSet<Class <?>>();
	private Map<Integer, JsonValue> used = new HashMap<>();
	
	static {
		BASIC_TYPES.add(byte.class);
		BASIC_TYPES.add(short.class);
		BASIC_TYPES.add(int.class);
		BASIC_TYPES.add(long.class);
		BASIC_TYPES.add(float.class);
		BASIC_TYPES.add(double.class);
		BASIC_TYPES.add(boolean.class);
		BASIC_TYPES.add(char.class);
		
		WRAPPER_TYPES.add(Byte.class);
		WRAPPER_TYPES.add(Short.class);
		WRAPPER_TYPES.add(Integer.class);
		WRAPPER_TYPES.add(Long.class);
		WRAPPER_TYPES.add(Float.class);
		WRAPPER_TYPES.add(Double.class);
		WRAPPER_TYPES.add(Boolean.class);
		WRAPPER_TYPES.add(Character.class);
		
		EASY_TYPES.addAll(BASIC_TYPES);
		EASY_TYPES.addAll(WRAPPER_TYPES);
		
		EASY_TYPES.add(String.class);
		EASY_TYPES.add(Date.class);
		
		Collection_TYPES.add(Set.class);
		Collection_TYPES.add(List.class);
		
		INTEGERS.add(byte.class);
		INTEGERS.add(short.class);
		INTEGERS.add(int.class);
		INTEGERS.add(long.class);
		INTEGERS.add(Byte.class);
		INTEGERS.add(Short.class);
		INTEGERS.add(Integer.class);
		INTEGERS.add(Long.class);
		
		FLOATINGPOINTS.add(Float.class);
		FLOATINGPOINTS.add(Double.class);
		FLOATINGPOINTS.add(float.class);
		FLOATINGPOINTS.add(double.class);
		
		NUMMBERS.addAll(INTEGERS);
		NUMMBERS.addAll(FLOATINGPOINTS);
	}
	
	public JsonValue getRegistred(Object obj) {
		return used.get(System.identityHashCode(obj));
	}
	
	@SuppressWarnings("unchecked")
	private < T extends JsonValue > T addtoRegistered( Object obj, T value ) {
		used.put(System.identityHashCode(obj), value);
		
		return value;
	}
	
	//Methode die den Objekttyp bestimmt und dieses an die entsprechende Methode weitergiebt
	@SuppressWarnings("unchecked")
	public JsonValue handleObject(Object obj) throws Throwable {
		JsonValue jsonValue = getRegistred(obj);
		if (jsonValue == null) {
			
			if (obj == null)
				jsonValue = handleNull();
			else if (EASY_TYPES.contains(obj.getClass()))
				jsonValue = handleEasyTypes(obj);
			else if (obj.getClass().isArray())
				jsonValue = handleArray(obj, obj);
			else if (obj instanceof Map)
				jsonValue = handleMaps((Map<String, Object>)obj, obj);
			else if (obj instanceof Collection)
				jsonValue = handleCollection((Collection<?>)obj);
			else
				jsonValue = handleComplexTypes(obj);
		}
		
		return jsonValue;
	}

	private JsonObject handleComplexTypes(Object obj) throws Throwable {
			Map<String, Object> map = new HashMap<>();
			List< Method > methods = Arrays.asList(obj.getClass().getMethods());
			List< Method > getters = new ArrayList<>();
			for (Method method : methods) {
				if (method.getName().startsWith("get") &&
				method.getParameterTypes().length == 0 &&
				method.getReturnType() != void.class  &&
				method.getName() != "getClass") {
					getters.add(method);
				}
			}
			for (Method getter : getters) {
				String name = getter.getName().substring(3);
				map.put(name, getter.invoke(obj));
			}
			return handleMaps(map, obj);		
	}

	private JsonObject handleMaps(Map<String, Object> map, Object originalObject) throws Throwable {
			List<String> keylist = new ArrayList<>( map.keySet() );
			Collections.sort( keylist, new Comparator<String>() {
				@Override
				public int compare(String o1, String o2) {
					return -o1.compareTo(o2);
				}
			} );
			
			JsonObject jsonObject = addtoRegistered(originalObject, new JsonObject());
			
			for (String key : keylist) {
				jsonObject.setPair((JsonString)handleObject(key), handleObject(map.get(key)));
			}
			
			return jsonObject;
	}

	private JsonArray handleCollection( Collection< ? > collection ) throws Throwable{
		return handleArray(collection.toArray(), collection);
	}

	private JsonArray handleArray(Object array, Object originalObject) throws Throwable {
		JsonArray jsonArray = addtoRegistered(originalObject, new JsonArray());
		
		for (int i = 0; i < Array.getLength(array); i++) {
			jsonArray.addElement(handleObject(Array.get(array, i)));
		}
		
		return jsonArray;
	}

	private JsonValue handleEasyTypes(Object obj) throws Throwable {
		JsonValue value = null;
		if (NUMMBERS.contains(obj.getClass()))
			value = handleNummbers(obj);
		else if (obj instanceof String || obj instanceof Character)
			value = handleString(obj);
		else if (obj instanceof Boolean)
			value = handleBoolean((boolean)obj);
		else
			value = null;

		return value;
	}

	private JsonKeyWord handleBoolean( boolean obj ) {
		JsonKeyWord booleanKeyword;
		
		if ( obj == true)
			booleanKeyword = JsonKeyWord.TRUE;
		else
			booleanKeyword = JsonKeyWord.FALSE;
			
		return booleanKeyword;
	}

	private JsonKeyWord handleNull() {
		return JsonKeyWord.NULL;
	}

	private JsonString handleString(Object obj) {
		return addtoRegistered( obj, new JsonString(obj) );
	}

	private JsonNummber handleNummbers(Object obj) throws Throwable {
		JsonNummber jsonNumber;
		
		if (INTEGERS.contains(obj.getClass())) 
			jsonNumber = addtoRegistered( obj, new JsonInt(obj) );
		else if (FLOATINGPOINTS.contains(obj.getClass()))
			jsonNumber = addtoRegistered( obj, new JsonFloat(obj) );
		else
			throw new IllegalStateException( "Unexpected type for number, type=" + obj.getClass().getName() );
		
		return jsonNumber;
	}

}
