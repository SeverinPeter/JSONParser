package workingClass;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class ReturnObject
{
	private Map<String, Object> variables = new HashMap<>();

	public Map<String, Object> getVariables()
	{
		return variables;
	}

	public void setVariables(Map<String, Object> variables)
	{
		this.variables = variables;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		Set<String> keys = (Set<String>) variables.keySet();
		for (String key : keys) {
			if (key != null) {
				sb.append(key.toString());
				sb.append(variables.get(key).toString());
			}
		}
		System.out.println(keys.toString());
		return sb.toString();
	}
}
