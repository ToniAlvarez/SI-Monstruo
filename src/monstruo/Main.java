package monstruo;

import monstruo.controller.Controller;
import monstruo.model.Model;
import monstruo.view.View;

public class Main {

    private Model model;                // Puntero al Modelo
    private View view;                  // Puntero a la Vista
    private Controller controller;      // Puntero al Controlador

    /**
     * Construcció de l'esquema MVC
     */
    private void init() {
        model = new Model(this);
        controller = new Controller(this);
        view = new View("Práctica 2 - Monstruo", this);
        view.showView();
    }

    public static void main(String[] args) {
        (new Main()).init();
    }

    public Model getModel() {
        return model;
    }

    public View getView() {
        return view;
    }

    public Controller getController() {
        return controller;
    }
}
