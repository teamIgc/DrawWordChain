package project.drawwordchain.model;

public class WebSocketUser {

	private String name;
    private String sessionId;

	public void setName(String name) {
		this.name = name;
	}
    public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getName() {
		return this.name;
	}
	public String getSessionId() {
		return this.sessionId;
	}

}
