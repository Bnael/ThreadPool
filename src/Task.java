import java.util.concurrent.Callable;

public class Task implements Callable<Double> {

	Calculations calc;
	boolean firstCalc;
	Queue<Double> numq;

	public Task(double calcType, boolean firstCalc) {
		this.firstCalc = firstCalc;
		this.calc = new Calculations(calcType);
		numq = new Queue<Double>();
	}

	public Task(double calc) {
		this.firstCalc = false;
		this.calc = new Calculations(calc);
		numq = new Queue<Double>();
	}

	public void addNum(double n) {
		if (firstCalc) {
			numq.enqueue(calc.calc(n));
		} else {
			numq.enqueue(n);
		}

	}

	@Override
	public String toString() {
		return "Task [numq=" + numq + "]";
	}

	@Override
	public Double call() throws Exception {
		double ans =0;

		while (!numq.isEmpty()) {
			ans = calc.calc(ans, numq.dequeue());
		}
		 
		return ans;

	}

}
