//make the date combo boxes labels
//make text files unique
//disable site combo while dialog is active
//make it locked into the calendar

package campingPack;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.WindowConstants;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

public class GUICampingReg extends JFrame implements ActionListener {

    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenu checkInMenu;
    private JMenu viewMenu;
    private JMenuItem openSerial;
    private JMenuItem saveSerial;
    private JMenuItem openText;
    private JMenuItem saveText;
    private JMenuItem deleteItem;
    private JMenuItem undoItem;
    private JMenuItem redoItem;
    private JMenuItem exitItem;
    private JMenuItem checkInRv;
    private JMenuItem checkInTent;
    private JMenuItem fullView;
    private JMenuItem dailyView;
    private menuListener menuListen;
    private SiteModel siteModel;
    private JTable table;

    public GUICampingReg(){

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Campground Checkin Interface");
        menuBar = new JMenuBar();

        fileMenu 	= new JMenu("File");
        checkInMenu = new JMenu("Check-In");
        viewMenu 	= new JMenu("Views");
        menuBar.add(fileMenu);
        menuBar.add(checkInMenu);
        menuBar.add(viewMenu);

        menuListen = new menuListener();
        fileMenu.addMenuListener(menuListen);

        createFileMenuItems();
        createCheckInMenuItems();
        createViewMenuItems();

        setJMenuBar(menuBar);

        siteModel = new SiteModel();
        table = new JTable(siteModel);
        add(new JScrollPane(table));
    }
    
    private void createViewMenuItems() {
    	fullView   = new JMenuItem("Full View");
        dailyView  = new JMenuItem("Daily View");

        
        fullView.addActionListener(this);
        dailyView.addActionListener(this);
        
        viewMenu.add(fullView);
        viewMenu.add(dailyView);

    }

    private void createCheckInMenuItems() {
        checkInRv   = new JMenuItem("Check in RV");
        checkInTent = new JMenuItem("Check in Tent");

        checkInRv.addActionListener(this);
        checkInTent.addActionListener(this);

        checkInMenu.add(checkInRv);
        checkInMenu.add(checkInTent);
    }

    private void createFileMenuItems() {
        openSerial = new JMenuItem("Open Serial");
        saveSerial = new JMenuItem("Save Serial");
        openText   = new JMenuItem("Open Text");
        saveText   = new JMenuItem("Save Text");
        deleteItem = new JMenuItem("Delete Reservation");
        exitItem   = new JMenuItem("Exit");
        undoItem   = new JMenuItem("Undo");
        redoItem   = new JMenuItem("Redo");

        openSerial.addActionListener(this);
        saveSerial.addActionListener(this);
        openText.addActionListener(this);
        saveText.addActionListener(this);
        deleteItem.addActionListener(this);
        undoItem.addActionListener(this);
        redoItem.addActionListener(this);
        exitItem.addActionListener(this);

        fileMenu.add(openSerial);
        fileMenu.add(saveSerial);
        fileMenu.addSeparator();
        fileMenu.add(openText);
        fileMenu.add(saveText);
        fileMenu.addSeparator();
        fileMenu.add(deleteItem);
        fileMenu.add(undoItem);
        fileMenu.add(redoItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);
    }
    
    public int getSiteListSize() {
    	return siteModel.getSiteListSize();
    }
    
    public ArrayList<String> getSiteListTakenDays() {
    	return siteModel.getTakenDays();
    }
    
    public void addSiteInfo(Site s) {
    	siteModel.addSiteInfo(s);
    }

    public void actionPerformed(ActionEvent e) {
    	
        if (e.getSource() == checkInRv) {
            RV r = new RV();
            DialogCheckIn dialog = new DialogCheckIn(this, r);
        }

        if(e.getSource() == checkInTent) {

            Tent t = new Tent();
            DialogCheckIn dialog = new DialogCheckIn(this, t);
        }

        if(e.getSource() == saveSerial){

            JFileChooser chooser = new JFileChooser();
            int status = chooser.showSaveDialog(null);
            if (status == JFileChooser.APPROVE_OPTION) {
                String filename = chooser.getSelectedFile().getAbsolutePath();
                siteModel.saveAccount(filename);
            }
        }

        if(e.getSource() == openSerial){

            JFileChooser chooser = new JFileChooser();
            int status = chooser.showOpenDialog(null);
            if (status == JFileChooser.APPROVE_OPTION) {
                String filename = chooser.getSelectedFile().getAbsolutePath();
                siteModel.loadAccount(filename);
            }
        }

        if (e.getSource() == saveText) {

            JFileChooser chooser = new JFileChooser();
            int status = chooser.showSaveDialog(null);

            if (status == JFileChooser.APPROVE_OPTION)  {
                String filename = chooser.getSelectedFile().getAbsolutePath();
                siteModel.saveText(filename);
            }
        }

        if (e.getSource() == openText) {

            JFileChooser chooser = new JFileChooser();
            int status = chooser.showOpenDialog(null);

            if (status == JFileChooser.APPROVE_OPTION)  {
                String filename = chooser.getSelectedFile().getAbsolutePath();
                siteModel.openText(filename);
            }
        }

        if(e.getSource() == deleteItem){
            siteModel.removeSiteInfo(table.getSelectedRow());
        }
        
        if (e.getSource() == fullView) {
        	new FullYearCalendar(getSiteListTakenDays());
        }
        
        if (e.getSource() == dailyView) {
        	new DailyViewCalendar(getSiteListTakenDays());
        }

        if (e.getSource() == undoItem) {
            siteModel.undo();
        }

        if (e.getSource() == redoItem) {
            siteModel.redo();
        }

        if (e.getSource() == exitItem) {
            System.exit(0);
        }
    }

    private class menuListener implements MenuListener {

        @Override
        public void menuCanceled(MenuEvent e) {
        }

        @Override
        public void menuDeselected(MenuEvent e) {
        }

        @Override
        public void menuSelected(MenuEvent e) {
            if (siteModel.atEnd())
                redoItem.setEnabled(false);
            else
                redoItem.setEnabled(true);

            if (siteModel.atBegin())
                undoItem.setEnabled(false);
            else
                undoItem.setEnabled(true);

            if(table.getSelectedRow() == -1)
                deleteItem.setEnabled(false);
            else
                deleteItem.setEnabled(true);
        }

    }

    public static void main(String[] args) {
        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Metal".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
        	 JOptionPane.showMessageDialog(null, "Unable to load Look and Feel, using default..", "Error", JOptionPane.INFORMATION_MESSAGE);

        }

        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createSurroundGUI();
            }
        });
    }

    public static void createSurroundGUI() {
        GUICampingReg frame = new GUICampingReg();
        frame.setSize(600, 300);
        frame.setVisible(true);
    }
}
