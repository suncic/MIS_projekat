package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class MedSestraForma extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MedSestraForma frame = new MedSestraForma();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MedSestraForma() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton narudzbina = new JButton("Napravite novu narudzbinu");
		narudzbina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new NarucivanjeForma().setVisible(true);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		narudzbina.setBounds(26, 50, 187, 44);
		contentPane.add(narudzbina);
		
		JButton btnNewButton = new JButton("Pregled rasporeda");
		btnNewButton.setBounds(26, 141, 187, 44);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Spisak narudzbina");
		btnNewButton_1.setBounds(240, 50, 165, 42);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Log out");
		btnNewButton_2.setBounds(240, 141, 165, 44);
		contentPane.add(btnNewButton_2);
	}
}
