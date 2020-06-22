package com.aem.learning.core.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class WordCounting {

	public static void main(String[] args) {
        System.out.println("Come on!!!"); 
		
		String[] SentenceArray = { "This is Apple", "That is Banana", "Where is Orange", "Who have taken my Grapes" };
        String[] checkList =  { "This", "That", "Where", "is" };
       
        Map<String, Integer> map = new HashMap<String, Integer>();
        
        for (int i=0; i<checkList.length; i++){
        	int finalCount = 0;
        	for (int j=0; j<SentenceArray.length; j++){
        		int count = countOccurences(SentenceArray[j], checkList[i]);
        		finalCount= finalCount+count;
        	}
        	map.put(checkList[i], finalCount);
        }
       
        /**Using Java 8 Stream use*/
        Stream.of(map.keySet().toString(),map.values()).forEach(System.out::println);
	}

	static int countOccurences(String str, String word) {
		String a[] = str.split(" ");

		int count = 0;
		for (int i = 0; i < a.length; i++) {
			if (word.equals(a[i]))
				count++;
		}
		return count;
	}

}
