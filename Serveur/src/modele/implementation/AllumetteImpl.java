package modele.implementation;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import modele.interfaceRMI.AllumetteInterface;

public class AllumetteImpl extends UnicastRemoteObject implements AllumetteInterface {

	private static final long serialVersionUID = 1L;

	int idAllumette;
	boolean bool;
	ArrayList<Integer> arrayAllumettes;

	public AllumetteImpl() throws RemoteException {
		super();
		idAllumette = -1;
		arrayAllumettes = new ArrayList<Integer>();
	}

	public int getNbAllumettes(int idPartie) throws RemoteException {
		return arrayAllumettes.get(idPartie);
	}

	public void retirerAllumettes(int nbAllumettes, int idPartie) throws RemoteException {
		arrayAllumettes.set(idPartie,(arrayAllumettes.get(idPartie)-nbAllumettes));
	}

	public boolean partieTerminee(int idPartie) throws RemoteException {
		if (getNbAllumettes(idPartie) == 0) {
			bool = true;
		}
		else {
			bool = false;
		}
		return bool;
	}

	public int newAllumette() throws RemoteException {
		arrayAllumettes.add(15);
		idAllumette++;
		return idAllumette;
	}
}