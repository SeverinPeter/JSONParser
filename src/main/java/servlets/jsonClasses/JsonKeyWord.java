package servlets.jsonClasses;

public final class JsonKeyWord extends JsonValue {
	public static final JsonKeyWord TRUE = new JsonKeyWord("true");
	public static final JsonKeyWord FALSE = new JsonKeyWord("false");
	public static final JsonKeyWord NULL = new JsonKeyWord("null");

	private String value;

	private JsonKeyWord(String string) {
		this.value = string;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		JsonKeyWord other = (JsonKeyWord) obj;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return value;
	}
}
