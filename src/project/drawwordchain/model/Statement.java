package project.drawwordchain.model;

public class Statement {

	private User user;

	private String word;

	private String imgData;

	public void setUser(User user) {
		this.user = user;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public void setImageData(String imgData) {
		this.imgData = imgData;
	}

	public User getUser() {
		return this.user;
	}

	public String getWord() {
		return this.word;
	}

	public String getImageData() {
		return this.imgData;
	}

}
