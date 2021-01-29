package prototype.process.managers;


public abstract class BugManager {
	
	private String type = "none";
	private String groupID = "0";
	private String agressivity = "peaceful";

	
	
	
	
	public BugManager(String type, String groupID, String agressivity) {
		super();
		this.type = type;
		this.groupID = groupID;
		this.agressivity = agressivity;
	}
	
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

	
	public void update() {
		
	}
	
	
}