package simulator.model;

import java.util.List;

public class NoForce implements ForceLaws{

	@Override
	public void apply(List<Body> bs, int i) {
		// TODO Auto-generated method stub
		// vacio, no implementar
	}
	
	public String toString() {
		return "No Force";
	}

}