package com.tutorial.streamtutorial.entity;

public class Dish {

	private final String name;
	private final boolean vegeteratian;
	private final int calories;
	private final Type type;

	public Dish(String name, boolean vegeteratian, int calories, Type type) {
		this.name = name;
		this.vegeteratian = vegeteratian;
		this.calories = calories;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public boolean isVegeteratian() {
		return vegeteratian;
	}

	public int getCalories() {
		return calories;
	}

	public Type getType() {
		return type;
	}

	@Override
	public String toString() {
		return name;
	}

	public enum Type {
		MEAT, FISH, OTHER
	}

}
