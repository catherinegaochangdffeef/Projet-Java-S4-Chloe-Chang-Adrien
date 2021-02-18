package application;

import java.rmi.*;

import interfaceRMI.PenduInterface;

public class Client {
	public static void main(String[] args) {
		try {
			String hote ="127.0.0.1";
			int port = Integer.parseInt("6002");
			// lookup est une méthode de Naming qui permet de rechercher un objet dans le service de nommage
			HelloInterface obj =
					(HelloInterface) Naming.lookup ("rmi://"+ hote +":"+port+ "/hello");
			System.out.println (obj.echo());
		} catch (Exception e) {
			System.out.println ("HelloClient exception: " + e);
		}
	}
}