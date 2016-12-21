package jsonClasses;

import java.util.Set;

public class JsonString extends JsonValue{
	private String vstring;
	
	public JsonString (Object obj) {
		setValue((String)obj);
	}
	
	public void setValue (String s) {
		vstring = s;
	}
	
	public String getValue () {
		return vstring;
		
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		JsonString other = (JsonString) obj;
		if (vstring == null) {
			if (other.vstring != null)
				return false;
		} else if (!vstring.equals(other.vstring))
			return false;
		return true;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((vstring == null) ? 0 : vstring.hashCode());
		return result;
	}
	
	@Override
	public String toString() {
		return "\"" + getValue() + "\"";
	}
}
