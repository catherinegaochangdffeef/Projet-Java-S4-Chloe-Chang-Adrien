package implementation;

import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;

import interfaceRMI.AccueilInterface;

public class AccueilImpl extends UnicastRemoteObject implements AccueilInterface{

	public AccueilImpl()throws RemoteException {
		super();
	}

}
