package application;


import java.rmi.*;
import java.rmi.registry.LocateRegistry;

import modele.implementation.AccueilImpl;




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
		System.out.println ("Conversion Server prêt ! .");
		} catch (Exception e) {
		System.out.println ("Conversion Server échec " + e);
		}
		}
		}

