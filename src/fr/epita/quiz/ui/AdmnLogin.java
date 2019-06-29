package fr.epita.quiz.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class AdmnLogin extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5540687645487609987L;
	
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public AdmnLogin() {
	setVisible(true);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	//setBounds(100, 100, 450, 300);
	setBounds(100, 100, 461, 623);

	contentPane = new JPanel();
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	setContentPane(contentPane);
	contentPane.setLayout(null);
	
	JLabel lblNewLabel = new JLabel("Welcome to Admin Console");
	lblNewLabel.setBounds(96, 11, 307, 14);
	contentPane.add(lblNewLabel);
	
	
	
	JButton createBtn = new JButton("Create Quiz");
	createBtn.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {

			new CreateQuiz("ADD", null);
			setVisible(false);			
		}
	});
	createBtn.setBounds(172, 48, 119, 23);
	contentPane.add(createBtn);
	
	JButton updateBtn = new JButton("Search Quiz");
	updateBtn.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {

			UpdateQuiz updt = new UpdateQuiz();
			updt.init(); 
			setVisible(false);			
		}
	});
	updateBtn.setBounds(172, 98,119, 23);
	contentPane.add(updateBtn);
	
	JButton deleteBtn = new JButton("Delete Quiz");
	deleteBtn.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {

			DeleteQuiz delQ = new DeleteQuiz();
			delQ.init(); 
			setVisible(false);			
		}
	});
	deleteBtn.setBounds(172, 158, 119, 23);
	contentPane.add(deleteBtn);
	
	JButton hmeBtn = new JButton("Home");
	hmeBtn.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			new Main();
			setVisible(false);
		}
	});
	hmeBtn.setBounds(172, 208, 119, 23);
	contentPane.add(hmeBtn);
	}
}
