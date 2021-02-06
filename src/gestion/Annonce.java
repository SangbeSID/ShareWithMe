package gestion;


public class Annonce {
	private String titre;
	private String description;
	private String date; 	
	private Integer dureeDePret;
	private String auteur;
	
	public Annonce(String t, String desc, String date, String a) {
		this.titre = t;
		this.description = desc;		
		this.date = date;
		this.auteur = a;
	}
	
	
	public String getTitre() {
		return this.titre;
	}
	
	
	public String getDescription() {
		return this.description;
	}
	
	
	public String getDate() {
		return this.date;
	}
	
	
	public String getAuteur() {
		return this.auteur;
	}
	
	
	public int getDureePret() {
		return this.dureeDePret;
	}
	
	
	@Override
	public String toString() {
		return this.titre;
	}
}
