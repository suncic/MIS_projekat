package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import crud.UslugaCrud;
import entity.Nabavka;

public class NarucivanjeForma extends JDialog implements MessageListener {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField kol;
	private JLabel dobav;
	private JLabel lek;
	private StringBuilder narudzbina = new StringBuilder();
	private JTextArea textArea;
	private UslugaCrud uc = new UslugaCrud();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			NarucivanjeForma dialog = new NarucivanjeForma();
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
	public NarucivanjeForma() throws IOException {
		setTitle("Napravite narudzbinu: ");
		setBounds(100, 100, 566, 369);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("Odaberite dobavljaca");
			lblNewLabel.setBounds(10, 32, 141, 14);
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Izaberite lek/proizvod");
			lblNewLabel_1.setBounds(10, 115, 141, 14);
			contentPanel.add(lblNewLabel_1);
		}
		{
			JLabel lblNewLabel_2 = new JLabel("Unesite kolicinu");
			lblNewLabel_2.setBounds(10, 216, 141, 14);
			contentPanel.add(lblNewLabel_2);
		}
		
		textArea = new JTextArea();
		textArea.setBounds(309, 11, 231, 275);
		contentPanel.add(textArea);
		
		kol = new JTextField();
		kol.setBounds(161, 212, 134, 22);
		contentPanel.add(kol);
		kol.setColumns(10);
		
		dobav = new JLabel("");
		dobav.setBounds(20, 62, 193, 22);
		contentPanel.add(dobav);
		
		JButton btnDobavljaci = new JButton("dobavljaci");
		btnDobavljaci.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new DobaviDobavljaceForma(NarucivanjeForma.this).setVisible(true);
					btnDobavljaci.setEnabled(false);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnDobavljaci.setBounds(161, 28, 138, 23);
		contentPanel.add(btnDobavljaci);
		
		
		JButton btnProizvodi = new JButton("proizvodi");
		btnProizvodi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new DobaviLekoveForma(NarucivanjeForma.this, dobav.getText()).setVisible(true);;
			}
		});
		
		btnProizvodi.setBounds(161, 111, 138, 23);
		contentPanel.add(btnProizvodi);
		
		lek = new JLabel("");
		lek.setBounds(20, 168, 169, 22);
		contentPanel.add(lek);
		
		JButton prebaci = new JButton(">>");
		prebaci.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!kol.getText().equals("")) {
					String l = lek.getText();
					String k = kol.getText();
					narudzbina.append(l + ", kolicina: " + k + "\n");
					textArea.append(l + ", kolicina: " + k + "\n");
					lek.setText("");
					kol.setText("");
				}else {
					if(e.getSource() == prebaci) {
						JOptionPane.showMessageDialog(NarucivanjeForma.this, "Niste uneli kolicinu", "ERROR", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		prebaci.setBounds(250, 263, 49, 23);
		contentPanel.add(prebaci);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton naruci = new JButton("Naruci");
				naruci.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						uc.naruci(new Nabavka(narudzbina));
						setVisible(false);
					}
				});
				naruci.setActionCommand("OK");
				buttonPane.add(naruci);
				getRootPane().setDefaultButton(naruci);
			}
		}
	}

	@Override
	public void onMessage(String str, String imeLabele) {
		if(imeLabele.equalsIgnoreCase("dobav")) {
			dobav.setText(str);
			textArea.append("Trenutnu narudzbinu obavlja: \n" + str + "\n");
		}else if (imeLabele.equalsIgnoreCase("lek")) {
			lek.setText(str);
		}
	}
}
