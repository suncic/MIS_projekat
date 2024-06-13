package gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Poliklinika {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Poliklinika window = new Poliklinika();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Poliklinika() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Footlight MT Light", Font.PLAIN, 11));
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Dobro dosli! Odaberite ulogu");
		lblNewLabel.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 11));
		lblNewLabel.setBounds(189, 58, 144, 41);
		frame.getContentPane().add(lblNewLabel);
		
		JButton pacijent = new JButton("Pacijent");
		pacijent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new LogInForma("pacijent").setVisible(true);
			}
		});
		pacijent.setBounds(98, 130, 165, 29);
		frame.getContentPane().add(pacijent);
		
		JButton medSestra = new JButton("Medicinska sestra/brat");
		medSestra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new LogInForma("medicinska sestra/brat").setVisible(true);
			}
		});
		medSestra.setBounds(98, 178, 165, 32);
		frame.getContentPane().add(medSestra);
		
		JButton admin = new JButton("Admin");
		admin.setBounds(292, 181, 144, 29);
		frame.getContentPane().add(admin);
		
		JButton lekar = new JButton("Lekar");
		lekar.setBounds(292, 130, 144, 29);
		frame.getContentPane().add(lekar);
		frame.setBounds(100, 100, 570, 396);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
