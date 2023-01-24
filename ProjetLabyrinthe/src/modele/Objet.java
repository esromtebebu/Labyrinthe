package modele;

public class Objet {
	/*
	 * Les propriétés :
	 */
	private String nomObjet;
	private String typeAction;
	
	/*
	 * Le constructeur :
	 */
	
	public Objet(String nomObjet, String typeAction) {
		this.nomObjet = nomObjet;
		this.typeAction = typeAction;
	}
	
	/*
	 * Les méthodes :
	 */
	public String toString() {
		return this.nomObjet;
	}
	
	public void setNomObjet(String nomObjet) { this.nomObjet = nomObjet; }
	public String getNomObjet() { return this.nomObjet; }
	
	public void setTypeAction(String typeAction) { this.typeAction = typeAction; }
	public String getTypeAction() { return this.typeAction; }

}
