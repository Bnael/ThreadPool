//make it callable or marge to Task!

public class Calculations {
/* test!!
	public static void main(String[] args) {
		Calculations c = new Calculations(1.1);
		double ans = c.calc(2);
		System.out.println(ans);
		ans = c.calc(ans, 2);
		System.out.println(ans);

		double arr[] = new double[5];
		double ans2 =1;
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (Math.pow(-1, 3 * (i+1)) / (2 * (i+1 + 1) + 1));
			System.out.print("   @arr[" + (i + 1) + "] = " + arr[i]);
		}
		System.out.println();

		for (int i = 0; i < arr.length; i++) {
			ans2 *= arr[i];
		}
		System.out.println("ans = " + ans2);

	}
*/
	double calcType;//סוג החישוב

	public Calculations(double calcType) {
		this.calcType = calcType;

	}
	//מקבל תוצאה ואת מה שצריך להכפיל או לחבר אליה תלוי בסוג המשוואה
	//change to call - moved to Task.call
	public double calc(double ans, double num) {

		switch (calcType + "") {
		case "1.1":
			if(ans == 0)ans =1;
			ans = (ans * num);
			break;
		case "1.2":
			if(ans == 0)ans =1;
			ans = (ans * num);
			break;
		case "1.3":
			ans = (ans + num);
			break;
		}
		return ans;
	}

	//במידה וזאת הפעם הראשונה שבונים את מערך המשימות אז הוא מציב את המספר בנוסחה
	//moved to a function at the Tracker
	public double calc(double num) {
		double ans = 1;

		switch (calcType + "") {
		case "1.1":
			ans = (Math.pow(-1, num) / (2 * num + 1));
			break;
		case "1.2":
			ans = (Math.pow(-1, 3 * num) / (2 * (num + 1) + 1));
			break;
		case "1.3":
			ans = ((num) / (2 * Math.pow(num, 2) + 1));
			break;
		}
		return ans;
	}

}
