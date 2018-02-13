package campingPack;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;
import java.io.*;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Scanner;

/**********************************************************************
 * The SiteModel Class is the database that stores all the data on all
 * the sites being occupied on the campground. It allows for adding,
 * updating, and deleting of site info on the campground. The SiteModel
 * also extends the AbstractTableModel Class. This allows for the JTable
 * created in the GUICampingReg Class to update and display all the
 * site info in a formatted table. SiteModel handles the save and load
 * functions of the Campsite program allowing for a site database to be
 * saved as/loaded as as a serial or a text file. Finally, SiteModel
 * also handles the undoing and redoing of actions whenever a misake is
 * made by the user and they want it undone/redone.
 *
 * @author David Baas, Noah Goncher, Matthew Kennedy
 * @version 5.0
 *********************************************************************/
public class SiteModel extends AbstractTableModel{
    /**an ArrayList to serve as the database of sites*/
    private ArrayList<Site> listSites;

    /**A String array with the names of various data fields*/
    private String[] columnNames = { "Name Reserving", "Checked in",
            "Days Staying", "Site #", "Tent/RV info"};

    /**An arrayList of sites added or deleated that can have the
     * action performed on them undone*/
    private ArrayList<Site> undoDiff;

    /**An ArrayList of sites added or removed by the user undoing
     * something.*/
    private ArrayList<Site> redoDiff;

    /**An ArrayList of Strings used to determine if a date is taken.
     * It will have the GregorianCalendar object converted to a string,
     * and it will have a new string for every object. At the end of
     * every String, there will be an indicator of what site the
     * date is for. */
    private ArrayList<String> takenDays;

    /**********************************************************************
     * The Constructor for SiteModel. The constructor initializes all of the
     * private ArrayList being used in the database.
     *********************************************************************/
    public SiteModel(){
        listSites = new ArrayList<Site>();
        undoDiff = new ArrayList<Site>();
        redoDiff = new ArrayList<Site>();
        takenDays = new ArrayList<String>();
    }

    /**********************************************************************
     * Used exclusively in testing, the getSiteListSize method will return
     * the current size of database
     * @return the size of the Database
     *********************************************************************/
    public int getSiteListSize() {
        return listSites.size();
    }

    /**********************************************************************
     * Used exclusively in testing, the getUndoDiffSize method will return
     * the current size of the undo differential.
     * @return the size of the undo differential.
     *********************************************************************/
    public int getUndoDiffSize(){
        return undoDiff.size();
    }

    /**********************************************************************
     * Used exclusively in testing, the getRedoDiffSize method will return
     * the current size of the redo differential.
     * @return the size of the redo differential.
     *********************************************************************/
    public int getRedoDiffSize(){
        return redoDiff.size();
    }


    /**********************************************************************
     * Used exclusively in testing, the getTakenDaySize method will return
     * the current size of the takenDays list.
     * @return the size of the takenDays list.
     *********************************************************************/
    public int getTakenDaySize(){
        return takenDays.size();
    }

    /**********************************************************************
     * The addSiteInfo method takes a site input from the user and stores
     * it intothe database. After storing the data, the table is updated
     * to reflectthe changes made. The site is also archived into the
     * undoDiff for ifthe user want to undo their addition later on.
     *
     * @param s Site being added to the database.
     *********************************************************************/
    public void addSiteInfo(Site s) {
        for(Site t: listSites)
            if(t.equals(s))
                return;
        listSites.add(s);
        fireTableRowsInserted(listSites.size()-1,listSites.size()-1);
        archiveList(s);
        addSiteDates(s);

    }

    /**********************************************************************
     * The removeSiteInfo method allows the program to remove a specific
     * site from the database. After the removal of the of the site,
     * it updates the table to reflect the change. The site that was removed
     * is also archived into undoDiff with information on what row it belonged
     * to if the user wants to undo their deletion later on.
     *
     * @param row Site being removed from the database.
     * @throws IndexOutOfBoundsException if the user trys to remove a row
     * that does not exist.
     *********************************************************************/
    public void removeSiteInfo(int row){
        try {
            listSites.get(row).setIndex(row);
            removeSiteDates(listSites.get(row));
            fireTableRowsDeleted(row,row);
            archiveList(listSites.get(row));
            listSites.remove(row);
        }
        catch (IndexOutOfBoundsException e){
            System.out.println("Row does not exist.");
        }

    }

    /**********************************************************************
     * The saveAccount method creates and saves the database as a
     * serialized file in the location chosen by the user in the GUI
     *
     * @param filename String name of the file being saved.
     * @throws Exception if something goes wrong saving the file.
     *********************************************************************/
    public void saveAccount(String filename) {
        File f = new File(filename);
        try {
            FileOutputStream outputStream = new FileOutputStream(f);
            ObjectOutputStream out = new ObjectOutputStream(outputStream);

            out.writeObject(listSites);
            outputStream.close();
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null,
                    "Something went Wrong.");
        }
    }

    /**********************************************************************
     * The loadAccount method takes the serialized file found by the user
     * and loads it into the database. This entails replacing all the
     * data that was currently in the database and inserting the new data
     * in. It also takes all the data in the redoDiff and undoDiff list
     * and clears it away.
     *
     * @param filename String name of the file being loaded into the
     *                 database.
     * @throws Exception if there is an error loading the file
     *********************************************************************/
    public void loadAccount(String filename){
        try {
            FileInputStream fis = new FileInputStream(filename);
            ObjectInputStream ois = new ObjectInputStream(fis);
            String temp = null;

            listSites = (ArrayList<Site>)ois.readObject();
            ois.close();
            fireTableRowsInserted(listSites.size()-1,listSites.size()-1);
            takenDays.clear();
            redoDiff.clear();
            undoDiff.clear();

            for (int i = 0; i < listSites.size(); i++)
                addSiteDates(listSites.get(i));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Something went Wrong.");

        }
    }

    /**********************************************************************
     * The saveText method creates and saves a file containing the database as a
     * formatted text file in the location chosen by the user in the GUI.
     *
     * @param filename String name of the file being saved.
     * @throws IOException if there is a problem saving the file.
     *********************************************************************/
    public void saveText(String filename) {

        try {
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(filename)));
//            out.println(listSites.size());
//            out.println();
            for (int i = 0; i < listSites.size(); i++) {
                // Output the class name.
                out.print(listSites.get(i).getClass().getName() + ",");

                // Output the owner's name.
                out.print(listSites.get(i).occupant + ",");

                // Output the OccupyOn date to a file in a readable format.
                out.print(listSites.get(i).formatCalendar(listSites.get(i).reservDate)
                        + ",");

                out.print(listSites.get(i).duration + ",");

                out.print(listSites.get(i).siteNum + ",");

                if (listSites.get(i) instanceof RV)
                    out.println(((RV) listSites.get(i)).getPower());
                else if (listSites.get(i) instanceof Tent)
                    out.println(((Tent) listSites.get(i)).getNumTenters());
                out.println();
            }
            out.close();

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null,
                    "Something went Wrong.");
        }
    }


    /**********************************************************************
     * The loadText method takes a textFile loaded in by the user and
     * replaces the current data in the database with the data loaded from
     * the text file. The undoDiff and redoDiff ArrayList are also cleared.
     *
     * @param filename String name of the file being loaded.
     * @throws Exception if there is an error loading the file.
     *********************************************************************/
    public void openText(String filename) {
        listSites.clear();
        takenDays.clear();
        redoDiff.clear();
        undoDiff.clear();

        Scanner inFS = null;
        FileInputStream fileByteStream = null;
        //TEST ME: THIS CLASS MAY NOT BE CORRECT
        try{
            // open the File and set delimiters
            fileByteStream = new FileInputStream(filename);
            inFS = new Scanner(fileByteStream);
            inFS.useDelimiter("[,\r\n]+");

            // continue while there is more data to read
            while(inFS.hasNext()) {
                Site unit = null;

                // read five data elements
                String siteType = inFS.next();
                String name = inFS.next();
                String dateStr = inFS.next();
                int duration = inFS.nextInt();
                int siteNum = inFS.nextInt();
                int specialData = inFS.nextInt();

                String[] date = dateStr.split("/");
                GregorianCalendar g1 = new GregorianCalendar
                        ( Integer.parseInt
                                (date[2]),Integer.parseInt(date[0]) - 1,
                                Integer.parseInt(date[1]));

                if(siteType.equals("campingPack.RV"))
                    unit = new RV(name, g1, duration, siteNum, specialData);

                else if(siteType.equals("campingPack.Tent"))
                    unit = new Tent(name, g1, duration, siteNum, specialData);

                if(unit != null) {
                    listSites.add(unit);
                    addSiteDates(unit);
                }

            }
            fileByteStream.close();

            // error while reading the file
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,
                    "Something went Wrong.");

        }

        fireTableRowsInserted(listSites.size()-1,listSites.size()-1);
    }

    /**********************************************************************
     * This method return an ArrayList of Strings that are the taken days
     * of all sites in the Database
     *
     * @return ArrayList of Strings takenDays
     */
    public ArrayList<String> getTakenDays() {
        ArrayList<String> temp = takenDays;
        return temp;
    }

    /**********************************************************************
     * This private method is invoked when a site is added to the database.
     * It analyzes the taken days of a given reservation and adds them to
     * the ArrayList of taken days as a string that follows the format
     * mm/dd/yyyy:siteNum
     * @param s Site being input.
     *********************************************************************/
    private void addSiteDates(Site s) {
        String temp;
        String startDate = s.formatCalendar(s.reservDate);
        String[] dateArray = startDate.split("/");

        int currentMonth = Integer.parseInt(dateArray[0]);
        int currentDay = Integer.parseInt(dateArray[1]);
        int currentYear = Integer.parseInt(dateArray[2]);
        int maxDays = CampingCalc.getMaxDays(currentMonth, currentYear);
        int duration = s.duration;
        int currentSite = s.siteNum;

        for (int i = 0; i < duration; i++) {
            temp = currentMonth + "/"
                    + currentDay + "/"
                    + currentYear + ": "
                    + currentSite;
            takenDays.add(temp);

            if (currentDay == maxDays) {
                if (currentMonth + 1 == 13) {
                    currentDay = 1;
                    currentMonth = 1;
                    currentYear +=1;

                } else {
                    currentMonth +=1;
                    currentDay = 1;

                }
                maxDays = CampingCalc.getMaxDays(currentMonth, currentYear);

            } else {
                currentDay +=1;
            }
        }
    }

    /**********************************************************************
     * This private method is invoked when a site is removed. When a site
     * is removed, This method looks through the arraylist of taken days and
     * checks if its date is removed with the removed site. If the date is
     * being removed, it is taken out of the arrayList.
     * @param s Site being removed from the DataBase
     */
    private void removeSiteDates(Site s) {
        String temp;
        String startDate = s.formatCalendar(s.reservDate);
        String[] dateArray = startDate.split("/");

        int currentMonth = Integer.parseInt(dateArray[0]);
        int currentDay = Integer.parseInt(dateArray[1]);
        int currentYear = Integer.parseInt(dateArray[2]);
        int maxDays = CampingCalc.getMaxDays(currentMonth, currentYear);
        int duration = s.duration;
        int currentSite = s.siteNum;

        for (int i = 0; i < duration; i++) {
            temp = currentMonth + "/"
                    + currentDay + "/"
                    + currentYear + ": "
                    + currentSite;

            for (int j = 0; j < takenDays.size(); j++)
                if (temp.equals(takenDays.get(j))) {
                    takenDays.remove(j);
                    break;
                }

            if (currentDay == maxDays) {
                if (currentMonth + 1 == 13) {
                    currentDay = 1;
                    currentMonth = 1;
                    currentYear +=1;

                } else {
                    currentMonth +=1;
                    currentDay = 1;

                }
                maxDays = CampingCalc.getMaxDays(currentMonth, currentYear);

            } else {
                currentDay +=1;
            }

        }
    }


    /**********************************************************************
     * This method takes a site and archives it into the undoDiff list
     * for later if the user wants to undo an action.
     *
     * @param s Site being added to the undoDiff List.
     *********************************************************************/
    private void archiveList(Site s){
        undoDiff.add(s);
        if(!redoDiff.isEmpty())
            redoDiff.clear();
    }

    /**********************************************************************
     * The redo method allows any undone action to be redone returning the
     * database to a previous state that has been undone. Once this action
     * has been taken, the table is updated.
     *********************************************************************/
    //Needs more details in method description.
    public void redo(){
        if(!atEnd()) {
            Site s = redoDiff.get(redoDiff.size() - 1);
            if(!listSites.isEmpty())
                fireTableRowsDeleted(0, listSites.size() - 1);

            //remove an item if it is in the database
            if(listSites.contains(s)) {
                s.setIndex(listSites.indexOf(s));
                removeSiteDates(s);
                listSites.remove(s);
                redoDiff.remove(s);
                undoDiff.add(s);
            }
            //re-add the item to the database if it was removed previously.
            else {
                listSites.add(s.getIndex(),s);
                addSiteDates(s);
                redoDiff.remove(s);
                undoDiff.add(s);
            }

            repaintTable();
        }
    }

    /**********************************************************************
     * The undo method takes the last entry in the undoDff ArrayList and
     * removes/ re adds the site back into the database depending on what
     * happened to the itme last. After taking this action the table is
     * updated to reflect the change.
     *********************************************************************/
    public void undo() {
        if(!atBegin()) {
            Site s = undoDiff.get(undoDiff.size() - 1);
            if(!listSites.isEmpty())
                fireTableRowsDeleted(0, listSites.size() - 1);

            //Removes an item from the database that was already added.
            if (listSites.contains(s)) {
                s.setIndex(listSites.indexOf(s));
                removeSiteDates(s);
                listSites.remove(s);
                undoDiff.remove(s);
                redoDiff.add(s);
            }
            else {
                listSites.add(s.getIndex(),s);
                addSiteDates(s);
                undoDiff.remove(s);
                redoDiff.add(s);
            }

            repaintTable();
        }
    }

    /**********************************************************************
     * Method tells the program if there is nothing to redo or not
     * This is useful to allow for the disabling of the redo Button in the
     * GUICampinReg class.
     *
     * @return true if the redoDiff List is empty, false otherwise.
     *********************************************************************/
    public boolean atEnd(){
        if (redoDiff.isEmpty())
            return true;
        return false;
    }

    /**********************************************************************
     * Method tells the program if there is nothing to undo or not.
     * This is useful to allow the disabling of the undo Button in the
     * GUICampingReg class.
     *
     * @return true if the undoDiff is empty, false otherwise.
     *********************************************************************/
    public boolean atBegin(){
        if(undoDiff.isEmpty())
            return true;
        return false;
    }

    /**********************************************************************
     * The private repaintTable method updates the table and is ued after
     * an action has been undone or redone.
     *********************************************************************/
    private void repaintTable() {
        fireTableRowsInserted(0, listSites.size() - 1);
        fireTableRowsUpdated(0, listSites.size() - 1);
    }

    /**********************************************************************
     * Method tells the Jtable how may rows there are in the table.
     *
     * @return listSites.size() which is the number of items in the database
     * and the number of rows in the table.
     *********************************************************************/
    @Override
    public int getRowCount() {
        return listSites.size();
    }

    /**********************************************************************
     * Method tells the JTable how may column there are for the JTable.
     *
     * @return columnNames.length the number of data points in a
     * site and the number of columns in the table.
     *********************************************************************/
    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    /**********************************************************************
     * Method tells the JTable what the names of the columns are for the
     * table
     * @param col the column being looked up
     *
     * @return columnNames[col] The name of the column bering searched for.
     *********************************************************************/
    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    /**********************************************************************
     * Method tells the JTable what to populate the table with.
     * this method is called in a for loop.
     *
     * @param rowIndex row of the item being added to table
     * @param columnIndex column of the item being added to the table
     * @return The Item at rowIndex,columnIndex.
     *********************************************************************/
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Site s = listSites.get(rowIndex);

        switch (columnIndex){
            case 0:
                return s.occupant;

            case 1:
                return s.formatCalendar(s.reservDate);

            case 2:
                return s.duration;

            case 3:
                return s.siteNum;

            case 4:
                if(s instanceof Tent)
                    return ((Tent)s).getNumTenters() + " Tents";
                else
                    return ((RV)s).getPower() + " Amps";
        }

        return null;
    }
}

