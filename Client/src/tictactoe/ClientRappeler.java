package tictactoe;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientRappeler extends Remote {
	void setCellOnClientCallback (ClientRappelerListener clientRappelerListener) throws RemoteException;
	ClientRappelerListener getCelluleClientRappeler() throws RemoteException;
}
