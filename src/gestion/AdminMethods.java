package gestion;

public interface AdminMethods {
	public void createProfile(String login, String password, Integer role);
	public void deleteProfile(Profile p);
	public void deleteAnnouncement(Annonce a);
}
