package campingPack;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;

/***********************************************************************
 * Creates a JDialog for getting input from a user for a new Site. Gets
 * user name, site number, either number of tents or RV power, start date
 * and end date.
 * 
 * @author kennemat
 * @version Fall 2017
 **********************************************************************/

public class DialogCheckIn extends JDialog implements ActionListener {
	/** SerialVersionUID for the class. */
    private static final long serialVersionUID = 1L;
    
    /** LabelPanel for adding labels for input. */
    private JPanel labelPanel;
    
    /** InputPanel for adding inputs for data. */
    private JPanel inputPanel;
    
    /** Panel for adding label and button for starting date. */
    private JPanel calendarStartPanel;
    
    /** Panel for adding label and button for ending date. */
    private JPanel calendarEndPanel;
    
    /** Panel for adding OK button and cancel button. */
    private JPanel buttonPanel;
    
    /** JTextField for getting user name. */
    private JTextField nameTxt;
    
    /** JComboBox for getting the site number. */
    private JComboBox siteNumberTxt;
    
    /** Final string array for the siteNumberTxt JComboBox. */
    private final String[] siteNumbers = {"1", "2", "3", "4", "5"};
    
    /** Integer for passing to the CalendarClasses. 
     * Has the current site selected. */
    private int currentSite;
    
    /** Label for displaying the start date text. */
    private JLabel startDateTxt;
    
    /** Label for displaying the end date text. */
    private JLabel endDateTxt;
    
    /** JComboBox for selecting RV power. */
    private JComboBox powerTxt;
    
    /** Constant for the max number of tents. */
    private final int maxTents = 100;
    
    /** JComboBox for selecting number of tents. */
    private JComboBox tentCombo;
    
    /** String used for tentCombo to display the number of tents. */
    private String[] tentCounts;
    
    /** Button to confirm user is done inputting information. */
    private JButton okButton;

    /** Button to confirm user wants to cancel inputting information. */
    private JButton cancelButton;

    /** Button to create CalendarDateChooser object to pick start date. */
    private JButton chooseStartDate;
    
    /** Button to create CalendarEndDateChooser object to pick end date. */
    private JButton chooseEndDate;
    
    /** Site object to get info for. Will edit instance variables of the
     * site object directly from this class. */
    private Site unit;
    
    /** GUICampingReg object to get information and set data. */
    private GUICampingReg paFrame;
    
    /** Formatter to always display 2 decimals for the cost. */
    private DecimalFormat df;

    
    /*******************************************************************
     * Only constructor for the DialogCheckIn class. Takes a frame, 
     * specifally GUICampingReg frame, as a "parent" frame. Also uses the
     * frame object to get info about the database. Also takes a site to
     * create then pass to the SiteModel class through the parent frame.
     * Uses helper methods to create JComponents and JPanels.
     * 
     * @param paFrame Parent frame for the JDialog.
     * @param d Site to set data for, then pass to site database.
     ******************************************************************/
    public DialogCheckIn(GUICampingReg paFrame, Site d) {
    	unit = d;
    	this.paFrame = paFrame;
    	df = new DecimalFormat("#,###.00");
        makePanels();
        makeJComponents();
        addPanelComponents();
        addListeners();
        setStartAndEndPanels();
        setInputAndLabelPanels();
        setJDialogBox();
    }
    
    /*******************************************************************
     * Instantiates the 3 main panels and 2 sub panels for the JDialog.
     ******************************************************************/
    private void makePanels() {
    	labelPanel 			= new JPanel();
        inputPanel 			= new JPanel();
        buttonPanel 		= new JPanel();
        calendarStartPanel 	= new JPanel();
        calendarEndPanel 	= new JPanel();
    }
    
    /*******************************************************************
     * Instantiates the JComponents for the 3 main panels and 2 sub
     * panels.
     ******************************************************************/
    private void makeJComponents() {
    	
    	nameTxt 		= new JTextField("Firstname Lastname");
        siteNumberTxt 	= new JComboBox(siteNumbers);
        startDateTxt 	= new JLabel("mm/dd/yyyy");
        endDateTxt 		= new JLabel("mm/dd/yyyy");
        String[] comboString = {"30", "40", "50"};
        powerTxt 		= new JComboBox(comboString);
        okButton 		= new JButton("OK");
        cancelButton	= new JButton("Cancel");
        chooseStartDate = new JButton("");
        chooseEndDate 	= new JButton("");
        
        chooseEndDate.setEnabled(false);
        
        currentSite = 1;
        tentCounts 	= new String[maxTents];
        for (int i = 0; i < maxTents; i++)
            tentCounts[i] = Integer.toString(i + 1);
        
        tentCombo 	= new JComboBox(tentCounts);
    }
    
    /*******************************************************************
     * Adds the JComponents to their respective panels.
     ******************************************************************/
    private void addPanelComponents() {
    	labelPanel.add(new JLabel("Name of Reserver:  "));
        inputPanel.add(nameTxt);
        labelPanel.add(new JLabel("Requested site number:  "));
        inputPanel.add(siteNumberTxt);
        if (unit instanceof RV) {
            labelPanel.add(new JLabel("Type of power in Amps:  "));
            inputPanel.add(powerTxt);
        } else if (unit instanceof Tent) {
            labelPanel.add(new JLabel("Number of tents: "));
            inputPanel.add(tentCombo);
        }
        labelPanel.add(new JLabel("Starting Date:  "));
        labelPanel.add(new JLabel("Ending Date:    "));
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);
        inputPanel.add(calendarStartPanel);
        inputPanel.add(calendarEndPanel);
        inputPanel.setPreferredSize(new Dimension(130, 100));
    }
    
    /*******************************************************************
     * Adds this as a listener to the necessary JComponents.
     ******************************************************************/
    private void addListeners() {
    	cancelButton.addActionListener(this);
        okButton.addActionListener(this);
        chooseStartDate.addActionListener(this);
        chooseEndDate.addActionListener(this);
        siteNumberTxt.addActionListener(this);
    }
    
    /*******************************************************************
     * Sets sizes and adds necessary JComponents to the 2 sub panels, 
     * calendarStartPanel and calendarEndPanel.
     ******************************************************************/
    private void setStartAndEndPanels() {
    	startDateTxt.setPreferredSize(new Dimension(92, 37));
        chooseStartDate.setPreferredSize(new Dimension(20, 37));
        endDateTxt.setPreferredSize(new Dimension(92, 37));
        chooseEndDate.setPreferredSize(new Dimension(20, 37));
        
        calendarStartPanel.setBackground(Color.WHITE);
        calendarEndPanel.setBackground(Color.WHITE);
        calendarStartPanel.add(startDateTxt);
        calendarStartPanel.add(chooseStartDate);
        calendarEndPanel.add(endDateTxt);
        calendarEndPanel.add(chooseEndDate);
        calendarStartPanel.setSize(new Dimension(35, 15));
    }
    
    /*******************************************************************
     * Sets the layout, color, and border of the label and input panels.
     ******************************************************************/
    private void setInputAndLabelPanels() {
    	labelPanel.setLayout(new GridLayout(5, 1, 0, 10));
        labelPanel.setBackground(Color.WHITE);
        labelPanel.setBorder(BorderFactory.createCompoundBorder(new BevelBorder(1), new EtchedBorder()));
        inputPanel.setLayout(new GridLayout(5, 1, 0, 10));
        inputPanel.setBackground(Color.WHITE);
        inputPanel.setBorder(BorderFactory.createCompoundBorder(new BevelBorder(1), new EtchedBorder()));
    }
    
    /*******************************************************************
     * Finalizes the JDialog.
     ******************************************************************/
    private void setJDialogBox() {
    	setLayout(new BorderLayout());
        add(labelPanel, BorderLayout.WEST);
        add(inputPanel, BorderLayout.EAST);
        add(buttonPanel, BorderLayout.SOUTH);
        setSize(300, 350);
        setLocationRelativeTo(null);
        setModal(true);
        setVisible(true);
    }

    /*******************************************************************
     * Checks the input to verify there are no bad inputs. After input
     * is verified, gets the difference between start and end date input,
     * and sets the new Site object instance variables.
     ******************************************************************/
    public boolean setVars() {
    	if (unit instanceof RV)
    		try {
	    		unit = new RV(
	    				nameTxt.getText(),
	    				startDateTxt.getText(),
	    				endDateTxt.getText(),
	    				currentSite,
	    				Integer.parseInt((String)powerTxt.getSelectedItem()));
    		} catch(IllegalNameException e){
				JOptionPane.showMessageDialog(null, "Incorrect name input.");
				return false;
			} 
			catch(IllegalDateException e){
				JOptionPane.showMessageDialog(null, "Incorrect date input.");
				return false;
			}
			catch(IllegalDurationException e){
				JOptionPane.showMessageDialog(null, "Incorrect duration input.");
				return false;
			}
			catch(IllegalSiteException e){
				JOptionPane.showMessageDialog(null, "Incorrect site input.");
				return false;
			}
			catch(IllegalPowerException e){
				JOptionPane.showMessageDialog(null, "Incorrect power input.");
				return false;
			}
			catch(Exception e){
				JOptionPane.showMessageDialog(null, "Oops! Something went wrong.");
				return false;
			}
    	
    	else
    		try {
	    		unit = new Tent(
	    				nameTxt.getText(),
	    				startDateTxt.getText(),
	    				endDateTxt.getText(),
	    				currentSite,
	    				tentCombo.getSelectedIndex() + 1);
    		} catch(IllegalNameException e){
				JOptionPane.showMessageDialog(null, "Incorrect name input.");
				return false;
			} 
			catch(IllegalDateException e){
				JOptionPane.showMessageDialog(null, "Incorrect date input.");
				return false;
			}
			catch(IllegalDurationException e){
				JOptionPane.showMessageDialog(null, "Incorrect duration input.");
				return false;
			}
			catch(IllegalSiteException e){
				JOptionPane.showMessageDialog(null, "Incorrect site input.");
				return false;
			}
			catch(IllegalTentsException e){
				JOptionPane.showMessageDialog(null, "Incorrect tents input.");
				return false;
			}
			catch(Exception e){
				JOptionPane.showMessageDialog(null, "Oops! Something went wrong.");
				return false;
			}
   
    	unit.setCost();
    	paFrame.addSiteInfo(unit);
    	return true;
    	
    	
    }
    
    /*******************************************************************
     * Sets the start date text. Used from the CalendarDateChooser class.
     * 
     * @param date The date the user picked in the CalendarDateChooser
     * object.
     ******************************************************************/
    public void setStartDateText(String date) {
    	startDateTxt.setText(date);
    	endDateTxt.setText("mm/dd/yyyy");
    	chooseEndDate.setEnabled(true);
    }
    
    /*******************************************************************
     * Sets the end date text. Used from the CalendarDateChooser class.
     * 
     * @param date The date the user picked in the CalendarEndDateChooser
     * object.
     ******************************************************************/
    public void setEndDateText(String date) {
    	endDateTxt.setText(date);
    }
    
    /*******************************************************************
     * Gets the start date text. Used from the CalendarEndDateChooser 
     * class.
     * 
     * @return The startDateTxt.Text() value.
     ******************************************************************/
    public String getStartDateText() {
        return startDateTxt.getText();
    }

    /*******************************************************************
     * Sets the end date text. Not used in any classes currently.
     * 
     * @return The endDateTxt.Text() value.
     ******************************************************************/
    public String getEndDateText() {
        return endDateTxt.getText();
    }

    /*******************************************************************
     * actionPerformed method. Inherited by implementing ActionListener.
     * 
     * @param arg0 the action that occured.
     ******************************************************************/
    @Override
    public void actionPerformed(ActionEvent arg0) {
        Object source = arg0.getSource();

        if (source == okButton) {
            if (setVars()) {
            	JOptionPane.showMessageDialog(null, "You owe $" + df.format(unit.getCost())+ ".");
	            dispose();
            }
        } else if (source == cancelButton) {
            dispose();
            
        } else if (source == siteNumberTxt) {
            startDateTxt.setText("mm/dd/yyyy");
            endDateTxt.setText("mm/dd/yyyy");
            currentSite = siteNumberTxt.getSelectedIndex() + 1;
            
        } else if (source == chooseStartDate) {
            new CalendarDateChooser(this, paFrame.getSiteListTakenDays(), currentSite);
            
        } else if (source == chooseEndDate) {
            new CalendarEndDateChooser(this, paFrame.getSiteListTakenDays(), currentSite);
            
        }

    }

}