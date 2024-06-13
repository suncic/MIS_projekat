package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import crud.UslugaCrud;

import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class ZakazivanjeForma extends JDialog implements MessageListener {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JLabel usluga;
	private JLabel lekar;
	private JLabel termin;
	private UslugaCrud uc = new UslugaCrud();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ZakazivanjeForma dialog = new ZakazivanjeForma();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Create the dialog.
	 * @throws IOException 
	 */
	public ZakazivanjeForma() {
		setTitle("Zakazite pregled: ");
		setBounds(100, 100, 524, 362);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("Izaberite uslugu");
			lblNewLabel.setBounds(40, 30, 108, 23);
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Izaberite lekara");
			lblNewLabel_1.setBounds(40, 112, 108, 21);
			contentPanel.add(lblNewLabel_1);
		}
		{
			JLabel lblNewLabel_2 = new JLabel("Izaberite termin");
			lblNewLabel_2.setBounds(40, 196, 108, 21);
			contentPanel.add(lblNewLabel_2);
		}
		{
			JButton btnUsluga = new JButton("usluge");
			btnUsluga.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new DobaviUslugeForma(ZakazivanjeForma.this).setVisible(true);
				}
			});
			btnUsluga.setBounds(218, 30, 89, 23);
			contentPanel.add(btnUsluga);
		}
		{
			JButton btnLekar = new JButton("lekari");
			btnLekar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new DobaviLekareZaUsluguForma(ZakazivanjeForma.this, usluga.getText()).setVisible(true);
				}
			});
			btnLekar.setBounds(218, 110, 89, 23);
			contentPanel.add(btnLekar);
		}
		{
			JButton btnTermin = new JButton("termini");
			btnTermin.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new DobaviTermineZaDoktoraForma(ZakazivanjeForma.this, lekar.getText()).setVisible(true);;
				}
			});
			btnTermin.setBounds(218, 194, 89, 23);
			contentPanel.add(btnTermin);
		}
		{
			usluga = new JLabel("");
			usluga.setBounds(40, 74, 282, 14);
			contentPanel.add(usluga);
		}
		{
			lekar = new JLabel("");
			lekar.setBounds(40, 157, 289, 14);
			contentPanel.add(lekar);
		}
		{
			termin = new JLabel("");
			termin.setBounds(40, 249, 321, 14);
			contentPanel.add(termin);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton zakazi = new JButton("Zakazi");
				zakazi.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String u = usluga.getText();
						String l = lekar.getText();
						String t = termin.getText();
						uc.zakazi(u, l, t);
						setVisible(false);
					}
				});
				zakazi.setActionCommand("OK");
				buttonPane.add(zakazi);
				getRootPane().setDefaultButton(zakazi);
			}
		}
	}

	@Override
	public void onMessage(String str, String l) {
		if(l.equalsIgnoreCase("usluga")) {
			usluga.setText(str);
		}else if(l.equalsIgnoreCase("lekar")) {
			lekar.setText(str);
		}else if(l.equalsIgnoreCase("termin")) {
			termin.setText(str);
		}
	}
}
