package simulator.factories;

import org.json.JSONObject;

import simulator.misc.Vector2D;
import simulator.model.Body;
import simulator.model.MassLossingBody;

public class MassLosingBodyBuilder extends Builder<Body>{

	private final String type = "mlb";

	@Override
	public JSONObject getBuilderInfo() {
		JSONObject obj = new JSONObject();
		JSONObject data = new JSONObject();
		
		data.put("id", "b1");
		data.put("p", "posicion");
		data.put("v", "velocidad");
		data.put("m", 0.0);
		data.put("freq", 0.0);
		data.put("factor", 0.0);
		
		obj.put("type", "mlb");
		obj.put("data", data);
		
		return obj;
	}

	@Override
	public Body createInstance(JSONObject info) {
		
		Body body = null;
		
		if(info.getString("type") == type) {
			String id = info.getString("id");
			double masa = info.getDouble("m");
			Vector2D vel = jsonToArray(info.getJSONArray("v"));
			Vector2D pos = jsonToArray(info.getJSONArray("p"));
			double lfactor = info.getDouble("factor");
			double lfreq = info.getDouble("freq");
		
			body = new MassLossingBody(id, masa, vel, pos, new Vector2D(), lfactor, lfreq);
		}
		return body;
	}
	

	

}
