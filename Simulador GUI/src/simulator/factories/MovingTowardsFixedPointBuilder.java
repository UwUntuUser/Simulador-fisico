package simulator.factories;

import org.json.JSONObject;

import simulator.misc.Vector2D;
import simulator.model.ForceLaws;
import simulator.model.MovingTowardsFixedPoint;

public class MovingTowardsFixedPointBuilder extends Builder<ForceLaws>{

	private Vector2D c;
	private double g;
	
	public MovingTowardsFixedPointBuilder() {
		super("mtfp", "Moving towards a fixed point");
		c = new Vector2D(0,0);
		g = 9.81;
	}

	@Override
	public JSONObject getBuilderInfo() {
		JSONObject obj = new JSONObject();
		JSONObject data = new JSONObject();
		
		data.put("c", "\"the point towards which bodies move\r\n"
				+ "(a json list of 2 numbers, e.g., [100.0,50.0])");
		data.put("g","the length of the acceleration vector (a number)");
		
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
				c = new Vector2D(0, 0);
			if(data.has("g"))
				g = 9.81;
			ley = new MovingTowardsFixedPoint(g,c);
		}
		return ley;
		
	}

}
