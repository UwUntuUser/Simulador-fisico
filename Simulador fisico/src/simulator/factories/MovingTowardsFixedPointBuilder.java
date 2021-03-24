package simulator.factories;

import org.json.JSONObject;

import simulator.model.ForceLaws;
import simulator.model.MovingTowardsFixedPoint;

public class MovingTowardsFixedPointBuilder extends Builder<ForceLaws>{

	
	private final String type = "mtcp";

	@Override
	public JSONObject getBuilderInfo() {
		JSONObject obj = new JSONObject();
		JSONObject data = new JSONObject();
		
		data.put("c", "vector origen");
		data.put("g", 9.81);
		
		obj.put("type", "mtcp");
		obj.put("data", data);
		
		return obj;
	}

	@Override
	public ForceLaws createInstance(JSONObject info) {
		
		ForceLaws ley = null;
		
		if(info.getString("type") == type) {
			ley = new MovingTowardsFixedPoint();
		}
		return ley;
		
	}

}
