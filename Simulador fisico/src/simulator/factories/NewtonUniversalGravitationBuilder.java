package simulator.factories;

import org.json.JSONObject;

import simulator.model.ForceLaws;
import simulator.model.NewtonUniversalGravitation;

public class NewtonUniversalGravitationBuilder extends Builder<ForceLaws> {

	private Double g;

	public NewtonUniversalGravitationBuilder() {
		super("nlug", "newton universal law");
		g = 6.67E-11;
	}
	
	@Override
	public ForceLaws createInstance(JSONObject info) throws IllegalArgumentException{
		
		if(info == null)
			throw new IllegalArgumentException();
		
		ForceLaws ley = null;
		if(info.getString("type").equals(type)) {
			JSONObject data = info.getJSONObject("data");
			if(data.has("G")) {
				g = data.getDouble("G");
				if(g == null)
					throw new IllegalArgumentException();
			}
			ley = new NewtonUniversalGravitation(g);
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
		obj.put("desc", desc);

		return obj;
	}
	
}
