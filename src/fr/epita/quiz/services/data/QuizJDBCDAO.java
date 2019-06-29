package fr.epita.quiz.services.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import fr.epita.quiz.datamodel.Answer;
import fr.epita.quiz.datamodel.Quiz;
import fr.epita.quiz.exception.CreateFailedException;
import fr.epita.quiz.exception.SearchFailedException;
import fr.epita.quiz.services.ConfigEntry;
import fr.epita.quiz.services.ConfigurationService;

public class QuizJDBCDAO {

	private static QuizJDBCDAO instance;

	private static final String INSERT_QUERY = "INSERT into QUIZ (name) values(?)";
	private static final String UPDATE_QUERY = "UPDATE QUIZ SET NAME=? WHERE ID = ?";
	private static final String DELETE_QUERY = "DELETE FROM QUIZ  WHERE ID = ?";

	private static final String INSERT_QA = "INSERT into QUESTION (id, content, topics, difficulty, answer, choiceA, choiceB, choiceC, choiceD) values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE_QA = "UPDATE QUESTION SET CONTENT=?, TOPICS=?, DIFFICULTY=?, ANSWER=?, CHOICEA=?, CHOICEB=?, CHOICEC=?, CHOICED=?    WHERE QID =?";
	private static final String DELETE_QA = "DELETE FROM QUESTION  WHERE QID=?";

	private QuizJDBCDAO() {

	}

	public static QuizJDBCDAO getInstance() {
		if (instance == null) {
			instance = new QuizJDBCDAO();
		}
		return instance;
	}

	private Connection getConnection() throws SQLException {
		System.out.println("Connection Before");

		ConfigurationService conf = ConfigurationService.getInstance();
		String username = conf.getConfigurationValue("db.username", "");
		String password = conf.getConfigurationValue("db.password", "");
		String url = conf.getConfigurationValue("db.url", "");
		Connection connection = DriverManager.getConnection(url, username, password);
		return connection;
	}

	public void create(Quiz quiz) throws CreateFailedException {

		try (Connection connection = getConnection();
				PreparedStatement pstmt = connection.prepareStatement(INSERT_QUERY);) {
			pstmt.setString(1, quiz.getTitle());
			pstmt.execute();
		} catch (SQLException sqle) {
			throw new CreateFailedException(quiz);
		}

	}

	public void update(Quiz quiz) {
		try (Connection connection = getConnection();
				PreparedStatement pstmt = connection.prepareStatement(UPDATE_QUERY);) {
			pstmt.setString(1, quiz.getTitle());
			pstmt.setInt(2, quiz.getId());
			pstmt.execute();
		} catch (SQLException sqle) {
		}

	}

	public boolean deleteQuiz(int id) {
		boolean isSucc = false;
		try (Connection connection = getConnection();
				PreparedStatement pstmt = connection.prepareStatement(DELETE_QUERY);) {
			pstmt.setInt(1, id);
			pstmt.execute();
			isSucc = true;
		} catch (SQLException sqle) {
		}
		return isSucc;
	}

	public Quiz getById(int id) {
		return null;

	}

	public List<Quiz> search(Quiz quizCriterion) throws SearchFailedException {
		String searchQuery = ConfigurationService.getInstance()
				.getConfigurationValue(ConfigEntry.DB_QUERIES_QUIZ_SEARCHQUERY, "");
		List<Quiz> quizList = new ArrayList<>();
		try (Connection connection = getConnection();

				PreparedStatement pstmt = connection.prepareStatement(searchQuery)) {

			pstmt.setInt(1, quizCriterion.getId());
			pstmt.setString(2, "%" + quizCriterion.getTitle() + "%");

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("ID");
				String topic = rs.getString("NAME");
				Quiz quiz = new Quiz(topic);
				quiz.setId(id);
				quizList.add(quiz);
			}

			rs.close();
		} catch (SQLException e) {
			throw new SearchFailedException(quizCriterion);
		}
		return quizList;
	}

	public boolean createQues(Answer ans) throws CreateFailedException {
		boolean isSucc = false;
		try (Connection connection = getConnection();
				PreparedStatement pstmt = connection.prepareStatement(INSERT_QA);) {
			pstmt.setInt(1, ans.getQuestion().getId());
			pstmt.setString(2, ans.getQuestion().getContent());
			pstmt.setString(3, ans.getQuestion().getTopics());
			pstmt.setInt(4, ans.getQuestion().getDifficulty());
			pstmt.setString(5, ans.getText());
			pstmt.setString(6, ans.getMultChce().getChcA());
			pstmt.setString(7, ans.getMultChce().getChcB());
			pstmt.setString(8, ans.getMultChce().getChcC());
			pstmt.setString(9, ans.getMultChce().getChcD());

			pstmt.execute();
			isSucc = true;

		} catch (SQLException sqle) {
			throw new CreateFailedException(ans);
		}
		return isSucc;

	}
	
	public boolean updtQues(Answer ans) throws CreateFailedException {
		boolean isSucc = false;
		try (Connection connection = getConnection();
				PreparedStatement pstmt = connection.prepareStatement(UPDATE_QA);) {
			
			System.out.println("DAO Answer : "+ans.getQuestion().getContent()+
					 " - - "+ans.getQuestion().getTopics()+
					 " - - "+ans.getQuestion().getDifficulty()+
					 " - - "+ans.getText()+
					 " - - "+ans.getMultChce().getChcA()+
					 " - - "+ans.getMultChce().getChcB()+
					 " - - "+ans.getMultChce().getChcC()+
					 " - - "+ans.getMultChce().getChcD()+
					 " - - "+ans.getQuestion().getQid() );
			
			pstmt.setString(1, ans.getQuestion().getContent());
			pstmt.setString(2, ans.getQuestion().getTopics());
			pstmt.setInt(3, ans.getQuestion().getDifficulty());
			pstmt.setString(4, ans.getText());
			pstmt.setString(5, ans.getMultChce().getChcA());
			pstmt.setString(6, ans.getMultChce().getChcB());
			pstmt.setString(7, ans.getMultChce().getChcC());
			pstmt.setString(8, ans.getMultChce().getChcD());
			pstmt.setInt(9, ans.getQuestion().getQid());


			pstmt.execute();
			isSucc = true;

		} catch (SQLException sqle) {
			throw new CreateFailedException(ans);
		}
		return isSucc;

	}
	
	
	public boolean delQues(int qid) throws CreateFailedException {
		boolean isSucc = false;
		try (Connection connection = getConnection();
				PreparedStatement pstmt = connection.prepareStatement(DELETE_QA);) {
			pstmt.setInt(1, qid);
			pstmt.execute();
			isSucc = true;

		} catch (SQLException sqle) {
			throw new CreateFailedException(qid);
		}
		return isSucc;

	}

	public List<Quiz> retreiveTitle() {
		List<Quiz> quizList = new ArrayList<>();
		try (Connection connection = getConnection();
				PreparedStatement pstmt = connection.prepareStatement("select ID, NAME from QUIZ")) {

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("ID");
				String topic = rs.getString("NAME");
				Quiz quiz = new Quiz(topic);
				quiz.setId(id);
				quizList.add(quiz);
			}

			rs.close();
		} catch (SQLException e) {
		}
		return quizList;

	}

	public HashMap<String , String> retreiveQues(String string){
		HashMap<String , String> retMap  = new HashMap<String, String>();
			try (Connection connection = getConnection();
					PreparedStatement pstmt = connection.prepareStatement("select QID, CONTENT, TOPICS, DIFFICULTY, ANSWER, CHOICEA, CHOICEB, CHOICEC, CHOICED from QUESTION WHERE QID=?")) {
				pstmt.setString(1, string);
				ResultSet rs = pstmt.executeQuery();
				
			if (rs.next()) {
				
				retMap.put("QID", rs.getString(1));
				retMap.put("CONTENT", rs.getString(2));
				retMap.put("TOPICS", rs.getString(3));
				retMap.put("DIFFICULTY", rs.getString(4));
				retMap.put("ANSWER", rs.getString(5));
				retMap.put("CHOICEA", rs.getString(6));
				retMap.put("CHOICEB", rs.getString(7));
				retMap.put("CHOICEC", rs.getString(8));
				retMap.put("CHOICED", rs.getString(9));

			}

			rs.close();
		} catch (SQLException e) {
		}
		return retMap;

		}

	
}
