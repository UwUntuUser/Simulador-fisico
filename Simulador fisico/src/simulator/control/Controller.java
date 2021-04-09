package simulator.control;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import simulator.factories.Factory;
import simulator.model.Body;
import simulator.model.PhysicsSimulator;

public class Controller {

	private PhysicsSimulator sim;
	private Factory<Body> factoria;
	
	public Controller(PhysicsSimulator sim, Factory<Body> factoria) {
		this.sim = sim;
		this.factoria = factoria;
	}
	
	public void loadBodies(InputStream in) {
		
		JSONObject jsonInupt = new JSONObject(new JSONTokener(in));
		JSONArray cuerpos = jsonInupt.getJSONArray("bodies");
		
		for(int i = 0; i < cuerpos.length(); i++ ) {
				JSONObject jsonCuerpo = cuerpos.getJSONObject(i);
				Body cuerpo = factoria.createInstance(jsonCuerpo);
				sim.addBody(cuerpo);
		}
	}
	
	public void run(int n, OutputStream out, InputStream expOut, StateComparator cmp) throws Exception {
		
		PrintStream console = System.out;
		PrintStream p = (out == null)? console : new PrintStream(out);
		JSONObject jsonInput = (expOut == null)? null : new JSONObject(new JSONTokener(expOut));

		p.println("{");
		p.println("\"states\": [");
		p.println(sim + ",");
		
		for(int i = 0; i < n; i++) {
			if(jsonInput!= null && !cmp.equal(sim.getState(), (JSONObject) jsonInput.getJSONArray("states").get(i))) {
				throw new BodiesNotEqualException(i, sim.getState(), (JSONObject) jsonInput.getJSONArray("states").get(i));

			} else {
				sim.advance(i);
				p.print(sim);
				if(i < n - 1)
					p.println(",");
			}
		}
		
		p.println("]");
		p.println("}");
		p.close();
	}
}
