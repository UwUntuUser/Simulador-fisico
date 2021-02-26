package simulator.model;

import org.json.JSONObject;

import simulator.misc.Vector2D;

public class Body {
	protected String id;
	protected double m;
	protected Vector2D velocity;
	protected Vector2D force;
	protected Vector2D position;
	
	Body(String id, double masa, Vector2D v, Vector2D p, Vector2D f){
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
	
	void addForce(Vector2D f) {
		force.plus(f);
	}

	void resetForce() {
		force = new Vector2D();
	}
	
	void move(double t) {
		Vector2D aceleration = new Vector2D();
		double time = t;
		
		while(t > 0) {
			if(m != 0){
				aceleration = force.scale(1/m);
			}
			Vector2D new_a = aceleration.scale(0.5).scale(time*time);
			Vector2D new_p = position.plus(velocity.scale(time));
			
			position = new_p.plus(new_a);
			velocity = aceleration.scale(time).plus(velocity);
			
			t--;
		}
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
