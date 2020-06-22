package com.aem.learning.core.utils;

import java.util.stream.IntStream;

public class StreamChecking {

	public static void main(String[] args) {

		IntStream.range(1, 8).forEach((number) -> {
	     System.out.println(number);
		 }
		);
	}

}
