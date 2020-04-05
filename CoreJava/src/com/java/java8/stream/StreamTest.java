package com.java.java8.stream;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTest 
{
	public static void main(String[] args)
	{
        streamCreation();
        
        //convertStreamToCollection();
        
        //coreStreamOperations();
        
        //parallelStream();
	}

	private static void streamCreation()
	{
		//1. Stream.of(val1, val2, val3….)
		System.out.println("Stream.of(val1, val2, val3….)");
		Stream<Integer> stream1 = Stream.of(1,2,3,4,5,6,7,8,9);
		stream1.forEach(p -> System.out.println(p));

		//2. Stream.of(arrayOfElements)
		System.out.println("Stream.of(arrayOfElements)");
		Stream<Integer> stream2 = Stream.of( new Integer[]{1,2,3,4,5,6,7,8,9} );
		stream2.forEach(p -> System.out.println(p));

		//3. List.stream()
		System.out.println("List.stream()");
		List<Integer> list = new ArrayList<Integer>();

		for(int i = 1; i< 10; i++)
		{
			list.add(i);
		}

		Stream<Integer> stream3 = list.stream();
		stream3.forEach(p -> System.out.println(p));
	}
	
	private static void convertStreamToCollection()
	{
		List<Integer> list = new ArrayList<Integer>();
        for(int i = 1; i< 10; i++)
        {
            list.add(i);
        }
        
        Stream<Integer> stream = list.stream();
        
		//1. Convert Stream to List – Stream.collect( Collectors.toList() )
		System.out.println("Stream.collect( Collectors.toList() )");
        List<Integer> evenNumbersList = stream.filter(i -> i%2 == 0).collect(Collectors.toList());
        System.out.print(evenNumbersList);
        
        //2. Convert Stream to array – Stream.toArray( EntryType[]::new )
        System.out.println("Stream.toArray( EntryType[]::new )");
        stream = list.stream();
        Integer[] evenNumbersArr = stream.filter(i -> i%2 == 0).toArray(Integer[]::new);
        System.out.print(evenNumbersArr);
	}
	
	private static void coreStreamOperations() 
	{
		List<String> memberNames = new ArrayList<>();
		memberNames.add("Amy");
		memberNames.add("Sam");
		memberNames.add("Aman");
		memberNames.add("Robin");
		memberNames.add("Sameer");
		memberNames.add("Symond");
		memberNames.add("Yat");
		memberNames.add("Lenin");
		
		// 1.  Intermediate operations - return the stream itself
		//filter
		System.out.println("Filter");
		memberNames.stream().filter((s) -> s.startsWith("A"))
							.forEach(s -> System.out.println(s));
	
		//map
		System.out.println("map");
		memberNames.stream().filter((s) -> s.startsWith("A"))
        					.map(String::toUpperCase);
        					
        //sorted
		System.out.println("sorted");
        memberNames.stream().sorted()
                            .map(String::toUpperCase)
                            .forEach(System.out::println);	
        
        //2. Terminal operations - return a result of a certain type
        //forEach
        System.out.println("forEach");
        memberNames.forEach(System.out::println);
        
        //collect
        System.out.println("collect");
        List<String> memNamesInUppercase = memberNames.stream().sorted()
                												.map(String::toUpperCase)
                												.collect(Collectors.toList());

        System.out.print(memNamesInUppercase);
        
        //count
        System.out.println("count");
        long totalMatched = memberNames.stream()
        								.filter((s) -> s.startsWith("A"))
        								.count();

        System.out.println(totalMatched);
        
        //3. short-circuit operations
        //anyMatch
        System.out.println("anymatch");
        boolean matched = memberNames.stream()
                					 .anyMatch((s) -> s.startsWith("A"));

        System.out.println(matched);
        
        //findFirst
        System.out.println("findFirst");
        String firstMatchedName = memberNames.stream()
                							  .filter((s) -> s.startsWith("L"))
                							  .findFirst().get();
 
        System.out.println(firstMatchedName);
	}
	
	private static void parallelStream()
	{
		System.out.println("parallelStream");
		List<Integer> list = new ArrayList<Integer>();
        for(int i = 1; i< 10; i++)
        {
            list.add(i);
        }
        
        //Here creating a parallel stream
        Stream<Integer> stream = list.parallelStream();  
        Integer[] evenNumbersArr = stream.filter(i -> i%2 == 0).toArray(Integer[]::new);
        System.out.print(evenNumbersArr);
	}

}
