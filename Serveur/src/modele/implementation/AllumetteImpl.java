package modele.implementation;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Random;

import modele.interfaceRMI.AllumetteInterface;

public class AllumetteImpl extends UnicastRemoteObject implements AllumetteInterface {

	int idAllumette;
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
	
	public int newAllumette() throws RemoteException {
		arrayAllumettes.add(15);
		idAllumette++;
        return idAllumette;
    }
}