package campingPack;

import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

import static org.junit.Assert.*;

public class DatabaseTestMatt {
	
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
	
	/*******************************************************************
	 * Tests GregCalendar RV constructor with a name with numbers.
	 ******************************************************************/
	@Test
	public void testGregCalRVGoodName() {
		RV r = null;
		try {
			r = new RV("Matt Kinoodle", 
					new GregorianCalendar(2017, 11, 11), 
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
	//EndRegion: RV(Name, ReserveDate, duration, site, power). Name
	
	//Region: RV(Name, ReserveDate, duration, site, power). ReserveDate
	/*******************************************************************
	 * Tests GregCalendar RV constructor with a ReserveDate with year
	 * 2017 and month before November.
	 ******************************************************************/
	@Test
	public void testGregCalRVGregCalEarlyMonth() {
		RV r = null;
		try {
			r = new RV("Matt Kinoodle", 
					new GregorianCalendar(2017, 10, 11), 
					5, 5, 30);
		} catch (Exception e) {
			;
		}
		assertEquals(r, null);
	}
	
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
	
	/*******************************************************************
	 * Tests GregCalendar RV constructor with a hour, minute, and second.
	 ******************************************************************/
	@Test
	public void testGregCalRVHourMinSecGregCal() {
		RV r = null;
		try {
			r = new RV("Matt Kinoodle", 
					new GregorianCalendar(2017, 11, 11, 11, 11, 11), 
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
	 * Tests GregCalendar RV constructor with a negative hour.
	 ******************************************************************/
	@Test
	public void testGregCalRVNegHourGregCal() {
		RV r = null;
		try {
			r = new RV("Matt Kinoodle", 
					new GregorianCalendar(2017, 11, 11, -11, 11, 11), 
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
	 * Tests GregCalendar RV constructor with a negative minute.
	 ******************************************************************/
	@Test
	public void testGregCalRVNegMinGregCal() {
		RV r = null;
		try {
			r = new RV("Matt Kinoodle", 
					new GregorianCalendar(2017, 11, 11, 11, -11, 11), 
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
		assertEquals(r.formatCalendar(r.reservDate), "11/11/2017");
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
		assertEquals(r.formatCalendar(r.reservDate), "11/11/2017");
		assertEquals(r.duration, 5);
		assertEquals(r.siteNum, 5);
		assertEquals(r.getPower(), 30);
	}
	
	/*******************************************************************
	 * Tests GregCalendar RV constructor with a ReserveDate of every
	 * possible year, month, and day.
	 ******************************************************************/
	@Test
	public void testGregCalRVGregCalTestAllNormalYears() {
		RV r = null;
		
		for (int i = 1; i < 20; i++)
			for (int j = 1; j < 13; j++)
				for (int k = 1; 
						k <= CampingCalc.getMaxDays(j, i + 2017); 
						k++) {
					try {
						r = new RV("Matt Kinoodle", 
								new GregorianCalendar(i + 2017, j, k), 
								5, 5, 30);
					} catch (Exception e) {
						;
					}
					assertEquals(r.occupant, "Matt Kinoodle");
					assertEquals(r.formatCalendar(r.reservDate), 
							j + "/" + k + "/" + (i + 2017));
					assertEquals(r.duration, 5);
					assertEquals(r.siteNum, 5);
					assertEquals(r.getPower(), 30);
				}
	}
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
	
	/*******************************************************************
	 * Tests GregCalendar RV constructor duration for all values between
	 * 1 and 366.
	 ******************************************************************/
	@Test
	public void testGregCalRVAllViableDurations() {
		for (int i = 1; i < 367; i++) {
			RV r = null;
			try {
				r = new RV("Matt Kinoodle", 
						new GregorianCalendar(2017, 11, 11), 
						i, 5, 30);
			} catch (Exception e) {
				;
			}
			assertEquals(r.occupant, "Matt Kinoodle");
			assertEquals(r.formatCalendar(r.reservDate), "11/11/2017");
			assertEquals(r.duration, i);
			assertEquals(r.siteNum, 5);
			assertEquals(r.getPower(), 30);
		}
	}
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
	
	/*******************************************************************
	 * Tests GregCalendar RV constructor site number for all values
	 * between 1 and 5
	 ******************************************************************/
	@Test
	public void testGregCalRVAllViableSiteNumbers() {
		for (int i = 1; i < 6; i++) {
			RV r = null;
			try {
				r = new RV("Matt Kinoodle", 
						new GregorianCalendar(2017, 11, 11), 
						5, i, 30);
			} catch (Exception e) {
				;
			}
			assertEquals(r.occupant, "Matt Kinoodle");
			assertEquals(r.formatCalendar(r.reservDate), "11/11/2017");
			assertEquals(r.duration, 5);
			assertEquals(r.siteNum, i);
			assertEquals(r.getPower(), 30);
		}
	}
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
	
	@Test
	public void testTest() {
		Tent t = null;
		try {
			t = new Tent("a a", "12/11/2017", "12/14/2017", 5, 5);
		}
		catch (Exception e) {
			;
		}
	}
	//EndRegion: RV(Name, startDate, endDate, site, power). endDate
}
