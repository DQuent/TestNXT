import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.comm.Bluetooth;
import lejos.nxt.comm.NXTConnection;



public class Hello_world {
		  public static void main (String[] args) throws IOException, InterruptedException {
				Hello_world.readTest();
		  }
		  
		  
		  public static void sendTest() throws IOException, InterruptedException  {
			  boolean isrunning = true;

				// Main loop   
				while (true)
				{
				  LCD.drawString("waiting",0,0);
				  LCD.refresh();

				  // Listen for incoming connection

				  NXTConnection btc = Bluetooth.waitForConnection();

				  btc.setIOMode(NXTConnection.RAW);

				  LCD.clear();
				  LCD.drawString("connected",0,0);
				  LCD.refresh();  


				  // The InputStream for read data 
				  DataInputStream dis = btc.openDataInputStream();
				  //envoie de la BD
				  DataOutputStream out = btc.openDataOutputStream();
				  String msg_out = "/T1;7;/T2;7;/T3;7;/Lum;1;lumiere;true;50;/ConfStandard;1;conf1;1;2;3;true;/ConfCour;2;conf2;1;4;/Conf;3;conf3;5;1;false;";
				  out.writeUTF(msg_out);  
				  out.flush();	
				  boolean isSomeOneInRoom = false;
				// Loop for read data  
				  while (isrunning) {
					
					  java.lang.String msg = dis.readUTF();
				    LCD.drawString(msg, 4, 4); //à supprimer : affichage du message recu sur la brique
					
					
					LCD.clear();
					LCD.refresh();
					
				  }
			
				  dis.close();
				  out.close();
				  // Wait for data to drain
					Thread.sleep(100);


				  LCD.clear();
				  LCD.drawString("closing",0,0);
				  LCD.refresh();

				  btc.close();

				  LCD.clear();
				}
		  }
		  
		  public static void readTest()  throws IOException, InterruptedException {
			  boolean isrunning = true;

				// Main loop   
				while (true)
				{
				  LCD.drawString("waiting",0,0);
				  LCD.refresh();

				  // Listen for incoming connection

				  NXTConnection btc = Bluetooth.waitForConnection();

				  btc.setIOMode(NXTConnection.RAW);

				  LCD.clear();
				  LCD.drawString("connected",0,0);
				  LCD.refresh();  


				  // The InputStream for read data 
				  DataInputStream dis = btc.openDataInputStream();
				  //envoie de la BD
				  DataOutputStream out = btc.openDataOutputStream();
				  String msg_out = "/T1;7;/T2;7;/T3;7;/Lum;1;lumiere;true;50;/ConfStandard;1;conf1;1;2;3;true;/ConfCour;2;conf2;1;4;/Conf;3;conf3;5;1;false;";
				  out.writeUTF(msg_out);  
				  out.flush();	
				  boolean isSomeOneInRoom = true;
				// Loop for read data  

				  while (isrunning) {
			

					byte msg = dis.readByte();

						LCD.clear();
					    LCD.drawString("JE RECOIS UN TRUC", 4, 4); //à supprimer : affichage du message recu sur la brique
						LCD.refresh(); 
						  
					if(msg == 00000001) {
						LCD.clear();
					    LCD.drawString("Lum 1 : on", 4, 4); //à supprimer : affichage du message recu sur la brique
						LCD.refresh(); 
					}
					else if(msg == 00000010){
						LCD.clear();
					    LCD.drawString("Lum 1 : off", 4, 4); //à supprimer : affichage du message recu sur la brique
						LCD.refresh(); 
					}
					else {
						LCD.clear();
					    LCD.drawString("Un pb, msg = "+Byte.toString(msg), 4, 4); //à supprimer : affichage du message recu sur la brique
						LCD.refresh(); 
					}
					
				  }
			
				  dis.close();
				  out.close();
				  // Wait for data to drain
					Thread.sleep(100);


				  LCD.clear();
				  LCD.drawString("closing",0,0);
				  LCD.refresh();

				  btc.close();

				  LCD.clear();
				}
			  
		  }
}

