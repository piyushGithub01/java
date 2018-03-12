package com.java8.collectors.example;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CollectorsExample {
	
	public static void main(String[] args) {
		List<String> countries = Arrays.asList("India","China","South Africa","Russia",
				"Germany","United States Of America","Brazil");
		
		//collect stream to a list
		List<String> countriesList = countries.stream().collect(Collectors.toList());
		
		//collection list to set
		Set<String> countriesSet = countries.stream().collect(Collectors.toSet());
		
		//collect to specific implementation of collection
		LinkedList<String> countiresLinkedList = countries.stream()
				.collect(Collectors.toCollection(LinkedList::new));
		
		//collect to map
		Map<String, Integer> countryLengthMap = countries.stream()
				.collect(Collectors.toMap(Function.identity(), String::length));
		countryLengthMap.entrySet().stream().forEach(x -> {System.out.println(x);});
		
		//collect to map with duplicate keys possible
		Map<String, Integer> countryLenDupMap = countries.stream()
				.collect(Collectors.toMap(Function.identity(), String::length, (a,b) -> a));
		
		//collecting anthen - making immutable collection
		List<String> immutableCountryList = countries.stream()
				.collect(Collectors.collectingAndThen(Collectors.toList(), Collections::unmodifiableList));
		immutableCountryList.forEach(System.out::println);
//		immutableCountryList.add("Jack");  UnsupportedOperationException 
		
		//string join operation
		String countriesString = countries.stream().collect(Collectors.joining());
		System.out.println("string countries" + countriesString);
		
		String countriesStringSpace = countries.stream().collect(Collectors.joining(" "));
		System.out.println("string countries space delimiter" + countriesStringSpace);
		
		String countriesStringPreDelimiterPost = countries.stream()
				.collect(Collectors.joining(" ", "**", "##"));
		System.out.println("string countries space delimiter pre ** post ##" + countriesStringPreDelimiterPost);
		
		//counting summing average min max
		Long resultA = countries.stream().collect(Collectors.counting());
		System.out.println("elements in collection " + resultA); 
		
		DoubleSummaryStatistics resultB = countries.stream().collect(Collectors.summarizingDouble(String::length));
		System.out.println(" get average : " + resultB.getAverage());
		System.out.println(" get count : " + resultB.getCount());
		System.out.println(" get max : " + resultB.getMax());
		System.out.println(" get min : " + resultB.getMin());
		System.out.println(" get sum : " + resultB.getSum());
		
		Double resultC = countries.stream().collect(Collectors.averagingDouble(String::length));
		System.out.println(" average : " + resultC);
		
		//max string find - min string find
		Optional<String> maxString = countries.stream().collect(Collectors.maxBy(Comparator.naturalOrder()));
		System.out.println(" max length string : " + maxString);
		
		//group by
		Map<Integer, Set<String>> lengthNameGroup = countries.stream()
				.collect(Collectors.groupingBy(String::length, Collectors.toSet()));
		lengthNameGroup.entrySet().stream().forEach(System.out::println);
		
		//partition by
		Map<Boolean, List<String>> boooleanNamePartition = countries.stream()
				.collect(Collectors.partitioningBy(s -> s.length()>6 , Collectors.toList()));
		boooleanNamePartition.entrySet().stream().forEach(System.out::println);
		
		
	}

}
