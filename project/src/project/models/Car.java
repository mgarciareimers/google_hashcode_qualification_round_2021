package project.models;

import java.util.List;

public class Car {
	private int numberStreets;
	private List<Street> streets;
	
	public Car(int numberStreets, List<Street> streets) {
		super();
		
		this.numberStreets = numberStreets;
		this.streets = streets;
	}

	public int getNumberStreets() {
		return numberStreets;
	}

	public void setNumberStreets(int numberStreets) {
		this.numberStreets = numberStreets;
	}

	public List<Street> getStreets() {
		return streets;
	}

	public void setStreets(List<Street> streets) {
		this.streets = streets;
	}
}
