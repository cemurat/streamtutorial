package com.tutorial.streamtutorial;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import com.tutorial.streamtutorial.entity.Dish;
import com.tutorial.streamtutorial.entity.Dish.Type;

import static java.util.stream.Collectors.toList;

public class MappingTutorial {

	public static void main(String[] args) {

		mappingList();
		mappingWithFlatMap();

	}

	private static void mappingList() {
		List<Dish> specialList = Arrays.asList(new Dish("seasonol fruit", true, 120, Type.OTHER),
				new Dish("prawns", false, 300, Type.FISH), new Dish("rice", true, 350, Type.OTHER),
				new Dish("chicken", false, 400, Type.MEAT));

		List<Integer> nameLengths = specialList.stream().map(Dish::getName).map(String::length).collect(toList());
		System.out.println(nameLengths);
	}

	private static void mappingWithFlatMap() {

		List<String> words = Arrays.asList("hello", "world"); // print helowrd

		List<String[]> distinctLettersFail1 = words.stream().map(s -> s.split("")).distinct().collect(toList()); // not
		// working,split
		// method
		// returns
		// String[]

		List<Stream<String>> distinctLettersFail2 = words.stream().map(s -> s.split("")).map(Arrays::stream).distinct()
				.collect(toList()); // map(Arrays:stream) creates new streams

		List<String> distinctLettersSuccess = words.stream().map(s -> s.split("")).flatMap(Arrays::stream).distinct()
				.collect(toList());
		System.out.println(distinctLettersSuccess); // [h, e, l, o, w, r, d]

	}

}
