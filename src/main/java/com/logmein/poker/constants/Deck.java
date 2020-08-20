package com.logmein.poker.constants;

import java.util.HashMap;

public class Deck {
	public static final HashMap<Long,HashMap<String,Integer>> deck = new HashMap<>();
	public static final HashMap<Integer,String> valueFace = new HashMap<>();
	public static final HashMap<Integer,String> suit = new HashMap<>();
	static{
		suit.put(1,"Hearts");
		suit.put(2,"Spades");
		suit.put(3,"Clubs");
		suit.put(4,"Diamonds");
		valueFace.put(2,"Two");
		valueFace.put(3,"Three");
		valueFace.put(4,"Four");
		valueFace.put(5,"Five");
		valueFace.put(6,"Six");
		valueFace.put(7,"Seven");
		valueFace.put(8,"Eight");
		valueFace.put(9,"Nine");
		valueFace.put(10,"Ten");
		valueFace.put(11,"Jack");
		valueFace.put(12,"Queen");
		valueFace.put(13,"King");
		valueFace.put(1,"Ace");
		long m=1;
		for(int i : suit.keySet()){
			for(int j : valueFace.keySet()){
				HashMap<String, Integer> hm=new HashMap<>();
				hm.put("value",j);
				hm.put("suit",i);
				deck.put(m,hm);
				m++;
			}
		}
	}
}
