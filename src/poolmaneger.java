import java.util.concurrent.Semaphore;

public class poolmaneger {
	poolthread[] pt;// ������ ������� ����� T
	Queue<Node> q;// ������� ������� ������
	boolean flag = true; // when all the work is finished it's turn to false and all the threads kill themselves
	Semaphore semGet ;
	
	public poolmaneger(int numOfWorkers) {
		semGet = new Semaphore(1, true);
		pt = new poolthread[numOfWorkers];
		q = new Queue<Node>();

		for (int i = 0; i < pt.length; i++) {
			pt[i] = new poolthread(i + "", this);
			pt[i].start();
		}
	}

	/*
	// ��� �� ���� ��� ����� ��������� ����
	//no use - can delete
	public synchronized boolean isQEmpty() {
		return q.isEmpty();
	}
	 */
	
	// ����� ������, ���� �� ����� ������ ������� ���� ���� ����� �� �� ������..
	// ��� ���� �� ���� ������ ���
	//classical semaphore for n procces? - counting , lect4.8
	public synchronized Node getTask() {
		return q.dequeue();
	}

	// ���� ������ ����� �� ���� ���� �� ������� �� ��������� ������� ���� ����
	// ����� ����� ��������
	// ��� �� ����� ������ ��� ������ ... �� �� �� ���� ����� �� �����
	//use by the feeder - mutex/counting semaphore?
	//classical semaphore for n procces? - counting , lect4.8
	public synchronized void addTask(Node item) {
		this.q.enqueue(item);
	}

	public int numOfTask() {
		return q.size();
	}

}
