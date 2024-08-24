package com.mycompany.minirpg;

import com.mycompany.marcopanelpersonalizado.FramePer;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.*;

public class VentanaPrincipal {

    private FramePer marco;
    private JPanel panelPrincipal, panelSuperior, panelInferior;

    private JLabel etNombre, etNivel, etExp, etOro, etAtributos;
    private JLabel etImagen;

    private JButton botExplorar;
    private JButton botTienda;

    private Personaje pj; // creamos un personaje para poder llenar los JLabels

    public VentanaPrincipal(Personaje pj) {

        this.pj = pj;

        marco = new FramePer(600, 500, "Mini RPG", true);

        panelPrincipal = new JPanel(new BorderLayout());
        panelSuperior = new JPanel();
        panelInferior = new JPanel();

        //completamos los JLabel con los atributos del personaje
        etNombre = new JLabel(pj.getNombre()+ " | ");
        etNivel = new JLabel(" Lvl: " + pj.getNivel() + " | ");
        etExp = new JLabel(" Exp: " + pj.getExp() + " / " + pj.getExpNecesaria()+ " | ");
        etOro = new JLabel(" Oro: " + pj.getOro()+ " | ");
        etAtributos = new JLabel(" Atq: " + pj.getAtaque() + " | "+" Def: " + pj.getDefensa()+ " | " + " Vida: ");

        etImagen = new JLabel();

        botExplorar = new JButton("Explorar");
        botTienda = new JButton("Tienda");
    }

    public void ComenzarJuego() {
        montarEscena();
       
        marco.setVisible(true);
        marco.setResizable(false);
    }

    private void montarEscena() {

        //Elaboramos panel superior con los datos del pj
        modificarFuentes();
        panelSuperior.add(etNombre);
        panelSuperior.add(etNivel);
        panelSuperior.add(etExp);
        panelSuperior.add(etOro);
        panelSuperior.add(etAtributos);
        panelSuperior.add(pj.getBarraVida());

        //Elaboramos la imagen central de la pantalla
        etImagen.setIcon(new ImageIcon("./imagenes/Castillo.jpg"));
        panelPrincipal.add(etImagen, BorderLayout.CENTER);// imagen en el medio

        //Añanadimos nuestros botones al panel inferior
        botExplorar.addActionListener(e -> nuevaExploracion()); // le agregamos una accion al boton
        botTienda.addActionListener(e->abrirTienda());
        panelInferior.add(botExplorar); // agregamos el boton al panel inferior
        panelInferior.add(botTienda);

        //Añadimos panales secundarios al panel principal
        panelPrincipal.add(panelSuperior, BorderLayout.NORTH);
        panelPrincipal.add(panelInferior, BorderLayout.SOUTH);

        //Añandimos panel principal al marco
        marco.add(panelPrincipal);

    }

    private void nuevaExploracion() {

        Exploracion ex = new Exploracion(this);
        ex.comenzarExploracion();
        
        //Codigo para solucionar problemas con la barra superior al desplazar fuera de la pantalla
        panelPrincipal.add(panelSuperior, BorderLayout.NORTH);
        marco.repaint();

    }

    private void modificarFuentes() {
        
        Font miFuente = new Font("Roboto", Font.BOLD, 20);
        etNombre.setFont(miFuente);
       
    }

    public Personaje getPj() {
        return pj;
    }    
    
    public JPanel getPanelSup() { // metodo para devolver el panel superiror donde se encuentras los atributos y asi no tener que repetir todo el proceso de creacion
        return panelSuperior;
    }

    public JLabel getEtNivel() {
        return etNivel;
    }

    public JLabel getEtExp() {
        return etExp;
    }


    public JLabel getEtOro() {
        return etOro;
    }

    public JLabel getEtAtributos() {
        return etAtributos;
    }

    private void abrirTienda() {
        
        Tienda t = new Tienda(this);// this para pasar esta ventana principal
        t.abrirTienda();
        panelPrincipal.add(panelSuperior, BorderLayout.NORTH);
        marco.repaint();
        
    }

}
