package cn.drizzt;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SimpleTest {
	
	public static void main(String[] args) {
		Pattern pattern = Pattern.compile("[1][34578]\\d{9}");
		Matcher isPn = pattern.matcher("16555555555");
		System.out.println(isPn.matches());
	}

}
