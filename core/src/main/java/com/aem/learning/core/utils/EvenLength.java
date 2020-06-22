package com.aem.learning.core.utils;

public class EvenLength {

	public static void main(String[] args) {
		
		String ss = "Time to write great code";
		String sss= "It iss a pleasants day today";
		
		String results = getDetaills(sss);
		System.out.println("Results  is :"+ results);
	}
	
	static String getDetaills(String sentence){
		
		String maxima = "0";
		
		String[] arrayList = sentence.split(" ");
		
		for(String list : arrayList){
			
			if(list.length()%2 != 0)
				continue;
			if(list.length() > maxima.length() ){
				maxima =list;
			}
			
		}
		
		return maxima;
		
	}

}
