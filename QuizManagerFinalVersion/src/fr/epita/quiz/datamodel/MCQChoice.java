package fr.epita.quiz.datamodel;

public class MCQChoice {

	private String choice;
	private boolean valid;
	
	public String getChoice() {
		return choice;
	}
	public void setChoice(String choice) {
		this.choice = choice;
	}
	public boolean isValid() {
		return valid;
	}
	public void setValid(boolean valid) {
		this.valid = valid;
	}
	public MCQChoice(String choice, boolean valid) {
		super();
		this.choice = choice;
		this.valid = valid;
	}
	
	
}
