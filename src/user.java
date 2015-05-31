import java.util.ArrayList;

public class user {

	final int MULTIPLY = 1;
	final int SUMMARIZE = 2;
	static poolmaneger pm;
	Result[] res12;

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

		lr.add(2);
		lr.add(2);
		lr.add(2);

		mr.add(13);
		mr.add(17);
		mr.add(29);

		user u = new user();
		u.solution(k, r, nk, lr, mr, t, s, m, p);

	}

	public void solution(int k, int r, ArrayList<Integer> nk,
			ArrayList<Integer> lr, ArrayList<Integer> mr, int t, int s, int m,
			int p) {
		pm = new poolmaneger(p);

		Tracker track11[] = new Tracker[k];
		Tracker track12[] = new Tracker[r];
		Tracker track13[] = new Tracker[r];
		
		calc11(k,nk,t,m,1.1,track11);

		calc12(r,lr,mr,t,m,s,1.2,track12,track13);

		// all done - killing the threads
		pm.flag= false;
//		System.out.println("all done! now kill PM and print results");
		//if all done print everything
		System.out.println("print 1.1 expressions");
		for (int i = 0; i < track11.length; i++) {
			System.out.println("Expr. type (1.1),  n = " + nk.get(i) + " == "
					+ (track11[i].finalAns.res));
		}
		//not finished, should sum 1.2+1.3 expressions 
		System.out.println("print 1.2 expressions");
		for (int i = 0; i < track12.length; i++) {
//			System.out.println("Expr. type (1.2), l = m = " + lr.get(i)
//					+ " == " + track12[i].finalAns.res);
//			System.out.println("Expr. type (1.3), l = m = " + mr.get(i)
//					+ " == " + track13[i].finalAns.res);
			System.out.println("Expr. type (1.2), l = m = " + mr.get(i)
					+ " == " + res12[i].res);
		}

		System.out.println("end");
		/*
		 * כאשר מסתיימים כל החישובים צריך לעצור את הטרדים שעדיין עובדים
		 * done
		 */
	}

	private void calc12(int r, ArrayList<Integer> lr, ArrayList<Integer> mr,
			int t, int m, int s, double d, Tracker[] track12,
			Tracker[] track13) {
		for (int i = 0; i < r; i++) {
			track12[i] = new Tracker(lr.get(i), pm, t, m, 1.2);
			track13[i] = new Tracker(mr.get(i), pm, t, s, 1.3);
			track12[i].start();
			track13[i].start();
		}
		//Summaries 1.2 and 1.3
		SecondTrack t14 = new SecondTrack(pm, t, s,track12,track13);
		res12 = t14.start();	
	}
	private void calc11(int k, ArrayList<Integer> nk, int t,
			int m, double d,Tracker[] track11) {
		for (int i = 0; i < k; i++) {
			track11[i] = new Tracker(nk.get(i), pm, t, m, 1.1);
			track11[i].start();
		}
	}
}
