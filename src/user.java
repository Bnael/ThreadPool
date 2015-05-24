import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.Semaphore;

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
		nk.add(50);

		lr.add(15);
		//צריך לבדוק למה לא עובד במצב זבו מחשבים את 1.2 כאשר 
		//N=20
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

		for (int i = 0; i < r; i++) {
			track13[i] = new Tracker(nk.get(i), pm, t, s, 1.3);
			track13[i].start();
		}

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
