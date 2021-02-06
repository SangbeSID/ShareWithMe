package IHM;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

// ==================================

// ===================================

import DB.MySystem;
import DB.Requete;

public class CreationUtilisateur extends MyFrame{
	private JPanel panel = new JPanel();
	private JTextField txt_username = new JTextField(25);
	private JPasswordField txt_password = new JPasswordField(25);
	private MyButton btn_creation = new MyButton("Creer");
	private Requete requete;
	public final static int ROLE_ADMIN = 1;
	public final static int ROLE_USER = 0;
	
	public CreationUtilisateur() {
		super("Creation d'Utilisateur");
		this.requete = new Requete();
		JLabel lbl_username = new JLabel("UTILISATEUR");
		JLabel lbl_password = new JLabel("MOT DE PASSE");
		
		this.btn_creation.addActionListener(new CreationUtilisateurController());
		
		Box box_username = new Box(BoxLayout.Y_AXIS);
		box_username.add(lbl_username);
		box_username.add(Box.createVerticalStrut(15));
		box_username.add(this.txt_username);
		
		Box box_password = new Box(BoxLayout.Y_AXIS);
		box_password.add(lbl_password);
		box_password.add(Box.createVerticalStrut(15));
		box_password.add(this.txt_password);
		
		Box cadre = new Box(BoxLayout.Y_AXIS);
		cadre.add(box_username);
		cadre.add(Box.createVerticalStrut(30));
		cadre.add(box_password);
		cadre.add(Box.createVerticalStrut(30));
		cadre.add(this.btn_creation);
		
		panel.add(cadre);
		this.add(panel);
		this.setResizable(false);
		this.pack();
		this.setVisible(true);
	}

	
	private class CreationUtilisateurController implements ActionListener{
//		private static DB db = new DB();
		
		@Override
		public void actionPerformed(ActionEvent evt) {
			String nom = txt_username.getText();
			String mdp = txt_password.getText();
			int r = requete.insertionUtilisateur(nom, mdp, ROLE_USER);
			if(r == 1)
				JOptionPane.showMessageDialog(null, "Creation Utilisateur", "Utilisateur créé", JOptionPane.INFORMATION_MESSAGE);
			else
				JOptionPane.showMessageDialog(null, "Creation Utilisateur", "Impossible de créer l'utilisateur", JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
}
