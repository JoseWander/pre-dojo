package com.amil.predojo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

public class Player {
	private String name;
	private int deads;
	private int kills;
	private String prefKillWay="";
	Map<String,KillWay> killWays=new HashMap<String,KillWay>();
	private boolean won;
	List<Integer> seqWins=new ArrayList<Integer>();
	int bestSeqWins;
	
	public Player(String name) {
		super();
		this.name = name;
	}
	
	/**Method that sets the preferred kill way interactively.
	 * Each Player has a Map with its kill ways and number of usages. Map was chosen because its Search feature. 
	 * The preferred kill way is got with the Sort feature of List and Comparators. 
	 * @param killWay String
	 */
	public void setPrefKillWay(String killWay){
		if(killWays.isEmpty()||!killWays.containsKey(killWay)){
			killWays.put(killWay, new KillWay (killWay,1));
			prefKillWay=killWay;
		}else{
			if(killWays.containsKey(killWay)){
				KillWay tempKillWay=killWays.get(killWay);
				tempKillWay.setCounter(tempKillWay.getCounter()+1);
				killWays.replace(killWay, tempKillWay);
			}
			
			List<KillWay> listKillWays=new ArrayList<KillWay> (killWays.values());
			Comparator comp=new  ComparatorKillWays();
			listKillWays.sort(comp);
			prefKillWay=listKillWays.get(0).getKillWay();
		}
	}
	
	public boolean isWon() {
		return won;
	}

	/**Method that sets the greatest sequence of kills interactively.
	 * Each Player has a List with all its kill sequences. ArrayList implementation was chosen because it keeps the order just like the initial sequence. 
	 * A Set receives the ArrayList and retrieves the greatest number. Set was chosen because of its natural sorting feature  
	 * @param won boolean
	 */
	public void setWon(boolean won) {
		
		if(won){
			if(this.won)
				seqWins.set(seqWins.size()-1,seqWins.get(seqWins.size()-1)+1);
			else
				seqWins.add(1);
			
			TreeSet best= new TreeSet((Collection)seqWins);
			bestSeqWins=(int) best.last();
		}
		
		this.won = won;
	}

	public int getBestSeqWins() {
		return bestSeqWins;
	}

	public void setBestSeqWins(int bestSeqWins) {
		this.bestSeqWins = bestSeqWins;
	}

	public String getPrefKillWay() {
		return prefKillWay;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDeads() {
		return deads;
	}

	public void setDeads(int deads) {
		this.deads = deads;
	}

	public int getKills() {
		return kills;
	}

	public void setKills(int kills) {
		this.kills = kills;
	}

}
