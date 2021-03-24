package simulator.model;
import simulator.misc.Vector2D;

public class MassLossingBody extends Body{

	private double lossFactor;
	private double lossFrequency;
	private double c;
	
	public MassLossingBody(String id, double m, Vector2D vel, Vector2D pos, Vector2D ace, double lFactor, double lFreq) {
		super(id, m, vel, pos, ace);
		this.lossFactor = lFactor;
		this.lossFrequency = lFreq;
		c=0.0;
	}

	public double getLossFactor() {
		return lossFactor;
	}

	public double getLossFrequency() {
		return lossFrequency;
	}
	
	public void setLossFactor(double lossFactor) {
		this.lossFactor = lossFactor;
	}

	public void setLossFrequency(double lossFrequency) {
		this.lossFrequency = lossFrequency;
	}
	
	public void move(double t) {
		super.move(t);	
		c += t;
		if(c >= this.lossFrequency) {
			this.m *= (1 - this.lossFactor);
			c = 0.0;
		}
	}
}