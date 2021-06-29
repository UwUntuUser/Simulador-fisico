package simulator.view;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import simulator.control.Controller;


public class MainWindow extends JFrame{

	Controller _ctrl;
	ControlPanel panelControl;
	
	
	public MainWindow() {
		super("Physics Simulator");
		initGUI();
	}
	
	public MainWindow(Controller ctrl) {
		super("Physics Simulator");
		_ctrl = ctrl;
		initGUI();
	}
	
	private void initGUI() {
		Container a = this.getContentPane();
		
		JPanel mainPanel = new JPanel(new BorderLayout());
		
		JPanel panelSup = new ControlPanel(this._ctrl);
		JPanel panelCentro = new JPanel();
		JPanel panelInf = new StatusBar(this._ctrl);
		
		panelCentro.setLayout(new BoxLayout(panelCentro, BoxLayout.Y_AXIS));

		
		BodiesTable tablaBodies = new BodiesTable(_ctrl);
		panelCentro.add(tablaBodies);
		panelCentro.add(new Viewer(_ctrl));
		
		mainPanel.add(panelSup, BorderLayout.PAGE_START);
		mainPanel.add(panelCentro, BorderLayout.CENTER);
		mainPanel.add(panelInf, BorderLayout.PAGE_END);
		
		a.add(mainPanel);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // TODO CAMBIAR
		this.pack();

	}

	
}