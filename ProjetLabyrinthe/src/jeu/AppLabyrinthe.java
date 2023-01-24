package jeu;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import modele.Aventurier;
import modele.Espace;
import modele.Labyrinthe;
import modele.Mur;
import modele.Objet;
import vue.Vue;


/**
 * Application de l'aventurier et du labyrinthe.
 * 
 * Cette classe permet de charger le jeu et de le lancer.
 */
public class AppLabyrinthe
{
	/**
	 * Noms des fichiers contenant des labyrinthes.
	 */
	private static final String LAB_1 = "data" + File.separator + "labyrinthe_1(1).csv";
	private static final String LAB_2 = "data" + File.separator + "labyrinthe_2.csv";


	/**
	 * Chargement du labyrinthe et de l'aventurier.
	 *
	 * A partir d'un fichier csv, cette méthode crée le labyrinthe et
	 * l'aventurier sur la case de départ du labyrinthe.
	 */
	public static Labyrinthe chargeLabyrinthe (String fichier)
		{
		Labyrinthe laby = null;

		try (Scanner scanner = new Scanner(new FileInputStream(fichier)))
			{
			/*----- Lecture de la taille du labyrinthe -----*/
			int taille = Integer.valueOf(scanner.nextLine());

			/*----- Initialisation du labyrinthe -----*/
			laby = new Labyrinthe(taille);

			/*----- Lecture du fichier et des types de cases composant le labyrinthe -----*/
			for (int i=0; i<taille; i++)
				{
				/*----- Lecture d'une ligne du fichier -----*/
				String[] liste = scanner.nextLine().trim().split(";");

				int type_case;
				for (int j=0; j<taille; j++)
					{
					type_case = Integer.valueOf(liste[j]);

					/*----- Type 0 --> "Prairie sans objet" -----*/
					if (type_case == 0) laby.setCase(i, j, new Espace("Prairie", true, false));

					/*----- Type 1 --> "Mur" -----*/
					if (type_case == 1) laby.setCase(i, j, new Mur());

					/*----- Type 2 --> "Porte" -----*/
					if (type_case == 2) laby.setCase(i, j, new Espace("Porte", false, true));
					/*----- Type 3 --> "Clé et prairie" -----*/
					if (type_case == 3) {
						laby.setCase(i, j, new Espace("Prairie", true, true));
						laby.setObjet(i, j, new Objet("Clé", "Ouvrir la porte"));
					}
					/*----- Type 4 --> "Mer" -----*/
					if (type_case == 4) laby.setCase(i, j, new Espace("Mer", true, false));
					/*----- Type 5 --> "Mer et épée" -----*/
					if (type_case == 5) {
						laby.setCase(i, j, new Espace("Mer", true, true));
						laby.setObjet(i, j, new Objet("Epée", "Tuer le minotaure"));
					}
					/*----- Type 6 --> "Mer et sirene" -----*/
					if (type_case == 6) {
						laby.setCase(i, j, new Espace("Mer", false, true));
						laby.setObjet(i, j, new Objet("Sirene", "Réduit les points de vie"));
					}
					/*----- Type 7 --> "Prairie et minotaure" -----*/
					if (type_case == 7) {
						laby.setCase(i, j, new Espace("Prairie", false, true));
						laby.setObjet(i, j, new Objet("Minotaure", "Tente de tuer l'aventurier. Sans être équipé d'épée, le minotaure tue l'aventurier. À sa mort, la reine est libérée."));
					}
					/*----- Type 8 --> "Prairie et écouteur" -----*/
					if (type_case == 8) {
						laby.setCase(i, j, new Espace("Prairie", true, true));
						laby.setObjet(i, j, new Objet("Ecouteur", "Réduire le dommage de la sirène"));
					}
					/*----- Type 9 --> "Espace de départ" et "Aventurier" -----*/
					if (type_case == 9)
						{
						laby.setCase(i, j, new Espace("Prairie", true, false));
						laby.setAventurier(new Aventurier(i, j, modele.Aventurier.PT_VIE_DEPART, modele.Aventurier.INVENTAIRE_DEPART));
						}
					/*----- Type 10 --> "Désert" -----*/
					if (type_case == 10) laby.setCase(i, j, new Espace("Désert", true, false));
					/*----- Type 11 --> "Désert et coffre" -----*/
					if (type_case == 11) {
						laby.setCase(i, j, new Espace("Désert", true, true));
						laby.setObjet(i, j, new Objet("Coffre", "Permettra de passer les gendarmes"));
					}
					/*----- Type 12 --> "Prairie et gendarmes" -----*/
					if (type_case == 12) {
						laby.setCase(i, j, new Espace("Prairie", false, true));
						laby.setObjet(i, j, new Objet("Gendarme", "Ne vont pas laisser passer sans pot-de-vin"));
					}
					/*----- Type 13 --> "Prairie et reine" -----*/
					if (type_case == 13) {
						laby.setCase(i, j, new Espace("Prairie", false, true));
						laby.setObjet(i, j, new Objet("Reine", "Prisonière du minotaure."));
					}
					/*----- Type 14 --> "Prairie et roi" -----*/
					if (type_case == 14) {
						laby.setCase(i, j, new Espace("Prairie", false, true));
						laby.setObjet(i, j, new Objet("Roi", "Donne la clé de la porte en échange de la reine."));
					}
					/*----- Type 15 --> "Prairie et monstre" -----*/
					if (type_case == 15) {
						laby.setCase(i, j, new Espace("Prairie", true, true));
						laby.setObjet(i, j, new Objet("Monstre", "Si l'aventurier le touche, il réduit les points de vie."));
					}
					}
				}
			}
		catch (FileNotFoundException ex)
			{
			System.err.println("Erreur lors de la lecture du fichier : " + fichier + " - " + ex.getMessage());
			}

		return laby;
		}


	/*---------------------*/
	/* Programme principal */
	/*---------------------*/

	public static void main (String[] s) throws InterruptedException
		{
		/*----- Chargement du labyrinthe -----*/
		Labyrinthe labyrinthe = chargeLabyrinthe(LAB_1);

		/*----- Création de la fenêtre de visualisation du labyrinthe et affichage -----*/
		new Vue(100, 100, labyrinthe);
		}

} /*----- Fin de ma classe AppLabyrinthe -----*/
