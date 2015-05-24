import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.Semaphore;

public class Tracker implements Callable<Result> {

	Feeder feeder;
	ArrayList<ArrayList<Node>> arr;
	boolean isDone, calcFinished;
	Result finalAns;
	int m,t;
	double calc;
	int To;
	poolmaneger pm;

	

	public Tracker(int tonum, poolmaneger pm, int t, int MaxSizeOfTask, double calc) {
		this.To = tonum;
		this.isDone = false;
		this.pm = pm;
		this.calc = calc;
		
		
		this.t = t;
		this.m = MaxSizeOfTask;

		this.arr = new ArrayList<ArrayList<Node>>();
		this.calcFinished = false;
		BuildFirsfNodeList();

	}
//׳‘׳•׳ ׳” ׳�׳× ׳”׳�׳¢׳¨׳� ׳”׳¨׳�׳©׳•׳ ׳™... ׳�׳§׳‘׳� ׳�׳¡׳₪׳¨׳™׳� 
	/*
	 * 1.2.3.4... 
	 * ׳•׳‘׳•׳ ׳” ׳¨׳©׳™׳�׳” ׳©׳� TASK
	 * 
	 */
	private void BuildFirsfNodeList() {
		
		ArrayList<Node> Narr = new ArrayList<Node>();//׳�׳’׳“׳™׳¨ ׳¨׳©׳™׳�׳” ׳—׳“׳©׳”

		int from = 1;//I=1 ׳¢׳“ TO

		while (from <= To) {
			Task t = new Task(calc,true);//׳™׳•׳¦׳¨ ׳�׳©׳™׳�׳” ׳—׳“׳©׳”
	//TRUE - ׳�׳•׳�׳¨ ׳©׳¢׳�׳™׳• ׳�׳—׳©׳‘ ׳�׳× ׳”׳‘׳™׳˜׳•׳™ ׳”׳¨׳�׳©׳•׳ ׳™
			while (from % m != 0 && from <= To) {//׳™׳•׳¦׳¨ ׳�׳©׳™׳�׳•׳× ׳‘׳’׳•׳“׳� M
				t.addNum(from);//׳�׳›׳ ׳™׳¡׳” ׳�׳¡׳₪׳¨׳™׳� ׳�׳˜׳•׳¨ ׳©׳‘׳�׳©׳™׳�׳” גכעא
				from++;
			}
			if(from <= To){
			t.addNum(from);
			from++;
			}
			Narr.add(new Node(t, new Result()));//׳�׳•׳¡׳™׳£ ׳ ׳•׳“ ׳�׳¨׳©׳™׳�׳”

		}
		this.arr.add(Narr);//׳�׳•׳¡׳™׳£ ׳�׳× ׳”׳¨׳©׳™׳�׳” ׳”׳¨׳�׳©׳•׳ ׳” ׳�׳¨׳©׳™׳�׳” ׳”׳¨׳�׳©׳™׳×
	
	}


//׳₪׳•׳ ׳§׳¦׳™׳™׳” ׳©׳�׳₪׳¢׳™׳�׳” ׳�׳× ׳”׳§׳•׳�׳�׳‘׳�
	public Result start() {
		try {
			
			Result a = this.call();
			System.out.println("Tracker" + To + " > finel Res" + a.toString());
			System.out.println("up!@#");
			
			return a;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Result call() throws Exception {

		while (!calcFinished) {//׳�׳�׳©׳™׳� ׳›׳� ׳¢׳•׳“ ׳�׳� ׳”׳’׳¢׳ ׳• ׳�׳×׳•׳¦׳�׳” ׳™׳—׳™׳“׳” 
			this.feeder = new Feeder(this.pm, this.t);

			// System.out.println(feeder.getState());
			ArrayList<Node> a = this.arr.get(this.arr.size() - 1);//׳�׳•׳§׳— ׳�׳× ׳”׳�׳¢׳¨׳� ׳”׳�׳—׳¨׳•׳� 
			feeder.setArray(a);//׳�׳›׳ ׳™׳¡ ׳�׳₪׳™׳“׳¨
			feeder.start();

			while (!isDone) {//׳›׳� ׳¢׳•׳“ ׳�׳� ׳”׳¡׳×׳™׳™׳� ׳”׳—׳™׳©׳•׳‘
				int counter = 0;
				for (int i = 0; i < a.size(); i++) {
					if (a.get(i).finish) {
						counter++;
					}
				}
				if (counter == a.size())//׳‘׳•׳“׳§ ׳�׳� ׳›׳•׳�׳� ׳¡׳™׳™׳�׳• ׳�׳—׳©׳‘
					isDone = true;
			}

			this.arr.add(CreateNodeListFromResult(a));//׳�׳›׳™׳� ׳�׳¢׳¨׳� ׳—׳“׳© ׳©׳� ׳�׳©׳™׳�׳•׳× ׳¢׳� ׳¡׳�׳� ׳”׳×׳•׳¦׳�׳•׳× ׳”׳�׳—׳¨׳•׳ ׳•׳×
			isDone = false;
		}

		return finalAns;
	}
/**
 * 
 * @param a ׳�׳§׳‘׳�׳× ׳�׳¢׳¨׳� ׳©׳� ׳ ׳•׳“׳™׳� ׳•׳¢׳� ׳¡׳�׳� ׳”׳×׳•׳¦׳�׳•׳× ׳©׳‘׳ ׳•׳“׳™׳� 
 * @return ׳”׳™׳� ׳�׳›׳™׳ ׳” ׳�׳¢׳¨׳� ׳—׳“׳© ׳©׳� ׳˜׳¡׳§׳™׳�, ׳�׳©׳™׳�׳•׳×
 */
	private ArrayList<Node> CreateNodeListFromResult(ArrayList<Node> a) {

		ArrayList<Node> temp = new ArrayList<Node>();

		int i = 0;
		if (a.size() == 1) {
			// System.out.println("FinelANS Set");
			finalAns = a.get(0).res;
			calcFinished = true;
		} else if (a.size() <= m) {
			Task t = new Task(calc);
			for (int k = 0; k < a.size(); k++) {
				t.addNum(a.get(k).getRes());
			}
			temp.add(new Node(t, new Result()));

		} else {
			while (i < a.size()) {
				Task t = new Task(calc);
				while (i % m != 0 && i < a.size()) {

					double ans = a.get(i).getRes();
					t.addNum(ans);
					i++;
					// System.out.println("!res[" + i + "] = " + ans);

				}
				if (i < a.size()) {
					double ans = a.get(i).getRes();
					t.addNum(ans);
					i++;
					// System.out.println("res[" + i + "] = " + ans);
				}
				temp.add(new Node(t, new Result()));
			}
		}
		// System.out.println(temp.toString());

		return (temp);
	}

}
