import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

import com.sun.org.apache.xpath.internal.operations.Mod;


public class playGround_01 {

	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub

		SimpleDateFormat ball = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss.SSS");
		SimpleDateFormat ball2 = new SimpleDateFormat("YYYY-MM-dd");
		SimpleDateFormat ball3 = new SimpleDateFormat("HH:mm:ss.SSS");
		System.out.println(ball.format(new Date()));
		System.out.println(ball2.format(new Date()));
		System.out.println(ball3.format(new Date()));
		System.out.println(ball.getInstance().toString());


		Date ingred = new Date();
		System.out.println();
		System.out.println(ingred.toString());
		//ingred = ball3.parse("21:21:21:210");
		System.out.println();

		System.out.println(ingred.toString());
		Calendar cal = Calendar.getInstance();
		//cal.set(2015, 11, 14, 23, 02);
		//cal.add(Calendar.HOUR, 2);
		System.out.println(cal.toString());
		System.out.println(cal.getTime());

		System.out.println("<<<<<<<<<<<<<<<<<<<< Game Begins >>>>>>>>>>>>>>>>>>>>>");
		System.out.println(cal.get(Calendar.DAY_OF_MONTH));
		System.out.println(cal.get(Calendar.MONTH));
		System.out.println(cal.get(Calendar.YEAR));
		System.out.println(cal.get(Calendar.HOUR));
		System.out.println(cal.get(Calendar.MINUTE));
		System.out.println(cal.get(Calendar.SECOND));
		System.out.println(cal.get(Calendar.MILLISECOND));

		System.out.println();

		Calendar cal2 = Calendar.getInstance();
		//System.out.println(cal2.getTime());
		Date dt = cal2.getTime();
		cal2.add(Calendar.MINUTE, Math.abs(new Random().nextInt()%20));
		System.out.println(dt);
		System.out.println(cal2.getTime());

		for (int i=0;i<10;i++)
		{
			System.out.println(Math.abs(new Random().nextInt()%20));

		}

		Job j1 = new Job();
		Job j2 = new Job();

		System.out.println(j1.toString());
		System.out.println(j2.toString());

		System.out.println(ball.getDateFormatSymbols());



		List<Integer> sortedByEndTime = new ArrayList<Integer>();
		sortedByEndTime.add(1);
		sortedByEndTime.add(4);
		sortedByEndTime.add(2);
		sortedByEndTime.add(7);
		sortedByEndTime.add(5);
		sortedByEndTime.add(6);


		Collections.sort(sortedByEndTime, new Comparator<Integer>(){

			@Override
			public int compare(Integer o1, Integer o2) {
				// TODO Auto-generated method stub
				if(o1 > o2)
					return 1;
				else if(o1 < o2)
					return -1;
				else return 0;
			}});

		Iterator<Integer> itr1 = sortedByEndTime.iterator();
		while(itr1.hasNext())
		{
			System.out.println(itr1.next());
		}
		System.out.println(sortedByEndTime.get(0));
		sortedByEndTime.remove(0);
		System.out.println(sortedByEndTime.get(0));
		System.out.println(sortedByEndTime);

		PriorityQueue<Machine<Integer>> a = 
				new PriorityQueue<Machine<Integer>>(new Comparator<Machine<Integer>>() {

					@Override
					public int compare(Machine<Integer> o1, Machine<Integer> o2) {
						// TODO Auto-generated method stub
						if(o1.getEarliestAvailable()>o2.getEarliestAvailable())
						{
							return -1;
						}
						else if(o1.getEarliestAvailable()<o2.getEarliestAvailable())
						{
							return 1;
						}
						else 
							return 0;
					}
				});
		a.add(new Machine<Integer>("1", 1));
		a.add(new Machine<Integer>("2", 5));
		a.add(new Machine<Integer>("3", 3));
		a.add(new Machine<Integer>("9", 9));
		a.add(new Machine<Integer>("7", 7));
		a.add(new Machine<Integer>("4", 4));
		a.add(new Machine<Integer>("8", 8));
		a.add(new Machine<Integer>("5", 5));

		System.out.println(a.toString());

	}

}
