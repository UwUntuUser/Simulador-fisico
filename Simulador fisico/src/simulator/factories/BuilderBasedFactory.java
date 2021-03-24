package simulator.factories;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

public class BuilderBasedFactory<T> implements Factory<T> {

	private List<Builder<T>> listaBuilders;
	
	public BuilderBasedFactory(List<Builder<T>> builders) {
		listaBuilders = new ArrayList<Builder<T>>(builders);
	}
	
	@Override
	public T createInstance(JSONObject info) throws IllegalArgumentException{
		
		if(info == null) {
			throw new IllegalArgumentException();
		} else {
			
			T obj = null;
			int pivote = 0;
			while(pivote < listaBuilders.size() && obj != null) {
				obj = listaBuilders.get(pivote).createInstance(info);
				pivote++;
			}
			return obj;
		}
	}

	@Override
	public List<JSONObject> getInfo() {
		List<JSONObject> lista = new ArrayList<>();
		
		for(Builder<T> builder : listaBuilders) {
			lista.add(builder.getBuilderInfo());
		}
		return lista;
	}

}