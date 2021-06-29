package simulator.factories;

import org.json.JSONObject;

import simulator.control.MassEqualStates;
import simulator.control.StateComparator;

public class MassEqualStatesBuilder extends Builder<StateComparator>{

	public MassEqualStatesBuilder() {
		super("masseq", "mass equal comparator");
		// TODO Auto-generated constructor stub
	}

	@Override
	public StateComparator createInstance(JSONObject info) throws IllegalArgumentException{
		
		if(info == null)
			throw new IllegalArgumentException();
		
		MassEqualStates comp = null;
		
		if(info.getString("type").equals(type)) {
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
		obj.put("desc", desc);

		return obj;
	}

	

}
