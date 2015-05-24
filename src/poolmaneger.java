public class poolmaneger {
	poolthread[] pt;// ������ ������� ����� T
	Queue<Node> q;// ������� ������� ������
	boolean flag = true;// ����� �� ������ ����� ���� ... ���� ����� ����
						// ��������� �� ������

	public poolmaneger(int numOfWorkers) {
		pt = new poolthread[numOfWorkers];
		q = new Queue<Node>();

		for (int i = 0; i < pt.length; i++) {
			pt[i] = new poolthread(i + "", this);
			pt[i].start();
		}
	}

	// ��� �� ���� ��� ����� ��������� ����
	//no use - can delete
	public synchronized boolean isQEmpty() {
		return q.isEmpty();
	}

	// ����� ������, ���� �� ����� ������ ������� ���� ���� ����� �� �� ������..
	// ��� ���� �� ���� ������ ���
	public synchronized Node getTask() {
		return q.dequeue();
	}

	// ���� ������ ����� �� ���� ���� �� ������� �� ��������� ������� ���� ����
	// ����� ����� ��������
	// ��� �� ����� ������ ��� ������ ... �� �� �� ���� ����� �� �����
	public synchronized void addTask(Node item) {
		this.q.enqueue(item);
	}

	public int numOfTask() {
		return q.size();
	}

}
