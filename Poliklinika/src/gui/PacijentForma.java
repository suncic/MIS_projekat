package gui;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import crud.UslugaCrud;
import entity.Lekar;
import entity.Usluga;

public class PacijentForma extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private ZakazivanjeForma zf = new ZakazivanjeForma();
	private UslugaCrud uc = new UslugaCrud();

	/**
	 * Launch the application.
	 */
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PacijentForma frame = new PacijentForma();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws IOException 
	 */
	public PacijentForma() throws IOException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 657, 431);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel usluge = new JPanel();
		usluge.setBounds(10, 42, 300, 339);
		contentPane.add(usluge);
		usluge.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("USLUGE");
		lblNewLabel.setBounds(102, 11, 78, 14);
		usluge.add(lblNewLabel);
		
		DefaultListModel<Usluga> model1 = new DefaultListModel<>();
		JList<Usluga> list = new JList<>(model1);
		list.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		list.setBounds(10, 36, 280, 292);
		usluge.add(list);
		
		String[] fajlUsluge = uc.citajFajl("usluge.csv");
		for(int i = 0; i < fajlUsluge.length; i++) {
			Pattern pat = Pattern.compile(".+");
			Matcher mat = pat.matcher(fajlUsluge[i]);
			
			if(mat.find()) {
				Usluga uslug = new Usluga(mat.group().trim());
				model1.add(i, uslug);
			}
		}
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 641, 31);
		contentPane.add(menuBar);
		
		JMenu mnNewMenu = new JMenu("HOME");
		menuBar.add(mnNewMenu);
		
		JMenu mnNewMenu_1 = new JMenu("AKCIJE");
		menuBar.add(mnNewMenu_1);
		
		JMenu mnNewMenu_2 = new JMenu("CENOVNIK");
		menuBar.add(mnNewMenu_2);
		
		JMenu mnNewMenu_3 = new JMenu("NAS TIM");
		menuBar.add(mnNewMenu_3);
		
		JMenu mnNewMenu_4 = new JMenu("KONTAKT");
		menuBar.add(mnNewMenu_4);
		Component horizontalGlue = Box.createHorizontalGlue();
		menuBar.add(horizontalGlue);
		
		JMenu profil = new JMenu("PROFIL");
		menuBar.add(profil);
		//profil.setMnemonic(KeyEvent.VK_F);
		profil.setVisible(true);
		
		JMenuItem zakazi = new JMenuItem("Zakazivanje");
		profil.add(zakazi);
		zakazi.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				zf.setVisible(true);
			}
		});
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Arzuriraj profil");
		profil.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Log out");
		profil.add(mntmNewMenuItem_1);
		
		JPanel doktori = new JPanel();
		doktori.setBounds(332, 42, 299, 339);
		contentPane.add(doktori);
		doktori.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("LEKARI");
		lblNewLabel_1.setBounds(109, 11, 46, 14);
		doktori.add(lblNewLabel_1);
		
		DefaultListModel<Lekar> model = new DefaultListModel<>();
		JList<Lekar> list1 = new JList<>(model);
		list1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		list1.setBounds(10, 39, 279, 289);
		doktori.add(list1);
		
		String patLekari = "(.+);(.+);(.+);(.+)";
		String[] fajlLekari = uc.citajFajl("lekari.csv");
		
		for(int i = 0; i < fajlLekari.length; i++) {
			Pattern pat = Pattern.compile(patLekari);
			Matcher mat = pat.matcher(fajlLekari[i]);
			
			if(mat.find()) {
				Lekar lek = new Lekar(mat.group(1).trim(), mat.group(2).trim(), mat.group(3).trim());
				model.add(i, lek);
			}
		}
	}
}
