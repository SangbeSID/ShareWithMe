package gestion;

public class User extends Profile implements UserMethods {

	public Administrator admin;
	
	public User(String n, String mdp, int role) {
		super(n, mdp, role);
		// TODO Auto-generated constructor stub
	}

	
	public void setAdmin(Administrator a) {
		if(a != null)
			this.admin = a;
	}
	
	
	@Override
	public void deleteAnnoucement(Annonce a) {
		if(this.getNom().equals(a.getAuteur()) && a != null)
			this.admin.deleteAnnouncement(a);
	}

	
	@Override
	public void deleteProfile(Profile p) {
		if(p != null)
			this.admin.deleteProfile(p);
	}

	
	@Override
	public void updateAnnouncement(Annonce a) {
		// TODO Auto-generated method stub
	}

}
