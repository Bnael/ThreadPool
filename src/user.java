import java.util.ArrayList;

public class user {

	final int MULTIPLY = 1;
	final int SUMMARIZE = 2;
	static poolmaneger pm;
	Result[] res12;

	public static void main(String[] args) {
		
		System.out.println("test 1");
		user u1 = new user();
		ArrayList<Integer> nk2 = new ArrayList<Integer>();
		ArrayList<Integer> lr2 = new ArrayList<Integer>();
		ArrayList<Integer> mr2 = new ArrayList<Integer>();
		nk2.add(1);
		lr2.add(1);
		mr2.add(2);
		
		u1.solution(1, 1,nk2, lr2, mr2,1, 2, 2, 20);
		
		System.out.println("test 2");
		
		int k = 5;
		int r = 3;
		ArrayList<Integer> nk = new ArrayList<Integer>();
		ArrayList<Integer> lr = new ArrayList<Integer>();
		ArrayList<Integer> mr = new ArrayList<Integer>();
		int t = 5;
		int s = 3;
		int m = 3;
		int p = 5;

		nk.add(1);
		nk.add(10);
		nk.add(2);
		nk.add(15);
		nk.add(8);

		lr.add(5);
		lr.add(5);
		lr.add(5);
		lr.add(6);
		lr.add(2);

		mr.add(1);
		mr.add(1);
		mr.add(1);
		mr.add(2);
		mr.add(5);

		user u = new user();
		u.solution(k, r, nk, lr, mr, t, s, m, p);
		
		
		System.out.println("test 3");
		int k3 = 20;
		int r3 = 30;
		ArrayList<Integer> nk3 = new ArrayList<Integer>();
		ArrayList<Integer> lr3 = new ArrayList<Integer>();
		ArrayList<Integer> mr3 = new ArrayList<Integer>();
		int t3 = 500;
		int s3 = 3;
		int m3 = 3;
		int p3 = 5;

//		nk3.add(1);
		for (int i = 1; i <= 20; i++) {
			nk3.add(i);
		}
		
		for (int i = 20; i <= 50; i++) {
			lr3.add(i);
		}
		
		for (int i = 10; i <=40; i++) {
			mr3.add(i);
		}
		

		user u3 = new user();
		u3.solution(k3, r3, nk3, lr3, mr3, t3, s3, m3, p3);
		

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
//		System.out.println("1.2 res "+track12[0].finalAns);
//		System.out.println("1.3 res "+track13[0].finalAns);
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
