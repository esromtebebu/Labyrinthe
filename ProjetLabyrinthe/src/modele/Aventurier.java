package modele;

import java.util.ArrayList;


/**
 * Classe qui représente l'aventurier qui traverse le labyrinthe.
 */
public class Aventurier
{
	/*------------*/
	/* Propriétés */
	/*------------*/
	public static final int PT_VIE_DEPART = 10000;
	public static final ArrayList<Objet> INVENTAIRE_DEPART = new ArrayList<Objet>();
	private int ptVie;
	private ArrayList<Objet> inventaire = new ArrayList<Objet>();
	/*----- Sa position dans le labyrinthe -----*/
	private int x;
	private int y;
	private int[] positionAventurier = new int[2];
	private boolean isAlive;
	/*--------------*/
	/* Constructeur */
	/*--------------*/

	public Aventurier (int x, int y, int ptVie, ArrayList<Objet> inventaire)
		{
		this.x = x;
		this.y = y;
		this.ptVie = ptVie;
		this.inventaire = inventaire;
		this.positionAventurier[0] = this.x;
		this.positionAventurier[1] = this.y;
		}


	/*----------*/
	/* Méthodes */
	/*----------*/

	public int getX () { return this.x; }
	public void setX (int x) { this.x = x; }

	public int getY () { return this.y; }
	public void setY (int y) { this.y = y; }
	
	public int getPtVie() { return this.ptVie; }
	public void setPtVie (int ptVie) {
		if (ptVie > PT_VIE_DEPART) {
			this.ptVie = PT_VIE_DEPART;
		}
		this.ptVie = ptVie;
	}
	
	public ArrayList<Objet> getInventaire() { return this.inventaire; }
	public void setInventaire(Objet o) { this.inventaire.add(o); }
	
	public void setPositionAventurier(int i, int j) { this.positionAventurier[0] = i; this.positionAventurier[1] = j; }
	public int[] getPositionAventurier() { return this.positionAventurier; }

} /*----- Fin de la classe Aventurier -----*/
