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
import java.util.Vector;

//Test
public class Commutateur {
	private int nbLignes;
	private Vector listePosteAbonne = new Vector();

	public static void main(String args[]) {
		Commutateur commutateur = new Commutateur();
		Integer port = 5000;

		try {
			ServerSocket ServCommutateur = new ServerSocket(port);
			affichageMenu();
			while (true) {
				new InterfaceClient(ServCommutateur.accept(), commutateur);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	synchronized public void sendAll(String message, String sLast) {
		PrintWriter out;
		for (int i = 0; i < listePosteAbonne.size(); i++){
			out = (PrintWriter) listePosteAbonne.elementAt(i);
			if (out != null) {
				out.print(message + sLast);
				out.flush();
			}
		}
	}

	static private void affichageMenu() {
		System.out.println("Bienvenu sur Commutel");
	}

	synchronized public void delClient(int i) {
		nbLignes--;
		if (listePosteAbonne.get(i) != null) {
			listePosteAbonne.remove(i);
		}
	}

	synchronized public int addPosteAbonne(PrintWriter out) {
		nbLignes++;
		listePosteAbonne.add(out);
		return listePosteAbonne.size() - 1;
	}

	synchronized public int getNbLignes() {
		return nbLignes;
	}

}
