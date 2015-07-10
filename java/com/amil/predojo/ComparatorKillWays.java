package com.amil.predojo;

import java.util.Comparator;


public class ComparatorKillWays implements Comparator {

	@Override
	public int compare(Object obj1, Object obj2) {
		KillWay k1=(KillWay)obj1;
		KillWay k2=(KillWay)obj2;
		return k2.getCounter()-k1.getCounter();
	}

}
