package main.commutel;

import java.util.ArrayList;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

//Test
public class Commutateur {
	private int nbLignes;
	private ArrayList<Integer> listePosteAbonne = new ArrayList<Integer>();

	public Commutateur(ArrayList<Integer> listePosteAbonne) {
		super();
		this.nbLignes = 0;
		this.listePosteAbonne = listePosteAbonne;
	}

	public static void main(String args[]) {

		ServerSocket echoServer = null;
		String line;
		DataInputStream is;
		PrintStream os;
		Socket clientSocket = null;

		try {
			echoServer = new ServerSocket(5000);
		} catch (IOException e) {
			System.out.println(e);
		}

		System.out.println("The server started. To stop it press <CTRL><C>.");
		try {
			clientSocket = echoServer.accept();
			is = new DataInputStream(clientSocket.getInputStream());
			os = new PrintStream(clientSocket.getOutputStream());

			while (true) {
				line = is.readLine();
				os.println("From server: " + line);
			}
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	public int getNbLignes() {
		return nbLignes;
	}

	public void setNbLignes(int nbLignes) {
		this.nbLignes = nbLignes;
	}

	public ArrayList<Integer> getListePosteAbonne() {
		return listePosteAbonne;
	}

	public void setListePosteAbonne(ArrayList<Integer> listePosteAbonne) {
		this.listePosteAbonne = listePosteAbonne;
	}

	public void addPosteAbonne(posteAbonne posteAbonne) {
		int idToAdd = posteAbonne.getId();
		this.listePosteAbonne.add(idToAdd);
	}

}
