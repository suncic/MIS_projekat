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
import entity.Proizvod;

public class DobaviLekoveForma extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JList<Proizvod> list;
	private DefaultListModel<Proizvod> model1 = new DefaultListModel<Proizvod>();
	private String dobavljac;
	private UslugaCrud uc = new UslugaCrud();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DobaviLekoveForma dialog = new DobaviLekoveForma(null, null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DobaviLekoveForma(final MessageListener ml, String dobavljac) {
		this.dobavljac = dobavljac;
		setTitle("Izabrite proizvod");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JScrollPane scrollPane = new JScrollPane();
			contentPanel.add(scrollPane, BorderLayout.CENTER);
			{
				list = new JList<>(model1);
				scrollPane.setViewportView(list);
				
				list.setFont(new Font("Times New Roman", Font.PLAIN, 16));
				list.setBounds(10, 36, 280, 292);
				
				try {
					String[] fajlLek = uc.citajFajl("proizvodi.csv");
					int k = 0;
					
					for(int i = 0; i < fajlLek.length; i++) {
						Pattern pat = Pattern.compile("(.+);(.+)");
						Matcher mat = pat.matcher(fajlLek[i]);
						
						if(mat.find()) {
							String d = mat.group(2).trim();
							if(d.equalsIgnoreCase(dobavljac)) {
								Proizvod p = new Proizvod(mat.group(1).trim());
								model1.add(k, p);
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
						ml.onMessage(list.getSelectedValue().toString(), "lek");
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
