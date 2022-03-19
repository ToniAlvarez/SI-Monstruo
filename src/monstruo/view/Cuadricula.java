package monstruo.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Cuadricula extends JPanel {

    /**
     * Constant fields
     */
    private static final int MIN_BOARD_SIZE = 5;
    private static final int MAX_BOARD_SIZE = 10;

    private static final int DEFAULT_BOARD_SIZE = 5;

    /**
     * Fields
     */
    private int boardSize;

    /**
     * Array de celdas que representan el tablero
     */
    private Celda[][] casillas;

    /**
     * Constructor
     */
    public Cuadricula() {
        boardSize = DEFAULT_BOARD_SIZE;
        configureBoard();
    }

    /**
     * Inicializar el array de celdas
     * Se tiene que ejecutar cada vez que se cambia el tamaño del tablero
     */
    private void configureBoard() {
        setLayout(new GridLayout(0, boardSize));

        //Inicializar el array de Celdas que representa el tablero
        casillas = new Celda[boardSize][boardSize];

        //Rellenar el GridLayout con las celdas
        for (int row = 0; row < boardSize; row++) {
            for (int col = 0; col < boardSize; col++) {

                Celda cell = new Celda(row, col);

                if (row == 0 && col == 0)
                    cell.setCurrentPosition(true);

                int finalCol = row;
                int finalRow = col;

                cell.addMouseListener(new MouseAdapter() {
                    public void mouseReleased(MouseEvent mouseEvent) {

                        if (SwingUtilities.isLeftMouseButton(mouseEvent))
                            if (cell.isEnabled())
                                cell.setBlocked(!cell.isBlocked());

                        if (SwingUtilities.isRightMouseButton(mouseEvent))
                            if (cell.isEnabled()) {
                                setCurrentPosition(finalRow, finalCol);
                            }
                    }
                });

                casillas[row][col] = cell;
                add(casillas[row][col]);
            }
        }
    }

    /**
     * Obtener la casilla actual
     *
     * @return
     */
    public Celda getCurrentPosition() {
        //Resetear la anterior casilla inicial
        for (int row = 0; row < boardSize; row++)
            for (int col = 0; col < boardSize; col++)
                if (casillas[row][col].isCurrentPosition())
                    return casillas[row][col];

        return null;
    }

    /**
     * Cambiar casilla actual
     *
     * @param currentRow fila de la casilla actual
     * @param currentCol columna de la casilla actual
     */
    public void setCurrentPosition(int currentCol, int currentRow) {

        //Resetear la anterior casilla inicial
        for (int row = 0; row < boardSize; row++)
            for (int col = 0; col < boardSize; col++)
                casillas[row][col].setCurrentPosition(false);

        casillas[currentRow][currentCol].setBlocked(false);
        casillas[currentRow][currentCol].setCurrentPosition(true);
    }

    /**
     * Aumentar el tamaño del tablero
     */
    public void increaseSize() {
        if (boardSize >= MAX_BOARD_SIZE)
            return;

        boardSize++;
        removeAll();
        configureBoard();
        repaint();
    }

    /**
     * Reducir el tamaño del tablero
     */
    public void decreaseSize() {
        if (boardSize <= MIN_BOARD_SIZE)
            return;

        boardSize--;
        removeAll();
        configureBoard();
        repaint();
    }

    /**
     * Activar o desactivar las celdas
     *
     * @param enabled estado de las celdas
     */
    public void setCellsEnabled(boolean enabled) {
        for (int row = 0; row < boardSize; row++)
            for (int col = 0; col < boardSize; col++)
                casillas[row][col].setEnabled(enabled);
    }

    /**
     * Obtener tamaño del tablero
     *
     * @return tamaño del tablero
     */
    public int getBoardSize() {
        return boardSize;
    }

    /**
     * Obtener array de celdas del tablero
     *
     * @return array de celdas
     */
    public Celda[][] getCasillas() {
        return casillas;
    }
}