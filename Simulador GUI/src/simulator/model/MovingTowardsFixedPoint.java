package simulator.model;

import java.util.List;

import simulator.misc.Vector2D;

public class MovingTowardsFixedPoint implements ForceLaws{

	private double G = 9.8;
	private Vector2D C;
	public MovingTowardsFixedPoint(double g, Vector2D c) {
		G = g;
		C = c;

	}
	
	/*@Override
	public void apply(List<Body> bodies, int ia) {
		for(int i = 0; i < bodies.size(); i++) {
			
			Body planeta = bodies.get(i);
			Vector2D d = C.minus(planeta.getPosition()).direction();

			planeta.setForce(d.scale(planeta.getMass() / G));
		}
		
	}
			El metodo de debajo lo corrigió marina en una tutoria
	
	*/
	@Override
	public void apply(List<Body> bodies, int ia) {
		for(int i = 0; i < bodies.size(); i++) {
			
			Body planeta = bodies.get(i);
			Vector2D d = C.minus(planeta.getPosition()).direction();

			planeta.setForce(d.scale(planeta.getMass() * G));
		}
		
	}
	
	public String toString() {
		return "Moving towards -" + C +"with constant acceleration - " + G;
	}
	
	public double get_G() {
		return this.G;
	}
	
	public Vector2D get_c() {
		return this.C;
	}
	

}
