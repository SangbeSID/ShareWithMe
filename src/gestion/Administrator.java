package gestion;

public class Administrator extends Profile implements AdminMethods {
	
	public Administrator(String n, String p, Integer r) {
		super(n, p, r);
	}
	
	@Override
	public void createProfile(String login, String password, Integer role) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteProfile(Profile p) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAnnouncement(Annonce a) {		

	}
	
	
	@Override
	public void updateAnnouncement(Annonce a) {
		// TODO Auto-generated method stub
		
	}

}
