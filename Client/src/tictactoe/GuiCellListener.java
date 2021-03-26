package tictactoe;

import java.util.EventListener;


public interface GuiCellListener extends EventListener {
    void writeCell (Cell event);
}
