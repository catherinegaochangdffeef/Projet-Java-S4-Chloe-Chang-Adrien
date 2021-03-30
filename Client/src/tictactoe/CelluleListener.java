package tictactoe;

import java.util.EventListener;


public interface CelluleListener extends EventListener {
	void ecrireCellule (Cellule event);
}
