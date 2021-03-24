package simulator.factories;

import org.json.JSONObject;

import simulator.control.MassEqualStates;
import simulator.control.StateComparator;

public class MassEqualStatesBuilder extends Builder<StateComparator>{

	private final String type = "masseq";
	@Override
	public StateComparator createInstance(JSONObject info) {
		
		MassEqualStates comp = null;
		
		if(info.getString("type") == type) {
			comp = new MassEqualStates();
		}
		return comp;
	}

	@Override
	public JSONObject getBuilderInfo() {
		JSONObject obj = new JSONObject();
		JSONObject data = new JSONObject();
		
		obj.put("type", "masseq");
		obj.put("data", data);
		
		return obj;
	}

	

}
