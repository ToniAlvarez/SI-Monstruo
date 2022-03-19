package monstruo.controller;

import monstruo.EventsListener;
import monstruo.Main;

/**
 * @author antoniAlvarez
 * @author juanjoTorres
 */
public class Controller implements EventsListener {

    private Main main;
    private Logica logica;

    private int tourDelay;

    public Controller(Main main) {
        this.main = main;
    }

    @Override
    public void notify(String message) {
        System.out.println("Mensaje en Control: " + message);

        if (message.startsWith("Start")) {
            logica = new Logica(main);
            logica.setDelay(tourDelay);
            logica.start();
        } else if (message.startsWith("Stop")) {
            if (logica.isAlive())
                logica.stopTour();
        } else if (message.startsWith("Delay")) {

            String[] msgParts = message.split(",");
            tourDelay = Integer.parseInt(msgParts[1]);

            if (logica == null || !logica.isAlive())
                return;

            logica.setDelay(tourDelay);
        }
    }

}
