package prototype.process.managers;

public abstract class BugManager {
	
	private String type = "none";
	private String groupID = "0";
	private String agressivity = "peaceful";
	private Coordinate currentPostition;

	private Coordinate heading;
	
	public BugManager() {
		// TODO Auto-generated constructor stub
	}
	
	public abstract void update();
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	public String getGroupID() {
		return groupID;
	}

	public void setGroupID(String groupID) {
		this.groupID = groupID;
	}

	public String getAgressivity() {
		return agressivity;
	}

	public void setAgressivity(String agressivity) {
		this.agressivity = agressivity;
	}

	public Coordinate getCurrentPostition() {
		return currentPostition;
	}

	public void setCurrentPostition(Coordinate currentPostition) {
		this.currentPostition = currentPostition;
	}

	public Coordinate getHeading() {
		return heading;
	}

	public void setHeading(Coordinate heading) {
		this.heading = heading;
	}
	
	public void update() {
	}
	
	public void moveTo(Coordinate goTo) {
		setHeading(goTo);
		
	}
	
	
}


