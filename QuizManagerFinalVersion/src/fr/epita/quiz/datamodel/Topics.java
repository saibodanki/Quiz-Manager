package fr.epita.quiz.datamodel;

public class Topics {

	private int topicID;
	private String topicName;
	public int getTopicID() {
		return topicID;
	}
	public void setTopicID(int topicID) {
		this.topicID = topicID;
	}
	public String getTopicName() {
		return topicName;
	}
	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}
	public Topics(int topicID, String topicName) {
		super();
		this.topicID = topicID;
		this.topicName = topicName;
	}
	
	
}
