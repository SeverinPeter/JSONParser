package workingClass;

import java.awt.List;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import jsonClasses.JsonArray;
import jsonClasses.JsonFloat;
import jsonClasses.JsonInt;
import jsonClasses.JsonKeyWord;
import jsonClasses.JsonObject;
import jsonClasses.JsonString;
import jsonClasses.JsonValue;


public class JSONtoJava
{
	private Method setter;

    public Object handleJsonValue(JsonValue inputValue, Class<?> parameterClass) throws Throwable
    {
          Object object = null;

          if (inputValue == null)
          {
                 // Error
          }
          else
          {
                 object = getJavaObject(inputValue, parameterClass);
          }

          return object;
    }

    private Object getJavaObject(JsonValue jsonValue, Class<?> parameterClass) throws Throwable
    {
          Object javaObject = new Object();

          if (jsonValue instanceof JsonInt)
          {
                 javaObject = handleJsonInt((JsonInt) jsonValue, parameterClass);
          }
          else if (jsonValue instanceof JsonFloat)
          {
                 javaObject = handleJsonFloat((JsonFloat) jsonValue, parameterClass);
          }
          else if (jsonValue instanceof JsonString)
          {
                 javaObject = handleJsonString((JsonString) jsonValue);
          }
          else if (jsonValue instanceof JsonArray)
          {
        	  javaObject = handleJsonArray( (JsonArray) jsonValue, parameterClass);
          }
          else if (jsonValue.equals(JsonKeyWord.TRUE))
          {
                 javaObject = true;
          }
          else if (jsonValue.equals(JsonKeyWord.FALSE))
          {
                 javaObject = false;
          }
          else if (jsonValue.equals(JsonKeyWord.NULL))
          {
                 javaObject = null;
          }
          else if (jsonValue instanceof JsonObject)
          {
                 javaObject = handleJsonObject((JsonObject) jsonValue, parameterClass);
          }

          return javaObject;
    }

    private Object handleJsonInt(JsonInt jsonInt, Class<?> parameterClass)
    {
          if (parameterClass.equals(int.class) || parameterClass.equals(Integer.class))
          {
                 return (int) jsonInt.getValue();
          }
          else if (parameterClass.equals(long.class))
          {
                 return jsonInt.getValue();
          }
          else
          {
                 // Error
                 return null;
          }
    }

    private Object handleJsonFloat(JsonFloat jsonFloat, Class<?> parameterClass)
    {
          if (parameterClass.equals(float.class))
          {
                 return (float) jsonFloat.getValue();
          }
          else if (parameterClass.equals(double.class))
          {
                 return jsonFloat.getValue();
          }
          else
          {
                 // Error
                 return null;
          }
    }

    private String handleJsonString(JsonString jsonString)
    {
          return jsonString.getValue();
    }

    private Object handleJsonArray(JsonArray jsonArray, Class<?> parameterClass) throws Throwable
    {
          Object returnObject = null;

          Set<Class<?>> interfaces = new HashSet<>();
          interfaces.add(List.class);
          interfaces.add(Set.class);

          if (interfaces.contains(parameterClass))
          {
                 if (parameterClass.equals(List.class))
                 {
                        returnObject = createList(jsonArray);
                 }
                 else if (parameterClass.equals(Set.class))
                 {
                        returnObject = createSet(jsonArray);
                 }
                 else if (parameterClass.isArray())
                 {
                        returnObject = createArray(jsonArray, parameterClass);
                 }
          }
          else
          {
                 returnObject = createCollection(jsonArray, parameterClass);
          }

          return returnObject;
    }

    private ArrayList<Object> createList(JsonArray jsonArray) throws Throwable
    {
          ArrayList<Object> returnList = new ArrayList<>();

          for (JsonValue jsonValue : jsonArray.getElements())
          {
                 Type genericType = setter.getGenericParameterTypes()[0];
                 Class<?> genericClass = ((ParameterizedType) genericType).getActualTypeArguments()[0].getClass();

                 returnList.add(getJavaObject(jsonValue, genericClass));
          }

          return returnList;
    }

    private HashSet<Object> createSet(JsonArray jsonArray) throws Throwable
    {
          HashSet<Object> returnSet = new HashSet<>();

          for (JsonValue jsonValue : jsonArray.getElements())
          {
                 Type genericType = setter.getGenericParameterTypes()[0];
                 Class<?> genericClass = ((ParameterizedType) genericType).getActualTypeArguments()[0].getClass();

                 returnSet.add(getJavaObject(jsonValue, genericClass));
          }

          return returnSet;
    }

    @SuppressWarnings("unchecked")
    private <T> Object[] createArray(JsonArray jsonArray, Class<T> parameterClass) throws Throwable
    {
          T[] array = (T[]) Array.newInstance(parameterClass, jsonArray.getElements().size());

          for (int i = 0; i < jsonArray.getElements().size(); i++)
          {
                 array[i] = (T) jsonArray.getElements().get(i);
          }

          return array;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    private Object createCollection(JsonArray jsonArray, Class<?> parameterClass) throws Throwable
    {
          Collection<Object> returnCollection = (Collection<Object>) parameterClass.newInstance();

          for (JsonValue jsonValue : jsonArray.getElements())
          {
                 Type genericType = setter.getGenericParameterTypes()[0];
                 Class<?> genericClass = ((ParameterizedType) genericType).getActualTypeArguments()[0].getClass();

                 returnCollection.add(getJavaObject(jsonValue, genericClass));
          }

          return returnCollection;
    }

    private Object handleJsonObject(JsonObject jsonObject, Class<?> parameterClass) throws Throwable
    {
          Object javaObject = null;

          if (parameterClass.isInstance(Map.class))
          {
        	  if (parameterClass == Map.class) {
        		  Map<Object, Object> returnmap = new HashMap<>();
        		  Map<JsonString, JsonValue> entries = jsonObject.getMembers();
                  Set<JsonString> keyset = entries.keySet();
                  Type[] genericTypes = setter.getGenericParameterTypes();
                  Class<?> genericClassForKey = ((ParameterizedType) genericTypes[0]).getActualTypeArguments()[0].getClass();
                  Class<?> genericClassForValue = ((ParameterizedType) genericTypes[1]).getActualTypeArguments()[0].getClass();
                  for (JsonString key : keyset) {
                	  returnmap.put(getJavaObject(key, genericClassForKey), getJavaObject(entries.get(key), genericClassForValue));
                  }
                  javaObject = returnmap;
        	  }
        	  
        	  else {
	                 Map<JsonString, JsonValue> entries = jsonObject.getMembers();
	                 Set<JsonString> keyset = entries.keySet();
	                 Type[] genericTypes = setter.getGenericParameterTypes();
	                 Class<?> genericClassForKey = ((ParameterizedType) genericTypes[0]).getActualTypeArguments()[0].getClass();
	                 Class<?> genericClassForValue = ((ParameterizedType) genericTypes[1]).getActualTypeArguments()[0].getClass();
	                 Map returnmap = (Map) parameterClass.newInstance();
	                 for (JsonString key : keyset) {
	                        returnmap.put(getJavaObject(key, genericClassForKey), getJavaObject(entries.get(key), genericClassForValue));
	                 }
	                 javaObject = returnmap;
        	  }
          }
          else
          {
                 javaObject = parameterClass.newInstance();
                 Method[] methods = parameterClass.getMethods();
                 Map<String, Method> setterMap = new HashMap<>();

                 for (int i = 0; i < methods.length; i++)
                 {
                        if (isSetter(methods[i]))
                        {
                               setterMap.put(methods[i].getName().substring(3), methods[i]);
                        }
                 }

                 Set<Entry<JsonString, JsonValue>> objectEntrySet = jsonObject.getMembers().entrySet();
                 Iterator<Entry<JsonString, JsonValue>> objectIterator = objectEntrySet.iterator();

                 while (objectIterator.hasNext())
                 {
                        Entry<JsonString, JsonValue> entry = objectIterator.next();

                        Set<String> setterKeySet = setterMap.keySet();

                        for (String setterName : setterKeySet)
                        {
                               if (entry.getKey().getValue().toLowerCase().equals(setterName.toLowerCase()))
                               {
                                     setter = setterMap.get(setterName);
                                     setter.invoke(javaObject, getJavaObject(entry.getValue(), setter.getParameterTypes()[0]));
                               }
                        }
                 }
          }

          return javaObject;
    }

    private boolean isSetter(Method method)
    {
          return Modifier.isPublic(method.getModifiers()) && method.getReturnType().equals(void.class)
                        && method.getParameterTypes().length == 1 && method.getName().matches("^set[A-Z].*");
    }

}
