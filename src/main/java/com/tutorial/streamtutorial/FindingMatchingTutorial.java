package com.tutorial.streamtutorial;

import java.util.Arrays;
import java.util.List;

import com.tutorial.streamtutorial.entity.Dish;
import com.tutorial.streamtutorial.entity.Dish.Type;

public class FindingMatchingTutorial {
	public static void main(String[] args) {

		List<Dish> specialList = Arrays.asList(new Dish("seasonol fruit", true, 120, Type.OTHER),
				new Dish("prawns", false, 300, Type.FISH), new Dish("rice", true, 350, Type.OTHER),
				new Dish("chicken", false, 400, Type.MEAT));

		// short-circuiting stream operations; anyMatch,allMatch,noneMatch
		anyMatching(specialList);
		allMatch(specialList);
		noneMatching(specialList);

		findAnyTutorial(specialList);
		findFirstTutorial(specialList);

	}

	private static void findFirstTutorial(List<Dish> specialList) {
		specialList.stream().filter(dish -> dish.getCalories() > 100).findFirst() // finding the first element is more constraining in parallel
				.ifPresent(dish -> System.out.println("findFirst: " + dish)); // ifPresent method is Optional's method
	}

	private static void findAnyTutorial(List<Dish> specialList) {
		specialList.stream().filter(dish -> dish.getCalories() > 100).findAny()
				.ifPresent(a -> System.out.println("findAndy: " + a));
	}

	private static void noneMatching(List<Dish> specialList) {
		System.out.println("noneMatch Tutorial");
		System.out.println(specialList.stream().noneMatch(dish -> dish.getCalories() > 0)); // false
	}

	private static void allMatch(List<Dish> specialList) {
		System.out.println("allMatch tutorial");
		System.out.println(specialList.stream().allMatch(dish -> dish.getType().equals(Type.OTHER))); // false
		System.out.println(specialList.stream().allMatch(dish -> dish.getCalories() < 1000)); // true
	}

	private static void anyMatching(List<Dish> specialList) {
		System.out.println("anyMatch tutorial");
		System.out.println(specialList.stream().anyMatch(a -> a.getCalories() < 100)); // false
		System.out.println(specialList.stream().anyMatch(a -> a.getCalories() > 100)); // true
	}
}
