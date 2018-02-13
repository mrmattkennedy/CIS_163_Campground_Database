package campingPack;

import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.Assert.*;

public class DatabaseTestNoah {
	@Test
	public void testDefaultTentConstructor() {
		try {
			new Tent();
		}
		catch(Exception e) {
			fail();
		}
	}
	
	@Test
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
	
	@Test
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
	
	@Test
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
	
	@Test
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
	
	@Test
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
	
	@Test
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
	
	@Test
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
	
	@Test
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
	
	@Test
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
	
	@Test
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
	@Test
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
	
	@Test
	public void testAllYears() {
		Tent t = null;
		
		for(int i = 1; i < 20; i++) {
			for(int j = 1; j < 13; j++) {
				for(int k = 1; k <= CampingCalc.getMaxDays(j, i + 2017); k++) {
					try {
						t = new Tent("Noah Goncher", new GregorianCalendar(i + 2017, j, k), 5, 5, 5);
					}
					catch(Exception e) {
						;
					}
					assertEquals(t.occupant, "Noah Goncher");
					assertEquals(t.formatCalendar(t.reservDate), j + "/" + k + "/" + (i+2017));
					assertEquals(t.duration, 5);
					assertEquals(t.siteNum, 5);
					assertEquals(t.getNumTenters(), 5);
				}
			}
		}
	}
	
	@Test
	public void testStartDateEndDateConstructor(){
		Tent t = null;
		try{
			t = new Tent("Noah Goncher", "12/10/2017", "12/14/2018", 5, 5);
		}
		catch(Exception e){
			;
		}
		assertEquals(t.occupant, "Noah Goncher");
		assertEquals(t.reservDate, new GregorianCalendar(2017, 12, 11));
		assertEquals(t.duration, 3);
		assertEquals(t.siteNum, 5);
		assertEquals(t.getNumTenters(), 5);
	}

	@Test
	public void testIncorrectReservation(){
		Site t = null;
		try{
			t = new Tent("Noah Goncher", "12/11/2017", "12/11/2017", 5, 5);
		}
		catch(Exception e){
			;
		}
		assertEquals(t, null);
	}

	@Test
	public void testGetNumTenters(){
		Tent t = new Tent();

		t.setNumTenters(5);

		assertTrue(t.getNumTenters() == 5);
	}

	@Test
	public void testBadNumTents(){
		Tent t = new Tent();

		try {
			t.setNumTenters(0);
		}
		catch(IllegalTentsException e){
			;
		}

		assertEquals(t, null);

	}
}