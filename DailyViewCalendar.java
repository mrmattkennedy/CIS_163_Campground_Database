package campingPack;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class DailyViewCalendar  extends JDialog implements ActionListener{

	private JPanel sitePanel;
	private JPanel comboPanel;

	private final int maxSites = 5;
	private JLabel[] siteArray;
	private ArrayList<Integer> takenSites;

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

	/** String array for labeling the available years. */
	private String[] yearLabels;

	/** Constant value for the maximum number of years. */
	private final int maxYears = 20;

	/** Constant value for the maximum number of months. */
	private final int maxMonths = 12;

	/** JComboBox for the days. */
	private JComboBox dayCombo;

	/** JComboBox for the months. */
	private JComboBox monthCombo;

	/** JComboBox for the months in 2017. */
	private JComboBox shortYearMonthCombo;

	/** JComboBox for the years. */
	private JComboBox yearCombo;

	private int maxDays;

	private ArrayList<String> maxDaysList;
	
	private boolean shortYearStatus = true;
	
	private ArrayList<String> takenDays;

	public DailyViewCalendar(ArrayList<String> takenDays) {
		this.takenDays = takenDays;
		takenSites = new ArrayList<Integer>();
		makePanels();
		makeSiteLabels();
		makeCombos();
		addListeners();
		addComponents();
		finalizeThis();
	}

	private void makePanels() {
		sitePanel  = new JPanel();
		comboPanel = new JPanel();

		sitePanel.setLayout(new GridLayout(3, 3, 30, 30));

		sitePanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50)); 
		comboPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); 
	}

	private void makeSiteLabels() {
		siteArray = new JLabel[5];

		for (int i = 0; i < maxSites; i++) {
			siteArray[i] = new JLabel((i + 1) + "", SwingConstants.CENTER);
			siteArray[i].setToolTipText("Site " + (i + 1));
			siteArray[i].setOpaque(true);
			siteArray[i].setBorder(BorderFactory.createLineBorder(Color.black));
		}
	}

	private void makeCombos() {
		yearLabels = new String[maxYears];
		for (int i = 0; i < maxYears; i++)
			yearLabels[i] = Integer.toString(2017 + i);

		maxDays = CampingCalc.getMaxDays(11, 2017);
		maxDaysList = new ArrayList<String>();

		for (int i = 0; i < maxDays; i++) {
			maxDaysList.add((i + 1) + "");
		}

		dayCombo 			= new JComboBox();
		monthCombo 			= new JComboBox(monthLabels);
		shortYearMonthCombo = new JComboBox(shortYearMonthLabels);
		yearCombo 			= new JComboBox(yearLabels);

		dayCombo.setModel(new DefaultComboBoxModel(maxDaysList.toArray()));
	}

	private void addListeners() {
		dayCombo.addActionListener(this);
		shortYearMonthCombo.addActionListener(this);
		monthCombo.addActionListener(this);
		yearCombo.addActionListener(this);
	}

	private void addComponents() {
		sitePanel.add(siteArray[0]);
		sitePanel.add(new JLabel());
		sitePanel.add(siteArray[1]);
		sitePanel.add(siteArray[2]);
		sitePanel.add(new JLabel());
		sitePanel.add(siteArray[3]);
		sitePanel.add(new JLabel());
		sitePanel.add(siteArray[4]);
		sitePanel.add(new JLabel());

		comboPanel.add(shortYearMonthCombo);
		comboPanel.add(dayCombo);
		comboPanel.add(yearCombo);
	}

	private void finalizeThis() {
		colorLabels();
		setLayout(new BorderLayout());
		add(sitePanel, BorderLayout.CENTER);
		add(comboPanel, BorderLayout.SOUTH);

		setSize(800, 800);
		setVisible(true);
	}
	
	private void updateDaysCombo() {
		int month = 0;
		if (shortYearStatus)
			month = shortYearMonthCombo.getSelectedIndex() + 11;
		else
			month = monthCombo.getSelectedIndex() + 1;
		int year = yearCombo.getSelectedIndex() + 2017;
			
		maxDaysList.clear();
		maxDays = CampingCalc.getMaxDays(month, year);
		for (int i = 0; i < maxDays; i++) {
			maxDaysList.add((i + 1) + "");
		}
		dayCombo.setModel(new DefaultComboBoxModel(maxDaysList.toArray()));
		
	}
	
	private void colorLabels() {
		takenSites.clear();
		int day = dayCombo.getSelectedIndex() + 1;
		int month = 0;
		if (shortYearStatus)
			month = shortYearMonthCombo.getSelectedIndex() + 11;
		else
			month = monthCombo.getSelectedIndex() + 1;
		int year = yearCombo.getSelectedIndex() + 2017;
		
		String currentDate = month + "/" + day + "/" + year;
		String currentSite;
		for (int i = 0; i < takenDays.size(); i++) {
			if (takenDays.get(i).contains(currentDate)) {
				currentSite = takenDays.get(i).substring(takenDays.get(i).indexOf(":") + 2);
				takenSites.add(Integer.parseInt(currentSite));
			}
		}
		
		for (int i = 0; i < siteArray.length; i++) {
			siteArray[i].setBackground(Color.green);
			siteArray[i].setToolTipText("");
		}

		for (int i = 0; i < takenSites.size(); i++) {
			siteArray[takenSites.get(i) - 1].setBackground(Color.RED);
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Object source = arg0.getSource();
		if (source == yearCombo) {
			if (source.toString().contains("2017") && shortYearStatus)
				return;

			else if (source.toString().contains("2017") && !shortYearStatus) {
				comboPanel.remove(monthCombo);
				comboPanel.remove(dayCombo);
				comboPanel.remove(yearCombo);
				comboPanel.add(shortYearMonthCombo);
				comboPanel.add(dayCombo);
				comboPanel.add(yearCombo);
				comboPanel.revalidate();
				comboPanel.repaint();
				shortYearStatus = true;
				updateDaysCombo();
				colorLabels();

			} else if (!source.toString().contains("2017") && shortYearStatus) {
				comboPanel.remove(shortYearMonthCombo);
				comboPanel.remove(dayCombo);
				comboPanel.remove(yearCombo);
				comboPanel.add(monthCombo);
				comboPanel.add(dayCombo);
				comboPanel.add(yearCombo);
				comboPanel.revalidate();
				shortYearStatus = false;
				updateDaysCombo();
				colorLabels();
			} else
				updateDaysCombo();
				colorLabels();

		} else if (source == monthCombo) {
			updateDaysCombo();
			colorLabels();
		} else if (source == shortYearMonthCombo) {
			updateDaysCombo();
			colorLabels();
		} else if (source == dayCombo) {
			colorLabels();
		}
	}
}
