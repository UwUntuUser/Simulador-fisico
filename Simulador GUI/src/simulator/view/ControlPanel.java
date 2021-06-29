package simulator.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;

import org.json.JSONObject;

import simulator.control.Controller;
import simulator.model.Body;
import simulator.model.SimulatorObserver;


public class ControlPanel extends JPanel implements SimulatorObserver{

	private Controller _ctrl;
	private boolean _stopped;
	
	private JToolBar toolbar;
	private JButton loadButton;
	private JButton physicsButton;
	private JButton runButton;
	private JButton stopButton;
	private JButton exitButton;
	private JFileChooser fc;
	
	private JSpinner steps;
	private JLabel steps_text;
	
	private JTextField delta;
	private JLabel delta_text;
	
	private final int max_steps = 10000;
	private final String path = System.getProperty("user.dir") + File.separator + "resources" + File.separator +  "examples";
	
	private File file;


	public ControlPanel(Controller ctrl) {
		_ctrl = ctrl;
		_stopped = true;
		file = null;
		this.setVisible(true);
		initGUI();
		//_ctrl.addObserver(this);
	}
	
	
	private void initGUI() {
		// TODO build the tool bar by adding buttons, etc.
		this.toolbar = new JToolBar();
		this.fc = new JFileChooser();

		this.setLayout(new BorderLayout()); // Los elementos de la toolbar se colocan a lo largo del eje X
		this.fc.setCurrentDirectory(new File(path));
		this.add(this.toolbar, BorderLayout.PAGE_START);

		// Load button
		this.loadButton = new JButton();
		this.loadButton.setIcon(new ImageIcon("resources/icons/open.png"));
		this.loadButton.setToolTipText("Load a file");
		this.toolbar.add(this.loadButton);
		this.toolbar.addSeparator();
		
		// Physics button
		this.physicsButton = new JButton();
		this.physicsButton.setToolTipText("Change Physics laws");
		this.toolbar.add(this.physicsButton);
		this.physicsButton.setIcon(new ImageIcon("resources/icons/physics.png"));

		// Run button
		this.runButton = new JButton();
		this.runButton.setIcon(new ImageIcon("resources/icons/run.png"));
		this.runButton.setToolTipText("Run the simulation");
		this.toolbar.add(runButton);
		
		// Stop button
		this.stopButton = new JButton();
		this.stopButton.setIcon(new ImageIcon("resources/icons/stop.png"));		
		this.stopButton.setToolTipText("Stop the simulation");
		this.toolbar.add(this.stopButton);
		
		// JSpinner
		SpinnerNumberModel spinner_steps_model = new SpinnerNumberModel(max_steps, 0, max_steps, 25);
		this.steps_text = new JLabel("Steps : ");
		this.steps = new JSpinner(spinner_steps_model);
		this.steps.setPreferredSize(new Dimension(60,40));
		this.steps.setMinimumSize(new Dimension(60,40));
		this.steps.setMaximumSize(new Dimension(60,40));
		this.toolbar.add(steps_text);
		this.toolbar.add(steps);
		
		// JTextField
		this.delta_text = new JLabel("Delta-Time: ");
		this.delta = new JTextField("2500.0");
		this.delta.setPreferredSize(new Dimension(60,40));
		this.delta.setMinimumSize(new Dimension(60,40));
		this.delta.setMaximumSize(new Dimension(60,40));
		this.toolbar.add(delta_text);
		this.toolbar.add(delta);
		
		this.toolbar.add(Box.createGlue());
		
		// Exit button
		this.exitButton = new JButton();
		this.exitButton.setIcon(new ImageIcon("resources/icons/exit.png"));
		this.exitButton.setToolTipText("Exit the application");
		this.toolbar.add(this.exitButton);
		
		this.setVisible(true);
		
		//////////////////////////////////////////////////////////////		ACTION LISTENER DE LOS BOTONES
		this.loadButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				selectFile();
			}
		});
		
		this.physicsButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//selectLaws();
				ForceLawsSelector fls = new ForceLawsSelector(_ctrl);
			}
		});
		
		this.runButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				stopButtons();
				_ctrl.setDeltaTime(Double.parseDouble(delta.getText()));
				run_sim((int)steps.getValue());

			}
			
		});
		
		this.stopButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				_stopped = true;
			}
		});
		
		this.exitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				exitButton();
			}
		});
	}
	
	
	// other private/protected methods
	// ...
	private void run_sim(int n) {
		if ( n > 0 && !_stopped ) {
			try {
				_ctrl.run(1);
				} catch (Exception e) {
				JOptionPane.showMessageDialog(this, e.getMessage());
				startButtons();
				_stopped = true;
				return;
			}
			SwingUtilities.invokeLater( new Runnable() {
			@Override
				public void run() {
				run_sim(n-1);
				}
			});
		} else {
			startButtons();
		}
	}
	
	////////////////////////////////////////////////////////// 		FUNCIONES DE LOS OBSERVADORES

	@Override
	public void onRegister(List<Body> bodies, double time, double dt, String fLawsDesc) {
		delta.setText(String.valueOf(dt));
		
	}

	@Override
	public void onReset(List<Body> bodies, double time, double dt, String fLawsDesc) {
		delta.setText(String.valueOf(dt));
		
	}

	@Override
	public void onBodyAdded(List<Body> bodies, Body b) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onAdvance(List<Body> bodies, double time) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDeltaTimeChanged(double dt) {
		delta.setText(String.valueOf(dt));
		
	}

	@Override
	public void onForceLawsChanged(String fLawsDesc) {
		// TODO Auto-generated method stub
		
	}
	////////////////////////////////////////////////////////// 		FUNCIONES DE LOS  ACTION LISTENER
	
	public void selectFile() {
		int v = fc.showOpenDialog(null);
		if(v == JFileChooser.APPROVE_OPTION) {
			file = fc.getSelectedFile();
			InputStream in = null;
			try {
				in = new FileInputStream(file);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			_ctrl.reset();
			_ctrl.loadBodies(in);
		}
	}
	
	public void selectLaws() {
		String option = "";
		Object[] possibilities = {"Newtons law of universal gravitation (nlug)","Falling to center gravity (ftcg)","No gravity (ng)"};
		String n = (String) JOptionPane.showInputDialog(
				this, // contenedor padre
				"Elige una opcion:", // mensaje en la ventana 
				"Leyes de gravedad", // etiqueta de la ventana 
				JOptionPane.DEFAULT_OPTION, // icono seleccionado 
				null, // icono seleccionado por el usuario (Icon)
				possibilities, // opciones para seleccionar
				"Newtons law of universal gravitation (nlug)");
		if (n != null) {
			if (n.equals(possibilities[0])) option = "nlug";
			if (n.equals(possibilities[1])) option = "ftcg";
			if (n.equals(possibilities[2])) option = "ng";
			JSONObject info = null;
			for (JSONObject fe : this._ctrl.getForceLawsInfo()) {
				if (option.equals(fe.getString("type"))) {
					info = fe;
					break;
				}
			}
			this._ctrl.setForceLaws(info);
		}
	}
	
	public void stopButtons() {
		this.loadButton.setEnabled(false);
		this.physicsButton.setEnabled(false);
		this.runButton.setEnabled(false);
		this.exitButton.setEnabled(false);
		
		this._stopped = false;
	}
	public void startButtons() {
		this.loadButton.setEnabled(true);
		this.physicsButton.setEnabled(true);
		this.runButton.setEnabled(true);
		this.exitButton.setEnabled(true);
		
	}
	
	public void exitButton() {
		Object[] options = {"Yes", "No"};
		int n = JOptionPane.showOptionDialog(null,
				"Are you sure you want to exit?", "Exit",
				JOptionPane.YES_NO_OPTION,
				JOptionPane.INFORMATION_MESSAGE,
				null,
				options,
				options[1]);
		if (n==JOptionPane.YES_OPTION)
			System.exit(0);
	}
}