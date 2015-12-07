
public class Machine<T> {

	private String machineId;
	private T earliestAvailable;
	
	
	/**
	 * @param machineId
	 * @param earliestAvailable
	 */
	public Machine(String machineId, T earliestAvailable)
	{
		this.machineId = machineId;
		this.earliestAvailable = earliestAvailable;
	}
	
	/**
	 * @return
	 */
	public String getMachineId() {
		return machineId;
	}
	
	/**
	 * @param machineId
	 */
	public void setMachineId(String machineId) {
		this.machineId = machineId;
	}
	
	/**
	 * @return
	 */
	public T getEarliestAvailable() {
		return earliestAvailable;
	}
	
	/**
	 * @param earliestAvailable
	 */
	public void setEarliestAvailable(T earliestAvailable) {
		this.earliestAvailable = earliestAvailable;
	}
	
	public String toString()
	{
		StringBuilder str = new StringBuilder();
		str.append(""+this.getMachineId());
		return str.toString();
	}
	
	
}
