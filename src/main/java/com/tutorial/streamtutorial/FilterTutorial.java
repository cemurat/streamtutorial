package com.tutorial.streamtutorial;

import java.util.Arrays;
import java.util.List;

import com.tutorial.streamtutorial.entity.Dish;
import com.tutorial.streamtutorial.entity.Dish.Type;
import static java.util.stream.Collectors.toList;

public class FilterTutorial {
	public static void main(String[] args) {

		filterStream();
		filterStreamWithDistinctValues();

		// Java 9
		// If collection was already sorted
		sliceStreamWithTakeWhile();
		sliceStreamWithDropWhile();

		filterStreamWithLimit();
		filterStreamWithSkip();

	}

	private static void sliceStreamWithTakeWhile() {

		List<Dish> specialList = Arrays.asList(new Dish("seasonol fruit", true, 120, Type.OTHER),
				new Dish("prawns", false, 300, Type.FISH), new Dish("rice", true, 350, Type.OTHER),
				new Dish("chicken", false, 400, Type.MEAT));

		List<Dish> slicedList = specialList.stream().takeWhile(d -> d.getCalories() < 320).collect(toList());

		System.out.println(slicedList); // [seasonol fruit,prawns]

	}

	private static void sliceStreamWithDropWhile() {

		List<Dish> specialList = Arrays.asList(new Dish("seasonol fruit", true, 120, Type.OTHER),
				new Dish("prawns", false, 300, Type.FISH), new Dish("rice", true, 350, Type.OTHER),
				new Dish("chicken", false, 400, Type.MEAT));

		List<Dish> slicedList = specialList.stream().dropWhile(d -> d.getCalories() < 320).collect(toList());

		System.out.println(slicedList); // [rice,chicken]

	}

	private static void filterStreamWithDistinctValues() {
		List<Dish> menu = Arrays.asList(new Dish("Spaghetti", true, 300, Type.OTHER),
				new Dish("Meat", false, 250, Type.MEAT), new Dish("Red Beans", true, 150, Type.OTHER));

		List<Dish> vegetarianMenu = menu.stream().filter((Dish d) -> d.isVegeteratian()).collect(toList());

		System.out.println(vegetarianMenu); // [Spaghetti,Red Beans]

	}

	private static void filterStream() {
		List<Integer> numbers = Arrays.asList(1, 2, 4, 2, 6, 8, 5, 6, 5);
		List<Integer> distinctEventNumbers = numbers.stream().filter(i -> i % 2 == 0).distinct().collect(toList());

		System.out.println(distinctEventNumbers); // [2,4,6,8]

	}

	private static void filterStreamWithLimit() {
		List<Dish> specialList = Arrays.asList(new Dish("seasonol fruit", true, 120, Type.OTHER),
				new Dish("prawns", false, 300, Type.FISH), new Dish("rice", true, 350, Type.OTHER),
				new Dish("chicken", false, 400, Type.MEAT));

		List<Dish> slicedList = specialList.stream().filter(d -> d.getCalories() < 360).limit(1).collect(toList());

		System.out.println(slicedList); // [seasonol fruit]

	}

	private static void filterStreamWithSkip() {
		List<Dish> specialList = Arrays.asList(new Dish("seasonol fruit", true, 120, Type.OTHER),
				new Dish("prawns", false, 300, Type.FISH), new Dish("rice", true, 350, Type.OTHER),
				new Dish("chicken", false, 400, Type.MEAT));

		List<Dish> slicedList = specialList.stream().filter(d -> d.getCalories() < 360).skip(2).collect(toList());

		System.out.println(slicedList); // [rice]

	}

}
