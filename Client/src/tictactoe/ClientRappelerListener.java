package tictactoe;

import java.rmi.Remote;
import java.rmi.RemoteException;


public interface ClientRappelerListener extends Remote {
	void envoyerAClient(int status, int index) throws RemoteException;
}
