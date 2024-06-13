package gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import crud.UslugaCrud;

public class LogInForma extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tuser;
	private JPasswordField password;
	private String u;
	private JLabel uloga;
	private UslugaCrud uc = new UslugaCrud();
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LogInForma frame = new LogInForma(null);
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
	public LogInForma(String u) {
		this.u = u;
		setTitle("Logovanje");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 511, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel user = new JLabel("Username");
		user.setBounds(78, 116, 80, 21);
		contentPane.add(user);
		
		JLabel sifra = new JLabel("Password");
		sifra.setBounds(78, 163, 80, 21);
		contentPane.add(sifra);
		
		tuser = new JTextField();
		tuser.setBounds(203, 116, 112, 21);
		contentPane.add(tuser);
		tuser.setColumns(10);
		
		uloga = new JLabel("");
		uloga.setBounds(203, 81, 209, 18);
		contentPane.add(uloga);
		uloga.setText(u);
		
		JButton log = new JButton("Log in");
		log.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!tuser.getText().equals("") && password.getPassword() != null) {
					if(u.equals("pacijent")) {
						boolean tacniPodaci;
						try {
							tacniPodaci = uc.logIn(tuser.getText(), password.getPassword(), u);
							
							if(tacniPodaci) {
								try {
									new PacijentForma().setVisible(true);
								} catch (IOException e1) {
									e1.printStackTrace();
								}
							}else {
								JOptionPane.showMessageDialog(LogInForma.this, "Netacni podaci", "ERROR", JOptionPane.ERROR_MESSAGE);
								tuser.setText("");
								password.setText("");
							}
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}else if (u.equals("medicinska sestra/brat")) {
						boolean tacniPodaci;
						try {
							tacniPodaci = uc.logIn(tuser.getText(), password.getPassword(), u);
							
							if(tacniPodaci) {
								new MedSestraForma().setVisible(true);
							}else {
								JOptionPane.showMessageDialog(LogInForma.this, "Netacni podaci", "ERROR", JOptionPane.ERROR_MESSAGE);
								tuser.setText("");
								password.setText("");
							}
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
				}else {
					JOptionPane.showMessageDialog(LogInForma.this, "Nepotpuni podaci", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		log.setBounds(350, 251, 89, 23);
		contentPane.add(log);
		
		password = new JPasswordField();
		password.setBounds(203, 163, 112, 21);
		contentPane.add(password);
	}
}
