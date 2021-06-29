package simulator.control;

import org.json.JSONArray;
import org.json.JSONObject;

import simulator.misc.Vector2D;

public class EpsilonEqualStates implements StateComparator{

	private double epsilon; 
	
	public EpsilonEqualStates(double eps) {
		this.epsilon = eps;
	}
	
	@Override
	public boolean equal(JSONObject s1, JSONObject s2) {
		// TODO Auto-generated method stub
		Vector2D pos1, pos2, vel1, vel2, for1, for2;
		JSONArray arrB1 = new JSONArray();
		JSONArray arrB2 = new JSONArray();

		arrB1 = s1.getJSONArray("bodies");
		arrB2 = s2.getJSONArray("bodies");
		//arrB2 = states.getJSONArray("bodies");
		//System.out.println(arrB2.get(0));
		//System.out.println(arrB2);
		for(int i = 0; i < arrB1.length(); i++) {
			JSONObject b1 = arrB1.getJSONObject(i);
			JSONObject b2 = arrB2.getJSONObject(i);
			// Obtenemos los vectores de posicion de cada cuerpo
			pos1 = new Vector2D(b1.getJSONArray("p").getDouble(0), b1.getJSONArray("p").getDouble(1));
			pos2 = new Vector2D(b2.getJSONArray("p").getDouble(0), b2.getJSONArray("p").getDouble(1));
			// Obtenemos los vectores de velocidad de cada cuerpo
			vel1 = new Vector2D(b1.getJSONArray("v").getDouble(0), b1.getJSONArray("v").getDouble(1));
			vel2 = new Vector2D(b2.getJSONArray("v").getDouble(0), b2.getJSONArray("v").getDouble(1));
			// Obtenemos los vectores de fuerza de cada cuerpo
			for1 = new Vector2D(b1.getJSONArray("f").getDouble(0), b1.getJSONArray("f").getDouble(1));
			for2 = new Vector2D(b2.getJSONArray("f").getDouble(0), b2.getJSONArray("f").getDouble(1));
			if(!b1.getString("id").equals(b2.getString("id")) 
				|| Math.abs(b1.getDouble("m") - b2.getDouble("m")) > this.epsilon
				|| pos1.distanceTo(pos2) > this.epsilon
				|| vel1.distanceTo(vel2) > this.epsilon
				|| for1.distanceTo(for2) > this.epsilon) {
				System.out.println(Math.abs(b1.getDouble("m") - b2.getDouble("m")) > this.epsilon);
				System.out.println(pos1.distanceTo(pos2) > this.epsilon);
				System.out.println(vel1.distanceTo(vel2) > this.epsilon);
				return false;
				}
			}
			return s1.getDouble("time") == s2.getDouble("time");
	}
}