package campingPack;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JOptionPane;

/**********************************************************************
 * The RV class is used to take in information when a user reserves a
 * site that contains an RV.  It will take in a name, a date that they
 * reserve, the amount of days that they stay, the site they reserve,
 * and the amount of power required for their RV.
 *
 * @author David Baas, Noah Goncher, Matthew Kennedy
 *
 *@version 5.0
 **********************************************************************/
public class RV extends Site {

	/* Instance variable to store the amount of power of an RV */
	private int power;

	/* Instance variable to store the cost of an RV site */
	private int cost;

	/******************************************************************
	 * A constructor with no parameters. It is used to instantiate a
	 * blank RV site.
	 **********************************************************************/
	public RV() {
		super();
		this.power = 0;
	}

	/******************************************************************
	 * This is the main constructor. It takes in all the required
	 * values to reserve a site, plus the amount of power.  It passes
	 * the acquired values to the superclass, which is Site, and sets
	 * the amount of power to the instance variable "power".
	 *
	 * @param name - sets the reserver's name
	 * @param date - sets the reservation date
	 * @param stay - sets the amount of days the site is taken
	 * @param siteNum - sets the site taken
	 * @param power - sets the amount of power needed for an RV
	 * @throws Exception If a general exception is caught.
	 * @throws IllegalSiteException If the site num is not between 1 and 5
	 * @throws IllegalDurationException If the duration is less than 1 or
	 * greater than 366.
	 * @throws IllegalDateException If the date is invalid
	 * @throws IllegalNameException If the name is invalid
	 * @throws IllegalPowerException if the power is out of bounds
	 **********************************************************************/
	public RV(String name, GregorianCalendar date, int stay, int siteNum, int power) 
			throws IllegalNameException, 
			IllegalDateException, 
			IllegalDurationException, 
			IllegalSiteException,
			IllegalPowerException,
			Exception {


		//pass the given info to the Site class (superclass)
		super(name, date, stay, siteNum);

		checkPower(power);
		//set the amount of power provided to instance variable "power"
		this.power = power;
	}

	/******************************************************************
	 * This is the main constructor. It takes in all the required
	 * values to reserve a site, plus the amount of power.  It passes
	 * the acquired values to the superclass, which is Site, and sets
	 * the amount of power to the instance variable "power".
	 *
	 * @param name - sets the reserver's name
	 * @param startDate - sets the reservation start date
	 * @param endDate - sets the reservation end date.
	 * @param siteNum - sets the site taken
	 * @param power - sets the amount of power needed for an RV
	 * @throws Exception If a general exception is caught.
	 * @throws IllegalSiteException If the site num is not between 1 and 5
	 * @throws IllegalDurationException If the duration is less than 1 or
	 * greater than 366.
	 * @throws IllegalDateException If the date is invalid
	 * @throws IllegalNameException If the name is invalid
	 **********************************************************************/
	public RV(String name, String startDate, String endDate, int siteNum, int power) 
			throws IllegalNameException, 
			IllegalDateException, 
			IllegalDurationException, 
			IllegalSiteException,
			IllegalPowerException,
			Exception {

		//pass the given info to the Site class (superclass)
		super(name, startDate, endDate, siteNum);
		checkPower(power);

		//set the amount of power provided to instance variable "power"
		this.power = power;
	}

	/******************************************************************
	 * The getPower method returns the amount of power that the RV
	 * needs to power itself.
	 *
	 * @return - amount of power needed for RV.
	 *****************************************************************/
	public int getPower(){

		//return the instance variable "power".
		return this.power;

	}

	/******************************************************************
	 * Private method that ensures that the amperage is either 30 Amps,
	 * 40 Amps, or 50 Amps
	 * @param power Amperage input being checked
     * @throws IllegalPowerException if the power is out of bounds
	 */
	protected void checkPower(int power) {
		if (power != 30 && power != 40 && power != 50)
			throw new IllegalPowerException();
	}

	/******************************************************************
	 * The setPower method sets the provided power to the instance
	 * variable "power".
	 *
	 * @param power - user-entered amount of power.
	 *****************************************************************/
	public void setPower(int power) {
		this.power = power;
	}

	/******************************************************************
	 * The setCost method overrides the cost function from Site. It
	 * sets the cost to reserve a site for an RV. It is simply $30.00
	 * times the amount of days a person stays on the site.
	 */
	public void setCost(){

		//specific cost equation for an RV.
		this.cost = 30 * duration;

	}

	/******************************************************************
	 * The getCost function simply returns the calculated cost of the
	 * reservation for the RV. It overrides the getCost function in
	 * Site.
	 *
	 * @return - return the cost to reserve an RV.
	 */
	public double getCost(){

		return cost;

	}

	/******************************************************************
	 * The equals method overrides the Site equals method to see if
	 * Site is an instance of an RV. It has a parameter of a Site s
	 * and it returns true if the new RV instance of Site s equals
	 * whatever values are currently stored in Site. It will return
	 * false otherwise.
	 *
	 * @param s - a Site object to be compared to the current instance
	 *          of site.
	 * @return - true or false, whether s equals the current instance
	 * of Site or not.
	 */
	@Override
	public boolean equals(Site s) {

		//is s an instance of RV?
		if(s instanceof RV)

			//is s equal to the current instance of Site?
			if(super.equals(s) && power == ((RV) s).getPower())

				//return true if so
				return true;
		//return false otherwise
		return false;
	}
}