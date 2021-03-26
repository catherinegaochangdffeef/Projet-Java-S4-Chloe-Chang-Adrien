package tictactoe;

import java.util.EventObject;

public class Cell extends EventObject {
    // 1-> X
    // 2 -> O

    private int status;
    private int index;

    public Cell(Object source, int status, int index) {
        super(source);
        this.status = status;
        this.index = index;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public int getIndex() {
        return index;
    }
}
