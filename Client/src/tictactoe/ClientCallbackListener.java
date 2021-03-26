package tictactoe;

import java.rmi.Remote;
import java.rmi.RemoteException;


public interface ClientCallbackListener extends Remote {
    void sendToClient(int status, int index) throws RemoteException;
}
