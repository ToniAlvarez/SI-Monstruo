package monstruo.model;

/**
 * Clase que representa un movimiento desde la casilla actual
 */
public class Agente {

    //Posición X, Y
    private int x;
    private int y;

    /**
     * Constructor
     */
    public Agente() {

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    /**
     * @return String que representa este movimiento
     */
    @Override
    public String toString() {
        return "Movimiento {" + "x=" + x + ", y=" + y + '}';
    }
}
