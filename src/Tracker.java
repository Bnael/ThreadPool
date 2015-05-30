import java.util.ArrayList;
import java.util.concurrent.Callable;
//import java.util.concurrent.Semaphore;

public class Tracker implements Callable<Result> {

	final int MULTIPLY = 1;
	final int SUMMARIZE = 2;

	Feeder feeder;
	ArrayList<ArrayList<Node>> arr;
	boolean isDone, calcFinished,final12;
	Result finalAns;
	int m,t;
	double calcType;
	int To;
	poolmaneger pm;



	//
	public Tracker(int tonum, poolmaneger pm, int t, int MaxSizeOfTask, double calcType) {
		this.To = tonum;
		this.isDone = false;
		this.pm = pm;
		this.calcType = calcType;
		this.t = t;
		this.m = MaxSizeOfTask;
		this.arr = new ArrayList<ArrayList<Node>>();
		this.calcFinished = false;
		BuildFirsfNodeList();//initialize
	}


	//delete this
	//sum 1.2 and 1.3 ?
//	public Tracker(poolmaneger pm , int t,int MaxSizeOfTask,ArrayList<Node> arr) {
//		this.arr = new ArrayList<ArrayList<Node>>();
//		this.arr.add(arr);
//		calcType = SUMMARIZE;
//		this.To = arr.size();
//		this.isDone = false;
//		this.pm = pm;
//		//		this.calcType = calcType;
//		this.t = t;
//		this.m = MaxSizeOfTask;
//		//		this.arr = new ArrayList<ArrayList<Node>>();
//		this.calcFinished = false;
//	}

	public Tracker(poolmaneger pm, int t, int MaxSizeOfTask, Tracker[] track12,
			Tracker[] track13) {
		final12=true;
		this.arr = new ArrayList<ArrayList<Node>>();
		calcType = SUMMARIZE;
		this.To = track12.length;
		this.isDone = false;
		this.pm = pm;
		this.t = t;
		this.m = MaxSizeOfTask;
		//		this.arr = new ArrayList<ArrayList<Node>>();
		this.calcFinished = false;
		ArrayList<Node> Narr = new ArrayList<Node>();//מגדיר רשימה חדשה
		for (int i = 0; i < To; i++) {
			Task tmpTask = new Task(SUMMARIZE);
			tmpTask.addNum(track12[i].finalAns.res);

			System.out.println("1.3 res: "+ track13[i].finalAns.res);
			tmpTask.addNum(track13[i].finalAns.res);
//			tmpTask.addNum(track13[i].finalAns.res);
			System.out.println("1.2 res: "+ track12[i].finalAns.res);
//			tmpTask.addNum(1);
//			tmpTask.addNum(track12[i].finalAns.res);
			Narr.add(new Node(tmpTask, new Result()));
		}
		arr.add(Narr);
	}

	//בונה את המערך הראשוני... מקבל מספרים 
	/*
	 * 1.2.3.4... 
	 * ובונה רשימה של TASK
	 * 
	 */
	private void BuildFirsfNodeList() {

		ArrayList<Node> Narr = new ArrayList<Node>();//מגדיר רשימה חדשה

		int TaskCalcType = TaskCalcType();

		int from = 1;//I=1 עד TO
		while (from <= To) {
			//Task t = new Task(calcType,true);//יוצר משימה חדשה
			//TRUE - אומר שעליו לחשב את הביטוי הראשוני
			Task t = new Task(TaskCalcType);
			while (from % m != 0 && from <= To) {//יוצר משימות בגודל M
				t.addNum(calc(from));//מכניסה מספרים לטור שבמשימה
				from++;
			}
			if(from <= To){
				t.addNum(calc(from));
				from++;
			}
			Narr.add(new Node(t, new Result()));//מוסיף נוד לרשימה
		}
		this.arr.add(Narr);//מוסיף את הרשימה הראשונה לרשימה הראשית

	}


	//פונקצייה שמפעילה את הקולאבל
	public Result start() {
		try {
			//Result a = this.call();
			System.out.println("Tracker " + To +" working");
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
	public Result call() throws Exception {

		if(!final12){
			while (!calcFinished) {//ממשיך כל עוד לא הגענו לתוצאה יחידה 
				this.feeder = new Feeder(this.pm, this.t);
				// System.out.println(feeder.getState());
				ArrayList<Node> a = this.arr.get(this.arr.size() - 1);//לוקח את המערך האחרון 
				feeder.setArray(a);//מכניס לפידר
				feeder.start();

				while (!isDone) {//כל עוד לא הסתיים החישוב
					int counter = 0;
					for (int i = 0; i < a.size(); i++) {
						if (a.get(i).finish) {
							counter++;
						}
					}
					if (counter == a.size())//בודק אם כולם סיימו לחשב
						isDone = true;
				}
				this.arr.add(CreateNodeListFromResult(a));//מכין מערך חדש של משימות על סמך התוצאות האחרונות
				isDone = false;
			}

			printFinalAns();
		}
		else{
			this.feeder = new Feeder(this.pm, this.t);
			ArrayList<Node> a = this.arr.get(0);//לוקח את המערך האחרון 
			feeder.setArray(a);//מכניס לפידר
			feeder.start();

			while (!isDone) {//כל עוד לא הסתיים החישוב
				int counter = 0;
				for (int i = 0; i < a.size(); i++) {
					if (a.get(i).finish) {
						counter++;
					}
				}
				if (counter == a.size())//בודק אם כולם סיימו לחשב
					isDone = true;
			}
			printFinalAns12(a);
		}

		return finalAns;//Result type
	}
	private void printFinalAns12(ArrayList<Node> a) {
		for (int i = 0; i < To; i++) {
			System.out.println("Expr. type (1.2),  l = m = " + To + " == "
					+ (a.get(i).res.res));
		}
		
	}


	private void printFinalAns() {
		if(calcType==1.1){
			System.out.println("Expr. type (1.1),  n = " + To + " == "
					+ (finalAns.res));
		}
//		else{
//			if(calcType==1.2){
//				System.out.println("Expr. type (1.2),  l = m = " + To + " == "
//						+ (finalAns.res));
//			}
//		}
	}

	/**
	 * @param a מקבלת מערך של נודים ועל סמך התוצאות שבנודים 
	 * @return היא מכינה מערך חדש של טסקים, משימות
	 */
	private ArrayList<Node> CreateNodeListFromResult(ArrayList<Node> a) {

		int TaskCalcType = TaskCalcType();
		ArrayList<Node> temp = new ArrayList<Node>();

		int i = 0;
		if (a.size() == 1) {
			// System.out.println("FinelANS Set");
			finalAns = a.get(0).res;
			calcFinished = true;
		} else if (a.size() <= m) {//necessary? the while will not do the work?
			Task t = new Task(TaskCalcType);
			for (int j = 0; j < a.size(); j++) {
				t.addNum(a.get(j).getRes());
			}
			temp.add(new Node(t, new Result()));

		} else {
			while (i < a.size()) {
				Task t = new Task(TaskCalcType);
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

	//help function - do the first calculation 
	public double calc(double num) {
		double ans = 1;

		switch (calcType + "") {
		case "1.1":
			ans = (Math.pow(-1, num) / (2 * num + 1));
			break;
		case "1.2":
			ans = (Math.pow(-1, 3 * num) / (2 * (num + 1) + 1));
			break;
		case "1.3":
			ans = ((num) / (2 * Math.pow(num, 2) + 1));
			break;
		}
		return ans;
	}

	/**
	 * help function - convert Tracker.calcType to Task.calcType
	 * @return TaskCalcType
	 */
	private int TaskCalcType() {
		int type = -1;
		switch(calcType+""){
		case "1.1":
			type = MULTIPLY;
			break;
		case "1.2":
			type = MULTIPLY;
			break;
		case "1.3":
			type = SUMMARIZE;
			break;
		}
		return type;
	}

}
