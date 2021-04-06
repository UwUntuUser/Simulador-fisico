package simulator.model;

import org.json.JSONObject;

import simulator.misc.Vector2D;

public class Body {
	protected String id;
	protected double m;
	protected Vector2D velocity;
	protected Vector2D force;
	protected Vector2D position;
	
	public Body(String id, double masa, Vector2D v, Vector2D p, Vector2D f){
		this.id = id;
		m = masa;
		velocity = v;
		force = f;
		position = p;
	}

	public String getId() {
		return id;
	}

	public double getMass() {
		return m;
	}

	public Vector2D getVelocity() {
		return velocity;
	}

	public Vector2D getForce() {
		return force;
	}

	public Vector2D getPosition() {
		return position;
	}
	
	void setForce(Vector2D v) {
		force = force.plus(v);
	}
	
	void setVelocity(Vector2D v) {
		velocity = new Vector2D(v);
	}
	
	void move(double t) {
		Vector2D aceleration = new Vector2D();
		if(m != 0){
			aceleration = force.scale(1/m);
		}
		Vector2D new_a = aceleration.scale((t*t)/2);
		position = position.plus(velocity.scale(t).plus(new_a));
		velocity = velocity.plus(aceleration.scale(t));
		
		// por alguna razon el unico parametro que no sale "bien" es la velocidad, el resto se ajustan a los comparadores
	}
	
	public JSONObject getState() {
		JSONObject obj = new JSONObject();
		
		obj.put("id", id);
		obj.put("m", m);
		obj.put("p", position.asJSONArray());
		obj.put("v", velocity.asJSONArray());
		obj.put("f", force.asJSONArray());
		
		
		return obj;
	}
	
	public String toString() {
		return getState().toString();
	}
}
