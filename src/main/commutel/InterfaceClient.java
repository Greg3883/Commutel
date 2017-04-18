package main.commutel;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class InterfaceClient implements Runnable {

	private Thread t;
	private Socket s;
	private PrintWriter out;
	private BufferedReader in; 
	private Commutateur commutateur;
	private int numClient = 0;
	
	InterfaceClient(Socket s, Commutateur commutateur){
		this.commutateur = commutateur;
		this.s = s;
		try{
			this.out = new PrintWriter(this.s.getOutputStream());
			this.in = new BufferedReader(new InputStreamReader(this.s.getInputStream()));
			this.numClient = commutateur.addPosteAbonne(this.out);
		} catch(IOException e){ 
			
		}
		
		this.t = new Thread(this);
		t.start();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		String message = "";
		System.out.println("Un nouveau client s'est connecte, no " + numClient);
		
		try{
			 char charCur[] = new char[1];
			 while(this.in.read(charCur, 0, 1)!=-1){
				 if (charCur[0] != '\u0000' && charCur[0] != '\n' && charCur[0] != '\r'){
					 message += charCur[0];
				 } else if(!message.equalsIgnoreCase("")) {
					 if(charCur[0]=='\u0000'){
						 this.commutateur.sendAll(message,""+charCur[0]);
					 } else {
						 this.commutateur.sendAll(message,"");
					 }
					 message = "";
				 }
			 }
		}  catch (Exception e){
			
		}
		
		finally {
			try{
				System.out.println("Le client no "+numClient+" s'est deconnecte");
				this.commutateur.delClient(numClient);
				this.s.close();
			}  catch (IOException e){ 
				
			}
		}

	}

}
