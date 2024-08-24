package com.mycompany.minirpg;

public class Personaje extends Entidad {

    private int nivel, oro, exp, expNecesaria;

    public Personaje(String nombre, int ataque, int defensa, double vidaMax) {
        super(nombre, ataque, defensa, vidaMax);
        nivel = 1;
        oro = 0;
        exp = 0;
        expNecesaria = 10;
    }

    public int getNivel() {
        return nivel;
    }

    private void subirNivel() {
        nivel++;
        setAtaque(getAtaque() + 2);
        setDefensa(getDefensa() + 1);
        setVidaMax(getVidaMax() * 1.1);
        getBarraVida().setMaximum((int)getVidaMax()); // cambiamos nuestra barra de vida para mostrar la nueva vida maxima
        setVidaActual((int)getVidaMax());// recuperamos la vida 
        establecerVida((int)getVidaMax());
        expNecesaria += (expNecesaria + 5);
    }

    public int getExp() {
        return exp;
    }

    public void subirExp(int cantidad) {
        exp += cantidad;
        while(exp >= expNecesaria) {
            subirNivel();
        }
    }

    public int getExpNecesaria() {
        return expNecesaria;
    }

    public int getOro() {
        return oro;
    }

    public void setOro(int oro) {
        this.oro = oro;
    }

}
