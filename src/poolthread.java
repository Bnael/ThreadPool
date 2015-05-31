public class poolthread extends Thread {

	poolmaneger pm;

	public poolthread(String n, poolmaneger pm) {
		super(n);
		this.pm = pm;
	}

	public void run() {
		//���� �������� �� ������� ���� ����� �� ������ ���� ���� ��� ���� ����� ��������
		while (pm.flag) {
			Node node = pm.getTask();//���� �����
			if (node != null) {//�� ���� ����� ����� ���� ����
				try {
					node.setRes(node.task.call());//����� �� ������ �������
				} catch (Exception e) {
					e.printStackTrace();
				}
			} 
			
			//else { System.out.println("empty");}
		}
	}

}
