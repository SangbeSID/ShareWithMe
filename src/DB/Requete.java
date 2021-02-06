package DB;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JList;
import javax.swing.JOptionPane;

import gestion.Annonce;
import gestion.Profile;
import gestion.User;


public class Requete {
	private static MySystem db;
	
	public Requete() {
		this.db = new MySystem();
	}
	
	// ========================================================================================================
	/**
	 * Message d'erreur permettant de remonter a la source du probleme.
	 * @param sqlEx : Exception de type SQL generee
	 */
	private static void messageErreur(SQLException sqlEx) {
		try {
            System.out.println("SQLException occured. getErrorCode=> " + sqlEx.getErrorCode());
            System.out.println("SQLException occured. getCause=> " + sqlEx.getSQLState());
            System.out.println("SQLException occured. getCause=> " + sqlEx.getCause() );
            System.out.println("SQLException occured. getMessage=> " + sqlEx.getMessage());
        } 	          
        catch (Exception e) {  } finally {  }
	}
	// ========================================================================================================
	/**
	 * Insertion d'un utilisateur.
	 * @param nom  	: Nom de l'utilisateur
	 * @param mdp	: Mot de passe de l'utilisateur
	 * @param role	: Role de l'utilisateur
	 * @return		: 1 si l'insertion a ete effectuee et 0 sinon
	 */
	public static int insertionUtilisateur(String nom, String mdp, int role){
		//String sql = "INSERT INTO sidibesa.INFO742_Users" + "(nom, mdp) VALUES" + "(" + nom + "," + mdp +");";
		String sql = "INSERT INTO sidibesa.INFO742_Users(nom, mdp, role) VALUES (?, ?, ?)";
		try {
			PreparedStatement pstmt = db.conn.prepareStatement(sql);
			pstmt.setString(1, nom);
			pstmt.setString(2, mdp);
			pstmt.setInt(3, role);
			pstmt.execute();
			return 1;
		}catch (SQLException sqlEx) {
	       messageErreur(sqlEx);
		}
		return 0;
	}
	
	
	// ========================================================================================================
	/**
	 * Insertion d'annonce.
	 * @param a : Annonce a inserer
	 * @param u : Auteur de l'annonce
	 * @return	: 1 si l'insertion a ete effectuee et 0 sinon.
	 */
	public static int insertionAnnonce(Annonce a, Profile u) {
		String sql = "INSERT INTO sidibesa.INFO742_Annonces(titre, description, date, auteur) VALUES (?,?,?,?)";
		try {
			PreparedStatement pstmt = db.conn.prepareStatement(sql);
			pstmt.setString(1, a.getTitre());
			pstmt.setString(2, a.getDescription());
			pstmt.setString(3, a.getDate());
			pstmt.setString(4, u.getNom());
			pstmt.execute();
			return 1;
		}catch (SQLException sqlEx) {
	       messageErreur(sqlEx);
		}
		return 0;
	}
	
	
	// ========================================================================================================
	/**
	 * Suppression d'une annonce.
	 * @param a : Annonce a supprimer
	 * @return	: 1 si la suppression a ete effectuee et 0 sinon
	 */
	public static int supprimerAnnonce(Annonce a) {
		String sql = "DELETE * FROM sidibesa.INFO742_Annonces where titre = ? and description = ? and date = ?";
		try {
			PreparedStatement pstmt = db.conn.prepareStatement(sql);
			pstmt.setString(1, a.getTitre());
			pstmt.setString(2, a.getDescription());
			pstmt.setString(3, a.getDate());
			pstmt.execute();
			return 1;
		}catch (SQLException sqlEx) {
	        messageErreur(sqlEx);
		}
		return 0;
	}
	
	// ========================================================================================================
	/**
	 * Recherche Utilisateur.
	 * @param nom : Nom de l'utilisateur
	 * @param mdp : Mot de passe de l'utilisateur
	 * @return : L'utilisatuer correspondant s'il existe et null sinon.
	 */
	public static Profile estPresent(String nom, String mdp) {
		String sql = "SELECT * from sidibesa.INFO742_Users where nom = ? and mdp = ?";
		ResultSet rs; // Contiendra le resultat de la requete
		
		try {
			PreparedStatement pstmt = db.conn.prepareStatement(sql);
			pstmt.setString(1, nom);
			pstmt.setString(2, mdp);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				int id = rs.getInt("ID"); // Will be used next time
				String name = rs.getString("nom");
				String password = rs.getString("mdp");
				int role = rs.getInt("role"); // Will be used next time
				return new User(name, password, role);
			}
		} catch (SQLException sqlEx) {
			messageErreur(sqlEx);
		}
		return null;
	}
	
	
	// ========================================================================================================
	/**
	 * Recuperer Toutes les annonces de la base de donnees.
	 * @return : Toutes les annonces qui se trouvent dans la BDD.
	 */
	public static Annonce[] getToutesLesAnnonces(){
		String sql = "SELECT * from sidibesa.INFO742_Annonces";
		ResultSet rs;
		Annonce[] resultat = null;
		int i = 0;
		try {
			Statement stmt = db.conn.createStatement();
			rs = stmt.executeQuery(sql);
			boolean b = rs.last();
			int s = rs.getRow();
			rs.beforeFirst();
			resultat = new Annonce[s];
			while(rs.next()) {
				Annonce a = new Annonce(rs.getString("titre"), rs.getString("description"), rs.getString("date"), rs.getString("auteur"));
				resultat[i] = a;
				i++;
			}
		} catch (SQLException sqlEx) {
			messageErreur(sqlEx);
		}		
		return resultat;
	}
	
	
	// ========================================================================================================
	/**
	 * Recuperer Tous les utilisateurs de la base de donnees.
	 */
	public static Profile[] getTousLesUtilisateurs(){
		String sql = "SELECT nom, mdp from sidibesa.INFO742_Users ORDER BY nom";
		ResultSet rs;
		int i = 0;
		Profile[] resultat = null;
		
		try {
			Statement stmt = db.conn.createStatement();			
			rs = stmt.executeQuery(sql); // Execution de la requete
			
			boolean b = rs.last(); // Le curseur est mis sur la derniere ligne
			int s = rs.getRow(); // Le numero de la ligne est recupere
			resultat = new Profile[s];	// creation du tableau d'utilisateur
			
			rs.beforeFirst(); // Le curseur est mis devant le premier element
			
			while(rs.next()) {
				Profile a = new User(rs.getString("nom"), rs.getString("mdp"), rs.getInt("role"));
				resultat[i] = a;
				i++;			
			}
						
		} catch (SQLException sqlEx) {
			messageErreur(sqlEx);
		}
		return resultat;
	}
	
	
	// ========================================================================================================
	/**
	 * Recuperer toutes les annonces d'un utilisateur.
	 * @param utilsateur : Utilisateur dont on veut recuperer les annonces publiees
	 * @return 			 : Toutes les annonces publiees
	 */
	public static Annonce[] getAnnonces(Profile utilisateur) {
		Annonce[] resultat = null;
		int i = 0;
		String sql = "SELECT titre, description, date FROM sidibesa.INFO742_Annonces WHERE auteur = ?";
		try {
			PreparedStatement pstmt = db.conn.prepareStatement(sql);
			pstmt.setString(1, utilisateur.getNom());
			ResultSet rs = pstmt.executeQuery();

			boolean b = rs.last();
			int s = rs.getRow();
			rs.beforeFirst();
			resultat = new Annonce[s];

			while(rs.next()) {
				resultat[i] = new Annonce(rs.getString("titre"),rs.getString("description"), rs.getString("date"), rs.getString("auteur"));
				i++;
			}
		}catch (SQLException sqlEx) {
			messageErreur(sqlEx);
		}
		return resultat;
	}
		
}
