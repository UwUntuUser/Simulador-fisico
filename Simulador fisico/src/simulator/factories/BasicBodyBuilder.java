package simulator.factories;

import org.json.JSONArray;
import org.json.JSONObject;

import simulator.misc.Vector2D;
import simulator.model.Body;

public class BasicBodyBuilder extends Builder<Body>{

	private final String type = "basic";
	@Override
	public Body createInstance(JSONObject info) {
		
		Body body = null;
		
		if(info.getString("type") == type) {
			String id = info.getString("id");
			double masa = info.getDouble("m");
			Vector2D vel = jsonToArray(info.getJSONArray("v"));
			Vector2D pos = jsonToArray(info.getJSONArray("p"));
			
			body = new Body(id, masa, vel, pos, new Vector2D());
		}
		return body;
	}

	@Override
	public JSONObject getBuilderInfo() {
		JSONObject obj = new JSONObject();
		JSONObject data = new JSONObject();
		
		data.put("id", "b1");
		data.put("p", "posicion");
		data.put("v", "velocidad");
		data.put("m", 0.0);
		
		obj.put("type", "basic");
		obj.put("data", data);
		
		return obj;
	}
	
	
}