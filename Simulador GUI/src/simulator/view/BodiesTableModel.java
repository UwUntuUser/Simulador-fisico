package simulator.view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingUtilities;
import javax.swing.table.AbstractTableModel;

import simulator.control.Controller;
import simulator.misc.Vector2D;
import simulator.model.Body;
import simulator.model.SimulatorObserver;

public class BodiesTableModel extends AbstractTableModel implements SimulatorObserver{

	
	private String[] _colNames = {"id", "Mass", "Position", "Velocity", "Force"};
	private List<Body> _bodies;
	
	public BodiesTableModel(Controller c) {
		_bodies = new ArrayList<Body>();
		c.addObserver(this);
	}
	@Override
	public int getRowCount() {
		return _bodies.size();
	}

	@Override
	public int getColumnCount() {
		return _colNames.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Body b = _bodies.get(rowIndex);
		
		Object value = null;
		
		if(columnIndex == 0) {
			value = b.getId();
		}
		else if(columnIndex == 1) {
			value = b.getMass();
		}
		else if(columnIndex == 2) {
			value = b.getPosition();
		}
		else if(columnIndex == 3) {
			value = b.getVelocity();
		}
		else if(columnIndex == 4) {
			value = b.getForce();
		}
		return value;
	}
	
	@Override
	public String getColumnName(int column) {
		return _colNames[column];

	}
	/////////////////////////////////////////////////////////////////////////////////		Observer
	@Override
	public void onRegister(List<Body> bodies, double time, double dt, String fLawsDesc) {
		_bodies = bodies;
	}

	@Override
	public void onReset(List<Body> bodies, double time, double dt, String fLawsDesc) {
		_bodies.clear();
	}

	@Override
	public void onBodyAdded(List<Body> bodies, Body b) {
		this._bodies.add(b);
		fireTableStructureChanged();	
	}

	@Override
	public void onAdvance(List<Body> bodies, double time) {
		_bodies = bodies;
		fireTableStructureChanged();

	}

	@Override
	public void onDeltaTimeChanged(double dt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onForceLawsChanged(String fLawsDesc) {
		// TODO Auto-generated method stub
		
	}

}
