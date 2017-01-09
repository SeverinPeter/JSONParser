package servlets.jsonClasses;

public class JsonFloat extends JsonNummber {
	private Double vfloat;

	public JsonFloat(double obj) {
		setValue(obj);
	}

	public void setValue(double f) {
		vfloat = f;
	}

	public double getValue() {
		return vfloat.floatValue();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		JsonFloat other = (JsonFloat) obj;
		if (vfloat == null) {
			if (other.vfloat != null)
				return false;
		} else if (!vfloat.equals(other.vfloat))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((vfloat == null) ? 0 : vfloat.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return vfloat.toString();
	}
}
