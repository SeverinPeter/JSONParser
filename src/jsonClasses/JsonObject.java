package jsonClasses;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class JsonObject extends JsonValue{
	private Map<JsonString, JsonValue> pairs = new HashMap<>();
	static Map<JsonObject, Integer> used = new HashMap<>();
	
	public void setPair (JsonString s, JsonValue v) {
		pairs.put(s, v);
	}
	
	public JsonValue getPair(JsonString s) {
		return pairs.get(s);
	}
	
	public Map<JsonString, JsonValue> getMembers() {
		return pairs;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pairs == null) ? 0 : pairs.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		JsonObject other = (JsonObject) obj;
		if (pairs == null) {
			if (other.pairs != null)
				return false;
		} else if (!pairs.equals(other.pairs))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		Set<JsonString> keys = pairs.keySet();
		int i = 1;
		if (used.containsKey(this))
				sb.append(used.get(this));
		else {
			used.put(this, this.hashCode());
			sb.append("{");
			for (JsonString key : keys) {
				sb.append(key.toString());
				sb.append(" : ");
				sb.append(pairs.get(key).toString());
				if (i < keys.size())
					sb.append(", ");
				i++;
			}
			sb.append("}");
		}
		return sb.toString();
	}
}
