package siteParis;


public class Pari {

	
   public Pari(long miseEnJetons, Joueur joueurReference, Competiteur competiteurReference){
      
      //joueurReference
      this.miseEnJetons=miseEnJetons;
      this.competiteurReference=competiteurReference;
      this.joueurReference=joueurReference;
   
   }
      
   
   /**
	 * @uml.property  name="miseEnJetons"
	 */
	private long miseEnJetons;

	/**
	 * Getter of the property <tt>miseEnJetons</tt>
	 * @return  Returns the miseEnJetons.
	 * @uml.property  name="miseEnJetons"
	 */
	public long getMiseEnJetons() {
		return miseEnJetons;
	}

	/**
	 * Setter of the property <tt>miseEnJetons</tt>
	 * @param miseEnJetons  The miseEnJetons to set.
	 * @uml.property  name="miseEnJetons"
	 */
	public void setMiseEnJetons(long miseEnJetons) {
		this.miseEnJetons = miseEnJetons;
	}

	/**
	 * @uml.property  name="joeurReference"
	 */
	private Joueur joueurReference;

	/**
	 * Getter of the property <tt>joeurReference</tt>
	 * @return  Returns the joeurReference.
	 * @uml.property  name="joeurReference"
	 */
	public Joueur getJoueurReference() {
		return joueurReference;
	}

	/**
	 * Setter of the property <tt>joeurReference</tt>
	 * @param joeurReference  The joeurReference to set.
	 * @uml.property  name="joeurReference"
	 */
	public void setJoueurReference(Joueur joueurReference) {
		this.joueurReference = joueurReference;
	}

	/**
	 * @uml.property  name="competitionReference"
	 */
	private Competiteur competiteurReference;

	/**
	 * Getter of the property <tt>competitionReference</tt>
	 * @return  Returns the competitionReference.
	 * @uml.property  name="competitionReference"
	 */
	public Competiteur getCompetiteurReference() {
		return competiteurReference;
	}

	
	public void setCompetiteurReference(Competiteur competiteurReference) {
		this.competiteurReference = competiteurReference;
	}

	/** 
	 * @uml.property name="joeur"
	 * @uml.associationEnd multiplicity="(1 1)" inverse="pari:siteParis.Joeur"
	 * @uml.association name="a ete fait par"
	 */
	/**private Joueur joueur = new siteParis.Joueur();

	/** 
	 * Getter of the property <tt>joeur</tt>
	 * @return  Returns the joeur.
	 * @uml.property  name="joeur"
	 */
   /** 
	public Joueur getJoueur() {
		return joueur;
	}

	/** 
	 * Setter of the property <tt>joeur</tt>
	 * @param joeur  The joeur to set.
	 * @uml.property  name="joeur"
	 */
   /** 
	public void setJoueur(Joueur joueur) {
		this.joueur = joueur;
	}
   */
	/**
	 * @uml.property  name="competition"
	 * @uml.associationEnd  inverse="pari:siteParis.Competition"
	 * @uml.association  name="fait reference à"
	 */
	private Competition competition;

	/**
	 * Getter of the property <tt>competition</tt>
	 * @return  Returns the competition.
	 * @uml.property  name="competition"
	 */
	public Competition getCompetition() {
		return competition;
	}

	/**
	 * Setter of the property <tt>competition</tt>
	 * @param competition  The competition to set.
	 * @uml.property  name="competition"
	 */
	public void setCompetition(Competition competition) {
		this.competition = competition;
	}

	/**
	 * @uml.property  name="competiteur"
	 * @uml.associationEnd  multiplicity="(1 1)" inverse="pari:siteParis.Competiteur"
	 * @uml.association  name="a vainqueur en visage"
	 */
   /** 
	private Competiteur competiteur = new siteParis.Competiteur();

	/**
	 * Getter of the property <tt>competiteur</tt>
	 * @return  Returns the competiteur.
	 * @uml.property  name="competiteur"
	 */
   /** 
	public Competiteur getCompetiteur() {
		return competiteur;
	}*/

	/**
	 * Setter of the property <tt>competiteur</tt>
	 * @param competiteur  The competiteur to set.
	 * @uml.property  name="competiteur"
	 */
    
   /** 
	public void setCompetiteur(Competiteur competiteur) {
		this.competiteur = competiteur;
	}

	/**
	 * @uml.property  name="competiteur1"
	 * @uml.associationEnd  inverse="pari:siteParis.Competiteur"
	 */
}
