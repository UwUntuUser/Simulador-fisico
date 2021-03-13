package simulator.control;

import org.json.JSONArray;
import org.json.JSONObject;

public class MassEqualStates implements StateComparator{
	
	public MassEqualStates() {
		
	}
	
	@Override
	public boolean equal(JSONObject s1, JSONObject s2) {
		// TODO Auto-generated method stub
		JSONArray arrBodies1 = new JSONArray();
		JSONArray arrBodies2 = new JSONArray();
		
		arrBodies1 = s1.getJSONArray("bodies");
		arrBodies2 = s2.getJSONArray("bodies");
	
		if(arrBodies1.length() != arrBodies2.length()) {
			return false;
		}
		else {
			for(int i = 0; i < arrBodies1.length(); i++) {
				if((arrBodies1.getJSONObject(i).getString("id").equals(arrBodies2.getJSONObject(i).getString("id"))
						&& arrBodies1.getJSONObject(i).getDouble("m") == arrBodies2.getJSONObject(i).getDouble("m"))) {
					return true;
				}
				else return false;
			}
		}
		if(s1.getDouble("time") == s2.getDouble("time")) return true;
		return false;
	}
}