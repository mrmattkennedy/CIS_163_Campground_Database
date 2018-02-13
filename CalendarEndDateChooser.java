package campingPack;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

/***********************************************************************
 * Creates a Calendar for choosing the ending date for a reserveration.
 * Extends JFrame, very similar to CalendarDate chooser, with differences
 * in disabling buttons and what is being displayed.
 * 
 * @author kennemat
 * @version Fall 2017
 **********************************************************************/
public class CalendarEndDateChooser extends JDialog implements ActionListener {
	/** Panel for storing the date buttons. */
	private JPanel datesPanel;

	/** Panel for storing the combo boxes to pick a month. */
	private JPanel comboBoxPanel;

	/** Panel for storing the OK and Cancel buttons. */
	private JPanel buttonPanel;

	/** JPanel that holds the color key code. */
	private JPanel colorPanel;

	/** Constant String array for labeling the days of the week. */
	private final String[] dayLabels = 
		{"  Sun", 
				"  Mon", 
				"  Tue", 
				"  Wed", 
				"  Thu", 
				"  Fri", 
		"  Sat"};

	/** Constant String array for labeling the months. */
	private final String[] monthLabels = 
		{"January", 
				"February", 
				"March", 
				"April", 
				"May", 
				"June", 
				"July", 
				"August", 
				"September", 
				"October", 
				"November", 
		"December"};

	/** Constant String array for labeling the months available in 2017. */
	private final String[] shortYearMonthLabels = 
		{"November", 
		"December"};

	/** Constant value for the maximum number of years. */
	private final int maxYears = 20;

	/** Constant value for the maximum number of months. */
	private final int maxMonths = 12;

	/** String array for labeling the available years. */
	private String[] yearLabels;

	/** Label array for labeling the days of the week. */
	private JLabel[] dateLabels;

	/** JComboBox for the months. */
	private JComboBox monthCombo;

	/** JComboBox for the months in 2017. */
	private JComboBox shortYearMonthCombo;

	/** JComboBox for the years. */
	private JComboBox yearCombo;

	/** Boolean to determine if the yearCombo is set to 2017 or not. */
	private boolean shortYearStatus = true;

	/** JLabel that shows what date is currently selected. */
	private JLabel currentlySelectedLabel;

	/** JButton that signifies the user has selected a date. */
	private JButton okButton;

	/** JButton that signifies the user is done. */
	private JButton cancelButton;

	/** Array list of JButtons that is the buttons in the datePanel. */
	private ArrayList<JButton> buttonList;

	/** Font of the buttons, all set to smaller fonts so they display. */
	private Font dateFont = new Font("SansSerif", Font.PLAIN, 10);

	/** Passes the DialogCheckIn as a "parent" container, 
	 * uses methods in DialogCheckIn to set labels. */
	private DialogCheckIn output;

	/** Start date. This value comes from output reading the label. */
	private String startDate;

	/** 2D int array for storing the starting day of the week
	 * for each month. */
	private int[][] startingDateArray;

	/** An int that is the currently selected site from DialogCheckIn. */
	private int currentSite;

	/** Index of the date closest to the current start date. */
	private int shortestReserveIndex = -1;

	/** Array of the start date, read from startDate string. */
	private int[] dateArray;

	/** End date, either the shortest date which is less than a year, or
	 * 1 year after the start date, whichever comes first. */
	private int[] endDate;

	/** ArrayList of the taken dates along with the site number
	 * at the end of the string. */
	private ArrayList<String> takenDays;

	/*******************************************************************
	 * Only constructor for the class. Takes in information about already
	 * reserved sites, and creates the various JComponents here.
	 * 
	 * @param output The "parent" JDialog. Used to get info on start date.
	 * @param takenDays ArrayList<String> of days that are taken.
	 * @param currentSite Current site selected in output.
	 ******************************************************************/
	public CalendarEndDateChooser(
			DialogCheckIn output, 
			ArrayList<String> takenDays,
			int currentSite) {

		this.output = output;
		this.currentSite = currentSite;
		this.takenDays = takenDays;
		startDate = output.getStartDateText();

		makePanels();
		makeJComponents();
		addListeners();
		addComponents();
		addColorPanelComponents();

		fillStartingDateArray();
		fillTakenDates();
		getEndDate();

		finalizeThis();

	}

	/*******************************************************************
	 * Creates the 4 main JPanels.
	 ******************************************************************/
	private void makePanels() {
		datesPanel 		= new JPanel();
		comboBoxPanel 	= new JPanel();
		buttonPanel 	= new JPanel();
		colorPanel		= new JPanel();
	}

	/*******************************************************************
	 * Creates the JComponents for the main panels and dateArray.
	 ******************************************************************/
	private void makeJComponents() {
		yearLabels = new String[maxYears];
		for (int i = 0; i < maxYears; i++)
			yearLabels[i] = Integer.toString(2017 + i);

		monthCombo 			= new JComboBox(monthLabels);
		yearCombo 			= new JComboBox(yearLabels);
		shortYearMonthCombo = new JComboBox(shortYearMonthLabels);

		okButton	 = new JButton("OK");
		cancelButton = new JButton("Cancel");
		buttonList 	 = new ArrayList<JButton>();

		currentlySelectedLabel = new JLabel("Currently: mm/dd/yyyy");
		dateLabels = new JLabel[7];
		for (int i = 0; i < 7; i++) {
			dateLabels[i] = new JLabel(dayLabels[i]);
			dateLabels[i].setFont(dateFont);
		}

		String[] tempArray = startDate.split("/");
		dateArray = new int[3];
		for(int i = 0; i < tempArray.length; i++) {
			dateArray[i] = Integer.parseInt(tempArray[i]);
		}
	}

	/*******************************************************************
	 * Adds this as an action listener to the JComboBoxes and JButtons.
	 ******************************************************************/
	private void addListeners() {
		monthCombo.addActionListener(this);
		yearCombo.addActionListener(this);
		shortYearMonthCombo.addActionListener(this);
		okButton.addActionListener(this);
		cancelButton.addActionListener(this);
	}

	/*******************************************************************
	 * Adds the JComponents to the necessary panels.
	 ******************************************************************/
	private void addComponents() {
		comboBoxPanel.add(yearCombo);
		comboBoxPanel.add(shortYearMonthCombo);
		buttonPanel.add(okButton);
		buttonPanel.add(currentlySelectedLabel);
		buttonPanel.add(cancelButton);

		datesPanel.setLayout(new GridLayout(6, 7));
	}

	/*******************************************************************
	 * Creates 3 sub panels to add to the main colorPanel. These aren't
	 * instance variables because they are never used outside of this
	 * method.
	 ******************************************************************/
	private void addColorPanelComponents() {
		JPanel colorPanel1 = new JPanel();
		JPanel colorPanel2 = new JPanel();
		JPanel colorPanel3 = new JPanel();

		JLabel illegalLabel 	= new JLabel("Illegal reservation date: ");
		JLabel illegalLabelColor= new JLabel("");
		JLabel takenLabel 		= new JLabel("Taken reservation date: ");
		JLabel takenLabelColor 	= new JLabel("");
		JLabel viableLabel 		= new JLabel("Viable reservation date: ");
		JLabel viableLabelColor = new JLabel("");

		illegalLabelColor.setOpaque(true);
		illegalLabelColor.setPreferredSize(new Dimension(15, 15));
		illegalLabelColor.setBackground(Color.BLACK);

		takenLabelColor.setOpaque(true);
		takenLabelColor.setPreferredSize(new Dimension(15, 15));
		takenLabelColor.setBackground(Color.RED);

		viableLabelColor.setOpaque(true);
		viableLabelColor.setPreferredSize(new Dimension(15, 15));
		viableLabelColor.setBackground(Color.GREEN);

		colorPanel1.add(illegalLabel);
		colorPanel1.add(illegalLabelColor);
		colorPanel2.add(takenLabel);
		colorPanel2.add(takenLabelColor);
		colorPanel3.add(viableLabel);
		colorPanel3.add(viableLabelColor);

		colorPanel.add(colorPanel1);
		colorPanel.add(colorPanel2);
		colorPanel.add(colorPanel3);

		colorPanel.setLayout(new GridLayout(3, 1));
	}

	/*******************************************************************
	 * Adds the 4 main panels to this, sets the layout, puts this in the
	 * middle of the screen, sets the modal so the user can only click
	 * on this, and makes it visible.
	 ******************************************************************/
	private void finalizeThis() {
		setLayout(new BorderLayout());
		add(datesPanel, BorderLayout.CENTER);
		add(comboBoxPanel, BorderLayout.NORTH);
		add(buttonPanel, BorderLayout.SOUTH);
		add(colorPanel, BorderLayout.EAST);

		setLocationRelativeTo(null);
		setSize(425, 300);
		setResizable(false);
		setStartingMonth();
		setModal(true);
		setVisible(true);
	}
	
	/*******************************************************************
	 * Sets the starting year and month to whatever the year and month
	 * are in dateArray.
	 ******************************************************************/
	private void setStartingMonth() {
		int startingYear = dateArray[2];
		int startingMonth = dateArray[0];
		
		yearCombo.setSelectedItem(Integer.toString(startingYear));
		if (startingYear != 2017) {
			comboBoxPanel.remove(shortYearMonthCombo);
			comboBoxPanel.add(monthCombo);
			monthCombo.setSelectedItem(monthLabels[startingMonth - 1]);
			comboBoxPanel.revalidate();
			shortYearStatus = false;
			changeButtonsNormalYear();
		} else {
			shortYearMonthCombo.setSelectedItem(monthLabels[startingMonth - 11]);
			comboBoxPanel.revalidate();
			changeButtonsShortYear();
		}
	}

	/*******************************************************************
	 * Gets the starting day of the week for each month, and sets
	 * the values in startingDateArray.
	 ******************************************************************/
	private void fillStartingDateArray() {
		startingDateArray 	= new int[maxYears][maxMonths];
		int newStartDate 	= 0;
		int oldStartDate 	= 0;
		int maxDays 		= 0;
		ArrayList<Integer> temp = new ArrayList<Integer>();

		startingDateArray[0][0] = 0;
		temp.add(startingDateArray[0][0]);

		for (int i = 0; i < maxYears; i++)
			for (int j = 0; j < maxMonths; j++) {
				oldStartDate = newStartDate;
				maxDays 	 = CampingCalc.getMaxDays(j + 1, 2017 + i);
				newStartDate = ((oldStartDate + maxDays) % 7);
				temp.add(newStartDate);
			}

		int counter = 0;
		for (int i = 0; i < maxYears; i++)
			for (int j = 0; j < maxMonths; j++) {
				startingDateArray[i][j] = temp.get(counter);
				counter++;
			}
	}

	/*******************************************************************
	 * Sets the last possible selection date.
	 * If next year is a leap year:
	 * 	If it's after February:
	 * 		If it's the first of the month, end date is last day of month
	 * 			prior, 1 year later.
	 * 		If it's not, end date is 1 year later, 1 day before.
	 * 	If it's before February, end date is normal.
	 * If current year is leap year:
	 * 	If it's January:
	 * 		If it's the 1st, end date is December 31st, same year.
	 * 		If it's not the first, end date is 1 year later, 1 day
	 * 			before.
	 * 	If it's February:
	 * 		If it's the 1st, end date is last day of month prior, 1 year
	 * 			later.
	 * 		If it's not the first, end date is 1 year later, 1 day prior.
	 * 		
	 ******************************************************************/
	private void getEndDate() {
		endDate = new int[3];
		String temp;
		String[] tempArray;
		if (shortestReserveIndex == -1) {
			endDate[0] = dateArray[0];
			endDate[1] = dateArray[1];
			if ((dateArray[2] + 1) % 4 == 0) { //leap year next year.

				if (dateArray[0] > 2) { //after February.

					if (dateArray[1] == 1) {//first day of the month.
						
						endDate[0] = dateArray[0] - 1;
						endDate[2] = dateArray[2] + 1;
						endDate[1] = CampingCalc.getMaxDays(endDate[0], endDate[2]);
					} else {

						endDate[0] = dateArray[0];
						endDate[2] = dateArray[2] + 1;
						endDate[1] = dateArray[1] - 1;
					}
				} else {

					endDate[0] = dateArray[0];
					endDate[1] = dateArray[1];
					endDate[2] = dateArray[2] + 1;					
				}
			} else if (dateArray[2] % 4 == 0) {

				if (dateArray[0] == 1) {

					if (dateArray[1] == 1) {

						endDate[0] = 12;
						endDate[2] = dateArray[2];
						endDate[1] = CampingCalc.getMaxDays(endDate[0], endDate[2]);
					} else {

						endDate[0] = dateArray[0];
						endDate[2] = dateArray[2] + 1;
						endDate[1] = dateArray[1] - 1;
					}
				} else if (dateArray[0] == 2) {

					if (dateArray[1] == 1) {

						endDate[0] = dateArray[0] - 1;
						endDate[2] = dateArray[2] + 1;
						endDate[1] = CampingCalc.getMaxDays(endDate[0], endDate[2]);
					} else {

						endDate[0] = dateArray[0];
						endDate[2] = dateArray[2] + 1;
						endDate[1] = dateArray[1] - 1;
					}
				} else {
					
					endDate[0] = dateArray[0];
					endDate[1] = dateArray[1];
					endDate[2] = dateArray[2] + 1;	
				}
			} else {
				endDate[0] = dateArray[0];
				endDate[1] = dateArray[1];
				endDate[2] = dateArray[2] + 1;
			}
		} else {
			temp = takenDays.get(shortestReserveIndex);
			temp = temp.substring(0, temp.indexOf(":"));
			tempArray = temp.split("/");

			endDate[0] = Integer.parseInt(tempArray[0]);
			endDate[1] = Integer.parseInt(tempArray[1]) - 1;
			endDate[2] =Integer.parseInt(tempArray[2]);
		}

	}

	/*******************************************************************
	 * Gets the soonest reservation date from takenDays. If the date is
	 * less than a year away from dateArray, that date becomes the limit
	 * for possible select dates.
	 ******************************************************************/
	private void fillTakenDates() {
		int tempDifference;
		int shortestReserveDifference = 9999;
		String temp;
		String takenSiteNumber;
		String[] tempTaken;
		int[] tempIntTaken = new int[3];
		int[] tempIntStartDate = new int[3];

		for (int i = 0; i < takenDays.size(); i++) {
			temp = takenDays.get(i);
			takenSiteNumber = temp.substring(temp.indexOf(':') + 2);

			temp = temp.substring(0, temp.indexOf(":"));
			tempTaken = temp.split("/");

			tempIntTaken[0] = Integer.parseInt(tempTaken[2]);
			tempIntTaken[1] = Integer.parseInt(tempTaken[0]);
			tempIntTaken[2] = Integer.parseInt(tempTaken[1]);

			tempIntStartDate[0] = dateArray[2];
			tempIntStartDate[1] = dateArray[0];
			tempIntStartDate[2] = dateArray[1];

			if (Integer.parseInt(takenSiteNumber) == currentSite) {
				tempDifference = CampingCalc.getDifferenceInDates(tempIntStartDate, tempIntTaken);
				if (tempDifference != -1)
					if (tempDifference < shortestReserveDifference && tempDifference < 366) {
						shortestReserveDifference = tempDifference; 
						shortestReserveIndex = i;
					}
			}
		}
	}

	/*******************************************************************
	 * Changes the buttons for the respective month if the year isn't
	 * 2017. Clears the buttonList and datesPanel, replaces the day of
	 * the week labels, creates the new buttonList with the max days,
	 * disables the necessary buttons, and sets the layout depending
	 * on the number of buttons and start date.
	 ******************************************************************/
	private void changeButtonsNormalYear() {
		buttonList.clear();
		datesPanel.removeAll();
		int year = Integer.parseInt(((String)yearCombo.getSelectedItem())) - 2017;
		int month = monthCombo.getSelectedIndex();

		for (int i = 0; i < 7; i++) {
			dateLabels[i].setFont(dateFont);
			datesPanel.add(dateLabels[i]);			
		}

		for(int i = 0; i < startingDateArray[year][month]; i++) {
			datesPanel.add(Box.createRigidArea(new Dimension(5,0)));
		}
		int maxDays = CampingCalc.getMaxDays(month + 1, year + 2017);

		for(int i = 0; i < maxDays; i++) {
			buttonList.add(new JButton((i + 1) + ""));
			buttonList.get(i).setFont(dateFont);
			buttonList.get(i).setMargin(new Insets(0, 0, 0, 0));
			buttonList.get(i).addActionListener(this);
			datesPanel.add(buttonList.get(i));
		}

		disableButtons();

		if ((35 / (maxDays + startingDateArray[year][month]) == 0) && ((maxDays + startingDateArray[year][month]) % 35) != 0) {
			datesPanel.setLayout(new GridLayout(7, 7));
		} else
			datesPanel.setLayout(new GridLayout(6, 7));

		datesPanel.repaint();
		datesPanel.revalidate();
	}

	/*******************************************************************
	 * Changes the buttons for the respective month if the year is
	 * 2017. Clears the buttonList and datesPanel, replaces the day of
	 * the week labels, creates the new buttonList with the max days,
	 * disables the necessary buttons, and sets the layout depending
	 * on the number of buttons and start date.
	 ******************************************************************/
	private void changeButtonsShortYear() {
		buttonList.clear();
		datesPanel.removeAll();
		int year = 0;
		int month = shortYearMonthCombo.getSelectedIndex() + 10;

		for (int i = 0; i < 7; i++) {
			dateLabels[i].setFont(dateFont);
			datesPanel.add(dateLabels[i]);			
		}

		for(int i = 0; i < startingDateArray[year][month]; i++) {
			datesPanel.add(Box.createRigidArea(new Dimension(5,0)));
		}
		int maxDays = CampingCalc.getMaxDays(month + 1, year + 2017);

		for(int i = 0; i < maxDays; i++) {
			buttonList.add(new JButton((i + 1) + ""));
			buttonList.get(i).setFont(dateFont);
			buttonList.get(i).setMargin(new Insets(0, 0, 0, 0));
			buttonList.get(i).addActionListener(this);
			datesPanel.add(buttonList.get(i));
		}

		disableButtons();

		if ((35 / (maxDays + startingDateArray[year][month]) == 0) && ((maxDays + startingDateArray[year][month]) % 35) != 0) {
			datesPanel.setLayout(new GridLayout(7, 7));
		} else
			datesPanel.setLayout(new GridLayout(6, 7));

		datesPanel.repaint();
		datesPanel.revalidate();
	}

	/*******************************************************************
	 * Disables the necessary buttons. First, sets all buttons to 
	 * enabled. Next, gets the current month index (need to add 10 for 
	 * 2017, as there are only 2 months). Next, gets max days, and
	 * checks every day and every site to see if that date happens to be
	 * a reservation date. If it is a reservation date, disable every
	 * button starting there for an amount equal to the duration of the
	 * reservation. Last part checks if the date is a viable selection
	 * date.
	 ******************************************************************/
	private void disableButtons() {
		//re-enable all buttons before re-disabling
		for (int i = 0; i < buttonList.size(); i++) {
			buttonList.get(i).setEnabled(false);
			buttonList.get(i).setBackground(Color.BLACK);
		}

		int currentMonthIndex;
		if (shortYearStatus)
			currentMonthIndex = shortYearMonthCombo.getSelectedIndex() + 10;
		else
			currentMonthIndex = monthCombo.getSelectedIndex();

		int maxDays = CampingCalc.getMaxDays(currentMonthIndex + 1, dateArray[2]);

		String takenSiteNumber;
		String temp;
		int currentYear = Integer.parseInt(((String)yearCombo.getSelectedItem()));
		int currentMonth = currentMonthIndex + 1;
		int currentDay = 0;
		int takenYear;
		int takenMonth;
		int takenDay;
		String[] tempTaken;

		for (int i = 0; i < buttonList.size(); i++)
			for (int j = 0; j < takenDays.size(); j++) {
				temp = takenDays.get(j);
				takenSiteNumber = temp.substring(temp.indexOf(':') + 2);

				if (currentSite == Integer.parseInt(takenSiteNumber)) {
					temp = temp.substring(0, temp.indexOf(":"));
					tempTaken = temp.split("/");

					takenYear = Integer.parseInt(tempTaken[2]);
					takenMonth = Integer.parseInt(tempTaken[0]);
					takenDay = Integer.parseInt(tempTaken[1]);

					currentDay = i + 1;

					if (currentYear == takenYear)
						if (currentMonth == takenMonth)
							if (currentDay == takenDay) {
								buttonList.get(i).setEnabled(false);
								buttonList.get(i).setBackground(Color.RED);
							}
				}
			}

		//if starting year isn't the same as ending year.
		if (currentYear == dateArray[2] && dateArray[2] != endDate[2]) {
			//if current month is the same as starting month.
			if (currentMonthIndex == dateArray[0] - 1) {
				//enable every day from the starting day to end of month.
				for (int i = dateArray[1] - 1; i < maxDays; i++) {
					buttonList.get(i).setEnabled(true);
					buttonList.get(i).setBackground(Color.GREEN);
				}
				//if current month is greater than starting month.
			} else if (currentMonthIndex > dateArray[0] - 1){
				//enable every day for the whole month.
				for (int i = 0; i < maxDays; i ++) {
					buttonList.get(i).setEnabled(true);
					buttonList.get(i).setBackground(Color.GREEN);
				}
			}
			//if current year is the same as starting year,
			//and starting year == ending year.
		} else if (currentYear == dateArray[2] && dateArray[2] == endDate[2]) {
			//if the starting month isn't the same as the ending month.
			if (dateArray[0] != endDate[0]) {
				//if current month is the starting month.
				if (currentMonthIndex == dateArray[0] - 1) {
					//enable every day between the starting day and the last day.
					for (int i = dateArray[1] - 1; i < maxDays; i++) {
						buttonList.get(i).setEnabled(true);
						buttonList.get(i).setBackground(Color.GREEN);
					}
					//if current month is the ending month.
				} else if (currentMonthIndex == endDate[0] - 1) {
					//enable every day between 1 and the last date.
					for (int i = 0; i <= endDate[1] - 1; i++) {
						buttonList.get(i).setEnabled(true);
						buttonList.get(i).setBackground(Color.GREEN);
					}
					//if current month is less than the ending month.
				} else if (currentMonthIndex < endDate[0] - 1) {
					//enable every day for the whole month.
					for (int i = 0; i < maxDays; i++) {
						buttonList.get(i).setEnabled(true);
						buttonList.get(i).setBackground(Color.GREEN);
					}
				}
				//if starting month is the same as the ending month.
			} else if (dateArray[0] == endDate[0]) {
				//if the current month is the starting (and ending) month.
				if (currentMonthIndex == dateArray[0] - 1) {
					//enable every day between starting date and ending date.
					for (int i = dateArray[1] - 1; i <= endDate[1] - 1; i++) {
						buttonList.get(i).setEnabled(true);
						buttonList.get(i).setBackground(Color.GREEN);
					}
				}
			}
			//if the current year is the end year,
			//and the starting year != ending year.
		} else if (currentYear == endDate[2]) {
			//if the current month is the last month.
			if (currentMonthIndex == endDate[0] - 1) {
				//enable every day between 0 and the last day.
				for (int i = 0; i <= endDate[1] - 1; i++) {
					buttonList.get(i).setEnabled(true);
					buttonList.get(i).setBackground(Color.GREEN);
				}
				//if the current month is less than the last month.
			} else if (currentMonthIndex < endDate[0] - 1) {
				//enable every day for the whole month.
				for (int i = 0; i < maxDays; i++) {
					buttonList.get(i).setEnabled(true);
					buttonList.get(i).setBackground(Color.GREEN);
				}
			}
		}
	}

	/*******************************************************************
	 * Sets the current label to whatever button the user most recently
	 * selected.
	 * 
	 * @param source The number of the button that was pressed.
	 ******************************************************************/
	private void updateCurrentLabel(String source) {
		if (source.indexOf(',') != -1)
			source = source.substring(0, 1);
		String temp = null;
		if (shortYearStatus) {
			temp = (shortYearMonthCombo.getSelectedIndex() + 11) + "/" + source + "/" + ((String)yearCombo.getSelectedItem());
		} else if (!shortYearStatus) {
			temp = (monthCombo.getSelectedIndex() + 1) + "/" + source + "/" + ((String)yearCombo.getSelectedItem());
		}
		currentlySelectedLabel.setText("Current: " + temp);
	}

	/*******************************************************************
	 * Action listener abstract method. Sees what action event happened.
	 * 
	 * @param arg0 The source of the action event.
	 ******************************************************************/
	@Override
	public void actionPerformed(ActionEvent arg0) {
		Object source = arg0.getSource();
		if (source == yearCombo) {
			if (source.toString().contains("2017") && shortYearStatus)
				return;

			else if (source.toString().contains("2017") && !shortYearStatus) {
				comboBoxPanel.remove(monthCombo);
				comboBoxPanel.add(shortYearMonthCombo);
				comboBoxPanel.revalidate();
				comboBoxPanel.repaint();
				shortYearStatus = true;
				changeButtonsShortYear();

			} else if (!source.toString().contains("2017") && shortYearStatus) {
				comboBoxPanel.remove(shortYearMonthCombo);
				comboBoxPanel.add(monthCombo);
				comboBoxPanel.revalidate();
				shortYearStatus = false;
				changeButtonsNormalYear();

			} else
				changeButtonsNormalYear();

		} else if (source == monthCombo) {
			changeButtonsNormalYear();
		} else if (source == shortYearMonthCombo) {

			changeButtonsShortYear();
		} else if (source == okButton) {

			if (!currentlySelectedLabel.getText().equals("Currently: mm/dd/yyyy")) {
				output.setEndDateText(currentlySelectedLabel.getText().substring(currentlySelectedLabel.getText().indexOf(' ') + 1));
			}
			dispose();

		} else if (source == cancelButton) {
			dispose();

		} else if (source.toString().contains("javax.swing.JButton")) {
			updateCurrentLabel(source.toString().substring(source.toString().indexOf("text=") + 5, source.toString().indexOf("text=") + 7));

		}
	}
}