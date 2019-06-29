package fr.epita.quiz.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import fr.epita.quiz.datamodel.Answer;
import fr.epita.quiz.services.data.QuizJDBCDAO;

public class UpdateQuiz extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6839934241297489798L;
	JFrame searchFrame, tableFrame, resultFrame;
	JTextField textbox;
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
	String[] columnNames = { "Sno", "Topic", "Question", "Difficulty" };

	public void init() {
		searchFrame = new JFrame("Database Search Result");
		searchFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		searchFrame.setLayout(null);
		textbox = new JTextField();
		textbox.setBounds(190, 30, 150, 20);
		label = new JLabel("Enter Question ID to search: ");
		label.setBounds(10, 30, 180, 20);
		searchFrame.setBounds(100, 100, 461, 623);

		searchFrame.add(textbox);
		searchFrame.add(label);
		button = new JButton("search");
		button.setBounds(120, 100, 100, 20);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Qid::" + textbox.getText().toString());
				retMap = dao.retreiveQues(textbox.getText().toString());
				System.out.println("AnswerS: " + retMap.toString());
				new CreateQuiz("UPDATE", retMap);
				setVisible(true);
			}
		});
		searchFrame.add(button);
		searchFrame.setVisible(true);
		//searchFrame.setSize(500, 400);
	}

	public void showTableData() {
		/*
		 * 
		 * resultFrame = new JFrame("List of Questions");
		 * resultFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 * resultFrame.setLayout(new BorderLayout()); DefaultTableModel model = new
		 * DefaultTableModel(); model.setColumnIdentifiers(columnNames); table = new
		 * JTable(); table.setModel(model);
		 * table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		 * table.setFillsViewportHeight(true); JScrollPane scroll = new
		 * JScrollPane(table); scroll.setHorizontalScrollBarPolicy(JScrollPane.
		 * HORIZONTAL_SCROLLBAR_AS_NEEDED);
		 * scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		 * String textvalue = textbox.getText(); String id = ""; String content = "";
		 * String topic = ""; String diff = ""; try { Class.forName(driverName);
		 * Connection con = DriverManager.getConnection(url, userName, password); String
		 * sql = "select * from QUESTION where TOPICS LIKE ?"; PreparedStatement ps =
		 * con.prepareStatement(sql); ps.setString(1, textvalue); ResultSet rs =
		 * ps.executeQuery(); int i = 0; while (rs.next()) { id = rs.getString("QID");
		 * content = rs.getString("CONTENT"); topic = rs.getString("TOPICS"); diff =
		 * rs.getString("DIFFICULTY"); model.addRow(new Object[] { id, content, topic,
		 * diff }); i++; } if (i < 1) { JOptionPane.showMessageDialog(null,
		 * "No Record Found", "Error", JOptionPane.ERROR_MESSAGE); } if (i == 1) {
		 * System.out.println(i + " Record Found"); } else { System.out.println(i +
		 * " Records Found"); } } catch (Exception ex) {
		 * JOptionPane.showMessageDialog(null, ex.getMessage(), "Error",
		 * JOptionPane.ERROR_MESSAGE); } resultFrame.add(scroll);
		 * resultFrame.setVisible(true); resultFrame.setSize(500, 300);
		 * 
		 */}

}