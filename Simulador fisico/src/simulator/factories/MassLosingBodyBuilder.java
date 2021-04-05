package simulator.factories;

import org.json.JSONObject;

import simulator.misc.Vector2D;
import simulator.model.Body;
import simulator.model.MassLossingBody;

public class MassLosingBodyBuilder extends Builder<Body>{

	public MassLosingBodyBuilder() {
		super("mlb", "mass lossing body");
		// TODO Auto-generated constructor stub
	}

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
		obj.put("desc", desc);

		
		return obj;
	}

	@Override
	public Body createInstance(JSONObject info) throws IllegalArgumentException{
		
		if(info == null)
			throw new IllegalArgumentException();
		
		Body body = null;
		
		if(info.getString("type").equals(type)) {
			JSONObject data = info.getJSONObject("data");
			String id = data.getString("id");
			Double masa = data.getDouble("m");
			Vector2D vel = jsonToArray(data.getJSONArray("v"));
			Vector2D pos = jsonToArray(data.getJSONArray("p"));
			Double lfactor = data.getDouble("factor");
			Double lfreq = data.getDouble("freq");
			
			if(id == null || masa == null || vel == null || pos == null || lfactor == null || lfreq == null)
				throw new IllegalArgumentException();
			body = new MassLossingBody(id, masa, vel, pos, new Vector2D(), lfactor, lfreq);
		}
		return body;
	}
	

	

}
