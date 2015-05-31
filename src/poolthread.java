public class poolthread extends Thread {

	poolmaneger pm;

	public poolthread(String n, poolmaneger pm) {
		super(n);
		this.pm = pm;
	}

	public void run() {
		//כאשר מסתיימות כל הפעולות צריך לשנות את הלולאה הזאת לשקר ואז הטרד ייכבה אוטומטית
		while (pm.flag) {
			Node node = pm.getTask();//לוקח משימה
			if (node != null) {//אם באמת קיימת משימה מבצע אותה
				try {
					node.setRes(node.task.call());//מכניס את התוצאה לריזולט
				} catch (Exception e) {
					e.printStackTrace();
				}
			} 
			
			//else { System.out.println("empty");}
		}
	}

}
