package monstruo.model;

import monstruo.EventsListener;
import monstruo.Main;

/*
  Esta clase ahora mismo no tiene ningún tipo de funcionalidad porque los datos del programa
  únicamente son piezas y sus movimientos asociados. Se ha decidido crear una clase para cada
  una de ellas para tener una mejor gestión y estructura (también por requisito del enunciado
  de la práctica). Sin embargo se mantiene dicha clase por si en el futuro se añaden más
  funcionalidades que requieran más datos y así se podría hacer el tratamiento aqui mismo.
  También se mantiene por completitud del MVC.
 */

/**
 * @author antoniAlvarez
 * @author juanjoTorres
 */
public class Model implements EventsListener {

    private Main main;

    /**
     * Constructor
     */
    public Model(Main main) {
        this.main = main;
    }

    @Override
    public void notify(String message) {
        System.out.println("Mensaje en Modelo: " + message);
    }

}
