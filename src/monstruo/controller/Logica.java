package monstruo.controller;

import monstruo.Main;
import monstruo.view.Cuadricula;

/**
 * @author antoniAlvarez
 * @author juanjoTorres
 */
public class Logica extends Thread {

    private Main main;
    private Cuadricula cuadricula;

    private int delay = 1000;

    private boolean stopped = false;

    /**
     * Constructor
     *
     * @param main referencia a clase principal
     */
    public Logica(Main main) {
        this.main = main;
        this.cuadricula = main.getView().getBoard();
    }

    /**
     * Método runnable que ejecuta el algoritmo de búsqueda
     */
    public void run() {

        while (!stopped) {
            espera(delay);
        }

        //Actualizar interfaz al terminar
        main.getView().notify("End," + stopped);
    }

    /**
     * Mëtodo auxiliar de espera
     *
     * @param m milisegundos de espera
     */
    private void espera(long m) {
        try {
            Thread.sleep(m);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Modificar el delay entre llamadas recursivas
     *
     * @param delay nuevo delay
     */
    public void setDelay(int delay) {
        this.delay = delay;
    }

    /**
     * Para el algoritmo de búsqueda y en consecuencia el Thread.
     */
    public void stopTour() {
        stopped = true;
    }
}
