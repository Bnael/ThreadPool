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
				//print data for Testing 
				//System.out.println("thred num" + super.getName()+ " print:" + node.res.res);
				/*
				 * why it's here? necessary? 
				 * after remove this - it works even with 50!
				try {sleep((long) (Math.random() * 1000));} 
				catch (InterruptedException e) {e.printStackTrace();}
				*/
			} 
			
			//else { System.out.println("empty");}
		}
	}

}
