package IHM;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import DB.Requete;
import gestion.Annonce;
import gestion.Profile;

public class CreationAnnonce extends MyFrame{
	private Profile utilisateur; // Utilisateur qui cree l'annonce
	private Requete requete;
	private JTextField txt_titre = new JTextField(50);
	private JTextArea txt_description = new JTextArea(25, 25);
	private MyButton btn_creer = new MyButton("Créer");
	private MyButton btn_effacer = new MyButton("Effacer");
	private JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		
	public CreationAnnonce(Profile u) {
		super("CREATION ANNONCE | AUTEUR : " + u.getNom());
		this.utilisateur = u;
		this.requete = new Requete();
		
		JLabel lbl_titre = new JLabel("Titre");
		JLabel lbl_description = new JLabel("Description");
		
		// ======== ABONNEMENT DES BOUTONS
		this.btn_creer.addActionListener(new CreationAnnonceController());
		this.btn_effacer.addActionListener(new CreationAnnonceController());
		
		// ======== TITRE
		Box box_titre = new Box(BoxLayout.X_AXIS);
		box_titre.add(lbl_titre);
		box_titre.add(Box.createHorizontalStrut(10));
		box_titre.add(this.txt_titre);
		
		// ======== DESCRIPTION
		Box box_description = new Box(BoxLayout.X_AXIS);
		box_description.add(lbl_description);
		box_description.add(Box.createHorizontalStrut(10));
		box_description.add(this.txt_description);
		
		// ======== BOUTONS
		Box box_bouton = new Box(BoxLayout.Y_AXIS);
		box_bouton.add(this.btn_creer);
		box_bouton.add(Box.createVerticalStrut(15));
		box_bouton.add(this.btn_effacer);
		
		// ======= REMPLISSAGE DE LA FENETRE
		Box cadre = new Box(BoxLayout.Y_AXIS);
		cadre.add(box_titre);
		cadre.add(Box.createVerticalStrut(10));
		cadre.add(box_description);
		
		this.panel.add(cadre);
		this.panel.add(box_bouton, BorderLayout.SOUTH);
		this.add(this.panel);
		this.pack();
		this.setVisible(true);
	}
	
	public String getTitre() {
		return this.txt_titre.getText();
	}
	
	public String getDescription() {
		return this.txt_description.getText();
	}
	
	
	/**
	 * Le Controller.
	 */
	private class CreationAnnonceController implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent evt) {
			
			// ====== Lorsque l'on clique sur le bouton "Creer"
			if(evt.getSource() == btn_creer) {
				if(!txt_titre.getText().isEmpty() && !txt_description.getText().isEmpty()) {
					LocalDateTime d = LocalDateTime.now(); // On recupere la date du jour.
					DateTimeFormatter myFormat  =DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
					int r = utilisateur.submitAnnouncement(txt_titre.getText(), txt_description.getText(), d.format(myFormat));
					//Annonce a = new Annonce(txt_titre.getText(), txt_description.getText(), d.format(myFormat), utilisateur.getNom());
					//int r = requete.insertionAnnonce(a, utilisateur);
					if(r == 1) {
						JOptionPane.showMessageDialog(null, "Création annonce", "Annonce créée", JOptionPane.INFORMATION_MESSAGE);
						CreationAnnonce.this.dispose();
					}
					else
						JOptionPane.showMessageDialog(null, "Création annonce", "Impossible de créer l'annonce", JOptionPane.ERROR_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(null, "Titre ou description vide", "Creation Annonce", ERROR);
				}
			}
			
			// ====== Lorsque l'on clique sur le bouton "Effacer"
			if(evt.getSource() == btn_effacer) {
				txt_titre.setText(null);
				txt_description.setText(null);
			}
		}
		
	}
}
