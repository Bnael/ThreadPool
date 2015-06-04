import java.util.concurrent.Callable;

public class Task implements Callable<Double> {

	final int MULTIPLY = 1;
	final int SUMMARIZE = 2;
	private Queue<Double> numq;
	int calcType;

	/**
	 * initialize the Task
	 * @param calcType - multiplication or summaries 
	 */
	public Task(int calcType) {
		this.calcType = calcType;
		numq = new Queue<Double>();
	}

	public void addNum(double n) {
			numq.enqueue(n);
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
			//make the calculation here
			double num = numq.dequeue();
			switch (calcType ) {
			case MULTIPLY:
				ans = (ans * num);
				break;
			case SUMMARIZE:
				ans = ans + num;
				break;
			}
		} 
		return ans;
	}

}
