package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.AbstractButton;
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
import entity.Usluga;
import javax.swing.JLabel;

public class DobaviUslugeForma extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JList<Usluga> list;
	private DefaultListModel<Usluga> model = new DefaultListModel<>();
	private UslugaCrud uc = new UslugaCrud();
	private JButton okButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DobaviUslugeForma dialog = new DobaviUslugeForma(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 *
	 */
	public DobaviUslugeForma(final MessageListener ml)  {
		setTitle("Izberite uslugu");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JScrollPane scrollPane = new JScrollPane();
			contentPanel.add(scrollPane, BorderLayout.CENTER);
			{
				list = new JList<Usluga>(model);
				scrollPane.setViewportView(list);
				
				list.setFont(new Font("Times New Roman", Font.PLAIN, 16));
				list.setBounds(10, 36, 280, 292);
				
				String[] fajlUsluge;
				try {
					fajlUsluge = uc.citajFajl("usluge.csv");
					for(int i = 0; i < fajlUsluge.length; i++) {
						Pattern pat = Pattern.compile(".+");
						Matcher mat = pat.matcher(fajlUsluge[i]);
						
						if(mat.find()) {
							Usluga d = new Usluga(mat.group().trim());
							model.add(i, d);
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
				okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						ml.onMessage(list.getSelectedValue().toString(), "usluga");
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
