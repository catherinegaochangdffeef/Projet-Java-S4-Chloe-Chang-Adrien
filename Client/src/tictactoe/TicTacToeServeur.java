package tictactoe;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;


public class TicTacToeServeur extends UnicastRemoteObject implements ClientRappeler, ServerInterface {

	private ClientRappelerListener ClientRappelerListener;
	private CelluleListener CelluleListener;

	public void setCelluleListener(CelluleListener CelluleListener) {
		this.CelluleListener = CelluleListener;
	}

	public TicTacToeServeur() throws RemoteException {
		Registry registry = LocateRegistry.createRegistry(1234);
		registry.rebind("LocalServer", this);
	}

	public void envoyerAClient (int status, int index) {
		if(ClientRappelerListener != null) {
			try {
				ClientRappelerListener.envoyerAClient(status, index);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void setCellOnClientCallback (ClientRappelerListener ClientRappelerListener) throws RemoteException {
		this.ClientRappelerListener = ClientRappelerListener;
	}

	@Override
	public ClientRappelerListener getCelluleClientRappeler() throws RemoteException {
		return ClientRappelerListener;
	}

	@Override
	public void envoyerCellule(int status, int index) {
		if (CelluleListener != null) {
			CelluleListener.ecrireCellule(new Cellule(this,status, index));
		}
	}
}
