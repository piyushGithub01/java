package com.java8.function.example;

import java.util.function.BinaryOperator;
import java.util.function.Function;

public class FunctionComposition {
	
	public static void main(String[] args){
		
		Function<Integer, Integer> add1 = x -> x + 1;
		Function<Integer, Integer> mul3 = x -> x * 3;
		
		//Function Composition
		Integer a = 10;
		Integer aResult = mul3.apply(add1.apply(a));
		System.out.println(aResult);
		System.out.println("above operation : (f,g) -> x -> g( f(x) )  ");
		
		//simple binary operator
		BinaryOperator<Integer> sum = (x, y) -> x + y;
		Integer bResult = sum.apply(1, 2);
		System.out.println(bResult);
		System.out.println("above operation : simple binary operator  ");
		
		//function composition manually using uniary operations
		BinaryOperator<Function<Integer, Integer>> compose = (f,g) -> x -> g.apply(f.apply(x));
		Function<Integer, Integer> compA = compose.apply(add1, mul3);
		Integer cResult = compA.apply(10);
		System.out.println(cResult);
		System.out.println("above operation : function compose manually using 2 uniary function ");
		
		//function compose using function api compose method
		Function<Integer, Integer> compB = mul3.compose(add1);
		Integer dResult = compB.apply(10);
		System.out.println(dResult);
		System.out.println("above operation : function compose using function api compose ");
		
		//function currying
		Function<Integer, Function<Integer, Integer>> sumCurrying = x -> y -> (x + y);
		Function<Integer, Integer> plus10 = sumCurrying.apply(10);
		Integer eResult = plus10.apply(5);
		System.out.println(eResult);
		System.out.println("above operation : function currying - partially applied function ");
	}

}
