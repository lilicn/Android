package com.example.firstapp.util;

import java.util.regex.Pattern;

public final class Calculation {
	private static Pattern calculationPattern = Pattern.compile("[0-9]+(\\+|-|\\*|/)[0-9]+");
	public static String math(String cal){
		if(cal.length()<=0){
			return "";
		}else if(calculationPattern.matcher(cal).matches()){
			if(cal.contains("+")){
				String[] strs = cal.split("\\+");
				return (Integer.parseInt(strs[0])+Integer.parseInt(strs[1]))+"";
			}else if(cal.contains("-")){
				String[] strs = cal.split("-");
				return (Integer.parseInt(strs[0])-Integer.parseInt(strs[1]))+"";
			}else if(cal.contains("*")){
				String[] strs = cal.split("\\*");
				return Integer.parseInt(strs[0])*Integer.parseInt(strs[1])+"";
			}else if(cal.contains("/")){
				String[] strs = cal.split("/");
				return Integer.parseInt(strs[0])/Integer.parseInt(strs[1])+"";
			}
		}else {
			return cal;
		}			
		return "";	
	}	
}
