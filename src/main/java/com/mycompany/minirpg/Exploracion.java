package com.mycompany.minirpg;

import java.awt.BorderLayout;
import java.util.Locale;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Exploracion {

    private JDialog marco;

    private JPanel panelPrincipal, panelSuperior, panelInferior, PanelMonstruo, PanelMonstruoSec;

    private JButton botAtacar, botHuir;

    private JTextArea infoExploracion;
    private JScrollPane barrasDes;

    private Personaje pj;
    private Monstruo enemigo;
    private boolean esJefe = false;

    private static int numExploracion = 0; // para aumentar la dificultad

    private VentanaPrincipal vp; // creamos este atributo de VentanaPrincipal para obtener acceso a nuestros atributos

    public Exploracion(VentanaPrincipal vp) {

        this.vp = vp;

        pj = vp.getPj();

        marco = new JDialog();

        panelPrincipal = new JPanel(new BorderLayout());
        panelSuperior = vp.getPanelSup();
        panelInferior = new JPanel();
        PanelMonstruo = new JPanel();
        PanelMonstruoSec = new JPanel();

        infoExploracion = new JTextArea();
        infoExploracion.setEditable(false);
        barrasDes = new JScrollPane(infoExploracion);
        barrasDes.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        botAtacar = new JButton("Atacar");
        botHuir = new JButton("Huir");

    }

    public void comenzarExploracion() {

        decidirDificultar();
        montarInterfaz();

    }

    private void decidirDificultar() {

        int numAlea = (int) (Math.random() * 100) + numExploracion;
        numExploracion++;

        enemigo = Monstruo.generarMonstruo(numAlea);

        if (enemigo.getNombre().equals("Dragon")) {
            esJefe = true;
        }

    }

    private void montarInterfaz() {
        //Añadimos el area de texto al panel principal
        panelPrincipal.add(barrasDes, BorderLayout.CENTER);

        //Añadimos el monstruo
        PanelMonstruoSec.add(enemigo.getEtNombre());
        PanelMonstruoSec.add(enemigo.getBarraVida());

        PanelMonstruo.setLayout(new BoxLayout(PanelMonstruo, BoxLayout.Y_AXIS));
        PanelMonstruo.add(enemigo.getImagen());
        PanelMonstruo.add(PanelMonstruoSec);

        //Elaboramos panel inferior con sus botones
        botAtacar.addActionListener(e -> atacar());
        botHuir.addActionListener(e -> marco.dispose());

        panelInferior.add(botAtacar);
        panelInferior.add(new JLabel("             "));
        if (esJefe == true) {
            botHuir.setEnabled(false);
        }
        panelInferior.add(botHuir);

        //Añadimos los paneles secundarios al principal
        panelPrincipal.add(panelSuperior, BorderLayout.NORTH);
        panelPrincipal.add(panelInferior, BorderLayout.SOUTH);
        panelPrincipal.add(PanelMonstruo, BorderLayout.EAST);

        marco.add(panelPrincipal);
        if (!enemigo.getNombre().equals("Dragon")) {
            marco.setSize(641, 530);
        } else {
            marco.setSize(800, 550);
        }

        marco.setLocationRelativeTo(null);
        marco.setModal(true);
        marco.setUndecorated(true);
        marco.setVisible(true);

    }

    private void atacar() {

        int damage;

        pj.atacar(enemigo);
        infoExploracion.setText(infoExploracion.getText() + pj.getNombre() + " ataca con una fueza de " + pj.getAtaque() + ".\n");

        damage = pj.getAtaque() - enemigo.getDefensa();
        if (damage <= 0) {
            damage = 1;
        }
        infoExploracion.setText(infoExploracion.getText() + enemigo.getNombre() + " ha recibido " + damage + " de daño gracias a su defensa .\n\n");
        enemigo.establecerVida(enemigo.getVidaActual());

        if (!enemigo.isEstaVivo()) { // si el enemigo no esta vivo
            enemigoDerrotado();

        } else { // si el enemigo esta vivo
            enemigo.atacar(pj);
            infoExploracion.setText(infoExploracion.getText() + enemigo.getNombre() + " ataca con una fueza de " + enemigo.getAtaque() + ".\n");
            damage = enemigo.getAtaque() - pj.getDefensa();

            if (damage <= 0) {
                damage = 1;
            }
            infoExploracion.setText(infoExploracion.getText() + pj.getNombre() + " ha recibido " + damage + " de daño gracias a su defensa .\n\n");
            pj.establecerVida(pj.getVidaActual()); // cambia visiblemente la barra de vida

            if (!pj.isEstaVivo()) { //si el pj no esta vivo
                pjDerrotado();
            } else { // si el pj esta vivo

            }
        }

    }

    private void enemigoDerrotado() {

        botAtacar.setEnabled(false);
        botHuir.setText("Salir");

        infoExploracion.setText(infoExploracion.getText() + "¡EL ENEMIGO " + enemigo.getNombre().toUpperCase() + " HA SIDO DERROTADO! \n" + "Has obtenido " + enemigo.getPremioOro() + " de ORO.\n" + "Ganas " + enemigo.getPremioExp() + " de EXP");

        pj.subirExp(enemigo.getPremioExp());
        vp.getEtExp().setText(" Exp: " + pj.getExp() + " / " + pj.getExpNecesaria()); // obtenemos nuestra exp y la actualizamos
        vp.getEtNivel().setText(" Lvl: " + pj.getNivel()); // obtenemos nuestro nivel y lo actualizamos
        vp.getEtAtributos().setText(" Atq: " + pj.getAtaque() + " | " + " Def: " + pj.getDefensa() + " | " + " Vida: ");

        pj.setOro(pj.getOro() + enemigo.getPremioOro());
        vp.getEtOro().setText(" Oro: " + pj.getOro());

        if (esJefe) {
            VentanaFinal f = new VentanaFinal(VentanaFinal.VICTORIA, pj);
            f.abrir();

        }

    }

    private void pjDerrotado() {

        VentanaFinal f = new VentanaFinal(VentanaFinal.DERROTA, pj);
        f.abrir();

    }

    public static void setNumExploracion(int numExploracion) {
        Exploracion.numExploracion = numExploracion;
    }

}
