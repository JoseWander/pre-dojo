package com.amil.predojo;

public class KillWay {

	private String killWay;
	private int counter;
	
	
	public KillWay(String killWay, int counter) {
		super();
		this.killWay = killWay;
		this.counter = counter;
	}
	public String getKillWay() {
		return killWay;
	}
	public void setKillWay(String killWay) {
		this.killWay = killWay;
	}
	public int getCounter() {
		return counter;
	}
	public void setCounter(int counter) {
		this.counter = counter;
	}
	@Override
	public String toString() {
		return "KillWay [killWay=" + killWay + ", counter=" + counter + "]";
	}
	
	
}
