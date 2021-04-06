package simulator.control;

import org.json.JSONObject;

public class BodiesNotEqualException extends Exception{

	
	public BodiesNotEqualException(int n, JSONObject body1, JSONObject body2) throws Exception {
		
		String stateBody1 = body1.toString();
		String stateBody2 = body2.toString();
		
		StringBuilder mensaje = new StringBuilder("Paso de la ejecucion : ");
		mensaje.append(n + "\n");
		mensaje.append("SALIDA DEL SIMULADOR : " + stateBody1 + "\n");
		mensaje.append("SALIDA ESPERADA : " + stateBody2 + "\n");
		
		throw new Exception(mensaje.toString());
		
		
	}
}
