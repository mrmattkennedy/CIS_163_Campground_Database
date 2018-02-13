package campingPack;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;

/***********************************************************************
 * Creates a Calendar for choosing the ending date for a reserveration. Extends
 * JFrame, very similar to CalendarDate chooser, with differences in disabling
 * buttons and what is being displayed.
 * 
 * @author kennemat
 * @version Fall 2017
 **********************************************************************/
public class FullYearCalendar extends JDialog implements ActionListener {

	/** Holds all 12 monthPanels and monthLabelPanels. */
	private JPanel datePanel;

	/**
	 * Part of the bottomPanel. Holds the prevYearBtn, nextYearBtn, and crntYear
	 * label.
	 */
	private JPanel buttonPanel;

	/** Panel that holds the color code key. */
	private JPanel colorPanel;

	/* Holds the buttonPanel and colorPanel. */
	private JPanel bottomPanel;

	/** Changes the year to the previous year. */
	private JButton prevYearBtn;

	/** Changes the year to the next year. */
	private JButton nextYearBtn;

	/** Label that says what the current year is. */
	private JLabel crntYear;

	/** Constant value for the maximum number of years. */
	private final int maxYears = 20;

	/** Constant value for the maximum number of months. */
	private final int maxMonths = 12;

	/** Int value for the current year. */
	private int year;

	/**
	 * 2D int array for storing the starting day of the week for each month.
	 */
	private int[][] startingDateArray;

	/**
	 * ArrayList of JPanels. Holds a panel with the dates, and a JLabel with the
	 * current month.
	 */
	private ArrayList<JPanel> monthPanels;

	/** ArrayList of JPanels. Holds the dates. */
	private ArrayList<JPanel> monthLabelPanels;

	/**
	 * 2D array list that holds all the date labels and filler labels. Have to have
	 * a date for every month, because JComponents can't be in 2 spots.
	 */
	private ArrayList<ArrayList<JLabel>> labelList;

	/** ArrayList of the taken days. */
	private ArrayList<String> takenDays;

	/** ArrayList of the full days, where all 5 sites are reserved. */
	private ArrayList<String> fullDates;

	/** Constant String array for labeling the months. */
	private final String[] monthLabels = { "January", "February", "March", "April", "May", "June", "July", "August",
			"September", "October", "November", "December" };

	/*******************************************************************
	 * Only constructor for the class. Creates the panels, JComponents, and
	 * initializes the calendar for 2017, which is a short year.
	 * 
	 * @param takenDays
	 *            ArrayList of the reservation dates.
	 ******************************************************************/
	public FullYearCalendar(ArrayList<String> takenDays) {
		this.takenDays = takenDays;
		year = 0;

		makePanels();
		makeInstanceVariables();
		addListeners();
		addPanelComponents();

		fillStartingDateArray();
		resetPanel();
		fillButtonListShortYear();
		fillColorPanel();

		finalizeThis();
	}

	/*******************************************************************
	 * Creates the 4 main JPanels.
	 ******************************************************************/
	private void makePanels() {
		buttonPanel = new JPanel();
		bottomPanel = new JPanel();
		colorPanel = new JPanel();
		datePanel = new JPanel();
	}

	/*******************************************************************
	 * Creates the JComponenets and ArrayLists for the object.
	 ******************************************************************/
	private void makeInstanceVariables() {
		prevYearBtn = new JButton("Prev");
		nextYearBtn = new JButton("Next");
		crntYear = new JLabel("2017");

		monthPanels = new ArrayList<JPanel>();
		monthLabelPanels = new ArrayList<JPanel>();
		labelList = new ArrayList<ArrayList<JLabel>>();
		fullDates = new ArrayList<String>();
	}

	/*******************************************************************
	 * Adds listeners to the objects.
	 ******************************************************************/
	private void addListeners() {
		prevYearBtn.addActionListener(this);
		nextYearBtn.addActionListener(this);
	}

	/*******************************************************************
	 * Adds components to the appropriate panels.
	 ******************************************************************/
	private void addPanelComponents() {
		buttonPanel.add(prevYearBtn);
		prevYearBtn.setEnabled(false);
		buttonPanel.add(crntYear);
		buttonPanel.add(nextYearBtn);

		bottomPanel.add(colorPanel);
		bottomPanel.add(buttonPanel);
		bottomPanel.setLayout(new GridLayout(2, 1, 2, 2));
	}

	/*******************************************************************
	 * Sets this object and makes it visible.
	 ******************************************************************/
	private void finalizeThis() {
		setLayout(new BorderLayout());
		add(bottomPanel, BorderLayout.SOUTH);
		add(datePanel, BorderLayout.CENTER);
		setSize(800, 800);
		setVisible(true);
	}

	/*******************************************************************
	 * Gets the starting day of the week for each month, and sets the values in
	 * startingDateArray.
	 ******************************************************************/
	private void fillStartingDateArray() {
		startingDateArray = new int[maxYears][maxMonths];
		int newStartDate = 0;
		int oldStartDate = 0;
		int maxDays = 0;
		ArrayList<Integer> temp = new ArrayList<Integer>();

		startingDateArray[0][0] = 0;
		temp.add(startingDateArray[0][0]);

		for (int i = 0; i < maxYears; i++)
			for (int j = 0; j < maxMonths; j++) {
				oldStartDate = newStartDate;
				maxDays = CampingCalc.getMaxDays(j + 1, 2017 + i);
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
	 * Creates a color key code for the dates. These aren't instance variables
	 * because they are never used outside of this method.
	 ******************************************************************/
	private void fillColorPanel() {
		JLabel emptyLabel = new JLabel("Empty reservation date: ");
		JLabel emptyLabelColor = new JLabel("");
		JLabel partialLabel = new JLabel("Partial reservation date: ");
		JLabel partialLabelColor = new JLabel("");
		JLabel fullLabel = new JLabel("Full reservation date: ");
		JLabel fullLabelColor = new JLabel("");

		emptyLabelColor.setOpaque(true);
		emptyLabelColor.setPreferredSize(new Dimension(15, 15));
		emptyLabelColor.setBackground(Color.GREEN);

		partialLabelColor.setOpaque(true);
		partialLabelColor.setPreferredSize(new Dimension(15, 15));
		partialLabelColor.setBackground(Color.YELLOW);

		fullLabelColor.setOpaque(true);
		fullLabelColor.setPreferredSize(new Dimension(15, 15));
		fullLabelColor.setBackground(Color.RED);

		colorPanel.add(emptyLabel);
		colorPanel.add(emptyLabelColor);
		colorPanel.add(Box.createHorizontalStrut(50));
		colorPanel.add(partialLabel);
		colorPanel.add(partialLabelColor);
		colorPanel.add(Box.createHorizontalStrut(50));
		colorPanel.add(fullLabel);
		colorPanel.add(fullLabelColor);
	}

	/*******************************************************************
	 * Clears the datePanels, monthPanels, and monthLabelPanels ArrayLists. If it's
	 * not 2017, create 12 new JPanels, sets the border on them, and set the layout.
	 * Then, adds a JLabel at the top to identify the month. If it is 2017, create 2
	 * new JPanels, sets the border on them, and set the layout. Then, adds a JLabel
	 * at the top to identify the month.
	 ******************************************************************/
	private void resetPanel() {
		monthLabelPanels.clear();
		monthPanels.clear();
		datePanel.removeAll();

		if (year != 0)
			for (int i = 0; i < maxMonths; i++) {
				monthPanels.add(new JPanel());
				monthLabelPanels.add(new JPanel());
				monthPanels.get(i)
						.setBorder(BorderFactory.createCompoundBorder(new BevelBorder(1), new EtchedBorder()));
				monthPanels.get(i).setLayout(new BorderLayout());

				monthPanels.get(i).add(new JLabel(monthLabels[i]), BorderLayout.NORTH);
				monthPanels.get(i).add(monthLabelPanels.get(i), BorderLayout.CENTER);
			}
		else
			for (int i = 10; i < maxMonths; i++) {
				monthPanels.add(new JPanel());
				monthLabelPanels.add(new JPanel());
				monthPanels.get(i - 10)
						.setBorder(BorderFactory.createCompoundBorder(new BevelBorder(1), new EtchedBorder()));
				monthPanels.get(i - 10).setLayout(new BorderLayout());

				monthPanels.get(i - 10).add(new JLabel(monthLabels[i]), BorderLayout.NORTH);
				monthPanels.get(i - 10).add(monthLabelPanels.get(i - 10), BorderLayout.CENTER);
			}
	}

	/*******************************************************************
	 * Clears the labelList, then creates 12 new ArrayList<JLabel>. Next, add a
	 * filler amount to each month of the 12 ArrayLists in labelList equal to
	 * startingDateArray[year][month]. Next, creates the actual date JLabels, and
	 * sets the background color to either green or yellow. Next, fills up all 12
	 * ArrayLists in labelList to have each one size 42 to fit the gridLayout. Next,
	 * gets the dates that are full, and sets those dates to red. Next, sets the
	 * border on each JLabel, and adds them to the correct month. Finally, sets the
	 * layout of each month ArrayList to gridLayout(6, 7), and adds it to datePanel.
	 ******************************************************************/
	private void fillButtonList() {
		int maxDays;
		labelList.clear();

		// creates the new ArrayLists for the JLabels.
		for (int i = 0; i < maxMonths; i++)
			labelList.add(new ArrayList<JLabel>());

		// Creates the empty spaces at the beginning of each month.
		for (int month = 0; month < maxMonths; month++)
			for (int i = 0; i < startingDateArray[year][month]; i++)
				labelList.get(month).add(new JLabel());

		// Creates the actual dates for each month.
		for (int month = 0; month < maxMonths; month++) {
			maxDays = CampingCalc.getMaxDays(month + 1, year + 2017);
			for (int i = 0; i < maxDays; i++) {
				labelList.get(month).add(new JLabel((i + 1) + ""));
				labelList.get(month).get(i + startingDateArray[year][month]).setOpaque(true);
				labelList.get(month).get(i + startingDateArray[year][month])
						.setBackground(getLabelColor(month + 1, i + 1));
			}
		}

		// Fills each date up to 42 spaces, to fill out the grid layout.
		for (int month = 0; month < maxMonths; month++) {
			maxDays = CampingCalc.getMaxDays(month + 1, year + 2017);
			while (labelList.get(month ).size() < 42)
				labelList.get(month).add(new JLabel());
		}

		checkFullDates();
		String temp[];
		// Sets full dates to red.
		for (int month = 0; month < maxMonths; month++) {
			maxDays = CampingCalc.getMaxDays(month + 1, year + 2017);
			for (int i = 0; i < maxDays; i++) {
				for (int j = 0; j < fullDates.size(); j++) {
					temp = fullDates.get(j).split("/");
					if (Integer.parseInt(temp[0]) == month + 1)
						if (Integer.parseInt(temp[1]) == i + 1)
							if (Integer.parseInt(temp[2]) == year + 2017) {
								labelList.get(month).get(i + startingDateArray[year][month]).setOpaque(true);
								labelList.get(month).get(i + startingDateArray[year][month]).setBackground(Color.RED);
							}
				}
			}
		}

		// Sets the border of each JLabel
		// Adds them to the appropriate monthLabelPanel.
		for (int i = 0; i < maxMonths; i++)
			for (int j = 0; j < labelList.get(i).size(); j++) {
				labelList.get(i).get(j).setBorder(BorderFactory.createLineBorder(Color.black));
				monthLabelPanels.get(i).add(labelList.get(i).get(j));
			}

		// Sets the grid layout of each monthLabelPanel
		// Adds them to the datePanel.
		for (int i = 0; i < maxMonths; i++) {
			monthLabelPanels.get(i).setLayout(new GridLayout(6, 7));
			datePanel.add(monthPanels.get(i));
		}

		datePanel.setLayout(new GridLayout(3, 4, 5, 5));
	}

	/*******************************************************************
	 * Clears the labelList, then creates 2 new ArrayList<JLabel>. Next, add a
	 * filler amount to each month of the 2 ArrayLists in labelList equal to
	 * startingDateArray[year][month]. Next, creates the actual date JLabels, and
	 * sets the background color to either green or yellow. Next, fills up all 2
	 * ArrayLists in labelList to have each one size 42 to fit the gridLayout. Next,
	 * gets the dates that are full, and sets those dates to red. Next, sets the
	 * border on each JLabel, and adds them to the correct month. Finally, sets the
	 * layout of each month ArrayList to gridLayout(6, 7), and adds it to datePanel.
	 ******************************************************************/
	private void fillButtonListShortYear() {
		int maxDays;
		labelList.clear();

		// creates the new ArrayLists for the JLabels.
		for (int i = 10; i < maxMonths; i++)
			labelList.add(new ArrayList<JLabel>());

		// Creates the empty spaces at the beginning of each month.
		for (int month = 10; month < maxMonths; month++)
			for (int i = 0; i < startingDateArray[year][month]; i++)
				labelList.get(month - 10).add(new JLabel());

		// Creates the actual dates for each month.
		for (int month = 10; month < maxMonths; month++) {
			maxDays = CampingCalc.getMaxDays(month + 1, year + 2017);
			for (int i = 0; i < maxDays; i++) {
				labelList.get(month - 10).add(new JLabel((i + 1) + ""));
				labelList.get(month - 10).get(i + startingDateArray[year][month]).setOpaque(true);
				labelList.get(month - 10).get(i + startingDateArray[year][month])
						.setBackground(getLabelColor(month + 1, i + 1));
			}
		}

		checkFullDates();
		// Fills each date up to 42 spaces, to fill out the grid layout.
		for (int month = 10; month < maxMonths; month++) {
			maxDays = CampingCalc.getMaxDays(month + 1, year + 2017);
			while (labelList.get(month - 10).size() < 42)
				labelList.get(month - 10).add(new JLabel());
		}

		String temp[];
		// Sets full dates to red.
		for (int month = 10; month < maxMonths; month++) {
			maxDays = CampingCalc.getMaxDays(month + 1, year + 2017);
			for (int i = 0; i < maxDays; i++)
				for (int j = 0; j < fullDates.size(); j++) {
					temp = fullDates.get(j).split("/");
					if (Integer.parseInt(temp[0]) == month + 1)
						if (Integer.parseInt(temp[1]) == i + 1)
							if (Integer.parseInt(temp[2]) == year + 2017) {
								labelList.get(month - 10).get(i + startingDateArray[year][month]).setOpaque(true);
								labelList.get(month - 10).get(i + startingDateArray[year][month])
										.setBackground(Color.RED);
							}
				}
		}

		// Sets the border of each JLabel
		// Adds them to the appropriate monthLabelPanel.
		for (int i = 10; i < maxMonths; i++)
			for (int j = 0; j < labelList.get(i - 10).size(); j++) {
				labelList.get(i - 10).get(j).setBorder(BorderFactory.createLineBorder(Color.black));
				monthLabelPanels.get(i - 10).add(labelList.get(i - 10).get(j));
			}

		// Sets the grid layout of each monthLabelPanel
		// Adds them to the datePanel.
		for (int i = 10; i < maxMonths; i++) {
			monthLabelPanels.get(i - 10).setLayout(new GridLayout(6, 7));
			datePanel.add(monthPanels.get(i - 10));
		}

		datePanel.setLayout(new GridLayout(3, 4, 5, 5));
	}

	/*******************************************************************
	 * Sets the label color to either green or yellow. Checks each date given
	 * against the takenDays ArrayList. If it's in there, returns yellow. If not,
	 * returns green.
	 * 
	 * @param month
	 *            The month of the date to check.
	 * @param day
	 *            The day of the date to check.
	 * @return Green if not in the ArrayList takenDays, yellow if it is.
	 ******************************************************************/
	private Color getLabelColor(int month, int day) {
		String[] tempTaken;
		int[] tempDate = new int[3];
		String temp;

		for (int i = 0; i < takenDays.size(); i++) {
			temp = takenDays.get(i);

			temp = temp.substring(0, temp.indexOf(":"));
			tempTaken = temp.split("/");

			tempDate[2] = Integer.parseInt(tempTaken[2]);
			tempDate[0] = Integer.parseInt(tempTaken[0]);
			tempDate[1] = Integer.parseInt(tempTaken[1]);

			if (year + 2017 == tempDate[2])
				if (month == tempDate[0])
					if (day == tempDate[1]) {
						return Color.YELLOW;
					}
		}
		return Color.green;

	}

	/*******************************************************************
	 * Fills the fullDates array with dates that are full. Takes each date in
	 * takenDays, compares it to other strings in the array: if the same date is
	 * present 5 times, adds it to fullDates.
	 ******************************************************************/
	private void checkFullDates() {
		String currentDate;
		String temp;
		int takenCount = 0;
		fullDates.clear();

		for (int i = 0; i < takenDays.size(); i++) {
			currentDate = takenDays.get(i);
			currentDate = currentDate.substring(0, currentDate.indexOf(":"));

			takenCount = 0;
			for (int j = i; j < takenDays.size(); j++) {
				temp = takenDays.get(j);
				temp = temp.substring(0, temp.indexOf(":"));

				if (currentDate.equals(temp))
					takenCount += 1;
			}

			if (takenCount == 5)
				fullDates.add(currentDate);
		}
	}

	/*******************************************************************
	 * ActionListener method for this class. If prevYearButton or nextYearBtn
	 * pressed, enable / disable prevYearBtn and nextYearBtn appropriately and fills
	 * JLabel dates appropriately.
	 ******************************************************************/
	@Override
	public void actionPerformed(ActionEvent arg0) {
		Object source = arg0.getSource();

		if (source == prevYearBtn) {
			year -= 1;

			if (year == 0) {
				prevYearBtn.setEnabled(false);
				resetPanel();
				fillButtonListShortYear();
			} else if (year == 18) {
				nextYearBtn.setEnabled(true);
				resetPanel();
				fillButtonList();
			} else {
				resetPanel();
				fillButtonList();
			}
			crntYear.setText((year + 2017) + "");
			datePanel.repaint();
		} else if (source == nextYearBtn) {
			year += 1;

			if (year == 19) {
				nextYearBtn.setEnabled(false);
			} else if (year == 1) {
				prevYearBtn.setEnabled(true);
			}
			crntYear.setText((year + 2017) + "");
			resetPanel();
			fillButtonList();
			datePanel.repaint();
		}
	}
}