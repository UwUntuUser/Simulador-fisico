package simulator.factories;

import org.json.JSONArray;
import org.json.JSONObject;

import simulator.misc.Vector2D;

public abstract class Builder<T> {

	public abstract T createInstance(JSONObject info);
	public abstract JSONObject getBuilderInfo();
	
	
	protected Vector2D jsonToArray(JSONArray a) {
		double x = a.getDouble(0);
		double y = a.getDouble(1);
		Vector2D vector = new Vector2D(x, y);
		
		return vector;
	}

}
