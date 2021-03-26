package tictactoe;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientCallback extends Remote {
    void setCellOnClientCallback (ClientCallbackListener clientCallbackListener) throws RemoteException;
    ClientCallbackListener getCellOnClientCallback() throws RemoteException;
}
