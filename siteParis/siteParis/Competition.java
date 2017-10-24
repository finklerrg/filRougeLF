package siteParis;

import java.util.LinkedList;

import java.util.Arrays;


public class Competition {

      private LinkedList<Pari> paris;
       

      public Competition(String competition, DateFrancaise dateCloture, String[] competitors, int tai ) throws CompetitionException {
        
        
            competiteurs= new LinkedList<Competiteur> ();
           
            for(int i=0;i<tai-1; i=i+1){
               for(int j=i+1;j<tai; j=j+1){
                  if (competitors[i].equals(competitors[j])) throw new CompetitionException();
               }
            }
            
            for(int i=0;i<tai; i=i+1){
            
            validiteNomCompetiteur(competitors[i]);
            Competiteur c = new Competiteur(competitors[i]);
            competiteurs.add(c);
            }
        
            paris=new LinkedList<Pari>();
         
         validiteDateFrancaise(dateCloture);
         this.dateCloture=dateCloture;
             
      
      
      
         validiteCompetition(competition);
         this.competition=competition;
      		
               
      
    
      }
         
      
      
      
      

	
	private DateFrancaise dateCloture;

	
	public DateFrancaise getDateCloture() {
		return dateCloture;
	}

	
	public void setDateCloture(DateFrancaise dateCloture) {
		this.dateCloture = dateCloture;
	}

	
		
	private Joueur vainqueur;

	
	public Joueur getVainqueur() {
		return vainqueur;
	}

	
	public void setVainqueur(Joueur vainqueur) {
		this.vainqueur = vainqueur;
	}

	/**
	public void setCompetiteurs(LinkedList<Competiteur> competiteurs) {
		this.competiteurs = competiteurs;
	}*/

        
        
        
        public LinkedList<Pari> getParis() {	
      return paris;
	}

	
	//public void setCompetiteur(String[] competiteurs) {
	public void setParis(LinkedList<Pari> paris) {
   	this.paris = paris;
	}
        
        
        
        
	private LinkedList<Competiteur> competiteurs;
	//private  String[] competiteurs;

	
	//public String[] getCompetiteurs() {
	public LinkedList<Competiteur> getCompetiteurs() {	
      return competiteurs;
	}

	
	//public void setCompetiteur(String[] competiteurs) {
	public void setCompetiteur(LinkedList<Competiteur> competiteurs) {
   	this.competiteurs = competiteurs;
	}

	
	private String competition;

	
	public String getCompetition() {
		return competition;
	}

	
	public void setCompetition(String competition) {
		this.competition = competition;
	}

	
	private LinkedList joueurs = new java.util.LinkedList();

	
	public LinkedList getJoueurs() {
		return joueurs;
	}

	
	public void setJoueurs(LinkedList joueurs) {
		this.joueurs = joueurs;
	}

	
	private siteParis.Competiteur competiteurVainqueur;

	
	public siteParis.Competiteur getCompetiteurVainqueur() {
		return competiteurVainqueur;
	}

	
	public void setCompetiteurVainqueur(
			siteParis.Competiteur competiteurVainqueur) {
				this.competiteurVainqueur = competiteurVainqueur;
			}

	
	

	

   public void validiteCompetition(String competition )throws CompetitionException{ 
   
      if (competition==null) throw new CompetitionException();
      if (!competition.matches("[0-9A-Za-z]{4,}")) throw new CompetitionException();

   
   }
   
   public void validiteDateFrancaise( DateFrancaise dateCloture) throws CompetitionException{
      if(dateCloture==null) throw new CompetitionException();
      if (dateCloture.estDansLePasse()==true) throw new CompetitionException();
      
   }


   
   public void validiteNomCompetiteur(String nom) throws CompetitionException {
              
      
       if (nom==null) throw new CompetitionException();
       if (!nom.matches("[-A-Za-z]{4,}")) throw new CompetitionException();
      
      
   }   




}
