package cn.drizzt;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.drizzt.entity.SignalAuth;

public class SimpleTest {
	
	public static void main(String[] args) {
		Pattern pattern = Pattern.compile("[1][34578]\\d{9}");
		Matcher callingPattern = pattern.matcher("calling");
		System.out.println(callingPattern.matches());
		
		LinkedBlockingQueue<SignalAuth> authQueue = new LinkedBlockingQueue<SignalAuth>();
		SignalAuth s = new SignalAuth();
		s.setId("1");
		authQueue.add(s);
		System.out.println(authQueue.size());
		SignalAuth poll = authQueue.poll();
		System.out.println(poll);
		System.out.println(authQueue.size());
		SignalAuth poll1 = authQueue.poll();
		System.out.println(poll1);
	}

}
