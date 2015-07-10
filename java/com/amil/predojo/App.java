package com.amil.predojo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class App {

	/**Main method
	 * @param args String[] - 1st position must be the game log's path and file name (e.g. c:\\temp\\amil.txt).
	 */
	public static void main(String[] args) {
		
		if(args.length==0) {
			System.out.println("Please enter with the file name parameter");
		} else {
			Deque<String> killsQueue = readFile(args[0]);
			
			if(killsQueue.size()==0){
				System.out.println("File doesn't exist or it's empty");
			} else {
				Map<String,Player> players=processKillsQueue(killsQueue);
				
				printResults(players);
			}
		}
		
	}
		
	/**Method that reads the Map with player names and Player objects and prints the results. 
	 * Map values are inserted in a List because its Sort feature. ArrayList implementation was chosen because its constructor that accepts a Collection.
	 * In order to sort accordingly, Comparators are used within the List's Sort method
	 * @param players Deque
	 */
	public static void printResults(Map<String,Player> players){
		
		List<Player> listPlayers=new ArrayList<Player>(players.values());
		
		System.out.println("- Listing players by kills:");
		Comparator comp=new  ComparatorKills();
		listPlayers.sort(comp);
		for(Player player : listPlayers) {
			System.out.println("         " + player.getName() + "[" + player.getKills() + "]");
		}
		
		System.out.println("\n- Preferred winner's way of kill: " + listPlayers.get(0).getPrefKillWay());

		if(listPlayers.get(0).getDeads()==0)
				System.out.println("\n- Special award for the winner because no dead has been detected!");
			else
				System.out.println("\n- No special award for the winner because a dead has been detected");	;
		
		
		System.out.println("\n- Listing players by deads:");
		comp=new ComparatorDeads();
		listPlayers.sort(comp);
		for(Player player : listPlayers) {
			System.out.println("         " + player.getName() + "[" + player.getDeads() + "]");
		}
		
		comp=new ComparatorBestSequence();
		listPlayers.sort(comp);
		System.out.println("\n- Player with greater sequence of kills is " + listPlayers.get(0).getName() + " [" + listPlayers.get(0).getBestSeqWins() + "]");
		
	}

	/**Function that reads the Deque of lines with the word "killed" and processes it returning a Map with player names and Player objects. 
	 * Map was chosen because its Search feature. 
	 * @param killsQueue Deque
	 * @return Map
	 */
	public static Map<String,Player> processKillsQueue(Deque<String> killsQueue){
		
		Map<String,Player> players=new HashMap<String,Player>();
		String line;
		String killer;
		String killed;
		String killWay;
		int temp;
		Player tempPlayer;
		
		while (!killsQueue.isEmpty()){
			line=(String) killsQueue.poll();
			
			temp=line.indexOf("killed");
			killer=line.substring(22,temp-1);
			killed=line.substring(temp+7,line.indexOf(" ",temp+7));
			killWay=line.substring(line.indexOf(" ",temp+7)+1, line.length());
			
			if(!killer.equals("<WORLD>")){
				if(!players.containsKey(killer)){ 
					tempPlayer=new Player(killer);
					tempPlayer.setKills(1);
					tempPlayer.setPrefKillWay(killWay);
					tempPlayer.setWon(true);
					players.put(killer, tempPlayer);
				}
				else{
					tempPlayer=players.get(killer);
					tempPlayer.setKills(tempPlayer.getKills()+1);
					tempPlayer.setPrefKillWay(killWay);
					tempPlayer.setWon(true);
					players.replace(killer, tempPlayer);  
				}
			}
			
			if(!players.containsKey(killed)){ 
				tempPlayer=new Player(killed);
				tempPlayer.setDeads(1);
				tempPlayer.setWon(false);
				players.put(killed, tempPlayer);
			}
			else{
				tempPlayer=players.get(killed);
				tempPlayer.setDeads(tempPlayer.getDeads()+1);
				tempPlayer.setWon(false);
				players.replace(killed, tempPlayer);  
			}
			
		}
		
		return players;
	}
	
	/**Function that reads a file and returns a Deque of String with lines of the word "killed" inside. 
	 * Deque was chosen because it's fast and the file will be released sooner. 
	 * ArrayDeque implementation was chosen because it keeps the order just like the initial sequence. 
	 * @param fileName String
	 * @return Deque - If the file doesn't exist or it's empty, a empty Deque is returned
	 */
	public static Deque<String> readFile(String fileName) {
		
		File file=new File(fileName);
		Deque<String> kills = new ArrayDeque<String>();
		
		if (file.exists()){
			
			try {
				BufferedReader br = new BufferedReader(new FileReader(fileName));
				String line;
				while (br.ready()) {
					line = br.readLine();
					if (line.contains("killed"))
						kills.add(line);
				}
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return kills;
	}

}
