package com.mycompany.minirpg;

public class MiniRPG {

    public static void main(String[] args) {
        Personaje pj = new Personaje("Juan",10,3,80);
        
        VentanaPrincipal juego = new VentanaPrincipal(pj);
        
        juego.ComenzarJuego();
    }
}
