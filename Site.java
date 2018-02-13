package campingPack;

import javax.swing.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**********************************************************************
 * The Site class is the super class of RV and Tent. It has abstract
 * methods for RV and Tent, getCost and setCost. The occupant,
 * reserve date, site number, and duration of each site. If any
 * of these fail, a custom exception is thrown.
 *
 * @author David Baas, Noah Goncher, Matthew Kennedy
 *
 *@version 1.4
 *********************************************************************/
abstract public class Site implements Serializable {

	/** Serial Version for the Site class. */
	public static final long serialVersionUID = 1L;

	/** Instance variable for the name of the site occupant. */
	protected String occupant;

	/** Gregorian Calendar that holds the initial reservation date. */
	protected GregorianCalendar reservDate;

	/** Instance variable for the duration of the occupants stay. */
	protected int duration;

	/** Instance variable for the site number of the occupant. */
	protected int siteNum;

	/** Instance variable for the index in undoDiff and redoDiff
	 * lists. Used for undoing and redoing. */
	private int index;

	/******************************************************************
	 * Makes a blank site. All values are 0.
	 *****************************************************************/
	public Site(){
		super();
	}

	/******************************************************************
	 * Creates a site based off name, start date, end date, and site
	 * number. First, checks the name input and 2 date inputs for
	 * validity. Next, makes the duration based off the 2 dates, then
	 * checks the duration. Last, checks the site input, then creates
	 * the gregorian calendar object, and assigns the values.
	 * 
	 * @param name The site reserver's name.
	 * @param startDate The start date of the reservation.
	 * @param endDate The end Date of the reservation.
	 * @param site The site number of the reservation.
	 * @throws IllegalNameException if name input is invalid.
	 * @throws IllegalDateExceptio if date input is invalid.
	 * @throws IllegalDurationException if duration is invalid.
	 * @throws IllegalSiteException if site input is invalid.
	 * @throws Exception for any non-specific errors. 
	 *****************************************************************/
	public Site(String name, String startDate, String endDate, int site) 
			throws IllegalNameException,
			IllegalDateException,
			IllegalDurationException,
			IllegalSiteException,
			Exception {

		int tDuration;
		GregorianCalendar startReserve;
		
		nameInputIsValid(name);
		dateInputIsValid(startDate);
		dateInputIsValid(endDate);

		tDuration = getDuration(startDate, endDate);
		durationInputIsValid(tDuration);
		siteInputIsValid(site);

		String[] temp = startDate.split("/");
		startReserve = new GregorianCalendar(
				Integer.parseInt(temp[2]),
				Integer.parseInt(temp[0]) - 1,
				Integer.parseInt(temp[1]));

		this.occupant = name;

		this.reservDate = startReserve;

		this.duration = tDuration;

		this.siteNum = site;
	}

	/******************************************************************
	 * Creates a site based off name, Gregorian Calendar, stay, and site.
	 * number. First, checks the name input and 2 date inputs for
	 * validity. Next, makes the duration based off the 2 dates, then
	 * checks the duration. Last, checks the site input, then creates
	 * the gregorian calendar object, and assigns the values.
	 * 
	 * @param name The site reserver's name.
	 * @param date The start date of the reservation.
	 * @param stay The duration of the reservation.
	 * @param site The site number of the reservation.
	 * @throws IllegalNameException if name input is invalid.
	 * @throws IllegalDateExceptio if date input is invalid.
	 * @throws IllegalDurationException if duration is invalid.
	 * @throws IllegalSiteException if site input is invalid.
	 * @throws Exception for any non-specific errors. 
	 *****************************************************************/
	public Site(String name, GregorianCalendar date, int stay, int site) 
			throws IllegalNameException,
			IllegalDateException,
			IllegalDurationException,
			IllegalSiteException,
			Exception {

		nameInputIsValid(name);
		dateInputIsValid(formatCalendar(date));
		durationInputIsValid(stay);
		siteInputIsValid(site);

		this.occupant = name;

		this.reservDate = date;

		this.duration = stay;

		this.siteNum = site;
	}

	/******************************************************************
	 * Returns the occupant of the site.
	 * 
	 * @return The occupant's name.
	 *****************************************************************/	
	public String getOccupant() {

		return occupant;

	}

	/******************************************************************
	 * Returns the reserve date of the reservation.
	 * 
	 * @return A GregorianCalendar with the reservation date.
	 *****************************************************************/
	public GregorianCalendar getReservDate() {

		return reservDate;

	}

	/******************************************************************
	 * Returns the duration of the reservation.
	 * 
	 * @returns The site duration.
	 *****************************************************************/
	public int getDuration() {

		return duration;

	}

	/******************************************************************
	 * Returns the site number for a reservation.
	 * 
	 * @return The reservation site number.
	 *****************************************************************/	
	public int getSiteNum() {

		return siteNum;

	}

	/******************************************************************
	 * Returns the index of a site in undoDiff and redoDiff.
	 * 
	 * @return The Site's index.
	 *****************************************************************/	
	public int getIndex() {
		return index;
	}

	/******************************************************************
	 * Sets the index for a site.
	 * 
	 * @param Index the index of the site.
	 *****************************************************************/	
	public void setIndex(int index) {
		this.index = index;
	}

	/******************************************************************
	 * Abstract method. Gets the cost of a reservation.
	 * 
	 * @return The cost of a reservation.
	 *****************************************************************/	
	abstract public double getCost();

	/******************************************************************
	 * Abstract method. Sets the cost of a reservation.
	 * 
	 * @param The cost of a reservation.
	 *****************************************************************/
	abstract public void setCost();

	/******************************************************************
	 * Compares if two sites are equal.
	 * 
	 * @param s The site that is being compared to.
	 * @return True if they are equal, false if not.
	 *****************************************************************/
	public boolean equals(Site s) {
		if (occupant.equals(s.occupant) &&
				reservDate.equals(s.reservDate) &&
				duration == s.duration &&
				siteNum == s.siteNum)
			return true;
		return false;
	}

	/******************************************************************
	 * The nameInputIsValid method is a method that tests all input
	 * from the user to make sure the name is valid.
	 * If it isn't, the method will throw IllegalArgumentExceptions.
	 *****************************************************************/
	private void nameInputIsValid(String name) {
		String temp;
		
		//the user cannot leave the text field as this String
		String defaultName = "Firstname Lastname";

		//is the text field blank or left default?
		if (name.equals("") || name.equals(defaultName)) {
			throw new IllegalNameException();
		}

		temp = name.replaceAll("\\s", "");
		//does the entered text contain any sort of number?
		for (int i = 0; i < temp.length(); i++) {
			if (!Character.isLetter(temp.charAt(i))) {
				throw new IllegalNameException();
			}
		}

		//does the entered text have a space before or after the text?
		if ((name.charAt(0) == ' ') ||
				(name.charAt(name.length() - 1) == ' ')) {
			throw new IllegalNameException();
		}

		//does the entered text contain anything other than a letter,
		//including a space?
		for (int i = 1; i < name.length(); i++) {
			if (name.charAt(i) == ' ') {
				if (!Character.isLetter(name.charAt(i - 1)) ||
						(!Character.isLetter(name.charAt(i + 1)))) {
					throw new IllegalNameException();
				}
			}
		}

		//does the entered text contain more than one space?
		int spaceCount = name.length() -
				name.replace(" ", "").length();
		if (spaceCount != 1) {
			throw new IllegalNameException();
		}
	}

	/******************************************************************
	 * The dateInputIsValid method checks if the user-entered date is a
	 * valid date.  It will check for dates behind November, since by
	 * the time this program is used, it will be about that date.  It
	 * also checks for improper date inputs such as months greater than
	 * 12, days greater than 31 or 30 (depending), and the month of
	 * February when it is a leap year or not.
	 *****************************************************************/
	public static void dateInputIsValid(String date) {
		if (date.equals("mm/dd/yyyy")) {
			throw new IllegalDateException();
		}

		//check if there are any non-numeric and non-slash chars in input.
		String noSlashesDate = date.replaceAll("/", "");
		for (int i = 0; i < noSlashesDate.length(); i++) {
			if (!Character.isDigit(noSlashesDate.charAt(i))) {
				throw new IllegalDateException();
			}
		}

		//if there aren't exactly 2 slashes.
		if (date.length() - noSlashesDate.length() != 2) {
			throw new IllegalDateException();
		}

		//split the array, verify the days are between 0 and 31, 
		//the month is between 1 and 12
		//and that the year is 4 numbers that are 
		//greater than 2017 or greater.
		String temp[] = date.split("/");
		int dateArray[] = new int[3];
		for (int i = 0; i < 3; i++)
			dateArray[i] = Integer.parseInt(temp[i]);

		boolean invalidDate = false;
		if (dateArray[1] < 1 ||dateArray[1] > 31)
			invalidDate = true;
		else if (dateArray[0] < 1 || dateArray[0] > 12 )
			invalidDate = true;
		else if (Integer.toString(dateArray[2]).length() != 4 
				|| dateArray[2] < 2017 || (dateArray[2] > 2036))
			invalidDate = true;

		if (invalidDate) {
			throw new IllegalDateException();
		}

		//checks if year is 2017 and month is before November.
		if (dateArray[2] == 2017)
			if (dateArray[0] < 11)
				throw new IllegalDateException();

		int maxDays = CampingCalc.getMaxDays(
				dateArray[0], 
				dateArray[2]);

		if (dateArray[1] > maxDays) {
			throw new IllegalDateException();
		}
	}
	/******************************************************************
	 * Sets the duration given a startDate string and endDate string.
	 * 
	 * @param startDate A string that is the startDate.
	 * @param endDate A string that is the endDate.
	 * @return The duration between the two dates.
	 *****************************************************************/
	private int getDuration(String startDate, String endDate) {
		String[] temp1, temp2;
		int[] tempDates1 = new int[3];
		int[] tempDates2 = new int[3];
		int[] sortedDates1 = new int[3];
		int[] sortedDates2 = new int[3];

		//create the date arrays.
		temp1 = startDate.split("/");
		temp2 = endDate.split("/");

		//set the date arrays to integers.
		for (int i = 0; i < 3; i++) {
			tempDates1[i] = Integer.parseInt(temp1[i]);
			tempDates2[i] = Integer.parseInt(temp2[i]);
		}

		//organize the date arrays to yyyy/mm/dd
		sortedDates1[0] = tempDates1[2];
		sortedDates1[1] = tempDates1[0];
		sortedDates1[2] = tempDates1[1];

		sortedDates2[0] = tempDates2[2];
		sortedDates2[1] = tempDates2[0];
		sortedDates2[2] = tempDates2[1];

		//get the duration.
		return CampingCalc.getDifferenceInDates
				(sortedDates1, sortedDates2) + 1;
	}


	/******************************************************************
	 * The durationInputIsValid method checks to see if the duration is
	 * between 366 and 0.
	 *****************************************************************/
	private void durationInputIsValid(int duration) {
		if (duration > 366 || duration < 1)
			throw new IllegalDurationException();
	}

	/******************************************************************
	 * The durationInputIsValid method checks to see if the duration is
	 * between 366 and 0.
	 *****************************************************************/
	private void siteInputIsValid(int site) {
		if (site > 5 || site < 1)
			throw new IllegalSiteException();
	}

	/******************************************************************
	 * Returns a reservation date as a string in format MM-dd-yyyy.
	 * 
	 * @param calendar The gregorian calendar to turn into a string.
	 * @return The string version of the date.
	 *****************************************************************/
	public String formatCalendar(GregorianCalendar calendar) {
		String objStr = calendar.toString();
		String year = objStr.substring(objStr.indexOf("YEAR=") + 5,
				objStr.indexOf(",MONTH="));
		String month = objStr.substring(objStr.indexOf("MONTH=") + 6, 
				objStr.indexOf(",WEEK_OF_YEAR="));
		String day=objStr.substring(objStr.indexOf("DAY_OF_MONTH=")+13, 
				objStr.indexOf(",DAY_OF_YEAR="));
		String dateFormatted = 
				(Integer.parseInt(month) +1) + "/" + day + "/" + year;
		return dateFormatted;
	}

}

