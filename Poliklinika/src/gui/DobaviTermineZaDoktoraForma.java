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
import entity.Termin;

public class DobaviTermineZaDoktoraForma extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JList<Termin> list;
	private DefaultListModel<Termin> model = new DefaultListModel<>();
	private String lekar;
	private UslugaCrud uc = new UslugaCrud();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DobaviTermineZaDoktoraForma dialog = new DobaviTermineZaDoktoraForma(null, null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DobaviTermineZaDoktoraForma(final MessageListener ml, String lekar) {
		this.lekar = lekar;
		setTitle("Izaberite termin");
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
					String[] fajlTermini = uc.citajFajl("termini.csv");
					int k = 0;
					
					for(int i = 0; i < fajlTermini.length; i++) {
						Pattern pat = Pattern.compile("(.+);(.+);(.+)");
						Matcher mat = pat.matcher(fajlTermini[i]);
						
						if(mat.find()) {
							String l = mat.group(3).trim();
							if(l.equalsIgnoreCase(lekar)) {
								Termin t = new Termin(mat.group(1).trim(), mat.group(2).trim());
								model.add(k, t);
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
						ml.onMessage(list.getSelectedValue().toString(), "termin");
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
