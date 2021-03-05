package simulator.model;

import java.util.List;

public class MovingTowardsFixedPoint implements ForceLaws{

	private final double G = 9.8;
	
	@Override
	public void apply(List<Body> bodies) {
		for(int i = 0; i<bodies.size(); i++) {
			Body planeta = bodies.get(i);
			
			planeta.resetForce();
			planeta.addForce(planeta.getPosition().direction().scale(-G));
		}
		
	}

}
