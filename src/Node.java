public class Node {
	
	Task task;
	Result res;
	
	boolean finish;// אם הוזנה תוצאה 
	
	
	public Node(Task task, Result res) {
		this.task = task;
		this.res = res;
		this.finish = false;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task t) {
		this.task = t;
	}

	
	//כשמתקבלת תוצאה הופך את הדגל לאמת
	public void setRes(double r) {
		this.res.setRes(r);
		this.finish = true;
	}
	
	public double getRes(){
		if(finish)
			return this.res.res;
		else return -0;
	}

	@Override
	public String toString() {
		return "Node [task=" + task + ", res=" + res + ", finish=" + finish
				+ "]";
	}
	
	
}
