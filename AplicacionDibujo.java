import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AplicacionDibujo extends JFrame {
    private JPanel lienzo;
    private Color colorSeleccionado = Color.BLACK;
    private int xAnterior, yAnterior;

    public AplicacionDibujo() {
        super("Aplicación de Dibujo");

        // Configuración del lienzo
        lienzo = new JPanel() {
            private int xAnterior, yAnterior;

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
            }

            @Override
            public Dimension getPreferredSize() {
                return new Dimension(600, 400);
            }
        };
        lienzo.setBackground(Color.WHITE);
        lienzo.addMouseMotionListener(new MouseMotionAdapter(){
            public void mouseDragged(MouseEvent e){
                Graphics g = lienzo.getGraphics();
                g.setColor(colorSeleccionado);
                g.drawLine(xAnterior, yAnterior, e.getX(), e.getY());
                xAnterior = e.getX();
                yAnterior = e.getY();
            }
        });
        lienzo.addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent e){
                xAnterior = e.getX();
                yAnterior = e.getY();
            }
        });
        getContentPane().add(lienzo, BorderLayout.CENTER);

        // Configuración del panel de colores
        JPanel panelColores = new JPanel();
        panelColores.setLayout(new GridLayout(1, 5));
        JButton[] botonesColores = {
            new JButton("Negro"),
            new JButton("Rojo"),
            new JButton("Verde"),
            new JButton("Azul"),
            new JButton("Borrar")
        };
        Color[] colores = {Color.BLACK, Color.RED, Color.GREEN, Color.BLUE, Color.WHITE};
        for (int i = 0; i < botonesColores.length; i++) {
            JButton botonColor = botonesColores[i];
            botonColor.setBackground(colores[i]);
            botonColor.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (botonColor.getText().equals("Borrar")) {
                        colorSeleccionado = Color.WHITE;
                    } else {
                        colorSeleccionado = botonColor.getBackground();
                    }
                }
            });
            panelColores.add(botonColor);
        }
        getContentPane().add(panelColores, BorderLayout.SOUTH);

        // Configuración de la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new AplicacionDibujo();
            }
        });
    }
}
