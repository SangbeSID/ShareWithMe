package IHM;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import DB.Requete;
import gestion.Profile;

public class Login extends MyFrame{
	private JPanel panel = new JPanel();
	private JTextField txt_username = new JTextField(25);
	private JPasswordField txt_password = new JPasswordField(25);
	private MyButton btn_connexion = new MyButton("Connexion");
	private Requete requete;
	
	public Login(String title) {
		super(title);
		this.requete = new Requete();
		
		JLabel lbl_username = new JLabel("UTILISATEUR");
		JLabel lbl_password = new JLabel("MOT DE PASSE");
		
		this.btn_connexion.addActionListener(new LoginController());
			
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
		cadre.add(this.btn_connexion);
		
		panel.add(cadre);
		this.add(panel);
		this.setResizable(false);
		this.pack();
		this.setVisible(true);
	}
	
	
	public MyButton getBoutonConnexion() {
		return this.btn_connexion;
	}
	
	
	public String getNomUtilisateur() {
		return this.txt_username.getText();
	}
	
	
	public String getMotDePasse() {
		return this.txt_password.getText();
	}
	
	
	/**
	 * 
	 * Le Controller.
	 *
	 */
	private class LoginController implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent evt) {
			Profile u = requete.estPresent(txt_username.getText(), txt_password.getText());	
			if(u != null) { // Il faudra tenir compte du role pour la suite.
				if(u.getRole() == 0) {
					VueUtilisateur v = new VueUtilisateur(u);
				}
				else {
					VueAdministrateur v = new VueAdministrateur(u.getNom());
				}
			}
			else {
				JOptionPane.showMessageDialog(null, "Utilisateur inexistant", "Connexion", JOptionPane.ERROR_MESSAGE);
			}
			txt_username.setText("");
			txt_password.setText("");
		}
		
		
	}
}
