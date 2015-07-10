package com.amil.predojo;

import java.util.Comparator;


public class ComparatorDeads implements Comparator {

	@Override
	public int compare(Object obj1, Object obj2) {
		Player p1=(Player)obj1;
		Player p2=(Player)obj2;
		return p2.getDeads()-p1.getDeads();
	}

}
