package com.mycompany.marcopanelpersonalizado;

import javax.swing.JFrame;

public class FramePer extends JFrame {
    
    public FramePer(int ancho, int alto, String titulo, boolean esPrincipal){
        
        setSize(ancho, alto);
        setTitle(titulo);
        setLocationRelativeTo(null);
        
        if(esPrincipal) setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
}
