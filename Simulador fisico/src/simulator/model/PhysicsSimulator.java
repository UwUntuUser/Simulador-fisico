package simulator.model;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class PhysicsSimulator {

	private double realTime;
	private double currentTime;
	private ForceLaws laws;
	private List<Body> bodies;
	
	
	public PhysicsSimulator(double t, ForceLaws fl) {
		if(t < 0.0)  throw new IllegalArgumentException("El tiempo real por paso no es valido");
		if(fl == null) throw new IllegalArgumentException("Las leyes de fuerza no son validas");
		
		this.realTime = t;
		this.laws = fl;
		this.currentTime = 0.0;
		this.bodies = new ArrayList<Body>();
	}
	
	public void advance() {
		for(Body b: this.bodies) {
			b.resetForce();
		}
		this.laws.apply(this.bodies);
		for(Body b: this.bodies) {
			b.move(this.realTime);
		}
		this.currentTime += this.realTime;
	}
	
	
	public void addBody(Body b) {
		if(this.bodies.contains(b)) throw new IllegalArgumentException("El cuerpo ya existe en la lista");
		bodies.add(b);
	}
	
	public JSONObject getState() {
		JSONObject info = new JSONObject();
		JSONArray jarr = new JSONArray();
		
		for(Body b: this.bodies) {
			jarr.put(new JSONObject(b.toString()));
		}
		info.put("time", this.currentTime);
		info.put("bodies", jarr);
		return info;
	}
	
	public String toString() {
		String cad = "";
		for(Body b: this.bodies) {
			cad += b.toString();
		}
		return cad;
	}
}