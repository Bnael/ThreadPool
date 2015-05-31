import java.util.ArrayList;
import java.util.concurrent.Callable;

public class SecondTrack implements Callable<Result[]> {

	final int MULTIPLY = 1;
	final int SUMMARIZE = 2;
	ArrayList<Node> Narr;
	boolean isDone;
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
			TmpTask.addNum(track12[i].finalAns.res);
			TmpTask.addNum(track13[i].finalAns.res);
			Node tmpNode = new Node(TmpTask, new Result());
			Narr.add(tmpNode);
		}
	}

	public Result[] start() {
		try {
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
		Rarr= new Result[Narr.size()];
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
		return Rarr;//Result type
	}
	
}

