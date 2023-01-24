package modele;

/**
 * Classe qui représente une case du labyrinthe.
 */
public abstract class Case
{
	/**
	 * Méthode qui retourne le nom de la classe de l'objet.
	 */
	public final String getClassName() { return this.getClass().getSimpleName(); }
	
	//Setters et getters :
	
	public abstract String getNomTypeCase();
	public abstract boolean getEstFranchissable();
	public abstract boolean getContientObjet();
	public abstract void setNomTypeCase(String nomTypeCase);
	public abstract void setEstFranchissable(boolean estFranchissable);
	public abstract void setContientObjet(boolean contientObjet);


	/**
	 * Méthode abstraite qui permettra de définir une action / un changement
	 * sur l'aventurier.
	 */
	public abstract void action (Aventurier a);

} /*----- Fin de la classe Case -----*/
