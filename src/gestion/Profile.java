package gestion;

import java.util.ArrayList;

import DB.Requete;

public abstract class Profile {
	private String nom;
	private String mdp;
	private int role;
	private ArrayList<Annonce> annoncesCreees = new ArrayList<Annonce>();
	private Requete req;
	
	/**
	 * 
	 * @param n
	 * @param mdp
	 */
	public Profile(String n, String mdp) {
		this.nom = n;
		this.mdp = mdp;
	}
	
	
	public Profile(String n, String mdp, int role) {
		this.nom = n;
		this.mdp = mdp;
		this.role = role;
	}
	
	
	public void setSystem(Requete r) {
		if(r != null)
			this.req = r;
	}
	
	
	public int getRole() {
		return this.role;
	}
	
	
	public String getNom() {
		return this.nom;
	}
	
	
	@Override
	public String toString() {
		return this.nom;
	}
	
	
	public void addAnnonce(Annonce a) {
		if(a != null)
			this.annoncesCreees.add(a);
	}
	
	
	public void removeAnnonce(Annonce a) {
		if(this.annoncesCreees.contains(a))
			this.annoncesCreees.remove(a);
	}
	
	
	public int submitAnnouncement(String t, String desc, String d) {
		int r = 0;
		if(!t.isEmpty() && !desc.isEmpty() && !d.isEmpty()) {
			Annonce a = new Annonce(t, desc, d, this.getNom());
			r = this.req.insertionAnnonce(a, this);
			if(r == 1)
				this.addAnnonce(a);			
		}
		return r;
	}
	
	abstract public void updateAnnouncement(Annonce a);
}
