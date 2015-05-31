import java.util.concurrent.Callable;

public class Task implements Callable<Double> {

	final int MULTIPLY = 1;
	final int SUMMARIZE = 2;
	//Calculations calc; - not need
	//boolean firstCalc; - not need - done at the tracker
	Queue<Double> numq;
	int calcType;

	//first calc - can be done at Tracker
	/*
	public Task(double calcType, boolean firstCalc) {
		this.firstCalc = firstCalc;
		this.calc = new Calculations(calcType);
		numq = new Queue<Double>();
	}
	*/
	/**
	 * initialize the Task
	 * @param calcType - multiplication or summaries 
	 */
	public Task(int calcType) {
		//this.firstCalc = false;
		//this.calc = new Calculations(calcType);
		this.calcType = calcType;
		numq = new Queue<Double>();
	}

	public void addNum(double n) {
		//never will be firstCalc
//		if (firstCalc) {
//			numq.enqueue(calc.calc(n));
//		} else {
			numq.enqueue(n);
//		}
	}

	@Override
	public String toString() {
		return "Task [numq=" + numq + "]";
	}

	@Override
	public Double call() throws Exception {
		double ans =0;
		if(calcType==MULTIPLY){ ans = 1;};

		while (!numq.isEmpty()) {
//			ans = calc.calc(ans, numq.dequeue());
			//make the calculation here
			double num = numq.dequeue();
			switch (calcType ) {
			case MULTIPLY:
				//if(ans == 0)ans =1;  - moved outside the while
				ans = (ans * num);
				break;
			case SUMMARIZE:
				ans = ans + num;
				break;
//			case "1.2":
//			if(ans == 0)ans =1;
//			ans = (ans * num);
//			break;
			}
		} 
		return ans;
	}
	
	private float round(double res) {
		float f = new Float(res);
		return f;
	}

}
