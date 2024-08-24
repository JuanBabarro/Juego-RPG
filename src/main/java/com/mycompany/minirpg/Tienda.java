package com.mycompany.minirpg;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Tienda {

    private JDialog marco;
    private JPanel panelPrincipal, panelSuperior, panelInferior, panelTienda, panelEspada, panelEscudo, panelPocion, panelMapa;
    private JLabel imagenEspada, imagenEscudo, imagenPocion, imagenMapa, desEspada, desEscudo, desPocion, desMapa;
    private JButton botEspada, botEscudo, botPocion, botMapa, botSalir;
    private static boolean agoEspada, agoEscudo, agoPocion, agoMapa;
    private VentanaPrincipal vp; // para poder acceder a la ventana principal
    private Personaje pj;

    public Tienda(VentanaPrincipal vp) {
        this.vp = vp;
        pj = vp.getPj(); //recuperamos el personaje al crear una variable tipo VentanaPrincipal
        marco = new JDialog();

        panelPrincipal = new JPanel(new BorderLayout());
        panelSuperior = vp.getPanelSup();
        panelInferior = new JPanel();
        panelTienda = new JPanel(new GridLayout(2, 2));

        panelEspada = new JPanel();
        panelEscudo = new JPanel();
        panelPocion = new JPanel();
        panelMapa = new JPanel();

        imagenEspada = new JLabel(new ImageIcon("./imagenes/espada.png"));
        imagenEscudo = new JLabel(new ImageIcon("./imagenes/escudo.png"));
        imagenPocion = new JLabel(new ImageIcon("./imagenes/pocion.png"));
        imagenMapa = new JLabel(new ImageIcon("./imagenes/mapa.png"));

        desEspada = new JLabel("Espada - 100 oro.");
        desEscudo = new JLabel("Escudo - 100 oro.");
        desPocion = new JLabel("Pocion curativa - 50 oro.");
        desMapa = new JLabel("Mapa - 25 oro.");

        botEspada = new JButton("Comprar");
        botEscudo = new JButton("Comprar");
        botPocion = new JButton("Comprar");
        botMapa = new JButton("Comprar");
        botSalir = new JButton("Salir");

    }

    public void abrirTienda() {

        montarInterfaz();
        marco.setUndecorated(true);
        marco.setVisible(true);

    }

    private void montarInterfaz() {
        //panel superior
        panelPrincipal.add(panelSuperior, BorderLayout.NORTH);

        //panel zona central, con addObjeto le damos forma a la tienda
        addObjeto(panelEspada, imagenEspada, desEspada, botEspada, "espada", agoEspada);
        addObjeto(panelEscudo, imagenEscudo, desEscudo, botEscudo, "escudo", agoEscudo);
        addObjeto(panelPocion, imagenPocion, desPocion, botPocion, "pocion", agoPocion);
        addObjeto(panelMapa, imagenMapa, desMapa, botMapa, "mapa", agoMapa);

        //panel principal
        panelPrincipal.add(panelTienda, BorderLayout.CENTER);

        //panel inferior
        botSalir.addActionListener(e -> marco.dispose());
        panelInferior.add(botSalir);

        panelPrincipal.add(panelInferior, BorderLayout.SOUTH);

        marco.setSize(600, 600);
        marco.setLocationRelativeTo(null);
        marco.setModal(true);
        marco.add(panelPrincipal);

    }

    private void addObjeto(JPanel panelObjeto, JLabel imagen, JLabel descrpcion, JButton boton, String nombre, boolean agotado) {

        imagen.setAlignmentX(Component.CENTER_ALIGNMENT);// acomodamos las imagenes de manera central
        descrpcion.setAlignmentX(Component.CENTER_ALIGNMENT);// acomodamos la descripcion de manera central
        boton.setAlignmentX(Component.CENTER_ALIGNMENT);// acomodamos el boton de manera central

        if (agotado) {
            boton.setEnabled(false);
        }
        boton.addActionListener(e -> comprarObjeto(boton, nombre));// agregamos una action a los botones pasando el nombre como parametro

        panelObjeto.setLayout(new BoxLayout(panelObjeto, BoxLayout.Y_AXIS));
        panelObjeto.add(imagen);
        panelObjeto.add(descrpcion);
        panelObjeto.add(boton);

        panelTienda.add(panelObjeto);
    }

    private void comprarObjeto(JButton boton, String nombre) {

        switch (nombre) {
            case "espada":
                if (pj.getOro() >= 100) {
                    pj.setAtaque(pj.getAtaque() + 3);
                    vp.getEtAtributos().setText(" Atq: " + pj.getAtaque() + " | " + " Def: " + pj.getDefensa() + " | " + " Vida: "); // modificamos los datos de la ventana superior
                    pj.setOro(pj.getOro() - 100);
                    vp.getEtOro().setText(" Oro: " + pj.getOro());
                    boton.setEnabled(false);
                    agoEspada = true;
                }
                break;

            case "escudo":
                if (pj.getOro() >= 100) {
                    pj.setDefensa(pj.getDefensa() + 1);
                    vp.getEtAtributos().setText(" Atq: " + pj.getAtaque() + " | " + " Def: " + pj.getDefensa() + " | " + " Vida: "); // modificamos los datos de la ventana superior
                    pj.setOro(pj.getOro() - 100);
                    vp.getEtOro().setText(" Oro: " + pj.getOro());
                    boton.setEnabled(false);
                    agoEscudo = true;
                }
                break;

            case "pocion":
                if (pj.getOro() >= 50) {
                    pj.setVidaActual((int) pj.getVidaMax());
                    pj.establecerVida(pj.getVidaActual());
                    vp.getEtAtributos().setText(" Atq: " + pj.getAtaque() + " | " + " Def: " + pj.getDefensa() + " | " + " Vida: "); // modificamos los datos de la ventana superior
                    pj.setOro(pj.getOro() - 50);
                    vp.getEtOro().setText(" Oro: " + pj.getOro());
                    boton.setEnabled(false);
                    agoPocion = true;
                }
                break;
            case "mapa":
                if (pj.getOro() >= 10) {
                    Exploracion.setNumExploracion(250);
                    vp.getEtAtributos().setText(" Atq: " + pj.getAtaque() + " | " + " Def: " + pj.getDefensa() + " | " + " Vida: "); // modificamos los datos de la ventana superior
                    pj.setOro(pj.getOro() - 10);
                    vp.getEtOro().setText(" Oro: " + pj.getOro());
                    boton.setEnabled(false);
                    agoMapa = true;
                }
                break;

        }
    }

}
