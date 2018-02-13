package campingPack;

public class CampingCalc {
	
	public static void main(String[] args) {
		int[] dates1 = {2020, 2, 29};
		int[] dates2 = {2021, 2, 28};
		System.out.println(getDifferenceInDates(dates1, dates2));
	}
	/*******************************************************************
	 * Returns the maximum number of days in a month, given the month
	 * and the year.
	 * 
	 * @param month The month the user gave.
	 * @param year The year the user gave.
	 * @return The maximum number of days in the month of the given year.
	 ******************************************************************/
	public static int getMaxDays(int month, int year) {
		int maxDays = 0;
		switch (month) {
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:
			maxDays = 31;
			break;
		case 4:
		case 6:
		case 9:
		case 11:
			maxDays = 30;
			break;
		case 2:
			if (year % 4 == 0)
				maxDays = 29;
			if (year % 4 != 0)
				maxDays = 28;
			break;
		}
		return maxDays;
	}
	
	/*******************************************************************
     * Gets the difference between start date and end date. Have to do 
     * this manually due to Dates class being inaccurate.
     ******************************************************************/
	public static int getDifferenceInDates(int[] dates1, int[] dates2) {
    	int daysDiff = 0;
    	int maxDays = CampingCalc.getMaxDays(dates2[1], dates2[0]);
    	int currentYear = dates2[0];
    	int currentMonth = dates2[1];
    	int currentDay = dates2[2];
    	boolean datesEqual = false;
    	
    	if (currentYear < dates1[0])
    		return -1;
    		
    	if (currentYear == dates1[0])
			if (currentMonth < dates1[1])
				return -1;
    	
    	if (currentYear == dates1[0])
			if (currentMonth == dates1[1])
				if (currentDay < dates1[2])
					return -1;
    	
    	if (currentYear == dates1[0])
			if (currentMonth == dates1[1])
				if (currentDay == dates1[2])
					return 0;
		
    	while (!datesEqual) {
    		daysDiff+=1;
    		
    		if (currentDay == 1) {
    			if (currentMonth == 1) {
    				currentMonth = 12;
    				currentYear -= 1;
    				
    			} else {
    				currentMonth -=1;    
    				
    			}
    			maxDays = CampingCalc.getMaxDays(currentMonth, currentYear);
    			currentDay = maxDays;
    			
    		} else {
    			currentDay -=1;
    			
    		}
    		
    		if (currentYear == dates1[0])
    			if (currentMonth == dates1[1])
    				if (currentDay == dates1[2])
    					datesEqual = true;
    	}
    	return daysDiff;
    }
	
	public static int getDaysSinceStart(int day, int month, int year) {
		int currentYear = year;
		int currentMonth = month;
		int currentDay = day;
		int maxDays = CampingCalc.getMaxDays(currentMonth, currentYear);
		int daysDiff = 0;
		boolean atStart = false;
    	
    	if (currentYear == 2017)
			if (currentMonth == 11)
				if (currentDay == 1)
					return 0;
		
		while (!atStart) {
    		daysDiff+=1;
    		
    		if (currentDay == 1) {
    			if (currentMonth == 1) {
    				currentMonth = 12;
    				currentYear -= 1;
    				
    			} else {
    				currentMonth -=1;    
    				
    			}
    			maxDays = CampingCalc.getMaxDays(currentMonth, currentYear);
    			currentDay = maxDays;
    			
    		} else {
    			currentDay -=1;
    			
    		}
    		
    		if (currentYear == 2017)
    			if (currentMonth == 11)
    				if (currentDay == 1)
    					atStart = true;
    	}
    	return daysDiff;
	}
}
