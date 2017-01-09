package servlets.jsonClasses;

import java.util.ArrayList;
import java.util.List;

public class JsonArray extends JsonValue {
	private List<JsonValue> varray = new ArrayList<JsonValue>();

	public void addElement(JsonValue v) {
		varray.add(v);
	}

	public List<JsonValue> getElements() {
		return varray;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		JsonArray other = (JsonArray) obj;
		if (varray == null) {
			if (other.varray != null)
				return false;
		} else if (!varray.equals(other.varray))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((varray == null) ? 0 : varray.hashCode());
		return result;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		int lenght = varray.size();
		int i = 1;
		sb.append("[ ");
		for (JsonValue value : varray) {
			sb.append(value.toString());
			if (i < lenght)
				sb.append(", ");
			i++;
		}
		sb.append(" ]");
		return sb.toString();
	}
}
