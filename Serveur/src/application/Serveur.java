package application;


import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import modele.implementation.AccueilImpl;
import modele.implementation.Pendu;

public class Serveur {
	public static void main(String[] args) {
		try {
			String hote = "127.0.0.1";
			int port = Integer.parseInt("6002");
			// createRegistry permet de lancer le rmiregistry sur le port indiqué
			LocateRegistry.createRegistry(port);
			// rebind est une méthode de la classe Naming qui permet d’exporter un objet dans le service de nommage
			Naming.rebind ("rmi://"+ hote +":"+port+ "/pendu", new Pendu() );
			
			// faire une hashtable pour trier tout les uuid des clients
			
			System.out.println ("Serveur prêt ! .");
		} catch (Exception e) {
			System.out.println ("Serveur échec " + e);
		}
	}
}

