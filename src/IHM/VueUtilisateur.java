package IHM;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import DB.Requete;
import gestion.Annonce;
import gestion.Profile;

public class VueUtilisateur extends MyFrame{
	private Profile utilisateur; // L'utilisateur courant
	private JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
	private JList<Annonce> listeAnnonces; // Liste dans laquelle les annonces seront mises
	private MyButton btn_supprimer = new MyButton("Supprimer");
	private MyButton btn_modifier = new MyButton("modifier");
	private MyButton btn_toutesLesAnnonces = new MyButton("Toutes Les Annonces");
	private MyButton btn_annoncesCreees = new MyButton("Annonces créées");
	private MyButton btn_creer = new MyButton("Créer Annonce");	
	//private JScrollPane scrollPane = new JScrollPane(); // Fenetre deroulante qui contiendra les infos.
	private Requete requete;
	
	public VueUtilisateur(Profile utilisateur) {
		super("SESSION ==> " + utilisateur.getNom());
		this.utilisateur = utilisateur;
		
		this.setResizable(false); // Desactivation du redimensionnement de la fenetre
		this.requete = new Requete();
		
		this.listeAnnonces = new JList<Annonce>(new DefaultListModel<Annonce>());
		this.listeAnnonces.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.listeAnnonces.setPreferredSize(new Dimension(500 , 500));	
		
		// ========= Remplisage des listes d'utilisateurs et des annonces
		//this.listeAnnonces.setListData(this.requete.getToutesLesAnnonces());
		
		// ======== Abonnement des boutons 
		this.btn_toutesLesAnnonces.addActionListener(new VueUtilisateurController());
		this.btn_annoncesCreees.addActionListener(new VueUtilisateurController());
		this.btn_creer.addActionListener(new VueUtilisateurController());
		
		
		
		// ======= ZONE DU BAS (DES BOUTONS)
		JPanel panel_boutons= new JPanel(new FlowLayout(FlowLayout.CENTER));
		Box box_boutons = new Box(BoxLayout.X_AXIS);
		box_boutons.add(this.btn_creer);
		box_boutons.add(Box.createHorizontalStrut(15));
		box_boutons.add(this.btn_modifier);
		box_boutons.add(Box.createHorizontalStrut(15));
		box_boutons.add(this.btn_supprimer);
		box_boutons.add(Box.createHorizontalStrut(15));
		box_boutons.add(this.btn_annoncesCreees);
		box_boutons.add(Box.createHorizontalStrut(15));
		box_boutons.add(this.btn_toutesLesAnnonces);
		panel_boutons.add(box_boutons);		
		
		// ====== REMPLISSAGE DE LA FENETRE
		this.panel.add(new JScrollPane(this.listeAnnonces));
		this.add(this.panel);		
		this.add(panel_boutons, BorderLayout.NORTH);
		this.setVisible(true);
		this.pack();
	}
	
	/**
	 * Le controller
	 */
	private class VueUtilisateurController implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent evt) {
			// ====== Lorsque l'on clique sur le bouton "Toutes les annonces"
			if(evt.getSource() == btn_toutesLesAnnonces) {
				listeAnnonces.setListData(requete.getToutesLesAnnonces());
				panel.repaint();
			}
			
			// ===== Lorsque l'onn clique sur le bouton "Annonces crees"
			if(evt.getSource() == btn_annoncesCreees) {
				listeAnnonces.setListData(requete.getAnnonces(utilisateur));
				panel.repaint();
			}
			
			// ====== Lorsque l'on clique sur le bouton "Creer Annonce"
			if(evt.getSource() == btn_creer) {
				CreationAnnonce c = new CreationAnnonce(utilisateur);
			}
		}
		
	}
}
