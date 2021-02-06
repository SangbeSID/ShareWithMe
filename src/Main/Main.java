package Main;

import DB.Requete;
import IHM.CreationAnnonce;
import IHM.CreationUtilisateur;
import IHM.Login;
import IHM.MyFrame;
import IHM.VueAdministrateur;
import IHM.VueUtilisateur;
import gestion.Profile;
import gestion.User;

public class Main {

	public static void main(String[] args) {
		
		Login l = new Login("LOGIN");
		VueAdministrateur v1 = new VueAdministrateur("ADMIN");
		//CreationUtilisateur c = new CreationUtilisateur();
		//Requete r = new Requete();
		//System.out.println(r.getTousLesUtilisateurs()[1].getNom());
//		VueUtilisateur v = new VueUtilisateur(new Profile("test", "test"));
		//CreationAnnonce a = new CreationAnnonce(new Utilisateur("test", "test"));
		//System.out.println(r.getAnnonces(new Utilisateur("test", "test"))[0]);
	}

}
