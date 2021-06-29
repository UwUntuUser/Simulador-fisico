package simulator.factories;

import org.json.JSONObject;

import simulator.misc.Vector2D;
import simulator.model.Body;

public class BasicBodyBuilder extends Builder<Body>{

	public BasicBodyBuilder() {
		super("basic", "basic body");
	}
	@Override
	public Body createInstance(JSONObject info) throws IllegalArgumentException{
				
		Body body = null;
		
		if(info.getString("type").equals(type)) {
			JSONObject data = info.getJSONObject("data");
			String id = data.getString("id");
			Double masa = data.getDouble("m");
			Vector2D vel = jsonToArray(data.getJSONArray("v"));
			Vector2D pos = jsonToArray(data.getJSONArray("p"));
			
			if(id == null || masa == null || vel == null || pos == null)
				throw new IllegalArgumentException();
			
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
		
		obj.put("type", type);
		obj.put("data", data);
		obj.put("desc", desc);
		
		return obj;
	}
	
	
}
