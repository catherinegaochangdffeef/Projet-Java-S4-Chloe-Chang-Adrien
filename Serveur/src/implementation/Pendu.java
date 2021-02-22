package implementation;

import java.rmi.RemoteException;
import interfaceRMI.PenduInterface;

public class Pendu implements PenduInterface {
    public static int NB_ERREURS_MAX=10;
    // liste des mots du pendu
    String[] dico = new String[]{"complexe","analyse","depiter","cuillere","interface"};
    //
    @Override
    public boolean RechCharactere(char c) {
        
        return false;
    }
    
    
}