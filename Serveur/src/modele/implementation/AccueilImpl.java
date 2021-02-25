package modele.implementation;

import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;

import modele.interfaceRMI.AccueilInterface;

public class AccueilImpl extends UnicastRemoteObject implements AccueilInterface{

	public AccueilImpl()throws RemoteException {
		super();
	}


}
