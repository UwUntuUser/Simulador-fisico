package simulator.factories;

import org.json.JSONObject;

import simulator.misc.Vector2D;
import simulator.model.ForceLaws;
import simulator.model.MovingTowardsFixedPoint;

public class MovingTowardsFixedPointBuilder extends Builder<ForceLaws>{

	private Vector2D c;
	private double g;
	
	public MovingTowardsFixedPointBuilder() {
		super("mtfp", "moving towards fixed point");
		c = new Vector2D(0,0);
		g = 9.81;
	}

	@Override
	public JSONObject getBuilderInfo() {
		JSONObject obj = new JSONObject();
		JSONObject data = new JSONObject();
		
		data.put("c", "vector origen");
		data.put("g", 9.81);
		
		obj.put("type", "mtfp");
		obj.put("data", data);
		obj.put("desc", desc);

		return obj;
	}

	@Override
	public ForceLaws createInstance(JSONObject info) throws IllegalArgumentException{
		
		if(info == null)
			throw new IllegalArgumentException();
		
		ForceLaws ley = null;
		
		if(info.getString("type").equals(type)) {
			JSONObject data = info.getJSONObject("data");
			if(data.has("c"))
				c = jsonToArray(data.getJSONArray("c"));
			if(data.has("g"))
				g = data.getDouble("g");
			ley = new MovingTowardsFixedPoint(g,c);
		}
		return ley;
		
	}

}
