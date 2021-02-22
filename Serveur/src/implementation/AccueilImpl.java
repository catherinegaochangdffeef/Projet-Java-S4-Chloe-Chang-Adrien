package implementation;

import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;

import interfaceRMI.AccueilInterface;
import vue.VueAccueil;

public class AccueilImpl extends UnicastRemoteObject implements AccueilInterface{

	public AccueilImpl()throws RemoteException {
		super();
	}


}
