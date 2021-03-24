package application;


import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import modele.implementation.AllumetteImpl;
import modele.implementation.Pendu;
import modele.implementation.TicTacToeImpl;

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
            Naming.rebind("rmi://"+ hote +":"+port+ "/tictactoe", new TicTacToeImpl());
			System.out.println ("Serveur prêt ! .");
		
		} catch (Exception e) {
			System.out.println ("Serveur échec " + e);
		}
	}
}

