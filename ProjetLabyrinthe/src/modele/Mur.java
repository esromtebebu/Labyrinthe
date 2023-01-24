package modele;


/**
 * Classe qui représente un mur.
 */
public class Mur extends Case
{
	//Les propriétés :
		private String nomTypeCase;
		private boolean estFranchissable;
		private boolean contientObjet;
		
	//Constructeur :
	public Mur() {
		this.nomTypeCase = "mur";
		this.estFranchissable = false;
		this.contientObjet = false;
	}
	/**
	 * Méthode qui définit une action / un changement sur l'aventurier.
	 */
	@Override
	public void action (Aventurier a)
		{
		// Code à compléter !
		System.out.println("Je suis un " + this.nomTypeCase + " !");
		}
	@Override
	public String getNomTypeCase() {
		return this.nomTypeCase;
	}
	@Override
	public boolean getEstFranchissable() {
		return this.estFranchissable;
	}
	@Override
	public boolean getContientObjet() {
		return this.contientObjet;
	}
	@Override
	public void setNomTypeCase(String nomTypeCase) {
		this.nomTypeCase = nomTypeCase;
	}
	@Override
	public void setEstFranchissable(boolean estFranchissable) {
		this.estFranchissable = estFranchissable;
	}
	@Override
	public void setContientObjet(boolean contientObjet) {
		this.contientObjet = contientObjet;
	}

} /*----- Fin de la classe Mur -----*/
