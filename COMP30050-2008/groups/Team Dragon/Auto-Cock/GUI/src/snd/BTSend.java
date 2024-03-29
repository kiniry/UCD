/**
 * @author Lar
 * @deprecated
 * This class was originally written to establish a basis for communication between a laptop and the brick
 * It has since been updated into BTSendObject.
 */package snd;

 import lejos.pc.comm.*;
 import java.io.*;

 public class BTSend {

	 public static void main(String[] args) {
		 NXTComm nxtComm = NXTCommFactory.createNXTComm(NXTCommFactory.BLUETOOTH);

		 if (args.length != 2) {
			 System.out.println("Usage: BTSend name address");
			 System.exit(1);
		 }

		 NXTInfo[] nxtInfo = new NXTInfo[1];

		 nxtInfo[0] = new NXTInfo(args[0],args[1]);

		 System.out.println("Connecting to " + nxtInfo[0].btResourceString);

		 boolean opened = false;

		 try {
			 opened = nxtComm.open(nxtInfo[0]); 
		 } catch (NXTCommException e) {
			 System.out.println("Exception from open");
			 e.printStackTrace();
		 }

		 if (!opened) {
			 System.out.println("Failed to open " + nxtInfo[0].name);
			 System.exit(1);
		 }

		 System.out.println("Connected to " + nxtInfo[0].btResourceString);

		 InputStream is = nxtComm.getInputStream();
		 OutputStream os = nxtComm.getOutputStream();

		 DataOutputStream dos = new DataOutputStream(os);
		 DataInputStream dis = new DataInputStream(is);

		 for(int i=0;i<7;i++) {
			 try {
				 System.out.println("Sending " + i);
				 dos.writeInt(i);
				 dos.flush();			

			 } catch (IOException ioe) {
				 System.out.println("IO Exception writing bytes:");
				 System.out.println(ioe.getMessage());
				 break;
			 }

			 try {
				 System.out.println("Received " + dis.readInt());
			 } catch (IOException ioe) {
				 System.out.println("IO Exception reading bytes:");
				 System.out.println(ioe.getMessage());
				 break;
			 }
		 }

		 try {
			 dis.close();
			 dos.close();
			 nxtComm.close();
		 } catch (IOException ioe) {
			 System.out.println("IOException closing connection:");
			 System.out.println(ioe.getMessage());
		 }
	 }
 }
