import java.util.ArrayList;

public class Feeder extends Thread {
	poolmaneger pm;
	int maxTask;
	ArrayList<Node> arr;

	public Feeder(poolmaneger pm, int t) {
		super();
		this.pm = pm;
		this.maxTask = t;
	}
	
	
	//���� ���� �� ����� ��� ��� �� ����� ������
	public void setArray(ArrayList<Node> arr){
		this.arr = arr;
		//System.out.println("feeder : new Array set size:"+arr.size() + arr.toString());
	}

	//����� �� ������� ������ ���� ���� ���'�
	public void run() {

		int index = 0;

		while (index < arr.size()) {

			int temp = pm.numOfTask();
			if (temp < maxTask) {
				pm.addTask(arr.get(index));
				index++;
			}
			
			//����� ������ ... ���� ����� ����
			// System.out.println("feeder ensd");

			/*try {
				sleep(5000);
			} catch (InterruptedException e) { // TODO Auto-generated
				e.printStackTrace();
			}*/
		}
		// System.out.println("Feeder ENDs"+index);
		
	}
}


