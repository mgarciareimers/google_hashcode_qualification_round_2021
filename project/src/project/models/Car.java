package project.models;

import java.util.ArrayList;
import java.util.List;

public class Car {
	private int numberStreets;
	private List<Street> streets;
	private List<Integer> intersectionIds;
	private int minDuration;
	
	public Car(int numberStreets, List<Street> streets, List<Integer> intersectionIds) {
		super();
		
		this.numberStreets = numberStreets;
		
		this.streets = streets;
		this.intersectionIds = intersectionIds;
		
		this.minDuration = 0;
		
		for (Street street : this.streets) {
			this.minDuration = this.minDuration + street.getCrossingDuration();
		}
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
	

	public List<Integer> getIntersectionIds() {
		return intersectionIds;
	}

	public void setIntersectionIds(List<Integer> intersectionIds) {
		this.intersectionIds = intersectionIds;
	}

	public int getMinDuration() {
		return minDuration;
	}

	public void setMinDuration(int minDuration) {
		this.minDuration = minDuration;
	}
}
