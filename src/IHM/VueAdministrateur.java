package IHM;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import DB.Requete;
import gestion.Annonce;
import gestion.Profile;


public class VueAdministrateur extends MyFrame{

	private JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
	private JList<Profile> listeUtilisateurs; 
	private JList<Annonce> listeAnnonces; 
	private MyButton btn_supprimer = new MyButton("Supprimer");
	private MyButton btn_modifier = new MyButton("Modifier");
	private MyButton btn_utilisateur = new MyButton("Utilisateurs");
	private MyButton btn_annonce = new MyButton("Annonces");
	private MyButton btn_creer = new MyButton("Créer Utilisateur");
	private JScrollPane scrollPane; // Fenetre deroulante qui contiendra les infos.
	private Requete requete;
	
	public VueAdministrateur(String title) {
		super(title);
		this.setResizable(false);
		this.requete = new Requete();
		
		this.listeUtilisateurs = new JList<Profile>(new DefaultListModel<Profile>());
		this.listeUtilisateurs.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		this.listeAnnonces = new JList<Annonce>(new DefaultListModel<Annonce>());
		this.listeAnnonces.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		this.scrollPane = new JScrollPane();
		this.scrollPane.setPreferredSize(new Dimension(500 , 500));
		
		// ======== ABONNEMENT DES BOUTONS ET LISTES
		this.btn_creer.addActionListener(new VueAdminControler());
		this.btn_utilisateur.addActionListener(new VueAdminControler());
		this.btn_annonce.addActionListener(new VueAdminControler());
		this.listeAnnonces.addListSelectionListener(new VueAdminControler());
		this.listeUtilisateurs.addListSelectionListener(new VueAdminControler());
		
		// ======= ZONE DE GAUCHE
		JPanel panel_left= new JPanel(new FlowLayout(FlowLayout.CENTER));
		Box box_left = new Box(BoxLayout.Y_AXIS);
		box_left.add(this.btn_creer);
		box_left.add(Box.createVerticalStrut(15));
		box_left.add(this.btn_modifier);
		box_left.add(Box.createVerticalStrut(15));
		box_left.add(this.btn_supprimer);
		panel_left.add(box_left);
		
		// ======== ZONE DE DROITE
		JPanel panel_liste= new JPanel(new FlowLayout(FlowLayout.CENTER));
		Box box_liste = new Box(BoxLayout.Y_AXIS);
		box_liste.add(this.btn_utilisateur);
		box_liste.add(Box.createVerticalStrut(15));
		box_liste.add(this.btn_annonce);
		panel_liste.add(box_liste);
		
		
		// ======= REMPLISSAGE DE LA FENETRE
		this.panel.add(this.scrollPane);
		this.add(this.panel);
		this.add(panel_liste, BorderLayout.EAST);
		this.add(panel_left, BorderLayout.WEST);
		this.setVisible(true);
		this.pack();
	}
	
	
	/**
	 * 
	 * Le controller
	 *
	 */
	private class VueAdminControler implements ActionListener, ListSelectionListener{

		@Override
		public void valueChanged(ListSelectionEvent evt) {
			if(evt.getSource() == listeUtilisateurs) {
				System.out.println("Liste User");
			}
			
			if(evt.getSource() == listeAnnonces) {
				System.out.println("Liste Annonce");
			}
		}

		@Override
		public void actionPerformed(ActionEvent evt) {
			
			this.refresh((MyButton) evt.getSource());			
			
			// Si l'on clique sur le bouton "creer" on affiche
			// la fenetre permettant de creer des utilisateurs
			if(evt.getSource() == btn_creer) {
				CreationUtilisateur c = new CreationUtilisateur();
			}
		}
		
		/**
		 * Methode utilisee pour modifier la liste des elements a afficher.
		 * SI l'on clique sur le btn "utilisateur", on affiche la liste des utilisateurs.
		 * Si l'on clique sur la le btn "annonces", on affiche la liste des annonces.
		 */
		private void refresh(MyButton btn) {
			if(btn == VueAdministrateur.this.btn_utilisateur) { // Si l'on clique sur le bouton utilisateur
				listeUtilisateurs.setListData(requete.getTousLesUtilisateurs());
				scrollPane.getViewport().setView(listeUtilisateurs);
			}
			else if(btn == VueAdministrateur.this.btn_annonce) { // Si l'on clique sur le bouton annonces
				listeAnnonces.setListData(requete.getToutesLesAnnonces());
				scrollPane.getViewport().setView(listeAnnonces);
			}
			panel.repaint();
		}
		
	}
}
