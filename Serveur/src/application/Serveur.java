package application;


import java.rmi.*;
import java.rmi.registry.LocateRegistry;

import modele.implementation.AccueilImpl;
import modele.implementation.AllumetteImpl;
import modele.implementation.Pendu;

public class Serveur {
	public static void main(String[] args) {
		try {
			String hote = "127.0.0.1";
			int port = Integer.parseInt("6002");
			// createRegistry permet de lancer le rmiregistry sur le port indiqué
			LocateRegistry.createRegistry(port);
			AllumetteImpl allumette = new AllumetteImpl();
            Naming.rebind("rmi://"+ hote +":"+port+ "/allumette", allumette);
            Naming.rebind("rmi://"+ hote +":"+port+ "/pendu", new Pendu());
			System.out.println ("Serveur prêt ! .");
		
		} catch (Exception e) {
			System.out.println ("Serveur échec " + e);
		}
	}
}

