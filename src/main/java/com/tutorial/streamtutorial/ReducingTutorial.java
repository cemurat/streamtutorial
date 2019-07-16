package com.tutorial.streamtutorial;

import java.util.Arrays;
import java.util.List;

public class ReducingTutorial {

	public static void main(String[] args) {

		List<Integer> numbers = Arrays.asList(1, 10, 3, 2, 0, 5);

		calculateSumOfValues(numbers);
		findMaxValue(numbers);
	}

	private static void findMaxValue(List<Integer> numbers) {
		System.out.println("Max Value: " + numbers.stream().reduce(0, (a, b) -> a > b ? a : b)); // Max Value: 10
	}

	private static void calculateSumOfValues(List<Integer> numbers) {
		System.out.println("Sum: " + numbers.stream().reduce(0, (a, b) -> a + b)); // Sum: 21
		System.out.println("Sum:" + numbers.stream().reduce(Integer::sum)); // returns Optional
	}

}
