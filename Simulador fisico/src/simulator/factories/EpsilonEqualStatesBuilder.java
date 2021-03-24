package simulator.factories;

import org.json.JSONObject;

import simulator.control.EpsilonEqualStates;
import simulator.control.StateComparator;

public class EpsilonEqualStatesBuilder extends Builder<StateComparator>{

	private final String type = "epseq";
	
	@Override
	public StateComparator createInstance(JSONObject info) throws IllegalArgumentException{

		if(info == null)
			throw new IllegalArgumentException();
		EpsilonEqualStates comp = null;
		
		if(info.getString("type") == type) {
			double eps = info.getDouble("eps");
			comp = new EpsilonEqualStates(eps);
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
		
		return obj;
	}

}
