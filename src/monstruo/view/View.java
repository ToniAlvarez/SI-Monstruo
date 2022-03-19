package monstruo.view;

import monstruo.EventsListener;
import monstruo.Main;

import javax.swing.*;
import java.awt.*;

/**
 * @author nadalLlabres
 * @author antoniAlvarez
 */
public class View extends JFrame implements EventsListener {

    /**
     * Constant fields
     */
    private static final int DEFAULT_WIDTH = 640;
    private static final int DEFAULT_HEIGHT = 620;

    private static final int ICON_SIZE = 50;

    private static final int STATUS_READY = 1;
    private static final int STATUS_RUNNING = 2;
    private static final int STATUS_FINISH = 3;

    /**
     * Componentes de la interfaz
     */
    private JPanel mainPanel;

    private Cuadricula cuadricula;

    private JButton buttonPlay;

    /**
     * Componentes del menú
     */

    private Main main;

    /**
     * Variables de estado del programa
     */
    private int status = STATUS_READY;
    private JButton buttonKnight;
    private JButton buttonKing;
    private JButton buttonQueen;

    private JButton aumentarCeldas;
    private JButton disminuirCeldas;

    /**
     * Constructor
     *
     * @param title     titulo de la ventana
     * @param main referencia a clase principal
     */
    public View(String title, Main main) {
        super(title);

        this.main = main;

        //Inicializar tableros
        cuadricula = new Cuadricula();

        configureUI();
    }

    /**
     * Mostrar interfaz gráfica
     */
    public void showView() {
        setSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
        setMinimumSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
        revalidate();
        repaint();
    }

    /**
     * Configurar la interfaz gráfica de usuario con Java Swing
     */
    private void configureUI() {
        configureMainPanel();
        configureMenuPanel();
        configureObjectsPanel();

        setContentPane(mainPanel);
    }

    private void configureMainPanel() {

        //Border Layout para poner el menú en la parte de arriba
        mainPanel = new JPanel(new BorderLayout(0, 0));
        mainPanel.add(cuadricula);
    }

    /**
     * Inicializar menu superior
     */
    private void configureMenuPanel() {

        JToolBar menu = new JToolBar();
        menu.setFloatable(false);

        //Configurar botones para redimensionar el tablero
        aumentarCeldas = new JButton("+");
        disminuirCeldas = new JButton("-");

        JLabel labelBoardSizeTitle = new JLabel("Celdas: ");
        JLabel labelBoardSize = new JLabel(" " + cuadricula.getBoardSize() + "x" + cuadricula.getBoardSize() + " ");

        menu.add(labelBoardSizeTitle);

        menu.add(disminuirCeldas);
        menu.add(labelBoardSize);
        menu.add(aumentarCeldas);

        menu.addSeparator(new Dimension(20, 0));

        aumentarCeldas.addActionListener(e -> {
            cuadricula.increaseSize();
            labelBoardSize.setText(" " + cuadricula.getBoardSize() + "x" + cuadricula.getBoardSize() + " ");
        });

        disminuirCeldas.addActionListener(e -> {
            cuadricula.decreaseSize();
            labelBoardSize.setText(" " + cuadricula.getBoardSize() + "x" + cuadricula.getBoardSize() + " ");
        });

        mainPanel.add(menu, BorderLayout.PAGE_START);
    }

    /**
     * Inicializar panel con botones de piezas
     */
    public void configureObjectsPanel() {

        JPanel panelPieces = new JPanel(new GridLayout(0, 2));

        //Inicialización de los botones del menu lateral
        buttonPlay = new JButton();
        buttonPlay.setRequestFocusEnabled(false);

        buttonKnight = new JButton();
        buttonKing = new JButton();
        buttonQueen = new JButton();

        ImageIcon iconMonster = new ImageIcon(new ImageIcon("images/monstruo.png").getImage().getScaledInstance(ICON_SIZE, ICON_SIZE, Image.SCALE_SMOOTH));
        ImageIcon iconTreasure = new ImageIcon(new ImageIcon("images/tesoro.png").getImage().getScaledInstance(ICON_SIZE, ICON_SIZE, Image.SCALE_SMOOTH));
        ImageIcon iconHole = new ImageIcon(new ImageIcon("images/agujero.png").getImage().getScaledInstance(ICON_SIZE, ICON_SIZE, Image.SCALE_SMOOTH));

        buttonPlay.setIcon(new ImageIcon("images/icon_play.png"));
        buttonKnight.setIcon(iconMonster);
        buttonKing.setIcon(iconTreasure);
        buttonQueen.setIcon(iconHole);

        //Configurar los ToolTipText
        buttonPlay.setToolTipText("Play");
        buttonKnight.setToolTipText("Knight");
        buttonKing.setToolTipText("King");
        buttonQueen.setToolTipText("Queen");

        //Knight seleccionado por defecto
        buttonKnight.setSelected(true);

        //Agregar escuchadores de eventos.
        buttonPlay.addActionListener(evt -> {
            switch (status) {
                case STATUS_READY:
                    updateStatus(STATUS_RUNNING);
                    break;
                case STATUS_RUNNING:
                    main.getController().notify("Stop");
                    //updateStatus(STATUS_FINISH);
                    break;
                case STATUS_FINISH:
                    updateStatus(STATUS_READY);
                    break;
            }
            buttonPlay.setSelected(false);
        });

        buttonKnight.addActionListener(evt -> {
            buttonKnight.setSelected(true);
            buttonKing.setSelected(false);
            buttonQueen.setSelected(false);
        });

        buttonKing.addActionListener(evt -> {
            buttonKing.setSelected(true);
            buttonKnight.setSelected(false);
            buttonQueen.setSelected(false);
        });

        buttonQueen.addActionListener(evt -> {
            buttonQueen.setSelected(true);
            buttonKnight.setSelected(false);
            buttonKing.setSelected(false);
        });

        //Añadir los botones al panel del menú.
        panelPieces.add(buttonPlay);
        panelPieces.add(buttonKnight);
        panelPieces.add(buttonKing);
        panelPieces.add(buttonQueen);

        //Añadir el menú a la izquierda.
        mainPanel.add(panelPieces, BorderLayout.WEST);
    }

    /**
     * Actualizar estado de programa
     */
    public void updateStatus(int status) {
        this.status = status;

        switch (status) {
            case STATUS_READY:
                buttonPlay.setIcon(new ImageIcon("images/icon_play.png"));
                buttonPlay.setToolTipText("Play");
                break;
            case STATUS_RUNNING:
                buttonPlay.setIcon(new ImageIcon("images/icon_stop.png"));
                buttonPlay.setToolTipText("Stop");
                main.getController().notify("Start");
                break;
            case STATUS_FINISH:
                buttonPlay.setIcon(new ImageIcon("images/icon_restart.png"));
                buttonPlay.setToolTipText("Restart");
                main.getController().notify("Stop");
                break;
        }

    }

    /**
     * Retornar tablero principal
     *
     * @return tablero
     */
    public Cuadricula getBoard() {
        return cuadricula;
    }


    @Override
    public void notify(String message) {
        //System.out.println("Mensaje en Vista: " + message);

        //Mensaje parada forzada por el usuario
        if (message.startsWith("Stopped")) {
            updateStatus(STATUS_READY);
            JOptionPane.showMessageDialog(this, "Tour stopped by user", "", JOptionPane.PLAIN_MESSAGE, new ImageIcon("images/icon_fail.png"));
        }

        //Mensaje de solución no encontrada.
        if (message.startsWith("Move")) {
            int x = Integer.parseInt(message.split(",")[1]);
            int y = Integer.parseInt(message.split(",")[2]);
            int orientation = Integer.parseInt(message.split(",")[3]);
            cuadricula.setCurrentPosition(x, y);
        }
    }
}
