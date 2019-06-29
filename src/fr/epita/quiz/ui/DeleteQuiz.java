package fr.epita.quiz.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import fr.epita.quiz.services.data.QuizJDBCDAO;

public class DeleteQuiz extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6839934241297489798L;
	
	JFrame searchFrame, tableFrame, resultFrame;
	JLabel label;
	JButton button;
	JPanel panel;
	static JTable table;
	private static QuizJDBCDAO dao = QuizJDBCDAO.getInstance();
	private HashMap<String, String> retMap;

	String driverName = "org.h2.Driver";
	String url = "jdbc:h2:~/test";
	String userName = "sa";
	String password = "";
	int id;

	public void init() {
		searchFrame = new JFrame("Quiz List");
		searchFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		searchFrame.setLayout(null);
		searchFrame.setBounds(100, 100, 461, 623);
		label = new JLabel("Quiz");
		label.setBounds(10, 30, 180, 20);
		searchFrame.add(label);
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBounds(32, 364, 206, 20);

		Connection con;
		try {
			con = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
			Statement stat = con.createStatement();
			ResultSet rs = stat.executeQuery("select ID, NAME from QUIZ");
			while (rs.next()) {
				comboBox.addItem(rs.getString("NAME"));
				id = Integer.parseInt(rs.getString("ID"));
			}
			rs.close();
			stat.close();
			con.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		searchFrame.add(comboBox);
		
		button = new JButton("Delete");
		button.setBounds(120, 100, 100, 20);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean isSucc = dao.deleteQuiz(id);
				
				if (isSucc) {
					JOptionPane.showMessageDialog(null, "Quiz  is Deleted successfully");
				}
			}
		});
		searchFrame.add(button);
		searchFrame.setVisible(true);
	}

}