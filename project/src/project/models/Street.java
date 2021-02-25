package project.models;

public class Street {
	private int beginningIntersection;
	private int endIntersection;
	private String name;
	private int crossingDuration;
	
	public Street(String beginningIntersection, String endIntersection, String name, String crossingDuration) {
		super();
		
		this.beginningIntersection = Integer.parseInt(crossingDuration);
		this.endIntersection = Integer.parseInt(endIntersection);
		this.name = name;
		this.crossingDuration = Integer.parseInt(crossingDuration);
	}

	public int getBeginningIntersection() {
		return beginningIntersection;
	}

	public void setBeginningIntersection(int beginningIntersection) {
		this.beginningIntersection = beginningIntersection;
	}

	public int getEndIntersection() {
		return endIntersection;
	}

	public void setEndIntersection(int endIntersection) {
		this.endIntersection = endIntersection;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCrossingDuration() {
		return crossingDuration;
	}

	public void setCrossingDuration(int crossingDuration) {
		this.crossingDuration = crossingDuration;
	}
}
