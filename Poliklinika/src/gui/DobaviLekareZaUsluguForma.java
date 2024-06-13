package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import crud.UslugaCrud;
import entity.Lekar;
import entity.Usluga;

public class DobaviLekareZaUsluguForma extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JList<Lekar> list;
	private DefaultListModel<Lekar> model = new DefaultListModel<>();
	private String usluga;
	private UslugaCrud uc = new UslugaCrud();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DobaviLekareZaUsluguForma dialog = new DobaviLekareZaUsluguForma(null, null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DobaviLekareZaUsluguForma(final MessageListener ml, String usluga) {
		this.usluga = usluga;
		setTitle("Izaberite lekara");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JScrollPane scrollPane = new JScrollPane();
			contentPanel.add(scrollPane, BorderLayout.CENTER);
			{
				list = new JList<>(model);
				scrollPane.setViewportView(list);
				
				list.setFont(new Font("Times New Roman", Font.PLAIN, 16));
				list.setBounds(10, 36, 280, 292);
				
				try {
					String[] fajlLekari = uc.citajFajl("lekari.csv");
					int k = 0;
					
					for(int i = 0; i < fajlLekari.length; i++) {
						Pattern pat = Pattern.compile("(.*);(.+);(.+);(.+)");
						Matcher mat = pat.matcher(fajlLekari[i]);
						
						if(mat.find()) {
							String u = mat.group(4).trim();
							if(u.equalsIgnoreCase(usluga)) {
								Lekar l = new Lekar(mat.group(1).trim(), mat.group(2).trim(), mat.group(3).trim());
								model.add(k, l);
								k++;
							}
						}
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						ml.onMessage(list.getSelectedValue().toString(), "lekar");
						setVisible(false);
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}

}
