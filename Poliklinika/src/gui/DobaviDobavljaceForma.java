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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import crud.UslugaCrud;
import entity.Dobavljac;

public class DobaviDobavljaceForma extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JList<Dobavljac> list;
	private DefaultListModel<Dobavljac> model1 = new DefaultListModel<>();
	private UslugaCrud uc = new UslugaCrud();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DobaviDobavljaceForma dialog = new DobaviDobavljaceForma(null);
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
	public DobaviDobavljaceForma(final MessageListener ml) throws IOException {
		setTitle("Izaberite dobavljaca");
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
				
				String[] fajlUsluge = uc.citajFajl("dobavljaci.csv");
				for(int i = 0; i < fajlUsluge.length; i++) {
					Pattern pat = Pattern.compile(".+");
					Matcher mat = pat.matcher(fajlUsluge[i]);
					
					if(mat.find()) {
						String[] token = mat.group().split(";");
						Dobavljac d = new Dobavljac(token[0].trim(), token[1].trim());
						model1.add(i, d);
					}
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
						ml.onMessage(list.getSelectedValue().toString(), "dobav");
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
