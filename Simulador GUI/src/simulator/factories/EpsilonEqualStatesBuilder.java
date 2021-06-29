package simulator.factories;

import org.json.JSONObject;

import simulator.control.EpsilonEqualStates;
import simulator.control.StateComparator;

public class EpsilonEqualStatesBuilder extends Builder<StateComparator>{

	public EpsilonEqualStatesBuilder() {
		super("epseq", "epsilon equal comparator");
		// TODO Auto-generated constructor stub
	}	
	@Override
	public StateComparator createInstance(JSONObject info) throws IllegalArgumentException{
		
		EpsilonEqualStates comp = null;
		
		if(info.getString("type").equals(type)) {
			JSONObject data = info.getJSONObject("data");
			if(!data.has("eps"))
				comp = new EpsilonEqualStates(0.0);
			else {
				Double eps = data.getDouble("eps");
				if(eps == null)
					throw new IllegalArgumentException();
				comp = new EpsilonEqualStates(eps);
			}
		}
		return comp;
	}

	@Override
	public JSONObject getBuilderInfo() {
		
		JSONObject obj = new JSONObject();
		JSONObject data = new JSONObject();
		
		data.put("eps", 0.1);
		obj.put("type", "epseq");
		obj.put("data", data);
		obj.put("desc", desc);

		
		return obj;
	}

}
