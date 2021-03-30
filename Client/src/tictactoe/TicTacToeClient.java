package tictactoe;

import javafx.scene.control.TextField;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


public class TicTacToeClient extends UnicastRemoteObject implements ClientRappelerListener {
	Remote remoteService = null;
	public CelluleListener celluleListener;

	public TicTacToeClient(TextField textField) throws RemoteException {
		super();
		try {
			remoteService = Naming.lookup(textField.getText());

			ClientRappeler callback = (ClientRappeler) remoteService;
			callback.setCellOnClientCallback(this);
		} catch (NotBoundException | MalformedURLException e) {
			e.printStackTrace();
		}
	}

	public void envoyerAServeur(int status, int index) {
		try {
			ServerInterface server = (ServerInterface) remoteService;
			server.envoyerCellule(status, index);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void envoyerAClient(int status, int index) throws RemoteException {
		if (celluleListener != null) {
			celluleListener.ecrireCellule(new Cellule(this, status, index));
		}
	}

	public void setCelluleListener(CelluleListener celluleListener) {
		this.celluleListener = celluleListener;
	}
}
