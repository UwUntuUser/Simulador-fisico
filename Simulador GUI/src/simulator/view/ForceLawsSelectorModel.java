package simulator.view;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import simulator.model.ForceLaws;

public class ForceLawsSelectorModel extends AbstractTableModel{


	private String[] colums = {"Key", "Value", "Description"};
	private List<List<String>> leyes;

	public ForceLawsSelectorModel(List<List<String>> l) {
		leyes = l;
	}
	@Override
	public int getRowCount() {
		return this.leyes.size();
	}

	@Override
	public int getColumnCount() {
		return this.colums.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		List<String> ley = leyes.get(rowIndex);
		
		return ley.get(columnIndex);
	}
	public String getColumnName(int column) {
		return colums[column];
	}
	
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		if(columnIndex == 1) return true;
		return false;
	}
}
