package simulator.factories;

import org.json.JSONObject;

import simulator.model.ForceLaws;
import simulator.model.NewtonUniversalGravitation;

public class NewtonUniversalGravitationBuilder extends Builder<ForceLaws> {

	private final String type = "nlug";
	private double g;
	
	

	@Override
	public ForceLaws createInstance(JSONObject info) {
		
		ForceLaws ley = null;
		if(info.getString("type") == type) {
			if(info.has("G")) {
				g = info.getDouble("G");
			}
		}
		return ley;
	}

	@Override
	public JSONObject getBuilderInfo() {
		JSONObject obj = new JSONObject();
		JSONObject data = new JSONObject();
		
		data.put("G", 6.67e10-11);
		
		obj.put("type", "nlug");
		obj.put("data", data);
		
		return obj;
	}
	
}
