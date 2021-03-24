package simulator.model;

import java.util.List;
import java.util.Vector;

import simulator.misc.Vector2D;

public class NewtonUniversalGravitation implements ForceLaws{

	
	private double G = 6.67E-11;
	
	public NewtonUniversalGravitation(double g){
		G = g;
	}
	@Override
	public void apply(List<Body> bodies) {
		
		Vector2D fuerzaTotal = new Vector2D(); // fuerza total que aplican el resto de planetas j sobre un unico planeta i

		for(int i = 0; i < bodies.size(); i++) {
			Body planetaI = bodies.get(i);
			
			if(planetaI.getMass() != 0) {
				for(int j = 0; j < bodies.size(); j++) {
					Body planetaJ = bodies.get(j);
					
					if(i != j) {		// los cuerpos son distintos
						
						double distancia = planetaJ.getPosition().distanceTo(planetaI.getPosition());
						double Masas = planetaJ.getMass() * planetaI.getMass();
						double fuerza = G * Masas/(distancia*distancia);
						
						Vector2D direccion = planetaJ.getPosition().minus(planetaI.getPosition()).direction();
						Vector2D vectorFuerza = direccion.scale(fuerza);		//vector fuerza del cuerpo j sobre el cuerpo i
						
						fuerzaTotal.plus(vectorFuerza);
					}
				}
				planetaI.resetForce();
				planetaI.addForce(fuerzaTotal.scale(1/planetaI.getMass()));		// actualiza la aceleracion
			}
			else {
				planetaI.resetForce();		//	pone a 0 la aceleracion
				planetaI.resetVelocity();   //	pone a 0 la velocidad
			}
			
		}
		
	}

}
