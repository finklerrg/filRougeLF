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

}
