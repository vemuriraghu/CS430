import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import javax.naming.ldap.HasControls;


public class Algorithm {


	public static Map<String,List<Job>> findMaxJobs(List<Job> schedule, int m)
	{
		//This holds the final mapping of Machines and Jobs
		Map<String,List<Job>> assignments = new HashMap<String, List<Job>>();

		//This holds the available machines for each job
		Map<Job,List<String>> availableMachines = new HashMap<Job,List<String>>();

		//This holds the most recently used machines(stack)
		Stack<String> recentlyUsedMachineStack = new Stack<String>();
		Stack<String> helperStack = new Stack<String>();

		//Jobs sorted by EndTime
		List<Job> sortedByEndTime = new ArrayList<Job>(schedule);
		//Jobs sorted by StartTIme
		List<Job> sortedByStartTime = new ArrayList<Job>(schedule);


		//sorting by EndTime
		Collections.sort(sortedByEndTime, new Comparator<Job>(){

			@Override
			public int compare(Job o1, Job o2) {
				// TODO Auto-generated method stub
				if(o1.getEndTime() > o2.getEndTime())
					return 1;
				else if(o1.getEndTime() < o2.getEndTime())
					return -1;
				else return 0;
			}});


		//sorting by StartTime
		Collections.sort(sortedByStartTime, new Comparator<Job>(){

			@Override
			public int compare(Job o1, Job o2) {
				// TODO Auto-generated method stub
				if(o1.getStartTime() > o2.getStartTime())
					return 1;
				else if(o1.getStartTime() < o2.getStartTime())
					return -1;
				else return 0;
			}});


		//Initialize set of available machines for each Job
		List<String> machines = new ArrayList<String>();

		for(int i=1; i<=m; i++)
		{
			machines.add(new String("M"+i));
		}

		for(Job i: schedule)
		{
			availableMachines.put(i, new ArrayList<String>(machines));
		}


		//Initialize output Map
		for(String mach : machines)
		{
			assignments.put(mach, new LinkedList<Job>());
		}
		assignments.put("JobsThatAreDiscarded", new LinkedList<Job>());


		//for each job, assign it to available/compatible machine
		for(Job oneJob : sortedByEndTime)
		{

			String machineToBeAssignedVar = null,
					machineToBeAssignedTo = null;
			List<String> machinesAvailable = availableMachines.get(oneJob);


			//assign it to the optimal machine

			//Check if any of the machines on stack are compatible
			while(true)
			{
				//Fetch a potential compatible machine to assign the job
				if(!recentlyUsedMachineStack.isEmpty())
				{
					//fetch machine form the stack and assign the current job to it
					machineToBeAssignedVar = recentlyUsedMachineStack.pop();

					if(machinesAvailable.contains(machineToBeAssignedVar))
					{
						machineToBeAssignedTo = machineToBeAssignedVar;
						break;
					}
					else
					{
						helperStack.push(machineToBeAssignedVar);
					}
				}
				else
				{
					break;
				}

			}

			//None of the machines on the stack are compatible and 
			//hence fetching the machine form it's respective list
			if(machineToBeAssignedTo == null && !machinesAvailable.isEmpty())
			{
				machineToBeAssignedTo = machinesAvailable.get(0);
			}

			//update the assignment in the list for output
			//if(machinesAvailable.contains(machineToBeAssignedTo))
			if(machineToBeAssignedTo != null)
			{
				List<Job> jobsListOnTheMachine = assignments.get(machineToBeAssignedTo);
				jobsListOnTheMachine.add(oneJob);

				while(!helperStack.isEmpty())
				{
					recentlyUsedMachineStack.push(helperStack.pop());
				}

				recentlyUsedMachineStack.push(machineToBeAssignedTo);


				//Creating an iterator over list sorted by StartingTime
				Iterator<Job> sortedByStartTimeIterator = sortedByStartTime.iterator();

				//remove machine from overlapping jobs
				Job j;
				while(sortedByStartTimeIterator.hasNext())
				{
					j = sortedByStartTimeIterator.next();

					if(hasClash(j,oneJob) && j != oneJob)
					{
						System.out.println("\n\nClashFound!!");
						System.out.println("JobUnderConsideration: "+oneJob.getJobId());
						System.out.println("JobClashed: "+j.getJobId());

						//exclude this machine for consideration
						List<String> machAvailable = availableMachines.get(j);
						machAvailable.remove(machineToBeAssignedTo);
					}

				}


			}
			
			else
			{
				while(!helperStack.isEmpty())
				{
					recentlyUsedMachineStack.push(helperStack.pop());
				}
				
				List<Job> jobsListOnTheMachine = assignments.get("JobsThatAreDiscarded");
				jobsListOnTheMachine.add(oneJob);
			}

		}

		return assignments;
	}


	public static boolean hasClash(Job j, Job oneJob)
	{
		if((j.getStartTime() >= oneJob.getStartTime() 
				&& 
				j.getStartTime()<=oneJob.getEndTime()
				&&
				j.getEndTime()>=oneJob.getEndTime())

				||

				(j.getStartTime()<=oneJob.getStartTime() 
				&& j.getEndTime()>=oneJob.getEndTime()))

		{
			return true;
		}

		else
			return false;

	}



	public static void main(String[] args) throws ParseException
	{

		Map<String,List<Job>> intervalScedule = Algorithm.findMaxJobs(Schedule.generateSchedule(),2);

		for(String machine : intervalScedule.keySet())
		{
			System.out.println("Machine: "+machine+"\n");
			List<Job> jobs = intervalScedule.get(machine);

			for(Job job : jobs)
			{
				System.out.println("\t"+job.getJobId());
			}
		}
	}

}
