package modele.implementation;

import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;

import modele.interfaceRMI.AccueilInterface;

public class AccueilImpl extends UnicastRemoteObject implements AccueilInterface{
	private static final long serialVersionUID = 1L;

	public AccueilImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}
}
