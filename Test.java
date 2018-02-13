package campingPack;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Test {

	public static ArrayList<String> dateArray = new ArrayList<String>();
	private static int count = 0;
	public static void main(String[] args) {	
		newArrayList();
//		sortWithAddedNumbersOnString();
//		sortWithoutAddedNumbersOnString();
		sortWithBinarySearchOnString();
	}
	
	private static void newArrayList() {
		Random rnd1 = new Random();

		dateArray.clear();
		for (int i = 0; i < 50; i++) {
			String tempD = (rnd1.nextInt(12 - 1 + 1) + 1) + "/" + (rnd1.nextInt(28 - 1 + 1) + 1) + "/" + (rnd1.nextInt(19 - 1 + 1) + 2018) + ": " + (rnd1.nextInt(5 - 1 + 1) + 1);
			dateArray.add(tempD);
		}
	}
	
	private static void sortWithoutAddedNumbersOnString() {
		String temp;
		String[] tempStr;
		int[] tempInt = new int[3];
		int currentTotal = 0;
		int prevTotal = 0;
		boolean swapped = true;
		long startTime = System.currentTimeMillis();
		while (swapped) {
			currentTotal = 0;
			prevTotal = 0;
			swapped = false;
			
			for (int i = 0; i < dateArray.size(); i++) {
				temp = dateArray.get(i).substring(0, dateArray.get(i).indexOf(":"));
//				System.out.println(temp);
				tempStr = temp.split("/");
				for (int j = 0; j < 3; j++)
					tempInt[j] = Integer.parseInt(tempStr[j]);
				
				prevTotal = currentTotal;
				currentTotal = CampingCalc.getDaysSinceStart(tempInt[1], tempInt[0], tempInt[2]);
				
				if (currentTotal < prevTotal) {
					swapElements(i);
					swapped = true;
					continue;
				}
			}
		}
		
		System.out.println((System.currentTimeMillis() - startTime));
	}
	
	private static void sortWithAddedNumbersOnString() {
		String temp;
		String[] tempStr;
		int[] tempInt = new int[3];
		int currentTotal;
		long startTime = System.currentTimeMillis();
		ArrayList<Integer> tempList = new ArrayList<Integer>();
		ArrayList<String> newList = new ArrayList<String>();
		
		for (int i = 0; i < dateArray.size(); i++) {
			temp = dateArray.get(i).substring(0, dateArray.get(i).indexOf(":"));
			tempStr = temp.split("/");
			for (int j = 0; j < 3; j++)
				tempInt[j] = Integer.parseInt(tempStr[j]);
			
			currentTotal = CampingCalc.getDaysSinceStart(tempInt[1], tempInt[0], tempInt[2]);
			dateArray.set(i, dateArray.get(i) + "," + currentTotal);
			
			tempList.add(currentTotal);
		}
		
		Collections.sort(tempList);

		String currentTime;
		String dateArrayTime;
		for (int i = 0; i < tempList.size(); i++) {
			currentTime = Integer.toString(tempList.get(i));
			for (int j = 0; j < dateArray.size(); j++ ) {
				dateArrayTime = dateArray.get(j).substring(dateArray.get(j).indexOf(",") + 1);
				if (dateArrayTime.equalsIgnoreCase(currentTime)) {
					newList.add(dateArray.get(j).substring(0, dateArray.get(j).indexOf(",")));
					dateArray.remove(j);
					continue;
				}
			}	
		}
		
		System.out.println((System.currentTimeMillis() - startTime));
//		for (int i = 0; i < tempList.size(); i++) {
//			System.out.println(newList.get(i));
//		}
	}
	
	private static void sortWithBinarySearchOnString() {
		String temp;
		String[] tempStr;
		int[] tempInt = new int[3];
		int currentTotal;
		long startTime = System.currentTimeMillis();
		ArrayList<Integer> tempList = new ArrayList<Integer>();
		ArrayList<String> newList = new ArrayList<String>();
		
		for (int i = 0; i < dateArray.size(); i++) {
			newList.add("");
		}
		
		for (int i = 0; i < dateArray.size(); i++) {
			temp = dateArray.get(i).substring(0, dateArray.get(i).indexOf(":"));
			tempStr = temp.split("/");
			for (int j = 0; j < 3; j++)
				tempInt[j] = Integer.parseInt(tempStr[j]);
			
			currentTotal = CampingCalc.getDaysSinceStart(tempInt[1], tempInt[0], tempInt[2]);
//			dateArray.set(i, dateArray.get(i) + "," + currentTotal);
			
			tempList.add(currentTotal);
		}
		
		Collections.sort(tempList);

		int currentDiff = 0;
		int currentIndex = -1;
		for (int i = 0; i < tempList.size(); i++) {
			temp = dateArray.get(i).substring(0, dateArray.get(i).indexOf(":"));
			tempStr = temp.split("/");
			for (int j = 0; j < 3; j++)
				tempInt[j] = Integer.parseInt(tempStr[j]);
			
			currentDiff = CampingCalc.getDaysSinceStart(tempInt[1], tempInt[0], tempInt[2]);
			
			int l = 0;
	        int r = tempList.size() - 1;
	        int m = 0;
	        
	        while (l <= r)
	        {
	            m = l + (r-l)/2;
//	            currentIndex = m;
	            
	            // Check if x is present at mid
	            if (tempList.get(m) == currentDiff)
	            	currentIndex = m;
	 
	            // If x greater, ignore left half
	            if (tempList.get(m) < currentDiff)
	                l = m + 1;
	 
	            // If x is smaller, ignore right half
	            else
	                r = m - 1;
	        }
	        
	        newList.set(currentIndex, dateArray.get(i));
		}
		
		System.out.println((System.currentTimeMillis() - startTime));
		for (int i = 0; i < newList.size(); i++) {
			System.out.println(i + "= " +newList.get(i));
		}
	}
	
	private static void swapElements(int currentIndex) {
		String temp = dateArray.get(currentIndex);
		dateArray.set(currentIndex, dateArray.get(currentIndex - 1));
		dateArray.set(currentIndex - 1, temp);
		count++;
//		System.out.println("" + count);
	}
}

