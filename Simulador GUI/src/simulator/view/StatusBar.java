package simulator.view;

import java.awt.FlowLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;

import simulator.control.Controller;
import simulator.model.Body;
import simulator.model.SimulatorObserver;

public class StatusBar extends JPanel implements SimulatorObserver{

	// ...
	private JLabel _currTime; // for current time
	private JLabel _currLaws; // for gravity laws
	private JLabel _numOfBodies; // for number of bodies
	
	public StatusBar(Controller ctrl) {
		initGUI();
		ctrl.addObserver(this);
	}
	
	private void initGUI() {
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.setBorder(BorderFactory.createBevelBorder(1));
		this._currTime = new JLabel("Time: ");
		this._numOfBodies = new JLabel("Bodies: ");
		this._currLaws = new JLabel("Laws: ");
		
		this.add(_currTime);
		this.add(Box.createGlue());
		this.add(_numOfBodies);
		this.add(Box.createGlue());
		this.add(_currLaws);
	}
	
	@Override
	public void onRegister(List<Body> bodies, double time, double dt, String fLawsDesc) {
		this._currLaws.setText("Laws: " + fLawsDesc);

	}

	@Override
	public void onReset(List<Body> bodies, double time, double dt, String fLawsDesc) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onBodyAdded(List<Body> bodies, Body b) {
		this._currTime.setText("Time : 0.0");
		this._numOfBodies.setText("Bodies: " + bodies.size());

		
	}

	@Override
	public void onAdvance(List<Body> bodies, double time) {
		this._currTime.setText("Time : " + time);
		
	}

	@Override
	public void onDeltaTimeChanged(double dt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onForceLawsChanged(String fLawsDesc) {
		this._currLaws.setText("Laws: " + fLawsDesc);
		
	}

}
