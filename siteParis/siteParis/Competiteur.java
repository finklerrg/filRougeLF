package siteParis;

import java.util.Collection;


public class Competiteur {


	private String nom;

	public Competiteur(String nom) throws CompetitionException{

		validiteNomCompetiteur(nom);
		this.nom=nom;

	}




	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}

	public void validiteNomCompetiteur(String nom) throws CompetitionException {
		if (nom==null) throw new CompetitionException();
		if (!nom.matches("[-A-Za-z]{4,}")) throw new CompetitionException();
	}
}
