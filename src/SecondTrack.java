import java.util.ArrayList;
import java.util.concurrent.Callable;


public class SecondTrack implements Callable<Result[]> {


	final int MULTIPLY = 1;
	final int SUMMARIZE = 2;

	//Feeder feeder;
	ArrayList<Node> Narr;
	boolean isDone;
	//Result finalAns;
	int m,t;
	double calcType;
	int To;
	poolmaneger pm;
	Result[] Rarr;

	public SecondTrack(poolmaneger pm, int t, int MaxSizeOfTask, Tracker[] track12,
			Tracker[] track13){
		To = track12.length;
		this.pm=pm;
		this.t = t;
		this.m = MaxSizeOfTask;
		isDone=false;
		 Narr = new ArrayList<Node>();
		for (int i = 0; i < track13.length; i++) {
			Task TmpTask = new Task(SUMMARIZE);
			double tmp = (track13[i].finalAns.res)+(track12[i].finalAns.res);
//			System.out.print("ST New task 1.2 : "+track12[i].finalAns.res);
//			System.out.println("1.3 : "+track13[i].finalAns.res+"= "+tmp);
			TmpTask.addNum(track12[i].finalAns.res);
			
			TmpTask.addNum(track13[i].finalAns.res);
			Node tmpNode = new Node(TmpTask, new Result());
			Narr.add(tmpNode);
		}
		
		
//		final12=true;
//		this.arr = new ArrayList<Node>();
//		calcType = SUMMARIZE;
//		this.To = track12.length;
//		this.isDone = false;
//		this.pm = pm;
//		this.t = t;
//		this.m = MaxSizeOfTask;
//		//		this.arr = new ArrayList<ArrayList<Node>>();
//		this.calcFinished = false;
//		ArrayList<Node> Narr = new ArrayList<Node>();//מגדיר רשימה חדשה
//		for (int i = 0; i < To; i++) {
//			Task tmpTask = new Task(SUMMARIZE);
//			tmpTask.addNum(track12[i].finalAns.res);
//
//			System.out.println("1.3 res: "+ track13[i].finalAns.res);
//			tmpTask.addNum(track13[i].finalAns.res);
//			//			tmpTask.addNum(track13[i].finalAns.res);
//			System.out.println("1.2 res: "+ track12[i].finalAns.res);
//			//			tmpTask.addNum(1);
//			//			tmpTask.addNum(track12[i].finalAns.res);
//			Narr.add(new Node(tmpTask, new Result()));
//		}
//		arr.add(Narr);
		
	}
	
	

	public Result[] start() {
		try {
			//Result a = this.call();
//			System.out.println("Tracker " + To +" working");
			//System.out.println("Tracker" + To + " > final Res" + a.toString());
			//System.out.println("up!@#");
			return this.call();
		} catch (Exception e) {
			System.out.println("Tracker " + To +" stopped work!!!");
			e.printStackTrace();
		}
		System.out.println("Tracker.result did nothing!!");
		return null;
	}

	@Override
	public Result[] call() throws Exception {
//		System.out.println("ST START: ");
//		System.out.println("pm.flas = "+pm.flag);
		Rarr= new Result[Narr.size()];
//		System.out.println("pm.numOfTAsk  "+ pm.numOfTask());
//			System.out.println(Narr.toString());
		Feeder f1 = new Feeder(pm, t);
		f1.setArray(Narr);
		f1.start();
		isDone=false;
		while (!isDone) {//כל עוד לא הסתיים החישוב
			int counter = 0;
			for (int i = 0; i < Narr.size(); i++) {
				if (Narr.get(i).finish) {
					counter++;
				}
			}
			if (counter == Narr.size())//בודק אם כולם סיימו לחשב
				isDone = true;
		}
	
		
		for (int i = 0; i < Narr.size(); i++) {
			Rarr[i] = Narr.get(i).res;
		}	
//		printFinalAns12();
		return Rarr;//Result type
	}
	
	private void printFinalAns12() {
//		System.out.println("print 1.2 test");
		for (int i = 0; i < To; i++) {
			System.out.println("Expr. type (1.2),  l = m = " + To + " == "
					+ (Narr.get(i).res.res));
		}
		
	}
	
	
}

