package campingPack;

import org.junit.Test;

import java.util.GregorianCalendar;

import java.io.*;
import java.util.Locale;

import static org.junit.Assert.*;



public class UnitTesting {
    @Test //handles to instantiate a null constructor, expected to fail.
    public void testDefaultTentConstructor() {
        try {
            new Tent();

        }
        catch(Exception e) {
            fail();
        }
    }

    @Test //tests the Site class's get and set methods through instantiating constructors.
    public void testSiteGetAndSets(){
        Site t = null;
        Site t2 = null;

        try{
            t = new Tent("Noah Goncher", new GregorianCalendar(2017, 11, 11), 5, 5, 5);
            t2 = new Tent("Noah Goncher", "11/12/2017", "11/15/2017", 3, 10);
        }
        catch(Exception e){

        }
        assertEquals(t.getOccupant(), "Noah Goncher");
        assertEquals(t.getReservDate(), new GregorianCalendar(2017, 11, 11));
        assertEquals(t.getDuration(), 5 );
        assertEquals(t.getSiteNum(), 5);

        assertEquals(t2.getOccupant(), "Noah Goncher");
        assertEquals(t2.getReservDate(), new GregorianCalendar(2017, 11, 12));
        assertEquals(t2.getDuration(), 4);
        assertEquals(t2.getSiteNum(), 3);
    }

    @Test //tests to see if the name was left as the default name, expected to fail.
    public void testTentConstructorDefaultName() {
        Site t = null;
        try {
            t = new Tent("Firstname Lastname", new GregorianCalendar(2017, 11, 11), 5, 5, 5);
        }
        catch(Exception e) {
            ;
        }
        assertEquals(t, null);
    }

    @Test //tests to see if the name has a space before the first name, expected to fail.
    public void testTentConstructorSpaceInFirstName() {
        Site t = null;
        try {
            t = new Tent(" Noah", new GregorianCalendar(2017, 11, 11), 5, 5, 5);
        }
        catch(Exception e) {
            ;
        }
        assertEquals(t, null);
    }

    @Test //tests to see if the last name has even been generated, expected to fail.
    public void testTentConstructorSpaceInMiddleofName() {
        Site t = null;
        try {
            t = new Tent("Noah ", new GregorianCalendar(2017, 11, 11), 5, 5, 5);
        }
        catch(Exception e) {
            ;
        }
        assertEquals(t, null);
    }

    @Test //tests to see if there is more than one space in the entire name, expected to fail.
    public void testTentConstructorMultipleSpaces() {
        Site t = null;
        try {
            t = new Tent("Noah Goncher ", new GregorianCalendar(2017, 11, 11), 5, 5, 5);
        }
        catch(Exception e) {
            ;
        }
        assertEquals(t, null);
    }

    @Test //tests to see if the user has entered anything other than a letter for their name, expected to fail.
    public void testTentConstructorExoticName() {
        Site t = null;
        try {
            t = new Tent("Noah Gonche2", new GregorianCalendar(2017, 11, 11), 5, 5, 5);
        }
        catch(Exception e) {
            ;
        }
        assertEquals(t, null);
    }

    @Test //tests to see if the user has entered any day before november 1st, expected to fail.
    public void testTentConstructorPastDate() {
        Site t = null;
        try {
            t = new Tent("Noah Goncher", new GregorianCalendar(2017, 10, 11), 5, 5, 5);
        }
        catch(Exception e) {
            ;
        }
        assertEquals(t, null);
    }

    @Test //tests to see if the user has entered a date that is not valid for a non-leap year, expected to fail.
    public void testTentConstructorNotLeapYear() {
        Site t = null;
        try {
            t = new Tent("Noah Goncher", new GregorianCalendar(2021, 2, 29), 5, 5, 5);
        }
        catch(Exception e) {
            ;
        }
        assertEquals(t, null);
    }

    @Test // tests to see if the user has entered a date past the year 2037, which is the maximum year, expected to fail.
    public void testTentConstructorMaxYear() {
        Site t = null;
        try {
            t = new Tent("Noah Goncher", new GregorianCalendar(2038, 1, 1), 5, 5, 5);
        }
        catch(Exception e) {
            ;
        }
        assertEquals(t, null);
    }

    @Test //tests to see if the user has entered a day that is greater than what the month actually has, expected to fail.
    public void testTentConstructorMaxDay() {
        Site t = null;
        try {
            t = new Tent("Noah Goncher", new GregorianCalendar(2017, 12, 32), 5, 5, 5);
        }
        catch(Exception e) {
            ;
        }
        assertEquals(t, null);
    }

    @Test //checks another month for its max day, expected to fail.
    public void testOtherMaxDay(){
        Site t = null;
        try{
            t = new Tent("Noah Goncher", new GregorianCalendar(2019, 4, 31), 5, 5, 5);
        }
        catch(Exception e){

        }
        assertEquals(t, null);
    }

    @Test //checks another month for its max day, expected to fail.
    public void testAnotherMaxDay(){
        Site t = null;
        try{
            t = new Tent("Noah Goncher", new GregorianCalendar(2020, 3, 32), 5, 5, 5);
        }
        catch(Exception e){

        }
        assertEquals(t, null);
    }

    @Test //tests to see if the month entered is not greater than 12, expected to fail.
    public void testTentConstructorMaxMonth() {
        Site t = null;
        try {
            t = new Tent("Noah Goncher", new GregorianCalendar(2017, 13, 11), 5, 5, 5);
        }
        catch(Exception e) {
            ;
        }
        assertEquals(t, null);
    }

    @Test //checks to see if the entered value is zero, expected to fail.
    public void testTentConstructorZeroDay() {
        Site t = null;
        try {
            t = new Tent("Noah Goncher", new GregorianCalendar(2017, 10, 0), 5, 5, 5);
        }
        catch(Exception e) {
            ;
        }
        assertEquals(t, null);
    }

//    @Test //tests every day, month, and year to see if it is valid, expected to PASS.
//    public void testAllYears() {
//        Tent t = null;
//
//        for(int i = 1; i < 20; i++) {
//            for(int j = 1; j < 12; j++) {
//                for(int k = 1; k <= CampingCalc.getMaxDays(j, i + 2017); k++) {
//                    try {
//                        t = new Tent("Noah Goncher", new GregorianCalendar(i + 2017, j, k), 5, 5, 5);
//                    }
//                    catch(Exception e) {
//                        ;
//                    }
//                    assertEquals(t.occupant, "Noah Goncher");
//                    assertEquals(t.formatCalendar(t.reservDate), (j) + "/" + k + "/" + (i+2017));
//                    assertEquals(t.duration, 5);
//                    assertEquals(t.siteNum, 5);
//                    assertEquals(t.getNumTenters(), 5);
//                }
//            }
//        }
//    }

    @Test //tests the constructor that accepts two date inputs of type String, expected to PASS.
    public void testStartDateEndDateConstructor(){
        Tent t = null;
        try{
            t = new Tent("Noah Goncher", "12/10/2017", "12/14/2017", 5, 5);

        }
        catch(Exception e){
            ;
        }
        assertEquals(t.occupant, "Noah Goncher");
        assertEquals(t.reservDate, new GregorianCalendar(2017, 12, 10));
        assertEquals(t.duration, 5);
        assertEquals(t.siteNum, 5);
        assertEquals(t.getNumTenters(), 5);
    }

    @Test //tests to make sure the user did not enter the end date as a date less than the starting reservation date, expected to fail.
    public void testIncorrectReservation(){
        Site t = null;
        try{
            t = new Tent("Noah Goncher", "12/11/2017", "12/10/2017", 5, 5);
        }
        catch(Exception e){
            ;
        }
        assertEquals(t, null);
    }

    @Test //tests the get and set methods fo get and set methods.
    public void testGetAndSetNumTenters(){
        Tent t = new Tent();

        t.setNumTenters(5);

        assertTrue(t.getNumTenters() == 5);
    }

    @Test(expected = IllegalTentsException.class) //checks for an illegal value for number of tents above 100, fails.
    public void testCheckNumTents(){
        Tent t = new Tent();

        t.setNumTenters(1000);

        t.checkNumTents(t.getNumTenters());
    }

    @Test(expected = IllegalTentsException.class) //same as above, but below zero.
    public void testCheckNumTents2(){
        Tent t = new Tent();

        t.setNumTenters(-1000);

        t.checkNumTents(t.getNumTenters());
    }

    @Test
    public void testGetAndSetCost(){ //tests the get and set cost functions.
        Tent t = new Tent();

        t.duration = 5;

        t.setNumTenters(5);

        t.setCost();

        assertTrue(t.getCost() == 75.0);
    }

    @Test
    public void testGetAndSetIndex(){ //tests the get and set index functions in Site.
        Site t = null;

        t = new Tent();

        t.setIndex(4);

        assertTrue(t.getIndex() == 4);
        assertFalse(t.getIndex() == 0);
    }

    @Test
    public void testEquals(){ //tests the equals method in Site.
        Site t = null;
        Tent t2 = null;
        Site t3 = null;
        Tent t4 = null;

        try{
            t = new Tent("Noah Goncher", new GregorianCalendar(2017, 11, 11), 5, 5, 5);
            t2 = new Tent("Noah Goncher", new GregorianCalendar(2017, 11, 11), 5, 5, 5);

            t3 = new Tent("Noah Goncher", "12/11/2017", "12/14/2017", 5, 5);
            t4 = new Tent("Noah Goncher", "12/11/2017", "12/14/2017", 5, 5);
        }
        catch(Exception e){

        }

        assertTrue(t2.equals(t));
        assertTrue(t4.equals(t3));
        assertFalse(t3.equals(t));
    }

    @Test
    public void testDefaultRVConstructor() {
        try {
            new RV();
        }
        catch(Exception e) {
            fail();
        }
    }

    @Test
    public void testSiteGetAndSets_RV(){
        Site r = null;
        Site r2 = null;

        try{
            r = new RV("Noah Goncher", new GregorianCalendar(2017, 11, 11), 5, 5, 30);
            r2 = new RV("Noah Goncher", "11/12/2017", "11/15/2017", 3, 40);
        }
        catch(Exception e){

        }
        assertEquals(r.getOccupant(), "Noah Goncher");
        assertEquals(r.getReservDate(), new GregorianCalendar(2017, 11, 11));
        assertEquals(r.getDuration(), 5 );
        assertEquals(r.getSiteNum(), 5);

        assertEquals(r2.getOccupant(), "Noah Goncher");
        assertEquals(r2.getReservDate(), new GregorianCalendar(2017, 11, 12));
        assertEquals(r2.getDuration(), 4);
        assertEquals(r2.getSiteNum(), 3);
    }

    @Test
    public void testRVConstructorDefaultName() {
        Site r = null;
        try {
            r = new RV("Firstname Lastname", new GregorianCalendar(2017, 11, 11), 5, 5, 30);
        }
        catch(Exception e) {
            ;
        }
        assertEquals(r, null);
    }

    @Test
    public void testRVConstructorSpaceInFirstName() {
        Site r = null;
        try {
            r = new RV(" Noah", new GregorianCalendar(2017, 11, 11), 5, 5, 30);
        }
        catch(Exception e) {
            ;
        }
        assertEquals(r, null);
    }

    @Test
    public void testRVConstructorSpaceInMiddleofName() {
        Site r = null;
        try {
            r = new RV("Noah ", new GregorianCalendar(2017, 11, 11), 5, 5, 30);
        }
        catch(Exception e) {
            ;
        }
        assertEquals(r, null);
    }

    @Test
    public void testRVConstructorMultipleSpaces() {
        Site r = null;
        try {
            r = new RV("Noah Goncher ", new GregorianCalendar(2017, 11, 11), 5, 5, 30);
        }
        catch(Exception e) {
            ;
        }
        assertEquals(r, null);
    }

    @Test
    public void testRVConstructorExoticName() {
        Site r = null;
        try {
            r = new RV("Noah Gonche2", new GregorianCalendar(2017, 11, 11), 5, 5, 30);
        }
        catch(Exception e) {
            ;
        }
        assertEquals(r, null);
    }

    @Test
    public void testRVConstructorPastDate() {
        Site r = null;
        try {
            r = new RV("Noah Goncher", new GregorianCalendar(2017, 10, 11), 5, 5, 30);
        }
        catch(Exception e) {
            ;
        }
        assertEquals(r, null);
    }

    @Test
    public void testRVConstructorNotLeapYear() {
        Site r = null;
        try {
            r = new RV("Noah Goncher", new GregorianCalendar(2021, 2, 29), 5, 5, 30);
        }
        catch(Exception e) {
            ;
        }
        assertEquals(r, null);
    }

    @Test
    public void testRVConstructorMaxYear() {
        Site r = null;
        try {
            r = new RV("Noah Goncher", new GregorianCalendar(2038, 1, 1), 5, 5, 30);
        }
        catch(Exception e) {
            ;
        }
        assertEquals(r, null);
    }

    @Test
    public void testRVConstructorMaxDay() {
        Site r = null;
        try {
            r = new RV("Noah Goncher", new GregorianCalendar(2017, 12, 32), 5, 5, 30);
        }
        catch(Exception e) {
            ;
        }
        assertEquals(r, null);
    }

    @Test //checks another month for its max day, expected to fail.
    public void testRVOtherMaxDay(){
        Site r = null;
        try{
            r = new RV("Noah Goncher", new GregorianCalendar(2019, 4, 31), 5, 5, 5);
        }
        catch(Exception e){

        }
        assertEquals(r, null);
    }

    @Test //checks another month for its max day, expected to fail.
    public void testRVAnotherMaxDay(){
        Site r = null;
        try{
            r = new RV("Noah Goncher", new GregorianCalendar(2020, 3, 32), 5, 5, 5);
        }
        catch(Exception e){

        }
        assertEquals(r, null);
    }

    @Test
    public void testRVConstructorMaxMonth() {
        Site r = null;
        try {
            r = new RV("Noah Goncher", new GregorianCalendar(2017, 13, 11), 5, 5, 30);
        }
        catch(Exception e) {
            ;
        }
        assertEquals(r, null);
    }
    @Test
    public void testRVConstructorZeroDay() {
        Site r = null;
        try {
            r = new RV("Noah Goncher", new GregorianCalendar(2017, 10, 0), 5, 5, 30);
        }
        catch(Exception e) {
            ;
        }
        assertEquals(r, null);
    }

    @Test
    public void testRVAllYears() {
        RV r = null;

        for(int i = 1; i < 20; i++) {
            for(int j = 1; j < 13; j++) {
                for(int k = 1; k <= CampingCalc.getMaxDays(j, i + 2017); k++) {
                    try {
                        r = new RV("Noah Goncher", new GregorianCalendar(i + 2017, j, k), 5, 5, 30);
                    }
                    catch(Exception e) {
                        ;
                    }
                    assertEquals(r.occupant, "Noah Goncher");
                    assertEquals(r.formatCalendar(r.reservDate), j + "/" + k + "/" + (i+2017));
                    assertEquals(r.duration, 5);
                    assertEquals(r.siteNum, 5);
                    assertEquals(r.getPower(), 30);
                }
            }
        }
    }

    @Test
    public void testRVStartDateEndDateConstructor(){
        RV r = null;
        try{
            r = new RV("Noah Goncher", "12/10/2017", "12/14/2017", 5, 30);

        }
        catch(Exception e){
            ;
        }
        assertEquals(r.occupant, "Noah Goncher");
        assertEquals(r.reservDate, new GregorianCalendar(2017, 12, 10));
        assertEquals(r.duration, 5);
        assertEquals(r.siteNum, 5);
        assertEquals(r.getPower(), 30);
    }

    @Test
    public void testRVIncorrectReservation(){
        Site r = null;
        try{
            r = new RV("Noah Goncher", "12/11/2017", "12/10/2017", 5, 30);
        }
        catch(Exception e){
            ;
        }
        assertEquals(r, null);
    }

    @Test
    public void testGetAndSetPower(){
        RV r = new RV();

        r.setPower(30);

        assertTrue(r.getPower() == 30);
    }

    @Test(expected = IllegalPowerException.class)
    public void testCheckPower(){
        RV r = new RV();

        r.setPower(33);

        r.checkPower(r.getPower());
    }

    @Test(expected = IllegalPowerException.class)
    public void testCheckPower2(){
        RV r = new RV();

        r.setPower(41);

        r.checkPower(r.getPower());
    }

    @Test(expected = IllegalPowerException.class)
    public void testCheckPower3(){
        RV r = new RV();

        r.setPower(52);

        r.checkPower(r.getPower());
    }

    @Test
    public void testRVGetAndSetCost(){
        RV r = new RV();

        r.duration = 5;

        r.setCost();

        assertTrue(r.getCost() == 150.0);
    }

    @Test
    public void testRVGetAndSetIndex(){
        Site r = null;

        r = new RV();

        r.setIndex(4);

        assertTrue(r.getIndex() == 4);
        assertFalse(r.getIndex() == 0);
    }

    @Test
    public void testRVEquals(){
        Site r = null;
        RV r2 = null;
        Site r3 = null;
        RV r4 = null;

        try{
            r = new RV("Noah Goncher", new GregorianCalendar(2017, 11, 11), 5, 5, 30);
            r2 = new RV("Noah Goncher", new GregorianCalendar(2017, 11, 11), 5, 5, 30);

            r3 = new RV("Noah Goncher", "12/11/2017", "12/14/2017", 5, 40);
            r4 = new RV("Noah Goncher", "12/11/2017", "12/14/2017", 5, 40);
        }
        catch(Exception e){

        }

        assertTrue(r2.equals(r));
        assertTrue(r4.equals(r3));
        assertFalse(r3.equals(r));
    }
        //Region: RV()
        /*******************************************************************
         * Tests default RV constructor.
         ******************************************************************/
        @Test
        public void testDefaultRV() {
            try {
                new RV();
            } catch (Exception e) {
                fail();
            }
        }
        //EndRegion: RV()

        //Region: RV(Name, ReserveDate, duration, site, power). Name
        /*******************************************************************
         * Tests GregCalendar RV constructor with a name with no space.
         ******************************************************************/
        @Test
        public void testGregCalRVBadNameNoSpace() {
            RV r = null;
            try {
                r = new RV("e",
                        new GregorianCalendar(2017, 11, 11),
                        5, 5, 30);
            } catch (Exception e) {
                ;
            }
            assertEquals(r, null);
        }

        /*******************************************************************
         * Tests GregCalendar RV constructor with a name with 2 spaces.
         ******************************************************************/
        @Test
        public void testGregCalRVBadName2Spaces() {
            RV r = null;
            try {
                r = new RV("e e e",
                        new GregorianCalendar(2017, 11, 11),
                        5, 5, 30);
            } catch (Exception e) {
                ;
            }
            assertEquals(r, null);
        }

        /*******************************************************************
         * Tests GregCalendar RV constructor with a name with space at
         * beginning.
         ******************************************************************/
        @Test
        public void testGregCalRVBadNameStartingSpace() {
            RV r = null;
            try {
                r = new RV(" ee",
                        new GregorianCalendar(2017, 11, 11),
                        5, 5, 30);
            } catch (Exception e) {
                ;
            }
            assertEquals(r, null);
        }

        /*******************************************************************
         * Tests GregCalendar RV constructor with a name with space at
         * beginning.
         ******************************************************************/
        @Test
        public void testGregCalRVBadNameEndingSpace() {
            RV r = null;
            try {
                r = new RV("ee ",
                        new GregorianCalendar(2017, 11, 11),
                        5, 5, 30);
            } catch (Exception e) {
                ;
            }
            assertEquals(r, null);
        }

        /*******************************************************************
         * Tests GregCalendar RV constructor with a name with numbers.
         ******************************************************************/
        @Test
        public void testGregCalRVBadNameNumbers() {
            RV r = null;
            try {
                r = new RV("3 3",
                        new GregorianCalendar(2017, 11, 11),
                        5, 5, 30);
            } catch (Exception e) {
                ;
            }
            assertEquals(r, null);
        }

        /*******************************************************************
         * Tests GregCalendar RV constructor with a name with numbers and
         * letters.
         ******************************************************************/
        @Test
        public void testGregCalRVBadNameNumbersAndLetters() {
            RV r = null;
            try {
                r = new RV("3e e3",
                        new GregorianCalendar(2017, 11, 11),
                        5, 5, 30);
            } catch (Exception e) {
                ;
            }
            assertEquals(r, null);
        }

        /*******************************************************************
         * Tests GregCalendar RV constructor with a name with default name.
         ******************************************************************/
        @Test
        public void testGregCalRV2SpacesBackToBack() {
            RV r = null;
            try {
                r = new RV("e  e",
                        new GregorianCalendar(2017, 11, 11),
                        5, 5, 30);
            } catch (Exception e) {
                ;
            }
            assertEquals(r, null);
        }

        /*******************************************************************
         * Tests GregCalendar RV constructor with a name with numbers.
         ******************************************************************/
        @Test
        public void testGregCalRVDefaultName() {
            RV r = null;
            try {
                r = new RV("Firstname Lastname",
                        new GregorianCalendar(2017, 11, 11),
                        5, 5, 30);
            } catch (Exception e) {
                ;
            }
            assertEquals(r, null);
        }

//        /*******************************************************************
//         * Tests GregCalendar RV constructor with a name with numbers.
//         ******************************************************************/
//        @Test
//        public void testGregCalRVGoodName() {
//            RV r = null;
//            try {
//                r = new RV("Matt Kinoodle",
//                        new GregorianCalendar(2017, 11, 11),
//                        5, 5, 30);
//            } catch (Exception e) {
//                ;
//            }
//            assertEquals(r.occupant, "Matt Kinoodle");
//            assertEquals(r.formatCalendar(r.reservDate), "11/11/2017");
//            assertEquals(r.duration, 5);
//            assertEquals(r.siteNum, 5);
//            assertEquals(r.getPower(), 30);
//        }
        //EndRegion: RV(Name, ReserveDate, duration, site, power). Name

        //Region: RV(Name, ReserveDate, duration, site, power). ReserveDate
//        /*******************************************************************
//         * Tests GregCalendar RV constructor with a ReserveDate with year
//         * 2017 and month before November.
//         ******************************************************************/
//        @Test
//        public void testGregCalRVGregCalEarlyMonth() {
//            RV r = null;
//            try {
//                r = new RV("Matt Kinoodle",
//                        new GregorianCalendar(2017, 10, 11),
//                        5, 5, 30);
//            } catch (Exception e) {
//                ;
//            }
//            assertEquals(r, null);
//        }

        /*******************************************************************
         * Tests GregCalendar RV constructor with a ReserveDate with year
         * 2017 and month after December.
         ******************************************************************/
        @Test
        public void testGregCalRVGregCal2017LateMonth() {
            RV r = null;
            try {
                r = new RV("Matt Kinoodle",
                        new GregorianCalendar(2017, 13, 11),
                        5, 5, 30);
            } catch (Exception e) {
                ;
            }
            assertEquals(r, null);
        }

        /*******************************************************************
         * Tests GregCalendar RV constructor with a ReserveDate with year
         * 2017, month December, and day past max days.
         ******************************************************************/
        @Test
        public void testGregCalRVGregCal2017ExcessDays() {
            RV r = null;
            try {
                r = new RV("Matt Kinoodle",
                        new GregorianCalendar(2017, 12, 32),
                        5, 5, 30);
            } catch (Exception e) {
                ;
            }
            assertEquals(r, null);
        }

        /*******************************************************************
         * Tests GregCalendar RV constructor with a ReserveDate with year
         * 2017, month December, and negative day.
         ******************************************************************/
        @Test
        public void testGregCalRVGregCal2017NegativeDays() {
            RV r = null;
            try {
                r = new RV("Matt Kinoodle",
                        new GregorianCalendar(2017, 12, -31),
                        5, 5, 30);
            } catch (Exception e) {
                ;
            }
            assertEquals(r, null);
        }

        /*******************************************************************
         * Tests GregCalendar RV constructor with a ReserveDate with year
         * 2017 and negative month.
         ******************************************************************/
        @Test
        public void testGregCalRVGregCal2017NegativeMonth() {
            RV r = null;
            try {
                r = new RV("Matt Kinoodle",
                        new GregorianCalendar(2017, -12, 31),
                        5, 5, 30);
            } catch (Exception e) {
                ;
            }
            assertEquals(r, null);
        }

        /*******************************************************************
         * Tests GregCalendar RV constructor with a ReserveDate with negative
         * year.
         ******************************************************************/
        @Test
        public void testGregCalRVGregCal2017NegativeYear() {
            RV r = null;
            try {
                r = new RV("Matt Kinoodle",
                        new GregorianCalendar(-2017, 12, 31),
                        5, 5, 30);
            } catch (Exception e) {
                ;
            }
            assertEquals(r, null);
        }

        /*******************************************************************
         * Tests GregCalendar RV constructor with a ReserveDate with negative
         * year, month, day.
         ******************************************************************/
        @Test
        public void testGregCalRVGregCal2017NegativeYearMonthDay() {
            RV r = null;
            try {
                r = new RV("Matt Kinoodle",
                        new GregorianCalendar(-2017, -12, -31),
                        5, 5, 30);
            } catch (Exception e) {
                ;
            }
            assertEquals(r, null);
        }

        /*******************************************************************
         * Tests GregCalendar RV constructor with a default GregCal
         ******************************************************************/
        @Test
        public void testGregCalRVDefaultGregCal() {
            RV r = null;
            try {
                r = new RV("Matt Kinoodle",
                        new GregorianCalendar(),
                        5, 5, 30);
            } catch (Exception e) {
                ;
            }
            assertEquals(r, null);
        }

        /*******************************************************************
         * Tests GregCalendar RV constructor with a difference locale GregCal
         ******************************************************************/
        @Test
        public void testGregCalRVLocaleGregCal() {
            RV r = null;
            try {
                r = new RV("Matt Kinoodle",
                        new GregorianCalendar(new Locale("French", "France")),
                        5, 5, 30);
            } catch (Exception e) {
                ;
            }
            assertEquals(r, null);
        }

//        /*******************************************************************
//         * Tests GregCalendar RV constructor with a hour, minute, and second.
//         ******************************************************************/
//        @Test
//        public void testGregCalRVHourMinSecGregCal() {
//            RV r = null;
//            try {
//                r = new RV("Matt Kinoodle",
//                        new GregorianCalendar(2017, 11, 11, 11, 11, 11),
//                        5, 5, 30);
//            } catch (Exception e) {
//                ;
//            }
//            assertEquals(r.occupant, "Matt Kinoodle");
//            assertEquals(r.formatCalendar(r.reservDate), "11/11/2017");
//            assertEquals(r.duration, 5);
//            assertEquals(r.siteNum, 5);
//            assertEquals(r.getPower(), 30);
//        }

//        /*******************************************************************
//         * Tests GregCalendar RV constructor with a negative hour.
//         ******************************************************************/
//        @Test
//        public void testGregCalRVNegHourGregCal() {
//            RV r = null;
//            try {
//                r = new RV("Matt Kinoodle",
//                        new GregorianCalendar(2017, 11, 11, -11, 11, 11),
//                        5, 5, 30);
//            } catch (Exception e) {
//                ;
//            }
//            assertEquals(r.occupant, "Matt Kinoodle");
//            assertEquals(r.formatCalendar(r.reservDate), "11/11/2017");
//            assertEquals(r.duration, 5);
//            assertEquals(r.siteNum, 5);
//            assertEquals(r.getPower(), 30);
//        }

        /*******************************************************************
         * Tests GregCalendar RV constructor with a negative minute.
         ******************************************************************/
//        @Test
//        public void testGregCalRVNegMinGregCal() {
//            RV r = null;
//            try {
//                r = new RV("Matt Kinoodle",
//                        new GregorianCalendar(2017, 11, 11, 11, -11, 11),
//                        5, 5, 30);
//            } catch (Exception e) {
//                ;
//            }
//            assertEquals(r.occupant, "Matt Kinoodle");
//            assertEquals(r.formatCalendar(r.reservDate), "11/11/2017");
//            assertEquals(r.duration, 5);
//            assertEquals(r.siteNum, 5);
//            assertEquals(r.getPower(), 30);
//        }

        /*******************************************************************
         * Tests GregCalendar RV constructor with a negative second.
         ******************************************************************/
        @Test
        public void testGregCalRVNegSecGregCal() {
            RV r = null;
            try {
                r = new RV("Matt Kinoodle",
                        new GregorianCalendar(2017, 11, 11, 11, 11, -11),
                        5, 5, 30);
            } catch (Exception e) {
                ;
            }
            assertEquals(r.occupant, "Matt Kinoodle");
            assertEquals(r.formatCalendar(r.reservDate), "11/11/2017");
            assertEquals(r.duration, 5);
            assertEquals(r.siteNum, 5);
            assertEquals(r.getPower(), 30);
        }

        /*******************************************************************
         * Tests GregCalendar RV constructor with hour past 24
         ******************************************************************/
        @Test
        public void testGregCalRVExcessHourGregCal() {
            RV r = null;
            try {
                r = new RV("Matt Kinoodle",
                        new GregorianCalendar(2017, 11, 11, 25, 11, 11),
                        5, 5, 30);
            } catch (Exception e) {
                ;
            }
            assertEquals(r.occupant, "Matt Kinoodle");
            assertEquals(r.formatCalendar(r.reservDate), "11/11/2017");
            assertEquals(r.duration, 5);
            assertEquals(r.siteNum, 5);
            assertEquals(r.getPower(), 30);
        }

        /*******************************************************************
         * Tests GregCalendar RV constructor with minute past 60.
         ******************************************************************/
        @Test
        public void testGregCalRVExcessMinGregCal() {
            RV r = null;
            try {
                r = new RV("Matt Kinoodle",
                        new GregorianCalendar(2017, 11, 11, 11, 61, 11),
                        5, 5, 30);
            } catch (Exception e) {
                ;
            }
            assertEquals(r.occupant, "Matt Kinoodle");
            assertEquals(r.formatCalendar(r.reservDate), "12/11/2017");
            assertEquals(r.duration, 5);
            assertEquals(r.siteNum, 5);
            assertEquals(r.getPower(), 30);
        }

        /*******************************************************************
         * Tests GregCalendar RV constructor with second past 60
         ******************************************************************/
        @Test
        public void testGregCalRVExcessSecGregCal() {
            RV r = null;
            try {
                r = new RV("Matt Kinoodle",
                        new GregorianCalendar(2017, 11, 11, 11, 11, 61),
                        5, 5, 30);
            } catch (Exception e) {
                ;
            }
            assertEquals(r.occupant, "Matt Kinoodle");
            assertEquals(r.formatCalendar(r.reservDate), "12/11/2017");
            assertEquals(r.duration, 5);
            assertEquals(r.siteNum, 5);
            assertEquals(r.getPower(), 30);
        }

//        /*******************************************************************
//         * Tests GregCalendar RV constructor with a ReserveDate of every
//         * possible year, month, and day.
//         ******************************************************************/
//        @Test
//        public void testGregCalRVGregCalTestAllNormalYears() {
//            RV r = null;
//
//            for (int i = 1; i < 20; i++)
//                for (int j = 1; j < 13; j++)
//                    for (int k = 1;
//                         k <= CampingCalc.getMaxDays(j, i + 2017);
//                         k++) {
//                        try {
//                            r = new RV("Matt Kinoodle",
//                                    new GregorianCalendar(i + 2017, j, k),
//                                    5, 5, 30);
//                        } catch (Exception e) {
//                            ;
//                        }
//                        assertEquals(r.occupant, "Matt Kinoodle");
//                        assertEquals(r.formatCalendar(r.reservDate),
//                                j + "/" + k + "/" + (i + 2017));
//                        assertEquals(r.duration, 5);
//                        assertEquals(r.siteNum, 5);
//                        assertEquals(r.getPower(), 30);
//                    }
//        }
        //EndRegion: RV(Name, ReserveDate, duration, site, power). ReserveDate

        //Region: RV(Name, ReserveDate, duration, site, power). duration
        /*******************************************************************
         * Tests GregCalendar RV constructor duration less than 0.
         ******************************************************************/
        @Test
        public void testGregCalRVNegDuration() {
            RV r = null;
            try {
                r = new RV("Matt Kinoodle",
                        new GregorianCalendar(11, 11, 11),
                        -5, 5, 30);
            } catch (Exception e) {
                ;
            }
            assertEquals(r, null);
        }

        /*******************************************************************
         * Tests GregCalendar RV constructor duration equal to 0.
         ******************************************************************/
        @Test
        public void testGregCalRV0Duration() {
            RV r = null;
            try {
                r = new RV("Matt Kinoodle",
                        new GregorianCalendar(11, 11, 11),
                        0, 5, 30);
            } catch (Exception e) {
                ;
            }
            assertEquals(r, null);
        }

        /*******************************************************************
         * Tests GregCalendar RV constructor duration greater than 366
         ******************************************************************/
        @Test
        public void testGregCalRVExcessDuration() {
            RV r = null;
            try {
                r = new RV("Matt Kinoodle",
                        new GregorianCalendar(11, 11, 11),
                        367, 5, 30);
            } catch (Exception e) {
                ;
            }
            assertEquals(r, null);
        }

        /*******************************************************************
         * Tests GregCalendar RV constructor duration with Integer.MAX_VALUE.
         * What if user does Int.MAX_VALUE + Int.MAX_VALUE + 3 = 1.
         ******************************************************************/
        @Test
        public void testGregCalRVMaxValDuration() {
            RV r = null;
            try {
                r = new RV("Matt Kinoodle",
                        new GregorianCalendar(2017, 11, 11),
                        Integer.MAX_VALUE, 5, 30);
            } catch (Exception e) {
                ;
            }
            assertEquals(r, null);
        }

//        /*******************************************************************
//         * Tests GregCalendar RV constructor duration for all values between
//         * 1 and 366.
//         ******************************************************************/
//        @Test
//        public void testGregCalRVAllViableDurations() {
//            for (int i = 1; i < 367; i++) {
//                RV r = null;
//                try {
//                    r = new RV("Matt Kinoodle",
//                            new GregorianCalendar(2017, 11, 11),
//                            i, 5, 30);
//                } catch (Exception e) {
//                    ;
//                }
//                assertEquals(r.occupant, "Matt Kinoodle");
//                assertEquals(r.formatCalendar(r.reservDate), "11/11/2017");
//                assertEquals(r.duration, i);
//                assertEquals(r.siteNum, 5);
//                assertEquals(r.getPower(), 30);
//            }
//        }
        //EndRegion: RV(Name, ReserveDate, duration, site, power). duration

        //Region: RV(Name, ReserveDate, duration, site, power). site
        /*******************************************************************
         * Tests GregCalendar RV constructor site number equal to 0.
         ******************************************************************/
        @Test
        public void testGregCalRVSiteNumber0() {
            RV r = null;
            try {
                r = new RV("Matt Kinoodle",
                        new GregorianCalendar(11, 11, 11),
                        5, 0, 30);
            } catch (Exception e) {
                ;
            }
            assertEquals(r, null);
        }

        /*******************************************************************
         * Tests GregCalendar RV constructor site number greater than 5
         ******************************************************************/
        @Test
        public void testGregCalRVExcessSiteNumber() {
            RV r = null;
            try {
                r = new RV("Matt Kinoodle",
                        new GregorianCalendar(11, 11, 11),
                        5, 6, 30);
            } catch (Exception e) {
                ;
            }
            assertEquals(r, null);
        }

        /*******************************************************************
         * Tests GregCalendar RV constructor negative site number.
         ******************************************************************/
        @Test
        public void testGregCalRVNegSiteNumber() {
            RV r = null;
            try {
                r = new RV("Matt Kinoodle",
                        new GregorianCalendar(11, 11, 11),
                        5, -1, 30);
            } catch (Exception e) {
                ;
            }
            assertEquals(r, null);
        }

        /*******************************************************************
         * Tests GregCalendar RV constructor site number with
         * Integer.MAX_VALUE.
         ******************************************************************/
        @Test
        public void testGregCalRVMaxValSiteNumber() {
            RV r = null;
            try {
                r = new RV("Matt Kinoodle",
                        new GregorianCalendar(2017, 11, 11),
                        5, Integer.MAX_VALUE, 30);
            } catch (Exception e) {
                ;
            }
            assertEquals(r, null);
        }

//        /*******************************************************************
//         * Tests GregCalendar RV constructor site number for all values
//         * between 1 and 5
//         ******************************************************************/
//        @Test
//        public void testGregCalRVAllViableSiteNumbers() {
//            for (int i = 1; i < 6; i++) {
//                RV r = null;
//                try {
//                    r = new RV("Matt Kinoodle",
//                            new GregorianCalendar(2017, 11, 11),
//                            5, i, 30);
//                } catch (Exception e) {
//                    ;
//                }
//                assertEquals(r.occupant, "Matt Kinoodle");
//                assertEquals(r.formatCalendar(r.reservDate), "11/11/2017");
//                assertEquals(r.duration, 5);
//                assertEquals(r.siteNum, i);
//                assertEquals(r.getPower(), 30);
//            }
//        }
        //EndRegion: RV(Name, ReserveDate, duration, site, power). site

        //Region: RV(Name, ReserveDate, duration, site, power). power
        /*******************************************************************
         * Tests GregCalendar RV constructor power == 30.
         ******************************************************************/
        @Test
        public void testGregCalRVPower30() {
            RV r = null;
            try {
                r = new RV("Matt Kinoodle",
                        new GregorianCalendar(11, 11, 11),
                        5, 5, 30);
            } catch (Exception e) {
                ;
            }
            assertEquals(r, null);
        }

        /*******************************************************************
         * Tests GregCalendar RV constructor power == 40.
         ******************************************************************/
        @Test
        public void testGregCalRVPower40() {
            RV r = null;
            try {
                r = new RV("Matt Kinoodle",
                        new GregorianCalendar(11, 11, 11),
                        5, 5, 40);
            } catch (Exception e) {
                ;
            }
            assertEquals(r, null);
        }

        /*******************************************************************
         * Tests GregCalendar RV constructor power == 50.
         ******************************************************************/
        @Test
        public void testGregCalRVPower50() {
            RV r = null;
            try {
                r = new RV("Matt Kinoodle",
                        new GregorianCalendar(11, 11, 11),
                        5, -1, 50);
            } catch (Exception e) {
                ;
            }
            assertEquals(r, null);
        }

        /*******************************************************************
         * Tests GregCalendar RV constructor power < 30.
         ******************************************************************/
        @Test
        public void testGregCalRVPowerUnder30() {
            RV r = null;
            try {
                r = new RV("Matt Kinoodle",
                        new GregorianCalendar(2017, 11, 11),
                        5, 5, 25);
            } catch (Exception e) {
                ;
            }
            assertEquals(r, null);
        }

        /*******************************************************************
         * Tests GregCalendar RV constructor power > 50.
         ******************************************************************/
        @Test
        public void testGregCalRVPowerOver50() {
            RV r = null;
            try {
                r = new RV("Matt Kinoodle",
                        new GregorianCalendar(2017, 11, 11),
                        5, 5, 55);
            } catch (Exception e) {
                ;
            }
            assertEquals(r, null);
        }

        /*******************************************************************
         * Tests GregCalendar RV constructor negative power.
         ******************************************************************/
        @Test
        public void testGregCalRVNegPower() {
            RV r = null;
            try {
                r = new RV("Matt Kinoodle",
                        new GregorianCalendar(2017, 11, 11),
                        5, 5, -5);
            } catch (Exception e) {
                ;
            }
            assertEquals(r, null);

        }

        //EndRegion: RV(Name, ReserveDate, duration, site, power). power

        //Region: RV(Name, startDate, endDate, site, power). startdate
        /*******************************************************************
         * Tests String RV constructor startDate with no slashes.
         ******************************************************************/
        @Test
        public void testStringRVStartDateNoSlashes() {
            RV r = null;
            try {
                r = new RV("Matt Kinoodle",
                        "1111", "11/11/2017",
                        5, 30);
            } catch (Exception e) {
                ;
            }
            assertEquals(r, null);

        }

        /*******************************************************************
         * Tests String RV constructor startDate with 1 slash.
         ******************************************************************/
        @Test
        public void testStringRVStartDate1Slash() {
            RV r = null;
            try {
                r = new RV("Matt Kinoodle",
                        "/1111", "11/11/2017",
                        5, 30);
            } catch (Exception e) {
                ;
            }
            assertEquals(r, null);

        }

        /*******************************************************************
         * Tests String RV constructor startDate with 2 slashes.
         ******************************************************************/
        @Test
        public void testStringRVStartDate2Slash() {
            RV r = null;
            try {
                r = new RV("Matt Kinoodle",
                        "/1111/", "11/11/2017",
                        5, 30);
            } catch (Exception e) {
                ;
            }
            assertEquals(r, null);
        }

        /*******************************************************************
         * Tests String RV constructor startDate with 3 slashes.
         ******************************************************************/
        @Test
        public void testStringRVStartDate3Slash() {
            RV r = null;
            try {
                r = new RV("Matt Kinoodle",
                        "/1111/", "11/11/2017",
                        5, 30);
            } catch (Exception e) {
                ;
            }
            assertEquals(r, null);

        }

        /*******************************************************************
         * Tests String RV constructor startDate with day less than 1.
         ******************************************************************/
        @Test
        public void testStringRVStartDate0Days() {
            RV r = null;
            try {
                r = new RV("Matt Kinoodle",
                        "11/0/2017", "11/11/2017",
                        5, 30);
            } catch (Exception e) {
                ;
            }
            assertEquals(r, null);

        }

        /*******************************************************************
         * Tests String RV constructor startDate with month less than 1.
         ******************************************************************/
        @Test
        public void testStringRVStartDate0Month() {
            RV r = null;
            try {
                r = new RV("Matt Kinoodle",
                        "0/11/2017", "11/11/2017",
                        5, 30);
            } catch (Exception e) {
                ;
            }
            assertEquals(r, null);

        }

        /*******************************************************************
         * Tests String RV constructor startDate with year less than 2017.
         ******************************************************************/
        @Test
        public void testStringRVStartDate2016Year() {
            RV r = null;
            try {
                r = new RV("Matt Kinoodle",
                        "11/11/2016", "11/11/2017",
                        5, 30);
            } catch (Exception e) {
                ;
            }
            assertEquals(r, null);

        }

        /*******************************************************************
         * Tests String RV constructor startDate with October 31, 2017.
         ******************************************************************/
        @Test
        public void testStringRVStartDateDayBeforeStart() {
            RV r = null;
            try {
                r = new RV("Matt Kinoodle",
                        "10/31/2016", "11/11/2017",
                        5, 30);
            } catch (Exception e) {
                ;
            }
            assertEquals(r, null);

        }

        /*******************************************************************
         * Tests String RV constructor startDate with letters instead of
         * numbers for month.
         ******************************************************************/
        @Test
        public void testStringRVStartDateMonthLetters() {
            RV r = null;
            try {
                r = new RV("Matt Kinoodle",
                        "aa/11/2017", "11/11/2017",
                        5, 30);
            } catch (Exception e) {
                ;
            }
            assertEquals(r, null);

        }

        /*******************************************************************
         * Tests String RV constructor startDate with letters instead of
         * numbers for day.
         ******************************************************************/
        @Test
        public void testStringRVStartDateDayLetters() {
            RV r = null;
            try {
                r = new RV("Matt Kinoodle",
                        "11/aa/2017", "11/11/2017",
                        5, 30);
            } catch (Exception e) {
                ;
            }
            assertEquals(r, null);

        }

        /*******************************************************************
         * Tests String RV constructor startDate with letters instead of
         * numbers for year.
         ******************************************************************/
        @Test
        public void testStringRVStartDateYearLetters() {
            RV r = null;
            try {
                r = new RV("Matt Kinoodle",
                        "11/11/aaaa", "11/11/2017",
                        5, 30);
            } catch (Exception e) {
                ;
            }
            assertEquals(r, null);

        }

        /*******************************************************************
         * Tests String RV constructor startDate with 3 digits for month
         * instead of 2.
         ******************************************************************/
        @Test
        public void testStringRVStartDate3Month() {
            RV r = null;
            try {
                r = new RV("Matt Kinoodle",
                        "111/11/2017", "11/11/2017",
                        5, 30);
            } catch (Exception e) {
                ;
            }
            assertEquals(r, null);

        }

        /*******************************************************************
         * Tests String RV constructor startDate with 3 digits for day
         * instead of 2.
         ******************************************************************/
        @Test
        public void testStringRVStartDate3Day() {
            RV r = null;
            try {
                r = new RV("Matt Kinoodle",
                        "11/111/2017", "11/11/2017",
                        5, 30);
            } catch (Exception e) {
                ;
            }
            assertEquals(r, null);

        }

        /*******************************************************************
         * Tests String RV constructor startDate with 5 digits for year
         * instead of 4.
         ******************************************************************/
        @Test
        public void testStringRVStartDate5Year() {
            RV r = null;
            try {
                r = new RV("Matt Kinoodle",
                        "11/11/20171", "11/11/2017",
                        5, 30);
            } catch (Exception e) {
                ;
            }
            assertEquals(r, null);

        }

        /*******************************************************************
         * Tests String RV constructor startDate with random chars instead
         * of numbers.
         ******************************************************************/
        @Test
        public void testStringRVStartDateRandomChars() {
            RV r = null;
            try {
                r = new RV("Matt Kinoodle",
                        "##/@&/(!*@", "11/11/2017",
                        5, 30);
            } catch (Exception e) {
                ;
            }
            assertEquals(r, null);

        }

        /*******************************************************************
         * Tests String RV constructor startDate with a space in the string.
         ******************************************************************/
        @Test
        public void testStringRVStartDateSpace() {
            RV r = null;
            try {
                r = new RV("Matt Kinoodle",
                        "1 1/11/2017", "11/11/2017",
                        5, 30);
            } catch (Exception e) {
                ;
            }
            assertEquals(r, null);

        }

        /*******************************************************************
         * Tests String RV constructor startDate for 1 day after the end of
         * every month.
         ******************************************************************/
        @Test
        public void testStringRVStartDateMaxDays() {
            RV r = null;
            for (int i = 1; i < 20; i++)
                for (int j = 1; j < 13; j++) {
                    try {
                        r = new RV("Matt Kinoodle",
                                ("" + (j)
                                        + "/" + (CampingCalc.getMaxDays(j, i + 2017) + 1)
                                        + "/" + (i + 2017)),
                                "11/11/2017", 5, 30);
                    } catch (Exception e) {
                        ;
                    }
                    assertEquals(r, null);
                }

        }

        /*******************************************************************
         * Tests String RV constructor startDate for 1 day after the end of
         * every month.
         ******************************************************************/
        @Test
        public void testStringRVStartDateEveryDay() {
            RV r = null;
            for (int i = 1; i < 20; i++)
                for (int j = 1; j < 13; j++)
                    for (int k = 1;
                         k <= CampingCalc.getMaxDays(j, i + 2017);
                         k++) {
                        try {
                            r = new RV("Matt Kinoodle",
                                    ("" + j + "/" + k + "/" + (i + 2017)),
                                    ("" + j + "/" + k + "/" + (i + 2017)), 5, 30);
                        } catch (Exception e) {
                            ;
                        }
                        assertEquals(r.occupant, "Matt Kinoodle");
                        assertEquals(r.formatCalendar(r.reservDate),
                                j + "/" + k + "/" + (i + 2017));
                        assertEquals(r.duration, 1);
                        assertEquals(r.siteNum, 5);
                        assertEquals(r.getPower(), 30);
                    }

        }
        //EndRegion: RV(Name, startDate, endDate, site, power). startDate

        //Region: RV(Name, startDate, endDate, site, power). endDate
        /*******************************************************************
         * Tests String RV constructor endDate before start date.
         ******************************************************************/
        @Test
        public void testStringRVEndDateBeforeStartDate() {
            RV r = null;
            try {
                r = new RV("Matt Kinoodle",
                        "11/11/2017", "11/10/2017",
                        5, 30);
            } catch (Exception e) {
                ;
            }
            assertEquals(r, null);
        }

        /*******************************************************************
         * Tests String RV constructor endDate 1 year after startDate.
         ******************************************************************/
        @Test
        public void testStringRVEndDateYearAfterStart() {
            RV r = null;
            try {
                r = new RV("Matt Kinoodle",
                        "11/11/2017", "11/12/2018",
                        5, 30);
            } catch (Exception e) {
                ;
            }
            assertEquals(r, null);
        }

        /*******************************************************************
         * Tests String RV constructor endDate after 12/31/2036.
         ******************************************************************/
        @Test
        public void testStringRVEndDateAfterMax() {
            RV r = null;
            try {
                r = new RV("Matt Kinoodle",
                        "11/11/2036", "1/1/2037",
                        5, 30);
            } catch (Exception e) {
                ;
            }
            assertEquals(r, null);
        }

        /*******************************************************************
         * Tests String RV constructor endDate same day as startDate.
         ******************************************************************/
        @Test
        public void testStringRVEndDateSameDayAsStartDate() {
            RV r = null;
            try {
                r = new RV("Matt Kinoodle",
                        "11/11/2017", "11/11/2017",
                        5, 30);
            } catch (Exception e) {
                ;
            }
            assertEquals(r.occupant, "Matt Kinoodle");
            assertEquals(r.formatCalendar(r.reservDate),
                    "11/11/2017");
            assertEquals(r.duration, 1);
            assertEquals(r.siteNum, 5);
            assertEquals(r.getPower(), 30);
        }

        /*******************************************************************
         * Tests String RV constructor endDate 365 days after startDate.
         ******************************************************************/
        @Test
        public void testStringRVEndDate1YearAfterStartDate() {
            RV r = null;
            try {
                r = new RV("Matt Kinoodle",
                        "11/11/2017", "11/11/2018",
                        5, 30);
            } catch (Exception e) {
                ;
            }
            assertEquals(r.occupant, "Matt Kinoodle");
            assertEquals(r.formatCalendar(r.reservDate),
                    "11/11/2017");
            assertEquals(r.duration, 366);
            assertEquals(r.siteNum, 5);
            assertEquals(r.getPower(), 30);
        }
        //EndRegion: RV(Name, startDate, endDate, site, power). endDate

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
        GregorianCalendar g3 = new GregorianCalendar(2017, 11, 25);
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
        GregorianCalendar g3 = new GregorianCalendar(2017, 11, 25);
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
        assertTrue(s.getSiteListSize() == 4);
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
        assertEquals(s.getSiteListSize(), 3);
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

        assertTrue(s.getTakenDaySize() == 469);
    }

    //Test the undo method
    //Checks there are items in the List
    @Test
    public void testUndo() {
        SiteModel s = new SiteModel();
        GregorianCalendar g1 = new GregorianCalendar(2022, 5, 6);
        GregorianCalendar g2 = new GregorianCalendar(2018, 9, 4);
        GregorianCalendar g3 = new GregorianCalendar(2017, 11, 25);
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
        GregorianCalendar g3 = new GregorianCalendar(2017, 11, 25);
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
        GregorianCalendar g3 = new GregorianCalendar(2017, 11, 25);
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
        GregorianCalendar g3 = new GregorianCalendar(2017, 11, 25);
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
        GregorianCalendar g3 = new GregorianCalendar(2017, 11, 25);
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
        GregorianCalendar g3 = new GregorianCalendar(2017, 11, 25);
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

    //End of Region SiteModel.

}

