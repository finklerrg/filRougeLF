

package siteParis;


import java.util.LinkedList;
import java.util.Collection;
import java.util.Arrays;

/**
 *
 * @author Bernard Prou et Julien Mallet
 * <br><br>
 * La classe qui contient toutes les méthodes "Métier" de la gestion du site de paris.
 * <br><br>
 * Dans toutes les méthodes :
 * <ul>
 * <li>un paramètre de type <code>String</code> est invalide si il n'est pas instancié.</li>
 *  <li>pour la validité d'un password de gestionnaire et d'un password de joueur :
 * <ul>
 * <li>       lettres et chiffres sont les seuls caractères autorisés </li>
 * <li>       il doit avoir une longueur d'au moins 8 caractères </li>
 * </ul></li>
 * <li>pour la validité d'un pseudo de joueur  :
 * <ul>
 * <li>        lettres et chiffres sont les seuls caractères autorisés  </li>
 * <li>       il doit avoir une longueur d'au moins 4 caractères</li>
 * </ul></li>
 * <li>pour la validité d'un prénom de joueur et d'un nom de joueur :
 *  <ul>
 *  <li>       lettres et tiret sont les seuls caractères autorisés  </li>
 *  <li>      il  doit avoir une longueur d'au moins 1 caractère </li>
 * </ul></li>
 * <li>pour la validité d'une compétition  :
 *  <ul>
 *  <li>       lettres, chiffres, point, trait d'union et souligné sont les seuls caractères autorisés </li>
 *  <li>      elle  doit avoir une longueur d'au moins 4 caractères</li>
 * </ul></li>
 * <li>pour la validité d'un compétiteur  :
 *  <ul>
 *  <li>       lettres, chiffres, trait d'union et souligné sont les seuls caractères autorisés </li>
 *  <li>      il doit avoir une longueur d'au moins 4 caractères.</li>
 * </ul></li></ul>
 */

public class SiteDeParisMetier {


	private Gestionnaire leGestionnaire;
	private LinkedList<Joueur> joueurs;
	private LinkedList<Competition> competitions;


	public SiteDeParisMetier(String passwordGestionnaire) throws MetierException {
		leGestionnaire= new Gestionnaire(passwordGestionnaire);
		joueurs=new LinkedList<Joueur> ();
		competitions=new LinkedList<Competition> ();

	}





	// Les méthodes du gestionnaire (avec mot de passe gestionnaire)



	/**
	 * inscrire un joueur.
	 *
	 * @param nom   le nom du joueur
	 * @param prenom   le prénom du joueur
	 * @param pseudo   le pseudo du joueur
	 * @param passwordGestionnaire  le password du gestionnaire du site
	 *
	 * @throws MetierException   levée
	 * si le <code>passwordGestionnaire</code> proposé est invalide,
	 * si le <code>passwordGestionnaire</code> est incorrect.
	 * @throws JoueurExistantException   levée si un joueur existe avec les mêmes noms et prénoms ou le même pseudo.
	 * @throws JoueurException levée si <code>nom</code>, <code>prenom</code>, <code>pseudo</code> sont invalides.
	 *
	 * @return le mot de passe (déterminé par le site) du nouveau joueur inscrit.
	 */
	public String inscrireJoueur(String nom, String prenom, String pseudo, String passwordGestionnaire) throws MetierException, JoueurExistantException, JoueurException {

		validitePasswordGestionnaire(passwordGestionnaire);
		if(!leGestionnaire.getPasswordGestionnaire().equals(passwordGestionnaire)) throw new MetierException();


		Joueur j=new Joueur(nom,prenom, pseudo,passwordGestionnaire);



		for(int i=0;i<joueurs.size();i=i+1){

			if(joueurs.get(i).getNom().equals(j.getNom()) && joueurs.get(i).getPrenom().equals(j.getPrenom()) ) throw new JoueurExistantException();
			if(joueurs.get(i).getPseudo().equals(j.getPseudo()) )  throw new JoueurExistantException();

		}


		joueurs.add(j);




		return "unPasswordUnique";
	}

	/**
	 * supprimer un joueur.
	 *
	 * @param nom   le nom du joueur
	 * @param prenom   le prénom du joueur
	 * @param pseudo   le pseudo du joueur
	 * @param passwordGestionnaire  le password du gestionnaire du site
	 *
	 * @throws MetierException
	 * si le <code>passwordGestionnaire</code>  est invalide,
	 * si le <code>passwordGestionnaire</code> est incorrect.
	 * @throws JoueurInexistantException   levée si il n'y a pas de joueur  avec le même <code>nom</code>, <code>prenom</code>  et <code>pseudo</code>.
	 * @throws JoueurException levée
	 * si le joueur a des paris en cours,
	 * si <code>nom</code>, <code>prenom</code>, <code>pseudo</code> sont invalides.
	 *
	 * @return le nombre de jetons à rembourser  au joueur qui vient d'être désinscrit.
	 *
	 */
	public long desinscrireJoueur(String nom, String prenom, String pseudo, String passwordGestionnaire) throws MetierException, JoueurInexistantException, JoueurException {

		validitePasswordGestionnaire(passwordGestionnaire);
		if(!leGestionnaire.getPasswordGestionnaire().equals(passwordGestionnaire)) throw new MetierException();

		int var;
		int cont=0;


		for(int i=0;i<joueurs.size();i=i+1){

			if(joueurs.get(i).getPseudo().equals(pseudo) && joueurs.get(i).getNom().equals(nom) && joueurs.get(i).getPrenom().equals(prenom)){

				cont=1;
				var=i;
				joueurs.remove(var);
			}

		}



		if(cont==0) throw new JoueurInexistantException();





		return 0;
	}



	/**
	 * ajouter une compétition.
	 *
	 * @param competition le nom de la compétition
	 * @param dateCloture   la date à partir de laquelle il ne sera plus possible de miser
	 * @param competiteurs   les noms des différents compétiteurs de la compétition
	 * @param passwordGestionnaire  le password du gestionnaire du site
	 *
	 * @throws MetierException levée si le tableau des
	 * compétiteurs n'est pas instancié, si le
	 * <code>passwordGestionnaire</code> est invalide, si le
	 * <code>passwordGestionnaire</code> est incorrect.
	 * @throws CompetitionExistanteException levée si une compétition existe avec le même nom.
	 * @throws CompetitionException levée si le nom de la
	 * compétition ou des compétiteurs sont invalides, si il y a
	 * moins de 2 compétiteurs, si un des competiteurs n'est pas instancié,
	 * si deux compétiteurs ont le même nom, si la date de clôture
	 * n'est pas instanciée ou est dépassée.
	 */
	public void ajouterCompetition(String competition, DateFrancaise dateCloture, String[] competiteurs, String passwordGestionnaire) throws MetierException, CompetitionExistanteException, CompetitionException  {



		validitePasswordGestionnaire(passwordGestionnaire);
		if(!leGestionnaire.getPasswordGestionnaire().equals(passwordGestionnaire)) throw new MetierException();


		if (competiteurs==null) throw new MetierException();


		int v = competiteurs.length;
		if (v==1)  throw new CompetitionException();



		Competition comp =new Competition(competition,dateCloture,competiteurs, v);


		for(int i=0;i<competitions.size();i=i+1){
			if(competitions.get(i).getCompetition().equals(competition)) throw new CompetitionExistanteException();

		}






		competitions.add(comp);





	}


	/**
	 * solder une compétition vainqueur (compétition avec vainqueur).
	 *
	 * Chaque joueur ayant misé sur cette compétition
	 * en choisissant ce compétiteur est crédité d'un nombre de
	 * jetons égal à :
	 *
	 * (le montant de sa mise * la somme des
	 * jetons misés pour cette compétition) / la somme des jetons
	 * misés sur ce compétiteur.
	 *
	 * Si aucun joueur n'a trouvé le
	 * bon compétiteur, des jetons sont crédités aux joueurs ayant
	 * misé sur cette compétition (conformément au montant de
	 * leurs mises). La compétition est "supprimée" si il ne reste
	 * plus de mises suite à ce solde.
	 *
	 * @param competition   le nom de la compétition
	 * @param vainqueur   le nom du vainqueur de la compétition
	 * @param passwordGestionnaire  le password du gestionnaire du site
	 *
	 * @throws MetierException   levée
	 * si le <code>passwordGestionnaire</code>  est invalide,
	 * si le <code>passwordGestionnaire</code> est incorrect.
	 * @throws CompetitionInexistanteException   levée si il n'existe pas de compétition de même nom.
	 * @throws CompetitionException levée
	 * si le nom de la compétition ou du vainqueur est invalide,
	 * si il n'existe pas de compétiteur du nom du vainqueur dans la compétition,
	 * si la date de clôture de la compétition est dans le futur.
	 *
	 */
	public void solderVainqueur(String competition, String vainqueur, String passwordGestionnaire) throws MetierException,  CompetitionInexistanteException, CompetitionException, JoueurException, JoueurInexistantException  {

		// solder vainqueur recebeu o venecedor então temos que ver quais apostas estão certas e depois mecher na grana dos vencedores
		// sendo a grana a ser crefitada na conta a porcentagem de sua aposta no vencedor em relação a todas as apostas no vencedor
		// vezes a grana total que a competição teve, exemplo, matheus apostou 5 reais no gremio no brasileirao 2012, o gremio teve 100 reais de apostas
		// e o campeonato 1000 reais, se gremio ganhou então 5% de 1000 vai para matheus.

		// faz a validação do password
		validitePasswordGestionnaire(passwordGestionnaire);
		if(!leGestionnaire.getPasswordGestionnaire().equals(passwordGestionnaire)) throw new MetierException();

		//ve qual a competição que vai ser acabada
		int cont1=0;
		int var1=0; // var 1 da a posição da competição que eu quero na linked

		for(int i=0;i<competitions.size();i=i+1){


			if(competitions.get(i).getCompetition().equals(competition)){


				cont1=1;
				var1=i;

			}

		}

		//se a competição nao existe
		if(cont1==0) throw new CompetitionInexistanteException();
		//se a competição nao estourou a data de fazer fechamento
		if(competitions.get(var1).getDateCloture().estDansLePasse()==false) throw new CompetitionException();
		//numero de apostas da competição
		int tamanho= competitions.get(var1).getParis().size(); //numero de apostas que devem ser percorridas


		//primeiramente tem que ver qual a grana total de todas as apostas
		long total=0;
		for(int i1=0;i1<tamanho;i1++){
			total=total+competitions.get(var1).getParis().get(i1).getMiseEnJetons();//soma toda grana da competição

		}



		//numero de competidores neste campeonato
		int tamanho2= competitions.get(var1).getCompetiteurs().size();
		int cont4=0;
		//percorre todo o campeonato para ver qual é o jogador venecedor
		for(int i2=0;i2<tamanho2;i2=i2+1){
			//System.out.println("rodoufirst "+i2);
			//ve se o competidor x existe
			if(competitions.get(var1).getCompetiteurs().get(i2).getNom().equals(vainqueur)){
				cont4=1;
			}
		}


		if(cont4==0) throw new CompetitionException(); //esse if ve se na linked list de competidores existe esse time

		// System.out.println(" NOVOOOOOOO SOLDER");
		//System.out.println("O total é "+total);


		//soma grana apostada no competidor certo
		long granacorreta=0;
		int cont3=0;

		//este for soma a grana total apostada no vencedor
		for(int i3=0;i3<tamanho;i3=i3+1){





			if(competitions.get(var1).getParis().get(i3).getCompetiteurReference().getNom().equals(vainqueur)){//ve se a aposta esta certa
				//se aposta esta certa entao faz o seguinte

				granacorreta=granacorreta+competitions.get(var1).getParis().get(i3).getMiseEnJetons();
				cont3=1;
			}
		}
		//System.out.println("A grana correta é"+granacorreta);
		if(granacorreta==0){
			for(int i=0;i<tamanho;i++){

				String nom=competitions.get(var1).getParis().get(i).getJoueurReference().getNom();
				String prenom=competitions.get(var1).getParis().get(i).getJoueurReference().getPrenom();
				String pseudo=competitions.get(var1).getParis().get(i).getJoueurReference().getPseudo();
				long MiseEnJeton=competitions.get(var1).getParis().get(i).getMiseEnJetons();


				//crediterJoueur(String nom, String prenom, String pseudo, long sommeEnJetons, String passwordGestionnaire)
				crediterJoueur(nom,prenom,pseudo,MiseEnJeton,passwordGestionnaire) ;

			}



		}



		//agora credita os caras que acertaram com a grana de cara um
		float crediter=0;
		float MiseEnJeton;
		for(int i=0;i<tamanho;i++){
			if(competitions.get(var1).getParis().get(i).getCompetiteurReference().getNom().equals(vainqueur)){//ve se a aposta esta certa
				//agora meche na grana do cara debitando

				String nom=competitions.get(var1).getParis().get(i).getJoueurReference().getNom();
				String prenom=competitions.get(var1).getParis().get(i).getJoueurReference().getPrenom();
				String pseudo=competitions.get(var1).getParis().get(i).getJoueurReference().getPseudo();
				MiseEnJeton=competitions.get(var1).getParis().get(i).getMiseEnJetons();


				crediter=(MiseEnJeton/granacorreta)*total;

				long crediter1=(long) crediter;

				// System.out.println("divisao estranha igual a "+MiseEnJeton/granacorreta );
				//System.out.println("O palhaço apostou "+MiseEnJeton+" O crediter é "+crediter+" na conta de "+ pseudo+" na competição"+ competitions.get(var1).getCompetition());
				//crediterJoueur(String nom, String prenom, String pseudo, long sommeEnJetons, String passwordGestionnaire)
				crediterJoueur(nom,prenom,pseudo,crediter1,passwordGestionnaire) ;


			}

		}



		competitions.remove(var1);










	}



	/**
	 * créditer le compte en jetons d'un joueur du site de paris.
	 *
	 * @param nom   le nom du joueur
	 * @param prenom   le prénom du joueur
	 * @param pseudo   le pseudo du joueur
	 * @param sommeEnJetons   la somme en jetons à créditer
	 * @param passwordGestionnaire  le password du gestionnaire du site
	 *
	 * @throws MetierException   levée
	 * si le <code>passwordGestionnaire</code>  est invalide,
	 * si le <code>passwordGestionnaire</code> est incorrect,
	 * si la somme en jetons est négative.
	 * @throws JoueurException levée
	 * si <code>nom</code>, <code>prenom</code>,  <code>pseudo</code> sont invalides.
	 * @throws JoueurInexistantException   levée si il n'y a pas de joueur  avec les mêmes nom,  prénom et pseudo.
	 */
	public void crediterJoueur(String nom, String prenom, String pseudo, long sommeEnJetons, String passwordGestionnaire) throws MetierException, JoueurException, JoueurInexistantException {

		validitePasswordGestionnaire(passwordGestionnaire);
		if(!leGestionnaire.getPasswordGestionnaire().equals(passwordGestionnaire)) throw new MetierException();

		if(sommeEnJetons<0) throw new MetierException();

		int cont1=0;
		int var1=0;
		for(int i=0;i<joueurs.size();i=i+1){

			if(joueurs.get(i).getPseudo().equals(pseudo) && joueurs.get(i).getNom().equals(nom) && joueurs.get(i).getPrenom().equals(prenom)){

				cont1=1;
				var1=i;

			}

		}

		if(cont1==0) throw new JoueurInexistantException();


		joueurs.get(var1).setSommeEnJetons(joueurs.get(var1).getSommeEnJetons()+sommeEnJetons);





	}


	/**
	 * débiter le compte en jetons d'un joueur du site de paris.
	 *
	 * @param nom   le nom du joueur
	 * @param prenom   le prénom du joueur
	 * @param pseudo   le pseudo du joueur
	 * @param sommeEnJetons   la somme en jetons à débiter
	 * @param passwordGestionnaire  le password du gestionnaire du site
	 *
	 * @throws MetierException   levée
	 * si le <code>passwordGestionnaire</code>  est invalide,
	 * si le <code>passwordGestionnaire</code> est incorrect,
	 * si la somme en jetons est négative.
	 * @throws JoueurException levée
	 * si <code>nom</code>, <code>prenom</code>,  <code>pseudo</code> sont invalides
	 * si le compte en jetons du joueur est insuffisant (il deviendrait négatif).
	 * @throws JoueurInexistantException   levée si il n'y a pas de joueur  avec les mêmes nom,  prénom et pseudo.
	 *
	 */

	public void debiterJoueur(String nom, String prenom, String pseudo, long sommeEnJetons, String passwordGestionnaire) throws  MetierException, JoueurInexistantException, JoueurException {

		validitePasswordGestionnaire(passwordGestionnaire);
		if(!leGestionnaire.getPasswordGestionnaire().equals(passwordGestionnaire)) throw new MetierException();

		if(sommeEnJetons<0) throw new MetierException();

		int cont2=0;
		int var2=0;
		for(int i=0;i<joueurs.size();i=i+1){

			if(joueurs.get(i).getPseudo().equals(pseudo) && joueurs.get(i).getNom().equals(nom) && joueurs.get(i).getPrenom().equals(prenom)){

				cont2=1;
				var2=i;

			}

		}

		if(cont2==0) throw new JoueurInexistantException();

		//System.out.println("O credito de "+joueurs.get(var2).getPseudo()+ " é "+joueurs.get(var2).getSommeEnJetons());
		//    System.out.println("  O dinheiro a ser devitado é "+sommeEnJetons);


		if (joueurs.get(var2).getSommeEnJetons()<sommeEnJetons) throw new JoueurException();



		joueurs.get(var2).setSommeEnJetons(joueurs.get(var2).getSommeEnJetons()-sommeEnJetons);
















	}



	/**
	 * consulter les  joueurs.
	 *
	 * @param passwordGestionnaire  le password du gestionnaire du site de paris

	 * @throws MetierException   levée
	 * si le <code>passwordGestionnaire</code>  est invalide,
	 * si le <code>passwordGestionnaire</code> est incorrect.
	 *
	 * @return une liste de liste dont les éléments (de type <code>String</code>) représentent un joueur avec dans l'ordre :
	 *  <ul>
	 *  <li>       le nom du joueur  </li>
	 *  <li>       le prénom du joueur </li>
	 *  <li>       le pseudo du joueur  </li>
	 *  <li>       son compte en jetons restant disponibles </li>
	 *  <li>       le total de jetons engagés dans ses mises en cours. </li>
	 *  </ul>
	 */
	public LinkedList <LinkedList <String>> consulterJoueurs(String passwordGestionnaire) throws MetierException {

		//validação do password gestionnaire
		validitePasswordGestionnaire(passwordGestionnaire);
		if(!leGestionnaire.getPasswordGestionnaire().equals(passwordGestionnaire)) throw new MetierException();

		LinkedList<LinkedList<String>> linkJoueur; //declaração
		linkJoueur=new LinkedList<LinkedList<String>>(); //inicialização

		LinkedList<String>[] atributos;
		int tamanhoLinked=joueurs.size();
		atributos=new LinkedList[tamanhoLinked];

		int soma=0;
		// System.out.println("o tamanho é "+tamanhoLinked);
		for(int i1=0;i1<tamanhoLinked;i1++){

			atributos[i1]=new LinkedList<String>();

			atributos[i1].add(joueurs.get(i1).getNom());
			atributos[i1].add(joueurs.get(i1).getPrenom());
			atributos[i1].add(joueurs.get(i1).getPseudo());
			atributos[i1].add(String.valueOf(joueurs.get(i1).getSommeEnJetons()));


			//percorre todas as competições;
			int tamanho2=competitions.size();
			for(int i2=0 ; i2<tamanho2 ;i2++){
				//agora a gnt acessa para cada competição todas as suas paris e ve se o joueur apostou em uma pari,
				//se apostou soma na variavel soma


				//todas as paris de uma competição
				int tamanho3=competitions.get(i2).getParis().size();


				for(int i3=0;i3<tamanho3;i3++){

					if(competitions.get(i2).getParis().get(i3).getJoueurReference().getPseudo().equals(joueurs.get(i1).getPseudo())){

						soma=soma+(int)competitions.get(i2).getParis().get(i3).getMiseEnJetons();

					}

				}

			}

			atributos[i1].add(String.valueOf(soma));
			linkJoueur.add(atributos[i1]);
			soma=0;

		}


		//System.out.println("O tamanho no final é "+linkJoueur.size());

		return linkJoueur;
	}








	// Les méthodes avec mot de passe utilisateur



	/**
	 * miserVainqueur  (parier sur une compétition, en désignant un vainqueur).
	 * Le compte du joueur est débité du nombre de jetons misés.
	 *
	 * @param pseudo   le pseudo du joueur
	 * @param passwordJoueur  le password du joueur
	 * @param miseEnJetons   la somme en jetons à miser
	 * @param competition   le nom de la compétition  relative au pari effectué
	 * @param vainqueurEnvisage   le nom du compétiteur  sur lequel le joueur mise comme étant le  vainqueur de la compétition
	 *
	 * @throws MetierException levée si la somme en jetons est négative.
	 * @throws JoueurInexistantException levée si il n'y a pas de
	 * joueur avec les mêmes pseudos et password.
	 * @throws CompetitionInexistanteException   levée si il n'existe pas de compétition de même nom.
	 * @throws CompetitionException levée
	 * si <code>competition</code> ou <code>vainqueurEnvisage</code> sont invalides,
	 * si il n'existe pas un compétiteur de  nom <code>vainqueurEnvisage</code> dans la compétition,
	 * si la compétition n'est plus ouverte (la date de clôture est dans le passé).
	 * @throws JoueurException   levée
	 * si <code>pseudo</code> ou <code>password</code> sont invalides,
	 * si le <code>compteEnJetons</code> du joueur est insuffisant (il deviendrait négatif).
	 */
	public void miserVainqueur(String pseudo, String passwordJoueur, long miseEnJetons, String competition, String vainqueurEnvisage) throws MetierException, JoueurInexistantException, CompetitionInexistanteException, CompetitionException, JoueurException  {

		if (miseEnJetons<=0) throw new MetierException();

		int cont1=0;
		int var1=0; //var1 da a posição do objeto jogador na limked list joueurs
		for(int i=0;i<joueurs.size();i=i+1){

			if(joueurs.get(i).getPseudo().equals(pseudo)){

				cont1=1;
				var1=i;
				//String nom=joueurs.get(i).getNom();
				//String prenom=joueurs.get(i).getPrenom();

			}

		}

		if (cont1==0) throw new JoueurInexistantException();





		int cont3=0;
		int var3=0; // var 3 da a posição do objeto competição na linked list competition
		for(int i=0;i<competitions.size();i=i+1){

			if(competitions.get(i).getCompetition().equals(competition)){

				cont3=1;
				var3=i;

			}

		}

		if(cont3==0) throw new CompetitionInexistanteException();

		if (competitions.get(var3).getDateCloture().estDansLePasse()==true) throw new CompetitionException();


		int cont2=0;
		int var2=0; //var 2 da a posição do objeto competidor na linked list competidores guardada em competição
		for(int i=0;i<competitions.get(var3).getCompetiteurs().size();i=i+1){

			if(competitions.get(var3).getCompetiteurs().get(i).getNom().equals(vainqueurEnvisage)){

				cont2=1;
				var2=i;

			}

		}

		if(cont2==0) throw new CompetitionException();


		//receber os argumentos, criar um objeto pari e guardar na linked list do objeto competição com o nome dado

		//antes d ecomeçar e necessario saber quem é o objeto jogador, então procura-se na liked list que esta aqui em site d eparis



		Pari pari= new Pari(miseEnJetons,joueurs.get(var1) ,competitions.get(var3).getCompetiteurs().get(var2) );

		//Agora adiciona-se esta Pari a uma Liked List que esta no objeto competição que agora a gnt ja sabe quem é

		//adiciona aposta na liked list de apostas da ocmpetição
		//debita a conta do cidadao
		if (joueurs.get(var1).getSommeEnJetons()<miseEnJetons) throw new JoueurException();



		competitions.get(var3).getParis().add(pari);

		joueurs.get(var1).setSommeEnJetons(joueurs.get(var1).getSommeEnJetons()-miseEnJetons);

		//System.out.println(" "+joueurs.get(var1).getPseudo()+" apostou "+ miseEnJetons+" na competição" +competitions.get(var3).getCompetition());













	}








	public LinkedList <LinkedList <String>> consulterCompetitions(){


		//a ideia é enviar uma linked list do tipo linked list, sendo
		// a cada elemento da primeira liked list nada mais que
		// uma competição e a segunda liked list tendo dois
		//elementos, o primeiro é o nome da ocmpetição e o segundo
		//é a data de fechamento.....
		//System.out.println("entrou");
		LinkedList<LinkedList<String>> competitions2;
		//System.out.println("entrou2");
		competitions2=new LinkedList<LinkedList<String>> ();
		//System.out.println("entrou3");

		LinkedList<String>[] info1;
		info1 = new LinkedList[competitions.size()];

		// System.out.println("o tamano novo e "+competitions2.size());

		//acho que ate o momento a melhor ideia seria dar um for e percorrer
		// toda a likedlist e ir endereçando cada posição

		for (int i=0;i<competitions.size();i++){

			info1[i]=new LinkedList<String>();
			//   System.out.println("entrou no for");
			info1[i].add(competitions.get(i).getCompetition());
			// System.out.println("A linked list é "+info1[i]);
			info1[i].add(competitions.get(i).getDateCloture().toString());
			//System.out.println("A linked list é "+info1[i]);
			competitions2.add(info1[i]);

			//System.out.println("A linked list é "+competitions2);

		}

		//System.out.println("o tamanho e "+competitions.size());

		//System.out.println("A liked list é "+competitions2);














		//return new LinkedList <LinkedList <String>>();
		return competitions2;
	}








	public LinkedList <String> consulterCompetiteurs(String competition) throws CompetitionException, CompetitionInexistanteException{

		//o metodo recebe o nome da competição, entao fazer um for e percorrer a likedList toda e ver qual é o
		// a competição correta e então devolver a linked list de strings com o nome dos competidores desta competição.

		LinkedList<String> competConsult1;
		competConsult1=new LinkedList<String>();

		validiteCompetition(competition);
		int verdade=0;
		int var=0;
		int tamanho=competitions.size();
		for(int i=0;i<tamanho;i++){

			//percorrer toda a liked list de competição chamar o nome da competição e comparar
			// com a string recebida como metodo

			if(competitions.get(i).getCompetition().equals(competition)){
				verdade=1;
				var=i;
			}

		}

		if(verdade==0) throw new CompetitionInexistanteException();



		//competConsult1=competitions.get(var).getCompetiteurs();

		int tamanho2=competitions.get(var).getCompetiteurs().size();
		for(int i1=0;i1<tamanho2;i1++){

			competConsult1.add(competitions.get(var).getCompetiteurs().get(i1).getNom().toString());


		}

		// System.out.println("a lista de competidores é "+competConsult1);




		return competConsult1;
	}


	protected void validitePasswordGestionnaire(String passwordGestionnaire) throws MetierException {
		if (passwordGestionnaire==null) throw new MetierException();
		if (!passwordGestionnaire.matches("[0-9A-Za-z]{8,}")) throw new MetierException();
	}







	protected void validiteCompetition(String competition )throws CompetitionException{

		if (competition==null) throw new CompetitionException();
		if (!competition.matches("[0-9A-Za-z]{4,}")) throw new CompetitionException();


	}














}


