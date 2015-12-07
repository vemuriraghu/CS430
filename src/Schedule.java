import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class Schedule {


	public static List<Job> generateSchedule()
	{
		List<Job > schedule =new ArrayList<Job>();
		BufferedReader br = null;
		String sCurrentLine;

		try {

			FileReader fr = new FileReader("/Users/raghuvemuri/Documents/workspace/CS430Project/src/testing3.txt");
			br = new BufferedReader(fr);

			while ((sCurrentLine = br.readLine()) != null) 
			{

				String[] jobSeq = sCurrentLine.split(",");
				String jobId=jobSeq[0] ;

				String stime=jobSeq[1] ;
				stime = stime.trim();

				String ftime=jobSeq[2] ;
				ftime = ftime.trim();


				Job job= new Job();

				job.setJobId(jobId);
				job.setStartTime(Integer.parseInt(stime));
				job.setEndTime(Integer.parseInt(ftime));

				schedule.add(job);


			}

		}

		catch (IOException e) {
			e.printStackTrace();
		} 

		finally {

			try {
				if (br != null)br.close();
			} 
			catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return schedule;

	}
}
