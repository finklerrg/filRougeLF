package siteParis;

import java.util.Collection;


public class Joueur {

   private String nom;
   
   public Joueur (String nom, String prenom, String pseudo, String passwordGestionnaire) throws MetierException, JoueurExistantException, JoueurException{
      
     // if(nomCre==null)throw new JoueurException();
   	//this.nom=nomCre;		
   		/**
   			 */
     // if (!nomCre.matches("[0-9A-Za-z]{8,}")) throw new JoueurException();      
      //this.nom=nomCre;
      validitePrenomJoueur(prenom);
      this.prenom=prenom;
      		
      validiteNomJoueur(nom);
      this.nom=nom;
      
      validitePseudoJoueur(pseudo);
      this.pseudo=pseudo;
      
      this.sommeEnJetons=0;
      
      
   }

	   public String getNom() {
      return nom;
   }

	   public void setNom(String nom) {
      this.nom = nom;
   }

	
   private String prenom;

	   public String getPrenom() {
      return prenom;
   }

	   public void setPrenom(String prenom) {
      this.prenom = prenom;
   }

	   private String pseudo;

	   public String getPseudo() {
      return pseudo;
   }

	
   public void setPseudo(String pseudo) {
      this.pseudo = pseudo;
   }

	   private long sommeEnJetons;

	   public long getSommeEnJetons() {
      return sommeEnJetons;
   }
   
    public void setSommeEnJetons(long sommeEnJetons) {
      this.sommeEnJetons=sommeEnJetons;
   }


   private String passwordJouer;

	   public String getPasswordJouer() {
      return passwordJouer;
   }

	   public void setPasswordJouer(String passwordJouer) {
      this.passwordJouer = passwordJouer;
   }
   
   public void validiteNomJoueur(String nom) throws JoueurException {
      if (nom==null) throw new JoueurException();
      if (!nom.matches("[-A-Za-z]{1,}")) throw new JoueurException();
   }
   public void validitePrenomJoueur(String prenom) throws JoueurException {
      if (prenom==null) throw new JoueurException();
      if (!prenom.matches("[-A-Za-z]{1,}")) throw new JoueurException();
   }
   public void validitePseudoJoueur(String Pseudo) throws JoueurException {
      if (Pseudo==null) throw new JoueurException();
      if (!Pseudo.matches("[0-9A-Za-z]{4,}")) throw new JoueurException();
   }
  
  
}
