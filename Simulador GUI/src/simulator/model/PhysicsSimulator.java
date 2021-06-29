package simulator.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.json.JSONArray;
import org.json.JSONObject;

import simulator.misc.Vector2D;

public class PhysicsSimulator {

	private double realTime;
	private double currentTime;
	private ForceLaws laws;
	private List<Body> bodies;
	private List<SimulatorObserver> observers;
	
	
	public PhysicsSimulator(double t, ForceLaws fl) {
		if(t < 0.0)  throw new IllegalArgumentException("El tiempo real por paso no es valido");
		if(fl == null) throw new IllegalArgumentException("Las leyes de fuerza no son validas");
		
		this.realTime = t;
		this.laws = fl;
		this.currentTime = 0.0;
		this.bodies = new ArrayList<Body>();
		this.observers = new ArrayList<SimulatorObserver>();
	}
	
	public void advance(int i) {
		for(Body b: this.bodies) {
			b.resetForce();
		}
		this.laws.apply(this.bodies, i);
		for(Body b: this.bodies) {
			b.move(this.realTime);
		}
		this.currentTime += this.realTime;
		
		for(SimulatorObserver o : this.observers) {
			o.onAdvance(bodies, this.currentTime);
		}
	}
	
	
	public void addBody(Body b) {
		if(this.bodies.contains(b)) throw new IllegalArgumentException("El cuerpo ya existe en la lista");
		bodies.add(b);
		
		for(SimulatorObserver o : this.observers) {
			o.onBodyAdded(bodies, b);
		}
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
	
	/////////////////////////////////////////////////////////////////////////////////////////			PRACTICA 2
	
	public void reset() {
		this.bodies = new ArrayList<Body>();
		this.realTime = 0.0;
		
		for(SimulatorObserver o : this.observers) {
			o.onReset(bodies, realTime, currentTime, laws.toString());
		}
	}
	
	public void setDeltaTime(double dt) throws IllegalArgumentException {
		if(dt < 0)
			throw new IllegalArgumentException();
		
		this.realTime = dt;
		
		for(SimulatorObserver o : this.observers) {
			o.onDeltaTimeChanged(dt);
		}
	}
	
	public void setForceLaws(ForceLaws forceLaws) throws IllegalArgumentException{
		if(forceLaws == null)
			throw new IllegalArgumentException();
		this.laws = forceLaws;
		
		for(SimulatorObserver o : this.observers) {
			o.onForceLawsChanged(forceLaws.toString());
		}
	}
	
	public void addObserver(SimulatorObserver o) {
		if(!this.observers.contains(o)) {
			this.observers.add(o);
			o.onRegister(bodies, realTime, currentTime, laws.toString());
		}
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////			

	
	public String toString() {
		String cad = "{\"bodies\":[";
		for(Body b: this.bodies) {
			cad += b.toString() + ",";
		}
		cad = cad.substring(0, cad.length() - 1);
		cad += "],";
		cad += "\"time\": " + this.currentTime + "}"; 
		return cad;
	}
}