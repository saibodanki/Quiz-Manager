package fr.epita.quiz.launcher;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

import fr.epita.services.dao.QuestionJDBCDAO;

public class Launcher {
	private static Scanner in;
	static QuestionJDBCDAO questionJDBCDao = new QuestionJDBCDAO();

	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		studentData();
		//questionJDBCDao.create("adads", 1);
	}
	
	public static void studentData() throws FileNotFoundException, UnsupportedEncodingException {
		
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Please enter your name");
		String name = scanner.nextLine();
		System.out.println("Your name is:"+name);
		
		System.out.println("Please enter your id");
		String id = scanner.nextLine();
		System.out.println("Your id is:"+id);
		
		
		System.out.println("Please enter the number to perform the operations from the below list:");
		System.out.println("1.To insert a new question into database");
		System.out.println("2.To update a question in the database");
		System.out.println("3.To delete a question from the database");
		
		System.out.println("4.To search the question based on the label");
		System.out.println("5.To get the questions based on the difficulty selected");
		System.out.println("6.To list all the topics");
		String answer;
		answer = scanner.nextLine();
		System.out.println("you entered: "+answer);
		
		switch(answer) {
		case "1":
			System.out.println("Inserting new question");
			handlingInsertQuestion();
			break;
		case "2":
			System.out.println("Updating the existing one");
			handlingUpdateQuestion();
			break;
		case "3":
			System.out.println("Delete the question");
			handlingDeleteQuestion();
			break;
		case "4":
			System.out.println("To search the question");
			handlingSearchQuestion();
			break;
		case "5":
			System.out.println("To get the questions based on the difficulty");
			handlingQuestionsOnDifficulty();
			break;
		case "6":
			System.out.println("To list all the topics");
			questionJDBCDao.ListTopics();
			break;
		default:
			System.out.println("please select valid option");
		}
	}
	public static void handlingInsertQuestion() {
		in = new Scanner(System.in);
		System.out.print("Enter the question: ");    
        String questionName = in.nextLine();
        System.out.println("Enter the topic name: ");
        @SuppressWarnings("unused")
		String topicName = in.next();
        System.out.println("Enter the difficulty");
        int dfc = in.nextInt();
        
        
        questionJDBCDao.create(questionName,dfc);
       
       System.out.println("Inserted Successfully");
	}
public static void handlingUpdateQuestion() {
		
		in = new Scanner(System.in);
		 
        System.out.print("Enter the question to be updated: ");    
        String questionName = in.nextLine();
        
        System.out.println("Enter the new difficulty");
        int dfc = in.nextInt();
        
        System.out.print("Enter the new question ");    
        String newQuestionName = in.nextLine();
        
        System.out.println("Enter the new topic");
        @SuppressWarnings("unused")
		String topicName = in.nextLine();
      
        questionJDBCDao.update(questionName, newQuestionName, dfc);
        //topicsJDBCDao.updateQuestionTopic(questionName, newQuestionName, topicName);
        System.out.println("Updated successfully");
	}
	public static void handlingDeleteQuestion() {
	 in = new Scanner(System.in);
		 
       System.out.print("Enter the question to be deleted ");    
       String questionName = in.next();
       questionJDBCDao.delete(questionName);
       //topicsJDBCDao.deleteQuestionTopic(questionName);
       System.out.println("deleted successfully");
		
	}
	public static void handlingSearchQuestion() {
	
	in = new Scanner(System.in);
	
	 
    System.out.print("Enter the question label to search: ");    
    String questionLabel = in.next();
    
    
    questionJDBCDao.searchQuestion(questionLabel);
    System.out.println("Searched successfully");
    
    
}
public static void handlingQuestionsOnDifficulty() throws FileNotFoundException, UnsupportedEncodingException {
	Scanner scanner = new Scanner(System.in);
		System.out.println("Please select the difficulty level");		
		System.out.println("1.Easy");
		System.out.println("2.Medium");
		System.out.println("3.Hard");
		
		String answer;
		
		answer = scanner.nextLine();
		
		System.out.println("the difficulty you have selected is:"+answer);
		
		questionJDBCDao.getQuestionsByDifficulty(answer);
		
		
	}
	 

}
