package com.mycompany.marcopanelpersonalizado;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JPanel;

public class PanelPer extends JPanel {

    public static final int FLOWLAYOUT = 0;
    public static final int BORDERLAYOUT = 1;
    public static final int GRIDLAYOUT = 2;

    public PanelPer(int LayoutPorDefecto) {

        if (LayoutPorDefecto == 1) {
            setLayout(new BorderLayout());
        }
        if (LayoutPorDefecto == 2) {
            setLayout(new GridLayout());
        }

    }

}
