public class poolmaneger {
	poolthread[] pt;// הטרדים הפועלים בגודל T
	Queue<Node> q;// המשימות שנכנסות מהפידר
	boolean flag = true;// מדליק את הטרדים ומכבה אותם ... צריך להפוך לשקר
						// כשמסתיימת כל העבודה

	public poolmaneger(int numOfWorkers) {
		pt = new poolthread[numOfWorkers];
		q = new Queue<Node>();

		for (int i = 0; i < pt.length; i++) {
			pt[i] = new poolthread(i + "", this);
			pt[i].start();
		}
	}

	// אני לא חושב שיש שימוש בפונקצייה הזאת
	public synchronized boolean isQEmpty() {
		return q.isEmpty();
	}

	// בשביל הטרדים, מכאן הם מושים משימות שנמצאות בתור צריך לשנות את זה לסמפור..
	// שלא יקחו את אותה המשימה בוז
	public synchronized Node getTask() {
		return q.dequeue();
	}

	// בגלל שאנחנו ממשים את הטור והוא לא מסונכרן אז הפונקצייה שמכניסה לתוך התור
	// צריכה להיות מסונכרנת
	// כדי לא לדרוס נתונים בעת ההכנסה ... גם את זה אפשר לעשות עם סמפור
	public synchronized void addTask(Node item) {
		this.q.enqueue(item);
	}

	public int numOfTask() {
		return q.size();
	}

}
