package simulator.model;

import java.util.List;

import simulator.misc.Vector2D;

public class NewtonUniversalGravitation implements ForceLaws{

	
	private double G = 6.67E-11;
	
	public NewtonUniversalGravitation(double g){
		G = g;
	}
	@Override
	public void apply(List<Body> bodies, int ia) {
		
		
		for(int i = 0; i < bodies.size(); i++) {
			Body planetaI = bodies.get(i);
			Vector2D fuerzaTotal = new Vector2D(); // este new tiene que ir aqui!!
			
			if(planetaI.getMass() != 0) {
				for(int j = 0; j < bodies.size(); j++) {
					Body planetaJ = bodies.get(j);
					
					if(i != j) {		// los cuerpos son distintos
						
						Vector2D direccion = new Vector2D();
						Vector2D vectorFuerza = new Vector2D();
						double fuerza = 0.0;
						double distancia = 0.0;
						double masas = 0.0;
						
						distancia = planetaJ.getPosition().distanceTo(planetaI.getPosition());
						masas = planetaJ.getMass() * planetaI.getMass();
						if(distancia != 0)
							fuerza = G * (masas/(distancia*distancia));
						
						direccion = planetaJ.getPosition().minus(planetaI.getPosition()).direction();
						vectorFuerza = direccion.scale(fuerza);		//vector fuerza del cuerpo j sobre el cuerpo i
						fuerzaTotal = fuerzaTotal.plus(vectorFuerza);
					}
				}
				//System.out.println(fuerzaTotal.scale(1/planetaI.getMass()));
				planetaI.setForce(fuerzaTotal);		// actualiza la aceleracion
			}
			else {
				planetaI.setForce(new Vector2D());		//	pone a 0 la aceleracion
				planetaI.setVelocity(new Vector2D());   //	pone a 0 la velocidad
			}
			
		}
		
	}

}
