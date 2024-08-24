package com.mycompany.minirpg;

import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class VentanaFinal {

    private JTextArea areaTexto;
    private JLabel imagen;
    private JButton salir;
    private ImageIcon rutaImagen;

    private JDialog marco;
    private JPanel panelPrincipal;

    private int condicion;
    private Personaje pj;

    public static final int VICTORIA = 0;
    public static final int DERROTA = 1;

    public VentanaFinal(int condicion, Personaje pj) {

        marco = new JDialog();
        panelPrincipal = new JPanel(new BorderLayout());

        areaTexto = new JTextArea();
        salir = new JButton("Finalizar");

        this.condicion = condicion;
        this.pj = pj;

        if (condicion == VICTORIA) {
            rutaImagen = new ImageIcon("./imagenes/victoria.png");
        } else {
            rutaImagen = new ImageIcon("./imagenes/derrota.png");
        }
        imagen = new JLabel(rutaImagen);
    }

    public void abrir() {

        prepararMensaje();
        montarEscena();
        marco.setUndecorated(true);
        marco.setVisible(true);
    }

    private void montarEscena() {
        //Añadimos a la parte norte
        panelPrincipal.add(imagen, BorderLayout.NORTH);

        //Añadimos area texto al centro de la pantalla
        panelPrincipal.add(areaTexto, BorderLayout.CENTER);

        //Añadimos botón a la parte sur
        salir.addActionListener(e -> System.exit(0));
        panelPrincipal.add(salir, BorderLayout.SOUTH);

        marco.add(panelPrincipal);
        marco.setSize(800, 550);
        marco.setLocationRelativeTo(null);
        marco.setModal(true);

    }

    private void prepararMensaje() {

        String mensajeFinal;

        if (condicion == VICTORIA) {
            mensajeFinal = "¡Has conseguido derrotar al señor del castillo! Vuelves a tu hogar con una gran victoria. \n" + "Tienes heridas que nunca podrás borrar. Has vencido, pero... ¿A qué precio? \n" + pj.getNombre() + " Nivel: " + pj.getNivel() + " Has logrado traer contigo " + pj.getOro() + " monedas de oro.";
        } else {
            mensajeFinal = "Fuiste aniquilado en el castillo. Tus seres queridos enterraron lo que quedaba de ti. \n" + "En poco tiempo todos se olvidaran de ti. \n" + "Vuelve a intentarlo si te atreves.";
        }

        areaTexto.setText(mensajeFinal);
    }

}
