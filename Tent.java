package campingPack;

import java.util.GregorianCalendar;

import javax.swing.JOptionPane;

/**********************************************************************
 * The Tent class is used to take in information when a user reserves a
 * site that contains one or more tents.  It will take in a name, a
 * date that they reserve, the amount of days that they stay, the site
 * they reserve, and the amount of tents on the site.
 *
 * @author David Baas, Noah Goncher, Matthew Kennedy
 *
 *@version 1.4
 ********************************************************************/
public class Tent extends Site {

	/** Instance variable to store the amount of tents on a site. */
	private int numTenters;

	/** Instance variable to store the cost of a site (function to
	 * calculate is below). */
	private int cost;

	/******************************************************************
	 * A constructor with no parameters. It is used to instantiate a
	 * blank tent site.
	 */
	public Tent() {

		//blank instance of Site
		super();

		//instantiate numTenters to zero
		this.numTenters = 0;
	}

	/******************************************************************
	 * This is the main constructor. It takes in all the required
	 * values to reserve a site, plus the number of tents.  It passes
	 * the acquired values to the superclass, which is Site, and sets
	 * the number of tents to the instance variable "numTenters".
	 *
	 * @param name - sets the reserver's name
	 * @param date - sets the reservation date
	 * @param stay - sets the amount of days the site is taken
	 * @param siteNum - sets the site taken
	 * @param numTenters - sets the number of tents in the site
	 * @throws Exception If a general exception is caught.
	 * @throws IllegalSiteException If the site num is not between 1 and 5
	 * @throws IllegalDurationException If the duration is less than 1 or
	 * greater than 366.
	 * @throws IllegalDateException If the date is invalid
	 * @throws IllegalNameException If the name is invalid
	 * @throws IllegalTentsException if the num of Tenters is below 1 or
	 * greater than 100.
	 **********************************************************************/
	public Tent(String name, GregorianCalendar date, int stay, int siteNum, int numTenters) 
			throws IllegalNameException, 
			IllegalDateException, 
			IllegalDurationException, 
			IllegalSiteException,
			IllegalTentsException,
			Exception {

		//pass the given info to the Site class (superclass)
		super(name, date, stay, siteNum);
		
		checkNumTents(numTenters);
		this.numTenters = numTenters;
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
	 * @param numTenters - sets the number of Tenters on the site
	 * @throws Exception If a general exception is caught.
	 * @throws IllegalSiteException If the site num is not between 1 and 5
	 * @throws IllegalDurationException If the duration is less than 1 or
	 * greater than 366.
	 * @throws IllegalDateException If the date is invalid
	 * @throws IllegalNameException If the name is invalid
	 * @throws IllegalTentsException if the num of Tenters is below 1 or
	 * greater than 100.
	 **********************************************************************/
	public Tent(String name, String startDate, String endDate, int siteNum, int numTenters)
			throws IllegalNameException, 
			IllegalDateException, 
			IllegalDurationException, 
			IllegalSiteException,
			IllegalTentsException,
			Exception {

		//pass the given info to the Site class (superclass)
		super(name, startDate, endDate, siteNum);

		checkNumTents(numTenters);
		this.numTenters = numTenters;
	}

	/******************************************************************
	 * The getNumTenters method gets the number of tents on the site.
	 *
	 * @return - numTenters, the number of tents on the site
	 *****************************************************************/
	public int getNumTenters(){

		//return the number of tents
		return this.numTenters;

	}

	/******************************************************************
	 * The setNumTenters method takes in a number of tents on a site
	 * and assigns it to the instance variable "numTenters".  Also
	 * checks to see if the user-entered value for "numTenters" is
	 * greater than zero.
	 *
	 * @param numTenters - an entered number of tents
	 * @throws IllegalTentsException if the num of tenters is less than
	 * 1 or greater than 100
	 *****************************************************************/
	public void setNumTenters(int numTenters) {

		//set "this" number of tents to the provided number of tents
		if(numTenters < 1){
			throw new IllegalTentsException();
		}
		this.numTenters = numTenters;

	}

	/******************************************************************
	 * Private method that checks the number of tenters on a site to
	 * make sure the number is within the correct bounds
	 *
	 * @param numTenters the number of tenters in a site being checked
	 * @throws IllegalTentsException if the tenter range is less than 1
	 * or greater than 100
	 *****************************************************************/
    protected void checkNumTents(int numTenters) {
		if (numTenters < 1 || numTenters > 100)
			throw new IllegalTentsException();
	}

	/******************************************************************
	 * The setCost method is a method from the Site class that is
	 * overridden in this class to perform a specific calculation of
	 * the cost to rent a site for a tent.  It takes the amount of
	 * days a person/people stay and multiplies it by 3, since it's 3
	 * dollars per day for the site, and then multiplies that by the
	 * amount of tents on the site.
	 *****************************************************************/
	public void setCost(){

		//set the cost of a tent to the following equation. 3 is the
		//daily rate.
		this.cost = (3 * duration) * numTenters;

	}

	/******************************************************************
	 * The getCost method simply returns the cost calculated by the
	 * setCost method.
	 *
	 * @return - the cost of the tent site
	 *****************************************************************/
	public double getCost(){

		//return the cost of a single site.
		return this.cost;
	}

	/******************************************************************
	 * The equals method overrides the Site equals method to see if
	 * Site is an instance of a Tent. It has a parameter of a Site s
	 * and it returns true if the new Tent instance of Site s equals
	 * whatever values are currently stored in Site. It will return
	 * false otherwise.
	 *
	 * @param s - a Site object to be compared to the current instance
	 *          of site.
	 * @return - true or false, whether s equals the current instance
	 * of Site or not.
	 *****************************************************************/
	@Override
	public boolean equals(Site s) {
		//check to see if Site is an instance of a tent
		if(s instanceof Tent)
			//is it equal to the current instance of Site?
			if(super.equals(s) && numTenters == ((Tent) s).getNumTenters())
				//if it's equal
				return true;
		//if it's false
		return false;
	}
}