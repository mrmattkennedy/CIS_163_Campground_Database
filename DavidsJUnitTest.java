package campingPack;

import org.junit.Test;

import java.util.GregorianCalendar;

import java.io.*;

import static org.junit.Assert.*;

public class DavidsJUnitTest {


    //Region: SiteModel
    @Test
    public void testConstructor() {
        SiteModel s = new SiteModel();
        assertTrue(s.getSiteListSize() == 0);
        assertTrue(s.getUndoDiffSize() == 0);
        assertTrue(s.getRedoDiffSize() == 0);
        assertTrue(s.getTakenDaySize() == 0);
    }

    @Test
    public void testGetSiteListSize() {
        SiteModel s = new SiteModel();
        assertTrue(s.getSiteListSize() == 0);
    }

    @Test
    public void testGetSiteListSize2() {
        SiteModel s = new SiteModel();
        GregorianCalendar g1 = new GregorianCalendar(2022, 5, 6);
        try {
            s.addSiteInfo(new RV("David Baas", g1, 3, 1, 50));
        } catch (Exception e) {
            fail();
        }
        assertTrue(s.getSiteListSize() == 1);
    }

    @Test
    public void testGetUndoDiffListSize() {
        SiteModel s = new SiteModel();
        assertTrue(s.getUndoDiffSize() == 0);
    }

    @Test
    public void testGetUndoDiffListSize2() {
        SiteModel s = new SiteModel();
        GregorianCalendar g1 = new GregorianCalendar(2022, 5, 6);
        try {
            s.addSiteInfo(new RV("David Baas", g1, 3, 1, 50));
        } catch (Exception e) {
            fail();
        }
        assertTrue(s.getUndoDiffSize() == 1);
    }

    @Test
    public void testGetUndoDiffListSize3() {
        SiteModel s = new SiteModel();
        GregorianCalendar g1 = new GregorianCalendar(2022, 5, 6);
        try {
            s.addSiteInfo(new RV("David Baas", g1, 3, 1, 50));
        } catch (Exception e) {
            fail();
        }
        assertTrue(s.getUndoDiffSize() == 1);

        s.undo();
        assertTrue(s.getUndoDiffSize() == 0);
    }

    @Test
    public void testRedoDiffListSize() {
        SiteModel s = new SiteModel();
        assertTrue(s.getRedoDiffSize() == 0);
    }

    @Test
    public void testRedoDiffListSize2() {
        SiteModel s = new SiteModel();
        GregorianCalendar g1 = new GregorianCalendar(2022, 5, 6);
        try {
            s.addSiteInfo(new RV("David Baas", g1, 3, 1, 50));
        } catch (Exception e) {
            fail();
        }
        s.undo();
        assertTrue(s.getRedoDiffSize() == 1);
    }

    @Test
    public void testRedoDiffListSize3() {
        SiteModel s = new SiteModel();
        GregorianCalendar g1 = new GregorianCalendar(2022, 5, 6);
        try {
            s.addSiteInfo(new RV("David Baas", g1, 3, 1, 50));
        } catch (Exception e) {
            fail();
        }
        s.undo();
        assertTrue(s.getRedoDiffSize() == 1);
        s.redo();
        assertTrue(s.getRedoDiffSize() == 0);
    }

    @Test
    public void testgetTakenDayList() {
        SiteModel s = new SiteModel();
        assertTrue(s.getTakenDaySize() == 0);
    }

    @Test
    public void testgetTakenDayList2() {
        SiteModel s = new SiteModel();
        GregorianCalendar g1 = new GregorianCalendar(2022, 5, 6);
        GregorianCalendar g2 = new GregorianCalendar(2018, 9, 4);

        try {
            s.addSiteInfo(new RV("David Baas", g1, 3, 1, 50));
            s.addSiteInfo(new Tent("Alex Smith", g2, 7, 2, 100));
        } catch (Exception e) {
            fail();
        }
        assertTrue(s.getTakenDaySize() == 10);
    }

    @Test
    public void testgetTakenDayList3() {
        SiteModel s = new SiteModel();
        GregorianCalendar g1 = new GregorianCalendar(2022, 5, 6);
        GregorianCalendar g2 = new GregorianCalendar(2018, 9, 4);

        try {
            s.addSiteInfo(new RV("David Baas", g1, 3, 1, 50));
            s.addSiteInfo(new Tent("Alex Smith", g2, 7, 2, 100));
        } catch (Exception e) {
            fail();
        }
        assertTrue(s.getTakenDaySize() == 10);
        //remove the first entry:sorta like they check out.
        s.removeSiteInfo(0);
        assertTrue(s.getTakenDaySize() == 7);
    }

    @Test
    public void testAddSiteInfo() {
        SiteModel s = new SiteModel();
        GregorianCalendar g1 = new GregorianCalendar(2022, 5, 6);


        try {
            s.addSiteInfo(new RV("David Baas", g1, 3, 1, 50));
        } catch (Exception e) {
            fail();
        }

        assertTrue(s.getSiteListSize() == 1);
    }

    //try to add an invalid site
    @Test
    public void testAddSiteInfo2() {
        SiteModel s = new SiteModel();
        GregorianCalendar g1 = new GregorianCalendar(2022, 5, 6);
        GregorianCalendar g2 = new GregorianCalendar(2018, 9, 4);
        GregorianCalendar g3 = new GregorianCalendar(2017, 12, 25);
        try {
            s.addSiteInfo(new RV("David Baas", g1, 3, 1, 50));
            s.addSiteInfo(new Tent("Alex Smith", g2, 7, 2, 100));
            s.addSiteInfo(new RV("Mr Blobby", g3, 40, 3, 50));
            s.addSiteInfo(new Tent("JOESHMOE420", g3, 100, 3, 99));
        } catch (Exception e) {
            System.out.println("Oops. Somthing went wrong.");
        }

        assertTrue(s.getSiteListSize() == 3);
    }

    //Try to add a duplicate to the DB. I should not be added.
    @Test
    public void testAddSiteInfo3() {
        SiteModel s = new SiteModel();
        GregorianCalendar g1 = new GregorianCalendar(2022, 5, 6);
        GregorianCalendar g2 = new GregorianCalendar(2018, 9, 4);
        GregorianCalendar g3 = new GregorianCalendar(2017, 12, 25);
        try {
            s.addSiteInfo(new RV("David Baas", g1, 3, 1, 50));
            s.addSiteInfo(new Tent("Alex Smith", g2, 7, 2, 100));
            s.addSiteInfo(new RV("Mr Blobby", g3, 40, 3, 50));
            s.addSiteInfo(new RV("David Baas", g1, 3, 1, 50));
        } catch (Exception e) {
            System.out.println("Oops. Somthing went wrong.");
        }

        assertTrue(s.getSiteListSize() == 3);
    }

    @Test
    public void removeSiteInfo() {
        SiteModel s = new SiteModel();
        //load a file with 5 site entries on it.
        s.loadAccount("TestFile");
        s.removeSiteInfo(0);
        assertEquals(s.getSiteListSize() , 4);
    }

    @Test
    public void removeSiteInfo2() {
        SiteModel s = new SiteModel();
        //load a file with 5 site entries on it.
        s.loadAccount("TestFile");
        for (int i = 0; i < 5; i++)
            s.removeSiteInfo(0);
        assertTrue(s.getSiteListSize() == 0);
    }

    //try to remove from an index that doesn't exist
    @Test
    public void removeSiteInfo3() {
        SiteModel s = new SiteModel();
        //load a file with 5 site entries on it.
        s.loadAccount("TestFile");

        s.removeSiteInfo(7);
    }

    //Load a correct file.
    @Test
    public void testLoadSerial() {
        SiteModel s = new SiteModel();
        s.loadAccount("TestFile");
        assertTrue(s.getSiteListSize() == 5);
    }

    //Load a file that does not exist.
    @Test
    public void testLoadSerial2() {
        SiteModel s = new SiteModel();
        assertTrue(s.getSiteListSize() == 0);

        s.loadAccount("fAkE FiLe BrO");
        assertTrue(s.getSiteListSize() == 0);
    }

    //Try to load a file that isn't the correct type
    //In this case the incorrect file is a video file
    @Test
    public void testLoadSerial3() {
        SiteModel s = new SiteModel();
        assertTrue(s.getSiteListSize() == 0);

        s.loadAccount("UnderPressureLive.mp4");
        assertTrue(s.getSiteListSize() == 0);
    }

    @Test
    public void testLoadSerial4() {
        SiteModel s = new SiteModel();
        assertTrue(s.getSiteListSize() == 0);

        s.loadAccount("TestFile2");
        assertTrue(s.getSiteListSize() == 0);
    }

    @Test
    public void testSerialSave() {
        SiteModel s = new SiteModel();
        GregorianCalendar g1 = new GregorianCalendar(2022, 5, 6);
        GregorianCalendar g2 = new GregorianCalendar(2018, 9, 4);
        GregorianCalendar g3 = new GregorianCalendar(2017, 12, 25);
        try {
            s.addSiteInfo(new RV("David Baas", g1, 3, 1, 50));
            s.addSiteInfo(new Tent("Alex Smith", g2, 7, 2, 100));
            s.addSiteInfo(new RV("Mr Blobby", g3, 40, 3, 50));
        } catch (Exception e) {
            System.out.println("Oops. Somthing went wrong.");
        }

        s.saveAccount("JUnitFile");

        File file = new File("JUnitFile");
        assertTrue(file.exists());
    }

    //Trys to save a file with an invalid name.
    //ONLY VALID ON WINDOWS MACHINES
    @Test
    public void testSerialSave2() {
        SiteModel s = new SiteModel();
        GregorianCalendar g1 = new GregorianCalendar(2022, 5, 6);
        GregorianCalendar g2 = new GregorianCalendar(2018, 9, 4);
        GregorianCalendar g3 = new GregorianCalendar(2017, 12, 25);
        try {
            s.addSiteInfo(new RV("David Baas", g1, 3, 1, 50));
            s.addSiteInfo(new Tent("Alex Smith", g2, 7, 2, 100));
            s.addSiteInfo(new RV("Mr Blobby", g3, 40, 3, 50));
        } catch (Exception e) {
            System.out.println("Oops. Somthing went wrong.");
        }

        s.saveAccount("LPT9");

        File file = new File("LPT9");
        assertFalse(file.exists());

    }

    //Another Invalid File Name Test
    @Test
    public void testSerialSave3() {
        SiteModel s = new SiteModel();
        GregorianCalendar g1 = new GregorianCalendar(2022, 5, 6);
        GregorianCalendar g2 = new GregorianCalendar(2018, 9, 4);
        GregorianCalendar g3 = new GregorianCalendar(2017, 12, 25);
        try {
            s.addSiteInfo(new RV("David Baas", g1, 3, 1, 50));
            s.addSiteInfo(new Tent("Alex Smith", g2, 7, 2, 100));
            s.addSiteInfo(new RV("Mr Blobby", g3, 40, 3, 50));
        } catch (Exception e) {
            System.out.println("Oops. Somthing went wrong.");
        }

        s.saveAccount("fIlE\\0");

        File file = new File("fIlE\\0");
        assertFalse(file.exists());
    }

    //Test a normal text file Save
    @Test
    public void testTextSave() {
        SiteModel s = new SiteModel();
        GregorianCalendar g1 = new GregorianCalendar(2022, 5, 6);
        GregorianCalendar g2 = new GregorianCalendar(2018, 9, 4);
        GregorianCalendar g3 = new GregorianCalendar(2017, 12, 25);
        try {
            s.addSiteInfo(new RV("David Baas", g1, 3, 1, 50));
            s.addSiteInfo(new Tent("Alex Smith", g2, 7, 2, 100));
            s.addSiteInfo(new RV("Mr Blobby", g3, 40, 3, 50));
        } catch (Exception e) {
            System.out.println("Oops. Somthing went wrong.");
        }

        s.saveText("JUnitTextFile");

        File file = new File("JUnitTextFile");
        assertTrue(file.exists());
    }

    //Enter an Invalid File Name
    //ONLY WORKS ON WINDOWS
    @Test
    public void testTextSave2() {
        SiteModel s = new SiteModel();
        GregorianCalendar g1 = new GregorianCalendar(2022, 5, 6);
        GregorianCalendar g2 = new GregorianCalendar(2018, 9, 4);
        GregorianCalendar g3 = new GregorianCalendar(2017, 12, 25);
        try {
            s.addSiteInfo(new RV("David Baas", g1, 3, 1, 50));
            s.addSiteInfo(new Tent("Alex Smith", g2, 7, 2, 100));
            s.addSiteInfo(new RV("Mr Blobby", g3, 40, 3, 50));
        } catch (Exception e) {
            System.out.println("Oops. Somthing went wrong.");
        }

        s.saveText("AUX");

        File file = new File("AUX");
        assertFalse(file.exists());
    }

    //Enter an Invalid File Name
    //Works universally.
    @Test
    public void testTextSave3() {
        SiteModel s = new SiteModel();
        GregorianCalendar g1 = new GregorianCalendar(2022, 5, 6);
        GregorianCalendar g2 = new GregorianCalendar(2018, 9, 4);
        GregorianCalendar g3 = new GregorianCalendar(2017, 12, 25);
        try {
            s.addSiteInfo(new RV("David Baas", g1, 3, 1, 50));
            s.addSiteInfo(new Tent("Alex Smith", g2, 7, 2, 100));
            s.addSiteInfo(new RV("Mr Blobby", g3, 40, 3, 50));
        } catch (Exception e) {
            System.out.println("Oops. Somthing went wrong.");
        }

        s.saveText("bad/\\0");

        File file = new File("bad/\\0");
        assertFalse(file.exists());
    }

    //Test Loading a file
    @Test
    public void testLoadText() {
        SiteModel s = new SiteModel();

        s.openText("TestFileText");
        assertTrue(s.getSiteListSize() == 5);
    }

    //Test loading a file that doesn't exist
    @Test
    public void testLoadText2() {
        SiteModel s = new SiteModel();
        assertTrue(s.getSiteListSize() == 0);

        s.openText("badFile");
        assertTrue(s.getSiteListSize() == 0);
    }

    //Test loading a file that is invalid type (Not of type File)
    //Video File
    @Test
    public void testLoadText3() {
        SiteModel s = new SiteModel();
        assertTrue(s.getSiteListSize() == 0);

        s.openText("UnderPressureLive.mp4");
        assertTrue(s.getSiteListSize() == 0);
    }

    //Test loading an invalid File of type File
    @Test
    public void testLoadText4() {
        SiteModel s = new SiteModel();
        assertTrue(s.getSiteListSize() == 0);

        s.openText("TestFile");
        assertTrue(s.getSiteListSize() == 0);
    }

    //Test the getTakenDays
    //Test an empty DB
    @Test
    public void testGetTakenDays() {
        SiteModel s = new SiteModel();

        assertTrue(s.getTakenDaySize() == 0);
    }

    //Test the getTakenDays
    //Test a filled DB
    @Test
    public void testGetTakenDays2() {
        SiteModel s = new SiteModel();

        //load a db with five files
        //There are 442 separate day taken in this list
        s.loadAccount("TestFile");

        assertTrue(s.getTakenDaySize() == 442);
    }

    //Test the undo method
    //Checks there are items in the List
    @Test
    public void testUndo() {
        SiteModel s = new SiteModel();
        GregorianCalendar g1 = new GregorianCalendar(2022, 5, 6);
        GregorianCalendar g2 = new GregorianCalendar(2018, 9, 4);
        GregorianCalendar g3 = new GregorianCalendar(2017, 12, 25);
        try {
            s.addSiteInfo(new RV("David Baas", g1, 3, 1, 50));
            s.addSiteInfo(new Tent("Alex Smith", g2, 7, 2, 100));
            s.addSiteInfo(new RV("Mr Blobby", g3, 40, 3, 50));
        } catch (Exception e) {
            System.out.println("Oops. Somthing went wrong.");
        }
        assertTrue(s.getUndoDiffSize() == 3);
        s.undo();
        s.undo();
        s.undo();
        assertTrue(s.getUndoDiffSize() == 0);
    }

    //Test the undo method with removing sites
    @Test
    public void testUndo2() {
        SiteModel s = new SiteModel();
        GregorianCalendar g1 = new GregorianCalendar(2022, 5, 6);
        GregorianCalendar g2 = new GregorianCalendar(2018, 9, 4);
        GregorianCalendar g3 = new GregorianCalendar(2017, 12, 25);
        try {
            s.addSiteInfo(new RV("David Baas", g1, 3, 1, 50));
            s.addSiteInfo(new Tent("Alex Smith", g2, 7, 2, 100));
            s.addSiteInfo(new RV("Mr Blobby", g3, 40, 3, 50));
        } catch (Exception e) {
            System.out.println("Oops. Somthing went wrong.");
        }

        s.removeSiteInfo(0);
        s.removeSiteInfo(1);
        assertTrue(s.getSiteListSize() == 1);
        assertTrue(s.getUndoDiffSize() == 5);
        s.undo();
        s.undo();
        s.undo();
        assertTrue(s.getUndoDiffSize() == 2);
    }

    //Test the redo method
    @Test
    public void testRedo() {
        SiteModel s = new SiteModel();
        GregorianCalendar g1 = new GregorianCalendar(2022, 5, 6);
        GregorianCalendar g2 = new GregorianCalendar(2018, 9, 4);
        GregorianCalendar g3 = new GregorianCalendar(2017, 12, 25);
        try {
            s.addSiteInfo(new RV("David Baas", g1, 3, 1, 50));
            s.addSiteInfo(new Tent("Alex Smith", g2, 7, 2, 100));
            s.addSiteInfo(new RV("Mr Blobby", g3, 40, 3, 50));
        } catch (Exception e) {
            System.out.println("Oops. Somthing went wrong.");
        }

        s.undo();
        s.undo();
        s.undo();

        s.redo();
        s.redo();
        s.redo();
        assertTrue(s.getUndoDiffSize() == 3);
        assertTrue(s.getRedoDiffSize() == 0);
        assertTrue(s.getSiteListSize() == 3);
    }

    //Test the redo method with removing sites
    @Test
    public void testRedo2() {
        SiteModel s = new SiteModel();
        GregorianCalendar g1 = new GregorianCalendar(2022, 5, 6);
        GregorianCalendar g2 = new GregorianCalendar(2018, 9, 4);
        GregorianCalendar g3 = new GregorianCalendar(2017, 12, 25);
        try {
            s.addSiteInfo(new RV("David Baas", g1, 3, 1, 50));
            s.addSiteInfo(new Tent("Alex Smith", g2, 7, 2, 100));
            s.addSiteInfo(new RV("Mr Blobby", g3, 40, 3, 50));
        } catch (Exception e) {
            System.out.println("Oops. Somthing went wrong.");
        }

        s.removeSiteInfo(0);
        s.removeSiteInfo(1);

        assertTrue(s.getSiteListSize() == 1);
        assertTrue( s.getUndoDiffSize() == 5);
        s.undo();
        s.undo();
        s.undo();

        s.redo();
        s.redo();

        assertTrue(s.getUndoDiffSize() == 4);
        assertTrue(s.getRedoDiffSize() == 1);
        assertTrue(s.getSiteListSize() == 2);
    }

    //Test that all redo items are removed after a new action has been taken
    @Test
    public void testRedo3() {
        SiteModel s = new SiteModel();
        GregorianCalendar g1 = new GregorianCalendar(2022, 5, 6);
        GregorianCalendar g2 = new GregorianCalendar(2018, 9, 4);
        GregorianCalendar g3 = new GregorianCalendar(2017, 12, 25);
        try {
            s.addSiteInfo(new RV("David Baas", g1, 3, 1, 50));
            s.addSiteInfo(new Tent("Alex Smith", g2, 7, 2, 100));
            s.addSiteInfo(new RV("Mr Blobby", g3, 40, 3, 50));
        } catch (Exception e) {
            System.out.println("Oops. Somthing went wrong.");
        }

        assertTrue( s.getUndoDiffSize() == 3);
        s.undo();
        s.undo();
        s.undo();

        assertTrue(s.getUndoDiffSize() == 0);
        assertTrue(s.getRedoDiffSize() == 3);
        try{
            s.addSiteInfo(new RV("David Baas", g1, 3, 1, 50));
        }
        catch (Exception e1){
            System.out.println("Oops. Something went wrong ");
        }
        assertTrue(s.getRedoDiffSize() == 0);
    }

    //Test the atEnd method
    @Test
    public void testAtEndAndAtBegin(){
        SiteModel s = new SiteModel();

        assertTrue(s.atBegin());
        assertTrue(s.atEnd());

        GregorianCalendar g1 = new GregorianCalendar(2022, 5, 6);
        GregorianCalendar g2 = new GregorianCalendar(2018, 9, 4);
        GregorianCalendar g3 = new GregorianCalendar(2017, 12, 25);
        try {
            s.addSiteInfo(new RV("David Baas", g1, 3, 1, 50));
            s.addSiteInfo(new Tent("Alex Smith", g2, 7, 2, 100));
            s.addSiteInfo(new RV("Mr Blobby", g3, 40, 3, 50));
        } catch (Exception e) {
            System.out.println("Oops. Somthing went wrong.");
        }

        assertTrue(s.atEnd());
        assertFalse(s.atBegin());
        s.undo();
        s.undo();
        s.undo();

        assertFalse(s.atEnd());
        assertTrue(s.atBegin());

        s.redo();
        s.redo();
        s.redo();

        assertTrue(s.atEnd());
        assertFalse(s.atBegin());

    }

    //Test the getRowCountMethod that overrides Abstract Table Model
    @Test
    public void testGetRowCount(){
        SiteModel s = new SiteModel();

        assertTrue(s.atBegin());
        assertTrue(s.atEnd());

        GregorianCalendar g1 = new GregorianCalendar(2022, 5, 6);
        GregorianCalendar g2 = new GregorianCalendar(2018, 9, 4);
        GregorianCalendar g3 = new GregorianCalendar(2017, 12, 25);
        try {
            s.addSiteInfo(new RV("David Baas", g1, 3, 1, 50));
            s.addSiteInfo(new Tent("Alex Smith", g2, 7, 2, 100));
            s.addSiteInfo(new RV("Mr Blobby", g3, 40, 3, 50));
        } catch (Exception e) {
            System.out.println("Oops. Somthing went wrong.");
        }

        assertEquals(s.getRowCount(),3);
    }

    //Test get ColumnCount
    @Test
    public void testGetColCount(){
        SiteModel s = new SiteModel();
        assertEquals(s.getColumnCount(), 5);
    }

    //Test that the column names are what they need to be.
    @Test
    public void testColNames(){
        String[] columnNames = { "Name Reserving", "Checked in",
                "Days Staying", "Site #", "Tent/RV info"};

        SiteModel s = new SiteModel();

        for (int i = 0; i < columnNames.length; i++)
            assertEquals(s.getColumnName(i),columnNames[i]);
    }
}

