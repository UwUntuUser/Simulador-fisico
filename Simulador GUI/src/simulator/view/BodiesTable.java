package simulator.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;

import simulator.control.Controller;

public class BodiesTable extends JPanel{

	private JTable tabla;
	
	BodiesTable(Controller ctrl) {
		super();
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createTitledBorder(
		BorderFactory.createLineBorder(Color.black, 2), "Bodies", TitledBorder.LEFT, TitledBorder.TOP));
		
		BodiesTableModel model = new BodiesTableModel(ctrl);
		tabla = new JTable(model);
		
		JScrollPane panelTabla = new JScrollPane(tabla, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		this.setPreferredSize(new Dimension(500,200));
		this.setMinimumSize(new Dimension(100,100));
		this.setMaximumSize(new Dimension(600,400));
		
		this.setVisible(true);
		this.add(panelTabla);
	}
}
