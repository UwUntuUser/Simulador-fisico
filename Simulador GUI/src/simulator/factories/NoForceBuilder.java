package simulator.factories;

import org.json.JSONObject;

import simulator.model.ForceLaws;
import simulator.model.NoForce;

public class NoForceBuilder extends Builder<ForceLaws>{

	public NoForceBuilder() {
		super("nf", "No force");
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public ForceLaws createInstance(JSONObject info) throws IllegalArgumentException{
		
		if(info == null)
			throw new IllegalArgumentException();
		
		ForceLaws ley = null;
		if(info.getString("type").equals(type)) {
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
		obj.put("desc", desc);

		return obj;
	}

	
}
