package com.mycompany.minirpg;

import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Monstruo extends Entidad {

    private int premioOro;
    private int premioExp;
    private JLabel etNombre, imagen;

    private static String[] nombresFacil = {"Zombie","Golem","Esqueleto","Lobo"};
    private static String[] nombresMedio = {"Troll","Momia","Gargola"};
    private static String[] nombresDificil = {"Satanas","Mago","Brujo","Demonio"};
    private static String[] nombresJefe = {"Dragon"}; 

    

    public Monstruo(String nombre, int ataque, int defensa, double vidaMax, String dificultad) { //Agregamos el atributo Dificultad
        super(nombre, ataque, defensa, vidaMax);

        etNombre = new JLabel(nombre);
        etNombre.setFont(new Font("Roboto", Font.BOLD, 20));
        String rutaImagen = "./imagenes/" + nombre.toLowerCase() + ".png"; //Ruta para cambiar la imagen
        imagen = new JLabel();
        imagen.setIcon(new ImageIcon(rutaImagen));

        switch (dificultad) {

            case "facil":
                premioExp = (int) (Math.random() * 2 + 1);
                premioOro = (int) (Math.random() * 5 + 1);
                etNombre.setForeground(Color.GREEN);
                break;
            case "medio":
                premioExp = (int) (Math.random() * 6 + 2);
                premioOro = (int) (Math.random() * 20 + 1);
                etNombre.setForeground(Color.BLUE);
                break;
            case "dificil":
                premioExp = (int) (Math.random() * 16 + 5);
                premioOro = (int) (Math.random() * 50 + 1);
                etNombre.setForeground(Color.ORANGE);
                break;
            default:
                premioExp = 500;
                premioOro = 1000;
                etNombre.setForeground(Color.RED);
                break;
        }
    }

    public static Monstruo generarMonstruo(int i) {

        Monstruo m;

        //Establecemos las caracteristicas del Monstruo de manera aleatoria
        int nMonstruo = (int) (Math.random() * 3);
        int nVida = (int) (Math.random() * 30);
        int nAtaque = (int) (Math.random() * 5);
        int nDefensa = (int) (Math.random() * 2);

        if (i < 80) {
            m = new Monstruo(nombresFacil[nMonstruo], nAtaque + 1, nDefensa, nVida + 15, "facil");
        } else if (i < 140) {
            m = new Monstruo(nombresMedio[nMonstruo], nAtaque + 4, nDefensa + 2, nVida + 30, "medio");
        } else if (i < 200) {
            m = new Monstruo(nombresDificil[nMonstruo], nAtaque + 8, nDefensa + 5, nVida + 85, "dificil");
        } else {
            m = new Monstruo("Dragon", nAtaque + 15, nDefensa + 10, nVida + 150, "jefe");
        }
        
        return m;

    }

    public int getPremioOro() {
        return premioOro;
    }

    public int getPremioExp() {
        return premioExp;
    }

    public JLabel getEtNombre() {
        return etNombre;
    }

    public JLabel getImagen() {
        return imagen;
    }

}
