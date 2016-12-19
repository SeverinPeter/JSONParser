package jsonClasses;

import java.util.Set;

public class JsonInt extends JsonNummber{
	private Integer vint;
	
	public JsonInt (Object obj) {
		setValue((Integer)obj);
	}
	
	public void setValue (Integer i) {
		vint = i;
	}
	
	public long getValue () {
		return vint.longValue();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		JsonInt other = (JsonInt) obj;
		if (vint == null) {
			if (other.vint != null)
				return false;
		} else if (!vint.equals(other.vint))
			return false;
		return true;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((vint == null) ? 0 : vint.hashCode());
		return result;
	}
	
	@Override
	public String toString() {
		return vint.toString();
	}
}
