package servlets.jsonClasses;

public class JsonIdentifier extends JsonValue {
	private String vstring;

	public JsonIdentifier(String string) {
		setValue(string);
	}

	public void setValue(String s) {
		vstring = s;
	}

	public String getValue() {
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
		JsonIdentifier other = (JsonIdentifier) obj;
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
