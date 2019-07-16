package com.tutorial.streamtutorial;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import com.tutorial.streamtutorial.entity.Trader;
import com.tutorial.streamtutorial.entity.Transaction;

import static java.util.stream.Collectors.toList;
import static java.util.Comparator.comparing;

public class StreamPractice {

	// Problems
	// 1-Find all transactions in the year 2011 and sort them by value (small to
	// high).
	// 2-What are all the unique cities where the traders work?
	// 3-Find all traders from Cambridge and sort them by name.
	// 4-Return a string of all traders’ names sorted alphabetically.
	// 5-Are any traders based in Milan?
	// 6-Print the values of all transactions from the traders living in Cambridge.
	// 7-What’s the highest value of all the transactions?
	// 8-Find the transaction with the smallest value.

	public static void main(String[] args) {

		Trader raoul = new Trader("Raoul", "Cambridge");
		Trader mario = new Trader("Mario", "Milan");
		Trader alan = new Trader("Alan", "Cambridge");
		Trader brian = new Trader("Brian", "Cambridge");

		List<Transaction> transactions = Arrays.asList(new Transaction(brian, 2011, 300),
				new Transaction(raoul, 2012, 1000), new Transaction(raoul, 2011, 400),
				new Transaction(mario, 2012, 710), new Transaction(mario, 2012, 700), new Transaction(alan, 2012, 950));

		findAllTransactionsByYearAndSortThem(transactions, 2011);
		findUniqueCities(transactions);
		findTradersByCityAndSortByName(transactions, "Cambridge");
		retrieveAllSortedTradersName(transactions);
		checkTradersByCity(transactions, "Milan");
		retrieveTransactionValuesByCity(transactions, "Cambridge");
		findHighestValueOfTransactions(transactions);
		findSmallestValueOfTransactions(transactions);
	}

	// Solution for Problem 1
	public static void findAllTransactionsByYearAndSortThem(List<Transaction> transactions, int year) {

		List<Transaction> list = transactions.stream().filter(transaction -> transaction.getYear() == year)
				.sorted(comparing((Transaction transaction) -> transaction.getValue())).collect(toList());

		System.out.println(list); // [{Trader:Brian in Cambridge, year: 2011, value:300}, {Trader:Raoul in
									// Cambridge, year: 2011, value:400}]
	}

	// Solution for Problem 2
	public static void findUniqueCities(List<Transaction> transactions) {

		List<String> cities = transactions.stream().map((Transaction transaction) -> transaction.getTrader().getCity())
				.distinct().collect(toList());

		System.out.println(cities); // [Cambridge, Milan]

	}

	// Solution for Problem 3
	public static void findTradersByCityAndSortByName(List<Transaction> transactions, String city) {

		List<Trader> list = transactions.stream().map(transaction -> transaction.getTrader()).distinct()
				.filter(trader -> trader.getCity().equalsIgnoreCase(city)).sorted(comparing(Trader::getName))
				.collect(toList());

		System.out.println(list); // [Trader:Alan in Cambridge, Trader:Brian in Cambridge, Trader:Raoul in
									// Cambridge]
	}

	// Solution for Problem 4
	public static void retrieveAllSortedTradersName(List<Transaction> transactions) {

		String traderStr = transactions.stream().map(transaction -> transaction.getTrader().getName()).distinct()
				.sorted().reduce("", (n1, n2) -> n1 + n2);

		System.out.println(traderStr); // AlanBrianMarioRaoul
	};

	// Solution for Problem 5
	public static void checkTradersByCity(List<Transaction> transactions, String city) {

		boolean isTraderBasedByCity = transactions.stream()
				.anyMatch(transaction -> transaction.getTrader().getCity().equalsIgnoreCase(city));

		System.out.println(isTraderBasedByCity); // true

	}

	// Solution for Problem 6
	public static void retrieveTransactionValuesByCity(List<Transaction> transactions, String city) {

		List<Integer> values = transactions.stream()
				.filter(transaction -> transaction.getTrader().getCity().equalsIgnoreCase(city))
				.map(transaction -> transaction.getValue()).collect(toList());

		System.out.println(values); // [300, 1000, 400, 950]
	}

	// Solution for Problem 7
	public static void findHighestValueOfTransactions(List<Transaction> transactions) {

		Optional<Transaction> trOptional = transactions.stream()
				.reduce((t1, t2) -> t1.getValue() > t2.getValue() ? t1 : t2);
		System.out.println(trOptional); // Optional[{Trader:Raoul in Cambridge, year: 2012, value:1000}]

		// Second solution
		Optional<Integer> highestValue = transactions.stream().map(Transaction::getValue).reduce(Integer::max);

	}

	// Solution for Problem 8
	public static void findSmallestValueOfTransactions(List<Transaction> transactions) {

		Optional<Transaction> trOptional = transactions.stream()
				.reduce((t1, t2) -> t1.getValue() < t2.getValue() ? t1 : t2);
		System.out.println(trOptional); // Optional[{Trader:Brian in Cambridge, year: 2011, value:300}]

		// Second solution
		Optional<Transaction> smallestTransaction = transactions.stream().min(comparing(Transaction::getValue));

	}

}
