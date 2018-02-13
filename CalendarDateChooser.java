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
 * Creates a JDialog for choosing the start date of a site. Disables 
 * previously registered dates and colors them red. Only goes through
 * 2019.
 * 
 * @author kennemat
 * @version Fall 2017
 **********************************************************************/
public class CalendarDateChooser extends JDialog implements ActionListener {
	/** Panel for storing the date buttons. */
	private JPanel datesPanel;

	/** Panel for storing the combo boxes to pick a month. */
	private JPanel comboBoxPanel;

	/** Panel for storing the OK and Cancel buttons. */
	private JPanel buttonPanel;

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

	/** Constant String array for labeling the available years. */
	private String[] yearLabels;

	/** Constant value for the maximum number of years. */
	private final int maxYears = 20;

	/** Constant value for the maximum number of months. */
	private final int maxMonths = 12;

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

	/** 2D int array for storing the starting day of the week
	 * for each month. */
	private int[][] startingDateArray;

	/** ArrayList of the taken dates along with the site number
	 * at the end of the string. */
	private ArrayList<String> takenDays;

	/** An int that is the currently selected site from DialogCheckIn. */
	private int currentSite;

	/** JLabel for signifying what color taken dates are. */
	private JLabel takenLabel;

	/** JLabel that shows the taken dates color. */
	private JLabel takenLabelColor;

	/*******************************************************************
	 * Only constructor for the class. Takes in information about already
	 * reserved sites, and creates the various JComponents here.
	 * 
	 * @param output The "parent" JDialog. Used to get info on start date.
	 * @param takenDays ArrayList<String> of days that are taken.
	 * @param currentSite Current site selected in output.
	 ******************************************************************/
	public CalendarDateChooser(
			DialogCheckIn output, 
			ArrayList<String> takenDays, 
			int currentSite) {

		this.output = output;
		this.currentSite = currentSite;
		this.takenDays = takenDays;

		makePanels();
		makeJComponents();
		fillStartingDateArray();
		addListeners();
		addComponents();
		finalizeThis();
	}

	/*******************************************************************
	 * Creates the 3 main JPanels.
	 ******************************************************************/
	private void makePanels() {
		datesPanel 	  = new JPanel();
		comboBoxPanel = new JPanel();
		buttonPanel   = new JPanel();
	}

	/*******************************************************************
	 * Creates the JComponents for the main panels.
	 ******************************************************************/
	private void makeJComponents() {
		yearLabels = new String[maxYears];
		for (int i = 0; i < maxYears; i++) {
			yearLabels[i] = Integer.toString(2017 + i);
		}

		monthCombo 			= new JComboBox(monthLabels);
		yearCombo 			= new JComboBox(yearLabels);
		shortYearMonthCombo = new JComboBox(shortYearMonthLabels);

		okButton 		= new JButton("OK");
		cancelButton 	= new JButton("Cancel");
		buttonList 		= new ArrayList<JButton>();

		currentlySelectedLabel = new JLabel("Currently: mm/dd/yyyy");
		dateLabels = new JLabel[7];
		for (int i = 0; i < 7; i++) {
			dateLabels[i] = new JLabel(dayLabels[i]);
			dateLabels[i].setFont(dateFont);
		}

		takenLabel 		= new JLabel("Reserved Dates: ");
		takenLabelColor = new JLabel("");
		takenLabelColor.setOpaque(true);
		takenLabelColor.setPreferredSize(new Dimension(15, 15));
		takenLabelColor.setBackground(Color.RED);
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
		
		//adds January 2017 as the first element.
		startingDateArray[0][0] = 0;
		temp.add(startingDateArray[0][0]);

		//Starts by adding February, March...
		//Takes start date from last month, added to the max days in the
		//last month, and gets the remainder from 7.
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
		comboBoxPanel.add(takenLabel);
		comboBoxPanel.add(takenLabelColor);
		buttonPanel.add(okButton);
		buttonPanel.add(currentlySelectedLabel);
		buttonPanel.add(cancelButton);

		datesPanel.setLayout(new GridLayout(6, 7));
	}

	/*******************************************************************
	 * Adds the 3 main panels to this, sets the layout, puts this in the
	 * middle of the screen, sets the modal so the user can only click
	 * on this, and makes it visible.
	 ******************************************************************/
	private void finalizeThis() {
		setLayout(new BorderLayout());
		add(datesPanel, BorderLayout.CENTER);
		add(comboBoxPanel, BorderLayout.NORTH);
		add(buttonPanel, BorderLayout.SOUTH);

		setLocationRelativeTo(null);
		setSize(325, 300);
		setResizable(false);
		changeButtonsShortYear();
		setModal(true);
		setVisible(true);
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

		//Craetes the days of the week for the panel.
		for (int i = 0; i < 7; i++) {
			dateLabels[i].setFont(dateFont);
			datesPanel.add(dateLabels[i]);			
		}

		//Adds filler labels for the starting day.
		for(int i = 0; i < startingDateArray[year][month]; i++) {
			datesPanel.add(Box.createRigidArea(new Dimension(5,0)));
		}
		
		//Adds the actual buttons to the date panel.
		int maxDays = CampingCalc.getMaxDays(month + 1, year + 2017);
		for(int i = 0; i < maxDays; i++) {
			buttonList.add(new JButton((i + 1) + ""));
			buttonList.get(i).setFont(dateFont);
			buttonList.get(i).setMargin(new Insets(0, 0, 0, 0));
			buttonList.get(i).addActionListener(this);
			datesPanel.add(buttonList.get(i));
		}

		disableButtons();

		//Checks if the startin date + max days > 35. If it is, set grid layout to 7 x 7. Else, 6 x 7.
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
	 * reservation.
	 ******************************************************************/
	private void disableButtons() {
		//re-enable all buttons before re-disabling
		for (int i = 0; i < buttonList.size(); i++)
			buttonList.get(i).setEnabled(true);

		int currentMonthIndex;
		if (shortYearStatus)
			currentMonthIndex = shortYearMonthCombo.getSelectedIndex() + 10;
		else
			currentMonthIndex = monthCombo.getSelectedIndex();

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
			temp = (shortYearMonthCombo.getSelectedIndex() + 11) + "/" 
					+ source + "/" 
					+ ((String)yearCombo.getSelectedItem());
		} else if (!shortYearStatus) {
			temp = (monthCombo.getSelectedIndex() + 1) + "/" 
					+ source + "/" 
					+ ((String)yearCombo.getSelectedItem());
		}
		
		currentlySelectedLabel.setText("Current: " + temp);
	}

	/*******************************************************************
	 * Action listener abstract method. Sees what action event happened.
	 * 
	 * @param source The source of the action event.
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
				comboBoxPanel.remove(takenLabel);
				comboBoxPanel.remove(takenLabelColor);
				comboBoxPanel.add(takenLabel);
				comboBoxPanel.add(takenLabelColor);
				comboBoxPanel.revalidate();
				comboBoxPanel.repaint();
				shortYearStatus = true;
				changeButtonsShortYear();
				
			} else if (!source.toString().contains("2017") && shortYearStatus) {
				comboBoxPanel.remove(shortYearMonthCombo);
				comboBoxPanel.add(monthCombo);
				comboBoxPanel.remove(takenLabel);
				comboBoxPanel.remove(takenLabelColor);
				comboBoxPanel.add(takenLabel);
				comboBoxPanel.add(takenLabelColor);
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
			if (!currentlySelectedLabel.getText().equals("Currently: mm/dd/yyyy"))
				output.setStartDateText(currentlySelectedLabel.getText().substring(currentlySelectedLabel.getText().indexOf(' ') + 1));
			dispose();
			
		} else if (source == cancelButton) {
			dispose();
			
		} else if (source.toString().contains("javax.swing.JButton")) {
			updateCurrentLabel(source.toString().substring(source.toString().indexOf("text=") + 5, source.toString().indexOf("text=") + 7));
			
		}
	}
}
