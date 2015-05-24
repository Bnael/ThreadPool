import java.util.ArrayList;


public class user {

	public static void main(String[] args) {
		int k = 3;
		int r = 3;
		ArrayList<Integer> nk = new ArrayList<Integer>();
		ArrayList<Integer> lr = new ArrayList<Integer>();
		ArrayList<Integer> mr = new ArrayList<Integer>();
		int t = 5;
		int s = 2;
		int m = 3;
		int p = 10;

		nk.add(5);
		
		nk.add(20);
		nk.add(10);

		lr.add(15);
		//צריך לבדוק למה לא עובד במצב זבו מחשבים את 1.2 כאשר 
		//N=20
		//no more memory!
		lr.add(20);
		lr.add(30);

		user u = new user();
		u.solution(k, r, nk, lr, mr, t, s, m, p);

	}

	public void solution(int k, int r, ArrayList<Integer> nk,
			ArrayList<Integer> lr, ArrayList<Integer> mr, int t, int s, int m,
			int p) {
		poolmaneger pm = new poolmaneger(p);

		Tracker track11[] = new Tracker[k];
		Tracker track12[] = new Tracker[r];
		Tracker track13[] = new Tracker[r];

		for (int i = 0; i < k; i++) {
			track11[i] = new Tracker(nk.get(i), pm, t, m, 1.1);
			track11[i].start();
		}

		/*
		 * אפשר לחשב כל ביטוי בנפרד 1.2.1ו 1.2.2, צריך לחשב את הביטוי כולו ביחד
		 * 
		 */

		for (int i = 0; i < r; i++) {
			track12[i] = new Tracker(lr.get(i), pm, t, m, 1.2);
			track12[i].start();
		}
//
//		for (int i = 0; i < r; i++) {
//			track13[i] = new Tracker(mr.get(i), pm, t, s, 1.3);
//			track13[i].start();
//		}
		
		
		//wait till everything is complete
		boolean allDone = false;
		while(allDone){
			boolean secFlag = true;
			for (int i = 0; i < track11.length && secFlag; i++) {
				if(!track11[i].isDone){
					secFlag = false;
				}
			}
			for (int i = 0; i < track12.length && secFlag; i++) {
				if(!track12[i].isDone){
					secFlag = false;
				}
			}
			for (int i = 0; i < track13.length && secFlag; i++) {
				if(!track13[i].isDone){
					secFlag = false;
				}
			}
			if(secFlag){
				allDone = true;
			}
		}
		pm.flag= false;
		System.out.println("all done! now kill PM and print results");
		
		//if all done print everything
		for (int i = 0; i < track11.length; i++) {
			System.out.println("Expr. type (1.1),  n =" + nk.get(i) + " == "
					+ track11[i].finalAns.res);
		}

		for (int i = 0; i < track12.length; i++) {
			System.out.println("Expr. type (1.2), l = m = " + lr.get(i)
					+ " == " + track12[i].finalAns.res);
		}

		System.out.println("end");
		/*
		 * כאשר מסתיימים כל החישובים צריך לעצור את הטרדים שעדיין עובדים
		 */

	}

}
