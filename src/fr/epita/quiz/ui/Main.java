package fr.epita.quiz.ui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main extends JFrame {
	private static final long serialVersionUID = -2728957700299762075L;
	private StatusBar statusBar;

	Main() {
		JFrame frame = new JFrame("Quiz Manager");
		
		JPanel pane = new JPanel();
		JButton admn, stdnt;
		admn = new JButton("Admin");
		admn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Admn();
				setVisible(false);
			}
		});
		stdnt = new JButton("Student");
		

		frame.add(admn);
		frame.add(stdnt);
		frame.setLayout(new FlowLayout());
		//frame.setSize(300, 300);
		frame.setBounds(100, 100, 461, 623);

		frame.setVisible(true);
		statusBar = new StatusBar();
		statusBar.setPreferredSize(new Dimension(270, 420));
		//pane.add(frame);
	//	pane.add(statusBar, java.awt.BorderLayout.SOUTH);

	}

	public static void main(String[] args) {
		new Main();
	}

}
