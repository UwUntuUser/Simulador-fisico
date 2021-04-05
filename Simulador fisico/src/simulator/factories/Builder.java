package simulator.factories;

import org.json.JSONArray;
import org.json.JSONObject;

import simulator.misc.Vector2D;

public abstract class Builder<T> {

	protected String type;
	protected String desc;
	
	public Builder(String type, String desc) {
		this.type = type;
		this.desc = desc;
	}
	public abstract T createInstance(JSONObject info);
	public abstract JSONObject getBuilderInfo();
	
	
	protected Vector2D jsonToArray(JSONArray a) {
		double x = a.getDouble(0);
		double y = a.getDouble(1);
		Vector2D vector = new Vector2D(x, y);
		
		return vector;
	}

}
