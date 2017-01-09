/**
 * 
 */
package net.iosynth.sensor;

/**
 * @author rradev
 *
 */
public class SensorCycleInt extends Sensor {
	private int values[];
	private long state;
	private static String FORMAT = "%d";
	
	/**
	 * 
	 */
	public SensorCycleInt() {
		final int val[] = {1};
		init(val);
	}
	
	/**
	 * @param values
	 */
	public SensorCycleInt(int[] values) {
		init(values);
	}
	
	/**
	 * @param values 
	 * 
	 */
	public void init(int[] values) {
		this.state = 0;
		this.values = new int[values.length];
		for(int i=0; i<values.length; i++){
			this.values[i] = values[i];
		}
	}
	
	/* (non-Javadoc)
	 * @see net.iosynth.sensor.Sensor#replicate()
	 */
	@Override
	public void replicate() {
		state = rnd.nextInt(values.length);
	}
	
	/* (non-Javadoc)
	 * @see net.iosynth.sensor.Sensor#checkParameters()
	 */
	@Override
	public void checkParameters() {
		if (values == null) {
			values = new int[1];
			values[0] = 1;
		}
	}

	// Propagate internal state and epoch
	public void step(long step){
		state = (state + step) % values.length;
		epoch = epoch + step;
	}
	
	/**
	 * @return Sensor value
	 */
	public int getValue(){
		return values[(int)state];
	}

	@Override
	public String getString() {
		return String.format(FORMAT, getValue());
	}


}
