package simulator.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

import org.json.JSONObject;

import simulator.control.Controller;
import simulator.model.ForceLaws;


public class ForceLawsSelector extends JFrame{

	private JTextArea topTitle;
	private JComboBox combobox;
	private String[] laws;
	private JSONObject[] tableModel_info;
	private JTable tabla;
	
	private Controller c;
	private JSONObject obj;
	
	public ForceLawsSelector(Controller c) {
		super("Force Laws Selector");
		
		laws = new String[c.getForceLawsInfo().size()];
		tableModel_info = new JSONObject[c.getForceLawsInfo().size()];
		
		for(int i = 0; i < c.getForceLawsInfo().size(); i++) {
			JSONObject obj = c.getForceLawsInfo().get(i);
			
			laws[i] = obj.getString("desc");
			tableModel_info[i] = (JSONObject) obj.get("data");
		}
		tabla = new JTable();
		this.c = c;
		initGUI();
	}
	
	private void initGUI() {
		this.setPreferredSize(new Dimension(400,200));
		this.setMinimumSize(new Dimension(60,40));
		this.setMaximumSize(new Dimension(200,200));
		
		Container container = this.getContentPane();
		Border b = BorderFactory.createLineBorder(Color.CYAN);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		
		this.topTitle = new JTextArea("Select a force law and provide values for the parameters in the Value column(default values are used for parameters with no value)");
		this.topTitle.setLineWrap(true);
		this.topTitle.setEditable(false);
		
		mainPanel.add(topTitle, BorderLayout.PAGE_START);
		
		JPanel centerPanel = new JPanel();
		centerPanel.setBorder(b);
		
		this.tabla = new JTable(new ForceLawsSelectorModel(new ArrayList<List<String>>()));		//	Descomentar esto pone la tabla, pero no funciona del todo

		
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));
		
		bottomPanel.add(new JLabel("Force Laws"));
		this.combobox = new JComboBox<String>(laws);
		
		this.combobox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				List<List<String>> lista = new ArrayList<List<String>>();
				List<String> lista_auxiliar = new ArrayList<String>();
				String type = null;
				obj = null;
				
				
				if(combobox.getSelectedIndex() == 0) {		 // ley de newton
					obj = tableModel_info[0];
					type = "nlug";
				}
				else if(combobox.getSelectedIndex() == 1) { // fixed point
					obj = tableModel_info[1];
					type = "mtfp";
				}
				else {										// NO FORCE
					obj = tableModel_info[2];
					type = "nf";
				}
				Iterator<String> keys = obj.keys();
				while(keys.hasNext()) {
					String clave = keys.next();
					lista_auxiliar.add(clave);
					lista_auxiliar.add(" ");
					lista_auxiliar.add(obj.getString(clave));
					
					lista.add(lista_auxiliar);
					lista_auxiliar = new ArrayList<String>();

				}
				tabla = new JTable(new ForceLawsSelectorModel(lista)); 	//	Descomentar esto pone la tabla, pero no funciona del todo
				
				for(int i = 0; i < c.getForceLawsInfo().size(); i++) {
					JSONObject obj = c.getForceLawsInfo().get(i);
					if(obj.getString("type").equals(type)) {
						c.setForceLaws(obj);
						break;
					}
				}
				centerPanel.add(tabla);
				centerPanel.revalidate();

			}
		});

		
		bottomPanel.add(combobox);
		
		mainPanel.add(bottomPanel, BorderLayout.PAGE_END);

		container.add(mainPanel);
		this.setVisible(true);
		this.pack();
		
	}
	
	
}
