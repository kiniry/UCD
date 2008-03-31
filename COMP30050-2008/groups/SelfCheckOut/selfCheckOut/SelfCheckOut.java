/**
 * This  class is used as the main loop 
 *  for the SelfChekcOut project.
 * <p>
 * 
 * @author Peter Gibney
 * @version 30th March 2008.
 */


package selfCheckOut;

import java.awt.*;
import selfCheckOut.hardWareInterface.HardWareResult;
import selfCheckOut.hardWareInterface.HWIconduit;

import java.util.*;

public class SelfCheckOut extends Thread {

	private HWIconduit hwc = null;
	
	SelfCheckOut() {
		System.out.println("This is the test for the SelfCheckout project");
		hwc = new HWIconduit();
		hwc.start();
		//Process();
	}
	
	public void run() {
		int counter = 0;
		while (true) {
			counter++;
			//if (counter == 90)
				//hwc.gather(false);
			HardWareResult temp = null;
			temp =  hwc.getHardWareResult();
			if (temp != null) {
				BarCode[] barCodes = temp.getBarCodes();
				System.out.println("\n");
				for (int i = 0; i < barCodes.length; i++) {
					System.out.println("Barcode number " 
										+ i + " is " + 
										barCodes[i].toString() +
										", time stamp= " +
										barCodes[i].getTimeStamp() + 
										", probability= " +
										barCodes[i].getProbability());
				}
				System.out.println("\n");
				for (int i = 1; i <= 2; i++) {
					System.out.println("Weight number " 
										+ i + " is " + 
										temp.getWeight(i).toString() +
										", time stamp= " +
										temp.getWeight(i).getTimeStamp());
				}
				System.out.println("**************************************");
			}
			if (counter == 100) {
				System.out.print(".");
				hwc.gather(true);
				counter = 0;
			}

			try {
				sleep(50);
			} catch (InterruptedException e) {
				System.out.println("selfCheckOut() exception = " + e.toString());
				e.printStackTrace();
			}
		}
		
	}
}