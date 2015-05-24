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
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					/*
					//אפשר למחוק בסוף
					//print data for Testing 
				 */System.out.println("thred num" + super.getName()+ " print:" + node.res.res);
					try {sleep((long) (Math.random() * 1000));} 
					catch (InterruptedException e) {e.printStackTrace();}
				} 
				
				//else { System.out.println("empty");}
		}
	}

}
