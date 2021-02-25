package project.models;

import java.util.ArrayList;
import java.util.List;

public class Intersection {
	private int id;
	private List<Street> inStreets;
	private List<Street> outStreets;
	private List<StreetSchedule> schedules;
	private List<StreetSchedule> arrivals;
	
	public Intersection(int id) {
		super();
		
		this.id = id;
		this.inStreets = new ArrayList<>();
		this.outStreets = new ArrayList<>();
		this.schedules = new ArrayList<>();
		this.arrivals = new ArrayList<>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Street> getInStreets() {
		return inStreets;
	}

	public void setInStreets(List<Street> inStreets) {
		this.inStreets = inStreets;
	}

	public List<Street> getOutStreets() {
		return outStreets;
	}

	public void setOutStreets(List<Street> outStreets) {
		this.outStreets = outStreets;
	}

	public List<StreetSchedule> getSchedules() {
		return schedules;
	}

	public void setSchedules(List<StreetSchedule> schedules) {
		this.schedules = schedules;
	}

	public List<StreetSchedule> getArrivals() {
		return arrivals;
	}

	public void setArrivals(List<StreetSchedule> arrivals) {
		this.arrivals = arrivals;
	}
	
}
