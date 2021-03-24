package simulator.factories;

import org.json.JSONObject;

import simulator.model.ForceLaws;
import simulator.model.NoForce;

public class NoForceBuilder extends Builder<ForceLaws>{

	private final String type = "ng";
	
	@Override
	public ForceLaws createInstance(JSONObject info) {
		ForceLaws ley = null;
		if(info.getString("type") == type) {
			ley = new NoForce();
		}
			
		return ley;
	}

	@Override
	public JSONObject getBuilderInfo() {
		JSONObject obj = new JSONObject();
		JSONObject data = new JSONObject();
		
		obj.put("type", "nf");
		obj.put("data", data);
		
		return obj;
	}

	
}
