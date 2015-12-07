import java.util.Random;


public class Job {
	
	private int startTime;
	private int endTime;
	private String jobId;
	

	/**
	 * Creates a new Job starting in 20 minutes from current time 
	 * and ending in next 40 minutes.
	 * Job Duration will be less than 20 minutes
	 */
	public Job()
	{
		
		int i=  Math.abs(new Random().nextInt()%20);
		this.startTime = i;
		
		i+= Math.abs(new Random().nextInt()%20);
		this.endTime = i;
		
		this.jobId =null;
		
	}
	
	/**
	 * @param startTime
	 * @param endTime
	 */
	public Job(int startTime, int endTime, String jobId)
	{
		this.startTime = startTime;
		this.endTime = endTime;
		this.jobId = jobId;
	}
	
	/**
	 * @return
	 */
	public int getStartTime() {
		return startTime;
	}
	
	/**
	 * @param startTime
	 */
	protected void setStartTime(int startTime) {
		this.startTime = startTime;
	}
	
	/**
	 * @return
	 */
	public int getEndTime() {
		return endTime;
	}
	
	/**
	 * @param endTime
	 */
	protected void setEndTime(int endTime) {
		this.endTime = endTime;
	}
	
	/**
	 * @return
	 */
	public String getJobId() {
		return jobId;
	}
	
	/**
	 * @param jobId
	 */
	protected void setJobId(String jobId) {
		this.jobId = jobId;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString()
	{
		StringBuilder st = new StringBuilder();
		st.append("Details of Current Job:\n");
		st.append("JobId ="+this.jobId+"\n");
		st.append("StartTime ="+this.startTime+"\n");
		st.append("EndTime ="+this.endTime+"\n");
		return st.toString();
	}
	
}
