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
			AccueilImpl acip = new AccueilImpl();
			// rebind est une méthode de la classe Naming qui permet d’exporter un objet dans le service de nommage
			Naming.rebind ("rmi://"+ hote +":"+port+ "/hello", acip );
			AllumetteImpl allumette = new AllumetteImpl();
            Naming.rebind("rmi://"+ hote +":"+port+ "/allumette", allumette);
			System.out.println ("Serveur prêt ! .");
			Pendu p1 = new Pendu();
			System.out.println(p1.toString());
			System.out.println(p1.RechCharactere('C'));
			if (p1.RechCharactere('Z') != true) {
				p1.AfficheLettres( 'C');
			}
			System.out.println(p1.toString());
		} catch (Exception e) {
			System.out.println ("Serveur échec " + e);
		}
	}
}

