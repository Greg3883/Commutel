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

public class InterfaceClient {

	public static void main(String[] args) {

		Socket clientSocket = null;
		DataInputStream is = null;
		PrintStream os = null;
		DataInputStream inputLine = null;

		try {
			clientSocket = new Socket("localhost", 5000);
			os = new PrintStream(clientSocket.getOutputStream());
			is = new DataInputStream(clientSocket.getInputStream());
			inputLine = new DataInputStream(new BufferedInputStream(System.in));
		} catch (UnknownHostException e) {
			System.err.println("Don't know about host");
		} catch (IOException e) {
			System.err.println("Couldn't get I/O for the connection to host");
		}

		if (clientSocket != null && os != null && is != null) {
			try {

				System.out.println("The client started. Type any text. To quit it type 'Ok'.");
				String responseLine;
				os.println(inputLine.readLine());
				while ((responseLine = is.readLine()) != null) {
					System.out.println(responseLine);
					if (responseLine.indexOf("Ok") != -1) {
						break;
					}
					os.println(inputLine.readLine());
				}

				os.close();
				is.close();
				clientSocket.close();
			} catch (UnknownHostException e) {
				System.err.println("Trying to connect to unknown host: " + e);
			} catch (IOException e) {
				System.err.println("IOException:  " + e);
			}
		}
	}

	public void decrocher() {

	}

}
