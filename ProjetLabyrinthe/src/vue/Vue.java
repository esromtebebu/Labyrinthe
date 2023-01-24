package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import modele.Aventurier;
import modele.Labyrinthe;
import modele.Objet;
import modele.Espace;


/**
 * Fenêtre de visualisation du labyrinthe.
 */
public class Vue extends JFrame
{
	/*------------*/
	/* Propriétés */
	/*------------*/

	/**
	 * Référence vers le labyrinthe que la classe Vue va visualiser.
	 */
	private final Labyrinthe labyrinthe;

	/*----- Barre d'état de la fenêtre -----*/
	private final JLabel barre_etat;

	/*----- Zone de dessin -----*/
	Dessin dessin;


	/*--------------*/
	/* Constructeur */
	/*--------------*/

	public Vue (int x, int y, Labyrinthe labyrinthe)
		{
		/*----- Lien avec le labyrinthe -----*/
		this.labyrinthe = labyrinthe;

		/*----- Paramètres de la fenêtre -----*/
		this.setTitle("Labyrinthe");
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocation(x,y);
		this.setLayout(new BorderLayout());

		/*----- Zone de dessin -----*/
		this.dessin = new Dessin(600);
		this.dessin.setFocusable(true);

		/*----- Attachement des écouteurs des évènements souris et clavier -----*/
		this.dessin.addMouseListener(this.dessin);
		this.dessin.addMouseMotionListener(this.dessin);
		this.dessin.addKeyListener(this.dessin);
		this.add(this.dessin, BorderLayout.CENTER);

		/*----- Barre d'état de la fenêtre -----*/
		this.barre_etat = new JLabel("Barre d'état");
		this.add(this.barre_etat, BorderLayout.SOUTH);

		/*----- Pour ajuster la fenêtre à son contenu et la rendre visible -----*/
		this.pack();
		this.setVisible(true);
		}


	/*----------------*/
	/* Classe interne */
	/*----------------*/

	class Dessin extends JPanel implements KeyListener, MouseListener, MouseMotionListener
		{
		/*----- Propriétés de la classe interne -----*/
		int largeur;
		int taille_case;

		/*----- Constructeur de la classe interne -----*/
		public Dessin (int larg)
			{
			/*----- Initialisation des données -----*/
			this.taille_case = larg / labyrinthe.getTaille();
			this.largeur = this.taille_case * labyrinthe.getTaille();

			/*----- Paramètre du JPanel -----*/
			this.setPreferredSize(new Dimension(this.largeur, this.largeur));
			}


		/**
		 * Méthode qui permet de dessiner ou redessinner le labyrinthe lorsque
		 * la méthode repaint() est appelée sur la classe Dessin.
		 */
		@Override
		public void paint (Graphics g)
			{
			/*----- On efface le dessin en entier -----*/
			g.setColor(Color.WHITE);
			g.fillRect(0,0,this.largeur,this.largeur);

			/*----- Affichage des cases du labyrinthe -----*/
			BufferedImage mur_png = null, prairie_png = null, porte_png = null, cle_png = null, victoire_png = null, mer_png = null, sirene_png = null, minotaure_png = null, reine_png = null, roi_png = null, ecouteur_png = null, gendarme_png = null, epee_png = null, desert_png = null, coffre_png = null;
			try {
				mur_png = ImageIO.read(new File(System.getProperty("user.dir")+"\\data\\wall.png"));
				prairie_png = ImageIO.read(new File(System.getProperty("user.dir")+"\\data\\grass.png"));
				porte_png = ImageIO.read(new File(System.getProperty("user.dir")+"\\data\\gate.png"));
				cle_png = ImageIO.read(new File(System.getProperty("user.dir")+"\\data\\key.png"));
				victoire_png = ImageIO.read(new File(System.getProperty("user.dir")+"\\data\\trophy.png"));
				mer_png = ImageIO.read(new File(System.getProperty("user.dir")+"\\data\\ocean.png"));
				sirene_png = ImageIO.read(new File(System.getProperty("user.dir")+"\\data\\mermaid.png"));
				minotaure_png = ImageIO.read(new File(System.getProperty("user.dir")+"\\data\\minotaur.png"));
				reine_png = ImageIO.read(new File(System.getProperty("user.dir")+"\\data\\queen.png"));
				roi_png = ImageIO.read(new File(System.getProperty("user.dir")+"\\data\\king.png"));
				ecouteur_png = ImageIO.read(new File(System.getProperty("user.dir")+"\\data\\headphones.png"));
				gendarme_png = ImageIO.read(new File(System.getProperty("user.dir")+"\\data\\warrior.png"));
				epee_png = ImageIO.read(new File(System.getProperty("user.dir")+"\\data\\sword.png"));
				desert_png = ImageIO.read(new File(System.getProperty("user.dir")+"\\data\\dry.png"));
				coffre_png = ImageIO.read(new File(System.getProperty("user.dir")+"\\data\\treasure.png"));
			} catch (IOException err) {
				err.printStackTrace();			
			}
			for (int i=0; i < labyrinthe.getTaille(); i++)
				for (int j=0; j < labyrinthe.getTaille(); j++)
					{
					/*----- Couleur de la case -----*/
					if (labyrinthe.getCase(i,j).getClassName().equals("Mur")) g.drawImage(mur_png, taille_case*j, taille_case*i, taille_case, taille_case, null);
					if (labyrinthe.getCase(i,j).getNomTypeCase().equals("Prairie")) {
						g.drawImage(prairie_png, taille_case*j, taille_case*i, taille_case, taille_case, null);
						if (labyrinthe.getCase(i,j).getContientObjet() == true) {
							if (labyrinthe.getObjet(i, j).getNomObjet().equals("Clé")) g.drawImage(cle_png, taille_case*j, taille_case*i, taille_case, taille_case, null);
							if (labyrinthe.getObjet(i, j).getNomObjet().equals("Minotaure")) {
								g.drawImage(minotaure_png, taille_case*j, taille_case*i, taille_case, taille_case, null);
								for (int objet = 0; objet < labyrinthe.getAventurier().getInventaire().size(); objet++) {
									if ((labyrinthe.getAventurier().getX() + 1 == i && labyrinthe.getAventurier().getY() == j)
											|| (labyrinthe.getAventurier().getX() - 1 == i && labyrinthe.getAventurier().getY() == j)
											|| (labyrinthe.getAventurier().getX() == i && labyrinthe.getAventurier().getY() == j + 1)
											|| (labyrinthe.getAventurier().getX() == i && labyrinthe.getAventurier().getY() == j - 1)
									&& labyrinthe.getAventurier().getInventaire().get(objet).getNomObjet().equals("Epée")) {
										labyrinthe.getCase(i, j).setEstFranchissable(true);
										labyrinthe.getAventurier().getInventaire().remove(objet);
									} else {
										labyrinthe.getAventurier().setPtVie(labyrinthe.getAventurier().getPtVie() - 500);
									}
								}
							}
							
							if (labyrinthe.getObjet(i, j).getNomObjet().equals("Reine")) {
								g.drawImage(reine_png, taille_case*j, taille_case*i, taille_case, taille_case, null);
								for (int objet = 0; objet < labyrinthe.getAventurier().getInventaire().size(); objet++) {
									if ((labyrinthe.getAventurier().getX() + 1 == i && labyrinthe.getAventurier().getY() == j)
											|| (labyrinthe.getAventurier().getX() - 1 == i && labyrinthe.getAventurier().getY() == j)
											|| (labyrinthe.getAventurier().getX() == i && labyrinthe.getAventurier().getY() == j + 1)
											|| (labyrinthe.getAventurier().getX() == i && labyrinthe.getAventurier().getY() == j - 1)
									&& labyrinthe.getAventurier().getInventaire().get(objet).getNomObjet().equals("Minotaure")) {
										labyrinthe.getCase(i, j).setEstFranchissable(true);
										labyrinthe.getAventurier().getInventaire().remove(objet);
									}
								}
							}
							if (labyrinthe.getObjet(i, j).getNomObjet().equals("Ecouteur")) g.drawImage(ecouteur_png, taille_case*j, taille_case*i, taille_case, taille_case, null);
							if (labyrinthe.getObjet(i, j).getNomObjet().equals("Roi")) {
								g.drawImage(roi_png, taille_case*j, taille_case*i, taille_case, taille_case, null);
								for (int objet = 0; objet < labyrinthe.getAventurier().getInventaire().size(); objet++) {
									if ((labyrinthe.getAventurier().getX() + 1 == i && labyrinthe.getAventurier().getY() == j)
											|| (labyrinthe.getAventurier().getX() - 1 == i && labyrinthe.getAventurier().getY() == j)
											|| (labyrinthe.getAventurier().getX() == i && labyrinthe.getAventurier().getY() == j + 1)
											|| (labyrinthe.getAventurier().getX() == i && labyrinthe.getAventurier().getY() == j - 1)
									&& (labyrinthe.getAventurier().getInventaire().get(objet).getNomObjet().equals("Reine") || labyrinthe.getAventurier().getInventaire().get(objet).getNomObjet().equals("Epée"))) {
										labyrinthe.getCase(i, j).setEstFranchissable(true);
										labyrinthe.getCase(i, j).setContientObjet(true);
										labyrinthe.setObjet(i, j, new Objet("Clé", ""));
										labyrinthe.getAventurier().getInventaire().remove(objet);
									}
								}
							}
							if (labyrinthe.getObjet(i, j).getNomObjet().equals("Gendarme")) {
								g.drawImage(gendarme_png, taille_case*j, taille_case*i, taille_case, taille_case, null);
								for (int objet = 0; objet < labyrinthe.getAventurier().getInventaire().size(); objet++) {
									if ((labyrinthe.getAventurier().getX() + 1 == i && labyrinthe.getAventurier().getY() == j)
											|| (labyrinthe.getAventurier().getX() - 1 == i && labyrinthe.getAventurier().getY() == j)
											|| (labyrinthe.getAventurier().getX() == i && labyrinthe.getAventurier().getY() == j + 1)
											|| (labyrinthe.getAventurier().getX() == i && labyrinthe.getAventurier().getY() == j - 1)
									&& (labyrinthe.getAventurier().getInventaire().get(objet).getNomObjet().equals("Coffre") || labyrinthe.getAventurier().getInventaire().get(objet).getNomObjet().equals("Epée"))) {
										labyrinthe.getCase(i, j).setEstFranchissable(true);
										labyrinthe.getCase(i, j).setContientObjet(false);
										labyrinthe.getAventurier().getInventaire().remove(objet);
									}
								}
							}
						}
					}
					if (labyrinthe.getCase(i, j).getNomTypeCase().equals("Porte")) {
						g.drawImage(prairie_png, taille_case*j, taille_case*i, taille_case, taille_case, null);
						if (!labyrinthe.getCase(i, j).getEstFranchissable()) {
							g.drawImage(porte_png, taille_case*j, taille_case*i, taille_case, taille_case, null);
							for (int objet = 0; objet < labyrinthe.getAventurier().getInventaire().size(); objet++) {
								if (labyrinthe.getAventurier().getX() + 1 == i && labyrinthe.getAventurier().getY() == j
								&& labyrinthe.getAventurier().getInventaire().get(objet).getNomObjet().equals("Clé")) {
									labyrinthe.getCase(i, j).setEstFranchissable(true);
									labyrinthe.getCase(i, j).setContientObjet(false);
								}
							}
						} else g.drawImage(victoire_png, taille_case*j, taille_case*i, taille_case, taille_case, null);
					}
					if (labyrinthe.getCase(i, j).getNomTypeCase().equals("Mer")) {
						g.drawImage(mer_png, taille_case*j, taille_case*i, taille_case, taille_case, null);
						if (labyrinthe.getCase(i, j).getContientObjet()) {
							if (labyrinthe.getObjet(i, j).getNomObjet().equals("Sirene")) g.drawImage(sirene_png, taille_case*j, taille_case*i, taille_case, taille_case, null);
							if (labyrinthe.getObjet(i, j).getNomObjet().equals("Epée")) g.drawImage(epee_png, taille_case*j, taille_case*i, taille_case, taille_case, null);
						}
					}
					if (labyrinthe.getCase(i, j).getNomTypeCase().equals("Désert")) {
						g.drawImage(desert_png, taille_case*j, taille_case*i, taille_case, taille_case, null);
						if (labyrinthe.getCase(i, j).getContientObjet()) {
							if (labyrinthe.getObjet(i, j).getNomObjet().equals("Coffre")) g.drawImage(coffre_png, taille_case*j, taille_case*i, taille_case, taille_case, null);
							}
						
					}
					/*----- Affichage des points de vie sous forme d'un rectangle plein -----*/
					if (labyrinthe.getAventurier().getPtVie() > 7500) {
		                g.setColor(Color.BLACK);
		                g.fillRect(0, 10, labyrinthe.getAventurier().PT_VIE_DEPART/100, 15);
		                g.setColor(Color.decode("#009F3E"));
		                g.fillRect(0, 10, labyrinthe.getAventurier().getPtVie()/100, 15);
		            } else if (labyrinthe.getAventurier().getPtVie() > 5000) {
		                g.setColor(Color.BLACK);
		                g.fillRect(0, 10, labyrinthe.getAventurier().PT_VIE_DEPART/100, 15);
		                g.setColor(Color.GREEN);
		                g.fillRect(0, 10, labyrinthe.getAventurier().getPtVie()/100, 15);
		            } else if (labyrinthe.getAventurier().getPtVie() > 2500) {
		                g.setColor(Color.BLACK);
		                g.fillRect(0, 10, labyrinthe.getAventurier().PT_VIE_DEPART/100, 15);
		                g.setColor(Color.ORANGE);
		                g.fillRect(0, 10, labyrinthe.getAventurier().getPtVie()/100, 15);
		            } else {
		                g.setColor(Color.BLACK);
		                g.fillRect(0, 10, labyrinthe.getAventurier().PT_VIE_DEPART/100, 15);
		                g.setColor(Color.RED);
		                g.fillRect(0, 10, labyrinthe.getAventurier().getPtVie()/100, 15);
		            }
					
					}

			/*----- Affichage de l'aventurier -----*/
			Aventurier aventurier = labyrinthe.getAventurier();
			BufferedImage aventurier_png = null, mort_png = null;
			if (aventurier.getPtVie() > 0) {
				try {
					aventurier_png = ImageIO.read(new File(System.getProperty("user.dir")+"\\data\\soldier.png"));
				} catch (IOException err) {
					err.printStackTrace();
				}
				g.drawImage(aventurier_png, taille_case*aventurier.getY() + taille_case/4, taille_case*aventurier.getX() + taille_case/4, taille_case/2, taille_case/2, null);
				
			} else {
				try {
					mort_png = ImageIO.read(new File(System.getProperty("user.dir")+"\\data\\skull-and-bones.png"));
				} catch (IOException err) {
					err.printStackTrace();
				}
				g.drawImage(mort_png, taille_case*aventurier.getY() + taille_case/4, taille_case*aventurier.getX() + taille_case/4, taille_case/2, taille_case/2, null);
			}
			
			
			}
			

		/**
		 * Gestion des interactions souris et clavier sur le labyrinthe.
		 */
		@Override
		public void mouseClicked (MouseEvent e)
			{
			/*----- Lecture de la position de la souris lors du clic sur l'objet Dessin -----*/
			int x = e.getX();
			int y = e.getY();

			/*----- Recherche des coordonnées de la case du labyrinthe sur laquelle le joueur a cliqué -----*/
			int ligne = y / this.taille_case;
			int colonne = x / this.taille_case;

			/*----- On regarde si l'aventurier est sur la case sur laquelle on vient de cliquer -----*/
			String msgAventurier = "";
			if (labyrinthe.getAventurier().getX() == ligne && labyrinthe.getAventurier().getY() == colonne)
				msgAventurier = "\nL'aventurier est sur cette case.\n Son point de vie est " + labyrinthe.getAventurier().getPtVie() + ".\n Dans son inventaire, il a " + labyrinthe.getAventurier().getInventaire().toString() + ".";

			/*----- Etat de la case -----*/
			JOptionPane.showMessageDialog(this, "Vous avez cliqué sur la case (" + ligne + "," + colonne +").\nC'est un "
												+ labyrinthe.getCase(ligne, colonne).getClassName()
												+ "." + msgAventurier);
			}

		@Override
		public void mousePressed (MouseEvent e) { }

		@Override
		public void mouseReleased (MouseEvent e) { }

		@Override
		public void mouseEntered (MouseEvent e) { }

		@Override
		public void mouseExited (MouseEvent e) { }

		@Override
		public void mouseDragged (MouseEvent e) { }

		@Override
		public void mouseMoved (MouseEvent e)
			{
			barre_etat.setText(" Position de la souris : " + e.getX() + " " + e.getY());
			}

		@Override
		public void keyTyped (KeyEvent e) { }

		@Override
		public void keyPressed (KeyEvent e) { }

		@Override
		public void keyReleased (KeyEvent e)
			{
			/**
			 * Déplacement de l'aventurier dans le labyrinthe.
			 */
			int pos;

			/*----- Vers le bas -----*/
			if (e.getKeyCode() == KeyEvent.VK_DOWN
					&& (labyrinthe.getCase(labyrinthe.getAventurier().getX() + 1, labyrinthe.getAventurier().getY()).getEstFranchissable())
					&& labyrinthe.getAventurier().getX() + 1 <= labyrinthe.getTaille())
				{
				pos = labyrinthe.getAventurier().getX();
				labyrinthe.getAventurier().setX(pos+1);
				if (labyrinthe.getCase(labyrinthe.getAventurier().getX(), labyrinthe.getAventurier().getY()).getNomTypeCase().equals("Prairie")) {
					labyrinthe.getAventurier().setPtVie(labyrinthe.getAventurier().getPtVie() - 25);
				} else if (labyrinthe.getCase(labyrinthe.getAventurier().getX(), labyrinthe.getAventurier().getY()).getNomTypeCase().equals("Mer")) {
					labyrinthe.getAventurier().setPtVie(labyrinthe.getAventurier().getPtVie() - 50);
				} else if (labyrinthe.getCase(labyrinthe.getAventurier().getX(), labyrinthe.getAventurier().getY()).getNomTypeCase().equals("Désert")) {
					labyrinthe.getAventurier().setPtVie(labyrinthe.getAventurier().getPtVie() - 100);
				}
				if (labyrinthe.getCase(labyrinthe.getAventurier().getX(), labyrinthe.getAventurier().getY()).getContientObjet()) {
					labyrinthe.getAventurier().setInventaire(labyrinthe.getObjet(labyrinthe.getAventurier().getX(), labyrinthe.getAventurier().getY()));
					labyrinthe.getCase(labyrinthe.getAventurier().getX(), labyrinthe.getAventurier().getY()).setContientObjet(false);
				}
				}

			/*----- Vers le haut -----*/
			if (e.getKeyCode() == KeyEvent.VK_UP
					&& (labyrinthe.getCase(labyrinthe.getAventurier().getX() - 1, labyrinthe.getAventurier().getY()).getEstFranchissable())
					&& labyrinthe.getAventurier().getX() - 1 <= labyrinthe.getTaille())
				{
				pos = labyrinthe.getAventurier().getX();
				labyrinthe.getAventurier().setX(pos-1);
				if (labyrinthe.getCase(labyrinthe.getAventurier().getX(), labyrinthe.getAventurier().getY()).getNomTypeCase().equals("Prairie")) {
					labyrinthe.getAventurier().setPtVie(labyrinthe.getAventurier().getPtVie() - 25);
				} else if (labyrinthe.getCase(labyrinthe.getAventurier().getX(), labyrinthe.getAventurier().getY()).getNomTypeCase().equals("Mer")) {
					labyrinthe.getAventurier().setPtVie(labyrinthe.getAventurier().getPtVie() - 50);
				} else if (labyrinthe.getCase(labyrinthe.getAventurier().getX(), labyrinthe.getAventurier().getY()).getNomTypeCase().equals("Désert")) {
					labyrinthe.getAventurier().setPtVie(labyrinthe.getAventurier().getPtVie() - 100);
				}
				if (labyrinthe.getCase(labyrinthe.getAventurier().getX(), labyrinthe.getAventurier().getY()).getContientObjet()) {
					labyrinthe.getAventurier().setInventaire(labyrinthe.getObjet(labyrinthe.getAventurier().getX(), labyrinthe.getAventurier().getY()));
					labyrinthe.getCase(labyrinthe.getAventurier().getX(), labyrinthe.getAventurier().getY()).setContientObjet(false);
				}
				}

			/*----- Vers la droite -----*/
			if (e.getKeyCode() == KeyEvent.VK_RIGHT
					&& (labyrinthe.getCase(labyrinthe.getAventurier().getX(), labyrinthe.getAventurier().getY() + 1).getEstFranchissable())
					&& labyrinthe.getAventurier().getY() + 1 <= labyrinthe.getTaille())
				{
				pos = labyrinthe.getAventurier().getY();
				labyrinthe.getAventurier().setY(pos+1);
				if (labyrinthe.getCase(labyrinthe.getAventurier().getX(), labyrinthe.getAventurier().getY()).getNomTypeCase().equals("Prairie")) {
					labyrinthe.getAventurier().setPtVie(labyrinthe.getAventurier().getPtVie() - 25);
				} else if (labyrinthe.getCase(labyrinthe.getAventurier().getX(), labyrinthe.getAventurier().getY()).getNomTypeCase().equals("Mer")) {
					labyrinthe.getAventurier().setPtVie(labyrinthe.getAventurier().getPtVie() - 50);
				} else if (labyrinthe.getCase(labyrinthe.getAventurier().getX(), labyrinthe.getAventurier().getY()).getNomTypeCase().equals("Désert")) {
					labyrinthe.getAventurier().setPtVie(labyrinthe.getAventurier().getPtVie() - 100);
				}
				if (labyrinthe.getCase(labyrinthe.getAventurier().getX(), labyrinthe.getAventurier().getY()).getContientObjet()) {
					labyrinthe.getAventurier().setInventaire(labyrinthe.getObjet(labyrinthe.getAventurier().getX(), labyrinthe.getAventurier().getY()));
					labyrinthe.getCase(labyrinthe.getAventurier().getX(), labyrinthe.getAventurier().getY()).setContientObjet(false);
				}
				}

			/*----- Vers la gauche -----*/
			if (e.getKeyCode() == KeyEvent.VK_LEFT
					&& (labyrinthe.getCase(labyrinthe.getAventurier().getX(), labyrinthe.getAventurier().getY() - 1).getEstFranchissable())
					&& labyrinthe.getAventurier().getY() - 1 <= labyrinthe.getTaille())
				{
				pos = labyrinthe.getAventurier().getY();
				labyrinthe.getAventurier().setY(pos-1);
				if (labyrinthe.getCase(labyrinthe.getAventurier().getX(), labyrinthe.getAventurier().getY()).getNomTypeCase().equals("Prairie")) {
					labyrinthe.getAventurier().setPtVie(labyrinthe.getAventurier().getPtVie() - 25);
				} else if (labyrinthe.getCase(labyrinthe.getAventurier().getX(), labyrinthe.getAventurier().getY()).getNomTypeCase().equals("Mer")) {
					labyrinthe.getAventurier().setPtVie(labyrinthe.getAventurier().getPtVie() - 50);
				} else if (labyrinthe.getCase(labyrinthe.getAventurier().getX(), labyrinthe.getAventurier().getY()).getNomTypeCase().equals("Désert")) {
					labyrinthe.getAventurier().setPtVie(labyrinthe.getAventurier().getPtVie() - 100);
				}
				if (labyrinthe.getCase(labyrinthe.getAventurier().getX(), labyrinthe.getAventurier().getY()).getContientObjet()) {
					labyrinthe.getAventurier().setInventaire(labyrinthe.getObjet(labyrinthe.getAventurier().getX(), labyrinthe.getAventurier().getY()));
					labyrinthe.getCase(labyrinthe.getAventurier().getX(), labyrinthe.getAventurier().getY()).setContientObjet(false);
				}
				}

			/*----- On déclenche l'action de la case sur laquelle est l'aventurier -----*/
			labyrinthe.action();

			/*----- On refait le dessin -----*/
			repaint();
			}

		} /*----- Fin de la classe interne Dessin -----*/

} /*----- Fin de la classe Vue -----*/