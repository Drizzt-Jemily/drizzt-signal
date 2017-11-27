package cn.drizzt;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SimpleTest {
	
	public static void main(String[] args) {
		Pattern pattern = Pattern.compile("[1][34578]\\d{9}");
		Matcher callingPattern = pattern.matcher("calling");
		System.out.println(callingPattern.matches());
	}

}
