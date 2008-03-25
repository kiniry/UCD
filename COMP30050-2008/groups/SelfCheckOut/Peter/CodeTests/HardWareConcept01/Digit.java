	/**
	 * This  class is used to decode a digit from a barcode for the Hardware interface 
	 * components of the SelfChekcOut project.
	 * <p>
	 * 
	 * @author Peter Gibney
	 * @version 23rd March 2008.
	 */
public class Digit {

	private static int ODD_AND_EVEN = 0;
	private static int EVENS = 1;
	private static int ODDS = 2;
	private static int maxDiffValue = 0;

	private static final int[][] KEY_CODES = 	
				{{300, 200, 100, 100},
				{200, 200, 200, 100},
				{200, 100, 200, 200},
				{100, 400, 100, 100},
				{100, 100, 300, 200},
				{100, 200, 300, 100},
				{100, 100, 100, 400},
				{100, 300, 100, 200},
				{100, 200, 100, 300},
				{300, 100, 100, 200},
				{100, 100, 200, 300},
				{100, 200, 200, 200},
				{200, 200, 100, 200},
				{100, 100, 400, 100},
				{200, 300, 100, 100},
				{100, 300, 200, 100},
				{400, 100, 100, 100},
				{200, 100, 300, 100},
				{300, 100, 200, 100},
				{200, 100, 100, 300}};

	
	private static final boolean FIRST_DIGIT_LIST[][] =
		{{ false, false, false, false, false },
		{false, true, false, true, true },
		{false, true, true, false, true },
		{false, true, true, true, false },
		{true, false, false, true, true },
		{true, true, false, false, true },
		{true, true, true, false, false },
		{true, false, true, false, true },
		{true, false, true, true, false },
		{true, true, false, true, false }};


	
	private int parity; //odd = -1, NA = 0, even = 1
	private int digit;
//---------------------------------------------------------------
	/**
	 * Constructs a Digit object from a run length encoded array of 
	 * bars and spaces.
	 * @param codeTable Which code table to use, 0 to use odd and even, 
	 * 1 to use even, 2 to use odd.
	 * @param bars the run length coding of bars and spaces in an 
	 * array of ints, length 4.
	 * @throws IllegalArgumentException if bars is null.
	 * @throws IllegalArgumentException if length of bars is not 4.
	 * @throws IllegalArgumentException if codeTable is  < 0.
	 * @throws IllegalArgumentException if codeTable is  > 2.
	 */
	public Digit(int codeTable, int[] bars) {
		if (bars == null)
			throw new IllegalArgumentException("Digit() bars is null");
		if (bars.length != 4)
			throw new IllegalArgumentException("Digit() bars " +
					"is wrong length = " + bars.length + 
					", expecting length = 4");
		if (codeTable < 0)
			throw new IllegalArgumentException("Digit() codeTable value too " +
				"small= " + codeTable + ", expecting: 0<= codeTable <= 2 ");

		if (codeTable > 2)
			throw new IllegalArgumentException("Digit() codeTable value too " +
				"large= " + codeTable + ", expecting: 0<= codeTable <= 2 ");
		int start = 0; //where we read in the table
		int end = 0; //finish reading
		int offSet = 0;
		// normalize the run code so that the total length is 700
		
		int amtPixel = bars[0] + bars[1] + bars[2] + bars[3];
		int normedCode[] = new int[4];
		for (int i = 0; i < 4; i++) {
			normedCode[i] = Math.round((((float) bars[i]) / ((float) amtPixel)) * 700);
		}
		// print some debugging information:
		if (HWIconst.DE_BUG) {
			System.out.println("Recognize Number (code table to use: " + codeTable + "):");
			System.out.println("lengths: " + bars[0] + " " + bars[1] + " " + bars[2]+ " " + bars[3]);
			System.out.println("normed lengths: " + normedCode[0] + " " + normedCode[1] + " " + normedCode[2] + " " + normedCode[3]);
		}
		// try to detect the digit that is encoded by the set of four normed bar lenghts:
		int maxDiff = 960;
		int temp;
		int minDiff = Integer.MAX_VALUE;
		int minDiffPos = 0;
		boolean tie = false;  //no ties yet
		start = 0;
		end = 0;
		if (codeTable == ODD_AND_EVEN) {
			start = 0;
			end = 19;
			offSet = 0;
		}
		if (codeTable == ODDS) {
			start = 0;
			end = 9;
			offSet = 0;
		}
		if (codeTable == EVENS) {
			start = 10;
			end = 19; //end on this number
			offSet = 10;
		}
		int Diffs[] = new int[(end - start) + 1];
		
		for (int i = start; i <= end; i++) {
			for (int j = 0; j < 4; j++) {
				// calculate the differences in the even group:
				temp = Math.abs(normedCode[j] - KEY_CODES[i][j]);
				Diffs[i-offSet] = Diffs[i-offSet] + temp;
			}
			maxDiffValue = Math.max(maxDiffValue, Diffs[i-offSet]);
			if (codeTable == ODDS) {
				//System.out.println("start=" + start + ", end=" + end + ", 0ff=" + offSet + ", i=" + i);
				
			}
			if (Diffs[i-offSet] < minDiff) {
				minDiff = Diffs[i-offSet];
				minDiffPos = i;//-offSet;
			}
		}
		//crawl through the array looking for ties
		for (int i = start; i <= end; i++) {
			if (i != minDiffPos) {
				//not looking at self
				if (Diffs[i-offSet] == minDiff) {
					tie = true;
					if (HWIconst.DE_BUG)
						System.out.println("Tie; i=" + i + ", start = " + start + 
							", end= " + end + ", minDiffPos=" + minDiffPos +
							", offSet = " + offSet + ", minDiff= " + minDiff);
				}
			}
		}
		if (minDiffPos < 10) {
			digit = minDiffPos;
			parity = -1; //odd = -1, NA = 0, even = 1
		} else {
			
			//digit = minDiffPos%10; //as we got an even number >=10 in list
			digit = minDiffPos;
			parity = 1; //odd = -1, NA = 0, even = 1
		}
		if ((minDiff > maxDiff) || (tie)) {
			digit = -1;
			parity = 0; //odd = -1, NA = 0, even = 1
		}
		
		if (HWIconst.DE_BUG)
			System.out.println("maxDiffValue = " + maxDiffValue);
	}
	//---------------------------------------------------------------
	/**
	 * Constructs a Digit object from an array of booleans.
	 * @param parityValues The array of booleans, length 5 which is used to 
	 * create the first digit from the parity of digits 3 to 7.
	 * @throws IllegalArgumentException if parityValues is null.
	 * @throws IllegalArgumentException if length of parityValues is not 5.
	 */
	public  Digit(boolean[] parityValues) {
		if (parityValues == null)
			throw new IllegalArgumentException("Digit() parityValues is null");
		if (parityValues.length != 5)
			throw new IllegalArgumentException("Digit() parityValues " +
					"is wrong length = " + parityValues.length + 
					", expecting length = 5");
		// search for a matching parity
		boolean found = false;
		int i = 0;
		while (!found && i < 10){ 
			found = true;
			int j = 0;
			while (found && j < 5) {
				found = found && parityValues[j] == FIRST_DIGIT_LIST[i][j];
				j++;
			}
			//if found is true here then we have got a match
			i++;
		}
		if (found) {
			digit = i-1;
			parity = 0; //odd = -1, NA = 0, even = 1
		} else {
			parity = 0; //odd = -1, NA = 0, even = 1
			digit = -1;
		}
	}
	//------------------------------------------------------------------------------	
	public int getDigit() {
		return digit;
	}
	// ------------------------------------------------------
	public boolean isEven() {
		return (parity == 1); //odd = -1, NA = 0, even = 1
	}
	// ------------------------------------------------------
	public boolean isOdd() {
		return (parity == -1); //odd = -1, NA = 0, even = 1
	}
	// ------------------------------------------------------
	public boolean hasNoParity() {
		return (parity == 0); //odd = -1, NA = 0, even = 1
	}
	// ---------------------------------
	/**
	 * Converts this Digit object to a String.
	 * 
	 * @return this Code as a String.
	 */
	// ----------------------------------------
	public String toString() {
		String s = "Digit= " + digit + ", parity = " + parity + ", [odd = -1, NA = 0, even = 1]";
		return s;
	}
	// --------------------------------------------------
}