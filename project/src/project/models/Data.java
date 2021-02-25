package project.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Data {
	private int duration;
	private int numberIntersections;
	private int numberStreets;
	private int numberCars;
	private int bonusPoints;
	
	private Map<String, Street> streets;
	private List<Car> cars;
	private List<Intersection> intersections;
	
	public Data(String duration, String numberIntersections, String numberStreets, String numberCars, String bonusPoints) {
		super();
		
		this.duration = Integer.parseInt(duration);
		this.numberIntersections = Integer.parseInt(numberIntersections);
		this.numberStreets = Integer.parseInt(numberStreets);
		this.numberCars = Integer.parseInt(numberCars);
		this.bonusPoints = Integer.parseInt(bonusPoints);
		
		this.streets = new HashMap<>();
		this.cars = new ArrayList<>();
		this.intersections = new ArrayList<>();
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public int getNumberIntersections() {
		return numberIntersections;
	}

	public void setNumberIntersections(int numberIntersections) {
		this.numberIntersections = numberIntersections;
	}

	public int getNumberStreets() {
		return numberStreets;
	}

	public void setNumberStreets(int numberStreets) {
		this.numberStreets = numberStreets;
	}

	public int getNumberCars() {
		return numberCars;
	}

	public void setNumberCars(int numberCars) {
		this.numberCars = numberCars;
	}

	public int getBonusPoints() {
		return bonusPoints;
	}

	public void setBonusPoints(int bonusPoints) {
		this.bonusPoints = bonusPoints;
	}

	public Map<String, Street> getStreets() {
		return streets;
	}

	public void setStreets(Map<String, Street> streets) {
		this.streets = streets;
	}

	public List<Car> getCars() {
		return cars;
	}

	public void setCars(List<Car> cars) {
		this.cars = cars;
	}

	public List<Intersection> getIntersections() {
		return intersections;
	}

	public void setIntersections(List<Intersection> intersections) {
		this.intersections = intersections;
	}
}
