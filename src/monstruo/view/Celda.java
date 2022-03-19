package monstruo.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

/**
 * Clase que representa las celdas del tablero
 * Las celdas pueden estar bloqueadas
 * Solo una de ellas puede ser la casilla inicial
 * Una vez encontrada una solución, a cada casilla se le asigna un numero de movimiento
 */
public class Celda extends JComponent {

    //ImageIcon de la hormiga, constante pues nunca cambia la imagen
    private static final ImageIcon IMAGE_ICON = new ImageIcon("images/heroe.png");

    private int x;
    private int y;

    private boolean blocked = false;
    private boolean currentPosition = false;

    // Static para que se comparta con todas las instancias de la clase
    private static Image image;

    /**
     * Constructor
     */
    public Celda(int x, int y) {
        this.x = x;
        this.y = y;

        setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        updateColor();

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                if (isCurrentPosition())
                    resizeImage();
            }
        });
    }

    public void resizeImage() {
        Celda.image = IMAGE_ICON.getImage().getScaledInstance(getWidth(), getWidth(), Image.SCALE_SMOOTH);
        repaint();
    }

    /**
     * Cambiar color de la celda en función de sus parámetros
     */
    private void updateColor() {

        if (blocked)
            setBackground(Color.BLACK);
        else
            setBackground(Color.WHITE);

        repaint();
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        graphics.setColor(getBackground());
        graphics.fillRect(0, 0, getWidth(), getHeight());

        //Si es la casilla de inicio, pintar la imagen de la pieza
        if (isCurrentPosition())
            graphics.drawImage(image, 0, 0, this);
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;

        updateColor();
    }

    public void setCurrentPosition(boolean currentPosition) {
        this.currentPosition = currentPosition;

        updateColor();
    }

    public boolean isCurrentPosition() {
        return currentPosition;
    }
}
