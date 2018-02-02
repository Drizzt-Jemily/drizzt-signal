package cn.drizzt;

import java.util.Calendar;

public class SimpleTest {
	
	public static void main(String[] args) {
//		Pattern pattern = Pattern.compile("[1][34578]\\d{9}");
//		Matcher callingPattern = pattern.matcher("calling");
//		System.out.println(callingPattern.matches());
//		
//		LinkedBlockingQueue<SignalAuth> authQueue = new LinkedBlockingQueue<SignalAuth>();
//		SignalAuth s = new SignalAuth();
//		s.setId("1");
//		authQueue.add(s);
//		System.out.println(authQueue.size());
//		SignalAuth poll = authQueue.poll();
//		System.out.println(poll);
//		System.out.println(authQueue.size());
//		SignalAuth poll1 = authQueue.poll();
//		System.out.println(poll1);
		
		int i = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
		int j = Calendar.getInstance().get(Calendar.MINUTE);
		System.out.println(i+":"+j);
		
	}

}
