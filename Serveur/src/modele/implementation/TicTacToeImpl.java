package modele.implementation;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


import modele.interfaceRMI.TicTacToeInterface;

public class TicTacToeImpl extends UnicastRemoteObject implements TicTacToeInterface{

	public TicTacToeImpl()throws RemoteException  {
		super();
	}

}
