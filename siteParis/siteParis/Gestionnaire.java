package siteParis;


public class Gestionnaire {

	/**
	 * @uml.property  name="passwordGestionnaire"
	 */
	private String passwordGestionnaire;
   
   public Gestionnaire(String password)throws MetierException{
      validitePasswordGestionnaire(password);        
      this.setPasswordGestionnaire(password);
   }

	/**
	 * Getter of the property <tt>passwordGestionnaire</tt>
	 * @return  Returns the passwordGestionnaire.
	 * @uml.property  name="passwordGestionnaire"
	 */
	public String getPasswordGestionnaire() {
		return passwordGestionnaire;
	}

	/**
	 * Setter of the property <tt>passwordGestionnaire</tt>
	 * @param passwordGestionnaire  The passwordGestionnaire to set.
	 * @uml.property  name="passwordGestionnaire"
	 */
	public void setPasswordGestionnaire(String passwordGestionnaire) {
		this.passwordGestionnaire = passwordGestionnaire;
      
	}
   protected void validitePasswordGestionnaire(String passwordGestionnaire) throws MetierException {
	   if (passwordGestionnaire==null) throw new MetierException();
	   if (!passwordGestionnaire.matches("[0-9A-Za-z]{8,}")) throw new MetierException();
	}

}
