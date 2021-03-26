package tictactoe;

import java.rmi.Remote;
import java.rmi.RemoteException;


public interface ServerInterface extends Remote {
    void sendCell (int status, int index) throws RemoteException;
}
