package fr.epita.services.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import fr.epita.quiz.services.Configuration;

public class QuestionJDBCDAO {

	private static final String INSERT_QUERY = "INSERT INTO QUESTIONS (QUESTION,DIFFICULTY) VALUES (?, ?)";
	private static final String UPDATE_QUERY = "UPDATE QUESTION SET QUESTION=?,DIFFICULTY=? WHERE QUESTION=?";
	private static final String DELETE_QUERY = "DELETE QUESTIONS WHERE QUESTION=?";
	private static final String SEARCH_QUERY = "SELECT QUESTION FROM QUESTION WHERE QUESTION like ?";
	
	
	
	public void create(String questionName, int dfc) {
		try {
			Connection connection = getConnection();
			PreparedStatement stmt = connection
					.prepareStatement(INSERT_QUERY);
			stmt.setString(1, questionName);
			stmt.setInt(2, dfc);
			stmt.execute();
			stmt.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void update(String questionName, String newQuestionName, int dfc) {
		try {
			Connection connection = getConnection();
			PreparedStatement stmt = connection
					.prepareStatement(UPDATE_QUERY);
			stmt.setString(1, questionName);
			stmt.setInt(2, dfc);
			stmt.setString(3, newQuestionName);
			stmt.execute();
			stmt.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void delete(String question) {
		try {
			Connection connection = getConnection();
			PreparedStatement stmt = connection
					.prepareStatement(DELETE_QUERY);
			stmt.setString(1, question);
			
			stmt.execute();
			stmt.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void searchQuestion(String question) {
		
		try {
			Connection connection = getConnection();
			PreparedStatement stmt = connection
					.prepareStatement(SEARCH_QUERY);
			
			stmt.setString(1, "%" + question+ "%");
			
			
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				String SearchedQuestion = rs.getString(1);
				System.out.format("%s  \n",SearchedQuestion);
				
			}
			stmt.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
public void ListTopics() {
		
		
		String sqlCommand = "select * from topic";
		try  {
			Connection connection = getConnection();
			PreparedStatement selectStatement = connection.prepareStatement(sqlCommand);
			
			
			ResultSet rs = selectStatement.executeQuery();
			while(rs.next()) {
				int topicID = rs.getInt(1);
				String topicName = rs.getString(2);
				
				System.out.format("%s %s \n",topicID,topicName);
			}
			selectStatement.close();
			connection.close();

		} catch (SQLException e) {
			
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}	

public void getQuestionsByDifficulty(String difficulty) throws FileNotFoundException, UnsupportedEncodingException {
	
	String selectQuery = "select Question from QUESTIONs where difficulty=?";
	PrintWriter writer;
	
	
	
	try  {
		Connection connection = getConnection();
		PreparedStatement selectStatement = connection.prepareStatement(selectQuery);
		
		selectStatement.setString(1,difficulty);
		
		ResultSet rs = selectStatement.executeQuery();
		while(rs.next()) {
			
			String question = rs.getString(1);
			
			System.out.format("%s  \n",question);
			
		}
		selectStatement.close();
		connection.close();
		
	} catch (SQLException e) {
		
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}

	private Connection getConnection() throws SQLException, FileNotFoundException, IOException {
		Configuration config = Configuration.getInstance();
		String url = config.getPropertyValue("jdbc.url");
		String username = config.getPropertyValue("jdbc.username");
		String password = config.getPropertyValue("jdbc.password");
		
		return DriverManager.getConnection(url, username, password);
	}

}
