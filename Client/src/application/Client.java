package application;

import java.rmi.*;

import interfaceRMI.PenduInterface;
import interfaceRMI.AccueilInterface;
import interfaceRMI.AllumettreInterface;
import interfaceRMI.TicTacToeInterface;

public class Client {

	public static void main(String[] args) {
		try {
			String hote = "127.0.0.1";
			int port = Integer.parseInt("6002");
			// lookup est une méthode de Naming qui permet de rechercher un objet dans le
			// service de nommage
			AccueilInterface accueil = (AccueilInterface) Naming.lookup("rmi://" + hote + ":" + port + "/hello");
			System.out.println("Client est pret !");
		} catch (Exception e) {
			System.out.println("Client exception: " + e);
		}
	}
}